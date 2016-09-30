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
import com.obdobion.billiardsFX.model.Tag.TagGroup;
import com.obdobion.billiardsFX.model.Tag.TagUsage;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_01;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_02;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_03;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_04;
import com.obdobion.billiardsFX.model.drills.drdave.VEPP_05;
import com.obdobion.billiardsFX.model.drills.sologames.SoloGames;

/**
 * <p>
 * TestTags class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TestTags
{
    @SuppressWarnings("unused")
    static private void generateAssertCodeToSysout(final List<Tag> tags)
    {
        for (final Tag tag : tags)
            System.out.println("Assert.assertEquals(\"tag\" + x, Tag.findOrCreate(\"" + tag.getTagID()
                    + "\"), tags.get(x++));");
    }

    @SuppressWarnings("unused")
    static private void generateAssertUsageCodeToSysout(final List<TagUsage> tags)
    {
        for (final TagUsage tag : tags)
        {
            System.out.println("Assert.assertEquals(\"tagUsage id \" + x, Tag.findOrCreate(\"" + tag.tag.getTagID()
                    + "\"), tags.get(x).tag);");
            System.out.println("Assert.assertEquals(\"tagUsage cnt \" + x, " + tag.referencedCount
                    + ", tags.get(x++).referencedCount);");
        }
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
        myDS.getDrillPackageManager().add(new SoloGames());
        myDS.getDrillPackageManager().add(new VEPP_01());
        myDS.getDrillPackageManager().add(new VEPP_02());
        myDS.getDrillPackageManager().add(new VEPP_03());
        myDS.getDrillPackageManager().add(new VEPP_04());
        myDS.getDrillPackageManager().add(new VEPP_05());
    }

    /**
     * <p>
     * tagReferenceCountForGroup.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void tagReferenceCountForGroup() throws Exception
    {
        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();
        drillRefs = DrillMaster.availableDrills(filter);
        int x;

        List<TagUsage> tags = Tag.usageForGroup(TagGroup.Package, drillRefs);
        x = 0;
        // generateAssertUsageCodeToSysout(tags);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("SOLO_GAMES"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 3, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_01"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 38, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_02"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 31, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_03"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 32, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_04"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 26, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_05"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 12, tags.get(x++).referencedCount);

        filter.setIncludeTags(Tag.findOrCreate("DRAW"));
        drillRefs = DrillMaster.availableDrills(filter);
        tags = Tag.usageForGroup(TagGroup.Package, drillRefs);
        x = 0;
        // generateAssertUsageCodeToSysout(tags);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("SOLO_GAMES"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 0, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_01"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 12, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_02"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 2, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_03"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 0, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_04"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 1, tags.get(x++).referencedCount);
        Assert.assertEquals("tagUsage id " + x, Tag.findOrCreate("VEPP_05"), tags.get(x).tag);
        Assert.assertEquals("tagUsage cnt " + x, 0, tags.get(x++).referencedCount);
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
     * uniqueTags.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void uniqueTags() throws Exception
    {
        List<DrillLookupReference> drillRefs;
        final DrillLookupFilter filter = new DrillLookupFilter();
        drillRefs = DrillMaster.availableDrills(filter);
        final List<Tag> tags = Tag.uniqueTagsInDrills(drillRefs);

        // generateAssertCodeToSysout(tags);

        int x = 0;
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SOLO_GAMES"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_01"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_02"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_03"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_04"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("VEPP_05"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("AIMING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("BANK"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("BREAK"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CAROM"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("COMBINATION"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CUT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("JACKEDUP"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("KICK"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CONTROL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("DRAW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("ENGLISH"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("FOLLOW"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("JUMP"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("LAG"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("MASSE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SPEED"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STOP"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STUN"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("PATTERN"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SAFETY"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("STRATEGY"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("8BALL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("9BALL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("CHALLENGE"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("GAME"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RATING"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("ROTATION"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("SKILL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("LEFT"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RAIL"), tags.get(x++));
        Assert.assertEquals("tag" + x, Tag.findOrCreate("RIGHT"), tags.get(x++));
    }
}
