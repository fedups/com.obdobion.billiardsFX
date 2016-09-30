package com.obdobion.billiardsFX.model;

import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.drills.DrillFactory;

/**
 * This class allows filters to be placed on drill factory selection. It is a
 * linked list of filters where each next item in the list refines the filtering
 * done in a previous list. Sending reset to the filter will trim that filter
 * and all refining filters - like a back button. Setting the tags causes a lock
 * in of the filter and a new refining filter is started.
 *
 * @author degreefc
 */
public class DrillLookupFilter
{
    private String            exactDrillID;
    private List<Tag>         excludeTags;
    private List<Tag>         includeTags;
    private DrillLookupFilter parentFilter;
    private DrillLookupFilter refiningFilter;

    /**
     * <p>
     * Constructor for DrillLookupFilter.
     * </p>
     */
    public DrillLookupFilter()
    {}

    DrillLookupFilter(final DrillLookupFilter p_parentFilter)
    {
        this();
        parentFilter = p_parentFilter;
    }

    /**
     * <p>
     * Constructor for DrillLookupFilter.
     * </p>
     *
     * @param p_exactDrillID a {@link java.lang.String} object.
     */
    public DrillLookupFilter(final String p_exactDrillID)
    {
        this();
        exactDrillID = p_exactDrillID;
    }

    /**
     * <p>
     * back.
     * </p>
     */
    public void back()
    {
        final DrillLookupFilter finestFilter = getLastRefiningFilter();
        if (finestFilter.parentFilter != null)
            finestFilter.parentFilter.reset();
    }

    List<Tag> getExcludeTags()
    {
        if (excludeTags == null)
            excludeTags = new ArrayList<>();
        return excludeTags;
    }

    List<Tag> getIncludeTags()
    {
        if (includeTags == null)
            includeTags = new ArrayList<>();
        return includeTags;
    }

    private DrillLookupFilter getLastRefiningFilter()
    {
        if (refiningFilter == null)
            return this;
        return refiningFilter.getLastRefiningFilter();
    }

    /**
     * <p>
     * Getter for the field <code>refiningFilter</code>.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.DrillLookupFilter} object.
     */
    public DrillLookupFilter getRefiningFilter()
    {
        return refiningFilter;
    }

    /**
     * <p>
     * reset.
     * </p>
     */
    public void reset()
    {
        exactDrillID = null;
        includeTags = null;
        excludeTags = null;
        setRefiningFilter(null);
    }

    /**
     * <p>
     * selects.
     * </p>
     *
     * @param df a {@link com.obdobion.billiardsFX.model.drills.DrillFactory} object.
     * @return a boolean.
     */
    public boolean selects(final DrillFactory df)
    {
        if (exactDrillID != null)
            return df.drillID.equalsIgnoreCase(exactDrillID);

        if (getIncludeTags().isEmpty() && getExcludeTags().isEmpty())
            /*
             * A null filter selects everything.
             */
            return true;

        nextMustHaveTag: for (final Tag mustHaveTag : getIncludeTags())
        {
            for (final Tag drillTag : df.tags)
            {
                if (drillTag == mustHaveTag)
                    continue nextMustHaveTag;
            }
            return false;
        }

        for (final Tag mustNotHaveTag : getExcludeTags())
        {
            for (final Tag drillTag : df.tags)
            {
                if (drillTag == mustNotHaveTag)
                    return false;
            }
        }
        if (refiningFilter != null)
            return refiningFilter.selects(df);
        return true;
    }

    /**
     * <p>selectsEverything.</p>
     *
     * @return a boolean.
     */
    protected boolean selectsEverything()
    {
        if (exactDrillID == null)
        {
            if (includeTags == null && excludeTags == null)
                return true;

            if (getIncludeTags().isEmpty() && getExcludeTags().isEmpty())
                return true;
        }
        return false;

    }

    /**
     * <p>
     * Setter for the field <code>exactDrillID</code>.
     * </p>
     *
     * @param drillID a {@link java.lang.String} object.
     * @return a {@link com.obdobion.billiardsFX.model.DrillLookupFilter} object.
     */
    public DrillLookupFilter setExactDrillID(final String drillID)
    {
        exactDrillID = drillID;
        return this;
    }

    /**
     * <p>
     * Setter for the field <code>excludeTags</code>.
     * </p>
     *
     * @param tags a {@link com.obdobion.billiardsFX.model.Tag} object.
     * @return a {@link com.obdobion.billiardsFX.model.DrillLookupFilter} object.
     */
    public DrillLookupFilter setExcludeTags(final Tag... tags)
    {
        final DrillLookupFilter filter = getLastRefiningFilter();
        getExcludeTags().clear();
        for (final Tag tag : tags)
        {
            filter.getExcludeTags().add(tag);
        }
        filter.setRefiningFilter(new DrillLookupFilter(filter));
        return this;
    }

    /**
     * <p>
     * Setter for the field <code>includeTags</code>.
     * </p>
     *
     * @param tags a {@link com.obdobion.billiardsFX.model.Tag} object.
     * @return a {@link com.obdobion.billiardsFX.model.DrillLookupFilter} object.
     */
    public DrillLookupFilter setIncludeTags(final Tag... tags)
    {
        final DrillLookupFilter filter = getLastRefiningFilter();
        for (final Tag tag : tags)
        {
            filter.getIncludeTags().add(tag);
        }
        filter.setRefiningFilter(new DrillLookupFilter(filter));
        return this;
    }

    /**
     * <p>
     * Setter for the field <code>refiningFilter</code>.
     * </p>
     *
     * @param p_refiningFilter a
     *            {@link com.obdobion.billiardsFX.model.DrillLookupFilter} object.
     * @return a {@link com.obdobion.billiardsFX.model.DrillLookupFilter} object.
     */
    public DrillLookupFilter setRefiningFilter(final DrillLookupFilter p_refiningFilter)
    {
        this.refiningFilter = p_refiningFilter;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * toString.
     * </p>
     */
    @Override
    public String toString()
    {
        if (selectsEverything())
            return "select all";

        final StringBuilder sb = new StringBuilder();
        if (exactDrillID != null)
            sb.append("exactDrillId = ").append(exactDrillID);

        String prefix = " include ";
        for (final Tag tag : getIncludeTags())
        {
            sb.append(prefix).append(tag.toString());
            prefix = ", ";
        }

        prefix = " exclude ";
        for (final Tag tag : getExcludeTags())
        {
            sb.append(prefix).append(tag.toString());
            prefix = ", ";
        }
        if (getRefiningFilter() != null)
        {
            sb.append(" then ");
            sb.append(getRefiningFilter().toString());
        }
        return sb.toString();
    }
}
