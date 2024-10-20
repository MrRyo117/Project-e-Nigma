package com.example.projectenigma;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
    
    @FXML
    private Button btnLed;

    @FXML
    private Button btnCable;

    @FXML
    private Button btnSwitch;

    @FXML
    private Button btnChip;

    @FXML
    private Button btnResistencia;

    @FXML
    private Button Boton_encendido;

    @FXML
    private Button btnBorrar;

    private Protoboard Protoboard2 = new Protoboard();
    private Circle[][] ArCircles = new Circle[32][16];
    private ArrayList<Chip> chips = new ArrayList<>();

    private Motor motor;
    private int[][] registro = new int[4][2];

    public int tamano_filas = Protoboard2.protoboard.length;
    public int tamano_columnas = Protoboard2.protoboard[0].length;

    public int lastInt;
    public int lastMod;

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
            registro[0][1] = 15;
        } else if (registro[1][0] == 0) {

            registro[1][0] = op;
            registro[1][1] = 15;


        } else if (registro[2][0] == 0) {

            registro[2][0] = op;
            registro[2][1] = 15;
            int diff = lastInt-1 - ( 14-registro[0][1] ) - ( 14 * (30-registro[0][0] ) );

            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
        }else if (registro[3][0] == 0) {

            registro[3][0] = op;
            registro[3][1] = 15;

            int diff = lastInt-1 - ( 14-registro[1][1] ) - ( 14 * (30-registro[1][0] ) );

            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
        }else {

            int diff = lastInt-1 - ( 14-registro[0][1] ) - ( 14 * (30-registro[0][0] ) );


            if (((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.RED ) {
                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.BLACK);
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
            }

            diff = lastInt-1 - ( 14-registro[2][1] ) - ( 14 * (30-registro[2][0] ) );
            if (((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.RED ) {

                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
            }else {
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
            }




            for (int i = 0; i < 3; i++){

                registro[i][0] = registro[i+1][0];
                registro[i][1] = registro[i+1][1];
            }

            registro[3][0] = op;
            registro[3][1] = 15;

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

    public void CapturarMotor(int op , Circle motor){
        if (registro[0][0]==0){
            registro[0][0]=op;
            registro[0][1]=15;
        }else if(registro[1][0]==0){
            registro[2][0]=op;
            registro[2][1]=15;
        }else if(registro[2][0]==0){
            registro[2][0]=op;
            registro[2][1]=15;
            int diff =lastInt-1-(14-registro[0][1])-14*(30-registro[0][0]);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
        }else if(registro[3][0]==0){
            registro[3][0]=op;
            registro[3][1]=15;
            int diff =lastInt-1-(14-registro[1][1])-14*(30-registro[1][0]);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
        }else{
            int diff =lastInt-1-(14-registro[0][1])-14*(30-registro[0][0]);
            if (((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.RED ) {
                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.BLACK);
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
            }
            diff = lastInt-1 - ( 14-registro[2][1] ) - ( 14 * (30-registro[2][0] ) );
            if (((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() != Color.RED ) {

                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
            }else {
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
            }
            for(int j=0;j<3;j++){
                registro[j][0]=registro[j+1][0];
                registro[j][1]=registro[j+1][1];
            }
            registro[3][0]=op;
            registro[3][1]=15;
        }
        motor.setStroke(Color.GREEN);
        motor.setStrokeWidth(4);

    }

    public void DibujoLed(){

        double puntoX1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX();
        double puntoY1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY();
        double puntoX2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX();
        double puntoY2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY();

        if (puntoX1 > puntoX2){
            double aux = puntoX1;
            puntoX1 = puntoX2;
            puntoX2 = aux;

            aux = puntoY1;
            puntoY1 = puntoY2;
            puntoY2 = aux;

        }

        double DiffSpaceX = puntoX2 - puntoX1;

        Arc semicirculo= new Arc();
        Line conector1 = new Line();
        Line conector2 = new Line();
        if (puntoY1 == puntoY2){

            semicirculo.setCenterX(puntoX1 + (DiffSpaceX) / 2);
            semicirculo.setCenterY((puntoY1 * 2 - puntoY2) - 10);
            semicirculo.setRadiusX(10);
            semicirculo.setRadiusY(20);
            semicirculo.setStartAngle(0);
            semicirculo.setLength(180);
            semicirculo.setType(ArcType.ROUND);
            semicirculo.setFill(Color.WHITE);
            semicirculo.setStroke(Color.BLACK);


            conector1.setStartX(puntoX1);
            conector1.setStartY(puntoY1);
            conector1.setEndX(puntoX1+7);
            conector1.setEndY(puntoY1 - 10);
            conector1.setStroke(Color.BLACK);
            conector1.setStrokeWidth(3);


            conector2.setStartX(puntoX2);
            conector2.setStartY(puntoY2);
            conector2.setEndX(puntoX2-7);
            conector2.setEndY(puntoY2 - 10);
            conector2.setStroke(Color.BLACK);
            conector2.setStrokeWidth(3);
        }
        Cargar();
        Group led = new Group();

        led.getChildren().addAll(semicirculo, conector2, conector1);
        AnchorPane.getChildren().add(led);
        Historial.add(2);
    }
    @FXML
    public void DibujoResistencia(){
        Resistencia resistencia = new Resistencia();
        boolean negativoIzq = false;
        double puntoX1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX();
        double puntoY1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY();
        double puntoX2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX();
        double puntoY2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY();

        if (puntoX1 > puntoX2){
            double aux = puntoX1;
            puntoX1 = puntoX2;
            puntoX2 = aux;

            aux = puntoY1;
            puntoY1 = puntoY2;
            puntoY2 = aux;
            negativoIzq = true;

        }

        if (puntoY1 == puntoY2 && puntoX1+60 == puntoX2 ){
            Rectangle resistenciaD = new Rectangle(
                    puntoX1+10,
                    puntoY1-5,
                    40,
                    12
            );
            Line patitas = new Line(
              puntoX1,
              puntoY1,
              puntoX2,
              puntoY2
            );
            patitas.setStroke(Color.BLACK);
            resistenciaD.setFill(Color.BURLYWOOD);
            resistenciaD.setStroke(Color.BLACK);
            AnchorPane.getChildren().add(patitas);
            AnchorPane.getChildren().add(resistenciaD);
            Line cruz = new Line(
                    puntoX1+40,
                    puntoY1,
                    puntoX1+45,
                    puntoY1
            );
            cruz.setStrokeWidth(2);
            Line cruz2 = new Line(
                    puntoX1+42.5,
                    puntoY1-2.5,
                    puntoX1+42.5,
                    puntoY1+2.5
            );

            Line resta = new Line(
                    puntoX1+15,
                    puntoY1,
                    puntoX1+20,
                    puntoY1
            );

            if (negativoIzq){
                cruz2.setStartX(puntoX1+17.5);
                cruz2.setEndX(puntoX1+17.5);

            }

            AnchorPane.getChildren().add(cruz);
            AnchorPane.getChildren().add(cruz2);

            AnchorPane.getChildren().add(resta);

        }

        Cargar();


    }
    @FXML
    public void DibujoChip(){
        if ((registro[0][1] == 7 || registro[0][1] == 8) && (registro[1][1] == 7 || registro[1][1] == 8)  && (registro[2][1] == 7 || registro[2][1] == 8) && (registro[3][1] == 7 || registro[3][1] == 8)){
            Chip chip= new Chip();

            int coordY = registro[0][0];
            int coordX = registro[0][1];

            if (coordY > registro[1][0]){
                coordY = registro[1][0];
                coordX = registro[1][1];
            }else if (coordY > registro[2][0]){
                coordY = registro[2][0];
                coordX = registro[2][1];
            }

            if (coordY == registro[0][0] && coordX != registro[0][1]){

                if (coordX > registro[0][1]){
                    coordX = registro[0][1];
                }

            }else if (coordY == registro[1][0] && coordX != registro[1][1]){

                if (coordX > registro[1][1]){
                    coordX = registro[1][1];
                }

            }else if (coordY == registro[2][0] && coordX != registro[2][1]){

                if (coordX > registro[2][1]){
                    coordX = registro[2][1];
                }
            }else if (coordY == registro[3][0] && coordX != registro[3][1]){

                if (coordX > registro[3][1]){
                    coordX = registro[3][1];
                }
            }

            double diffX = -1;
            double diffY = -1;

            for(int i = 0 ; i <= 3 ; i++){

                if (coordX == registro[i][1] && coordY != registro[i][0]){

                    diffX = ArCircles[registro[i][0]][registro[i][1]].getCenterX() - ArCircles[coordY][coordX].getCenterX();
                }

                if (coordY == registro[i][0] && coordX != registro[i][1]){

                    diffY = ArCircles[registro[i][0]-1][registro[i][1]-1].getCenterY() - ArCircles[coordY-1][coordX-1].getCenterY();

                }
            }

            Rectangle cuerpoChip = new Rectangle(
                    ArCircles[coordY-1][coordX-1].getCenterX(),
                    ArCircles[coordY-1][coordX-1].getCenterY()+5,
                    diffX,
                    diffY-10
            );

            cuerpoChip.setFill(Color.BLACK);
            cuerpoChip.setStroke(Color.BLACK);
            AnchorPane.getChildren().add(cuerpoChip);

            for(int i = 0; (i*30) <= diffX; i++){
                Line patitaSup = new Line(
                        ArCircles[coordY-1][coordX-1].getCenterX()+30*i,
                        ArCircles[coordY-1][coordX-1].getCenterY(),
                        ArCircles[coordY-1][coordX-1].getCenterX()+30*i,
                        ArCircles[coordY-1][coordX-1].getCenterY()+5

                );


                Line patitaInf = new Line(
                        ArCircles[coordY-1][coordX-1].getCenterX()+30*i,
                        ArCircles[coordY-1][coordX-1].getCenterY()+diffY,
                        ArCircles[coordY-1][coordX-1].getCenterX()+30*i,
                        ArCircles[coordY-1][coordX-1].getCenterY()-5+diffY

                );
                if (i < 3) {
                    int diff = lastInt-1 - ( 14-coordX+1 ) - ( 14 * (30-coordY-i ) );

                    if ( ( (Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.BLUE ){
                        CargarPistas(coordY+i-1, 1, 2);
                    } else if ( ( (Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.RED ){
                        CargarPistas(coordY+i-1, -1, 2);
                    }

                }


                patitaSup.setStrokeWidth(2);
                patitaInf.setStrokeWidth(2);

                patitaSup.setStroke(Color.GRAY);
                patitaInf.setStroke(Color.GRAY);

                AnchorPane.getChildren().add(patitaSup);
                AnchorPane.getChildren().add(patitaInf);
            }

        }
        else {
            System.out.println("No ingresado dentro de los parametros");
        }


    }

    @FXML
    public void DibujoSwitch() {

       double punto_X1=ArCircles[registro[0][0]+1][registro[0][1]+1].getCenterX();
       double punto_Y1=ArCircles[registro[0][0]+1][registro[0][1]+1].getCenterY();
       double punto_X2=ArCircles[registro[1][0]+1][registro[1][1]+1].getCenterX();
       double punto_Y2=ArCircles[registro[1][0]+1][registro[1][1]+1].getCenterY();
       double punto_X3=ArCircles[registro[2][0]+1][registro[2][1]+1].getCenterX();
       double punto_Y3=ArCircles[registro[2][0]+1][registro[2][1]+1].getCenterY();
       double punto_X4=ArCircles[registro[3][0]+1][registro[3][1]+1].getCenterX();
       double punto_Y4=ArCircles[registro[3][0]+1][registro[3][1]+1].getCenterY();


       if(punto_X1>punto_X2){
           double aux=punto_X1;
           punto_X1=punto_X2;
           punto_X2=aux;

           aux=punto_Y1;
           punto_Y1=punto_Y2;
           punto_Y2=aux;
       }
       if(punto_X3>punto_X4){
           double aux =punto_X3;
           punto_X3=punto_X4;
           punto_X4=aux;

           aux= punto_Y3;
           punto_Y3=punto_Y4;
           punto_Y4=aux;
       }

       double rectX,rectY,Ancho,Alto;

       if(punto_X1<punto_X3){
           rectX= punto_X1-10;
       }else{
           rectX= punto_X3-10;
       }
       if (punto_Y1<punto_Y3){
           rectY=punto_Y1-10;
       }else{
           rectY=punto_Y3-10;
       }
        if (punto_X2 > punto_X1) {
            Ancho=(punto_X2-punto_X1)+20;
        }else{
            Ancho=(punto_X1-punto_X2)+20;
        }
        if(punto_Y4>punto_Y1){
            Alto=(punto_Y4-punto_Y1)+20;
        }else{
            Alto=(punto_Y1-punto_Y4)+20;
        }

        Rectangle base= new Rectangle();
        base.setX(rectX);
        base.setY(rectY);
        base.setWidth(Ancho);
        base.setHeight(Alto);
        base.setFill(Color.GRAY);
        base.setStroke(Color.BLACK);

        Circle circulo_Arriba_der= new Circle(punto_X2,punto_Y2,5);
        circulo_Arriba_der.setFill(Color.BLACK);
        Circle circulo_Abajo_der= new Circle(punto_X4,punto_Y4,5);
        circulo_Abajo_der.setFill(Color.BLACK);
        Circle circulo_Arriba_izq= new Circle(punto_X3,punto_Y3,5);
        circulo_Arriba_izq.setFill(Color.BLACK);
        Circle circulo_Abajo_izq= new Circle(punto_X1,punto_Y1,5);
        circulo_Abajo_izq.setFill(Color.BLACK);
        Circle circulo_Centro= new Circle((punto_X2+punto_X1)/2,(punto_Y1+punto_Y3)/2,15);
        circulo_Centro.setFill(Color.BLACK);

        Group Dibujo_Switch= new Group();
        Dibujo_Switch.getChildren().addAll(base,circulo_Arriba_der,circulo_Abajo_der,circulo_Arriba_izq,circulo_Abajo_izq,circulo_Centro);

        AnchorPane.getChildren().add(Dibujo_Switch);
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

        int diff;
        if (registro[0][0] == 0) {

            registro[0][0] = Columna;
            registro[0][1] = Fila;

        } else if (registro[1][0] == 0) {

            registro[1][0] = Columna;
            registro[1][1] = Fila;

        }else if (registro[2][0] == 0) {

            registro[2][0] = Columna;
            registro[2][1] = Fila;


            if (registro[0][1] != 15 ) {

                diff = lastInt-1 - ( 14-registro[0][1] ) - ( 14 * (30-registro[0][0] ) );

                ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.CHOCOLATE);
                ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);
            }
            else if(registro[0][0] == 33){
                ((Rectangle) AnchorPane.getChildren().get(1) ).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1) ).setStrokeWidth(3);
            }else if (registro[0][0] == 34){
                ((Rectangle) AnchorPane.getChildren().get(2) ).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2) ).setStrokeWidth(3);
            }



        } else if (registro[3][0] == 0) {

            registro[3][0] = Columna;
            registro[3][1] = Fila;

            if (registro[1][1] != 15){
                diff = lastInt-1 - ( 14-registro[1][1] ) - ( 14 * (30-registro[1][0] ) );

                ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(Color.CHOCOLATE);
                ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(3);

            }else if(registro[1][0] == 33){

                ((Rectangle) AnchorPane.getChildren().get(1) ).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1) ).setStrokeWidth(3);

            }else if (registro[1][0] == 34){
                ((Rectangle) AnchorPane.getChildren().get(2) ).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2) ).setStrokeWidth(3);
            }

        }else {

            if (registro[0][1] != 15) {
                //calculo para encontrar el punto dentro de la matriz del protoboard
                diff = lastInt - 1 - (14 - registro[0][1]) - (14 * (30 - registro[0][0]));
                Color colorDiff = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();

                if (((Circle) AnchorPane.getChildren().get(diff)).getStrokeWidth() == 3) {
                    if (colorDiff == Color.CHOCOLATE || colorDiff == Color.GREEN){
                        ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.BLACK);
                        ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
                    }else {
                        ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(2);
                    }
                }

            }

            if (registro[2][0] == 33) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);
            } else if (registro[2][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(3);
            }

            if (registro[0][0] == 33) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.BLACK);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(1);
            } else if (registro[0][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.BLACK);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(1);
            }


            for (int i = 0; i < 3; i++) {

                registro[i][0] = registro[i + 1][0];
                registro[i][1] = registro[i + 1][1];
            }

            registro[3][0] = Columna;
            registro[3][1] = Fila;



            for (int i = 0; i <= 1; i++) {

                if (registro[i][1] != 15) {

                    diff = lastInt - 1 - (14 - registro[i][1]) - (14 * (30 - registro[i][0]));
                    Color colorDiff = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();
                    if (colorDiff != Color.BLUE  && colorDiff != Color.RED && colorDiff != Color.BROWN){
                        ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);

                    }

                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
                }
            }
        }
        if (circle.getStroke() != Color.BLUE && circle.getStroke() != Color.RED && circle.getStroke() != Color.BROWN){
            circle.setStroke(Color.GREEN);
        }

        circle.setStrokeWidth(3);


    }

    @FXML
    public void Cables() {
        Line cable1 = null;
        int fila = 0, columna = 0, carga = 0;
        // los valores 33 y 34 son de la bateria

        if (registro[3][0] != 0){
        //Cableado dentro del protoboard
            if (registro[2][1] != 15 && registro[3][1] != 15) {
                cable1 = new Line(
    
                        ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                        ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY(),
                        ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                        ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()
                );
                Cargar();

            } else {  //Cableado del protoboard a la bateria


                if (registro[2][0] == 34) { //Conexion Bateria (Parte Positiva)
                    cable1 = new Line(

                            1200,
                            120,
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()

                    );
                    columna = registro[3][0];
                    fila = registro[3][1];

                    carga = 1;

                } else if (registro[3][0] == 34) { // Conexion Bateria (Parte Negativa)
                    cable1 = new Line(

                            1200,
                            120,
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY()

                    );
                    columna = registro[2][0];
                    fila = registro[2][1];

                    carga = 1;

                } else if (registro[2][0] == 33) {
                    cable1 = new Line(

                            1200,
                            380,
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()

                    );
                    columna = registro[3][0];
                    fila = registro[3][1];

                    carga = -1;

                } else if(registro[3][0]==33){
                    cable1 = new Line(

                            1200,
                            380,
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY()

                    );

                    columna = registro[2][0];
                    fila = registro[2][1];

                    carga = -1;
                }else if(registro[2][0]==35){  //Conexion positiva(Motor)
                    cable1=new Line(
                            500,
                            225,
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()
                    );
                    columna=registro[3][0];
                    fila=registro[3][1];
                    carga=1;

                } else if (registro[3][0]==35) {  //Conexion negativa(Motor)
                    cable1 = new Line(
                            500,
                            225,
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY()
                    );
                    columna = registro[2][0];
                    fila = registro[2][1];
                    carga = 1;
                }
                fila -= 1;
                columna -= 1;
                if (fila == 0 || fila == 1 || fila == 12 || fila == 13) {
                    CargasBuses(fila, carga);
                } else if (fila > 1 && fila <= 6){
                    CargarPistas(columna, carga, 1);
                } else if (fila > 6 && fila < 12){
                    CargarPistas(columna, carga, 2);
                }

                Protoboard2.CambiarCargaBus(fila, columna, carga);
                //Protoboard2.setCableDBateria();
            }
            cable1.setStroke(Color.BLACK);
            cable1.setStrokeWidth(3);

            Protoboard2.EstadoHoyito(fila,columna);
            AnchorPane.getChildren().add(cable1);

            Historial.add(1);
        }else {
            System.out.println(" Primero seleccione 4 elementos");
        }



    }

    public void Cargar(){
        int fila = registro[3][1]-1;
        int columna = registro[3][0]-1;
        int carga = 0;
        int diff = lastInt-1 - ( 14-registro[2][1] ) - ( 14 * (30-registro[2][0] ) );
        int diff2 = lastInt-1 - ( 14-registro[3][1] ) - ( 14 * (30-registro[3][0] ) );


        Color colorDiff1 = (Color) ( (Circle) AnchorPane.getChildren().get(diff) ).getStroke();
        if (colorDiff1 == Color.GREEN){
            int aux = diff;
            diff = diff2;
            diff2 = aux;
            colorDiff1 = (Color) ( (Circle) AnchorPane.getChildren().get(diff) ).getStroke();
            fila = registro[2][1]-1;
            columna = registro[2][0]-1;

        }
        Color colorDiff2 = (Color) ( (Circle) AnchorPane.getChildren().get(diff2) ).getStroke();
        if ((colorDiff1 != Color.GREEN || colorDiff2 != Color.GREEN) && ((colorDiff1 == Color.BLUE && colorDiff2 != Color.RED) || (colorDiff1 == Color.RED && colorDiff2 != Color.BLUE))) {
            if(((Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.BLUE){
                carga = -1;
            }
            if(((Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.RED){
                carga = 1;
            }
            if(((Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.BLUE || ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.RED ){
                if (fila == 0 || fila == 1 || fila == 12 || fila == 13) {
                    CargasBuses(fila, carga);
                } else if (fila > 1 && fila <= 6){
                    CargarPistas(columna, carga, 1);
                } else if (fila > 6 && fila < 12){
                    CargarPistas(columna, carga, 2);
                }
            }
        }else if(!(colorDiff1 == Color.GREEN || colorDiff2 == Color.GREEN)){
            if(((Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.BLUE || ((Circle) AnchorPane.getChildren().get(diff) ).getStroke() == Color.RED ){
                if (fila == 0 || fila == 1 || fila == 12 || fila == 13) {
                    CargasBuses(fila, 3);
                } else if (fila > 1 && fila <= 6){
                    CargarPistas(columna, 23, 1);
                } else if (fila > 6 && fila < 12){
                    CargarPistas(columna, 3, 2);
                }
            }
            pararTodo();
        }


    }

    public void CargasBuses(int fila, int carga){

        Color color = null;

        if (carga == -1){
            color = Color.BLUE;
        }else if (carga == 1){
            color = Color.RED;
        }else {
            color = Color.BROWN;
        }

        for (int i = 0; i < 30; i++){

            int diff = lastInt - (14*i) - (14-fila) ;

            ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(color);
            ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(2);
        }
    }

    public void CargarPistas(int columna, int carga, int op){

        Color color = null;

        if (carga == -1){
            color = Color.BLUE;
        }else if (carga == 1){
            color = Color.RED;
        }else {
            color = Color.BROWN;
        }

        if (op == 2){
            for (int i = 0; i < 5; i++){

                int diff = (lastInt - (i) - ( 14 * (29 - columna)) -3 );
                ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(color);
                ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(2);

            }
        }else {
            for (int i = 5; i < 10; i++){

                int diff = (lastInt - (i) - ( 14 * (29 - columna)) -3 );

                ((Circle) AnchorPane.getChildren().get(diff) ).setStroke(color);
                ((Circle) AnchorPane.getChildren().get(diff) ).setStrokeWidth(2);

            }
        }
    }

    @FXML
    public void reset(){

        for (int i = AnchorPane.getChildren().size(); i > lastMod; i--) {
            AnchorPane.getChildren().removeLast();
        }
        btnLed.setDisable(false);

        btnCable.setDisable(false);
        btnResistencia.setDisable(false);
        btnBorrar.setDisable(false);
        btnChip.setDisable(false);
        btnSwitch.setDisable(false);
        Boton_encendido.setDisable(false);

        colocarHoyitos();
    }

    public void colocarHoyitos(){

        for (int i = 0; i < ArCircles.length-2; i++){
            for ( int j = 0; j < ArCircles[i].length-2; j++){
                System.out.println(" AAaa");
                AnchorPane.getChildren().addAll(ArCircles[i][j]);
            }

        }
    }

    public void pararTodo(){
        //btnLed.setDisable(true);
        //btnCable.setDisable(true);
        //btnResistencia.setDisable(true);
        //btnBorrar.setDisable(true);
        //btnChip.setDisable(true);
        //btnSwitch.setDisable(true);
        //Boton_encendido.setDisable(true);
        System.out.println("-----> Corto en corto <------");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Protoboard2.CrearProtoboard(tamano_filas,tamano_columnas);


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
        System.out.println(AnchorPane.getChildren().size());

        Rectangle bateria2 = new Rectangle();
        bateria2.setWidth(180);
        bateria2.setHeight(130);
        bateria2.setX(1145);
        bateria2.setY(120);
        bateria2.setFill(Color.GOLD);
        bateria2.setStroke(Color.BLACK);
        bateria2.setOnMouseClicked(event -> capturaBateria(34, bateria2));
        AnchorPane.getChildren().addAll(bateria2);
        motor=new Motor(AnchorPane,this);
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
        lastMod = AnchorPane.getChildren().size();
        //Creacion del protoboard
        int AuxSpace = 0;
        for (int i = 0; i < 30; i++) {

            for (int j = 0; j < 14; j++) {

                Circle circle = new Circle(i, j, 7);

                //espacios extra
                if (j == 2 || j == 7 || j == 12) {
                    AuxSpace += 20;
                }

                circle.setCenterX((i+2) * 30);
                circle.setCenterY((j+2) * 30 + AuxSpace);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setOnMouseClicked(event -> ClickCirculo(circle));

                ArCircles[i][j] = circle;

                AnchorPane.getChildren().add(ArCircles[i][j]);
            }

            AuxSpace = 0;
        }

        lastInt = AnchorPane.getChildren().size(); // Tamano del anchorpane, cantidad de cosas que es 514 (DEBE ESTAR AL FINAL, PUES SI ESTA EN EL PRINCIPIO, NO HAY NADA; ENTONCES EL PROGRAMA SE MUERE)
    }
}



