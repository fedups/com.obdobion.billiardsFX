package com.obdobion.billiardsFX.model.drills;

import java.util.List;

import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.DrillLookupFilter;

/**
 * This class defines the drill package.
 *
 * @author degreefc
 */
public interface IDrillPackage
{
    /**
     * <p>
     * availableDrills.
     * </p>
     *
     * @param filter a {@link com.obdobion.billiardsFX.model.DrillLookupFilter}
     *            object.
     * @return a {@link java.util.List} object.
     */
    List<DrillFactory> availableDrills(DrillLookupFilter filter);

    /**
     * Use this method to create a drill from the named definition within this
     * package.
     *
     * @param simpleClassName is the name of the drill provided by this package
     * @return the instance of the drill or null if the definition of the drill
     *         does not exist in this package.
     * @throws java.lang.InstantiationException if any.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    IDrill createDrillByID(Context context, String simpleClassName) throws InstantiationException;
}
