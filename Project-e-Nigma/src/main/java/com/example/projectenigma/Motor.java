package com.example.projectenigma;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

public class Motor {
    private Rectangle superficie;
    private Circle boton_en_ap;
    private Circle circulo_der;
    private Circle circulo_izq;
    private boolean motorEncendido;
    private SampleController controller;


    public Motor(AnchorPane anchorPane) {

        motorEncendido=false;

        superficie = new Rectangle(150,50,100,200);
        superficie.setX(1150);
        superficie.setY(450);
        superficie.setHeight(80);
        superficie.setWidth(100);
        superficie.setFill(Color.rgb(43, 123, 228));
        superficie.setStroke(Color.rgb(0, 0, 0));

        boton_en_ap=new Circle();
        boton_en_ap.setCenterX(1200);
        boton_en_ap.setCenterY(490);
        boton_en_ap.setRadius(12);
        boton_en_ap.setFill(Color.rgb(224,60,28));
        boton_en_ap.setStroke(Color.rgb(0,0,0));

        circulo_der=new Circle();
        circulo_der.setCenterX(1160);
        circulo_der.setCenterY(540);
        circulo_der.setRadius(12);
        circulo_der.setFill(Color.rgb(43,123,228));
        circulo_der.setStroke(Color.rgb(0,0,0));

        circulo_izq=new Circle();
        circulo_izq.setCenterX(1240);
        circulo_izq.setCenterY(540);
        circulo_izq.setRadius(12);
        circulo_izq.setFill(Color.rgb(43,123,228));
        circulo_izq.setStroke(Color.rgb(0,0,0));

        anchorPane.getChildren().addAll(superficie,boton_en_ap,circulo_der,circulo_izq);

        superficie.setOnMouseClicked(mouseEvent -> {Encender_Apagar();controller.CapturarMotor(1,boton_en_ap);
                }
        );
    }
    private void Encender_Apagar(){
        motorEncendido=!motorEncendido;

        if(motorEncendido){
            boton_en_ap.setFill(Color.rgb(60,224,28));
        }else{
            boton_en_ap.setFill(Color.rgb(224,60,28));
        }

    }

    public Rectangle getSuperficie(){
        return superficie;
    }
    public Circle getBoton_en_ap(){
        return boton_en_ap;
    }
    public Circle getCirculo_der(){
        return circulo_der;
    }
    public Circle getCirculo_izq(){
        return circulo_izq;
    }

    private boolean Conexion(){
        return true;
    }

    public Circle getConector1(){
        return circulo_der;
    }
    public Circle getConector2(){
        return circulo_izq;
    }
    public boolean Encendido(){
        return motorEncendido;
    }


}