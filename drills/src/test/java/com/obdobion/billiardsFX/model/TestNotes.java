package com.obdobion.billiardsFX.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill;
import com.obdobion.billiardsFX.model.drills.drdave.UpdateScoreFromInt;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_01;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.UpdateRound;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.BreakModifier;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateBreak;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.UpdateInning;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.UpdateScore;

/**
 * <p>
 * TestNotes class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestNotes
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * bacaballNote.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void bacaballNote() throws Exception
    {
        final String note = "I am great, and you are too!";

        BacaBall drill = (BacaBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("BacaBall")).get(0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateRound(drill, 1, 1, 1, 1, note));
        final SaveStoreFilter filter = DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);

        Assert.assertEquals("note after write to save store", note, drill.getNote());

        drill = (BacaBall) DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, filter, 1).get(0);
        Assert.assertEquals("note after read from save store", note, drill.getNote());
    }

    /**
     * <p>
     * bowlliardsNote.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void bowlliardsNote() throws Exception
    {
        final String note = "I am great, and you are too!";

        Bowlliards drill = (Bowlliards) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("Bowlliards")).get(0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateBreak(drill, 1, BreakModifier.SCRATCH));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10, note));

        Assert.assertEquals("note after update", note, drill.getNote());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));

        Assert.assertEquals("note after update with no note", note, drill.getNote());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateInning(drill, 10));
        final SaveStoreFilter filter = DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);

        Assert.assertEquals("note after write to save store", note, drill.getNote());

        drill = (Bowlliards) DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, filter, 1)
                .get(0);
        Assert.assertEquals("note after read from save store", note, drill.getNote());
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
        myDS.getDrillPackageManager().add(new VEPP_01());
    }

    /**
     * <p>
     * singleScoreDrillNote.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void singleScoreDrillNote() throws Exception
    {
        final String note = "I am great, and you are too!";

        SingleScoreDrill drill = (SingleScoreDrill) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ppcut1dl")).get(0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScoreFromInt(drill, 1, note));
        final SaveStoreFilter filter = DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);

        Assert.assertEquals("note after write to save store", note, drill.getNote());

        drill = (SingleScoreDrill) DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, filter, 1)
                .get(0);
        Assert.assertEquals("note after read from save store", note, drill.getNote());
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
     * threeBallNote.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void threeBallNote() throws Exception
    {
        final String note = "I am great, and you are too!";

        ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 1, 1, note));
        final SaveStoreFilter filter = DrillMaster.saveDrillToSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);

        Assert.assertEquals("note after write to save store", note, drill.getNote());

        drill = (ThreeBall) DrillMaster.createDrillFromSaveStore(JUnitDrillMasterImpl.J_UNIT_SESSION, filter, 1).get(0);
        Assert.assertEquals("note after read from save store", note, drill.getNote());
    }
}
