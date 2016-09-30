package com.obdobion.billiardsFX.model;

import java.text.ParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.UpdateRound;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestBacaBall class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestBacaBall
{
    JUnitDrillMasterImpl       myDS;
    IDrillMaster               previousDrillSergeant;

    /**
     * <p>
     * playBeginner.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playBeginner() throws Exception
    {
        setCurrentDateTime("2015-08-20T08:11:00");

        BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        drill.addObserver(myDS);
        drill.start();

        Assert.assertEquals("score", 0, drill.score());

        // scratches, fouls, misses, safeties

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 3, 3, 3, 0));
        Assert.assertEquals("score", 1, drill.score());
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 4, 2, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 2, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 3, 2, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 1, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 2, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 4, 3, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 3, 3, 4, 0));

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 27, drill.score());

        DrillMaster.saveDrillToTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        drill = (BacaBall) DrillMaster.createDrillFromTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION);

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 27, drill.score());

    }

    /**
     * <p>
     * playExpert.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playExpert() throws Exception
    {
        setCurrentDateTime("2015-08-20_08:01:00");
        BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        drill.addObserver(myDS);
        drill.start();

        Assert.assertEquals("score", 0, drill.score());

        // scratches, fouls, misses, safeties

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        Assert.assertEquals("score", 10, drill.score());
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 0, 0, 0, 0));

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 100, drill.score());

        DrillMaster.saveDrillToTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        drill = (BacaBall) DrillMaster.createDrillFromTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION);

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 100, drill.score());

    }

    /**
     * <p>
     * scoreBoard.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void scoreBoard() throws Exception
    {
        setCurrentDateTime("2015-08-20T08:11:00");

        final BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("bacaball")).get(0));

        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 3, 3, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 4, 2, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 2, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 3, 2, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 3, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 1, 1, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 2, 2, 2, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 4, 3, 3, 0));

        // scratches, fouls, misses, safeties

        Assert.assertEquals("round 1 scratches", 3, drill.round(0).getScratches());
        Assert.assertEquals("round 1 fouls", 3, drill.round(0).getFouls());
        Assert.assertEquals("round 1 misses", 3, drill.round(0).getMisses());
        Assert.assertEquals("round 1 safeties", 0, drill.round(0).getSafeties());
        Assert.assertTrue("round 1 complete", drill.round(0).isComplete());
        Assert.assertEquals("round 1 score", 1, drill.score(0));

        Assert.assertEquals("round 2 scratches", 4, drill.round(1).getScratches());
        Assert.assertEquals("round 2 fouls", 2, drill.round(1).getFouls());
        Assert.assertEquals("round 2 misses", 3, drill.round(1).getMisses());
        Assert.assertEquals("round 2 safeties", 0, drill.round(1).getSafeties());
        Assert.assertTrue("round 2 complete", drill.round(1).isComplete());
        Assert.assertEquals("round 2 score", 2, drill.score(1));

        Assert.assertEquals("round 9 scratches", 4, drill.round(8).getScratches());
        Assert.assertEquals("round 9 fouls", 3, drill.round(8).getFouls());
        Assert.assertEquals("round 9 misses", 3, drill.round(8).getMisses());
        Assert.assertEquals("round 9 safeties", 0, drill.round(8).getSafeties());
        Assert.assertTrue("round 9 complete", drill.round(8).isComplete());
        Assert.assertEquals("round 9 score", 27, drill.score(8));

        Assert.assertFalse("round 10 complete", drill.round(9).isComplete());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 3, 3, 1, 0));

        Assert.assertEquals("round 10 scratches", 3, drill.round(9).getScratches());
        Assert.assertEquals("round 10 fouls", 3, drill.round(9).getFouls());
        Assert.assertEquals("round 10 misses", 1, drill.round(9).getMisses());
        Assert.assertEquals("round 10 safeties", 0, drill.round(9).getSafeties());
        Assert.assertTrue("round 10 complete", drill.round(9).isComplete());
        Assert.assertEquals("round 10 score", 30, drill.score());

        Assert.assertEquals("score", 30, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
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
        myDS.addObserver(myDS);
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
