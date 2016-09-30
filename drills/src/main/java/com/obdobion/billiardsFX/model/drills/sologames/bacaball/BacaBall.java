package com.obdobion.billiardsFX.model.drills.sologames.bacaball;

import java.text.ParseException;

import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.drills.AbstractDrill;

/**
 * This is an unofficial variation of 9-Ball, adapted for solo play. In regular
 * 9-Ball, the objective is to get each of the nine balls in ascending numerical
 * order, with the winner being the player who sinks the 9 ball. In Baca Ball,
 * rather than competing against an opponent, you strive to get the lowest score
 * possible. You give yourself 10 points to begin with. As you try to sink each
 * ball in the correct order, you deduct a point each time you scratch, miss or
 * fowl. If you successfully play a "safety," you get the ball in hand. Play 10
 * rounds with the potential of 10 points per round and see how close you can
 * get to a perfect 100.
 *
 * @author degreefc
 */
public class BacaBall extends AbstractDrill
{
    /** Constant <code>RoundsPerGame=10</code> */
    static final public int RoundsPerGame = 10;
    int                     currentRound  = 0;
    private final Round[]   round;

    /**
     * <p>
     * Constructor for BacaBall.
     * </p>
     */
    public BacaBall()
    {
        super();
        round = new Round[RoundsPerGame];
        for (int i = 0; i < RoundsPerGame; i++)
            round[i] = new Round();
        DrillMaster.notifyDrillCreatedObservers(getContext(), this);
    }

    /** {@inheritDoc} */
    @Override
    public String gameData()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(currentRound);
        for (int r = 0; r < RoundsPerGame; r++)
        {
            sb.append(",");
            sb.append(r);
            sb.append(",");
            sb.append(round(r).getScratches());
            sb.append(",");
            sb.append(round(r).getFouls());
            sb.append(",");
            sb.append(round(r).getMisses());
            sb.append(",");
            sb.append(round(r).getSafeties());
            sb.append(",");
            sb.append(externalize(round(r).isComplete()));
        }
        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void gameData(final String[] fields, final int start) throws ParseException
    {
        final int fieldIndex = start;
        try
        {
            currentRound = Integer.parseInt(fields[fieldIndex]);
        } catch (final NumberFormatException e)
        {
            throw new ParseException("invalid current round " + e.getMessage(), 2);
        }

        for (int i = 0; i < RoundsPerGame; i++)
        {
            final int offset = i * 6;
            try
            {
                final int iNumber = Integer.parseInt(fields[start + offset + 1]);
                if (iNumber != i)
                    throw new ParseException("out of sequence round: " + iNumber, start + offset + 1);
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid round number " + e.getMessage(), start + offset + 1);
            }
            try
            {
                round(i).setScratches(Integer.parseInt(fields[start + offset + 2]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid scratches " + e.getMessage(), start + offset + 2);
            }
            try
            {
                round(i).setFouls(Integer.parseInt(fields[start + offset + 3]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid fouls " + e.getMessage(), start + offset + 3);
            }
            try
            {
                round(i).setMisses(Integer.parseInt(fields[start + offset + 4]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid misses " + e.getMessage(), start + offset + 4);
            }
            try
            {
                round(i).setSafeties(Integer.parseInt(fields[start + offset + 5]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid safeties " + e.getMessage(), start + offset + 5);
            }
            round(i).setComplete(internalizeBoolean(fields[start + offset + 6]));
        }
    }

    /** {@inheritDoc} */
    @Override
    public int gameDataVersion()
    {
        return 1;
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
        return currentRound >= RoundsPerGame;
    }

    /** {@inheritDoc} */
    @Override
    public String[] readFrom(final String externalRepresentation) throws ParseException
    {
        final String fields[] = super.readFrom(externalRepresentation);
        if (fields.length != 64)
            throw new ParseException("64 fields required: found " + fields.length, fields.length);
        if (!(getDrillId().equals(fields[0])))
            throw new ParseException("wrong drill: " + fields[0], 0);
        if (!("1".equals(fields[1])))
            throw new ParseException("unsupported format version: " + fields[1], 1);
        gameData(fields, 2);
        noteFromDiskFormat(fields[63]);
        return fields;
    }

    /**
     * <p>
     * round.
     * </p>
     *
     * @param i a int.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.sologames.bacaball.Round}
     *         object.
     */
    public Round round(final int i)
    {
        return round[i];
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * score.
     * </p>
     */
    @Override
    public int score()
    {
        return score(currentRound - 1);
    }

    /**
     * <p>
     * score.
     * </p>
     *
     * @param throughRound a int.
     * @return a int.
     */
    public int score(final int throughRound)
    {
        int score = 0;
        for (int i = 0; i <= throughRound; i++)
        {
            int rscore = 10 - (round(i).getScratches() + round(i).getFouls() + round(i).getMisses());
            if (rscore < 0)
                rscore = 0;

            score += rscore;
        }
        return score;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" currentRound(").append(currentRound).append(")");
        sb.append(" score(").append(score()).append(")");
        return sb.toString();
    }

}
