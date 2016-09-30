package com.obdobion.billiardsFX.model.drills.drdave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.AbstractDrillCommand;

/**
 * <p>
 * UpdateScoreFromFloat class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class UpdateScoreFromFloat extends AbstractDrillCommand<SingleScoreDrill>
{
    final static Logger logger = LoggerFactory.getLogger(UpdateScoreFromFloat.class);

    int                 previousScore;
    float               score;

    private UpdateScoreFromFloat()
    {
        super(null);
    }

    /**
     * <p>
     * Constructor for UpdateScoreFromFloat.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill}
     *            object.
     * @param p_score a float.
     */
    public UpdateScoreFromFloat(SingleScoreDrill p_drill, float p_score)
    {
        this(p_drill, p_score, null);
    }

    /**
     * <p>
     * Constructor for UpdateScoreFromFloat.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill}
     *            object.
     * @param p_score a float.
     * @param p_note a {@link java.lang.String} object.
     */
    public UpdateScoreFromFloat(SingleScoreDrill p_drill, float p_score, String p_note)
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

        drill.score = (int) (drill.scoreDivisor() * score);

        logger.debug("updateScore({})", this);

        drill.notifyScoreChangedObservers();
        if (drill.isComplete())
            drill.notifyDrillOverObservers();
    }

    /** {@inheritDoc} */
    @Override
    public void privateUndo()
    {
        drill.score = previousScore;
        logger.debug("updateScoreUndo({})", drill);

        drill.notifyDrillStartObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("float(").append(score).append(")");
        sb.append(" => ").append(drill);
        return sb.toString();
    }
}
