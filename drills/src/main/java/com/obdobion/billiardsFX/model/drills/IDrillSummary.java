package com.obdobion.billiardsFX.model.drills;

/**
 * This class is created by the
 * {@link com.obdobion.billiardsFX.model.drills.IDrillSummarizer} to provide analysis
 * suitable for charting. A {@link java.util.List} of these instances will
 * usually be returned.
 * <p>
 * Implementors of this interface will usually add more data fields and most
 * likely will not use the getter / setter pattern. The getters and setters on
 * this interface are for the abstract / required pieces of information for
 * every drill.
 *
 * @author degreefc
 */
public interface IDrillSummary
{
    /**
     * <p>
     * getDay.
     * </p>
     *
     * @return the day of the month related to the data
     */
    public int getDay();

    /**
     * <p>
     * getDrillCount.
     * </p>
     *
     * @return the number of drills that are included in this result
     */
    public int getDrillCount();

    /**
     * <p>
     * getMonth.
     * </p>
     *
     * @return the month (Jan=1) related to the data
     */
    public int getMonth();

    /**
     * <p>
     * getScoreHi.
     * </p>
     *
     * @return the highest score for all drills that were aggregated for this
     *         result
     */
    public int getScoreHi();

    /**
     * <p>
     * getScoreLo.
     * </p>
     *
     * @return the lowest score for all drills that were aggregated for this
     *         result
     */
    public int getScoreLo();

    /**
     * <p>
     * getScoreTot.
     * </p>
     *
     * @return the total score for the drills that were aggregated for this
     *         result
     */
    public int getScoreTot();

    /**
     * <p>
     * getYear.
     * </p>
     *
     * @return the year (yyyy) related to the data
     */
    public int getYear();

    /**
     * <p>
     * setDay.
     * </p>
     *
     * @param dd is the day for this aggregated data
     */
    public void setDay(int dd);

    /**
     * <p>
     * setDrillCount.
     * </p>
     *
     * @param p_drillCount is the number of drills that were aggregated for this
     *            result
     */
    public void setDrillCount(int p_drillCount);

    /**
     * <p>
     * setMonth.
     * </p>
     *
     * @param mm is the month for this aggregated data
     */
    public void setMonth(int mm);

    /**
     * <p>
     * setScoreHi.
     * </p>
     *
     * @param p_scoreHi is the highest score for all drills that were aggregated
     *            for this result
     */
    public void setScoreHi(int p_scoreHi);

    /**
     * <p>
     * setScoreLo.
     * </p>
     *
     * @param p_scoreLo is the lowest score for all drills that were aggregated
     *            for this result
     */
    public void setScoreLo(int p_scoreLo);

    /**
     * <p>
     * setScoreTot.
     * </p>
     *
     * @param p_scoreTot is the total score for all drills that were aggregated
     *            for this result
     */
    public void setScoreTot(int p_scoreTot);

    /**
     * <p>
     * setYear.
     * </p>
     *
     * @param yyyy is the year for this aggregated data
     */
    public void setYear(int yyyy);
}
