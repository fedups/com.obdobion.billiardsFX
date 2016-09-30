package com.obdobion.billiardsFX.model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.obdobion.billiardsFX.model.IDrillMasterObserver;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillObserver;
import com.obdobion.billiardsFX.model.impl.DrillMasterFileImpl;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * JUnitDrillMasterImpl class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class JUnitDrillMasterImpl extends DrillMasterFileImpl implements IDrillObserver, IDrillMasterObserver
{
    private final static Logger logger         = Logger.getLogger(JUnitDrillMasterImpl.class);
    /** Constant <code>SAVE_STORE_DIR="\\tmp\\DrillMaster\\""</code> */
    @SuppressWarnings("hiding")
    public static final String  SAVE_STORE_DIR = "target\\savestore\\DrillMaster\\";
    /** Constant <code>J_UNIT_SESSION="jUnitSession"</code> */
    public static final String  J_UNIT_SESSION = "jUnitSession";
    LocalDateTime               currentDateTime;

    /**
     * <p>
     * clearSaveStore.
     * </p>
     */
    public void clearSaveStore()
    {
        deleteDirectory(new File(SAVE_STORE_DIR));
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateTime currentDateTime()
    {
        if (currentDateTime == null)
            try
            {
                currentDateTime = CalendarFactory.now();
            } catch (final ParseException e)
            {
                logger.error(e.getMessage(), e);
            }
        return currentDateTime;
    }

    boolean deleteDirectory(final File dir)
    {
        if (dir.isDirectory())
        {
            final File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++)
            {
                final boolean success = deleteDirectory(children[i]);
                if (!success) { return false; }
            }
        }
        // System.out.println("removing file or directory : " + dir.getName());
        return dir.delete();
    }

    /** {@inheritDoc} */
    @Override
    public void drillCreatedEvent(final IDrill drill)
    {
        // System.out.println("DC=" + drill.score());
    }

    /** {@inheritDoc} */
    @Override
    public void drillDestroyedEvent(final IDrill drill)
    {
        // System.out.println("DD=" + drill.score());
    }

    /** {@inheritDoc} */
    @Override
    public void drillOverEvent(final IDrill drill)
    {
        // System.out.println("DO=" + drill.score());
    }

    /** {@inheritDoc} */
    @Override
    public void drillStartEvent(final IDrill drill)
    {
        // System.out.println("DS=" + drill.score());
    }

    /** {@inheritDoc} */
    @Override
    protected File file(final String fileExt) throws IOException
    {
        return new File(SAVE_STORE_DIR + fileExt);
    }

    /** {@inheritDoc} */
    @Override
    public void scoreChangedEvent(final IDrill drill)
    {
        // System.out.println("SC=" + drill.score());
    }

    /**
     * <p>
     * Setter for the field <code>currentDateTime</code>.
     * </p>
     *
     * @param overriddingDateTime a {@link java.util.Calendar} object.
     */
    public void setCurrentDateTime(final LocalDateTime overriddingDateTime)
    {
        currentDateTime = overriddingDateTime;
    }

    /** {@inheritDoc} */
    @Override
    protected File tempStoreFile() throws IOException
    {
        return new File(SAVE_STORE_DIR + "\\" + TEMP_STORE_FILENAME);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return getClass().getSimpleName();
    }
}
