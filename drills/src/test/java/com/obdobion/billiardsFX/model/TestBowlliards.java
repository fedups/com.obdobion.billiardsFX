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
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BreakModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.InningModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateBreak;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateInning;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestBowlliards class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestBowlliards
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * bestCleanSheet.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void bestCleanSheet() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int f = 0; f < 10; f++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));
        }
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));

        Assert.assertEquals("score", 190, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
    }

    /**
     * <p>
     * countRemainingBalls.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countRemainingBalls() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 2, BreakModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill,
                6,
                InningModifier.SCRATCH,
                InningModifier.MISS,
                InningModifier.REMAINING_COUNT));
        Assert.assertEquals("f1a", 4, drill.score());
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 2, InningModifier.MISS, InningModifier.REMAINING_COUNT));
        Assert.assertEquals("f1b", 8, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 0, InningModifier.REMAINING_COUNT));
        Assert.assertEquals("f2", 18, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 6, InningModifier.SCRATCH, InningModifier.REMAINING_COUNT));
        Assert.assertEquals("f3a", 26, drill.score());
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 1, InningModifier.REMAINING_COUNT));
        Assert.assertEquals("f3b", 36, drill.score());

        Assert.assertFalse("complete", drill.isComplete());
    }

    /**
     * <p>
     * dutch200.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void dutch200() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int d = 0; d < 5; d++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        }
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));

        Assert.assertEquals("score", 200, drill.score());
        Assert.assertTrue("complete", drill.isComplete());

        final StringBuilder sb = new StringBuilder();
        drill.writeTo(sb);
    }

    /**
     * <p>
     * openGame.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void openGame() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int f = 0; f < 10; f++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0));
        }
        Assert.assertEquals("score", 90, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
    }

    /**
     * <p>
     * perfectGame.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void perfectGame() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int f = 0; f < 12; f++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        }
        Assert.assertEquals("score", 300, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
    }

    /**
     * <p>
     * playBowlliards.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playBowlliards() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");

        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 2, BreakModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 4, InningModifier.SCRATCH, InningModifier.MISS));
        Assert.assertEquals("f1a", 4, drill.score());
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.MISS));
        Assert.assertEquals("f1b", 8, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        Assert.assertEquals("f2", 18, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.SCRATCH));
        Assert.assertEquals("f3a", 26, drill.score());
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 5));
        Assert.assertEquals("f3b", 36, drill.score());

        Assert.assertFalse("complete", drill.isComplete());
    }

    /**
     * <p>
     * saveAndReadFromSaveStore.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void saveAndReadFromSaveStore() throws Exception
    {
        setCurrentDateTime("2010-08-18T08:01:02");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int f = 0; f < 10; f++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
        }
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0));

        final SaveStoreFilter filter = DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        final List<IDrill> drills = DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, filter,
                1);

        Assert.assertEquals("score", 109, ((Bowlliards) drills.get(0)).score());
        Assert.assertTrue("complete", ((Bowlliards) drills.get(0)).isComplete());
    }

    /**
     * <p>
     * saveAndReadFromTempStore.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void saveAndReadFromTempStore() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int f = 0; f < 10; f++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
        }
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0));

        DrillMaster.saveDrillToTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        drill = (Bowlliards) DrillMaster.createDrillFromTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION);

        Assert.assertEquals("score", 109, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
    }

    /**
     * <p>
     * scoreboard.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void scoreboard() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");

        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));

        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 2, BreakModifier.SCRATCH));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateInning(drill, 4, InningModifier.SCRATCH, InningModifier.MISS));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.MISS));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 4, InningModifier.SCRATCH));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 6));

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));

        Assert.assertEquals("frame 1 inning 1 pots", 4, drill.frame(0).inning(0).getPots());
        Assert.assertTrue("frame 1 inning 1 complete", drill.frame(0).inning(0).isComplete());
        Assert.assertTrue("frame 1 inning 1 miss", drill.frame(0).inning(0).isMiss());
        Assert.assertTrue("frame 1 inning 1 scratch", drill.frame(0).inning(0).isScratch());
        Assert.assertEquals("frame 1 inning 2 pots", 4, drill.frame(0).inning(1).getPots());
        Assert.assertTrue("frame 1 inning 1 complete", drill.frame(0).inning(1).isComplete());
        Assert.assertTrue("frame 1 inning 2 miss", drill.frame(0).inning(1).isMiss());
        Assert.assertFalse("frame 1 inning 2 scratch", drill.frame(0).inning(1).isScratch());
        Assert.assertTrue("frame 1 complete", drill.frame(0).isComplete());
        Assert.assertFalse("frame 1 spare", drill.frame(0).isSpare());
        Assert.assertFalse("frame 1 strike", drill.frame(0).isStrike());
        Assert.assertEquals("frame 1 score", 8, drill.score(0));

        Assert.assertEquals("frame 2 inning 1 pots", 10, drill.frame(1).inning(0).getPots());
        Assert.assertTrue("frame 2 inning 1 complete", drill.frame(1).inning(0).isComplete());
        Assert.assertFalse("frame 2 inning 1 miss", drill.frame(1).inning(0).isMiss());
        Assert.assertFalse("frame 2 inning 1 scratch", drill.frame(1).inning(0).isScratch());
        Assert.assertEquals("frame 2 inning 2 pots", 0, drill.frame(1).inning(1).getPots());
        Assert.assertTrue("frame 2 inning 1 complete", drill.frame(1).inning(1).isComplete());
        Assert.assertFalse("frame 2 inning 2 miss", drill.frame(1).inning(1).isMiss());
        Assert.assertFalse("frame 2 inning 2 scratch", drill.frame(1).inning(1).isScratch());
        Assert.assertTrue("frame 2 complete", drill.frame(1).isComplete());
        Assert.assertFalse("frame 2 spare", drill.frame(1).isSpare());
        Assert.assertTrue("frame 2 strike", drill.frame(1).isStrike());
        Assert.assertEquals("frame 2 score", 28, drill.score(1));

        Assert.assertEquals("frame 3 inning 1 pots", 4, drill.frame(2).inning(0).getPots());
        Assert.assertTrue("frame 3 inning 1 complete", drill.frame(2).inning(0).isComplete());
        Assert.assertFalse("frame 3 inning 1 miss", drill.frame(2).inning(0).isMiss());
        Assert.assertTrue("frame 3 inning 1 scratch", drill.frame(2).inning(0).isScratch());
        Assert.assertEquals("frame 3 inning 2 pots", 6, drill.frame(2).inning(1).getPots());
        Assert.assertTrue("frame 3 inning 1 complete", drill.frame(2).inning(1).isComplete());
        Assert.assertFalse("frame 3 inning 2 miss", drill.frame(2).inning(1).isMiss());
        Assert.assertFalse("frame 3 inning 2 scratch", drill.frame(2).inning(1).isScratch());
        Assert.assertTrue("frame 3 complete", drill.frame(2).isComplete());
        Assert.assertTrue("frame 3 spare", drill.frame(2).isSpare());
        Assert.assertFalse("frame 3 strike", drill.frame(2).isStrike());
        Assert.assertEquals("frame 3 score", 39, drill.score(2));

        Assert.assertEquals("frame 4 inning 1 pots", 1, drill.frame(3).inning(0).getPots());
        Assert.assertTrue("frame 4 inning 1 complete", drill.frame(3).inning(0).isComplete());
        Assert.assertFalse("frame 4 inning 1 miss", drill.frame(3).inning(0).isMiss());
        Assert.assertFalse("frame 4 inning 1 scratch", drill.frame(3).inning(0).isScratch());
        Assert.assertEquals("frame 4 inning 2 pots", 0, drill.frame(3).inning(1).getPots());
        Assert.assertFalse("frame 4 inning 1 complete", drill.frame(3).inning(1).isComplete());
        Assert.assertFalse("frame 4 inning 2 miss", drill.frame(3).inning(1).isMiss());
        Assert.assertFalse("frame 4 inning 2 scratch", drill.frame(3).inning(1).isScratch());
        Assert.assertFalse("frame 4 complete", drill.frame(3).isComplete());
        Assert.assertFalse("frame 4 spare", drill.frame(3).isSpare());
        Assert.assertFalse("frame 4 strike", drill.frame(3).isStrike());
        Assert.assertEquals("frame 4 score", 40, drill.score(3));

        Assert.assertFalse("frame 5 inning 1 complete", drill.frame(4).inning(0).isComplete());
        Assert.assertFalse("frame 5 inning 2 complete", drill.frame(4).inning(0).isComplete());
        Assert.assertFalse("frame 5 complete", drill.frame(4).isComplete());
        Assert.assertFalse("drill complete", drill.isComplete());
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

    /**
     * <p>
     * worstCleanSheet.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void worstCleanSheet() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0));

        for (int f = 0; f < 10; f++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
        }
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 0));

        Assert.assertEquals("score", 109, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
    }

    /**
     * <p>
     * writeRead.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void writeRead() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 0, BreakModifier.SCRATCH));

        for (int d = 0; d < 5; d++)
        {
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));
            DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        }
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 9));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 1));

        final StringBuilder sb = new StringBuilder();
        drill.writeTo(sb);
        drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.readFrom(sb.toString());

        Assert.assertEquals("score", 200, drill.score());
        Assert.assertTrue("complete", drill.isComplete());
    }
}
