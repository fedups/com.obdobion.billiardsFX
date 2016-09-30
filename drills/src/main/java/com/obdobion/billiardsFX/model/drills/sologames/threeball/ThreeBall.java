package com.obdobion.billiardsFX.model.drills.sologames.threeball;

import java.text.ParseException;

import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.drills.AbstractDrill;

/**
 * As the name would suggest, Three Ball is played with just three balls, set up
 * using either a standard triangle or diamond rack. The concept of Three Ball
 * is simple. You must get all three balls into the pockets of the table in as
 * few shots as possible. Neither the opening shot nor missed shots count as
 * actual shots. Any fouls count as an additional shot, but the rules are
 * simplified from the traditional billiards game so that most traditional fouls
 * are disregarded. The only fowls in this game are moving any ball with your
 * hand or cue, shooting the cue ball off the table, making jump shots or push
 * shots, or having a double hit on the cue ball. Because the last three balls
 * are generally the most important and stressful shots of any pool game,
 * playing three ball can really develop your mental game as well as your shot
 * accuracy.
 *
 * @author degreefc
 */
public class ThreeBall extends AbstractDrill
{
    /** Constant <code>InningsPerGame=5</code> */
    static final public int InningsPerGame = 5;

    int                     currentInning  = 0;
    private final Inning[]  inning;

    /**
     * <p>
     * Constructor for ThreeBall.
     * </p>
     */
    public ThreeBall()
    {
        super();
        inning = new Inning[InningsPerGame];
        for (int i = 0; i < InningsPerGame; i++)
            inning[i] = new Inning();
        DrillMaster.notifyDrillCreatedObservers(getContext(), this);
    }

    /** {@inheritDoc} */
    @Override
    public String gameData()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(currentInning);
        for (int i = 0; i < InningsPerGame; i++)
        {
            sb.append(",");
            sb.append(i);
            sb.append(",");
            sb.append(inning(i).getStrokes());
            sb.append(",");
            sb.append(inning(i).getFouls());
            sb.append(",");
            sb.append(externalize(inning(i).isComplete()));
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
            currentInning = Integer.parseInt(fields[fieldIndex]);
        } catch (final NumberFormatException e)
        {
            throw new ParseException("invalid current inning " + e.getMessage(), 2);
        }

        for (int i = 0; i < InningsPerGame; i++)
        {
            final int offset = i * 4;
            try
            {
                final int iNumber = Integer.parseInt(fields[start + offset + 1]);
                if (iNumber != i)
                    throw new ParseException("out of sequence inning: " + iNumber, start + offset + 1);
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid inning number " + e.getMessage(), start + offset + 1);
            }
            try
            {
                inning(i).setStrokes(Integer.parseInt(fields[start + offset + 2]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid strokes " + e.getMessage(), start + offset + 2);
            }
            try
            {
                inning(i).setFouls(Integer.parseInt(fields[start + offset + 3]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid fouls " + e.getMessage(), start + offset + 3);
            }
            inning(i).setComplete(internalizeBoolean(fields[4]));
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
     * inning.
     * </p>
     *
     * @param i a int.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.sologames.threeball.Inning}
     *         object.
     */
    public Inning inning(final int i)
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
        return currentInning >= InningsPerGame;
    }

    /** {@inheritDoc} */
    @Override
    public String[] readFrom(final String externalRepresentation) throws ParseException
    {
        final String fields[] = super.readFrom(externalRepresentation);
        if (fields.length != 24)
            throw new ParseException("24 fields required: found " + fields.length, fields.length);
        if (!(getDrillId().equals(fields[0])))
            throw new ParseException("wrong drill: " + fields[0], 0);
        if (!("1".equals(fields[1])))
            throw new ParseException("unsupported format version: " + fields[1], 1);
        gameData(fields, 2);
        noteFromDiskFormat(fields[23]);
        return fields;
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
        int score = 0;
        for (int i = 0; i < currentInning; i++)
        {
            score += inning(i).getStrokes() + inning(i).getFouls();
        }
        return score;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" currentInning(").append(currentInning).append(")");
        sb.append(" score(").append(score()).append(")");
        return sb.toString();
    }
}
