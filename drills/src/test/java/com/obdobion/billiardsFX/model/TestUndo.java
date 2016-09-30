package com.obdobion.billiardsFX.model;

import java.text.ParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.drills.IDrillCommand;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BreakModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.InningModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateBreak;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateInning;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestUndo class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestUndo
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

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
     * undoBreak.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void undoBreak() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        final IDrillCommand cmd = DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateBreak(drill, 1, BreakModifier.SCRATCH));
        Assert.assertEquals("potsOnBreak before undo", 1, drill.potsOnBreak);
        Assert.assertTrue("scratchOnBreak before undo", drill.scratchOnBreak);
        DrillMaster.undo(JUnitDrillMasterImpl.J_UNIT_SESSION, cmd);
        Assert.assertEquals("potsOnBreak after undo", 0, drill.potsOnBreak);
        Assert.assertFalse("scratchOnBreak after undo", drill.scratchOnBreak);
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, cmd);
    }

    /**
     * <p>
     * undoInning.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void undoInning() throws Exception
    {
        setCurrentDateTime("2015-08-18T08:01:01");
        final Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        drill.addObserver(myDS);
        drill.start();

        IDrillCommand cmd = DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new UpdateBreak(drill, 1, BreakModifier.SCRATCH));

        Assert.assertEquals("currentFrame before cmd", 0, drill.currentFrame);
        Assert.assertEquals("currentInning before cmd", 0, drill.currentInning);
        Assert.assertEquals("score before cmd", 0, drill.score());
        cmd = DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill,
                3,
                InningModifier.MISS,
                InningModifier.SCRATCH,
                InningModifier.REMAINING_COUNT));
        Assert.assertEquals("currentFrame after cmd", 0, drill.currentFrame);
        Assert.assertEquals("currentInning after cmd", 1, drill.currentInning);
        Assert.assertEquals("score after cmd", 7, drill.score());

        Assert.assertTrue("frame 1 inning 1 complete", drill.frame(0).inning(0).isComplete());
        Assert.assertFalse("frame 1 inning 2 complete", drill.frame(0).inning(1).isComplete());
        Assert.assertFalse("frame 1 complete", drill.frame(0).isComplete());

        DrillMaster.undo(JUnitDrillMasterImpl.J_UNIT_SESSION, cmd);
        Assert.assertEquals("currentFrame after undo", 0, drill.currentFrame);
        Assert.assertEquals("currentInning after undo", 0, drill.currentInning);
        Assert.assertEquals("score after undo", 0, drill.score());
        Assert.assertFalse("frame 1 inning 1 after undo complete", drill.frame(0).inning(0).isComplete());
        Assert.assertFalse("frame 1 inning 2 after undo complete", drill.frame(0).inning(1).isComplete());
        Assert.assertFalse("frame 1 after undo complete", drill.frame(0).isComplete());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, cmd);
        Assert.assertEquals("currentFrame after cmd", 0, drill.currentFrame);
        Assert.assertEquals("currentInning after cmd", 1, drill.currentInning);
        Assert.assertEquals("score after cmd", 7, drill.score());
        cmd = DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill,
                1,
                InningModifier.MISS,
                InningModifier.SCRATCH,
                InningModifier.REMAINING_COUNT));
        Assert.assertEquals("currentFrame after cmd", 1, drill.currentFrame);
        Assert.assertEquals("currentInning after cmd", 0, drill.currentInning);
        Assert.assertEquals("score after cmd", 9, drill.score());

        DrillMaster.undo(JUnitDrillMasterImpl.J_UNIT_SESSION, cmd);
        Assert.assertEquals("currentFrame after cmd", 0, drill.currentFrame);
        Assert.assertEquals("currentInning after cmd", 1, drill.currentInning);
        Assert.assertEquals("score after cmd", 7, drill.score());

        Assert.assertTrue("frame 1 inning 1 after undo complete", drill.frame(0).inning(0).isComplete());
        Assert.assertFalse("frame 1 inning 2 after undo complete", drill.frame(0).inning(1).isComplete());
        Assert.assertFalse("frame 1 after undo complete", drill.frame(0).isComplete());

        Assert.assertFalse("frame 2 inning 1 after undo complete", drill.frame(1).inning(0).isComplete());
        Assert.assertFalse("frame 2 inning 2 after undo complete", drill.frame(1).inning(1).isComplete());
        Assert.assertFalse("frame 2 after undo complete", drill.frame(1).isComplete());
    }
}
