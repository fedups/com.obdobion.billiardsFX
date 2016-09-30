package com.obdobion.billiardsFX.model.drills.drdave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.AbstractDrillCommand;

/**
 * <p>
 * UpdateScoreFromInt class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class UpdateScoreFromInt extends AbstractDrillCommand<SingleScoreDrill>
{
    final static Logger logger = LoggerFactory.getLogger(UpdateScoreFromInt.class);

    int                 previousScore;
    int                 score;

    private UpdateScoreFromInt()
    {
        super(null);
    }

    /**
     * <p>
     * Constructor for UpdateScoreFromInt.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill}
     *            object.
     * @param p_score a int.
     */
    public UpdateScoreFromInt(SingleScoreDrill p_drill, int p_score)
    {
        this(p_drill, p_score, null);
    }

    /**
     * <p>
     * Constructor for UpdateScoreFromInt.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill}
     *            object.
     * @param p_score a int.
     * @param p_note a {@link java.lang.String} object.
     */
    public UpdateScoreFromInt(SingleScoreDrill p_drill, int p_score, String p_note)
    {
        super(p_note);
        drill = p_drill;
        score = p_score;
    }

    /** {@inheritDoc} */
    @Override
    public void privateExecute()
    {
        previousScore = drill.score;

        drill.score = score;

        logger.info("updateScore({})", this);

        drill.notifyScoreChangedObservers();
        if (drill.isComplete())
            drill.notifyDrillOverObservers();
    }

    /** {@inheritDoc} */
    @Override
    public void privateUndo()
    {
        drill.score = previousScore;
        logger.info("updateScoreUndo({})", drill);
        drill.notifyDrillStartObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("int(").append(score).append(")");
        sb.append(" => ").append(drill);
        return sb.toString();
    }
}
