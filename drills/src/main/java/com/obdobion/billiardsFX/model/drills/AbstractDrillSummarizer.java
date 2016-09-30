package com.obdobion.billiardsFX.model.drills;

import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreSummarizer;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBallSummarizer;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BowlliardsSummarizer;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBallSummarizer;

/**
 * <p>
 * Abstract AbstractDrillSummarizer class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
abstract public class AbstractDrillSummarizer implements IDrillSummarizer
{
    /**
     * <p>
     * instanceFor.
     * </p>
     *
     * @param filter a {@link com.obdobion.billiardsFX.model.SaveStoreFilter} object.
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDrillSummarizer}
     *         object.
     * @throws java.lang.InstantiationException if any.
     */
    public static IDrillSummarizer instanceFor(final SaveStoreFilter filter) throws InstantiationException
    {
        if (BowlliardsSummarizer.supports(filter))
            return new BowlliardsSummarizer();
        if (ThreeBallSummarizer.supports(filter))
            return new ThreeBallSummarizer();
        if (BacaBallSummarizer.supports(filter))
            return new BacaBallSummarizer();
        if (SingleScoreSummarizer.supports(filter))
            return new SingleScoreSummarizer();
        throw new InstantiationException("Summarizer not found for filter "
                + filter.getPackageIDPattern() + " "
                + filter.getDrillIDPattern());
    }

    /**
     * {@inheritDoc}
     *
     * Compute an average skill level for the given drills.
     */
    @Override
    public SkillLevel averageSkillLevel(final List<IDrill> drills)
    {
        if (drills.size() == 0)
            return SkillLevel.BEGINNER;
        /*
         * All drills should be of the same type, so assume the first one is a
         * good prototype.
         */
        final IDrill protoDrill = drills.get(0);
        /*
         * Compute the total score for the given drills.
         */
        int totalScore = 0;
        int drillCount = 0;

        for (final IDrill drill : drills)
        {
            totalScore += drill.score();
            drillCount++;
        }
        final int averageScore = totalScore / drillCount;

        return protoDrill.skillLevelForScore(averageScore);
    }

    /** {@inheritDoc} */
    @Override
    public List<IDrillSummary> byDay(final List<IDrill> drills)
    {
        drills.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        final List<IDrillSummary> allResults = new ArrayList<>();
        IDrillSummary oneDayResult = null;
        for (final IDrill drill : drills)
        {
            if (oneDayResult == null
                    || oneDayResult.getYear() != drill.getDate().getYear()
                    || (oneDayResult.getMonth()) != drill.getDate().getMonthValue()
                    || oneDayResult.getDay() != drill.getDate().getDayOfMonth())
            {
                if (oneDayResult != null)
                    allResults.add(oneDayResult);

                oneDayResult = createSummaryInstance();
                oneDayResult.setYear(drill.getDate().getYear());
                oneDayResult.setMonth(drill.getDate().getMonthValue());
                oneDayResult.setDay(drill.getDate().getDayOfMonth());
            }
            summarizeDrill(drill, oneDayResult);
        }
        if (oneDayResult != null)
            allResults.add(oneDayResult);
        return allResults;
    }

    /**
     * <p>
     * createSummaryInstance.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDrillSummary} object.
     */
    abstract protected IDrillSummary createSummaryInstance();

    /**
     * <p>
     * summarizeDrill.
     * </p>
     *
     * @param drill a {@link com.obdobion.billiardsFX.model.drills.IDrill} object.
     * @param oneDayResult a
     *            {@link com.obdobion.billiardsFX.model.drills.IDrillSummary} object.
     */
    protected void summarizeDrill(final IDrill drill, final IDrillSummary oneDayResult)
    {
        oneDayResult.setDrillCount(oneDayResult.getDrillCount() + 1);

        final int score = drill.score();
        oneDayResult.setScoreTot(oneDayResult.getScoreTot() + score);
        if (score > oneDayResult.getScoreHi())
            oneDayResult.setScoreHi(score);
        if (oneDayResult.getScoreLo() == 0 || score < oneDayResult.getScoreLo())
            oneDayResult.setScoreLo(score);
    }

}
