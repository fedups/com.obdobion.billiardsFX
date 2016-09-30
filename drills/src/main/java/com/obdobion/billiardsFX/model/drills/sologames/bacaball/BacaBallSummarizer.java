package com.obdobion.billiardsFX.model.drills.sologames.bacaball;

import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.drills.AbstractDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;

/**
 * <p>
 * BacaBallSummarizer class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class BacaBallSummarizer extends AbstractDrillSummarizer
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
            return drillName.instanceClass().getSimpleName().equals(BacaBall.class.getSimpleName());
        } catch (final IllegalArgumentException e)
        {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected IDrillSummary createSummaryInstance()
    {
        final BacaBallSummary result = new BacaBallSummary();
        result.roundFoulLo = 99999;
        result.roundMissLo = 99999;
        result.roundScratchLo = 99999;
        result.roundSafetyLo = 99999;
        return result;
    }

    /** {@inheritDoc} */
    @Override
    protected void summarizeDrill(final IDrill idrill, final IDrillSummary oneDayResult)
    {
        super.summarizeDrill(idrill, oneDayResult);

        int score;
        final BacaBallSummary result = (BacaBallSummary) oneDayResult;
        final BacaBall drill = (BacaBall) idrill;

        for (int i = 0; i < BacaBall.RoundsPerGame; i++)
        {
            score = drill.round(i).getScratches();
            result.roundScratchTot += score;
            if (score > result.roundScratchHi)
                result.roundScratchHi = score;
            if (score < result.roundScratchLo)
                result.roundScratchLo = score;

            score = drill.round(i).getFouls();
            result.roundFoulTot += score;
            if (score > result.roundFoulHi)
                result.roundFoulHi = score;
            if (score < result.roundFoulLo)
                result.roundFoulLo = score;

            score = drill.round(i).getMisses();
            result.roundMissTot += score;
            if (score > result.roundMissHi)
                result.roundMissHi = score;
            if (score < result.roundMissLo)
                result.roundMissLo = score;

            score = drill.round(i).getSafeties();
            result.roundSafetyTot += score;
            if (score > result.roundSafetyHi)
                result.roundSafetyHi = score;
            if (score < result.roundSafetyLo)
                result.roundSafetyLo = score;
        }
    }
}
