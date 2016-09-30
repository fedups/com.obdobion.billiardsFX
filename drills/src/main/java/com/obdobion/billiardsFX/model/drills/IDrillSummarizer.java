package com.obdobion.billiardsFX.model.drills;

import java.util.List;

/**
 * This interface is for analyzer classes that process drills read from the save
 * store. The results are appropriate for charting purposes.
 *
 * @author degreefc
 */
public interface IDrillSummarizer
{
    /**
     * <p>
     * averageSkillLevel.
     * </p>
     *
     * @param drills a {@link java.util.List} object.
     * @return a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     */
    SkillLevel averageSkillLevel(List<IDrill> drills);

    /**
     * Aggregate the drills by day.
     *
     * @param drills are provided from the save store based on a
     *            {@link com.obdobion.billiardsFX.model.SaveStoreFilter}.
     * @return a list of results aggregated by day
     */
    List<IDrillSummary> byDay(List<IDrill> drills);
}
