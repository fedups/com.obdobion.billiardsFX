package com.obdobion.billiardsFX.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Tag class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class Tag
{
    static final public class SortByGroup implements Comparator<Tag>
    {
        @Override
        public int compare(final Tag o1, final Tag o2)
        {
            final int g = o1.getGroup().sortKey - o2.getGroup().sortKey();
            if (g != 0)
                return g;
            return o1.getTagID().compareTo(o2.getTagID());
        }
    }

    public static enum TagGroup
    {
        Activity(5),
        Aiming(2),
        CueBallControl(3),
        Misc(6),
        Package(1),
        Strategy(4);

        final int sortKey;

        private TagGroup(final int p_sortOrder)
        {
            sortKey = p_sortOrder;
        }

        public String getDisplayNameRef()
        {
            return "tagGroup_" + name() + "_name";
        }

        public int sortKey()
        {
            return sortKey;
        }
    }

    public static class TagUsage
    {

        static final public class SortByGroup implements Comparator<TagUsage>
        {
            @Override
            public int compare(final TagUsage o1, final TagUsage o2)
            {
                final int g = o1.tag.getGroup().sortKey - o2.tag.getGroup().sortKey();
                if (g != 0)
                    return g;
                return o1.tag.getTagID().compareTo(o2.tag.getTagID());
            }
        }

        public int referencedCount;
        public Tag tag;

        public TagUsage(final Tag p_tag)
        {
            tag = p_tag;
        }
    }

    private static List<Tag> KnownTags = new ArrayList<>();

    final static Logger      logger    = LoggerFactory.getLogger(Tag.class);

    /**
     * <p>
     * findOrCreate.
     * </p>
     *
     * @param tagID a {@link java.lang.String} object.
     * @return a {@link com.obdobion.billiardsFX.model.Tag} object.
     */
    public static Tag findOrCreate(final String tagID)
    {
        for (final Tag tag : KnownTags)
        {
            if (tag.getTagID().equalsIgnoreCase(tagID))
                return tag;
        }
        final Tag tag = new Tag(tagID);
        KnownTags.add(tag);

        tag.assignGroup();

        logger.trace("add {}", tagID);
        return tag;
    }

    /**
     * <p>
     * uniqueTagsInDrills.
     * </p>
     *
     * @param drillRefs a {@link java.util.List} object.
     * @return a {@link java.util.List} object.
     */
    static public List<Tag> uniqueTagsInDrills(final List<DrillLookupReference> drillRefs)
    {
        final List<Tag> tags = new ArrayList<>();
        for (final DrillLookupReference drillRef : drillRefs)
        {
            for (final Tag tag : drillRef.tags)
            {
                if (!tags.contains(tag))
                    tags.add(tag);
            }
        }
        Collections.sort(tags, new Tag.SortByGroup());
        return tags;
    }

    /**
     * <p>
     * usageForGroup.
     * </p>
     *
     * @param group a {@link com.obdobion.billiardsFX.model.Tag.TagGroup} object.
     * @param drillRefs a {@link java.util.List} object.
     * @return a {@link java.util.List} object.
     */
    static public List<TagUsage> usageForGroup(final TagGroup group, final List<DrillLookupReference> drillRefs)
    {
        final List<TagUsage> tagUsages = new ArrayList<>();

        for (final Tag tag : KnownTags)
        {
            if (group != tag.getGroup())
                continue;

            final TagUsage usage = new TagUsage(tag);
            tagUsages.add(usage);
            for (final DrillLookupReference drillRef : drillRefs)
            {
                for (final Tag drillTag : drillRef.tags)
                {
                    if (usage.tag == drillTag)
                        usage.referencedCount++;
                }
            }
        }
        Collections.sort(tagUsages, new TagUsage.SortByGroup());
        return tagUsages;
    }

    final private String displayNameRef;
    private TagGroup     group;
    final private String tagID;

    /**
     * <p>
     * Constructor for Tag.
     * </p>
     *
     * @param p_tagID a {@link java.lang.String} object.
     */
    public Tag(final String p_tagID)
    {
        tagID = p_tagID;
        displayNameRef = "tag_" + p_tagID + "_name";
    }

    /**
     * This method has the possible tag names hard coded to belong in predefined
     * groupings. New tags will be placed in the MISC group until they are coded
     * for.
     */
    void assignGroup()
    {
        switch (getTagID())
        {
            case "SOLO_GAMES":
                setGroup(TagGroup.Package);
                break;
            case "VEPP_01":
                setGroup(TagGroup.Package);
                break;
            case "VEPP_02":
                setGroup(TagGroup.Package);
                break;
            case "VEPP_03":
                setGroup(TagGroup.Package);
                break;
            case "VEPP_04":
                setGroup(TagGroup.Package);
                break;
            case "VEPP_05":
                setGroup(TagGroup.Package);
                break;

            case "AIMING":
                setGroup(TagGroup.Aiming);
                break;
            case "BREAK":
                setGroup(TagGroup.Aiming);
                break;
            case "CUT":
                setGroup(TagGroup.Aiming);
                break;
            case "BANK":
                setGroup(TagGroup.Aiming);
                break;
            case "CAROM":
                setGroup(TagGroup.Aiming);
                break;
            case "COMBINATION":
                setGroup(TagGroup.Aiming);
                break;
            case "KICK":
                setGroup(TagGroup.Aiming);
                break;
            case "JACKEDUP":
                setGroup(TagGroup.Aiming);
                break;

            case "LAG":
                setGroup(TagGroup.CueBallControl);
                break;
            case "SPEED":
                setGroup(TagGroup.CueBallControl);
                break;
            case "STOP":
                setGroup(TagGroup.CueBallControl);
                break;
            case "STUN":
                setGroup(TagGroup.CueBallControl);
                break;
            case "CONTROL":
                setGroup(TagGroup.CueBallControl);
                break;
            case "DRAW":
                setGroup(TagGroup.CueBallControl);
                break;
            case "ENGLISH":
                setGroup(TagGroup.CueBallControl);
                break;
            case "FOLLOW":
                setGroup(TagGroup.CueBallControl);
                break;
            case "JUMP":
                setGroup(TagGroup.CueBallControl);
                break;
            case "MASSE":
                setGroup(TagGroup.CueBallControl);
                break;

            case "PATTERN":
                setGroup(TagGroup.Strategy);
                break;
            case "SAFETY":
                setGroup(TagGroup.Strategy);
                break;
            case "STRATEGY":
                setGroup(TagGroup.Strategy);
                break;

            case "8BALL":
                setGroup(TagGroup.Activity);
                break;
            case "9BALL":
                setGroup(TagGroup.Activity);
                break;
            case "CHALLENGE":
                setGroup(TagGroup.Activity);
                break;
            case "GAME":
                setGroup(TagGroup.Activity);
                break;
            case "RATING":
                setGroup(TagGroup.Activity);
                break;
            case "ROTATION":
                setGroup(TagGroup.Activity);
                break;
            case "SKILL":
                setGroup(TagGroup.Activity);
                break;

            default:
                setGroup(TagGroup.Misc);
                break;
        }
    }

    /**
     * <p>
     * Getter for the field <code>displayNameRef</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDisplayNameRef()
    {
        return displayNameRef;
    }

    /**
     * <p>
     * Getter for the field <code>group</code>.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.Tag.TagGroup} object.
     */
    public TagGroup getGroup()
    {
        return group;
    }

    /**
     * <p>
     * Getter for the field <code>tagID</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTagID()
    {
        return tagID;
    }

    /**
     * <p>
     * Setter for the field <code>group</code>.
     * </p>
     *
     * @param p_group a {@link com.obdobion.billiardsFX.model.Tag.TagGroup} object.
     */
    public void setGroup(final TagGroup p_group)
    {
        group = p_group;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return getTagID();
    }
}
