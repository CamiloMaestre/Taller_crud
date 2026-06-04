package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.MainView;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView mainView = new MainView();
        mainView.launchView(stage);
    }
}