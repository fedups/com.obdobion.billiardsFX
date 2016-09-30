package com.obdobion.billiardsFX.javaFX.viewModel;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AvailableDrillsViewModel implements ViewModel
{
    static final public boolean   ASYNC                    = true;

    private final StringProperty  sortButtonTextProperty   = new SimpleStringProperty("Sort");
    private final Command         sortCommand;
    private final BooleanProperty sortCommandPrecondition;

    private final StringProperty  filterButtonTextProperty = new SimpleStringProperty("Filter");
    private final Command         filterCommand;
    private final BooleanProperty filterCommandPrecondition;

    public AvailableDrillsViewModel()
    {
        {
            sortCommandPrecondition = new SimpleBooleanProperty(true);
            sortCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    Platform.runLater(() -> {
                        sortCommandPrecondition.set(false);
                        filterCommandPrecondition.set(true);
                    });
                    System.out.println("camouwn sort");
                }
            }, sortCommandPrecondition, ASYNC);
        }
        {
            filterCommandPrecondition = new SimpleBooleanProperty(true);
            filterCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    Platform.runLater(() -> {
                        sortCommandPrecondition.set(true);
                        filterCommandPrecondition.set(false);
                    });
                    System.out.println("camouwn filter");
                }
            }, filterCommandPrecondition, ASYNC);
        }
    }

    public StringProperty getFilterButtonTextProperty()
    {
        return filterButtonTextProperty;
    }

    public Command getFilterCommand()
    {
        return filterCommand;
    }

    public StringProperty getSortButtonTextProperty()
    {
        return sortButtonTextProperty;
    }

    public Command getSortCommand()
    {
        return sortCommand;
    }
}
