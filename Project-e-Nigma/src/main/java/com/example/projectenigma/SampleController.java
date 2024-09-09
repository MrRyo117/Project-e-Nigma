package com.example.projectenigma;


import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import java.util.ArrayList;

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

    public ArrayList<Integer> Historial = new ArrayList<Integer>(); //cada numero representa una pieza: 1 cable, 2 led, 3 switch

    @FXML
    protected void inicio() {

        Protoboard2.CrearProtoboard(tamano_filas, tamano_columnas);
        //Protoboard2.CambiarCargaBus(Protoboard2.protoboard.length,Protoboard2.protoboard[0].length);

        //Creacion del rectangulo
        Rectangle Rectangulo = new Rectangle();
        Rectangulo.setWidth(1080);
        Rectangulo.setHeight(540);
        Rectangulo.setX(-30);
        Rectangulo.setY(-10);
        Rectangulo.setFill(Color.LIGHTGRAY);
        Rectangulo.setStroke(Color.BLACK);
        Rectangulo.setOnMouseClicked(event -> funcion(Rectangulo));
        AnchorPane.getChildren().addAll(Rectangulo);

        //creacion dibujo bateria
        Rectangle bateria = new Rectangle();
        bateria.setWidth(270);
        bateria.setHeight(180);
        bateria.setX(1100);
        bateria.setY(170);
        bateria.setFill(Color.BLACK);
        bateria.setStroke(Color.BLACK);
        bateria.setRotate(90);
        AnchorPane.getChildren().addAll(bateria);

        Rectangle bateria2 = new Rectangle();
        bateria2.setWidth(180);
        bateria2.setHeight(130);
        bateria2.setX(1145);
        bateria2.setY(120);
        bateria2.setFill(Color.GOLD);
        bateria2.setStroke(Color.BLACK);
        AnchorPane.getChildren().addAll(bateria2);

        Label label13 = new Label();
        label13.setLayoutX(1200);
        label13.setLayoutY(150);
        label13.setText("9v");
        label13.setFont(Font.font(50));
        AnchorPane.getChildren().addAll(label13);

        //Creacion de los label
        //Signo "+" arriba izq
        Label label1 = new Label();
        label1.setLayoutX(20);
        label1.setLayoutY(35);
        label1.setText("+");
        label1.setTextFill(Color.RED);
        label1.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label1);

        //Signo "-" arriba izq
        Label label2 = new Label();
        label2.setLayoutX(25);
        label2.setLayoutY(65);
        label2.setText("-");
        label2.setTextFill(Color.BLACK);
        label2.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label2);

        //Signo "+" Abajo iqz
        Label label3 = new Label();
        label3.setLayoutX(20);
        label3.setLayoutY(458);
        label3.setText("+");
        label3.setTextFill(Color.RED);
        label3.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label3);

        //Signo "-" Abajo izq
        Label label4 = new Label();
        label4.setLayoutX(25);
        label4.setLayoutY(488);
        label4.setText("-");
        label4.setTextFill(Color.BLACK);
        label4.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label4);

        //Signo "+" arriba der
        Label label5 = new Label();
        label5.setLayoutX(945);
        label5.setLayoutY(35);
        label5.setText("+");
        label5.setTextFill(Color.RED);
        label5.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label5);

        //Signo "-" arriba der
        Label label6 = new Label();
        label6.setLayoutX(950);
        label6.setLayoutY(70);
        label6.setText("-");
        label6.setTextFill(Color.BLACK);
        label6.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label6);

        //Signo "+" Abajo der
        Label label7 = new Label();
        label7.setLayoutX(945);
        label7.setLayoutY(458);
        label7.setText("+");
        label7.setTextFill(Color.RED);
        label7.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label7);

        //Signo "-" Abajo der
        Label label8 = new Label();
        label8.setLayoutX(950);
        label8.setLayoutY(488);
        label8.setText("-");
        label8.setTextFill(Color.BLACK);
        label8.setFont(Font.font(30));
        AnchorPane.getChildren().addAll(label8);

        int correcion_posy= 0, salto = 0; //valores para corregir la disposicion visual de las letras

        //creacion del abcedario parte izquierda
        for(char letter='a';letter<='j';letter++){
            if (letter=='f'){
                //añade una separacion
                Label label9= new Label();
                label9.setLayoutX(25);
                label9.setLayoutY(418-(letter-'a')*30);
                label9.setText(String.valueOf(' '));
                label9.setTextFill(Color.BLACK);
                label9.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label9);

                //coloca la letra f
                Label label9_5 = new Label();
                label9_5.setLayoutX(25);
                label9_5.setLayoutY(429-(letter-'a'+1)*30);
                label9_5.setText(String.valueOf(letter));
                label9_5.setTextFill(Color.BLACK);
                label9_5.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label9_5);

                correcion_posy = 11;
                salto++;

            }else {
                Label label9 = new Label();
                label9.setLayoutX(25);
                label9.setLayoutY((418 + correcion_posy) - (letter - 'a' + salto) * 30);
                label9.setText(String.valueOf(letter));
                label9.setTextFill(Color.BLACK);
                label9.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label9);
            }
        }

        correcion_posy= 0; salto = 0;

        //creacion del abcedario parte derecha
        for(char letter='a';letter<='j';letter++){
            if (letter=='f'){
                //añade una separacion
                Label label10= new Label();
                label10.setLayoutX(950);
                label10.setLayoutY(418-(letter-'a')*30);
                label10.setText(String.valueOf(' '));
                label10.setTextFill(Color.BLACK);
                label10.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label10);

                //coloca la letra f
                Label label10_5 = new Label();
                label10_5.setLayoutX(950);
                label10_5.setLayoutY(429-(letter-'a'+1)*30);
                label10_5.setText(String.valueOf(letter));
                label10_5.setTextFill(Color.BLACK);
                label10_5.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label10_5);

                correcion_posy = 11;
                salto++;

            }else {
                Label label10= new Label();
                label10.setLayoutX(950);
                label10.setLayoutY((418 + correcion_posy)-(letter-'a' + salto)*30);
                label10.setText(String.valueOf(letter));
                label10.setTextFill(Color.BLACK);
                label10.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label10);
            }
        }


        //creacion de los numeros parte superior
        for(int i=1;i<=30;i++){
            Label label11= new Label();
            label11.setLayoutX(25+(i*30));
            label11.setLayoutY(110);
            label11.setText(String.valueOf(i));
            label11.setTextFill(Color.BLACK);
            label11.setFont(Font.font(15));
            label11.setRotate(-90);
            AnchorPane.getChildren().addAll(label11);

        }

        //creacion de los numeros parte inferior
        for(int i=1;i<=30;i++){
            Label label12= new Label();
            label12.setLayoutX(25+(i*30));
            label12.setLayoutY(440);
            label12.setText(String.valueOf(i));
            label12.setTextFill(Color.BLACK);
            label12.setFont(Font.font(15));
            label12.setRotate(-90);
            AnchorPane.getChildren().addAll(label12);

        }


        //Creacion del protoboard
        int AuxSpace = 0;
        for (int i = 2; i < 32; i++){

            for (int j = 2; j< 16; j++){

                Circle circle = new Circle(i, j, 7);

                if(j==4 || j==9 || j==14){
                    AuxSpace+=20;
                }

                circle.setCenterX(i*30);
                circle.setCenterY(j*30+AuxSpace);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setOnMouseClicked(event -> ClickCirculo(circle));

                ArCircles[i][j] = circle;
                AnchorPane.getChildren().add(ArCircles[i][j]);

            }
            AuxSpace=0;
        }

    }

    public void Borrar_pieza(){
        if ((AnchorPane.getChildren().size()%514) != 0 ){
            switch (Historial.getLast()){
                case 1: // Cables
                    AnchorPane.getChildren().removeLast();
                    AnchorPane.getChildren().removeLast();
                    Historial.removeLast();
                    break;
                case 2: // Led
                    AnchorPane.getChildren().removeLast();
                    Historial.removeLast();
                    break;
                case 3: // Switch
                    AnchorPane.getChildren().removeLast();
                    Historial.removeLast();
                    break;
            }
        }
    }

    public void bloquear_boton(){
        iniciar.setDisable(true);
    }

    public void DibujoLed(){
        Arc semicirculo= new Arc();
        semicirculo.setCenterX(1200);
        semicirculo.setCenterY(500);
        semicirculo.setRadiusX(20);
        semicirculo.setRadiusY(20);
        semicirculo.setStartAngle(0);
        semicirculo.setLength(180);
        semicirculo.setType(ArcType.ROUND);
        semicirculo.setFill(Color.RED);

        Rectangle partebaja= new Rectangle();
        partebaja.setWidth(40);
        partebaja.setHeight(35);
        partebaja.setX(1180);
        partebaja.setY(500);
        partebaja.setFill(Color.RED);
        partebaja.setStroke(Color.RED);

        Line conector1= new Line();
        conector1.setStartX(1190);
        conector1.setStartY(537);
        conector1.setEndX(1190);
        conector1.setEndY(565);
        conector1.setStroke(Color.LIGHTGRAY);
        conector1.setStrokeWidth(3);

        Line conector2 = new Line();
        conector2.setStartX(1210);
        conector2.setStartY(537);
        conector2.setEndX(1210);
        conector2.setEndY(565);
        conector2.setStroke(Color.LIGHTGRAY);
        conector2.setStrokeWidth(3);

        Group led = new Group();

        led.getChildren().addAll(semicirculo,partebaja,conector2,conector1);
        AnchorPane.getChildren().add(led);
        Historial.add(2);
    }

    public void DibujoSwitch(){
        Rectangle base= new Rectangle();
        base.setWidth(30);
        base.setHeight(30);
        base.setX(1280);
        base.setY(500);
        base.setFill(Color.GRAY);
        base.setStroke(Color.BLACK);

        Circle circulo_arriba_der = new Circle();
        circulo_arriba_der.setCenterX(1305);
        circulo_arriba_der.setCenterY(505);
        circulo_arriba_der.setRadius(2);

        Circle circulo_abajo_der = new Circle();
        circulo_abajo_der.setCenterX(1305);
        circulo_abajo_der.setCenterY(525);
        circulo_abajo_der.setRadius(2);

        Circle circulo_arriba_izq = new Circle();
        circulo_arriba_izq.setCenterX(1285);
        circulo_arriba_izq.setCenterY(505);
        circulo_arriba_izq.setRadius(2);
        circulo_arriba_izq.setFill(Color.BLACK);

        Circle circulo_abajo_izq = new Circle();
        circulo_abajo_izq.setCenterX(1285);
        circulo_abajo_izq.setCenterY(525);
        circulo_abajo_izq.setRadius(2);
        circulo_abajo_izq.setFill(Color.BLACK);

        Circle circulo_Centro = new Circle();
        circulo_Centro.setCenterX(1295);
        circulo_Centro.setCenterY(515);
        circulo_Centro.setRadius(6);
        circulo_Centro.setFill(Color.BLACK);

        Group dib_switch = new Group();
        dib_switch.getChildren().addAll(base,circulo_Centro,circulo_arriba_der,circulo_abajo_der,circulo_abajo_izq,circulo_arriba_izq);
        AnchorPane.getChildren().add(dib_switch);
        Historial.add(3);
    }

    private int[] ClickCirculo(Circle circle){
        int Columna =0;
        int Fila;

        Columna = (((int) circle.getCenterX())- 30) / 30;


        System.out.println("Columna: " + Columna);
        //System.out.println(circle.getCenterY());
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
        return new int[]{Fila,Columna};

    }

    public void funcion (Rectangle rectangulo){
        System.out.println("AaA");
    }



    @FXML
    public void Cables(){
        Line cable1 = new Line();
        cable1.setStroke(Color.BLACK);
        cable1.setStrokeWidth(3);
        AnchorPane.getChildren().add(cable1);

        Circle circle =new Circle(7,Color.RED);
        circle.setOnMousePressed(event->{
            int[] InicioLinea= ClickCirculo(circle);
            cable1.setStartX(InicioLinea[1]*30+30);
            cable1.setStartY(InicioLinea[0]*30+60);
            System.out.println(InicioLinea);

        });

        circle.setOnMouseDragged(event->{
            int[] FinLinea =ClickCirculo(circle);
            cable1.setEndX(FinLinea[1]*30+30);
            cable1.setEndY(FinLinea[0]*30+60);
        });
        AnchorPane.getChildren().add(circle);
        Historial.add(1);

        }
    }




