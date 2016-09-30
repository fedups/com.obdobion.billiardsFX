package com.obdobion.billiardsFX.model;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillLookupReference;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.IDrillMaster;
import com.obdobion.billiardsFX.model.Tag;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_01;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_02;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_03;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_04;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_05;

/**
 * <p>
 * TestAllVEPPDisks class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestAllVEPPDisks
{
    @SuppressWarnings("unused")
    static private void generateAssertCodeToSysout(final List<Tag> tags)
    {
        for (final Tag tag : tags)
            System.out.println("Assert.assertEquals(\"tag\" + x, Tag.findOrCreate(\"" + tag.getTagID()
                    + "\"), tags.get(x++));");
    }

    IDrillMaster previousDrillMaster;

    /**
     * <p>
     * countAllDrills.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countAllDrills() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(new DrillLookupFilter());
        Assert.assertEquals("count", 139, drills.size());

    }

    /**
     * <p>
     * countVEPP01Drills.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countVEPP01Drills() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_01")));
        Assert.assertEquals("count", 38, drills.size());
    }

    /**
     * <p>
     * countVEPP02Drills.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countVEPP02Drills() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_02")));
        Assert.assertEquals("count", 31, drills.size());
    }

    /**
     * <p>
     * countVEPP03Drills.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countVEPP03Drills() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_03")));
        Assert.assertEquals("count", 32, drills.size());
    }

    /**
     * <p>
     * countVEPP04Drills.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countVEPP04Drills() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_04")));
        Assert.assertEquals("count", 26, drills.size());
    }

    /**
     * <p>
     * countVEPP05Drills.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void countVEPP05Drills() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_05")));
        Assert.assertEquals("count", 12, drills.size());
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
        previousDrillMaster = DrillMaster.setInstance(new JUnitDrillMasterImpl());
        DrillMaster.addPackage(new VEPP_01());
        DrillMaster.addPackage(new VEPP_02());
        DrillMaster.addPackage(new VEPP_03());
        DrillMaster.addPackage(new VEPP_04());
        DrillMaster.addPackage(new VEPP_05());
    }

    /**
     * <p>
     * tagsVEPP01.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void tagsVEPP01() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_01")));
        final List<Tag> tags = Tag.uniqueTagsInDrills(drills);
        Assert.assertEquals("count", 15, tags.size());

        // generateAssertCodeToSysout(tags);

        int x = 0;
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
    }

    /**
     * <p>
     * tagsVEPP02.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void tagsVEPP02() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_02")));
        final List<Tag> tags = Tag.uniqueTagsInDrills(drills);
        Assert.assertEquals("count", 12, tags.size());

        // generateAssertCodeToSysout(tags);

        int x = 0;
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_02"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("AIMING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CUT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CONTROL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("DRAW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("ENGLISH"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("FOLLOW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SPEED"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STUN"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CHALLENGE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RAIL"), tags.get(x++));
    }

    /**
     * <p>
     * tagsVEPP03.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void tagsVEPP03() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_03")));
        final List<Tag> tags = Tag.uniqueTagsInDrills(drills);
        Assert.assertEquals("count", 7, tags.size());

        // generateAssertCodeToSysout(tags);

        int x = 0;
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_03"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("PATTERN"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SAFETY"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STRATEGY"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("8BALL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("9BALL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
    }

    /**
     * <p>
     * tagsVEPP04.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void tagsVEPP04() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_04")));
        final List<Tag> tags = Tag.uniqueTagsInDrills(drills);
        Assert.assertEquals("count", 12, tags.size());

        // generateAssertCodeToSysout(tags);

        int x = 0;
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_04"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("AIMING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("BANK"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("JACKEDUP"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("KICK"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CONTROL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("DRAW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("JUMP"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("MASSE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CHALLENGE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RAIL"), tags.get(x++));
    }

    /**
     * <p>
     * tagsVEPP05.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void tagsVEPP05() throws Exception
    {
        final List<DrillLookupReference> drills = DrillMaster.availableDrills(
                new DrillLookupFilter().setIncludeTags(Tag.findOrCreate("VEPP_05")));
        final List<Tag> tags = Tag.uniqueTagsInDrills(drills);
        Assert.assertEquals("count", 15, tags.size());

        // generateAssertCodeToSysout(tags);

        int x = 0;
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_05"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("AIMING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("BREAK"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CAROM"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("COMBINATION"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CUT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("ENGLISH"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STUN"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SAFETY"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STRATEGY"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("9BALL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CHALLENGE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RATING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RAIL"), tags.get(x++));
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
        DrillMaster.setInstance(previousDrillMaster);
    }
}
