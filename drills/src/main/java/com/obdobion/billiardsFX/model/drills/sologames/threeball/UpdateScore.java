package com.obdobion.billiardsFX.model.drills.sologames.threeball;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.AbstractDrillCommand;

/**
 * <p>
 * UpdateScore class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class UpdateScore extends AbstractDrillCommand<ThreeBall>
{
    final static Logger logger = LoggerFactory.getLogger(UpdateScore.class);

    int                 fouls;
    int                 previousFouls;

    int                 previousStrokes;
    int                 strokes;

    private UpdateScore()
    {
        super(null);
    }

    /**
     * <p>
     * Constructor for UpdateScore.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall}
     *            object.
     * @param p_strokes a int.
     * @param p_fouls a int.
     */
    public UpdateScore(ThreeBall p_drill, int p_strokes, int p_fouls)
    {
        this(p_drill, p_strokes, p_fouls, null);
    }

    /**
     * <p>
     * Constructor for UpdateScore.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall}
     *            object.
     * @param p_strokes a int.
     * @param p_fouls a int.
     * @param p_note a {@link java.lang.String} object.
     */
    public UpdateScore(ThreeBall p_drill, int p_strokes, int p_fouls, String p_note)
    {
        super(p_note);
        drill = p_drill;
        strokes = p_strokes;
        fouls = p_fouls;
    }

    /** {@inheritDoc} */
    @Override
    public void privateExecute()
    {
        previousStrokes = drill.inning(drill.currentInning).getStrokes();
        previousFouls = drill.inning(drill.currentInning).getFouls();

        drill.inning(drill.currentInning).setComplete(true);
        drill.inning(drill.currentInning).setStrokes(strokes);
        drill.inning(drill.currentInning).setFouls(fouls);
        drill.currentInning++;

        logger.debug("updateScore({})", drill);

        drill.notifyScoreChangedObservers();
        if (drill.isComplete())
            drill.notifyDrillOverObservers();
    }

    /** {@inheritDoc} */
    @Override
    public void privateUndo()
    {
        drill.inning(drill.currentInning).setComplete(false);
        drill.inning(drill.currentInning).setStrokes(previousStrokes);
        drill.inning(drill.currentInning).setFouls(previousFouls);
        drill.currentInning--;

        logger.debug("updateScoreUndo({})", this);

        drill.notifyScoreChangedObservers();

        if (drill.currentInning == 0)
            drill.notifyDrillStartObservers();
        else
            drill.notifyScoreChangedObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("strokes(").append(strokes).append(")");
        sb.append(" fouls(").append(fouls).append(")");
        sb.append(" => ").append(drill);
        return sb.toString();
    }
}
