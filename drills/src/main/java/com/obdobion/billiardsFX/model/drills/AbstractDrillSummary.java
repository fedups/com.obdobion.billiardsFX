package com.obdobion.billiardsFX.model.drills;

/**
 * <p>
 * Abstract AbstractDrillSummary class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public abstract class AbstractDrillSummary implements IDrillSummary
{
    private int drillCount;
    private int scoreHi;
    private int scoreLo;
    private int scoreTot;
    private int year, month, day;

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>day</code>.
     * </p>
     */
    @Override
    public int getDay()
    {
        return day;
    }

    /** {@inheritDoc} */
    @Override
    public int getDrillCount()
    {
        return drillCount;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>month</code>.
     * </p>
     */
    @Override
    public int getMonth()
    {
        return month;
    }

    /** {@inheritDoc} */
    @Override
    public int getScoreHi()
    {
        return scoreHi;
    }

    /** {@inheritDoc} */
    @Override
    public int getScoreLo()
    {
        return scoreLo;
    }

    /** {@inheritDoc} */
    @Override
    public int getScoreTot()
    {
        return scoreTot;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>year</code>.
     * </p>
     */
    @Override
    public int getYear()
    {
        return year;
    }

    /** {@inheritDoc} */
    @Override
    public void setDay(final int dd)
    {
        day = dd;
    }

    /** {@inheritDoc} */
    @Override
    public void setDrillCount(final int p_drillCount)
    {
        this.drillCount = p_drillCount;
    }

    /** {@inheritDoc} */
    @Override
    public void setMonth(final int mm)
    {
        month = mm;
    }

    /** {@inheritDoc} */
    @Override
    public void setScoreHi(final int p_scoreHi)
    {
        this.scoreHi = p_scoreHi;
    }

    /** {@inheritDoc} */
    @Override
    public void setScoreLo(final int p_scoreLo)
    {
        this.scoreLo = p_scoreLo;
    }

    /** {@inheritDoc} */
    @Override
    public void setScoreTot(final int p_scoreTot)
    {
        this.scoreTot = p_scoreTot;
    }

    /** {@inheritDoc} */
    @Override
    public void setYear(final int yyyy)
    {
        year = yyyy;
    }

}
