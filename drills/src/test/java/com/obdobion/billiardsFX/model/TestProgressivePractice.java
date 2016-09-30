package com.obdobion.billiardsFX.model;

import java.io.IOException;
import java.text.ParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill;
import com.obdobion.billiardsFX.model.drills.drdave.UpdateScoreFromFloat;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_01;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_02;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * TestProgressivePractice class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestProgressivePractice
{
    static private void playPP(final String drillID)
            throws ParseException, InstantiationException, IOException, IllegalAccessException
    {
        SingleScoreDrill drill = (SingleScoreDrill) DrillMaster.createDrill(JUnitDrillMasterImpl.J_UNIT_SESSION,
                DrillMaster.availableDrills(new DrillLookupFilter(drillID)).get(0));

        Assert.assertEquals("score", 0, drill.score());
        Assert.assertFalse("complete", drill.isComplete());

        DrillMaster.execute(JUnitDrillMasterImpl.J_UNIT_SESSION, new UpdateScoreFromFloat(drill, 5.25F));

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 525, drill.score());

        DrillMaster.saveDrillToTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION, drill);
        drill = (SingleScoreDrill) DrillMaster.createDrillFromTempStore(JUnitDrillMasterImpl.J_UNIT_SESSION);

        Assert.assertTrue("complete", drill.isComplete());
        Assert.assertEquals("score", 525, drill.score());
    }

    JUnitDrillMasterImpl myDS;
    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * playCut.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playCut() throws Exception
    {
        playPP("ppcut1dl");
        playPP("ppcut2dl");
        playPP("ppcut3dl");
        playPP("ppcut4dl");
        playPP("ppcut5dl");
        playPP("ppcut1dr");
        playPP("ppcut2dr");
        playPP("ppcut3dr");
        playPP("ppcut4dr");
        playPP("ppcut5dr");
    }

    /**
     * <p>
     * playDraw.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playDraw() throws Exception
    {
        playPP("ppdraw1c");
        playPP("ppdraw2c");
        playPP("ppdraw3c");
        playPP("ppdraw4c");
        playPP("ppdraw5c");
    }

    /**
     * <p>
     * playFollow.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playFollow() throws Exception
    {
        playPP("ppfollow1b");
        playPP("ppfollow2b");
        playPP("ppfollow3b");
        playPP("ppfollow4b");
        playPP("ppfollow5b");
    }

    /**
     * <p>
     * playRailCut.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playRailCut() throws Exception
    {
        playPP("pprailcutl");
        playPP("pprailcutr");
    }

    /**
     * <p>
     * playStop.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playStop() throws Exception
    {
        playPP("ppstop1a");
        playPP("ppstop2a");
        playPP("ppstop3a");
    }

    /**
     * <p>
     * playStun.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void playStun() throws Exception
    {
        playPP("ppstun4a");
        playPP("ppstun5a");
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
        myDS.getDrillPackageManager().add(new VEPP_01());
        myDS.getDrillPackageManager().add(new VEPP_02());
        setCurrentDateTime("2015-08-20T08:01:00");
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
