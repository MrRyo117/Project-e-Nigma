package com.example.projectenigma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 800);
        // para que el programa no muera, el tamaño minimo de la ventana es de largo = 1400; ancho = 800
        stage.setMinWidth(1400);
        stage.setMinHeight(800);
        stage.setMaxWidth(1980);
        stage.setMaxHeight(1080);
        stage.setTitle("E-nigma …\uD80C\uDC79‿\uD80C\uDC79…");
        stage.setScene(scene);
        stage.show();



    }

}