package com.obdobion.billiardsFX.model;

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
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.IDrillSummarizer;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;
import com.obdobion.billiardsFX.model.drills.SkillLevel;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BowlliardsSummary;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BreakModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.InningModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateBreak;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateInning;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestBowlliardsAnalysis class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestBowlliardsAnalysis
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * analyzeBowlliards.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyzeBowlliards() throws Exception
    {
        myDS.clearSaveStore();
        game01("2015-08-18T08:01:01");
        game02("2015-08-18T08:01:02");
        game03("2015-08-19T08:01:03");
        game04("2015-08-20T08:01:04");
        game05("2015-08-20T08:01:05");

        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("sologames");
        ssf.setDrillSelection("bowlliards");
        ssf.setDateSelection("2015-08-18T08:01:01", SaveStoreFilter.DatePart.MONTH);

        List<IDrill> drills = null;
        final IDrillSummarizer analyzer = DrillMaster.getDrillAnalyzer(ssf);

        Assert.assertEquals(
                "average skill level 1",
                SkillLevel.INTERMEDIATE,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 1)));
        Assert.assertEquals(
                "average skill level 2",
                SkillLevel.INTERMEDIATE,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 2)));
        Assert.assertEquals(
                "average skill level 3",
                SkillLevel.ADVANCED,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 3)));
        Assert.assertEquals(
                "average skill level 4",
                SkillLevel.ADVANCED,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 4)));
        Assert.assertEquals(
                "average skill level 5",
                SkillLevel.ADVANCED,
                analyzer.averageSkillLevel(
                        DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5)));

        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount all", 5, drills.size());

        final List<IDrillSummary> results = analyzer.byDay(drills);
        Assert.assertEquals("days", 3, results.size());

        Assert.assertEquals("date 1 year", 2015, ((BowlliardsSummary) results.get(0)).getYear());
        Assert.assertEquals("date 1 month", 8, ((BowlliardsSummary) results.get(0)).getMonth());
        Assert.assertEquals("date 1 day", 18, ((BowlliardsSummary) results.get(0)).getDay());
        Assert.assertEquals("date 2 day", 19, ((BowlliardsSummary) results.get(1)).getDay());
        Assert.assertEquals("date 3 day", 20, ((BowlliardsSummary) results.get(2)).getDay());

        Assert.assertEquals("games d1", 2, ((BowlliardsSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("games d2", 1, ((BowlliardsSummary) results.get(1)).getDrillCount());
        Assert.assertEquals("games d3", 2, ((BowlliardsSummary) results.get(2)).getDrillCount());

        Assert.assertEquals("scratch on break d1", 1, ((BowlliardsSummary) results.get(0)).scratchOnBreakCount);
        Assert.assertEquals("scratch on break d2", 1, ((BowlliardsSummary) results.get(1)).scratchOnBreakCount);
        Assert.assertEquals("scratch on break d3", 0, ((BowlliardsSummary) results.get(2)).scratchOnBreakCount);

        Assert.assertEquals("scratches tot d1", 4, ((BowlliardsSummary) results.get(0)).scratchCountTot);
        Assert.assertEquals("scratches tot d2", 0, ((BowlliardsSummary) results.get(1)).scratchCountTot);
        Assert.assertEquals("scratches tot d3", 4, ((BowlliardsSummary) results.get(2)).scratchCountTot);

        Assert.assertEquals("scratches hi d1", 2, ((BowlliardsSummary) results.get(0)).scratchCountHi);
        Assert.assertEquals("scratches hi d2", 0, ((BowlliardsSummary) results.get(1)).scratchCountHi);
        Assert.assertEquals("scratches hi d3", 2, ((BowlliardsSummary) results.get(2)).scratchCountHi);

        Assert.assertEquals("scratches lo d1", 0, ((BowlliardsSummary) results.get(0)).scratchCountLo);
        Assert.assertEquals("scratches lo d2", 0, ((BowlliardsSummary) results.get(1)).scratchCountLo);
        Assert.assertEquals("scratches lo d3", 0, ((BowlliardsSummary) results.get(2)).scratchCountLo);

        Assert.assertEquals("misses tot d1", 11, ((BowlliardsSummary) results.get(0)).missCountTot);
        Assert.assertEquals("misses tot d2", 0, ((BowlliardsSummary) results.get(1)).missCountTot);
        Assert.assertEquals("misses tot d3", 24, ((BowlliardsSummary) results.get(2)).missCountTot);

        Assert.assertEquals("misses hi d1", 9, ((BowlliardsSummary) results.get(0)).missCountHi);
        Assert.assertEquals("misses hi d2", 0, ((BowlliardsSummary) results.get(1)).missCountHi);
        Assert.assertEquals("misses hi d3", 15, ((BowlliardsSummary) results.get(2)).missCountHi);

        Assert.assertEquals("misses lo d1", 0, ((BowlliardsSummary) results.get(0)).missCountLo);
        Assert.assertEquals("misses lo d2", 0, ((BowlliardsSummary) results.get(1)).missCountLo);
        Assert.assertEquals("misses lo d3", 0, ((BowlliardsSummary) results.get(2)).missCountLo);

        Assert.assertEquals("spares tot d1", 8, ((BowlliardsSummary) results.get(0)).spareCountTot);
        Assert.assertEquals("spares tot d2", 0, ((BowlliardsSummary) results.get(1)).spareCountTot);
        Assert.assertEquals("spares tot d3", 1, ((BowlliardsSummary) results.get(2)).spareCountTot);

        Assert.assertEquals("spares Hi d1", 6, ((BowlliardsSummary) results.get(0)).spareCountHi);
        Assert.assertEquals("spares Hi d2", 0, ((BowlliardsSummary) results.get(1)).spareCountHi);
        Assert.assertEquals("spares Hi d3", 1, ((BowlliardsSummary) results.get(2)).spareCountHi);

        Assert.assertEquals("spares Lo d1", 0, ((BowlliardsSummary) results.get(0)).spareCountLo);
        Assert.assertEquals("spares Lo d2", 0, ((BowlliardsSummary) results.get(1)).spareCountLo);
        Assert.assertEquals("spares Lo d3", 0, ((BowlliardsSummary) results.get(2)).spareCountLo);

        Assert.assertEquals("strikes tot d1", 4, ((BowlliardsSummary) results.get(0)).strikeCountTot);
        Assert.assertEquals("strikes tot d2", 12, ((BowlliardsSummary) results.get(1)).strikeCountTot);
        Assert.assertEquals("strikes tot d3", 3, ((BowlliardsSummary) results.get(2)).strikeCountTot);

        Assert.assertEquals("strikes Hi d1", 3, ((BowlliardsSummary) results.get(0)).strikeCountHi);
        Assert.assertEquals("strikes Hi d2", 12, ((BowlliardsSummary) results.get(1)).strikeCountHi);
        Assert.assertEquals("strikes Hi d3", 3, ((BowlliardsSummary) results.get(2)).strikeCountHi);

        Assert.assertEquals("strikes Lo d1", 0, ((BowlliardsSummary) results.get(0)).strikeCountLo);
        Assert.assertEquals("strikes Lo d2", 0, ((BowlliardsSummary) results.get(1)).strikeCountLo);
        Assert.assertEquals("strikes Lo d3", 0, ((BowlliardsSummary) results.get(2)).strikeCountLo);

        Assert.assertEquals("score tot d1", 278, ((BowlliardsSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score tot d2", 300, ((BowlliardsSummary) results.get(1)).getScoreTot());
        Assert.assertEquals("score tot d3", 168, ((BowlliardsSummary) results.get(2)).getScoreTot());

        Assert.assertEquals("score hi d1", 143, ((BowlliardsSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score hi d2", 300, ((BowlliardsSummary) results.get(1)).getScoreHi());
        Assert.assertEquals("score hi d3", 110, ((BowlliardsSummary) results.get(2)).getScoreHi());

        Assert.assertEquals("score lo d1", 135, ((BowlliardsSummary) results.get(0)).getScoreLo());
        Assert.assertEquals("score lo d2", 300, ((BowlliardsSummary) results.get(1)).getScoreLo());
        Assert.assertEquals("score lo d3", 58, ((BowlliardsSummary) results.get(2)).getScoreLo());

        Assert.assertEquals("score avg d1", 139, ((BowlliardsSummary) results.get(0)).getScoreTot()
                / ((BowlliardsSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("score avg d2", 300, ((BowlliardsSummary) results.get(1)).getScoreTot()
                / ((BowlliardsSummary) results.get(1)).getDrillCount());
        Assert.assertEquals("score avg d3", 84, ((BowlliardsSummary) results.get(2)).getScoreTot()
                / ((BowlliardsSummary) results.get(2)).getDrillCount());

        Assert.assertEquals("pots on break tot d1", 2, ((BowlliardsSummary) results.get(0)).potsOnBreakTot);
        Assert.assertEquals("pots on break tot d2", 1, ((BowlliardsSummary) results.get(1)).potsOnBreakTot);
        Assert.assertEquals("pots on break tot d3", 0, ((BowlliardsSummary) results.get(2)).potsOnBreakTot);

        Assert.assertEquals("pots on break hi d1", 2, ((BowlliardsSummary) results.get(0)).potsOnBreakHi);
        Assert.assertEquals("pots on break hi d2", 1, ((BowlliardsSummary) results.get(1)).potsOnBreakHi);
        Assert.assertEquals("pots on break hi d3", 0, ((BowlliardsSummary) results.get(2)).potsOnBreakHi);

        Assert.assertEquals("pots on break lo d1", 0, ((BowlliardsSummary) results.get(0)).potsOnBreakLo);
        Assert.assertEquals("pots on break lo d2", 1, ((BowlliardsSummary) results.get(1)).potsOnBreakLo);
        Assert.assertEquals("pots on break lo d3", 0, ((BowlliardsSummary) results.get(2)).potsOnBreakLo);

        Assert.assertEquals("frame tot d1", 186, ((BowlliardsSummary) results.get(0)).frameTot);
        Assert.assertEquals("frame tot d2", 120, ((BowlliardsSummary) results.get(1)).frameTot);
        Assert.assertEquals("frame tot d3", 126, ((BowlliardsSummary) results.get(2)).frameTot);

        Assert.assertEquals("frame hi d1", 10, ((BowlliardsSummary) results.get(0)).frameHi);
        Assert.assertEquals("frame hi d2", 10, ((BowlliardsSummary) results.get(1)).frameHi);
        Assert.assertEquals("frame hi d3", 10, ((BowlliardsSummary) results.get(2)).frameHi);

        Assert.assertEquals("frame lo d1", 6, ((BowlliardsSummary) results.get(0)).frameLo);
        Assert.assertEquals("frame lo d2", 10, ((BowlliardsSummary) results.get(1)).frameLo);
        Assert.assertEquals("frame lo d3", 1, ((BowlliardsSummary) results.get(2)).frameLo);

        Assert.assertEquals("inning1 tot d1", 125, ((BowlliardsSummary) results.get(0)).inning1Tot);
        Assert.assertEquals("inning1 tot d2", 120, ((BowlliardsSummary) results.get(1)).inning1Tot);
        Assert.assertEquals("inning1 tot d3", 76, ((BowlliardsSummary) results.get(2)).inning1Tot);

        Assert.assertEquals("inning1 hi d1", 10, ((BowlliardsSummary) results.get(0)).inning1Hi);
        Assert.assertEquals("inning1 hi d2", 10, ((BowlliardsSummary) results.get(1)).inning1Hi);
        Assert.assertEquals("inning1 hi d3", 10, ((BowlliardsSummary) results.get(2)).inning1Hi);

        Assert.assertEquals("inning1 lo d1", 4, ((BowlliardsSummary) results.get(0)).inning1Lo);
        Assert.assertEquals("inning1 lo d2", 10, ((BowlliardsSummary) results.get(1)).inning1Lo);
        Assert.assertEquals("inning1 lo d3", 4, ((BowlliardsSummary) results.get(2)).inning1Lo);

        Assert.assertEquals("inning2 tot d1", 61, ((BowlliardsSummary) results.get(0)).inning2Tot);
        Assert.assertEquals("inning2 tot d2", 0, ((BowlliardsSummary) results.get(1)).inning2Tot);
        Assert.assertEquals("inning2 tot d3", 50, ((BowlliardsSummary) results.get(2)).inning2Tot);

        Assert.assertEquals("inning2 hi d1", 5, ((BowlliardsSummary) results.get(0)).inning2Hi);
        Assert.assertEquals("inning2 hi d2", 0, ((BowlliardsSummary) results.get(1)).inning2Hi);
        Assert.assertEquals("inning2 hi d3", 8, ((BowlliardsSummary) results.get(2)).inning2Hi);

        Assert.assertEquals("inning2 lo d1", 0, ((BowlliardsSummary) results.get(0)).inning2Lo);
        Assert.assertEquals("inning2 lo d2", 0, ((BowlliardsSummary) results.get(1)).inning2Lo);
        Assert.assertEquals("inning2 lo d3", 0, ((BowlliardsSummary) results.get(2)).inning2Lo);
    }

    /**
     * <p>
     * filterSelection.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void filterSelection() throws Exception
    {
        myDS.clearSaveStore();
        game01("2015-08-18T08:01:01");
        game01("2015-08-18T08:01:01");
        game01("2015-08-19T08:01:01");
        game01("2015-08-20T08:01:01");
        game01("2015-08-20T08:01:01");

        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("sologames");
        ssf.setDrillSelection("bowlliards");

        List<IDrill> drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 2);
        Assert.assertEquals("drillCount most recent 2", 2, drills.size());

        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount all", 5, drills.size());

        ssf.setDateSelection("2015-08-18T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount 8/18", 2, drills.size());

        ssf.setDateSelection("2015-08-19T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount 8/19", 1, drills.size());

        ssf.setDateSelection("2015-08-20T08:01:01", SaveStoreFilter.DatePart.DAY);
        drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount 8/20", 2, drills.size());

    }

    private void game01(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 2, BreakModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 4, InningModifier.SCRATCH, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.SCRATCH));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4));
        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 135, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game02(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 8, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.SCRATCH));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 8, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 7, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 2, InningModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 143, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game03(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 1, BreakModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 300, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game04(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 3, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 3, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 3, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 3, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.SCRATCH));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 8, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 2, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 2, InningModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 8));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 58, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game05(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 2, InningModifier.SCRATCH));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 3));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 2, InningModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1, InningModifier.MISS));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 110, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
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
        myDS.getDrillPackageManager().add(new SoloGames());
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
