package com.obdobion.billiardsFX.model.drills.drdave;

import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.drills.AbstractDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;

/**
 * <p>
 * SingleScoreSummarizer class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class SingleScoreSummarizer extends AbstractDrillSummarizer
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
            return drillName.instanceClass().getSimpleName().equals(SingleScoreDrill.class.getSimpleName());
        } catch (final IllegalArgumentException e)
        {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected IDrillSummary createSummaryInstance()
    {
        final SingleScoreSummary result = new SingleScoreSummary();
        result.setScoreLo(99999);
        return result;
    }
}
