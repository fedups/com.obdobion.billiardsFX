package com.obdobion.billiardsFX.model.drills.sologames.bacaball;

/**
 * <p>
 * Round class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class Round
{
    private boolean complete;
    private int     fouls;
    private int     misses;
    private int     safeties;
    private int     scratches;

    /**
     * <p>
     * Getter for the field <code>fouls</code>.
     * </p>
     *
     * @return a int.
     */
    public int getFouls()
    {
        return fouls;
    }

    /**
     * <p>
     * Getter for the field <code>misses</code>.
     * </p>
     *
     * @return a int.
     */
    public int getMisses()
    {
        return misses;
    }

    /**
     * <p>
     * Getter for the field <code>safeties</code>.
     * </p>
     *
     * @return a int.
     */
    public int getSafeties()
    {
        return safeties;
    }

    /**
     * <p>
     * Getter for the field <code>scratches</code>.
     * </p>
     *
     * @return a int.
     */
    public int getScratches()
    {
        return scratches;
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
     * Setter for the field <code>fouls</code>.
     * </p>
     *
     * @param p_fouls a int.
     */
    public void setFouls(int p_fouls)
    {
        this.fouls = p_fouls;
    }

    /**
     * <p>
     * Setter for the field <code>misses</code>.
     * </p>
     *
     * @param p_misses a int.
     */
    public void setMisses(int p_misses)
    {
        this.misses = p_misses;
    }

    /**
     * <p>
     * Setter for the field <code>safeties</code>.
     * </p>
     *
     * @param p_safeties a int.
     */
    public void setSafeties(int p_safeties)
    {
        this.safeties = p_safeties;
    }

    /**
     * <p>
     * Setter for the field <code>scratches</code>.
     * </p>
     *
     * @param p_scratches a int.
     */
    public void setScratches(int p_scratches)
    {
        this.scratches = p_scratches;
    }
}
