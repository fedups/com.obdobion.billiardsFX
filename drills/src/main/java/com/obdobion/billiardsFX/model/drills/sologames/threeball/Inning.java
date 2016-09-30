package com.obdobion.billiardsFX.model.drills.sologames.threeball;

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
    private int     fouls;
    private int     strokes;

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
     * Getter for the field <code>strokes</code>.
     * </p>
     *
     * @return a int.
     */
    public int getStrokes()
    {
        return strokes;
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
     * Setter for the field <code>strokes</code>.
     * </p>
     *
     * @param p_strokes a int.
     */
    public void setStrokes(int p_strokes)
    {
        this.strokes = p_strokes;
    }
}
