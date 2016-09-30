package com.obdobion.billiardsFX.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.drills.AbstractDrillSummary;
import com.obdobion.billiardsFX.model.drills.IDrillSummary;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;

/**
 * <p>
 * TestXAxisLabels class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestXAxisLabels
{
    static public class JUnitSummary extends AbstractDrillSummary
    {
        // nothing needed
    }

    JUnitDrillMasterImpl myDS;

    IDrillMaster         previousDrillSergeant;

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
     * testMonthNameAtStart.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void testMonthNameAtStart() throws Exception
    {
        final List<IDrillSummary> results = new ArrayList<>();

        JUnitSummary r = null;

        r = new JUnitSummary();
        r.setMonth(10);
        r.setDay(10);
        r.setYear(2015);
        results.add(r);

        r = new JUnitSummary();
        r.setMonth(10);
        r.setDay(11);
        r.setYear(2015);
        results.add(r);

        final ArrayList<String> labels = DrillMaster.xLabelsFor(results);

        Assert.assertEquals("month103char10", labels.get(0));
        Assert.assertEquals("11", labels.get(1));
    }

    /**
     * <p>
     * testMonthWhenChanges.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void testMonthWhenChanges() throws Exception
    {
        final List<IDrillSummary> results = new ArrayList<>();

        JUnitSummary r = null;

        r = new JUnitSummary();
        r.setMonth(10);
        r.setDay(10);
        r.setYear(2015);
        results.add(r);

        r = new JUnitSummary();
        r.setMonth(10);
        r.setDay(11);
        r.setYear(2015);
        results.add(r);

        r = new JUnitSummary();
        r.setMonth(11);
        r.setDay(1);
        r.setYear(2015);
        results.add(r);

        final ArrayList<String> labels = DrillMaster.xLabelsFor(results);

        Assert.assertEquals("month103char10", labels.get(0));
        Assert.assertEquals("11", labels.get(1));
        Assert.assertEquals("month113char1", labels.get(2));
    }
}
