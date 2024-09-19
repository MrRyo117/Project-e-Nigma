package com.example.projectenigma;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SampleController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    private Protoboard Protoboard2 = new Protoboard();
    private Circle[][] ArCircles = new Circle[32][16];

    private int[][] registro = new int[2][2];

    public int tamano_filas = Protoboard2.protoboard.length;
    public int tamano_columnas = Protoboard2.protoboard[0].length;

    public int lastInt;

    public ArrayList<Integer> Historial = new ArrayList<Integer>(); //cada numero representa una pieza: 1 cable, 2 led, 3 switch

    //Recurso a utilizar (Futuro)
    private int[][] Cargas = new int[32][16];

    //Funcion para identificar el lugar del hoyito dentreo de la matriz
    // Ademas deja registrado los ultimos 2 clickeados


    //Funcion para dejar en "registro" cuando se clickee la bateria
    //Utiliza numeros fuera de la matriz esperanda (matriz de hoyitos) para registrar como algo distinto
    public void capturaBateria (int op, Rectangle rectangle) {

        if (registro[0][0] == 0) {

            registro[0][0] = op;
            registro[0][1] = 14;



        } else if (registro[1][0] == 0) {

            registro[1][0] = op;
            registro[1][1] = 14;
        } else {

            int diff = lastInt-1 - ( 14-registro[0][1] ) - ( 14 * (30-registro[0][0] ) );

            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.BLACK);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(1);

            registro[0][0] = registro[1][0];
            registro[0][1] = registro[1][1];
            registro[1][0] = op;
            registro[1][1] = 14;

        }
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(4);
    }

    public void Borrar_pieza() {
        if ((AnchorPane.getChildren().size() % 514) != 0) {
            switch (Historial.getLast()) {
                case 1: // Cables
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

    public void DibujoLed(){

        double puntoX1 = ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterX();
        double puntoY1 = ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterY();
        double puntoX2 = ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterX();
        double puntoY2 = ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterY();

        if (puntoX1 > puntoX2){
            double aux = puntoX1;
            puntoX1 = puntoX2;
            puntoX2 = aux;

            aux = puntoY1;
            puntoY1 = puntoY2;
            puntoY2 = aux;

        }
        double DiffSpaceX = puntoX2 - puntoX1;
        double DiffSpaceY = puntoY2 - puntoY1;
        Arc semicirculo= new Arc();
        Rectangle partebaja = new Rectangle();
        Line conector1 = new Line();
        Line conector2 = new Line();
        if (puntoY1 == puntoY2){

            semicirculo.setCenterX(puntoX1 + (puntoX2 - puntoX1) / 2);
            semicirculo.setCenterY((puntoY1 * 2 - puntoY2) - 60);
            semicirculo.setRadiusX(20);
            semicirculo.setRadiusY(20);
            semicirculo.setStartAngle(0);
            semicirculo.setLength(180);
            semicirculo.setType(ArcType.ROUND);
            semicirculo.setFill(Color.RED);


            partebaja.setWidth(40);
            partebaja.setHeight(35);
            partebaja.setX(puntoX1 - 5);
            partebaja.setY((puntoY1 * 2 - puntoY2) - 60);
            partebaja.setFill(Color.RED);
            partebaja.setStroke(Color.RED);



            conector1.setStartX(puntoX1);
            conector1.setStartY(puntoY1);
            conector1.setEndX(puntoX1);
            conector1.setEndY(puntoY1 - 20);
            conector1.setStroke(Color.BLACK);
            conector1.setStrokeWidth(3);


            conector2.setStartX(puntoX2);
            conector2.setStartY(puntoY2);
            conector2.setEndX(puntoX2);
            conector2.setEndY(puntoY2 - 20);
            conector2.setStroke(Color.BLACK);
            conector2.setStrokeWidth(3);
        }



        Group led = new Group();

        led.getChildren().addAll(semicirculo, partebaja, conector2, conector1);
        AnchorPane.getChildren().add(led);
        Historial.add(2);
    }

    @FXML
    public void DibujoSwitch() {

        Rectangle base = new Rectangle();
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
        dib_switch.getChildren().addAll(base, circulo_Centro, circulo_arriba_der, circulo_abajo_der, circulo_abajo_izq, circulo_arriba_izq);
        AnchorPane.getChildren().add(dib_switch);
        Historial.add(3);
    }

    private void ClickCirculo(Circle circle) {
        int Columna = (((int) circle.getCenterX()) - 30) / 30;
        int Fila;

        if ((int) circle.getCenterY() <= 90) {
            Fila = ((int) circle.getCenterY() - 60) / 30;
        } else if ((int) circle.getCenterY() > 90 && (int) circle.getCenterY() <= 260) {
            Fila = ((int) circle.getCenterY() - 80) / 30;
        } else if ((int) circle.getCenterY() > 260 && (int) circle.getCenterY() <= 480) {
            Fila = ((int) circle.getCenterY() - 100) / 30;
        } else {
            Fila = ((int) circle.getCenterY() - 120) / 30;
        }

        Fila += 1;

        if (registro[0][0] == 0) {

            registro[0][0] = Columna;
            registro[0][1] = Fila;

        } else if (registro[1][0] == 0) {

            registro[1][0] = Columna;
            registro[1][1] = Fila;

        } else {

            if (registro[0][0] != 33 && registro[0][0] != 34) {
                //calvculo para encontrar el punto dentro de la matriz de la protoboard
                int diff = lastInt-1 - ( 14-registro[0][1] ) - ( 14 * (30-registro[0][0] ) );

                if ( ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.RED ){

                    ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.BLACK);
                    ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(1);
                }

            } else {
                int op;

                if(registro[0][0] == 33){
                    op = 1;
                }else {
                    op = 2;
                }

                ((Rectangle) AnchorPane.getChildren().get(op) ).setStroke(Color.BLACK);
                ((Rectangle) AnchorPane.getChildren().get(op) ).setStrokeWidth(1);

            }

            registro[0][0] = registro[1][0];
            registro[0][1] = registro[1][1];

            registro[1][0] = Columna;
            registro[1][1] = Fila;

        }

        circle.setStroke(Color.GREEN);
        circle.setStrokeWidth(3);
    }

    @FXML
    public void Cables() {
        Line cable1;

        if (registro[0][0] != 33 && registro[0][0] != 34 && registro[1][0] != 33 && registro[1][0] != 34) {

            cable1 = new Line(

                    ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterX(),
                    ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterY(),
                    ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterX(),
                    ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterY()

            );

        } else {

            int op, carga;

            if (registro[0][0] == 34) {

                cable1 = new Line(

                        1200,
                        120,
                        ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterX(),
                        ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterY()

                );

                op = registro[1][1];
                carga = 1;

            } else if (registro[1][0] == 34) {

                cable1 = new Line(

                        1200,
                        120,
                        ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterX(),
                        ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterY()

                );

                op = registro[0][1];
                carga = 1;

            } else if (registro[0][0] == 33) {

                cable1 = new Line(

                        1200,
                        380,
                        ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterX(),
                        ArCircles[registro[1][0] + 1][registro[1][1] + 1].getCenterY()

                );

                op = registro[1][1];
                carga = -1;

            } else {

                cable1 = new Line(

                        1200,
                        380,
                        ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterX(),
                        ArCircles[registro[0][0] + 1][registro[0][1] + 1].getCenterY()

                );

                op = registro[0][1];
                carga = -1;
            }
            op -=1;

            if (op == 1 || op == 2 || op == 13 || op == 14) {
                CargasBuses(op, carga);
            }

            else{
                CargarMedio(op, carga);
            }

            Protoboard2.CambiarCargaBus(op-1, 1, carga);
        }

        cable1.setStroke(Color.BLACK);
        cable1.setStrokeWidth(3);

        Protoboard2.EstadoHoyito(0,0);
        AnchorPane.getChildren().add(cable1);
        Historial.add(1);

    }

    public void CargasBuses(int op, int carga){

        Color color = null;

        if (carga == -1){
            color = Color.BLUE;
        }else if (carga == 1){
            color = Color.RED;
        }

        for (int i = 0; i < 30; i++){

            int diff = lastInt - (14*i) - (14-op) ;

            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(color);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
        }


    }

    public void CargarMedio(int op, int carga){

        Color color = null;

        if (carga == -1){
            color = Color.BLUE;
        }else if (carga == 1){
            color = Color.RED;
        }

        for (int i = 0; i < 30; i++){

            int diff = lastInt - (14*i) - (14-op) ;

            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(color);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Protoboard2.CrearProtoboard(Protoboard2.protoboard.length,Protoboard2.protoboard[0].length);

        //Creacion del rectangulo
        Rectangle Rectangulo = new Rectangle();
        Rectangulo.setWidth(1080);
        Rectangulo.setHeight(540);
        Rectangulo.setX(-30);
        Rectangulo.setY(-10);
        Rectangulo.setFill(Color.LIGHTGRAY);
        Rectangulo.setStroke(Color.BLACK);
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
        bateria.setOnMouseClicked(event -> capturaBateria(33, bateria));
        AnchorPane.getChildren().addAll(bateria);

        Rectangle bateria2 = new Rectangle();
        bateria2.setWidth(180);
        bateria2.setHeight(130);
        bateria2.setX(1145);
        bateria2.setY(120);
        bateria2.setFill(Color.GOLD);
        bateria2.setStroke(Color.BLACK);
        bateria2.setOnMouseClicked(event -> capturaBateria(34, bateria2));
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

        int correcion_posy = 0, salto = 0; //valores para corregir la disposicion visual de las letras

        //creacion del abcedario parte izquierda
        for (char letter = 'a'; letter <= 'j'; letter++) {
            if (letter == 'f') {
                //añade una separacion
                Label label9 = new Label();
                label9.setLayoutX(25);
                label9.setLayoutY(418 - (letter - 'a') * 30);
                label9.setText(String.valueOf(' '));
                label9.setTextFill(Color.BLACK);
                label9.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label9);

                //coloca la letra f
                Label label9_5 = new Label();
                label9_5.setLayoutX(25);
                label9_5.setLayoutY(429 - (letter - 'a' + 1) * 30);
                label9_5.setText(String.valueOf(letter));
                label9_5.setTextFill(Color.BLACK);
                label9_5.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label9_5);

                correcion_posy = 11;
                salto++;

            } else {
                Label label9 = new Label();
                label9.setLayoutX(25);
                label9.setLayoutY((418 + correcion_posy) - (letter - 'a' + salto) * 30);
                label9.setText(String.valueOf(letter));
                label9.setTextFill(Color.BLACK);
                label9.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label9);
            }
        }

        correcion_posy = 0;
        salto = 0;

        //creacion del abcedario parte derecha
        for (char letter = 'a'; letter <= 'j'; letter++) {
            if (letter == 'f') {
                //añade una separacion
                Label label10 = new Label();
                label10.setLayoutX(950);
                label10.setLayoutY(418 - (letter - 'a') * 30);
                label10.setText(String.valueOf(' '));
                label10.setTextFill(Color.BLACK);
                label10.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label10);

                //coloca la letra f
                Label label10_5 = new Label();
                label10_5.setLayoutX(950);
                label10_5.setLayoutY(429 - (letter - 'a' + 1) * 30);
                label10_5.setText(String.valueOf(letter));
                label10_5.setTextFill(Color.BLACK);
                label10_5.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label10_5);

                correcion_posy = 11;
                salto++;

            } else {
                Label label10 = new Label();
                label10.setLayoutX(950);
                label10.setLayoutY((418 + correcion_posy) - (letter - 'a' + salto) * 30);
                label10.setText(String.valueOf(letter));
                label10.setTextFill(Color.BLACK);
                label10.setFont(Font.font(15));
                AnchorPane.getChildren().addAll(label10);
            }
        }
        //creacion de los numeros parte superior
        for (int i = 1; i <= 30; i++) {
            Label label11 = new Label();
            label11.setLayoutX(25 + (i * 30));
            label11.setLayoutY(110);
            label11.setText(String.valueOf(i));
            label11.setTextFill(Color.BLACK);
            label11.setFont(Font.font(15));
            label11.setRotate(-90);
            AnchorPane.getChildren().addAll(label11);

        }

        //creacion de los numeros parte inferior
        for (int i = 1; i <= 30; i++) {
            Label label12 = new Label();
            label12.setLayoutX(25 + (i * 30));
            label12.setLayoutY(440);
            label12.setText(String.valueOf(i));
            label12.setTextFill(Color.BLACK);
            label12.setFont(Font.font(15));
            label12.setRotate(-90);
            AnchorPane.getChildren().addAll(label12);

        }
        //Creacion del protoboard
        int AuxSpace = 0;
        for (int i = 2; i < 32; i++) {

            for (int j = 2; j < 16; j++) {

                Circle circle = new Circle(i, j, 7);

                //espacios extra
                if (j == 4 || j == 9 || j == 14) {
                    AuxSpace += 20;
                }

                circle.setCenterX(i * 30);
                circle.setCenterY(j * 30 + AuxSpace);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setOnMouseClicked(event -> ClickCirculo(circle));

                ArCircles[i][j] = circle;

                AnchorPane.getChildren().add(ArCircles[i][j]);


            }
            AuxSpace = 0;
        }

        lastInt = AnchorPane.getChildren().size();
    }
}



