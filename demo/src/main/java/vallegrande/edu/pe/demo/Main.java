package vallegrande.edu.pe.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.demo.controller.MainController;
import vallegrande.edu.pe.demo.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainView view = new MainView();
        new MainController(view);
        Scene scene = new Scene(view, 950, 600);

        stage.setTitle("MI SISTEMA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}