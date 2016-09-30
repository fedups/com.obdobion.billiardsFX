package com.obdobion.billiardsFX.model.drills;

/**
 * This interface must be implemented on a class if the class is to be used as a
 * listener for these events. See
 * {@link com.obdobion.billiardsFX.model.drills.IDrill#addObserver(IDrillObserver)}
 *
 * @author degreefc
 */
public interface IDrillObserver
{
    /**
     * The listener will be called with this method when the drill is done.
     *
     * @param drill is the drill that has completed
     */
    void drillOverEvent(IDrill drill);

    /**
     * The listener will be called with this method when the drill is started.
     *
     * @param drill is the drill that was started
     */
    void drillStartEvent(IDrill drill);

    /**
     * The listener will be called with this method when the drill's score has
     * changed
     *
     * @param drill is the drill that was modified
     */
    void scoreChangedEvent(IDrill drill);

}
