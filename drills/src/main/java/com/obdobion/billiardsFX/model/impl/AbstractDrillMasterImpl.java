package com.obdobion.billiardsFX.model.impl;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillLookupReference;
import com.obdobion.billiardsFX.model.DrillPackageManager;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.IDrillMasterObserver;
import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.drills.AbstractDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.DrillFactory;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillCommand;
import com.obdobion.billiardsFX.model.drills.IDrillPackage;
import com.obdobion.billiardsFX.model.drills.IDrillPackageManager;
import com.obdobion.billiardsFX.model.drills.IDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * Abstract AbstractDrillMasterImpl class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
abstract public class AbstractDrillMasterImpl implements IDrillMaster
{
    final static Logger                       logger    = LoggerFactory.getLogger(IDrillMaster.class);

    protected IDrillPackageManager            dpm;

    List<WeakReference<IDrillMasterObserver>> observers = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void addObserver(final IDrillMasterObserver observer)
    {
        for (final WeakReference<IDrillMasterObserver> ref : observers)
        {
            final IDrillMasterObserver obs = ref.get();
            if (obs == observer)
                return;
        }
        logger.debug("addObserver {}", observer);
        observers.add(new WeakReference<>(observer));
    }

    /** {@inheritDoc} */
    @Override
    public void addPackage(final IDrillPackage pkg)
    {
        getDrillPackageManager().add(pkg);
    }

    /** {@inheritDoc} */
    @Override
    public List<DrillLookupReference> availableDrills(final Context context, final DrillLookupFilter filter)
    {
        logger.debug("filter: {}", filter.toString());
        final List<DrillLookupReference> availableDrills = new ArrayList<>();
        for (final IDrillPackage pkg : getDrillPackageManager().getPackages())
        {
            for (final DrillFactory drillFactory : pkg.availableDrills(filter))
            {
                final DrillLookupReference drillRef = new DrillLookupReference();
                drillRef.drillID = drillFactory.drillID;
                drillRef.drillTitleRef = drillFactory.drillTitleRef;
                drillRef.tags = drillFactory.tags;
                availableDrills.add(drillRef);
            }
        }
        logger.debug("found {} available drills", availableDrills.size());
        return availableDrills;
    }

    /** {@inheritDoc} */
    @Override
    public IDrill createDrill(final Context context, final DrillLookupReference drillRef) throws InstantiationException,
            IllegalAccessException
    {
        final DrillLookupFilter filter = new DrillLookupFilter(drillRef.drillID);
        for (final IDrillPackage pkg : getDrillPackageManager().getPackages())
        {
            for (final DrillFactory drillFactory : pkg.availableDrills(filter))
            {
                final IDrill drill = drillFactory.newInstance(context);
                logger.debug("created a new drill: {}", drill);
                return drill;
            }
        }
        throw new InstantiationException("invalid drill reference: " + drillRef.drillID);
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateTime currentDateTime()
    {
        /*
         * Must always be a fresh instance.
         */
        try
        {
            return CalendarFactory.now();
        } catch (final ParseException e)
        {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public IDrillCommand execute(final Context context, final IDrillCommand command)
    {
        command.execute();
        return command;
    }

    /** {@inheritDoc} */
    @Override
    public Context getContext(final String sessionId, final int transactionNumber)
    {
        return Context.getInstance(sessionId, transactionNumber);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * getDrillPackageManager.
     * </p>
     */
    @Override
    public IDrillPackageManager getDrillPackageManager()
    {
        if (dpm == null)
            synchronized (this)
            {
                dpm = new DrillPackageManager();
            }
        return dpm;
    }

    /**
     * <p>
     * getDrillPackages.
     * </p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<IDrillPackage> getDrillPackages()
    {
        return dpm.getPackages();
    }

    /** {@inheritDoc} */
    @Override
    public IDrillSummarizer getDrillSummarizer(final Context context, final SaveStoreFilter filter)
            throws InstantiationException
    {
        return AbstractDrillSummarizer.instanceFor(filter);
    }

    /** {@inheritDoc} */
    @Override
    public void notifyDrillCreatedObservers(final Context context, final IDrill drill)
    {
        final List<WeakReference<IDrillMasterObserver>> removeThese = new ArrayList<>();

        for (final WeakReference<IDrillMasterObserver> ref : observers)
        {
            final IDrillMasterObserver obs = ref.get();
            if (obs != null)
            {
                logger.trace("notifying drill created observer {}", obs);
                obs.drillCreatedEvent(drill);
            } else
            {
                removeThese.add(ref);
            }
        }
        observers.removeAll(removeThese);
    }

    /** {@inheritDoc} */
    @Override
    public void notifyDrillDestroyedObservers(final Context context, final IDrill drill)
    {
        final List<WeakReference<IDrillMasterObserver>> removeThese = new ArrayList<>();

        for (final WeakReference<IDrillMasterObserver> ref : observers)
        {
            final IDrillMasterObserver obs = ref.get();
            if (obs != null)
            {
                logger.trace("notifying drill destroyed observer {}", obs);
                obs.drillDestroyedEvent(drill);
            } else
                removeThese.add(ref);
        }
        observers.removeAll(removeThese);
    }

    /** {@inheritDoc} */
    @Override
    public String resolveResourceReference(final String rr)
    {
        return rr;
    }

    /** {@inheritDoc} */
    @Override
    public IDrillPackageManager setDrillPackageManager(final Context context, final IDrillPackageManager newDPM)
    {
        final IDrillPackageManager oldDPM = dpm;
        dpm = newDPM;
        return oldDPM;
    }

    /** {@inheritDoc} */
    @Override
    public void start()
    {}

    /** {@inheritDoc} */
    @Override
    public void stop()
    {}

    /** {@inheritDoc} */
    @Override
    public void undo(final Context context, final IDrillCommand command)
    {
        command.undo();
    }

    /** {@inheritDoc} */
    @Override
    public ArrayList<String> xLabelsFor(final List<IDrillSummary> results)
    {
        final ArrayList<String> labels = new ArrayList<>(results.size());
        final StringBuilder sb = new StringBuilder();

        for (final IDrillSummary result : results)
        {
            if (labels.isEmpty() || result.getDay() == 1)
            {
                sb.append(resolveResourceReference("month" + result.getMonth() + "3char"));
            }
            sb.append(result.getDay());
            labels.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return labels;
    }
}
