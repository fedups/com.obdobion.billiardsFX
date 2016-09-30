package com.obdobion.billiardsFX.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.obdobion.billiardsFX.model.ConfigParm;
import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.DrillStatus;
import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.drills.AbstractDrill;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillPackage;
import com.obdobion.billiardsFX.model.drills.IDrillSummarizer;

/**
 * <p>
 * Abstract AbstractDrillMasterImpl class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrillMasterFileImpl extends AbstractDrillMasterImpl
{
    /**
     * Constant <code>SAVE_STORE_DIR="target\\savestore\\DrillMaster\\"</code>
     */
    public static final String SAVE_STORE_DIR      = "target\\savestore\\DrillMaster\\";
    /** Constant <code>TEMP_STORE_FILENAME="tempstore.txt"</code> */
    public static final String TEMP_STORE_FILENAME = "tempstore.txt";

    static private String drillStatusKey(final String drillID)
    {
        return drillID;
    }

    private final Map<String, ConfigParm> configParms   = new HashMap<>();
    private Map<String, DrillStatus>      drillStatuses = new HashMap<>();

    /** {@inheritDoc} */
    @Override
    public List<IDrill> createDrillFromSaveStore(final Context context, final SaveStoreFilter filter,
            final int mostRecentLimit)
                    throws InstantiationException, IOException, ParseException
    {
        final long sTime = System.currentTimeMillis();

        int totalDrills = 0;
        final List<IDrill> drills = new ArrayList<>();
        try (final BufferedReader reader = fileReader(saveStoreFileName(
                filter.getPackageIDPattern(),
                filter.getDrillIDPattern())))
        {
            String externalRepresentation;
            IDrill drill = null;
            while ((externalRepresentation = reader.readLine()) != null)
            {
                totalDrills++;
                final SaveStoreFilter header = AbstractDrill.readHeader(externalRepresentation);
                if (filter.selects(header))
                {
                    final IDrillPackage pkg = getDrillPackageManager()
                            .getPackageByID(header.getPackageIDPattern());
                    drill = pkg.createDrillByID(context, header.getDrillIDPattern());
                    drill.readFrom(externalRepresentation);
                    drills.add(drill);
                }
            }
        }

        if (drills.size() > mostRecentLimit)
        {
            /*
             * Remove the excessive drills from the beginning of the list
             */
            for (int x = drills.size() - mostRecentLimit; x > 0; x--)
                drills.remove(0);
        }

        logger.trace("selected {} of {} drills in {} ms. for {}", drills.size(), totalDrills,
                System.currentTimeMillis() - sTime, filter);

        return drills;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * createDrillFromTempStore.
     * </p>
     */
    @Override
    public IDrill createDrillFromTempStore(final Context context)
            throws InstantiationException, IOException, ParseException
    {
        try (final BufferedReader br = tempStoreReader())
        {
            final String externalRepresentation = br.readLine();
            if (externalRepresentation == null)
                throw new ParseException("invalid definition in file", 0);
            final SaveStoreFilter header = AbstractDrill.readHeader(externalRepresentation);

            final IDrillPackage pkg = getDrillPackageManager().getPackageByID(header.getPackageIDPattern());
            final IDrill drill = pkg.createDrillByID(context, header.getDrillIDPattern());
            drill.readFrom(externalRepresentation);

            logger.debug("loaded drill from temp store: {}", drill);

            return drill;
        }
    }

    /**
     * <p>
     * file.
     * </p>
     *
     * @return a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @param fileExt a {@link java.lang.String} object.
     */
    protected File file(final String fileExt) throws IOException
    {
        return new File(SAVE_STORE_DIR + fileExt);
    }

    /**
     * <p>
     * fileReader.
     * </p>
     *
     * @param fileNameAndExtOnly a {@link java.lang.String} object.
     * @return a {@link java.io.BufferedReader} object.
     * @throws java.io.IOException if any.
     */
    public BufferedReader fileReader(final String fileNameAndExtOnly) throws IOException
    {
        final File file = file(fileNameAndExtOnly);
        if (!file.getParentFile().mkdirs())
        {
            // System.out.println("mkdirs failed for " +
            // file.getAbsolutePath());
        }
        final FileInputStream fis = new FileInputStream(file);
        final InputStreamReader isr = new InputStreamReader(fis);
        return new BufferedReader(isr);
    }

    /**
     * <p>
     * fileWriter.
     * </p>
     *
     * @param fileNameAndExtOnly a {@link java.lang.String} object.
     * @return a {@link java.io.BufferedWriter} object.
     * @throws java.io.IOException if any.
     */
    public BufferedWriter fileWriter(final String fileNameAndExtOnly) throws IOException
    {
        final File file = file(fileNameAndExtOnly);
        if (!file.getParentFile().mkdirs())
        {
            // System.out.println("mkdirs failed for " +
            // file.getAbsolutePath());
        }
        final FileOutputStream fos = new FileOutputStream(file, true);
        final OutputStreamWriter osw = new OutputStreamWriter(fos);
        return new BufferedWriter(osw);
    }

    /** {@inheritDoc} */
    @Override
    public ConfigParm getConfigParm(final Context context, final String parmKey)
    {
        return configParms.get(parmKey);
    }

    /** {@inheritDoc} */
    @Override
    public DrillStatus getDrillStatus(final Context context, final String drillID)
            throws InstantiationException, IOException, ParseException
    {
        final String statusKey = drillStatusKey(drillID);
        /*
         * Lazy initialize a map.
         */
        DrillStatus status = getDrillStatuses().get(statusKey);
        if (status == null)
        {
            status = new DrillStatus();
            getDrillStatuses().put(statusKey, status);

            final SaveStoreFilter ssf = new SaveStoreFilter();
            ssf.setDrillSelection(drillID);
            final IDrillSummarizer analyzer = DrillMaster.getDrillAnalyzer(ssf);

            final List<IDrill> drills = createDrillFromSaveStore(context, ssf, 99);
            status.setCurrentSkillLevel(analyzer.averageSkillLevel(drills));
            status.setPlayCount(drills.size());

            logger.info(
                    "drill status computed and cached for {}; played {} times with {} skill",
                    statusKey,
                    status.getPlayCount(),
                    status.getCurrentSkillLevel().name());
        }

        return status;
    }

    /**
     * @return the drillStatuses
     */
    Map<String, DrillStatus> getDrillStatuses()
    {
        return drillStatuses;
    }

    /** {@inheritDoc} */
    @Override
    public SaveStoreFilter saveDrillToSaveStore(final Context context, final IDrill drill)
            throws IOException, InstantiationException, ParseException
    {
        final StringBuilder sb = new StringBuilder();
        drill.writeTo(sb);

        try (final BufferedWriter bw = fileWriter(saveStoreFileName(drill.getPackageId(), drill.getDrillId())))
        {
            bw.write(sb.toString());
            bw.newLine();
        }

        logger.info("saved drill to save store: {}", drill);

        getDrillStatuses().remove(drillStatusKey(drill.getDrillId()));
        getDrillStatus(context, drill.getDrillId());

        try
        {
            return AbstractDrill.readHeader(sb.toString());
        } catch (final ParseException e)
        {
            return null;
        } finally
        {
            notifyDrillDestroyedObservers(context, drill);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void saveDrillToTempStore(final Context context, final IDrill drill) throws IOException
    {
        final StringBuilder sb = new StringBuilder();
        drill.writeTo(sb);
        try (final BufferedWriter bw = tempStoreWriter())
        {
            bw.write(sb.toString());
            bw.newLine();
        }

        logger.debug("saved drill to temp store: {}", drill);
    }

    /**
     * <p>
     * saveStoreFileName.
     * </p>
     *
     * @param packageID a {@link java.lang.String} object.
     * @param drillID a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    protected String saveStoreFileName(final String packageID, final String drillID)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(drillID.toLowerCase());
        sb.append(".txt");
        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setConfigParm(final Context context, final ConfigParm parm)
    {
        if (parm == null)
            return;
        configParms.remove(parm.getPropertyName());
        configParms.put(parm.getPropertyName(), parm);
    }

    /**
     * @param drillStatuses the drillStatuses to set
     */
    void setDrillStatuses(final Map<String, DrillStatus> drillStatuses)
    {
        this.drillStatuses = drillStatuses;
    }

    /** {@inheritDoc} */
    @Override
    public void start()
    {
        super.start();
    }

    /** {@inheritDoc} */
    @Override
    public void stop()
    {
        super.stop();
    }

    /**
     * <p>
     * tempStoreFile.
     * </p>
     *
     * @return a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    protected File tempStoreFile() throws IOException
    {
        return new File(SAVE_STORE_DIR + "\\" + TEMP_STORE_FILENAME);
    }

    /**
     * <p>
     * tempStoreReader.
     * </p>
     *
     * @return a {@link java.io.BufferedReader} object.
     * @throws java.io.IOException if any.
     */
    protected BufferedReader tempStoreReader() throws IOException
    {
        final File file = tempStoreFile();
        if (!file.getParentFile().mkdirs())
        {
            // System.out.println("mkdirs failed for " +
            // file.getAbsolutePath());
        }
        final FileInputStream fis = new FileInputStream(file);
        final InputStreamReader isr = new InputStreamReader(fis);
        return new BufferedReader(isr);
    }

    /**
     * <p>
     * tempStoreWriter.
     * </p>
     *
     * @return a {@link java.io.BufferedWriter} object.
     * @throws java.io.IOException if any.
     */
    protected BufferedWriter tempStoreWriter() throws IOException
    {
        final File file = tempStoreFile();
        if (!file.getParentFile().mkdirs())
        {
            // System.out.println("mkdirs failed for " +
            // file.getAbsolutePath());
        }
        final FileOutputStream fos = new FileOutputStream(file, false);
        final OutputStreamWriter osw = new OutputStreamWriter(fos);
        return new BufferedWriter(osw);
    }
}
