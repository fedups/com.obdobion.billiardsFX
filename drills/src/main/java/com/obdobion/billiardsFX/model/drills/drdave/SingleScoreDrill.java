package com.obdobion.billiardsFX.model.drills.drdave;

import java.text.ParseException;

import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.drills.AbstractDrill;

/**
 * <p>
 * SingleScoreDrill class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class SingleScoreDrill extends AbstractDrill
{
    int score; // 100 * actual. ex: 6.5 = 650

    /**
     * <p>
     * Constructor for SingleScoreDrill.
     * </p>
     */
    public SingleScoreDrill()
    {
        super();
        DrillMaster.notifyDrillCreatedObservers(getContext(), this);
    }

    /** {@inheritDoc} */
    @Override
    public String gameData()
    {
        return "" + score;
    }

    /** {@inheritDoc} */
    @Override
    public void gameData(final String[] fields, final int start) throws ParseException
    {
        try
        {
            score = Integer.parseInt(fields[start]);
        } catch (final NumberFormatException e)
        {
            throw new ParseException("invalid score " + e.getMessage(), 2);
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
        return score > 0;
    }

    /** {@inheritDoc} */
    @Override
    public String[] readFrom(final String externalRepresentation) throws ParseException
    {
        final String fields[] = super.readFrom(externalRepresentation);
        if (fields.length != 4)
            throw new ParseException("4 fields required ", fields.length);
        if (!(getDrillId().equals(fields[0])))
            throw new ParseException("wrong drill: " + fields[0], 0);
        if (!("1".equals(fields[1])))
            throw new ParseException("unsupported format version: " + fields[1], 1);

        gameData(fields, 2);

        noteFromDiskFormat(fields[3]);
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
        return score;
    }

    /** {@inheritDoc} */
    @Override
    protected int scoreDivisor()
    {
        return 100;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" score(").append(score()).append(")");
        return sb.toString();
    }
}
