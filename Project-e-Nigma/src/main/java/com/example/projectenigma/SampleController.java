package com.example.projectenigma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class SampleController {

    @FXML
    private Circle circle;
    @FXML
    private Button iniciar;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Line linea;

    private Protoboard Protoboard2 = new Protoboard();
    private Circle[][] ArCircles = new Circle[30][14];

    public int tamano_filas = Protoboard2.protoboard.length;
    public int tamano_columnas =Protoboard2.protoboard[0].length;

    @FXML
    protected void inicio() {

        Protoboard2.CrearProtoboard(tamano_filas,tamano_columnas);
        //Protoboard2.CambiarCargaBus(Protoboard2.protoboard.length,Protoboard2.protoboard[0].length);


        for (int i = 0; i < 30; i++){

            for (int j = 0; j< 14; j++){
                Circle cicle = new Circle(i*20, j*30, 2);
                ArCircles[i][j] = cicle;
                AnchorPane.getChildren().add(ArCircles[i][j]);
            }
        }


        /*for(int i = 0; i < tamano_filas; i++) {
            System.out.println("Filas: " + (i+1));
            for (int j = 0; j < tamano_columnas; j++) {
                System.out.println("coordenadsas:" + i+" , "+j);
                System.out.println("Columnas: " + (j+1));
                Circle cicle = new Circle(i*20, j*30, 2);
                ArCircles[i][j] = cicle;
                AnchorPane.getChildren().add(ArCircles[i][j]);
            }
            //AnchorPane.getChildren().addAll(ArCircles[i]);
        }*/

    }

    @FXML
    public void imprimeline(){
        System.out.println("linea posision end x: "+linea.getEndX());
        System.out.println("linea posision end y: "+linea.getEndY());
        System.out.println("linea posision start x: "+linea.getStartX());
        System.out.println("linea posision start y: "+linea.getStartY());
        System.out.println("filas :"+tamano_filas+"\ncolumnas ;"+tamano_columnas);
    }
}
