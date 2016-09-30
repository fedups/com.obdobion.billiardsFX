package com.obdobion.billiardsFX.javaFX.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.obdobion.billiardsFX.javaFX.viewModel.AvailableDrillsViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class AvailableDrillsView implements FxmlView<AvailableDrillsViewModel>, Initializable
{
    @InjectViewModel
    private AvailableDrillsViewModel viewModel;

    @FXML
    private Button                   filterButton;

    @FXML
    private Button                   sortButton;

    @FXML
    private ListView<?>              availableDrillsList;

    @FXML
    public void filterButtonAction()
    {
        viewModel.getFilterCommand().execute();
    }

    public void initialize(final URL location, final ResourceBundle resources)
    {
        sortButton.disableProperty().bind(viewModel.getSortCommand().notExecutableProperty());
        sortButton.textProperty().bind(viewModel.getSortButtonTextProperty());

        filterButton.disableProperty().bind(viewModel.getFilterCommand().notExecutableProperty());
        filterButton.textProperty().bind(viewModel.getFilterButtonTextProperty());
    }

    @FXML
    public void sortButtonAction()
    {
        viewModel.getSortCommand().execute();
    }
}
