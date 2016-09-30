package com.obdobion.billiardsFX.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.drills.IDrillPackage;
import com.obdobion.billiardsFX.model.drills.IDrillPackageManager;

/**
 * <p>
 * DrillPackageManager class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrillPackageManager implements IDrillPackageManager
{
    final static Logger logger   = LoggerFactory.getLogger(IDrillPackageManager.class);

    List<IDrillPackage> packages = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void add(final IDrillPackage drillPackage)
    {
        logger.debug("{}", drillPackage);
        packages.add(drillPackage);
    }

    /** {@inheritDoc} */
    @Override
    public IDrillPackage getPackageByID(final String id)
    {
        for (final IDrillPackage pkg : packages)
        {
            if (pkg.getClass().getSimpleName().equalsIgnoreCase(id))
                return pkg;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>packages</code>.
     * </p>
     */
    @Override
    public List<IDrillPackage> getPackages()
    {
        return packages;
    }
}
