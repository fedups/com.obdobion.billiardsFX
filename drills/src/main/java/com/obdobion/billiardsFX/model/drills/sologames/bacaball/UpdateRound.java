package com.obdobion.billiardsFX.model.drills.sologames.bacaball;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.AbstractDrillCommand;

/**
 * <p>
 * UpdateRound class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class UpdateRound extends AbstractDrillCommand<BacaBall>
{
    final static Logger logger = LoggerFactory.getLogger(UpdateRound.class);

    int                 fouls;
    int                 misses;
    int                 previousFouls;
    int                 previousMisses;

    int                 previousSafeties;
    int                 previousScratches;
    int                 safeties;
    int                 scratches;

    private UpdateRound()
    {
        super(null);
    }

    /**
     * <p>
     * Constructor for UpdateRound.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall}
     *            object.
     * @param p_scratches a int.
     * @param p_fouls a int.
     * @param p_misses a int.
     * @param p_safeties a int.
     */
    public UpdateRound(BacaBall p_drill, int p_scratches, int p_fouls, int p_misses, int p_safeties)
    {
        this(p_drill, p_scratches, p_fouls, p_misses, p_safeties, null);
    }

    /**
     * <p>
     * Constructor for UpdateRound.
     * </p>
     *
     * @param p_drill a
     *            {@link com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall}
     *            object.
     * @param p_scratches a int.
     * @param p_fouls a int.
     * @param p_misses a int.
     * @param p_safeties a int.
     * @param p_note a {@link java.lang.String} object.
     */
    public UpdateRound(BacaBall p_drill, int p_scratches, int p_fouls, int p_misses, int p_safeties, String p_note)
    {
        super(p_note);
        drill = p_drill;
        scratches = p_scratches;
        fouls = p_fouls;
        misses = p_misses;
        safeties = p_safeties;
    }

    /** {@inheritDoc} */
    @Override
    public void privateExecute()
    {
        previousScratches = drill.round(drill.currentRound).getScratches();
        previousFouls = drill.round(drill.currentRound).getFouls();
        previousMisses = drill.round(drill.currentRound).getMisses();
        previousSafeties = drill.round(drill.currentRound).getSafeties();

        drill.round(drill.currentRound).setComplete(true);
        drill.round(drill.currentRound).setScratches(scratches);
        drill.round(drill.currentRound).setFouls(fouls);
        drill.round(drill.currentRound).setMisses(misses);
        drill.round(drill.currentRound).setSafeties(safeties);
        drill.currentRound++;

        logger.debug("updateRound({})", this);

        drill.notifyScoreChangedObservers();
        if (drill.isComplete())
            drill.notifyDrillOverObservers();
    }

    /** {@inheritDoc} */
    @Override
    public void privateUndo()
    {
        drill.round(drill.currentRound).setComplete(false);
        drill.round(drill.currentRound).setScratches(previousScratches);
        drill.round(drill.currentRound).setFouls(previousFouls);
        drill.round(drill.currentRound).setMisses(previousMisses);
        drill.round(drill.currentRound).setSafeties(previousSafeties);
        drill.currentRound--;

        logger.debug("updateRoundUndo({})", drill);

        if (drill.currentRound == 0)
            drill.notifyDrillStartObservers();
        else
            drill.notifyScoreChangedObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("scratches(").append(scratches).append(")");
        sb.append(" fouls(").append(fouls).append(")");
        sb.append(" misses(").append(misses).append(")");
        sb.append(" safeties(").append(safeties).append(")");
        sb.append(" => ").append(drill);
        return sb.toString();
    }
}
