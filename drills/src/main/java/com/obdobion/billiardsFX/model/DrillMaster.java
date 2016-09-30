package com.obdobion.billiardsFX.model;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillCommand;
import com.obdobion.billiardsFX.model.drills.IDrillPackage;
import com.obdobion.billiardsFX.model.drills.IDrillPackageManager;
import com.obdobion.billiardsFX.model.drills.IDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;

/**
 * This is a singleton that holds onto the major components of the application.
 * Ask for a manager and then interact with its interface.
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrillMaster
{
    private static IDrillMaster  instance;

    private static AtomicInteger transactionNumber = new AtomicInteger(1);

    /**
     * <p>
     * addPackage.
     * </p>
     *
     * @param pkg a
     *            {@link com.obdobion.billiardsFX.model.drills.IDrillPackage}
     *            object.
     */
    public static void addPackage(final IDrillPackage pkg)
    {
        try (final Context context = Context.getInstance(transactionNumber.getAndIncrement()))
        {
            instance.addPackage(pkg);
        }
    }

    /**
     * <p>
     * availableDrills.
     * </p>
     *
     * @param filter a
     *            {@link com.obdobion.billiardsFX.model.DrillLookupFilter}
     *            object.
     * @return a {@link java.util.List} object.
     */
    public static List<DrillLookupReference> availableDrills(final DrillLookupFilter filter)
    {
        try (final Context context = Context.getInstance(transactionNumber.getAndIncrement()))
        {
            return instance.availableDrills(context, filter);
        }
    }

    /**
     * <p>
     * createDrill.
     * </p>
     *
     * @param drillRef a
     *            {@link com.obdobion.billiardsFX.model.DrillLookupReference}
     *            object.
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDrill}
     *         object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.lang.IllegalAccessException if any.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static IDrill createDrill(final String sessionId, final DrillLookupReference drillRef)
            throws InstantiationException, IllegalAccessException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.createDrill(context, drillRef);
        }
    }

    /**
     * <p>
     * createDrillFromSaveStore.
     * </p>
     *
     * @param filter a SaveStoreFilter object.
     * @param mostRecentLimit a int.
     * @return a {@link java.util.List} object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.io.IOException if any.
     * @throws java.text.ParseException if any.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static List<IDrill> createDrillFromSaveStore(final String sessionId, final SaveStoreFilter filter,
            final int mostRecentLimit)
                    throws InstantiationException, IOException, ParseException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.createDrillFromSaveStore(context, filter, mostRecentLimit);
        }
    }

    /**
     * <p>
     * createDrillFromTempStore.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDrill}
     *         object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.io.IOException if any.
     * @throws java.text.ParseException if any.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static IDrill createDrillFromTempStore(final String sessionId)
            throws InstantiationException, IOException, ParseException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.createDrillFromTempStore(context);
        }
    }

    /**
     * <p>
     * currentDateTime.
     * </p>
     *
     * @return a {@link java.time.LocalDateTime} object.
     */
    static public LocalDateTime currentDateTime()
    {
        return instance.currentDateTime();
    }

    /**
     * <p>
     * execute.
     * </p>
     *
     * @param command a object.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.IDrillCommand}
     *         object.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static IDrillCommand execute(final String sessionId, final IDrillCommand command)
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.execute(context, command);
        }
    }

    /**
     * <p>
     * getConfigParm.
     * </p>
     *
     * @param parmKey a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     * @param sessionId a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    public static ConfigParm getConfigParm(final String sessionId, final String parmKey) throws IOException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.getConfigParm(context, parmKey);
        }
    }

    /**
     * <p>
     * getDrillAnalyzer.
     * </p>
     *
     * @param filter a
     *            {@link com.obdobion.billiardsFX.model.SaveStoreFilter}
     *            object.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.IDrillSummarizer}
     *         object.
     * @throws java.lang.InstantiationException if any.
     */
    public static IDrillSummarizer getDrillAnalyzer(final SaveStoreFilter filter)
            throws InstantiationException
    {
        try (final Context context = Context.getInstance(transactionNumber.getAndIncrement()))
        {
            return instance.getDrillSummarizer(context, filter);
        }
    }

    /**
     * <p>
     * getDrillStatus.
     * </p>
     *
     * @param drillID a {@link java.lang.String} object.
     * @return a {@link com.obdobion.billiardsFX.model.DrillStatus}
     *         object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.io.IOException if any.
     * @throws java.text.ParseException if any.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static DrillStatus getDrillStatus(final String sessionId, final String drillID)
            throws InstantiationException, IOException, ParseException
    {
        try (final Context context = instance.getContext(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.getDrillStatus(context, drillID);
        }
    }

    /**
     * <p>
     * notifyDrillCreatedObservers.
     * </p>
     *
     * @param drill a {@link com.obdobion.billiardsFX.model.drills.IDrill}
     *            object.
     * @param context a {@link com.obdobion.billiardsFX.model.Context}
     *            object.
     */
    public static void notifyDrillCreatedObservers(final Context context, final IDrill drill)
    {
        instance.notifyDrillCreatedObservers(context, drill);
    }

    /**
     * <p>
     * notifyDrillDestroyedObservers.
     * </p>
     *
     * @param drill a {@link com.obdobion.billiardsFX.model.drills.IDrill}
     *            object.
     * @param context a {@link com.obdobion.billiardsFX.model.Context}
     *            object.
     */
    public static void notifyDrillDestroyedObservers(final Context context, final IDrill drill)
    {
        instance.notifyDrillDestroyedObservers(context, drill);
    }

    /**
     * <p>
     * resolveResourceReference.
     * </p>
     *
     * @param rr a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String resolveResourceReference(final String rr)
    {
        return instance.resolveResourceReference(rr);
    }

    /**
     * <p>
     * saveDrillToSaveStore.
     * </p>
     *
     * @throws java.text.ParseException if any.
     * @throws java.lang.InstantiationException if any.
     * @param drill a {@link com.obdobion.billiardsFX.model.drills.IDrill}
     *            object.
     * @return a {@link com.obdobion.billiardsFX.model.SaveStoreFilter}
     *         object.
     * @throws java.io.IOException if any.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static SaveStoreFilter saveDrillToSaveStore(final String sessionId, final IDrill drill)
            throws IOException, InstantiationException, ParseException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            return instance.saveDrillToSaveStore(context, drill);
        }
    }

    /**
     * <p>
     * saveDrillToTempStore.
     * </p>
     *
     * @param drill a {@link com.obdobion.billiardsFX.model.drills.IDrill}
     *            object.
     * @throws java.io.IOException if any.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static void saveDrillToTempStore(final String sessionId, final IDrill drill) throws IOException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            instance.saveDrillToTempStore(context, drill);
        }
    }

    /**
     * <p>
     * setConfigParm.
     * </p>
     *
     * @param sessionId a {@link java.lang.String} object.
     * @param parm a {@link com.obdobion.billiardsFX.model.ConfigParm}
     *            object.
     * @throws java.io.IOException if any.
     */
    public static void setConfigParm(final String sessionId, final ConfigParm parm)
            throws IOException
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            instance.setConfigParm(context, parm);
        }
    }

    /**
     * <p>
     * setDrillPackageManager.
     * </p>
     *
     * @param newDPM a
     *            {@link com.obdobion.billiardsFX.model.drills.IDrillPackageManager}
     *            object.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.IDrillPackageManager}
     *         object.
     */
    public static IDrillPackageManager setDrillPackageManager(final IDrillPackageManager newDPM)
    {
        try (final Context context = Context.getInstance(transactionNumber.getAndIncrement()))
        {
            return instance.setDrillPackageManager(context, newDPM);
        }
    }

    /**
     * <p>
     * Setter for the field <code>instance</code>.
     * </p>
     *
     * @param newDS a {@link com.obdobion.billiardsFX.model.IDrillMaster}
     *            object.
     * @return a {@link com.obdobion.billiardsFX.model.IDrillMaster}
     *         object.
     */
    synchronized public static IDrillMaster setInstance(final IDrillMaster newDS)
    {
        try (final Context context = Context.getInstance(transactionNumber.getAndIncrement()))
        {
            final IDrillMaster oldDS = instance;
            instance = newDS;
            return oldDS;
        }
    }

    /**
     * <p>
     * undo.
     * </p>
     *
     * @param command a
     *            {@link com.obdobion.billiardsFX.model.drills.IDrillCommand}
     *            object.
     * @param sessionId a {@link java.lang.String} object.
     */
    public static void undo(final String sessionId, final IDrillCommand command)
    {
        try (final Context context = Context.getInstance(sessionId, transactionNumber.getAndIncrement()))
        {
            instance.undo(context, command);
        }
    }

    /**
     * <p>
     * xLabelsFor.
     * </p>
     *
     * @param summaries a {@link java.util.List} object.
     * @return a {@link java.util.ArrayList} object.
     */
    public static ArrayList<String> xLabelsFor(final List<IDrillSummary> summaries)
    {
        return instance.xLabelsFor(summaries);
    }

}
