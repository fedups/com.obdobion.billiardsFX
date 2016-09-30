package com.obdobion.billiardsFX.model.drills;

import java.util.List;

/**
 * This class is the package manager. A package is a collection of
 * {@link com.obdobion.billiardsFX.model.drills.IDrill}s that are installed as a
 * group.
 *
 * @author degreefc
 */
public interface IDrillPackageManager
{

    /**
     * Use this method to add a package. This is how
     * {@link com.obdobion.billiardsFX.model.drills.IDrill}s are added to the system.
     *
     * @param drillPackage is the
     *            {@link com.obdobion.billiardsFX.model.drills.IDrillPackage} to be
     *            added
     */
    void add(IDrillPackage drillPackage);

    /**
     * Use this method to get a previously {@link #add(IDrillPackage)}ed
     * package.
     *
     * @param name The case-insensitive package name to be used in the search
     * @return the selected package
     */
    IDrillPackage getPackageByID(String name);

    /**
     * Use this method to return all installed (added) packages.
     *
     * @return a list of packages (possibly empty)
     */
    List<IDrillPackage> getPackages();
}
