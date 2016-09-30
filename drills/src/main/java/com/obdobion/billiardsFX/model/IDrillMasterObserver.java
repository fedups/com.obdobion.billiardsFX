package com.obdobion.billiardsFX.model;

import com.obdobion.billiardsFX.model.drills.IDrill;

/**
 * This interface must be implemented on a class if the class is to be used as a
 * listener for these events. See
 * {@link com.obdobion.billiardsFX.model.IDrillMaster#addObserver(IDrillMasterObserver)}
 *
 * @author degreefc
 */
public interface IDrillMasterObserver
{
    /**
     * The listener will be called with this method when the drill is created.
     *
     * @param drill is the drill that was created
     */
    void drillCreatedEvent(IDrill drill);

    /**
     * The listener (implementor of this interface) will be called with this
     * method when the drill is destroyed.
     *
     * @param drill is the drill that was destroyed.
     */
    void drillDestroyedEvent(IDrill drill);
}
