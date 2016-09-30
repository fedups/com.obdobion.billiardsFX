package com.obdobion.billiardsFX.model.drills.sologames.threeball;

import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.drills.AbstractDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;

/**
 * <p>
 * ThreeBallSummarizer class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class ThreeBallSummarizer extends AbstractDrillSummarizer
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
            return drillName.instanceClass().getSimpleName().equals(ThreeBall.class.getSimpleName());
        } catch (final IllegalArgumentException e)
        {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected IDrillSummary createSummaryInstance()
    {
        final ThreeBallSummary result = new ThreeBallSummary();
        result.inningFoulLo = 99999;
        return result;
    }

    /** {@inheritDoc} */
    @Override
    protected void summarizeDrill(final IDrill idrill, final IDrillSummary oneDayResult)
    {
        super.summarizeDrill(idrill, oneDayResult);

        int score;
        final ThreeBallSummary result = (ThreeBallSummary) oneDayResult;
        final ThreeBall drill = (ThreeBall) idrill;

        for (int i = 0; i < ThreeBall.InningsPerGame; i++)
        {
            score = drill.inning(i).getStrokes();
            result.inningStrokeTot += score;
            if (score > result.inningStrokeHi)
                result.inningStrokeHi = score;
            if (result.inningStrokeLo == 0 || score < result.inningStrokeLo)
                result.inningStrokeLo = score;

            score = drill.inning(i).getFouls();
            result.inningFoulTot += score;
            if (score > result.inningFoulHi)
                result.inningFoulHi = score;
            if (score < result.inningFoulLo)
                result.inningFoulLo = score;
        }
    }
}
