package com.obdobion.billiardsFX.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;
import com.obdobion.billiardsFX.model.drills.SkillLevel;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreSummary;
import com.obdobion.billiardsFX.model.drills.drdave.UpdateScoreFromFloat;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_01;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestProgressivePracticeAnalysis class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestProgressivePracticeAnalysis
{
    static private void playPP(final String drillID, final float score)
            throws ParseException, InstantiationException, IOException, IllegalAccessException
    {
        SingleScoreDrill drill = (SingleScoreDrill) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter(drillID)).get(0));

        Assert.assertEquals("score", 0, drill.score());
        Assert.assertFalse("complete", drill.isComplete());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScoreFromFloat(drill, score));

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", (int) (score * 100), drill.score());

        DrillMaster.saveDrillToTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        drill = (SingleScoreDrill) DrillMaster.createDrillFromTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION);

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", (int) (score * 100), drill.score());

        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * analyze_ppdraw1c.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyze_ppdraw1c() throws Exception
    {
        IDrillSummarizer analyzer;
        List<IDrill> drills;
        List<IDrillSummary> results;
        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setDrillSelection(DrillName.ppdraw1c.displayName());
        /*
         * day 1
         */
        ssf.setDateSelection("2015-09-01T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 3, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);

        Assert.assertEquals(
                "average skill level",
                SkillLevel.BEGINNER,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99)));

        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 3, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1150, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 400, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 350, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 2
         */
        ssf.setDateSelection("2015-09-02T08:01:01", SaveStoreFilter.DatePart.DAY);

        Assert.assertEquals(
                "average skill level",
                SkillLevel.BEGINNER,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99)));

        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 3, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 2, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 3, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1180, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 410, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 360, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 3
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.DAY);

        Assert.assertEquals(
                "average skill level",
                SkillLevel.BEGINNER,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99)));

        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 3, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 3, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 3, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1208, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 419, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 370, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * all days in month
         */

        Assert.assertEquals(
                "average skill level",
                SkillLevel.BEGINNER,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99)));

        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.MONTH);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 9, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 3, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day 1", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("day 2", 2, ((SingleScoreSummary) results.get(1)).getDay());
        Assert.assertEquals("day 3", 3, ((SingleScoreSummary) results.get(2)).getDay());
    }

    /**
     * <p>
     * analyze_ppdraw2c.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyze_ppdraw2c() throws Exception
    {
        IDrillSummarizer analyzer;
        List<IDrill> drills;
        List<IDrillSummary> results;
        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("vepp_01");
        ssf.setDrillSelection("ppdraw2c");
        /*
         * day 1
         */
        ssf.setDateSelection("2015-09-01T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 2, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);

        Assert.assertEquals(
                "average skill level",
                SkillLevel.BEGINNER,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99)));

        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 2, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 400, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 250, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 150, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 2
         */
        ssf.setDateSelection("2015-09-02T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 2, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 2, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 2, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 420, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 260, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 160, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 3
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 2, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 3, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 2, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 440, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 270, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 170, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * all days in month
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.MONTH);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 6, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 3, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day 1", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("day 2", 2, ((SingleScoreSummary) results.get(1)).getDay());
        Assert.assertEquals("day 3", 3, ((SingleScoreSummary) results.get(2)).getDay());
    }

    /**
     * <p>
     * analyze_ppdraw3c.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyze_ppdraw3c() throws Exception
    {
        IDrillSummarizer analyzer;
        List<IDrill> drills;
        List<IDrillSummary> results;
        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("vepp_01");
        ssf.setDrillSelection("ppdraw3c");
        /*
         * day 1
         */
        ssf.setDateSelection("2015-09-01T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 4, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 4, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 700, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 300, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 100, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 2
         */
        ssf.setDateSelection("2015-09-02T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 4, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 2, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 4, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1100, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 400, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 200, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 3
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 4, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 3, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 4, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1600, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 500, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 300, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * all days in month
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.MONTH);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 12, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 3, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day 1", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("day 2", 2, ((SingleScoreSummary) results.get(1)).getDay());
        Assert.assertEquals("day 3", 3, ((SingleScoreSummary) results.get(2)).getDay());
    }

    /**
     * <p>
     * analyze_ppdraw4c.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyze_ppdraw4c() throws Exception
    {
        IDrillSummarizer analyzer;
        List<IDrill> drills;
        List<IDrillSummary> results;
        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("vepp_01");
        ssf.setDrillSelection("ppdraw4c");
        /*
         * day 1
         */
        ssf.setDateSelection("2015-09-01T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 2, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 2, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 500, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 350, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 150, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 2
         */
        ssf.setDateSelection("2015-09-02T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 2, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 2, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 2, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 520, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 360, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 160, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 3
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 2, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 3, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 2, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 540, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 370, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 170, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * all days in month
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.MONTH);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 6, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 3, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day 1", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("day 2", 2, ((SingleScoreSummary) results.get(1)).getDay());
        Assert.assertEquals("day 3", 3, ((SingleScoreSummary) results.get(2)).getDay());
    }

    /**
     * <p>
     * analyze_ppdraw5c.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyze_ppdraw5c() throws Exception
    {
        IDrillSummarizer analyzer;
        List<IDrill> drills;
        List<IDrillSummary> results;
        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("vepp_01");
        ssf.setDrillSelection("ppdraw5c");
        /*
         * day 1
         */
        ssf.setDateSelection("2015-09-01T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 3, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 3, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1200, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 500, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 300, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 2
         */
        ssf.setDateSelection("2015-09-02T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 3, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 2, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 3, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1500, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 600, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 400, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * day 3
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 3, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 1, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day", 3, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("games", 3, ((SingleScoreSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score tot", 1800, ((SingleScoreSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score hi", 700, ((SingleScoreSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score lo", 500, ((SingleScoreSummary) results.get(0)).getScoreLo());
        /*
         * all days in month
         */
        ssf.setDateSelection("2015-09-03T08:01:01", SaveStoreFilter.DatePart.MONTH);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 99);
        Assert.assertEquals("drillCount all", 9, drills.size());
        analyzer = DrillMaster.getDrillAnalyzer(ssf);
        results = analyzer.byDay(drills);
        Assert.assertEquals("days", 3, results.size());
        Assert.assertEquals("year", 2015, ((SingleScoreSummary) results.get(0)).getYear());
        Assert.assertEquals("month", 9, ((SingleScoreSummary) results.get(0)).getMonth());
        Assert.assertEquals("day 1", 1, ((SingleScoreSummary) results.get(0)).getDay());
        Assert.assertEquals("day 2", 2, ((SingleScoreSummary) results.get(1)).getDay());
        Assert.assertEquals("day 3", 3, ((SingleScoreSummary) results.get(2)).getDay());
    }

    private void day01() throws Exception
    {
        setCurrentDateTime("2015-09-01T08:01:01");
        playPP("ppdraw1c", 3.5F);
        setCurrentDateTime("2015-09-01T08:01:02");
        playPP("ppdraw1c", 4.0F);
        setCurrentDateTime("2015-09-01T08:01:03");
        playPP("ppdraw1c", 4.0F);
        setCurrentDateTime("2015-09-01T08:01:04");
        playPP("ppdraw2c", 1.5F);
        setCurrentDateTime("2015-09-01T08:01:05");
        playPP("ppdraw2c", 2.5F);
        setCurrentDateTime("2015-09-01T08:01:06");
        playPP("ppdraw3c", 1F);
        setCurrentDateTime("2015-09-01T08:01:07");
        playPP("ppdraw3c", 1F);
        setCurrentDateTime("2015-09-01T08:01:08");
        playPP("ppdraw3c", 2F);
        setCurrentDateTime("2015-09-01T08:01:09");
        playPP("ppdraw3c", 3F);
        setCurrentDateTime("2015-09-01T08:01:10");
        playPP("ppdraw4c", 1.5F);
        setCurrentDateTime("2015-09-01T08:01:11");
        playPP("ppdraw4c", 3.5F);
        setCurrentDateTime("2015-09-01T08:01:12");
        playPP("ppdraw5c", 3F);
        setCurrentDateTime("2015-09-01T08:01:13");
        playPP("ppdraw5c", 4F);
        setCurrentDateTime("2015-09-01T08:01:14");
        playPP("ppdraw5c", 5F);
    }

    private void day02() throws Exception
    {
        setCurrentDateTime("2015-09-02T08:01:01");
        playPP("ppdraw1c", 3.6F);
        setCurrentDateTime("2015-09-02T08:01:02");
        playPP("ppdraw1c", 4.1F);
        setCurrentDateTime("2015-09-02T08:01:03");
        playPP("ppdraw1c", 4.1F);
        setCurrentDateTime("2015-09-02T08:01:04");
        playPP("ppdraw2c", 1.6F);
        setCurrentDateTime("2015-09-02T08:01:05");
        playPP("ppdraw2c", 2.6F);
        setCurrentDateTime("2015-09-02T08:01:06");
        playPP("ppdraw3c", 2F);
        setCurrentDateTime("2015-09-02T08:01:07");
        playPP("ppdraw3c", 2F);
        setCurrentDateTime("2015-09-02T08:01:08");
        playPP("ppdraw3c", 3F);
        setCurrentDateTime("2015-09-02T08:01:09");
        playPP("ppdraw3c", 4F);
        setCurrentDateTime("2015-09-02T08:01:10");
        playPP("ppdraw4c", 1.6F);
        setCurrentDateTime("2015-09-02T08:01:11");
        playPP("ppdraw4c", 3.6F);
        setCurrentDateTime("2015-09-02T08:01:12");
        playPP("ppdraw5c", 4F);
        setCurrentDateTime("2015-09-02T08:01:13");
        playPP("ppdraw5c", 5F);
        setCurrentDateTime("2015-09-02T08:01:14");
        playPP("ppdraw5c", 6F);
    }

    private void day03() throws Exception
    {
        setCurrentDateTime("2015-09-03T08:01:01");
        playPP("ppdraw1c", 3.7F);
        setCurrentDateTime("2015-09-03T08:01:02");
        playPP("ppdraw1c", 4.2F);
        setCurrentDateTime("2015-09-03T08:01:03");
        playPP("ppdraw1c", 4.2F);
        setCurrentDateTime("2015-09-03T08:01:04");
        playPP("ppdraw2c", 1.7F);
        setCurrentDateTime("2015-09-03T08:01:05");
        playPP("ppdraw2c", 2.7F);
        setCurrentDateTime("2015-09-03T08:01:06");
        playPP("ppdraw3c", 3F);
        setCurrentDateTime("2015-09-03T08:01:07");
        playPP("ppdraw3c", 3F);
        setCurrentDateTime("2015-09-03T08:01:08");
        playPP("ppdraw3c", 5F);
        setCurrentDateTime("2015-09-03T08:01:09");
        playPP("ppdraw3c", 5F);
        setCurrentDateTime("2015-09-03T08:01:10");
        playPP("ppdraw4c", 1.7F);
        setCurrentDateTime("2015-09-03T08:01:11");
        playPP("ppdraw4c", 3.7F);
        setCurrentDateTime("2015-09-03T08:01:12");
        playPP("ppdraw5c", 5F);
        setCurrentDateTime("2015-09-03T08:01:13");
        playPP("ppdraw5c", 6F);
        setCurrentDateTime("2015-09-03T08:01:14");
        playPP("ppdraw5c", 7F);
    }

    private void setCurrentDateTime(final String override) throws ParseException
    {
        myDS.setCurrentDateTime(CalendarFactory.modify(override));
    }

    /**
     * <p>
     * setUp.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Before
    public void setUp() throws Exception
    {
        previousDrillSergeant = DrillMaster.setInstance(myDS = new JUnitDrillMasterImpl());
        myDS.getDrillPackageManager().add(new VEPP_01());
        myDS.clearSaveStore();
        day01();
        day02();
        day03();
    }

    /**
     * <p>
     * tearDown.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @After
    public void tearDown() throws Exception
    {
        DrillMaster.setInstance(previousDrillSergeant);
    }
}
