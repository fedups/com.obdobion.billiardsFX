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
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.UpdateScore;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestThreeBall class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestThreeBall
{
    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * playThreeBall.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playThreeBall() throws Exception
    {
        setCurrentDateTime("2015-08-19_08:01:01");
        ThreeBall drill = (ThreeBall) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter("ThreeBall")).get(0));
        drill.addObserver(myDS);
        drill.start();

        Assert.assertEquals("score", 0, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 4, 0));
        Assert.assertEquals("score", 4, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 4, 0));

        Assert.assertFalse("complete", drill.isComplete());
        Assert.assertEquals("score", 8, drill.score());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 10, 4));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 3, 0));
        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScore(drill, 5, 1));

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 31, drill.score());

        DrillMaster.saveDrillToTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        drill = (ThreeBall) DrillMaster.createDrillFromTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION);

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 31, drill.score());

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
