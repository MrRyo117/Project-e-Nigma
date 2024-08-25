package com.example.projectenigma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class SampleController {

    @FXML
    private Circle circle;
    @FXML
    private Button iniciar;
    @FXML
    private AnchorPane AnchorPane;

    private Protoboard Protoboard2 = new Protoboard();
    private Circle ArCircles[][] = new Circle[14][30];


    @FXML
    protected void inicio() {
        for(int i = 0; i < Protoboard2.protoboard.length; i++) {
            System.out.println("Fila: " + i);

            for (int j = 0; j < Protoboard2.protoboard[i].length; j++) {
                System.out.println("Columna: " + j);
                Circle cicle = new Circle(i*10, j*10, 1);
                ArCircles[i][j] = cicle;

            }
            AnchorPane.getChildren().addAll(ArCircles[i]);
        }



    }



}
