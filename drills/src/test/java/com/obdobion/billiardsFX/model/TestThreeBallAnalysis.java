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
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBallSummary;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.UpdateScore;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestThreeBallAnalysis class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestThreeBallAnalysis
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * analyzeThreeBall.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyzeThreeBall() throws Exception
    {
        myDS.clearSaveStore();
        game01("2015-08-18T08:01:01");
        game02("2015-08-18T08:01:01");
        game03("2015-08-19T08:01:01");
        game04("2015-08-20T08:01:01");
        game05("2015-08-20T08:01:01");

        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("sologames");
        ssf.setDrillSelection("threeball");
        ssf.setDateSelection("2015-08-18T08:01:01", SaveStoreFilter.DatePart.MONTH);

        final List<IDrill> drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount all", 5, drills.size());

        final IDrillSummarizer analyzer = DrillMaster.getDrillAnalyzer(ssf);
        final List<IDrillSummary> results = analyzer.byDay(drills);

        Assert.assertEquals("days", 3, results.size());

        Assert.assertEquals("date 1 year", 2015, ((ThreeBallSummary) results.get(0)).getYear());
        Assert.assertEquals("date 1 month", 8, ((ThreeBallSummary) results.get(0)).getMonth());
        Assert.assertEquals("date 1 day", 18, ((ThreeBallSummary) results.get(0)).getDay());
        Assert.assertEquals("date 2 day", 19, ((ThreeBallSummary) results.get(1)).getDay());
        Assert.assertEquals("date 3 day", 20, ((ThreeBallSummary) results.get(2)).getDay());

        // public int inningStrokeHi, inningStrokeLo, inningStrokeTot;
        // public int inningFoulHi, inningFoulLo, inningFoulTot;

        Assert.assertEquals("games d1", 2, ((ThreeBallSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("games d2", 1, ((ThreeBallSummary) results.get(1)).getDrillCount());
        Assert.assertEquals("games d3", 2, ((ThreeBallSummary) results.get(2)).getDrillCount());

        Assert.assertEquals("score tot d1", 43, ((ThreeBallSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score tot d2", 36, ((ThreeBallSummary) results.get(1)).getScoreTot());
        Assert.assertEquals("score tot d3", 34, ((ThreeBallSummary) results.get(2)).getScoreTot());

        Assert.assertEquals("score hi d1", 28, ((ThreeBallSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score hi d2", 36, ((ThreeBallSummary) results.get(1)).getScoreHi());
        Assert.assertEquals("score hi d3", 21, ((ThreeBallSummary) results.get(2)).getScoreHi());

        Assert.assertEquals("score lo d1", 15, ((ThreeBallSummary) results.get(0)).getScoreLo());
        Assert.assertEquals("score lo d2", 36, ((ThreeBallSummary) results.get(1)).getScoreLo());
        Assert.assertEquals("score lo d3", 13, ((ThreeBallSummary) results.get(2)).getScoreLo());

        Assert.assertEquals("inning Stroke tot d1", 40, ((ThreeBallSummary) results.get(0)).inningStrokeTot);
        Assert.assertEquals("inning Stroke tot d2", 30, ((ThreeBallSummary) results.get(1)).inningStrokeTot);
        Assert.assertEquals("inning Stroke tot d3", 31, ((ThreeBallSummary) results.get(2)).inningStrokeTot);

        Assert.assertEquals("inning Stroke hi d1", 7, ((ThreeBallSummary) results.get(0)).inningStrokeHi);
        Assert.assertEquals("inning Stroke hi d2", 8, ((ThreeBallSummary) results.get(1)).inningStrokeHi);
        Assert.assertEquals("inning Stroke hi d3", 5, ((ThreeBallSummary) results.get(2)).inningStrokeHi);

        Assert.assertEquals("inning Stroke lo d1", 3, ((ThreeBallSummary) results.get(0)).inningStrokeLo);
        Assert.assertEquals("inning Stroke lo d2", 5, ((ThreeBallSummary) results.get(1)).inningStrokeLo);
        Assert.assertEquals("inning Stroke lo d3", 1, ((ThreeBallSummary) results.get(2)).inningStrokeLo);

        Assert.assertEquals("inning Foul tot d1", 3, ((ThreeBallSummary) results.get(0)).inningFoulTot);
        Assert.assertEquals("inning Foul tot d2", 6, ((ThreeBallSummary) results.get(1)).inningFoulTot);
        Assert.assertEquals("inning Foul tot d3", 3, ((ThreeBallSummary) results.get(2)).inningFoulTot);

        Assert.assertEquals("inning Foul hi d1", 2, ((ThreeBallSummary) results.get(0)).inningFoulHi);
        Assert.assertEquals("inning Foul hi d2", 2, ((ThreeBallSummary) results.get(1)).inningFoulHi);
        Assert.assertEquals("inning Foul hi d3", 1, ((ThreeBallSummary) results.get(2)).inningFoulHi);

        Assert.assertEquals("inning Foul lo d1", 0, ((ThreeBallSummary) results.get(0)).inningFoulLo);
        Assert.assertEquals("inning Foul lo d2", 0, ((ThreeBallSummary) results.get(1)).inningFoulLo);
        Assert.assertEquals("inning Foul lo d3", 0, ((ThreeBallSummary) results.get(2)).inningFoulLo);
    }

    private void game01(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 15, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game02(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 4, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 5, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 6, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 7, 0));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 28, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game03(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 8, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 6, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 6, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 5, 2));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 36, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game04(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 5, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 5, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 21, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game05(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);
        final ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 4, 1));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 13, drill.score());
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
