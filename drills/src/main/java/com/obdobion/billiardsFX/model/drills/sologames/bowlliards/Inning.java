package com.obdobion.billiardsFX.model.drills.sologames.bowlliards;

/**
 * <p>
 * Inning class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class Inning
{
    private boolean complete;
    private boolean miss;
    private int     pots;
    private boolean scratch;

    /**
     * <p>
     * Getter for the field <code>pots</code>.
     * </p>
     *
     * @return a int.
     */
    public int getPots()
    {
        return pots;
    }

    /**
     * <p>
     * isComplete.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isComplete()
    {
        return complete;
    }

    /**
     * <p>
     * isMiss.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isMiss()
    {
        return miss;
    }

    /**
     * <p>
     * isScratch.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isScratch()
    {
        return scratch;
    }

    /**
     * <p>
     * Setter for the field <code>complete</code>.
     * </p>
     *
     * @param p_complete a boolean.
     */
    public void setComplete(boolean p_complete)
    {
        this.complete = p_complete;
    }

    /**
     * <p>
     * Setter for the field <code>miss</code>.
     * </p>
     *
     * @param p_miss a boolean.
     */
    public void setMiss(boolean p_miss)
    {
        this.miss = p_miss;
    }

    /**
     * <p>
     * Setter for the field <code>pots</code>.
     * </p>
     *
     * @param p_pots a int.
     */
    public void setPots(int p_pots)
    {
        this.pots = p_pots;
    }

    /**
     * <p>
     * Setter for the field <code>scratch</code>.
     * </p>
     *
     * @param p_scratch a boolean.
     */
    public void setScratch(boolean p_scratch)
    {
        this.scratch = p_scratch;
    }
}
