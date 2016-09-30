package com.obdobion.billiardsFX.model.drills;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.Tag;

/**
 * All drills must implement this interface. Usually this will be done by
 * extending {@link com.obdobion.billiardsFX.model.drills.AbstractDrill}. A drill
 * describes a specific activity at a specific time that has at least a score
 * associated with it.
 *
 * @author degreefc
 */
public interface IDrill
{
    /**
     * Drills produce events as they are processed. An observer will be called
     * back when events occur.
     *
     * @param observer is a listener for drill events.
     */
    void addObserver(IDrillObserver observer);

    /**
     * <p>gameData.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String gameData();

    /**
     * <p>gameData.</p>
     *
     * @param fields an array of {@link java.lang.String} objects.
     * @param start a int.
     * @throws java.text.ParseException if any.
     */
    void gameData(final String[] fields, final int start) throws ParseException;

    /**
     * <p>gameDataVersion.</p>
     *
     * @return a int.
     */
    int gameDataVersion();

    /**
     * <p>
     * getContext.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    Context getContext();

    /**
     * <p>
     * getDate.
     * </p>
     *
     * @return the date this drill occurred on
     */
    LocalDateTime getDate();

    /**
     * <p>
     * getDemonstration.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDemonstration} object.
     */
    IDemonstration getDemonstration();

    /**
     * <p>
     * getDrillID.
     * </p>
     *
     * @return the unique ID of this drill
     */
    String getDrillId();

    /**
     * <p>
     * getNote.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getNote();

    /**
     * <p>
     * getPackageID.
     * </p>
     *
     * @return the package id that owns this drill
     */
    String getPackageId();

    /**
     * <p>
     * getTags.
     * </p>
     *
     * @return the tags that were associated with this drill
     */
    List<Tag> getTags();

    /**
     * <p>
     * getTitleRef.
     * </p>
     *
     * @return the literal key that will be used to obtain the title of this
     *         drill
     */
    String getTitleRef();

    /**
     * <p>getUid.</p>
     *
     * @return a int.
     */
    int getUid();

    /**
     * <p>getUserUid.</p>
     *
     * @return a int.
     */
    int getUserUid();

    /**
     * <p>getVersion.</p>
     *
     * @return a int.
     */
    int getVersion();

    /**
     * Parse the drill's data from the external representation that was stored
     * on disk.
     *
     * @param externalRepresentation is the disk formatted data that contains
     *            the exact state of a drill
     * @return the external representation as an array of fields
     * @throws java.text.ParseException if the external representation is
     *             corrupt
     */
    String[] readFrom(String externalRepresentation) throws ParseException;

    /**
     * <p>
     * score.
     * </p>
     *
     * @return the current score of the drill
     */
    int score();

    /**
     * <p>
     * setContext.
     * </p>
     *
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    void setContext(Context context);

    /**
     * <p>setDate.</p>
     *
     * @param ldt a {@link java.time.LocalDateTime} object.
     */
    void setDate(LocalDateTime ldt);

    /**
     * <p>
     * setDemonstration.
     * </p>
     *
     * @param demo a {@link com.obdobion.billiardsFX.model.drills.IDemonstration}
     *            object.
     */
    void setDemonstration(IDemonstration demo);

    /**
     * <p>
     * setDrillID.
     * </p>
     *
     * @param drillID a {@link java.lang.String} object.
     */
    void setDrillId(String drillID);

    /**
     * <p>
     * setNote.
     * </p>
     *
     * @param note a {@link java.lang.String} object.
     */
    void setNote(String note);

    /**
     * <p>
     * setPackageID.
     * </p>
     *
     * @param id is the ID of the package that owns this drill
     */
    void setPackageId(String id);

    /**
     * <p>
     * setTargetScores.
     * </p>
     *
     * @param targetScores a
     *            {@link com.obdobion.billiardsFX.model.drills.TargetScores} object.
     */
    void setTargetScores(TargetScores targetScores);

    /**
     * <p>
     * setTitleRef.
     * </p>
     *
     * @param titleRef a {@link java.lang.String} object.
     */
    void setTitleRef(String titleRef);

    /**
     * <p>setUid.</p>
     *
     * @param uid a int.
     */
    void setUid(int uid);

    /**
     * <p>setUserUid.</p>
     *
     * @param userUid a int.
     */
    void setUserUid(int userUid);

    /**
     * <p>setVersion.</p>
     *
     * @param version a int.
     */
    void setVersion(int version);

    /**
     * <p>
     * skillLevelForScore.
     * </p>
     *
     * @param averageScore a int.
     * @return a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     */
    SkillLevel skillLevelForScore(int averageScore);

    /**
     * Apply a runtime tag to the drill.
     *
     * @param newTags a {@link com.obdobion.billiardsFX.model.Tag} object.
     */
    void tagIt(Tag... newTags);

    /**
     * <p>
     * targetScore.
     * </p>
     *
     * @param type a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     * @return a int.
     */
    int targetScore(SkillLevel type);

    /**
     * Format this drill's data into the external representation that will be
     * stored on disk.
     *
     * @param sb is where this method will write the formatted data
     */
    void writeTo(StringBuilder sb);
}
