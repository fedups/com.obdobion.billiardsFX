package com.obdobion.billiardsFX.model.drills;

/**
 * <p>
 * Abstract AbstractDrillCommand class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public abstract class AbstractDrillCommand<E extends IDrill> implements IDrillCommand
{
    protected E drill;
    String      note;
    String      previousNote;

    /**
     * <p>
     * Constructor for AbstractDrillCommand.
     * </p>
     *
     * @param p_note a {@link java.lang.String} object.
     */
    public AbstractDrillCommand(final String p_note)
    {
        note = p_note;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * execute.
     * </p>
     */
    @Override
    final public void execute()
    {
        previousNote = drill.getNote();
        if (note != null)
            drill.setNote(note);
        privateExecute();
    }

    /**
     * <p>
     * privateExecute.
     * </p>
     */
    abstract public void privateExecute();

    /**
     * <p>
     * privateUndo.
     * </p>
     */
    abstract public void privateUndo();

    /**
     * {@inheritDoc}
     *
     * <p>
     * undo.
     * </p>
     */
    @Override
    final public void undo()
    {
        drill.setNote(previousNote);
        privateUndo();
    }

}
