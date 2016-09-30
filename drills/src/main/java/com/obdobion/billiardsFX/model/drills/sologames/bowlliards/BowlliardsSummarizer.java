package com.obdobion.billiardsFX.model.drills.sologames.bowlliards;

import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.drills.AbstractDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;

/**
 * <p>
 * BowlliardsSummarizer class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class BowlliardsSummarizer extends AbstractDrillSummarizer
{
    /**
     * <p>
     * supports.
     * </p>
     *
     * @param filter a {@link com.obdobion.billiardsFX.model.SaveStoreFilter} object.
     * @return a boolean.
     */
    public static boolean supports(final SaveStoreFilter filter)
    {
        try
        {
            final DrillName drillName = DrillName.valueOf(filter.getDrillIDPattern());
            return drillName.instanceClass().getSimpleName().equals(Bowlliards.class.getSimpleName());
        } catch (final IllegalArgumentException e)
        {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected IDrillSummary createSummaryInstance()
    {
        return new BowlliardsSummary();
    }

    /** {@inheritDoc} */
    @Override
    protected void summarizeDrill(final IDrill idrill, final IDrillSummary result)
    {
        int score;
        super.summarizeDrill(idrill, result);

        final Bowlliards drill = (Bowlliards) idrill;
        final BowlliardsSummary oneDayResult = (BowlliardsSummary) result;

        score = drill.potsOnBreak;
        oneDayResult.potsOnBreakTot += score;
        if (score > oneDayResult.potsOnBreakHi)
            oneDayResult.potsOnBreakHi = score;
        if (oneDayResult.potsOnBreakLo == 0 || score < oneDayResult.potsOnBreakLo)
            oneDayResult.potsOnBreakLo = score;

        oneDayResult.scratchOnBreakCount += (drill.scratchOnBreak
                ? 1
                : 0);

        int gameSpareCount = 0;
        int gameStrikeCount = 0;
        int gameScratchCount = 0;
        int gameMissCount = 0;

        for (int f = 0; f <= drill.highestFrameInGame(); f++)
        {
            score = drill.frameScore(f);
            oneDayResult.frameTot += score;
            if (score > oneDayResult.frameHi)
                oneDayResult.frameHi = score;
            if (oneDayResult.frameLo == 0 || score < oneDayResult.frameLo)
                oneDayResult.frameLo = score;

            score = drill.inningScore(f, 0);
            oneDayResult.inning1Tot += score;
            if (score > oneDayResult.inning1Hi)
                oneDayResult.inning1Hi = score;
            if (oneDayResult.inning1Lo == 0 || score < oneDayResult.inning1Lo)
                oneDayResult.inning1Lo = score;

            score = drill.inningScore(f, 1);
            oneDayResult.inning2Tot += score;
            if (score > oneDayResult.inning2Hi)
                oneDayResult.inning2Hi = score;
            if (f == 0 || score < oneDayResult.inning2Lo)
                oneDayResult.inning2Lo = score;

            gameSpareCount += (drill.frame(f).isSpare()
                    ? 1
                    : 0);
            gameStrikeCount += (drill.frame(f).isStrike()
                    ? 1
                    : 0);
            gameScratchCount += drill.frameScratchCount(f);
            gameMissCount += drill.frameMissCount(f);

        }

        score = gameSpareCount;
        oneDayResult.spareCountTot += score;
        if (score > oneDayResult.spareCountHi)
            oneDayResult.spareCountHi = score;
        if (score < oneDayResult.spareCountLo)
            oneDayResult.spareCountLo = score;

        score = gameStrikeCount;
        oneDayResult.strikeCountTot += score;
        if (score > oneDayResult.strikeCountHi)
            oneDayResult.strikeCountHi = score;
        if (score < oneDayResult.strikeCountLo)
            oneDayResult.strikeCountLo = score;

        score = gameScratchCount;
        oneDayResult.scratchCountTot += score;
        if (score > oneDayResult.scratchCountHi)
            oneDayResult.scratchCountHi = score;
        if (score < oneDayResult.scratchCountLo)
            oneDayResult.scratchCountLo = score;

        score = gameMissCount;
        oneDayResult.missCountTot += score;
        if (score > oneDayResult.missCountHi)
            oneDayResult.missCountHi = score;
        if (score < oneDayResult.missCountLo)
            oneDayResult.missCountLo = score;
    }
}
