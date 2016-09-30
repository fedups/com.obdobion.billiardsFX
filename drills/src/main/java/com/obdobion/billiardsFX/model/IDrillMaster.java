package com.obdobion.billiardsFX.model;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillCommand;
import com.obdobion.billiardsFX.model.drills.IDrillPackage;
import com.obdobion.billiardsFX.model.drills.IDrillPackageManager;
import com.obdobion.billiardsFX.model.drills.IDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;

/**
 * This interface describes the main entry point into the drill master core. It
 * is a strategic singleton in that the implementation of the singleton can be
 * swapped out at any time. This is useful to support JUnit testing, and an
 * Android app that require different operating system environment changes.
 * <p>
 * The essential functionality is to use the package manager to create a drill.
 * Then interact with that drill with instances of commands IDrillCommand. The
 * drill can be queried for such things as score().
 * <p>
 * After a drill has been saved it can be analyzed with the analyzer. This will
 * return information needed for charting.
 *
 * @author degreefc
 */
public interface IDrillMaster
{
    /**
     * Use this method to register a listener for
     * {@link com.obdobion.billiardsFX.model.IDrillMasterObserver} events. The
     * listener must implement the interface.
     *
     * @param observer is what will listen for the events
     */
    void addObserver(IDrillMasterObserver observer);

    /**
     * <p>
     * addPackage.
     * </p>
     *
     * @param pkg a {@link com.obdobion.billiardsFX.model.drills.IDrillPackage}
     *            object.
     */
    void addPackage(IDrillPackage pkg);

    /**
     * <p>
     * availableDrills.
     * </p>
     *
     * @param filter a {@link com.obdobion.billiardsFX.model.DrillLookupFilter}
     *            object.
     * @return a {@link java.util.List} object.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    List<DrillLookupReference> availableDrills(final Context context, DrillLookupFilter filter);

    /**
     * <p>
     * createDrill.
     * </p>
     *
     * @param drillRef a {@link com.obdobion.billiardsFX.model.DrillLookupReference}
     *            object.
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDrill} object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.lang.IllegalAccessException if any.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    IDrill createDrill(final Context context, DrillLookupReference drillRef)
            throws InstantiationException, IllegalAccessException;

    /**
     * The provided filter will select matching drills from the disk based save
     * store. These were previously put in the save store with the corresponding
     * call to saveDrillToSaveStore(IDrill).
     * <p>
     * Use exact package and drill patterns for this method. Otherwise the save
     * stores will not be found.
     *
     * @param filter provides the matching criteria for selection of drills.
     * @param mostRecentLimit limits the number of drills returned
     * @return a list of IDrill instances that match the filter.
     * @throws java.lang.InstantiationException when the reflective call is made
     *             to create the drill instance.
     * @throws java.io.IOException if the file can not be read.
     * @throws java.text.ParseException when the contents of the file have been
     *             corrupted.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    List<IDrill> createDrillFromSaveStore(final Context context, SaveStoreFilter filter, int mostRecentLimit)
            throws InstantiationException, IOException, ParseException;

    /**
     * This method will load the drill from the temp store. Only one drill can
     * be stored in the temp store. This is intended to allow for recovery
     * during the execution of a drill.
     *
     * @return an instance of {@link com.obdobion.billiardsFX.model.drills.IDrill} in
     *         the state at which it was saved ({@link #saveDrillToTempStore}).
     * @throws java.lang.InstantiationException when the reflective call is made
     *             to create the drill instance.
     * @throws java.io.IOException if the file can not be read.
     * @throws java.text.ParseException when the contents of the file have been
     *             corrupted.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    IDrill createDrillFromTempStore(final Context context) throws InstantiationException, IOException, ParseException;

    /**
     * This method is a wrapper method for the System get time and converting it
     * to a calendar instance. For testing purposes this is overridden to
     * provide a controllable time.
     *
     * @return an instance of the Calendar with the required date / time in it.
     */
    LocalDateTime currentDateTime();

    /**
     * Use this method to execute a command to operate on a drill. Basically,
     * all score updates are processed with commands. A command can be reversed
     * with undo.
     *
     * @param command is an instance of a command. Do not reused instances since
     *            the initial pre-execution state is stored on the command
     *            (allowing for undo).
     * @return the command after it has been updated with the undo information
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    IDrillCommand execute(final Context context, IDrillCommand command);

    /**
     * <p>
     * getConfigParm.
     * </p>
     *
     * @param parmKey a {@link java.lang.String} object.
     * @return a ConfigParm object.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     * @throws java.io.IOException if any.
     */
    ConfigParm getConfigParm(final Context context, String parmKey) throws IOException;

    /**
     * <p>getContext.</p>
     *
     * @param sessionId a {@link java.lang.String} object.
     * @param andIncrement a int.
     * @return a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    Context getContext(String sessionId, int andIncrement);

    /**
     * The package manager is used to access the drill definitions. Use this
     * instance when a new drill must be created.
     *
     * @return the appropriate instance of the drill package manager
     */
    IDrillPackageManager getDrillPackageManager();

    /**
     * <p>
     * getDrillStatus.
     * </p>
     *
     * @param drillID a {@link java.lang.String} object.
     * @return a {@link com.obdobion.billiardsFX.model.DrillStatus} object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.io.IOException if any.
     * @throws java.text.ParseException if any.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    DrillStatus getDrillStatus(final Context context, String drillID)
            throws InstantiationException, IOException, ParseException;

    /**
     * The drill analyzer is used to compute the data needed for charting.
     *
     * @param filter is used to select an appropriate analyzer for a set of
     *            drills.
     * @return the best analyzer instance for the provided filter
     * @throws java.lang.InstantiationException when the reflective call fails
     *             for creating the analyzer.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    IDrillSummarizer getDrillSummarizer(final Context context, SaveStoreFilter filter) throws InstantiationException;

    /**
     * Only use this method from within the core. It will call all observers
     * {@link #addObserver(IDrillMasterObserver)} when a drill instance is
     * created.
     *
     * @param drill is the instance of the drill that was just created.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    void notifyDrillCreatedObservers(final Context context, IDrill drill);

    /**
     * Only use this method from within the core. It will call all observers
     * {@link #addObserver(IDrillMasterObserver)} when a drill instance is
     * destroyed (post-saved).
     *
     * @param drill is the instance of the drill that was just destroyed.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    void notifyDrillDestroyedObservers(final Context context, IDrill drill);

    /**
     * <p>
     * resolveResourceReference.
     * </p>
     *
     * @param rr a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    String resolveResourceReference(String rr);

    /**
     * Use this method to store a completed drill into the disk file. It can not
     * be updated once it has been stored.
     *
     * @param drill is the instance of the drill that has been completed
     * @return a filter that will find this specific instance using
     * @throws java.io.IOException when the write to the file fails
     * @throws java.text.ParseException if any.
     * @throws java.lang.InstantiationException if any.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    SaveStoreFilter saveDrillToSaveStore(final Context context, IDrill drill)
            throws IOException, InstantiationException, ParseException;

    /**
     * Use this method to store a partially completed drill to disk. In case of
     * failure, the drill can be read from disk createDrillFromTempStore
     *
     * @param drill is the partially completed drill to be saved
     * @throws java.io.IOException when the write to the file fails.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    void saveDrillToTempStore(final Context context, IDrill drill) throws IOException;

    /**
     * <p>
     * setConfigParm.
     * </p>
     *
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     * @throws java.io.IOException if any.
     * @param parm a {@link com.obdobion.billiardsFX.model.ConfigParm} object.
     */
    void setConfigParm(final Context context, ConfigParm parm) throws IOException;

    /**
     * Use this method to set the drill package manager instance.
     *
     * @param newDPM is the instance for the package manager that will be
     *            returned by {@link #getDrillPackageManager()}
     * @return the previous instance (or null) of the package manager
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    IDrillPackageManager setDrillPackageManager(final Context context, IDrillPackageManager newDPM);

    /**
     * <p>start.</p>
     */
    void start();

    /**
     * <p>stop.</p>
     */
    void stop();

    /**
     * Use this method to undo the effects of a previous IDrillCommand.
     *
     * @param command is the command that was returned from a previous
     *            execute(IDrillCommand)
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    void undo(final Context context, IDrillCommand command);

    /**
     * <p>
     * xLabelsFor.
     * </p>
     *
     * @param summaries a {@link java.util.List} object.
     * @return a {@link java.util.ArrayList} object.
     */
    ArrayList<String> xLabelsFor(List<IDrillSummary> summaries);

}
