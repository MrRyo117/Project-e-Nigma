package com.example.projectenigma;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

public class Motor {
    private Rectangle superficie;
    private boolean motorEncendido;
    private Circle boton_en_ap;
    private Circle circulo_der;
    private Circle circulo_izq;
    private AnchorPane anchorPane;

    public Motor(AnchorPane anchorPane) {
        this.anchorPane=anchorPane;

        superficie = new Rectangle();
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

        motorEncendido=false;

        boton_en_ap.setOnMouseClicked(mouseEvent -> Encender_Apagar());

        anchorPane.getChildren().addAll(superficie,boton_en_ap,circulo_der,circulo_izq);

    }

    private void Encender_Apagar(){
        if(motorEncendido){
            boton_en_ap.setFill(Color.rgb(224,60,28));
        }else{
            boton_en_ap.setFill(Color.rgb(60,224,28));
        }
        motorEncendido=!motorEncendido;
    }

    public void Cables_motor(){
        Line cable=new Line(superficie.getX()+ superficie.getWidth()/2,
                superficie.getY()+superficie.getHeight()/2,circulo_der.getCenterX(),circulo_der.getCenterY());

        cable.setStroke(Color.BLACK);
        cable.setStrokeWidth(3);

        anchorPane.getChildren().add(cable);
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
}