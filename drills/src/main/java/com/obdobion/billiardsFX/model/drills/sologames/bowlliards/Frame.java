package com.obdobion.billiardsFX.model.drills.sologames.bowlliards;

/**
 * <p>
 * Frame class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class Frame
{

    int            frameNumber;
    private Inning inning[];

    /**
     * <p>
     * Constructor for Frame.
     * </p>
     *
     * @param p_frameNumber a int.
     */
    public Frame(int p_frameNumber)
    {
        frameNumber = p_frameNumber;
        inning = new Inning[2];
        inning[0] = new Inning();
        inning[1] = new Inning();
    }

    /**
     * <p>
     * inning.
     * </p>
     *
     * @param i a int.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Inning}
     *         object.
     */
    public Inning inning(int i)
    {
        return inning[i];
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
        return inning(1).isComplete() || isStrike();
    }

    /**
     * <p>
     * isSpare.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isSpare()
    {
        if (isStrike())
            return false;
        return inning(0).getPots() + inning(1).getPots() == 10;
    }

    /**
     * <p>
     * isStrike.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isStrike()
    {
        return inning(0).getPots() == 10;
    }
}
