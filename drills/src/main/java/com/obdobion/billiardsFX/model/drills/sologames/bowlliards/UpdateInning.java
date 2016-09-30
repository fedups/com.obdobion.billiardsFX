package com.obdobion.billiardsFX.model.drills.sologames.bowlliards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.AbstractDrillCommand;

/**
 * <p>
 * UpdateInning class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class UpdateInning extends AbstractDrillCommand<Bowlliards>
{
    final static Logger logger = LoggerFactory.getLogger(UpdateInning.class);
    InningModifier[]    inningModifiers;
    int                 pots;

    int                 previousCurrentFrame;
    int                 previousCurrentInning;
    boolean             previousInningMiss;
    int                 previousInningPots;
    boolean             previousInningScratch;

    private UpdateInning()
    {
        super(null);
    }

    /**
     * <p>
     * Constructor for UpdateInning.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards}
     *            object.
     * @param p_pots a int.
     * @param p_inningModifiers a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.InningModifier}
     *            object.
     */
    public UpdateInning(Bowlliards p_drill, int p_pots, InningModifier... p_inningModifiers)
    {
        this(p_drill, p_pots, null, p_inningModifiers);
    }

    /**
     * <p>
     * Constructor for UpdateInning.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards}
     *            object.
     * @param p_pots a int.
     * @param p_note a {@link java.lang.String} object.
     * @param p_inningModifiers a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.InningModifier}
     *            object.
     */
    public UpdateInning(Bowlliards p_drill, int p_pots, String p_note, InningModifier... p_inningModifiers)
    {
        super(p_note);
        drill = p_drill;
        pots = p_pots;
        inningModifiers = p_inningModifiers;
    }

    /** {@inheritDoc} */
    @Override
    public void privateExecute()
    {
        Inning inning = drill.frame(drill.currentFrame).inning(drill.currentInning);

        previousCurrentFrame = drill.currentFrame;
        previousCurrentInning = drill.currentInning;
        previousInningPots = inning.getPots();
        previousInningScratch = inning.isScratch();
        previousInningMiss = inning.isMiss();

        inning.setComplete(true);
        inning.setPots(pots);

        for (InningModifier im : inningModifiers)
            switch (im)
            {
                case SCRATCH:
                    inning.setScratch(true);
                    break;
                case MISS:
                    inning.setMiss(true);
                    break;
                case REMAINING_COUNT:
                    if (drill.currentInning == 0)
                        inning.setPots(10 - pots);
                    else
                        inning.setPots(10 - drill.frame(drill.currentFrame).inning(0).getPots() - pots);
                    break;
                default:
                    break;
            }

        drill.currentInning++;
        if (drill.currentInning > 1 || drill.frame(drill.currentFrame).isStrike())
        {
            drill.frame(drill.currentFrame).inning(1).setComplete(true);
            drill.currentFrame++;
            drill.currentInning = 0;
        }

        logger.debug("updateInning({})", this);

        drill.notifyScoreChangedObservers();
        if (drill.isComplete())
            drill.notifyDrillOverObservers();
    }

    /** {@inheritDoc} */
    @Override
    public void privateUndo()
    {
        drill.currentFrame = previousCurrentFrame;
        drill.currentInning = previousCurrentInning;

        Inning inning = drill.frame(drill.currentFrame).inning(drill.currentInning);
        inning.setComplete(false);
        inning.setPots(previousInningPots);
        inning.setScratch(previousInningScratch);
        inning.setMiss(previousInningMiss);

        logger.debug("updateInningUndo({})", drill);

        if (drill.currentFrame == 0 && drill.currentInning == 0)
            drill.notifyDrillStartObservers();
        else
            drill.notifyScoreChangedObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("pots(").append(pots).append(")");
        sb.append(" modifiers(");
        String prefix = "";
        for (InningModifier mod : inningModifiers)
        {
            sb.append(prefix).append(mod);
            prefix = ",";
        }
        sb.append(")");
        sb.append(" => ").append(drill);
        return sb.toString();
    }
}
