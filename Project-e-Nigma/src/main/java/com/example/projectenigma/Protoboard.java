package com.example.projectenigma;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import java.awt.*;

public class Protoboard {

    //Atributos
    Hoyito[][] protoboard = new Hoyito[32][16];

    //Metodos

    // Crea una matriz llena de hoyitos sin nada
    public void CrearProtoboard(int fila, int columna){
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < columna; j++){
                Hoyito hoyito = new Hoyito();
                protoboard[i][j] = hoyito;
            }
        }
    }


    public void CambiarCargaBus(int fila, int columna){
        //dependiendo del valor de la fila, sabemos si son buses
        //el metodo crea un hoyito temporal que remplaza al original con los valores modificados
        switch (fila){
            case 0, 1, 12, 13:
            {
                for (int j = 0; j < columna; j++){
                    Hoyito temp = new Hoyito(1,1,false);
                    protoboard[fila][j] = temp;
                }
                break;
            }

        }
        //cambia el estado del hoyito cual esta conectado con un cable
        Hoyito temp = new Hoyito(1,1,true);
        protoboard[fila][columna] = temp;
    }

    public void CambiarCArgaPistas(int fila, String columna){
        if (fila==1 && columna.equals("j")){
            Circle circle= new Circle(fila,450,7);
            for(int i=0;i<5;i++) {
                circle.setCenterX(450);
                circle.setCenterY(120 + (i * 30));
                circle.setFill(Color.RED);

            }
        }


    }

    public void EstadoHoyito(int Columna, int Fila){
        System.out.println("Fila:"+ Fila + " Columna:"+ Columna );
        System.out.println("Contenido:");
        System.out.println("Cargavolt : "+ protoboard[Fila][Columna].getCargaVolt());
        System.out.println("Cargaamp : "+ protoboard[Fila][Columna].getCargaAmp());
        System.out.println("Estado : "+ protoboard[Fila][Columna].isEstado());

    }



}