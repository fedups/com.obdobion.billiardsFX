package com.obdobion.billiardsFX.model.drills.sologames.bowlliards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.AbstractDrillCommand;

/**
 * <p>
 * UpdateBreak class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class UpdateBreak extends AbstractDrillCommand<Bowlliards>
{
    final static Logger logger = LoggerFactory.getLogger(UpdateBreak.class);

    BreakModifier[]     breakModifiers;
    int                 pots;

    int                 previousPots;
    boolean             previousScratchOnBreak;

    private UpdateBreak()
    {
        super(null);
    }

    /**
     * <p>
     * Constructor for UpdateBreak.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards}
     *            object.
     * @param p_pots a int.
     * @param p_breakModifiers a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BreakModifier}
     *            object.
     */
    public UpdateBreak(Bowlliards p_drill, int p_pots, BreakModifier... p_breakModifiers)
    {
        this(p_drill, p_pots, null, p_breakModifiers);
    }

    /**
     * <p>
     * Constructor for UpdateBreak.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards}
     *            object.
     * @param p_pots a int.
     * @param p_note a {@link java.lang.String} object.
     * @param p_breakModifiers a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BreakModifier}
     *            object.
     */
    public UpdateBreak(Bowlliards p_drill, int p_pots, String p_note, BreakModifier... p_breakModifiers)
    {
        super(p_note);
        drill = p_drill;
        pots = p_pots;
        breakModifiers = p_breakModifiers;
    }

    /** {@inheritDoc} */
    @Override
    public void privateExecute()
    {
        previousPots = drill.potsOnBreak;
        previousScratchOnBreak = drill.scratchOnBreak;

        drill.potsOnBreak = pots;
        drill.scratchOnBreak = false;
        for (BreakModifier bm : breakModifiers)
            switch (bm)
            {
                case SCRATCH:
                    drill.scratchOnBreak = true;
                    break;
                default:
                    break;
            }

        logger.debug("updateBreak({})", this);

        drill.notifyScoreChangedObservers();
    }

    /** {@inheritDoc} */
    @Override
    public void privateUndo()
    {
        drill.potsOnBreak = previousPots;
        drill.scratchOnBreak = previousScratchOnBreak;

        logger.debug("updateBreakUndo({})", drill);

        drill.notifyDrillStartObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("pots(").append(pots).append(")");
        sb.append(" modifiers(");
        String prefix = "";
        for (BreakModifier mod : breakModifiers)
        {
            sb.append(prefix).append(mod);
            prefix = ",";
        }
        sb.append(")");
        sb.append(" => ").append(drill);
        return sb.toString();
    }
}
