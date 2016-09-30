package com.obdobion.billiardsFX.model.demos;

import com.obdobion.billiardsFX.model.ReservedNames.PackageName;
import com.obdobion.billiardsFX.model.drills.IDemonstration;

/**
 * <p>
 * DrDaveDemo class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrDaveDemo implements IDemonstration
{
    final PackageName packageName;
    final int         menuNumber;
    final String      startTime;

    /**
     * <p>
     * Constructor for DrDaveDemo.
     * </p>
     *
     * @param p_menuNumber a int.
     * @param p_startTime a {@link java.lang.String} object.
     * @param p_packageName a {@link com.obdobion.billiardsFX.model.ReservedNames.PackageName} object.
     */
    public DrDaveDemo(final PackageName p_packageName, final int p_menuNumber, final String p_startTime)
    {
        packageName = p_packageName;
        menuNumber = p_menuNumber;
        startTime = p_startTime;
    }
}
