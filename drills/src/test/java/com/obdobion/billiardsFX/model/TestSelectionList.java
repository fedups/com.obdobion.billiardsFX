package com.obdobion.billiardsFX.model;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.ConfigParm;
import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillLookupReference;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.Tag;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_01;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_02;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;

/**
 * <p>
 * TestSelectionList class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestSelectionList
{
    @SuppressWarnings("unused")
    static private void generateAssertCodeToSysout(final List<Tag> tags)
    {
        for (final Tag tag : tags)
            System.out.println("Assert.assertEquals(\"tag\" + x, Tag.findOrCreate(\"" + tag.getTagID()
                    + "\"), tags.get(x++));");
    }

    JUnitDrillMasterImpl myDS;

    IDrillMaster         previousDrillSergeant;

    /**
     * <p>
     * addPackagesAndVerifyDrillCount.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addPackagesAndVerifyDrillCount() throws Exception
    {
        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();

        myDS.getDrillPackageManager().add(new SoloGames());
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of drills with solo games", 3, drillRefs.size());

        myDS.getDrillPackageManager().add(new VEPP_01());
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of drills with vepp 01", 41, drillRefs.size());

        myDS.getDrillPackageManager().add(new VEPP_02());
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of drills with vepp 02", 72, drillRefs.size());
    }

    /**
     * <p>
     * availableFilterTags.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void availableFilterTags() throws Exception
    {
        myDS.getDrillPackageManager().add(new VEPP_01());

        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();

        drillRefs = DrillMaster.availableDrills(filter);
        List<Tag> tags = Tag.uniqueTagsInDrills(drillRefs);

        // generateAssertCodeToSysout(tags);

        int x = 0;
        Assert.assertEquals("all available tags", 15, tags.size());
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_01"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("AIMING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CUT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CONTROL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("DRAW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("FOLLOW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("LAG"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SPEED"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STOP"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STUN"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CHALLENGE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("LEFT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RAIL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RIGHT"), tags.get(x++));

        filter.setIncludeTags(Tag.findOrCreate("CUT"));
        drillRefs = DrillMaster.availableDrills(filter);
        tags = Tag.uniqueTagsInDrills(drillRefs);

        // generateAssertCodeToSysout(tags);

        x = 0;
        Assert.assertEquals("available tags for CUT", 7, tags.size());
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_01"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("AIMING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CUT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("LEFT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RAIL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RIGHT"), tags.get(x++));
    }

    /**
     * <p>
     * backButton.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void backButton() throws Exception
    {
        myDS.getDrillPackageManager().add(new SoloGames());
        myDS.getDrillPackageManager().add(new VEPP_01());
        myDS.getDrillPackageManager().add(new VEPP_02());

        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();

        filter.setIncludeTags(Tag.findOrCreate("CUT"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of cut drills", 14, drillRefs.size());

        filter.setExcludeTags(Tag.findOrCreate("LEFT"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of left side cut drills", 8, drillRefs.size());

        filter.back();
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of cut drills", 14, drillRefs.size());

        filter.back();
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of all drills", 72, drillRefs.size());
        /*
         * One last time to make sure it does blow up.
         */
        filter.back();
    }

    /**
     * <p>
     * excludeFilters.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void excludeFilters() throws Exception
    {
        myDS.getDrillPackageManager().add(new SoloGames());
        myDS.getDrillPackageManager().add(new VEPP_01());
        myDS.getDrillPackageManager().add(new VEPP_02());

        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();

        filter.setIncludeTags(Tag.findOrCreate("CUT"));
        filter.setExcludeTags(Tag.findOrCreate("LEFT"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of left side cut drills", 8, drillRefs.size());

        filter.reset();
        filter.setExcludeTags(Tag.findOrCreate("RAIL"));
        filter.setIncludeTags(Tag.findOrCreate("LEFT"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of left side non-railcut drills", 5, drillRefs.size());
    }

    /**
     * <p>
     * includeFilters.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void includeFilters() throws Exception
    {
        myDS.getDrillPackageManager().add(new SoloGames());
        myDS.getDrillPackageManager().add(new VEPP_01());
        myDS.getDrillPackageManager().add(new VEPP_02());

        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("CUT"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of cut drills", 14, drillRefs.size());

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("CUT"), Tag.findOrCreate("VEPP_01"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of cut drills in VEPP 01", 12, drillRefs.size());

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("CUT"), Tag.findOrCreate("VEPP_01"), Tag.findOrCreate("RIGHT"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of cut drills in VEPP 01 right", 6, drillRefs.size());

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("CUT"), Tag.findOrCreate("VEPP_01"), Tag.findOrCreate("RAIL"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of rail cut drills in VEPP 01", 2, drillRefs.size());

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("CUT"), Tag.findOrCreate("VEPP_02"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of cut drills in VEPP 02", 2, drillRefs.size());
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
     * userTags.
     *
     * See the commented code in DrillFactory. I have not yet dealt with how to
     * handle dbUser tags in a multi-dbUser system.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    /*-
    @Test
    */
    public void userTags() throws Exception
    {
        myDS.getDrillPackageManager().add(new SoloGames());
        myDS.getDrillPackageManager().add(new VEPP_01());

        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();

        DrillMaster.setConfigParm(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new ConfigParm("vepp_01/ppcut1dl/tags", "FAVORITE"));
        DrillMaster.setConfigParm(JUnitDrillMasterImpl.J_UNIT_SESSION,
                new ConfigParm("vepp_01/ppcut3dl/tags", "FAVORITE"));

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("FAVORITE"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of fav drills", 2, drillRefs.size());

        DrillMaster.setConfigParm(JUnitDrillMasterImpl.J_UNIT_SESSION, new ConfigParm("vepp_01/ppcut1dl/tags", null));

        filter.reset();
        filter.setIncludeTags(Tag.findOrCreate("FAVORITE"));
        drillRefs = DrillMaster.availableDrills(filter);
        Assert.assertEquals("number of fav drills", 1, drillRefs.size());

    }

}
