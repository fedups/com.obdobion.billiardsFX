package com.obdobion.billiardsFX.javaFX;

import com.obdobion.billiardsFX.javaFX.view.AvailableDrillsView;
import com.obdobion.billiardsFX.javaFX.viewModel.AvailableDrillsViewModel;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MobileApp extends Application
{
    public static void main(final String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage)
    {
        stage.setTitle("Billiards FX");

        final ViewTuple<AvailableDrillsView, AvailableDrillsViewModel> viewTuple = FluentViewLoader.fxmlView(AvailableDrillsView.class).load();

        final Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));

        stage.show();
    }
}
