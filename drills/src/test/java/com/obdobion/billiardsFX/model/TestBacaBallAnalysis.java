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
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBallSummary;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.UpdateRound;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestBacaBallAnalysis class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestBacaBallAnalysis
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * analyzeBacaBall.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void analyzeBacaBall() throws Exception
    {
        myDS.clearSaveStore();
        game01("2015-08-18T08:01:01");
        game02("2015-08-18T08:01:01");
        game03("2015-08-19T08:01:01");
        game04("2015-08-20T08:01:01");
        game05("2015-08-20T08:01:01");

        final SaveStoreFilter ssf = new SaveStoreFilter();
        ssf.setPackageSelection("sologames");
        ssf.setDrillSelection("bacaball");
        ssf.setDateSelection("2015-08-18T08:01:01", SaveStoreFilter.DatePart.MONTH);

        final List<IDrill> drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, ssf, 5);
        Assert.assertEquals("drillCount all", 5, drills.size());

        final IDrillSummarizer analyzer = DrillMaster.getDrillAnalyzer(ssf);
        final List<IDrillSummary> results = analyzer.byDay(drills);

        Assert.assertEquals("days", 3, results.size());

        Assert.assertEquals("date 1 year", 2015, ((BacaBallSummary) results.get(0)).getYear());
        Assert.assertEquals("date 1 month", 8, ((BacaBallSummary) results.get(0)).getMonth());
        Assert.assertEquals("date 1 day", 18, ((BacaBallSummary) results.get(0)).getDay());
        Assert.assertEquals("date 2 day", 19, ((BacaBallSummary) results.get(1)).getDay());
        Assert.assertEquals("date 3 day", 20, ((BacaBallSummary) results.get(2)).getDay());

        // public int roundStrokeHi, roundStrokeLo, roundStrokeTot;
        // public int roundFoulHi, roundFoulLo, roundFoulTot;

        Assert.assertEquals("games d1", 2, ((BacaBallSummary) results.get(0)).getDrillCount());
        Assert.assertEquals("games d2", 1, ((BacaBallSummary) results.get(1)).getDrillCount());
        Assert.assertEquals("games d3", 2, ((BacaBallSummary) results.get(2)).getDrillCount());

        Assert.assertEquals("score tot d1", 188, ((BacaBallSummary) results.get(0)).getScoreTot());
        Assert.assertEquals("score tot d2", 79, ((BacaBallSummary) results.get(1)).getScoreTot());
        Assert.assertEquals("score tot d3", 46, ((BacaBallSummary) results.get(2)).getScoreTot());

        Assert.assertEquals("score hi d1", 100, ((BacaBallSummary) results.get(0)).getScoreHi());
        Assert.assertEquals("score hi d2", 79, ((BacaBallSummary) results.get(1)).getScoreHi());
        Assert.assertEquals("score hi d3", 46, ((BacaBallSummary) results.get(2)).getScoreHi());

        Assert.assertEquals("score lo d1", 88, ((BacaBallSummary) results.get(0)).getScoreLo());
        Assert.assertEquals("score lo d2", 79, ((BacaBallSummary) results.get(1)).getScoreLo());
        Assert.assertEquals("score lo d3", 0, ((BacaBallSummary) results.get(2)).getScoreLo());

        Assert.assertEquals("round Scratch tot d1", 4, ((BacaBallSummary) results.get(0)).roundScratchTot);
        Assert.assertEquals("round Scratch tot d2", 7, ((BacaBallSummary) results.get(1)).roundScratchTot);
        Assert.assertEquals("round Scratch tot d3", 70, ((BacaBallSummary) results.get(2)).roundScratchTot);

        Assert.assertEquals("round Scratch hi d1", 1, ((BacaBallSummary) results.get(0)).roundScratchHi);
        Assert.assertEquals("round Scratch hi d2", 2, ((BacaBallSummary) results.get(1)).roundScratchHi);
        Assert.assertEquals("round Scratch hi d3", 5, ((BacaBallSummary) results.get(2)).roundScratchHi);

        Assert.assertEquals("round Scratch lo d1", 0, ((BacaBallSummary) results.get(0)).roundScratchLo);
        Assert.assertEquals("round Scratch lo d2", 0, ((BacaBallSummary) results.get(1)).roundScratchLo);
        Assert.assertEquals("round Scratch lo d3", 2, ((BacaBallSummary) results.get(2)).roundScratchLo);

        Assert.assertEquals("round Foul tot d1", 4, ((BacaBallSummary) results.get(0)).roundFoulTot);
        Assert.assertEquals("round Foul tot d2", 7, ((BacaBallSummary) results.get(1)).roundFoulTot);
        Assert.assertEquals("round Foul tot d3", 80, ((BacaBallSummary) results.get(2)).roundFoulTot);

        Assert.assertEquals("round Foul hi d1", 1, ((BacaBallSummary) results.get(0)).roundFoulHi);
        Assert.assertEquals("round Foul hi d2", 2, ((BacaBallSummary) results.get(1)).roundFoulHi);
        Assert.assertEquals("round Foul hi d3", 5, ((BacaBallSummary) results.get(2)).roundFoulHi);

        Assert.assertEquals("round Foul lo d1", 0, ((BacaBallSummary) results.get(0)).roundFoulLo);
        Assert.assertEquals("round Foul lo d2", 0, ((BacaBallSummary) results.get(1)).roundFoulLo);
        Assert.assertEquals("round Foul lo d3", 3, ((BacaBallSummary) results.get(2)).roundFoulLo);

        Assert.assertEquals("round Miss tot d1", 4, ((BacaBallSummary) results.get(0)).roundMissTot);
        Assert.assertEquals("round Miss tot d2", 7, ((BacaBallSummary) results.get(1)).roundMissTot);
        Assert.assertEquals("round Miss tot d3", 54, ((BacaBallSummary) results.get(2)).roundMissTot);

        Assert.assertEquals("round Miss hi d1", 1, ((BacaBallSummary) results.get(0)).roundMissHi);
        Assert.assertEquals("round Miss hi d2", 2, ((BacaBallSummary) results.get(1)).roundMissHi);
        Assert.assertEquals("round Miss hi d3", 5, ((BacaBallSummary) results.get(2)).roundMissHi);

        Assert.assertEquals("round Miss lo d1", 0, ((BacaBallSummary) results.get(0)).roundMissLo);
        Assert.assertEquals("round Miss lo d2", 0, ((BacaBallSummary) results.get(1)).roundMissLo);
        Assert.assertEquals("round Miss lo d3", 0, ((BacaBallSummary) results.get(2)).roundMissLo);

        Assert.assertEquals("round Safety tot d1", 6, ((BacaBallSummary) results.get(0)).roundSafetyTot);
        Assert.assertEquals("round Safety tot d2", 12, ((BacaBallSummary) results.get(1)).roundSafetyTot);
        Assert.assertEquals("round Safety tot d3", 24, ((BacaBallSummary) results.get(2)).roundSafetyTot);

        Assert.assertEquals("round Safety hi d1", 1, ((BacaBallSummary) results.get(0)).roundSafetyHi);
        Assert.assertEquals("round Safety hi d2", 2, ((BacaBallSummary) results.get(1)).roundSafetyHi);
        Assert.assertEquals("round Safety hi d3", 14, ((BacaBallSummary) results.get(2)).roundSafetyHi);

        Assert.assertEquals("round Safety lo d1", 0, ((BacaBallSummary) results.get(0)).roundSafetyLo);
        Assert.assertEquals("round Safety lo d2", 0, ((BacaBallSummary) results.get(1)).roundSafetyLo);
        Assert.assertEquals("round Safety lo d3", 0, ((BacaBallSummary) results.get(2)).roundSafetyLo);
    }

    private void game01(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);

        final BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        // scratches, fouls, misses, safeties

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));

        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 100, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game02(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);

        final BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        // scratches, fouls, misses, safeties

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 1, 0, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 1, 1, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 1, 0, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 1));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 1, 1, 1));
        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 88, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game03(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);

        final BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 2, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 2, 2, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 2, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 1, 1, 2));
        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 79, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game04(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);

        final BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 1, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 0, 2));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 0, 14));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 1, 2));
        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 46, drill.score());
        DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
    }

    private void game05(final String overrideTime) throws Exception
    {
        setCurrentDateTime(overrideTime);

        final BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 5, 5, 5, 0));
        Assert.assertTrue("isComplete", drill.isComplete());
        Assert.assertEquals("score", 0, drill.score());
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
