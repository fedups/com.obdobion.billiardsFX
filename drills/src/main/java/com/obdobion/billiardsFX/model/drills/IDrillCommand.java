package com.obdobion.billiardsFX.model.drills;

/**
 * This interface must be implemented on all commands that will be used to
 * update drills.
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public interface IDrillCommand
{
    /**
     * This method is called when the command needs to be executed.
     */
    void execute();

    /**
     * This method will be called if the execute activity must be undone. The
     * state will be returned to what it was prior to the execute.
     */
    void undo();
}
