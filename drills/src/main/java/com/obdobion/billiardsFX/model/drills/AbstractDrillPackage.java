package com.obdobion.billiardsFX.model.drills;

import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.DrillLookupFilter;

/**
 * <p>
 * Abstract AbstractDrillPackage class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
abstract public class AbstractDrillPackage implements IDrillPackage
{
    private List<DrillFactory> drillFactories;

    /** {@inheritDoc} */
    @Override
    public List<DrillFactory> availableDrills(final DrillLookupFilter filter)
    {
        final List<DrillFactory> availableDrills = new ArrayList<>();
        for (final DrillFactory df : drillFactories())
        {
            if (filter.selects(df))
                availableDrills.add(df);
        }
        return availableDrills;
    }

    /** {@inheritDoc} */
    @Override
    public IDrill createDrillByID(final Context context, final String id) throws InstantiationException
    {
        if (drillFactories == null)
            drillFactories = drillFactories();

        for (final DrillFactory df : drillFactories)
        {
            if (id.equalsIgnoreCase(df.drillID))
                try
                {
                    return df.newInstance(context);

                } catch (final IllegalAccessException e)
                {
                    throw new InstantiationException("can not create drill: " + id);
                }
        }
        throw new InstantiationException("unknown drill: " + id);
    }

    /**
     * <p>
     * drillFactories.
     * </p>
     *
     * @return a {@link java.util.List} object.
     */
    abstract protected List<DrillFactory> drillFactories();

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        String id = getClass().getSimpleName();
        int size = -1;
        if (id == null)
            id = "**NULL**";
        if (drillFactories != null)
            size = drillFactories.size();
        return id + " => " + size;
    }

}
