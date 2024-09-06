package com.example.projectenigma;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Objects;
import java.util.Scanner;

public class SampleController {

    @FXML
    private Circle circle;
    @FXML
    private Button iniciar;
    @FXML
    private Button Cable;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Line linea;
    @FXML
    private Rectangle rectangle;
    @FXML
    private TextArea textArea;


    private Protoboard Protoboard2 = new Protoboard();
    private Circle[][] ArCircles = new Circle[32][16];
    private Circle[][] Cargas = new Circle[5][5];

    public int tamano_filas = Protoboard2.protoboard.length;
    public int tamano_columnas =Protoboard2.protoboard[0].length;

    @FXML
    protected void inicio() {

        Protoboard2.CrearProtoboard(tamano_filas,tamano_columnas);
        //Protoboard2.CambiarCargaBus(Protoboard2.protoboard.length,Protoboard2.protoboard[0].length);

        //Creacion del rectangulo
        Rectangle Rectangulo= new Rectangle();
        Rectangulo.setWidth(1080);
        Rectangulo.setHeight(540);
        Rectangulo.setX(-30);
        Rectangulo.setY(-10);
        Rectangulo.setFill(Color.LIGHTGRAY);
        Rectangulo.setStroke(Color.BLACK);
        AnchorPane.getChildren().addAll(Rectangulo);

        //creacion dibujo bateria
        Rectangle bateria= new Rectangle();
        bateria.setWidth(270);
        bateria.setHeight(180);
        bateria.setX(1100);
        bateria.setY(60);
        bateria.setFill(Color.BLACK);
        bateria.setStroke(Color.BLACK);
        bateria.setRotate(90);
        AnchorPane.getChildren().addAll(bateria);


        Rectangle bateria2= new Rectangle();
        bateria2.setWidth(180);
        bateria2.setHeight(130);
        bateria2.setX(1145);
        bateria2.setY(15);
        bateria2.setFill(Color.GOLD);
        bateria2.setStroke(Color.BLACK);
        AnchorPane.getChildren().addAll(bateria2);

        Label label29 =new Label();
        label29.setLayoutX(1200);
        label29.setLayoutY(15);
        label29.setText("9v");
        label29.setFont(Font.font(50));
        AnchorPane.getChildren().addAll(label29);

        //Creacion de los label
        //Signo "+" arriba izq
        Label label1=new Label();
        label1.setLayoutX(20);
        label1.setLayoutY(35);
        label1.setText("+");
        label1.setTextFill(Color.RED);
        label1.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label1);

        //Signo "-" arriba izq
        Label label2=new Label();
        label2.setLayoutX(25);
        label2.setLayoutY(65);
        label2.setText("-");
        label2.setTextFill(Color.BLACK);
        label2.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label2);

        //Signo "+" Abajo iqz
        Label label23=new Label();
        label23.setLayoutX(20);
        label23.setLayoutY(458);
        label23.setText("+");
        label23.setTextFill(Color.RED);
        label23.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label23);

        //Signo "-" Abajo izq
        Label label24=new Label();
        label24.setLayoutX(25);
        label24.setLayoutY(488);
        label24.setText("-");
        label24.setTextFill(Color.BLACK);
        label24.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label24);

        //Signo "+" arriba der
        Label label25=new Label();
        label25.setLayoutX(945);
        label25.setLayoutY(35);
        label25.setText("+");
        label25.setTextFill(Color.RED);
        label25.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label25);

        //Signo "-" arriba der
        Label label26=new Label();
        label26.setLayoutX(950);
        label26.setLayoutY(70);
        label26.setText("-");
        label26.setTextFill(Color.BLACK);
        label26.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label26);

        //Signo "+" Abajo der
        Label label27=new Label();
        label27.setLayoutX(945);
        label27.setLayoutY(458);
        label27.setText("+");
        label27.setTextFill(Color.RED);
        label27.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label27);

        //Signo "-" Abajo der
        Label label28=new Label();
        label28.setLayoutX(950);
        label28.setLayoutY(488);
        label28.setText("-");
        label28.setTextFill(Color.BLACK);
        label28.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label28);

        //Abecedario

        Label label3=new Label();
        label3.setLayoutX(25);
        label3.setLayoutY(418);
        label3.setText("a");
        label3.setTextFill(Color.BLACK);
        label3.setFont(Font.font(15));
        label3.setRotate(-90);
        AnchorPane.getChildren().addAll(label3);

        Label label4=new Label();
        label4.setLayoutX(25);
        label4.setLayoutY(388);
        label4.setText("b");
        label4.setTextFill(Color.BLACK);
        label4.setFont(Font.font(15));
        label4.setRotate(-90);
        AnchorPane.getChildren().addAll(label4);

        Label label5=new Label();
        label5.setLayoutX(25);
        label5.setLayoutY(358);
        label5.setText("c");
        label5.setTextFill(Color.BLACK);
        label5.setFont(Font.font(15));
        label5.setRotate(-90);
        AnchorPane.getChildren().addAll(label5);

        Label label6=new Label();
        label6.setLayoutX(25);
        label6.setLayoutY(328);
        label6.setText("d");
        label6.setTextFill(Color.BLACK);
        label6.setFont(Font.font(15));
        label6.setRotate(-90);
        AnchorPane.getChildren().addAll(label6);

        Label label7=new Label();
        label7.setLayoutX(25);
        label7.setLayoutY(298);
        label7.setText("e");
        label7.setTextFill(Color.BLACK);
        label7.setFont(Font.font(15));
        label7.setRotate(-90);
        AnchorPane.getChildren().addAll(label7);

        Label label8=new Label();
        label8.setLayoutX(28);
        label8.setLayoutY(248);
        label8.setText("f");
        label8.setTextFill(Color.BLACK);
        label8.setFont(Font.font(15));
        label8.setRotate(-90);
        AnchorPane.getChildren().addAll(label8);

        Label label9=new Label();
        label9.setLayoutX(25);
        label9.setLayoutY(218);
        label9.setText("g");
        label9.setTextFill(Color.BLACK);
        label9.setFont(Font.font(15));
        label9.setRotate(-90);
        AnchorPane.getChildren().addAll(label9);

        Label label10=new Label();
        label10.setLayoutX(25);
        label10.setLayoutY(188);
        label10.setText("h");
        label10.setTextFill(Color.BLACK);
        label10.setFont(Font.font(15));
        label10.setRotate(-90);
        AnchorPane.getChildren().addAll(label10);

        Label label11=new Label();
        label11.setLayoutX(25);
        label11.setLayoutY(158);
        label11.setText("i");
        label11.setTextFill(Color.BLACK);
        label11.setFont(Font.font(15));
        label11.setRotate(-90);
        AnchorPane.getChildren().addAll(label11);

        Label label12=new Label();
        label12.setLayoutX(25);
        label12.setLayoutY(128);
        label12.setText("j");
        label12.setTextFill(Color.BLACK);
        label12.setFont(Font.font(15));
        label12.setRotate(-90);
        AnchorPane.getChildren().addAll(label12);

        //abecedario lado derecho

        Label label13=new Label();
        label13.setLayoutX(950);
        label13.setLayoutY(418);
        label13.setText("a");
        label13.setTextFill(Color.BLACK);
        label13.setFont(Font.font(15));
        label13.setRotate(-90);
        AnchorPane.getChildren().addAll(label13);

        Label label14=new Label();
        label14.setLayoutX(950);
        label14.setLayoutY(388);
        label14.setText("b");
        label14.setTextFill(Color.BLACK);
        label14.setFont(Font.font(15));
        label14.setRotate(-90);
        AnchorPane.getChildren().addAll(label14);

        Label label15=new Label();
        label15.setLayoutX(950);
        label15.setLayoutY(358);
        label15.setText("c");
        label15.setTextFill(Color.BLACK);
        label15.setFont(Font.font(15));
        label15.setRotate(-90);
        AnchorPane.getChildren().addAll(label15);

        Label label16=new Label();
        label16.setLayoutX(950);
        label16.setLayoutY(328);
        label16.setText("d");
        label16.setTextFill(Color.BLACK);
        label16.setFont(Font.font(15));
        label16.setRotate(-90);
        AnchorPane.getChildren().addAll(label16);

        Label label17=new Label();
        label17.setLayoutX(950);
        label17.setLayoutY(298);
        label17.setText("e");
        label17.setTextFill(Color.BLACK);
        label17.setFont(Font.font(15));
        label17.setRotate(-90);
        AnchorPane.getChildren().addAll(label17);

        Label label18=new Label();
        label18.setLayoutX(950);
        label18.setLayoutY(248);
        label18.setText("f");
        label18.setTextFill(Color.BLACK);
        label18.setFont(Font.font(15));
        label18.setRotate(-90);
        AnchorPane.getChildren().addAll(label18);

        Label label19=new Label();
        label19.setLayoutX(945);
        label19.setLayoutY(218);
        label19.setText("g");
        label19.setTextFill(Color.BLACK);
        label19.setFont(Font.font(15));
        label19.setRotate(-90);
        AnchorPane.getChildren().addAll(label19);

        Label label20=new Label();
        label20.setLayoutX(950);
        label20.setLayoutY(188);
        label20.setText("h");
        label20.setTextFill(Color.BLACK);
        label20.setFont(Font.font(15));
        label20.setRotate(-90);
        AnchorPane.getChildren().addAll(label20);

        Label label21=new Label();
        label21.setLayoutX(950);
        label21.setLayoutY(158);
        label21.setText("i");
        label21.setTextFill(Color.BLACK);
        label21.setFont(Font.font(15));
        label21.setRotate(-90);
        AnchorPane.getChildren().addAll(label21);

        Label label22=new Label();
        label22.setLayoutX(950);
        label22.setLayoutY(128);
        label22.setText("j");
        label22.setTextFill(Color.BLACK);
        label22.setFont(Font.font(15));
        label22.setRotate(-90);
        AnchorPane.getChildren().addAll(label22);

        int AuxSpace = 0;
        for (int i = 2; i < 32; i++){

            for (int j = 2; j< 16; j++){

                Circle circle = new Circle(i, j, 7);

                if(j==4 || j==9 || j==14){
                    AuxSpace+=20;
                }

                circle.setCenterX(i*30);
                circle.setCenterY(j*30+AuxSpace);
                System.out.println(circle.getCenterY());
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setOnMouseClicked(event -> ClickCirculo(circle));

                ArCircles[i][j] = circle;
                AnchorPane.getChildren().add(ArCircles[i][j]);

            }
            AuxSpace=0;
        }

    }

    private void ClickCirculo(Circle circle){
        int Columna = 0;
        int Fila = 0;

        Columna = (((int) circle.getCenterX())- 30) / 30;

        System.out.println("Columna: " + Columna);
        System.out.println(circle.getCenterY());
        if ((int) circle.getCenterY() <= 90){
            Fila = ( (int) circle.getCenterY() - 60) / 30;
        }
        else if ( (int) circle.getCenterY() > 90  && (int) circle.getCenterY() <= 260){
            Fila = ( (int) circle.getCenterY() -80) / 30;
        }
        else if ( (int) circle.getCenterY() > 260 && (int) circle.getCenterY() <= 480){
            Fila = ( (int) circle.getCenterY() -100) / 30;
        }
        else {
            Fila = ( (int) circle.getCenterY() -120) / 30;
        }
        Fila += 1;
        System.out.println("Fila : " + Fila);

    }
    @FXML
    public void imprimeline(){
        System.out.println("linea posision end x: "+linea.getEndX());
        System.out.println("linea posision end y: "+linea.getEndY());
        System.out.println("linea posision start x: "+linea.getStartX());
        System.out.println("linea posision start y: "+linea.getStartY());
        System.out.println("filas :"+tamano_filas+"\ncolumnas ;"+tamano_columnas);
    }

    @FXML
    public void Cables(){
        Line cable1 = new Line();
        cable1.setStartX(1145);
        cable1.setStartY(60);
        Scanner respuesta1 =new Scanner(System.in);
        System.out.println("Ingrese si el numero del bus (arriba(negativo=2,positivo=3)-abajo(negativo=15,positivo=16: ");
        int res1=respuesta1.nextInt();
        Scanner respuesta2 =new Scanner(System.in);
        System.out.println("Ingrese el numero del hoyito del bus: ");
        int  res2= respuesta2.nextInt();

        if(res1==2){

            int colocacion_x = 930; // valor de la posx del hoyito 1 es 930 // se restan 30 para ir a la izquierda
            int colocacion_y; // utilizar esta variable para cambiar el valor de la posy de los cables

            Scanner respuesta3 =new Scanner(System.in);
            System.out.println("Ingrese la letra del hoyito: ");
            String res3= respuesta3.nextLine();
            Line cable2 = new Line();


            cable1.setStroke(Color.BLACK);
            cable1.setStrokeWidth(3);
            cable1.setEndX(colocacion_x-((res2-1)*30));
            cable1.setEndY(90);
            AnchorPane.getChildren().add(cable1);

            if (res3.equals("j")){
                cable2.setStartY(90);
                cable2.setStartX(colocacion_x-((res2-1)*30));

                Scanner respuesta4 = new Scanner(System.in);
                System.out.println("Ingrese el numero del hoyito: ");
                int res4 = respuesta4.nextInt();
                cable2.setEndY(150);
                cable2.setEndX(colocacion_x-((res4-1)*30));
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);
                int x=1;
                for(int i=0;i<5;i++) {
                    Circle circle= new Circle(450,450,7);
                    circle.setCenterX(colocacion_x-((res4-1)*30));
                    circle.setCenterY(120 + (i * 30));
                    circle.setFill(Color.BLACK);
                    Cargas[x][i]= circle;
                    AnchorPane.getChildren().add(Cargas[x][i]);

                }
                AnchorPane.getChildren().add(cable2);

            }

            if (res3.equals("i")){
                cable2.setStartY(90);
                cable2.setStartX(colocacion_x-((res2-1)*30));

                Scanner respuesta4 = new Scanner(System.in);
                System.out.println("Ingrese el numero del hoyito: ");
                int res4 = respuesta4.nextInt();
                cable2.setEndY(180);
                cable2.setEndX(colocacion_x-((res4-1)*30));
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);
                int x=1;
                for(int i=0;i<5;i++) {
                    Circle circle= new Circle(450,450,7);
                    circle.setCenterX(colocacion_x-((res4-1)*30));
                    circle.setCenterY(120 + (i * 30));
                    circle.setFill(Color.BLACK);
                    Cargas[x][i]= circle;
                    AnchorPane.getChildren().add(Cargas[x][i]);

                }
                AnchorPane.getChildren().add(cable2);
            }
            if (res3.equals("h")){
                cable2.setStartY(90);
                cable2.setStartX(colocacion_x-((res2-1)*30));

                Scanner respuesta4 = new Scanner(System.in);
                System.out.println("Ingrese el numero del hoyito: ");
                int res4 = respuesta4.nextInt();
                cable2.setEndY(210);
                cable2.setEndX(colocacion_x-((res4-1)*30));
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);
                int x=1;
                for(int i=0;i<5;i++) {
                    Circle circle= new Circle(450,450,7);
                    circle.setCenterX(colocacion_x-((res4-1)*30));
                    circle.setCenterY(120 + (i * 30));
                    circle.setFill(Color.BLACK);
                    Cargas[x][i]= circle;
                    AnchorPane.getChildren().add(Cargas[x][i]);

                }
                AnchorPane.getChildren().add(cable2);
            }
            if (res3.equals("g")){
                cable2.setStartY(90);
                cable2.setStartX(colocacion_x-((res2-1)*30));

                Scanner respuesta4 = new Scanner(System.in);
                System.out.println("Ingrese el numero del hoyito: ");
                int res4 = respuesta4.nextInt();
                cable2.setEndY(230);
                cable2.setEndX(colocacion_x-((res4-1)*30));
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);
                int x=1;
                for(int i=0;i<5;i++) {
                    Circle circle= new Circle(450,450,7);
                    circle.setCenterX(colocacion_x-((res4-1)*30));
                    circle.setCenterY(120 + (i * 30));
                    circle.setFill(Color.BLACK);
                    Cargas[x][i]= circle;
                    AnchorPane.getChildren().add(Cargas[x][i]);

                }
                AnchorPane.getChildren().add(cable2);
            }
            if (res3.equals("f")){
                cable2.setStartY(90);
                cable2.setStartX(colocacion_x-((res2-1)*30));

                Scanner respuesta4 = new Scanner(System.in);
                System.out.println("Ingrese el numero del hoyito: ");
                int res4 = respuesta4.nextInt();
                cable2.setEndY(250);
                cable2.setEndX(colocacion_x-((res4-1)*30));
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);
                int x=1;
                for(int i=0;i<5;i++) {
                    Circle circle= new Circle(450,450,7);
                    circle.setCenterX(colocacion_x-((res4-1)*30));
                    circle.setCenterY(120 + (i * 30));
                    circle.setFill(Color.BLACK);
                    Cargas[x][i]= circle;
                    AnchorPane.getChildren().add(Cargas[x][i]);

                }
                AnchorPane.getChildren().add(cable2);
            }


        /*} else if (res1==3) {
            cable1.setStroke(Color.RED);
            cable1.setStrokeWidth(3);
            cable1.setEndX(930);
            cable1.setEndY(60);
            AnchorPane.getChildren().add(cable1);
            if(res2==16){
                cable2.setStartY(90);
                cable2.setStartX(930);
                cable2.setEndY(150);
                cable2.setEndX(930);
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);

                AnchorPane.getChildren().add(cable2);
            }

        } else if (res1==15) {
            cable1.setStroke(Color.BLACK);
            cable1.setStrokeWidth(3);
            cable1.setEndX(930);
            cable1.setEndY(450);
            AnchorPane.getChildren().add(cable1);
            if(res2==16){
                cable2.setStartY(90);
                cable2.setStartX(930);
                cable2.setEndY(150);
                cable2.setEndX(930);
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);

                AnchorPane.getChildren().add(cable2);
            }

        } else if (res1==16) {
            cable1.setStroke(Color.RED);
            cable1.setStrokeWidth(3);
            cable1.setEndX(930);
            cable1.setEndY(470);
            AnchorPane.getChildren().add(cable1);
            if(res2==16){
                cable2.setStartY(90);
                cable2.setStartX(930);
                cable2.setEndY(150);
                cable2.setEndX(930);
                cable2.setStroke(Color.BLACK);
                cable2.setStrokeWidth(3);

                AnchorPane.getChildren().add(cable2);
            }*/

        }
    }
}
