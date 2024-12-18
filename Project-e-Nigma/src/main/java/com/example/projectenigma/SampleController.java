package com.example.projectenigma;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.Node;

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
    private Button btnChipAND;
    @FXML
    private Button btnChipOR;
    @FXML
    private Button btnChipNOT;

    @FXML
    private Button btnSwich8P;

    @FXML
    private Button btnResistencia;

    @FXML
    private Button Boton_encendido;

    @FXML
    private Button btnReset;

    @FXML
    private Button cambiar_carga_motor;

    @FXML
    private TextField ohm;

    private Protoboard Protoboard2 = new Protoboard();
    private Circle[][] ArCircles = new Circle[36][16];
    private ArrayList<Chip> chips = new ArrayList<>();
    private ArrayList<Cable> cables = new ArrayList<>();


    private int[][] registro = new int[4][2];

    public int tamano_filas = Protoboard2.protoboard.length;
    public int tamano_columnas = Protoboard2.protoboard[0].length;

    public int lastInt;
    public int lastMod;

    public ArrayList<Integer> Historial = new ArrayList<Integer>(); //cada numero representa una pieza: 1 cable, 2 led, 3 switch, 4 resistencia, 5 chip, 6 swich8P

    //Recurso a utilizar (Futuro)
    private boolean[][] status_hoyitos = new boolean[36][16];

    //Funcion para identificar el lugar del hoyito dentreo de la matriz
    // Además deja registrado los ultimos 2 clickeados


    //Funcion para dejar en "registro" cuando se clickee la bateria
    //Utiliza numeros fuera de la matriz esperanda (matriz de hoyitos) para registrar como algo distinto
    public void capturaBateria(int op, Rectangle rectangle) {

        if (registro[0][0] == 0) {

            registro[0][0] = op;
            registro[0][1] = 15;
        } else if (registro[1][0] == 0) {

            registro[1][0] = op;
            registro[1][1] = 15;


        } else if (registro[2][0] == 0) {

            registro[2][0] = op;
            registro[2][1] = 15;
            int diff = ubicador(registro[0][1], registro[0][0]);
            ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);

        } else if (registro[3][0] == 0) {

            registro[3][0] = op;
            registro[3][1] = 15;

            int diff = ubicador(registro[1][1], registro[1][0]);
            ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
        } else {


            int diff = ubicador(registro[0][1], registro[0][0]);
            if (registro[0][1] != 15) {
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.RED) {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.BLACK);
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);

                }
            } else if (registro[0][0] == 33) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);
            } else if (registro[0][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(3);
            }

            diff = ubicador(registro[2][1], registro[2][0]);
            System.out.println("HERE");

            if (registro[2][1] != 15) {
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.RED) {

                    ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
                } else {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
                }
            } else if (registro[2][0] == 33) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);
            } else if (registro[2][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(3);
            }

            for (int i = 0; i < 3; i++) {

                registro[i][0] = registro[i + 1][0];
                registro[i][1] = registro[i + 1][1];
            }

            registro[3][0] = op;
            registro[3][1] = 15;

        }
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(4);
    }


    public void Borrar_pieza(int indice) {
        AnchorPane.getChildren().remove(indice);
    }

    public void CapturarMotor(int op, Rectangle rectangle) {
        if (registro[0][0] == 0) {

            registro[0][0] = op;
            registro[0][1] = 15;
        } else if (registro[1][0] == 0) {

            registro[1][0] = op;
            registro[1][1] = 15;


        } else if (registro[2][0] == 0) {

            registro[2][0] = op;
            registro[2][1] = 15;
            int diff = ubicador(registro[0][1], registro[0][0]);
            ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);

        } else if (registro[3][0] == 0) {

            registro[3][0] = op;
            registro[3][1] = 15;

            int diff = ubicador(registro[1][1], registro[1][0]);
            ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
            ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
        } else {


            int diff = ubicador(registro[0][1], registro[0][0]);
            if (registro[0][1] != 15) {
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.RED) {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.BLACK);
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);

                }
            } else if (registro[0][0] == 35) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);
            }

            diff = ubicador(registro[2][1], registro[2][0]);
            System.out.println("HERE");

            if (registro[2][1] != 15) {
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.BLUE && ((Circle) AnchorPane.getChildren().get(diff)).getStroke() != Color.RED) {

                    ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
                } else {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
                }
            } else if (registro[2][0] == 35) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);
            }
            for (int i = 0; i < 3; i++) {

                registro[i][0] = registro[i + 1][0];
                registro[i][1] = registro[i + 1][1];
            }

            registro[3][0] = op;
            registro[3][1] = 15;

        }
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(4);
    }

    public void DibujoLed() {

        double puntoX1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX();
        double puntoY1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY();
        double puntoX2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX();
        double puntoY2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY();
        int diff1 = ubicador(registro[2][1], registro[2][0]);
        int diff2 = ubicador(registro[3][1], registro[3][0]);

        if (diff1 == diff2){
            System.out.println("no se pueden poner en el mismo lugar");
        }else {

            if (!status_hoyitos[registro[3][0]][registro[3][1]] && !status_hoyitos[registro[2][0]][registro[2][1]]) {
                status_hoyitos[registro[3][0]][registro[3][1]] = true;
                status_hoyitos[registro[2][0]][registro[2][1]] = true;


                if (puntoX1 > puntoX2) {
                    double aux = puntoX1;
                    puntoX1 = puntoX2;
                    puntoX2 = aux;

                    aux = puntoY1;
                    puntoY1 = puntoY2;
                    puntoY2 = aux;

                }
                double DiffSpaceX = puntoX2 - puntoX1;

                Arc semicirculo = new Arc();
                Line conector1 = new Line();
                Line conector2 = new Line();
                if (puntoY1 == puntoY2) {

                    semicirculo.setCenterX(puntoX1 + (DiffSpaceX) / 2);
                    semicirculo.setCenterY((puntoY1 * 2 - puntoY2) - 10);
                    semicirculo.setRadiusX(10);
                    semicirculo.setRadiusY(20);
                    semicirculo.setStartAngle(0);
                    semicirculo.setLength(180);
                    semicirculo.setType(ArcType.ROUND);

                    Color Polaridad_positiva = (Color) ((Circle) AnchorPane.getChildren().get(diff1)).getStroke();
                    Color Polaridad_negativa = (Color) ((Circle) AnchorPane.getChildren().get(diff2)).getStroke();

                    // Condicional que revisa la polaridad del led
                    if (Polaridad_positiva == Color.RED && Polaridad_negativa == Color.BLUE) {
                        semicirculo.setFill(Color.RED);
                    } else if (Polaridad_positiva == Color.YELLOW && Polaridad_negativa == Color.BLUEVIOLET) {
                        semicirculo.setFill(Color.LIGHTPINK);
                    } else if (Polaridad_negativa == Color.YELLOW && Polaridad_positiva == Color.BLUEVIOLET) {
                        semicirculo.setFill(Color.BLACK);
                        System.out.println("Polaridad invertida");
                    } else if (Polaridad_negativa == Color.RED && Polaridad_positiva == Color.BLUE) {
                        semicirculo.setFill(Color.BLACK);
                        System.out.println("Polaridad invertida");
                    } else {
                        semicirculo.setFill(Color.WHITE);
                    }

                    semicirculo.setStroke(Color.WHITE);


                    conector1.setStartX(puntoX1);
                    conector1.setStartY(puntoY1);
                    conector1.setEndX(puntoX1 + 7);
                    conector1.setEndY(puntoY1 - 10);
                    conector1.setStroke(Color.BLACK);
                    conector1.setStrokeWidth(3);


                    conector2.setStartX(puntoX2);
                    conector2.setStartY(puntoY2);
                    conector2.setEndX(puntoX2 - 7);
                    conector2.setEndY(puntoY2 - 10);
                    conector2.setStroke(Color.BLACK);
                    conector2.setStrokeWidth(3);
                }

                Group led = new Group();

                led.getChildren().addAll(semicirculo, conector2, conector1);
                AnchorPane.getChildren().add(led);
                Historial.add(2);

                // cada vez que se haga click en un led, se borra independiente del orden colocado
                led.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        Node presionado = (Node) event.getSource();
                        int indice = AnchorPane.getChildren().indexOf(presionado);
                        Borrar_pieza(indice);
                    }
                });
            }
        }
    }

    // Coloca un Tooltip que me dice que es el objeto indicado
    public Tooltip Texto_Aparente(String texto){
        Tooltip mensaje = new Tooltip(texto);
        mensaje.setStyle("-fx-font-size: 12");
        return mensaje;
    }

    @FXML
    public void DibujoResistencia(){
        try {
            if (!status_hoyitos[registro[3][0]][registro[3][1]] && !status_hoyitos[registro[2][0]][registro[2][1]]) {
                status_hoyitos[registro[3][0]][registro[3][1]] = true;
                status_hoyitos[registro[2][0]][registro[2][1]] = true;
                String ohmTxt = ohm.getText();
                int ohmInt = Integer.parseInt(ohmTxt);
                Resistencia resistencia = new Resistencia(ohmInt);

                boolean negativoP1 = false;
                boolean negativoP2 = false;
                double puntoX1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX();
                double puntoY1 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY();
                double puntoX2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX();
                double puntoY2 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY();
                if (puntoX1 > puntoX2) {
                    double aux = puntoX1;
                    puntoX1 = puntoX2;
                    puntoX2 = aux;

                    aux = puntoY1;
                    puntoY1 = puntoY2;
                    puntoY2 = aux;
                    negativoP1 = true;


                }
                Group Agrupar_Dibujo_Resistencia = null;
                boolean buildResistencia = false;
                if (puntoY1 == puntoY2 && puntoX1 + 60 == puntoX2) {
                    Rectangle resistenciaD = new Rectangle(
                            puntoX1 + 10,
                            puntoY1 - 5,
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

                    Line cruz = new Line(
                            puntoX1 + 40,
                            puntoY1,
                            puntoX1 + 45,
                            puntoY1
                    );
                    cruz.setStrokeWidth(2);
                    Line cruz2 = new Line(
                            puntoX1 + 42.5,
                            puntoY1 - 2.5,
                            puntoX1 + 42.5,
                            puntoY1 + 2.5
                    );

                    Line resta = new Line(
                            puntoX1 + 15,
                            puntoY1,
                            puntoX1 + 20,
                            puntoY1
                    );

                    if (negativoP1) {
                        cruz2.setStartX(puntoX1 + 17.5);
                        cruz2.setEndX(puntoX1 + 17.5);

                    }


                    //Agrupa todos los elementos graficos que componen la resistencia en uno, para que sea mas facil su manejo
                    Agrupar_Dibujo_Resistencia = new Group();


                    Agrupar_Dibujo_Resistencia.getChildren().add(patitas);
                    Agrupar_Dibujo_Resistencia.getChildren().add(resistenciaD);
                    Agrupar_Dibujo_Resistencia.getChildren().add(cruz);
                    Agrupar_Dibujo_Resistencia.getChildren().add(cruz2);
                    Agrupar_Dibujo_Resistencia.getChildren().add(resta);

                    Tooltip.install(resistenciaD, Texto_Aparente("Resistencia : "+ohmTxt+"Ω"));

                    AnchorPane.getChildren().add(Agrupar_Dibujo_Resistencia);
                    Historial.add(4);
                    buildResistencia = true;
                }
                else if (puntoX1 == puntoX2 && puntoY1 + 60 == puntoY2 || puntoY1 - 60 == puntoY2 || puntoY1 + 50 == puntoY2 || puntoY1 - 50 == puntoY2 ){

                    if (puntoY1 > puntoY2){
                        double aux = puntoY1;
                        puntoY1 = puntoY2;
                        puntoY2 = aux;
                        negativoP2 = true;
                    }
                    System.out.println("Vertical");
                    Rectangle resistenciaD = new Rectangle(
                            puntoX1 - 6,
                            puntoY1 + 10,
                            12,
                            40
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

                    Line cruz = new Line(
                            puntoX1 + 2.5,
                            puntoY1 + 20,
                            puntoX1 - 2.5,
                            puntoY1 + 20
                    );
                    Line cruz2 = new Line(
                            puntoX1 ,
                            puntoY1 + 17.5,
                            puntoX1,
                            puntoY1 + 22.5
                    );
                    Line resta = new Line(
                            puntoX1 + 2.5,
                            puntoY1 + 40,
                            puntoX1 - 2.5,
                            puntoY1 + 40
                    );

                    if (!negativoP2) {
                        cruz2.setStartY(puntoY1 + 37.5);
                        cruz2.setEndY(puntoY1 + 42.5);
                    }


                    //Agrupa todos los elementos graficos que componen la resistencia en uno, para que sea mas facil su manejo
                    Agrupar_Dibujo_Resistencia = new Group();


                    Agrupar_Dibujo_Resistencia.getChildren().add(patitas);
                    Agrupar_Dibujo_Resistencia.getChildren().add(resistenciaD);
                    Agrupar_Dibujo_Resistencia.getChildren().add(cruz);
                    Agrupar_Dibujo_Resistencia.getChildren().add(cruz2);
                    Agrupar_Dibujo_Resistencia.getChildren().add(resta);

                    Tooltip.install(resistenciaD, Texto_Aparente("Resistencia : "+ohmTxt+"Ω"));

                    AnchorPane.getChildren().add(Agrupar_Dibujo_Resistencia);
                    Historial.add(4);
                    buildResistencia = true;
                }
                if (buildResistencia){
                    int diff = ubicador(registro[2][1], registro[2][0]);
                    Color color = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();

                    diff = ubicador(registro[3][1], registro[3][0]);
                    Color color2 = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();
                    if (((negativoP1 && (color == Color.BLUE)) || color2 == Color.RED)) {
                        System.out.println("Instalado correctamente");
                        Cargar(true);
                    }
                    else if (((!negativoP2 && (color == Color.BLUE)) || color2 == Color.RED)){
                        System.out.println("Instalado correctamente en vertical");
                        Cargar(true);
                    }
                    // cada vez que se haga click en un Agrupar_Dibujo_Resistencia, se borra independiente del orden colocado
                    Agrupar_Dibujo_Resistencia.setOnMouseClicked((event) -> {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            Node presionado = (Node) event.getSource();
                            int indice = AnchorPane.getChildren().indexOf(presionado);
                            Borrar_pieza(indice);
                        }
                    });
                }

            }

        } catch (NumberFormatException e) {

            System.out.println("El texto no es un número válido.");
        }

    }

    @FXML
    public void Eliminar_ocupa(){
        status_hoyitos[registro[3][0]][registro[3][1]] = false;
        status_hoyitos[registro[2][0]][registro[2][1]] = false;
        status_hoyitos[registro[1][0]][registro[1][1]] = false;
        status_hoyitos[registro[0][0]][registro[0][1]] = false;
    }

    @FXML
    public void DibujoSwitch8Pines(){
        double diffX = 0 ;
        int coordY = 0;
        int coordX = 0;

        //Arreglo con el cual uno puede saber cuál de los 8 switches esta siendo presionado
        boolean[] Pasa_corriente = new boolean[8];
        int[] Memoria_estado_original = new int[8];   //Guarda el estado original con el que llega la corriente (1 para cuando la carga venia de abajo, 2 para cuando la carga venia de arriba y -1 para cuando haya cortocircuito)

        //Este condicional es para saber si los puntos seleccionados estan al rededor del surco del protoboard
        if ((registro[0][1] == 7 || registro[0][1] == 8)
                && (registro[1][1] == 7 || registro[1][1] == 8)
                && (registro[2][1] == 7 || registro[2][1] == 8)
                && (registro[3][1] == 7 || registro[3][1] == 8)) {

            diffX = -1;
            double diffY = -1;

            coordY = registro[0][0];
            coordX = registro[0][1];

            /* Condicional el cual me guarda en las variables coordY, coordX cual es el punto menor dentro del protoboard
            Esto sirve para la creacion de la figura, pues permite que siempre se sepa cual es la esquina en donde se debe
            empezar a dibujar*/
            if (coordY > registro[1][0]) {
                coordY = registro[1][0];
                coordX = registro[1][1];
            } else if (coordY > registro[2][0]) {
                coordY = registro[2][0];
                coordX = registro[2][1];
            }

            /*Grupo de condicionales quienes comprueban que las coordenadas X correspondan con las coordenadas Y*/
            if (coordY == registro[0][0] && coordX != registro[0][1]) {

                if (coordX > registro[0][1]) {
                    coordX = registro[0][1];
                }

            } else if (coordY == registro[1][0] && coordX != registro[1][1]) {

                if (coordX > registro[1][1]) {
                    coordX = registro[1][1];
                }

            } else if (coordY == registro[2][0] && coordX != registro[2][1]) {

                if (coordX > registro[2][1]) {
                    coordX = registro[2][1];
                }

            } else if (coordY == registro[3][0] && coordX != registro[3][1]) {

                if (coordX > registro[3][1]) {
                    coordX = registro[3][1];
                }

            }

            /*Ciclo para saber el surco de las coordenadas X e Y*/
            for (int i = 0; i <= 3; i++) {

                if (coordX == registro[i][1] && coordY != registro[i][0]) {

                    diffX = ArCircles[registro[i][0]][registro[i][1]].getCenterX() - ArCircles[coordY][coordX].getCenterX();
                }

                if (coordY == registro[i][0] && coordX != registro[i][1]) {

                    diffY = ArCircles[registro[i][0] - 1][registro[i][1] - 1].getCenterY() - ArCircles[coordY - 1][coordX - 1].getCenterY();

                }
            }

            //Agrupar_Dibujo_Swich8P se encarga de encapsular todas las figuras que conforman el swich en un solo objeto.
            Group Agrupar_Dibujo_Swich8P = new Group();
            Group Agrupar_Dibujo_interruptor = new Group();

            Rectangle cuerpoSwich8P = new Rectangle(
                    ArCircles[coordY - 1][coordX - 1].getCenterX() - 10,
                    ArCircles[coordY - 1][coordX - 1].getCenterY() + 5,
                    diffX + 20,
                    diffY - 10
            );


            cuerpoSwich8P.setStroke(Color.BLACK);
            cuerpoSwich8P.setStrokeWidth(2);
            cuerpoSwich8P.setFill(Color.RED);


            Agrupar_Dibujo_Swich8P.getChildren().add(cuerpoSwich8P);

            if ((((int) diffX / 30) + 1) % 7 == 1) {
                for (int i = 0; (i * 30) <= diffX; i++) {


                    Line patitaSup = new Line(
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY(),
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() + 5

                    );

                    Line patitaInf = new Line(
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() + diffY,
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() - 5 + diffY

                    );

                    Rectangle interruptor = new Rectangle(
                            (ArCircles[coordY - 1][coordX - 1].getCenterX() - 5) + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() + 18,
                            10,
                            diffY - 37
                    );


                    interruptor.setStrokeWidth(1);

                    patitaSup.setStrokeWidth(2);
                    patitaInf.setStrokeWidth(2);

                    patitaSup.setStroke(Color.GRAY);
                    patitaInf.setStroke(Color.GRAY);

                    interruptor.setStroke(Color.BLACK);
                    interruptor.setFill(Color.WHITE);

                    Agrupar_Dibujo_Swich8P.getChildren().add(patitaInf);
                    Agrupar_Dibujo_Swich8P.getChildren().add(patitaSup);
                    Agrupar_Dibujo_interruptor.getChildren().add(interruptor);

                    Historial.add(6);

                    int finalCoordY = coordY;
                    int finalCoordX = coordX;
                    double finalDiffX = diffX;


                    interruptor.setOnMouseClicked((event) -> {
                        if (event.getButton() == MouseButton.PRIMARY){

                            Node presionado = (Node) event.getSource();
                            int indice = Agrupar_Dibujo_interruptor.getChildren().indexOf(presionado);


                            int diffArriba = ubicador(finalCoordX, (finalCoordY+indice));


                            int diffAbajo = ubicador(finalCoordX + 1, (finalCoordY+indice));

                            System.out.println("diff ariiba: "+ diffArriba+"\ndiff abajo: "+diffAbajo);


                            //Condicional que revisa: que pasa si hay corriente arriba y abajo opuestas. La respuesta es que nada porque se cierra el circuito nomas
                            if (((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.RED) && (((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLUE))
                                    || ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLUE) && (((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.RED)) ){
                                interruptor.setFill(Color.BLACK);
                                Memoria_estado_original[indice] = -1;

                            }else {

                                //Condicional que revisa: (si no hay carga arriba y no ha sido precionado) o si ya sabemos cual era el estado original
                                if(((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLACK) && Memoria_estado_original[indice] == 0) || Memoria_estado_original[indice] == 1){

                                    Memoria_estado_original[indice] = 1;

                                    //Pasa la carga de abajo hacia arriba
                                    if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.RED) ){
                                        CargarPistas(finalCoordY-1+indice, 1, 1);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 1);
                                        }
                                    } else if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLUE) ){
                                        CargarPistas(finalCoordY-1+indice, -1, 1);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 1);
                                        }
                                    } else if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.YELLOW) ) {
                                        CargarPistas(finalCoordY-1+indice, 2, 1);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 1);
                                        }
                                    } else if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLUEVIOLET)) {
                                        CargarPistas(finalCoordY-1+indice, -2, 1);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 1);
                                        }
                                    }

                                } else if (((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLACK) && Memoria_estado_original[indice] == 0)|| Memoria_estado_original[indice] == 2 ) {
                                    Memoria_estado_original[indice] = 2;

                                    //Pasa la carga de arriba hacia abajo
                                    if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.RED) ){
                                        CargarPistas(finalCoordY-1+indice, 1, 2);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 2);
                                        }
                                    } else if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLUE) ){
                                        CargarPistas(finalCoordY-1+indice, -1, 2);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 2);
                                        }
                                    } else if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.YELLOW)) {
                                        CargarPistas(finalCoordY-1+indice, 2, 2);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 2);
                                        }
                                    } else if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLUEVIOLET) ) {
                                        CargarPistas(finalCoordY-1+indice, -2, 2);
                                        if (Pasa_corriente[indice]){
                                            CargarPistas(finalCoordY-1+indice, 0, 2);
                                        }
                                    }
                                }

                            }

                            if (Memoria_estado_original[indice]!=-1){
                                if (!Pasa_corriente[indice]){
                                    interruptor.setFill(Color.GRAY);
                                }else{
                                    interruptor.setFill(Color.WHITE);
                                }

                                Pasa_corriente[indice]=!Pasa_corriente[indice];
                            }


                        }
                    });

                }
                Agrupar_Dibujo_Swich8P.getChildren().addAll(Agrupar_Dibujo_interruptor);
                // cada vez que se haga click en un Agrupar_Dibujo_Chip, se borra independiente del orden colocado
                AnchorPane.getChildren().add(Agrupar_Dibujo_Swich8P);
                Agrupar_Dibujo_Swich8P.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        Node presionado = (Node) event.getSource();
                        int indice = AnchorPane.getChildren().indexOf(presionado);
                        Borrar_pieza(indice);
                    }
                });



            } else{
                System.out.println("Seleccionado una cantidad de hoyitos no admisibles");
            }

        } else {
            System.out.println("No ingresado dentro de los parametros");
        }

    }

    public int[] DibujoChip(int tipoChip, String texto){
        double diffX = 0 ;
        int coordY = 0;
        int coordX = 0;

        if ((registro[0][1] == 7 || registro[0][1] == 8)
                && (registro[1][1] == 7 || registro[1][1] == 8)
                && (registro[2][1] == 7 || registro[2][1] == 8)
                && (registro[3][1] == 7 || registro[3][1] == 8)) {
            diffX = -1;
            double diffY = -1;

            Chip chip = new Chip();

            coordY = registro[0][0];
            coordX = registro[0][1];

            if (coordY > registro[1][0]) {
                coordY = registro[1][0];
                coordX = registro[1][1];
            } else if (coordY > registro[2][0]) {
                coordY = registro[2][0];
                coordX = registro[2][1];
            }

            if (coordY == registro[0][0] && coordX != registro[0][1]) {

                if (coordX > registro[0][1]) {
                    coordX = registro[0][1];
                }

            } else if (coordY == registro[1][0] && coordX != registro[1][1]) {

                if (coordX > registro[1][1]) {
                    coordX = registro[1][1];
                }

            } else if (coordY == registro[2][0] && coordX != registro[2][1]) {

                if (coordX > registro[2][1]) {
                    coordX = registro[2][1];
                }
            } else if (coordY == registro[3][0] && coordX != registro[3][1]) {

                if (coordX > registro[3][1]) {
                    coordX = registro[3][1];
                }
            }

            for (int i = 0; i <= 3; i++) {

                if (coordX == registro[i][1] && coordY != registro[i][0]) {

                    diffX = ArCircles[registro[i][0]][registro[i][1]].getCenterX() - ArCircles[coordY][coordX].getCenterX();
                }

                if (coordY == registro[i][0] && coordX != registro[i][1]) {

                    diffY = ArCircles[registro[i][0] - 1][registro[i][1] - 1].getCenterY() - ArCircles[coordY - 1][coordX - 1].getCenterY();

                }
            }
            //Agrupar_Dibujo_Chip se encarga de encapsular todas las figuras que conforman el chip en un solo objeto.
            Group Agrupar_Dibujo_Chip = new Group();
            if (((((int) diffX / 30) + 1) % 3 == 1 && tipoChip == 1) || ((((int) diffX) / 30) + 1) % 2 == 1 && tipoChip == 2 && diffX + 1 != 0) {
                Rectangle cuerpoChip = new Rectangle(
                        ArCircles[coordY - 1][coordX - 1].getCenterX(),
                        ArCircles[coordY - 1][coordX - 1].getCenterY() + 5,
                        diffX,
                        diffY - 10
                );

                cuerpoChip.setFill(Color.BLACK);
                cuerpoChip.setStroke(Color.BLACK);
                Tooltip.install(cuerpoChip,Texto_Aparente(texto));
                Agrupar_Dibujo_Chip.getChildren().add(cuerpoChip);


                for (int i = 0; (i * 30) <= diffX; i++) {
                    Line patitaSup = new Line(
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY(),
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() + 5

                    );

                    Line patitaInf = new Line(
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() + diffY,
                            ArCircles[coordY - 1][coordX - 1].getCenterX() + 30 * i,
                            ArCircles[coordY - 1][coordX - 1].getCenterY() - 5 + diffY

                    );

                    patitaSup.setStrokeWidth(2);
                    patitaInf.setStrokeWidth(2);

                    patitaSup.setStroke(Color.GRAY);
                    patitaInf.setStroke(Color.GRAY);


                    Agrupar_Dibujo_Chip.getChildren().add(patitaInf);
                    Agrupar_Dibujo_Chip.getChildren().add(patitaSup);



                }
                Historial.add(5);
                // cada vez que se haga click en un Agrupar_Dibujo_Chip, se borra independiente del orden colocado
                AnchorPane.getChildren().add(Agrupar_Dibujo_Chip);
                Agrupar_Dibujo_Chip.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        Node presionado = (Node) event.getSource();
                        int indice = AnchorPane.getChildren().indexOf(presionado);
                        Borrar_pieza(indice);
                    }
                });
            } else {
                System.out.println("Seleccionado una cantidad de hoyitos no admisibles");
            }

        } else {
            System.out.println("No ingresado dentro de los parametros");
        }

        return new int[] {(int) diffX, coordX, coordY} ;
    }


    @FXML
    public void DibujoChipAND(){
        int[] req = DibujoChip(1, "CHIP AND");
        int diffPrimero = ubicador(req[1], req[2]);
        int reqAux= req[2] + req[0]/30 ;
        int diffUltimo = ubicador(req[1]+1, reqAux);
        if((( req[0]/30)+1) % 3 == 1 && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.RED) && ( (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.GREEN)) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.BLUE) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.CHOCOLATE)) {
            for (int i = 0; (i * 30) < req[0]; i = i + 3) {

                int diffArriba = ubicador(req[1], req[2] + i + 1);
                int diffArribaAux = ubicador(req[1], req[2] + i + 2);

                int diffAbajo = ubicador(req[1] + 1, req[2] + i);
                int diffAbajoAux = ubicador(req[1] + 1, req[2] + i + 1);

                if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.RED) && (((Circle) AnchorPane.getChildren().get(diffArribaAux)).getStroke() == Color.RED) &&(((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.RED) ) {
                    CargarPistas(req[2] + i + 2, 1, 1);
                }

                if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.RED) && (((Circle) AnchorPane.getChildren().get(diffAbajoAux)).getStroke() == Color.RED)&&(((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.RED) ) {
                    CargarPistas(req[2] + i + 1, 1, 2);
                }
            }
        }

        //Variante con el voltaje mas pequeño, vale decir, cuando el color son YELLOW y BLUEVIOLET
        if((( req[0]/30)+1) % 3 == 1 && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.YELLOW) && ( (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.GREEN)) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.BLUEVIOLET) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.CHOCOLATE)) {
            for (int i = 0; (i * 30) < req[0]; i = i + 3) {

                int diffArriba = ubicador(req[1], req[2] + i + 1);
                int diffArribaAux = ubicador(req[1], req[2] + i + 2);

                int diffAbajo = ubicador(req[1] + 1, req[2] + i);
                int diffAbajoAux = ubicador(req[1] + 1, req[2] + i + 1);

                if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.YELLOW) && (((Circle) AnchorPane.getChildren().get(diffArribaAux)).getStroke() == Color.YELLOW) && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.YELLOW) ) {
                    CargarPistas(req[2] + i + 2, 1, 1);
                }

                if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.YELLOW) && (((Circle) AnchorPane.getChildren().get(diffAbajoAux)).getStroke() == Color.YELLOW) && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.YELLOW) ) {
                    CargarPistas(req[2] + i + 1, 1, 2);
                }
            }
        }
    }

    @FXML
    public void DibujoChipOR() {

        int[] req = DibujoChip(1, "CHIP OR");
        int diffPrimero = ubicador(req[1], req[2]);
        int reqAux= req[2] + req[0]/30 ;
        int diffUltimo = ubicador(req[1]+1, reqAux);
        if((( req[0]/30)+1) % 3 == 1 && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.RED) && ( (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.GREEN)) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.BLUE) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.CHOCOLATE)) {
            for (int i = 0; (i * 30) < req[0]; i = i + 3) {

                int diffArriba = ubicador(req[1], req[2] + i + 1);
                int diffArribaAux = ubicador(req[1], req[2] + i + 2);

                int diffAbajo = ubicador(req[1] + 1, req[2] + i);
                int diffAbajoAux = ubicador(req[1] + 1, req[2] + i + 1);

                if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.RED) || (((Circle) AnchorPane.getChildren().get(diffArribaAux)).getStroke() == Color.RED)) {
                    CargarPistas(req[2] + i + 2, 1, 1);
                }

                if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.RED) || (((Circle) AnchorPane.getChildren().get(diffAbajoAux)).getStroke() == Color.RED)) {

                    CargarPistas(req[2] + i + 1, 1, 2);
                }


            }
        }

        //Variante con el voltaje mas pequeño, vale decir, cuando el color son YELLOW y BLUEVIOLET
        if((( req[0]/30)+1) % 3 == 1 && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.YELLOW) && ( (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.GREEN)) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.BLUEVIOLET) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.CHOCOLATE)) {
            for (int i = 0; (i * 30) < req[0]; i = i + 3) {

                int diffArriba = ubicador(req[1], req[2] + i + 1);
                int diffArribaAux = ubicador(req[1], req[2] + i + 2);

                int diffAbajo = ubicador(req[1] + 1, req[2] + i);
                int diffAbajoAux = ubicador(req[1] + 1, req[2] + i + 1);

                if ((((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.YELLOW) || (((Circle) AnchorPane.getChildren().get(diffArribaAux)).getStroke() == Color.YELLOW)) {
                    CargarPistas(req[2] + i + 2, 2, 1);
                }

                if ((((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.YELLOW) || (((Circle) AnchorPane.getChildren().get(diffAbajoAux)).getStroke() == Color.YELLOW)) {

                    CargarPistas(req[2] + i + 1, 2, 2);
                }


            }
        }

    }
    @FXML
    public void DibujoChipNOT(){

        int[] req = DibujoChip(2, "CHIP NOT");
        int diffPrimero = ubicador(req[1], req[2]);
        int reqAux= req[2] + req[0]/30 ;
        int diffUltimo = ubicador(req[1]+1, reqAux);

        if(((( req[0]) / 30 ) + 1 ) % 2 == 1 && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.RED) && ( (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.GREEN)) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.BLUE) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.CHOCOLATE)) {
            for (int i = 0; (i * 30) < req[0]; i = i + 2) {

                int diffArriba = ubicador(req[1], req[2] + i + 1);
                int diffAbajo = ubicador(req[1]+1, req[2] + i);

                if ((((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.RED)){

                    if (((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.RED){
                        CargarPistas(req[2] + i + 1, 0, 1);
                    } else if (((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLUE || ((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.GREEN|| ((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.CHOCOLATE|| ((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLACK){
                        CargarPistas(req[2] + i + 1, 1, 1);
                    }
                    if (((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.RED){
                        CargarPistas(req[2] + i, 0, 2);
                    } else if (((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLUE || ((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.GREEN|| ((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.CHOCOLATE|| ((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLACK){
                        CargarPistas(req[2] + i, 1, 2);
                    }

                }
            }
        }

        //Variante con el voltaje mas pequeño, vale decir, cuando el color son YELLOW y BLUEVIOLET
        if(((( req[0]) / 30 ) + 1 ) % 2 == 1 && (((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.YELLOW) && ( (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.GREEN)) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.BLUEVIOLET) || (((Circle) AnchorPane.getChildren().get(diffUltimo)).getStroke() == Color.CHOCOLATE)) {
            for (int i = 0; (i * 30) < req[0]; i = i + 2) {

                int diffArriba = ubicador(req[1], req[2] + i + 1);
                int diffAbajo = ubicador(req[1]+1, req[2] + i);

                if ((((Circle) AnchorPane.getChildren().get(diffPrimero)).getStroke() == Color.YELLOW)){

                    if (((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.YELLOW){
                        CargarPistas(req[2] + i + 1, 0, 1);
                    } else if (((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLUEVIOLET || ((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.GREEN || ((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.CHOCOLATE || ((Circle) AnchorPane.getChildren().get(diffArriba)).getStroke() == Color.BLACK){
                        //CargarPistas(req[2] + i + 1, 1, 1);
                    }
                    if (((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.YELLOW){
                        CargarPistas(req[2] + i, 0, 2);
                    } else if (((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLUEVIOLET || ((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.GREEN || ((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.CHOCOLATE || ((Circle) AnchorPane.getChildren().get(diffAbajo)).getStroke() == Color.BLACK){
                        //CargarPistas(req[2] + i, 1, 2);
                    }

                }
            }
        }

    }


    @FXML
    public void DibujoSwitch() {

        /*toma los puntos necesarios para la creacion del switch*/
        double punto_X1 = ArCircles[registro[0][0] - 1][registro[0][1] - 1].getCenterX();
        double punto_Y1 = ArCircles[registro[0][0] - 1][registro[0][1] - 1].getCenterY();

        double punto_X2 = ArCircles[registro[1][0] - 1][registro[1][1] - 1].getCenterX();
        double punto_Y2 = ArCircles[registro[1][0] - 1][registro[1][1] - 1].getCenterY();

        double punto_X3 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX();
        double punto_Y3 = ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY();

        double punto_X4 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX();
        double punto_Y4 = ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY();

        /*toma los puntos para el funcionamiento del switch*/
        int cood1X = registro[0][1];
        int cood1Y = registro[0][0];

        int cood2X = registro[1][1];
        int cood2Y = registro[1][0];

        int cood3X = registro[2][1];
        int cood3Y = registro[2][0];

        int cood4X = registro[3][1];
        int cood4Y = registro[3][0];


        final boolean[] pasa_corriente = {false};

        int[] memoria_color = new int[1];

        int[] coodX = {cood1X,cood2X,cood3X,cood4X};
        int[] coodY = {cood1Y,cood2Y,cood3Y,cood4Y};

        /*Algoritmos de ordemanientos, cuales ordenan las coordenadas X e Y. De esta manera, se consigue una logica estandar para el funcionamiento*/
        for (int i = 0; i < coodX.length; i++) {
            for (int j = 0; j < coodX.length - 1; j++) {
                if (coodX[j] > coodX[j + 1]) {
                    int temp = coodX[j];
                    coodX[j] = coodX[j + 1];
                    coodX[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < coodY.length; i++) {
            for (int j = 0; j < coodY.length - 1; j++) {
                if (coodY[j] > coodY[j + 1]) {
                    int temp = coodY[j];
                    coodY[j] = coodY[j + 1];
                    coodY[j + 1] = temp;
                }
            }
        }

        /*Cambia el orden de los valores de coordY[1] con coordY[2], para que correspondan con su coodX correspondiente */
        if (coodY[1] < coodY[1 + 1]) {
            int temp = coodY[1];
            coodY[1] = coodY[1 + 1];
            coodY[1 + 1] = temp;
        }


        if (punto_X1 > punto_X2) {
            double aux = punto_X1;
            punto_X1 = punto_X2;
            punto_X2 = aux;

            aux = punto_Y1;
            punto_Y1 = punto_Y2;
            punto_Y2 = aux;
        }

        if (punto_X3 > punto_X4) {
            double aux = punto_X3;
            punto_X3 = punto_X4;
            punto_X4 = aux;

            aux = punto_Y3;
            punto_Y3 = punto_Y4;
            punto_Y4 = aux;
        }

        double puntominX = punto_X1;

        if (punto_X3 < puntominX) {
            puntominX = punto_X3;
        }

        double puntomaxX = punto_X2;

        if (punto_X4 > puntomaxX) {
            puntomaxX = punto_X4;
        }

        double puntominY = punto_Y1;

        if (punto_Y3 < puntominY) {
            puntominY = punto_Y3;
        }

        double puntomaxY = punto_Y2;

        if (punto_Y4 > puntomaxY) {
            puntomaxY = punto_Y4;
        }

        double ancho = puntomaxX - puntominX;
        double alto = puntomaxY - puntominY;

        if (ancho == 60 && alto == 60) {
            Rectangle base = new Rectangle(puntominX, puntominY, ancho, alto);
            base.setFill(Color.LIGHTGRAY);
            base.setStroke(Color.BLACK);

            double centroX = (puntominX + puntomaxX) / 2;
            double centroY = (puntominY + puntomaxY) / 2;


            Circle circulo_Arriba_der = new Circle(punto_X1, punto_Y1, 5);
            circulo_Arriba_der.setFill(Color.BLACK);
            circulo_Arriba_der.setStroke(null);

            Circle circulo_Abajo_der = new Circle(punto_X2, punto_Y2, 5);
            circulo_Abajo_der.setFill(Color.BLACK);
            circulo_Abajo_der.setStroke(null);

            Circle circulo_Arriba_izq = new Circle(punto_X3, punto_Y3, 5);
            circulo_Arriba_izq.setFill(Color.BLACK);
            circulo_Arriba_izq.setStroke(null);

            Circle circulo_Abajo_izq = new Circle(punto_X4, punto_Y4, 5);
            circulo_Abajo_izq.setFill(Color.BLACK);
            circulo_Abajo_izq.setStroke(null);

            Circle circulo_Centro = new Circle(centroX, centroY, 15);
            circulo_Centro.setFill(Color.BLACK);
            circulo_Centro.setStroke(null);


            /*Da la ubicacion en el AnchorPane de los puntos seleccionados*/
            int diffPunto1 = ubicador(coodX[0],coodY[0]);
            int diffPunto2 = ubicador(coodX[1],coodY[1]);
            int diffPunto3 = ubicador(coodX[2],coodY[2]);
            int diffPunto4 = ubicador(coodX[3],coodY[3]);


            circulo_Centro.setOnMouseClicked(mouseEvent -> {

                /*Captura el color de los circulos*/
                Paint color_punto1 = ((Circle) AnchorPane.getChildren().get(diffPunto1)).getStroke();
                Paint color_punto2 = ((Circle) AnchorPane.getChildren().get(diffPunto2)).getStroke();
                Paint color_punto3 = ((Circle) AnchorPane.getChildren().get(diffPunto3)).getStroke();
                Paint color_punto4 = ((Circle) AnchorPane.getChildren().get(diffPunto4)).getStroke();

                if (( ((color_punto1 == Color.RED) && (color_punto3 == Color.RED)) && ((color_punto2 == Color.BLUE) && (color_punto4 == Color.BLUE)) )
                        || ( ((color_punto1 == Color.BLUE) && (color_punto3 == Color.BLUE)) && ((color_punto2 == Color.RED) && (color_punto4 == Color.RED)) )
                        || ( ((color_punto1 == Color.YELLOW) && (color_punto3 == Color.YELLOW)) && ((color_punto2 == Color.BLUEVIOLET) && (color_punto4 == Color.BLUEVIOLET)) )
                        || ( ((color_punto1 == Color.BLUEVIOLET) && (color_punto3 == Color.BLUEVIOLET)) && ((color_punto2 == Color.YELLOW) && (color_punto4 == Color.YELLOW)) )){
                    circulo_Centro.setFill(Color.GRAY);
                    memoria_color[0] = -1;
                } else {

                    //Condicional que revisa: (si no hay carga en la izquierda y no ha sido presionado) o si ya sabemos cual era el estado original
                    if ((((color_punto1 == Color.BLACK) && (color_punto3 == Color.BLACK)) && memoria_color[0] == 0 ) || memoria_color[0] == 1){

                        memoria_color[0]= 1;
                        
                        if (coodX[3]<=7){
                            if ((color_punto2 == Color.RED) && (color_punto4 == Color.RED)){
                                CargarPistas(coodY[0]-1,1,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,1);
                                }
                            } else if ((color_punto2 == Color.BLUE) && (color_punto4 == Color.BLUE)) {
                                CargarPistas(coodY[0]-1,-1,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,1);
                                }
                            } else if ((color_punto2 == Color.YELLOW) && (color_punto4 == Color.YELLOW)) {
                                CargarPistas(coodY[0]-1,2,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,1);
                                }
                            } else if ((color_punto2 == Color.BLUEVIOLET) && (color_punto4 == Color.BLUEVIOLET)) {
                                CargarPistas(coodY[0]-1,-2,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,1);
                                }
                            }
                        } else {
                            if ((color_punto2 == Color.RED) && (color_punto4 == Color.RED)){
                                CargarPistas(coodY[0]-1,1,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,2);
                                }
                            } else if ((color_punto2 == Color.BLUE) && (color_punto4 == Color.BLUE)) {
                                CargarPistas(coodY[0]-1,-1,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,2);
                                }
                            } else if ((color_punto2 == Color.YELLOW) && (color_punto4 == Color.YELLOW)) {
                                CargarPistas(coodY[0]-1,2,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,2);
                                }
                            } else if ((color_punto2 == Color.BLUEVIOLET) && (color_punto4 == Color.BLUEVIOLET)) {
                                CargarPistas(coodY[0]-1,-2,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,2);
                                }
                            }
                        }
                        //Condicional que revisa: (si no hay carga en la derecha y no ha sido presionado) o si ya sabemos cual era el estado original
                    } else if ((((color_punto2 == Color.BLACK) && (color_punto4 == Color.BLACK)) && memoria_color[0] == 0 ) || memoria_color[0] == 2) {

                        memoria_color[0] = 2;

                        if (coodX[3]<=7){
                            if ((color_punto1 == Color.RED) && (color_punto3 == Color.RED)){
                                CargarPistas(coodY[1]-1,1,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,1);
                                }
                            } else if ((color_punto1 == Color.BLUE) && (color_punto3 == Color.BLUE)) {
                                CargarPistas(coodY[1]-1,-1,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,1);
                                }
                            } else if ((color_punto1 == Color.YELLOW) && (color_punto3 == Color.YELLOW)) {
                                CargarPistas(coodY[1]-1,2,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,1);
                                }
                            } else if ((color_punto1 == Color.BLUEVIOLET) && (color_punto3 == Color.BLUEVIOLET)) {
                                CargarPistas(coodY[1]-1,-2,1);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,1);
                                }
                            }
                        } else {
                            if ((color_punto1 == Color.RED) && (color_punto3 == Color.RED)){
                                CargarPistas(coodY[1]-1,1,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,2);
                                }
                            } else if ((color_punto1 == Color.BLUE) && (color_punto3 == Color.BLUE)) {
                                CargarPistas(coodY[1]-1,-1,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,2);
                                }
                            } else if ((color_punto1 == Color.YELLOW) && (color_punto3 == Color.YELLOW)) {
                                CargarPistas(coodY[1]-1,2,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[0]-1,0,2);
                                }
                            } else if ((color_punto1 == Color.BLUEVIOLET) && (color_punto3 == Color.BLUEVIOLET)) {
                                CargarPistas(coodY[1]-1,-2,2);
                                if (pasa_corriente[0]){
                                    CargarPistas(coodY[1]-1,0,2);
                                }
                            }
                        }
                    }
                }
                if (memoria_color[0] != -1){
                    pasa_corriente[0] =!pasa_corriente[0];
                }
            });

            Group Dibujo_Switch = new Group();
            Dibujo_Switch.getChildren().addAll(base, circulo_Arriba_der, circulo_Abajo_der, circulo_Arriba_izq, circulo_Abajo_izq, circulo_Centro);

            AnchorPane.getChildren().add(Dibujo_Switch);
            Historial.add(3);

            // cada vez que se haga click en un Dibujo_Switch, se borra independiente del orden colocado
            Dibujo_Switch.setOnMouseClicked((event) -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    Node presionado = (Node) event.getSource();
                    int indice = AnchorPane.getChildren().indexOf(presionado);
                    Borrar_pieza(indice);
                }
            });
        }


    }
    @FXML
    public void Display_Numero(){
        double diffX2 = 0 ;
        int coordY2 = 0;
        int coordX2 = 0;
        int punto_X2 =0 ;
        int punto_Y2 = 0;
        int punto_X3 =0 ;
        int punto_Y3 = 0;
        int punto_X4 =0 ;
        int punto_Y4 = 0;

        int[] Memoria_estado_original2 = new int[8];

        if ((registro[0][1] == 5 || registro[0][1] == 10)
                && (registro[1][1] == 5 || registro[1][1] == 10)
                && (registro[2][1] == 5 || registro[2][1] == 10)
                && (registro[3][1] == 5 || registro[3][1] == 10)) {

            diffX2 = -1;
            double diffY = -1;

            coordY2 = registro[0][0];
            coordX2 = registro[0][1];

            punto_X2 = registro[1][0];
            punto_Y2 = registro[1][1];

            punto_X3 = registro[2][0];
            punto_Y3 = registro[2][1];

            punto_X4 = registro[3][0];
            punto_Y4 = registro[3][1];

            int[] coordenadas ={coordY2,punto_X2,punto_X3,punto_X4};

            for(int i =0;i<coordenadas.length-1;i++){
                for(int j=0;j<coordenadas.length-1-i;j++){
                    if(coordenadas[j]>coordenadas[j+1]){
                        int temp= coordenadas[j];
                        coordenadas[j]=coordenadas[j+1];
                        coordenadas[j+1]=temp;
                    }
                }
            }



            /* Condicional el cual me guarda en las variables coordY, coordX cual es el punto menor dentro del protoboard
            Esto sirve para la creacion de la figura, pues permite que siempre se sepa cual es la esquina en donde se debe
            empezar a dibujar*/
            if (coordY2 > registro[1][0]) {
                coordY2 = registro[1][0];
                coordX2 = registro[1][1];
            } else if (coordY2 > registro[2][0]) {
                coordY2 = registro[2][0];
                coordX2 = registro[2][1];
            }

            /*Grupo de condicionales quienes comprueban que las coordenadas X correspondan con las coordenadas Y*/
            if (coordY2 == registro[0][0] && coordX2 != registro[0][1]) {

                if (coordX2 > registro[0][1]) {
                    coordX2 = registro[0][1];
                }

            } else if (coordY2 == registro[1][0] && coordX2 != registro[1][1]) {

                if (coordX2 > registro[1][1]) {
                    coordX2 = registro[1][1];
                }

            } else if (coordY2 == registro[2][0] && coordX2 != registro[2][1]) {

                if (coordX2 > registro[2][1]) {
                    coordX2 = registro[2][1];
                }

            } else if (coordY2 == registro[3][0] && coordX2 != registro[3][1]) {

                if (coordX2 > registro[3][1]) {
                    coordX2 = registro[3][1];
                }

            }

            /*Ciclo para saber el surco de las coordenadas X e Y*/
            for (int i = 0; i <= 3; i++) {

                if (coordX2 == registro[i][1] && coordY2 != registro[i][0]) {

                    diffX2 = ArCircles[registro[i][0]][registro[i][1]].getCenterX() - ArCircles[coordY2][coordX2].getCenterX();
                }

                if (coordY2 == registro[i][0] && coordX2 != registro[i][1]) {

                    diffY = ArCircles[registro[i][0] - 1][registro[i][1] - 1].getCenterY() - ArCircles[coordY2 - 1][coordX2 - 1].getCenterY();

                }

            }

            //Agrupar_Dibujo_Display se encarga de encapsular todas las figuras que conforman el swich en un solo objeto.
            Group Agrupar_Dibujo_Display = new Group();
            Group Agrupar_Dibujo_lineas_display = new Group();

            Rectangle cuerpoDisplay = new Rectangle(
                    ArCircles[coordY2 - 1][coordX2 - 1].getCenterX() - 10, //coordenada X
                    ArCircles[coordY2 - 1][coordX2 - 1].getCenterY() + 5,   //coordenada Y
                    diffX2 + 20,   //Ancho
                    diffY - 10   //Altura
            );

            cuerpoDisplay.setStroke(Color.BLACK);
            cuerpoDisplay.setStrokeWidth(2);
            cuerpoDisplay.setFill(Color.BLACK);

            Agrupar_Dibujo_Display.getChildren().add(cuerpoDisplay);

            int dibujo_vertical = 18;
            int dibujo_horizontal = 60;
            int desplazamineto_vertical = 18;
            if ((((int) diffX2 * 2 / 60) + 1) % 4 == 1) {//con % 4 son 5 hoyitos
                for (int i = 1; (i * 30) <= diffX2; i++) {
                    if (i <= 2) {
                        Rectangle lineas_verticales_sup = new Rectangle(
                                (ArCircles[coordY2 - 1][coordX2 - 1].getCenterX() - 5) + 30,
                                ArCircles[coordY2 - 1][coordX2 - 1].getCenterY() + dibujo_vertical,
                                10,
                                diffY - 110
                        );
                        lineas_verticales_sup.setStrokeWidth(1);

                        lineas_verticales_sup.setStroke(Color.BLACK);
                        lineas_verticales_sup.setFill(Color.GRAY);
                        Agrupar_Dibujo_lineas_display.getChildren().add(lineas_verticales_sup);

                    } else if (i <= 4) {
                        Rectangle lineas_verticales_inferiores = new Rectangle(
                                (ArCircles[coordY2 - 1][coordX2 - 1].getCenterX() - 5) + 30 + dibujo_horizontal,
                                ArCircles[coordY2 - 1][coordX2 - 1].getCenterY() + dibujo_vertical - 156,
                                10,
                                diffY - 110
                        );

                        lineas_verticales_inferiores.setStrokeWidth(1);

                        lineas_verticales_inferiores.setStroke(Color.BLACK);
                        lineas_verticales_inferiores.setFill(Color.GRAY);
                        Agrupar_Dibujo_lineas_display.getChildren().add(lineas_verticales_inferiores);

                    }
                    dibujo_vertical = dibujo_vertical + 78;

                    Historial.add(6);

                }
                for (int i = 2; (i * 30) <= diffX2; i++) {
                    Rectangle lineas_horizontales = new Rectangle(
                            (ArCircles[coordY2 - 1][coordX2 - 1].getCenterX() - 5) + 30 + 20,
                            ArCircles[coordY2 - 1][coordX2 - 1].getCenterY() + desplazamineto_vertical,
                            30,
                            diffY - 160
                    );
                    lineas_horizontales.setStrokeWidth(1);

                    lineas_horizontales.setStroke(Color.BLACK);
                    lineas_horizontales.setFill(Color.GRAY);
                    Agrupar_Dibujo_lineas_display.getChildren().add(lineas_horizontales);
                    desplazamineto_vertical = desplazamineto_vertical + 65;

                }
                Rectangle punto = new Rectangle(
                        (ArCircles[coordY2 - 1][coordX2 - 1].getCenterX() - 5) + 30 + 80,
                        ArCircles[coordY2 - 1][coordX2 - 1].getCenterY() + 148,
                        10,
                        diffY - 160
                );
                punto.setStrokeWidth(1);

                punto.setStroke(Color.BLACK);
                punto.setFill(Color.GRAY);
                Agrupar_Dibujo_lineas_display.getChildren().add(punto);
                AnchorPane.getChildren().addAll(Agrupar_Dibujo_Display, Agrupar_Dibujo_lineas_display);


                Group Agrupador_Total = new Group();

                Agrupador_Total.getChildren().add(Agrupar_Dibujo_Display);
                Agrupador_Total.getChildren().add(Agrupar_Dibujo_lineas_display);
                AnchorPane.getChildren().addAll(Agrupador_Total);


                Agrupador_Total.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        Node presionado = (Node) event.getSource();
                        int indice = AnchorPane.getChildren().indexOf(presionado);
                        Borrar_pieza(indice);
                    }
                });

            } else {
                System.out.println("Seleccionado una cantidad de hoyitos no admisibles");
            }

            if(coordY2<punto_X2 && punto_X3<punto_X4){
                int figura=0;
                int figura2=4;
                for(int i=coordY2;i<punto_X2+1;i++){
                    int diffDisplay = ubicador(coordX2,i);
                    if(figura==2 && (((Circle) AnchorPane.getChildren().get(diffDisplay)).getStroke() == Color.BLACK)){
                        for(int j=1;j<figura;j++){
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(j-1);
                            if (linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.GRAY);
                            }
                        }
                    }
                    else if((((Circle) AnchorPane.getChildren().get(diffDisplay)).getStroke() == Color.RED)) {
                        if (figura ==0 ) {
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura);
                            if (linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                        if (figura == 1) {
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura);
                            if (linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                        if (figura == 3) {
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura - 1);
                            if (linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                        if (figura == 4) {
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura - 1);
                            if (linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                    }
                    figura++;
                }
                for(int i=punto_X3;i<punto_X4+1;i++){
                    int diffDisplay = ubicador(punto_Y3,i);
                    if(figura2==6 && (((Circle) AnchorPane.getChildren().get(diffDisplay)).getStroke() == Color.BLACK)){
                        for(int j=1;j<figura2;j++){
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(j-1);
                            if (linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.GRAY);
                            }
                        }
                    }
                    else if((((Circle) AnchorPane.getChildren().get(diffDisplay)).getStroke() == Color.RED)){
                        if(figura2==4){
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura2);
                            if(linea instanceof Rectangle){
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                        if(figura2==5){
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura2);
                            if(linea instanceof Rectangle){
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                        if(figura2==7){
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura2-1);
                            if(linea instanceof Rectangle){
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                        if(figura2==8){
                            Node linea = Agrupar_Dibujo_lineas_display.getChildren().get(figura2-1);
                            if(linea instanceof Rectangle) {
                                ((Rectangle) linea).setFill(Color.RED);
                            }
                        }
                    }
                    figura2++;
                }

            }
        }

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

        System.out.println("Fila : "+Fila+"\tColumna : "+Columna);

        int diff;

        if (registro[0][0] == 0) {

            registro[0][0] = Columna;
            registro[0][1] = Fila;

        } else if (registro[1][0] == 0) {

            registro[1][0] = Columna;
            registro[1][1] = Fila;

        } else if (registro[2][0] == 0) {

            registro[2][0] = Columna;
            registro[2][1] = Fila;


            if (registro[0][1] != 15) {

                diff = lastInt - 1 - (14 - registro[0][1]) - (14 * (30 - registro[0][0]));

                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
            } else if (registro[0][0] == 33) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);
            } else if (registro[0][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(3);
            } else if (registro[0][0] == 35) {
                ((Rectangle) AnchorPane.getChildren().get(3)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(3)).setStrokeWidth(3);

            }


        } else if (registro[3][0] == 0) {

            registro[3][0] = Columna;
            registro[3][1] = Fila;

            if (registro[1][1] != 15) {
                diff = lastInt - 1 - (14 - registro[1][1]) - (14 * (30 - registro[1][0]));

                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);

            } else if (registro[1][0] == 33) {

                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(3);

            } else if (registro[1][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(3);
            } else if (registro[1][0] == 35) {
                ((Rectangle) AnchorPane.getChildren().get(3)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(3)).setStrokeWidth(3);
            }

        } else {

            if (registro[0][1] != 15) {
                //calculo para encontrar el punto dentro de la matriz del protoboard
                diff = lastInt - 1 - (14 - registro[0][1]) - (14 * (30 - registro[0][0]));
                Color colorDiff = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();

                if (((Circle) AnchorPane.getChildren().get(diff)).getStrokeWidth() == 3) {
                    if (colorDiff == Color.CHOCOLATE || colorDiff == Color.GREEN) {
                        ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.BLACK);
                        ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
                    } else {
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
            } else if (registro[2][0] == 35) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.CHOCOLATE);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(3);
            }

            if (registro[0][0] == 33) {
                ((Rectangle) AnchorPane.getChildren().get(1)).setStroke(Color.BLACK);
                ((Rectangle) AnchorPane.getChildren().get(1)).setStrokeWidth(1);
            } else if (registro[0][0] == 34) {
                ((Rectangle) AnchorPane.getChildren().get(2)).setStroke(Color.BLACK);
                ((Rectangle) AnchorPane.getChildren().get(2)).setStrokeWidth(1);
            } else if (registro[0][0] == 35) {
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
                    if (colorDiff != Color.BLUE
                            && colorDiff != Color.RED
                            && colorDiff != Color.BROWN
                            && colorDiff != Color.BLUEVIOLET
                            && colorDiff != Color.YELLOW) {
                        ((Circle) AnchorPane.getChildren().get(diff)).setStroke(Color.CHOCOLATE);

                    }

                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(3);
                }
            }
        }
        if (circle.getStroke() != Color.BLUE
                && circle.getStroke() != Color.RED
                && circle.getStroke() != Color.BROWN
                && circle.getStroke() != Color.BLUEVIOLET
                && circle.getStroke() != Color.YELLOW) {
            circle.setStroke(Color.GREEN);
        }

        circle.setStrokeWidth(3);

    }

    private int Carga_Motor=1;

    @FXML
    public int Cambiar_Carga_Motor(){
        if(Carga_Motor==1){
            Carga_Motor=-1;
        }else{
            Carga_Motor=1;
        }
        return Carga_Motor;
    }


    @FXML
    public void Cables() {

        Line cable1 = null;
        int fila = 0, columna = 0, carga = 0;
        boolean alright = false;
        // los valores 33 y 34 son de la bateria

        if (registro[3][0] != 0) {
            //Cableado dentro del protoboard

            if (registro[2][1] != 15 && registro[3][1] != 15) {
                if (!status_hoyitos[registro[3][0]][registro[3][1]] && !status_hoyitos[registro[2][0]][registro[2][1]]) {
                    status_hoyitos[registro[3][0]][registro[3][1]] = true;
                    status_hoyitos[registro[2][0]][registro[2][1]] = true;
                    cable1 = new Line(

                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()

                    );
                    Cargar(false);
                    alright = true;
                }
            } else {  //Cableado del protoboard a la bateria


                if (registro[2][0] == 34 && !status_hoyitos[registro[3][0]][registro[3][1]]) { //Conexion Bateria (Parte Positiva)

                    cable1 = new Line(

                            1200,
                            120,
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()

                    );
                    columna = registro[3][0];
                    fila = registro[3][1];

                    carga = 1;
                    status_hoyitos[registro[3][0]][registro[3][1]] = true;
                    alright = true;

                } else if (registro[3][0] == 34 && !status_hoyitos[registro[2][0]][registro[2][1]]) { // Conexion Bateria (Parte Negativa)

                    cable1 = new Line(

                            1200,
                            120,
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY()

                    );
                    columna = registro[2][0];
                    fila = registro[2][1];

                    carga = 1;
                    status_hoyitos[registro[2][0]][registro[2][1]] = true;
                    alright = true;

                } else if (registro[2][0] == 33 && !status_hoyitos[registro[3][0]][registro[3][1]]) {

                    cable1 = new Line(

                            1200,
                            380,
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterX(),
                            ArCircles[registro[3][0] - 1][registro[3][1] - 1].getCenterY()

                    );
                    columna = registro[3][0];
                    fila = registro[3][1];

                    carga = -1;
                    status_hoyitos[registro[3][0]][registro[3][1]] = true;
                    alright = true;

                } else if (registro[3][0] == 35 && !status_hoyitos[registro[3][0]][registro[3][1]]) {

                    int Carga_actual=Cambiar_Carga_Motor();

                    int coordX = 1150;
                    int coordY;
                    carga=Carga_actual;

                    if (carga==1) {
                        coordY = 450;
                        carga = 1;
                    } else {
                        coordY = 450;
                        carga = -1;
                    }

                    cable1 = new Line(
                            coordX,
                            coordY,
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                            ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY());

                    columna = registro[2][0];
                    fila = registro[2][1];

                    System.out.println(columna);
                    System.out.println(fila);


                    status_hoyitos[registro[2][0]][registro[2][1]] = true;
                    alright = true;

                } else {
                    if (!status_hoyitos[registro[3][0]][registro[3][1]]) {
                        cable1 = new Line(

                                1200,
                                380,
                                ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterX(),
                                ArCircles[registro[2][0] - 1][registro[2][1] - 1].getCenterY()

                        );

                        columna = registro[2][0];
                        fila = registro[2][1];

                        carga = -1;
                        alright = true;
                    }

                }
                if (alright) {
                    fila -= 1;
                    columna -= 1;
                    if (fila == 0 || fila == 1 || fila == 12 || fila == 13) {
                        CargasBuses(fila, carga);
                    } else if (fila > 1 && fila <= 6) {
                        CargarPistas(columna, carga, 1);
                    } else if (fila > 6 && fila < 12) {
                        CargarPistas(columna, carga, 2);
                    }

                    Protoboard2.CambiarCargaBus(fila, columna, carga);
                    //Protoboard2.setCableDBateria();
                }

            }
            if (alright) {
                cable1.setStroke(Color.BLACK);
                cable1.setStrokeWidth(3);

                Protoboard2.EstadoHoyito(fila, columna);
                AnchorPane.getChildren().add(cable1);

                Historial.add(1);
            }

            //Registro para recargar los hoyitos
            int[] entrada1 = new int[2];
            entrada1[0] = registro[3][0];
            entrada1[1] = registro[3][1];

            int[] entrada2 = new int[2];
            entrada2[0] = registro[2][0];
            entrada2[1] = registro[2][1];

            Cable cable = new Cable(cable1, entrada1, entrada2, carga);


            cables.add(cable);

            Historial.add(1);

            // cada vez que se haga click en un cable1, se borra independiente del orden colocado
            cable1.setOnMouseClicked((event) -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    Node presionado = (Node) event.getSource();
                    int indice = AnchorPane.getChildren().indexOf(presionado);
                    System.out.println(cables.size());
                    for (int i = 0; i < cables.size(); i++) {

                        if(cables.get(i).linea == AnchorPane.getChildren().get(indice)){
                            status_hoyitos[ cables.get(i).hoyitosConectados[0][0] ][ cables.get(i).hoyitosConectados[0][1] ] = false;
                            status_hoyitos[ cables.get(i).hoyitosConectados[1][0] ][ cables.get(i).hoyitosConectados[1][1] ] = false;
                            cables.remove(i);

                        }
                    }
                    Borrar_pieza(indice);
                    //Recargar();
                }
            });
        } else {
            System.out.println(" Primero seleccione 4 elementos");
        }
        //Funcion en proceso
        //Recargar();
    }

    public void cantidad_objetos() {
        System.out.println("Cantidad de elementos en pantalla: " + AnchorPane.getChildren().size());
    }


    public void Cargar(Boolean resistencia) {

        int fila = registro[3][1] - 1;
        int columna = registro[3][0] - 1;
        int carga = 0;

        int diff = ubicador(registro[2][1], registro[2][0]);
        int diff2 = ubicador(registro[3][1], registro[3][0]);


        Color colorDiff1 = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();

        if (colorDiff1 == Color.GREEN) {
            int aux = diff;
            diff = diff2;
            diff2 = aux;
            colorDiff1 = (Color) ((Circle) AnchorPane.getChildren().get(diff)).getStroke();
            fila = registro[2][1] - 1;
            columna = registro[2][0] - 1;

        }
        Color colorDiff2 = (Color) ((Circle) AnchorPane.getChildren().get(diff2)).getStroke();
        if ((colorDiff1 != Color.GREEN || colorDiff2 != Color.GREEN) &&
                (((colorDiff1 == Color.BLUE || colorDiff1 == Color.BLUEVIOLET) && (colorDiff2 != Color.RED || colorDiff2 != Color.YELLOW))
                        || ((colorDiff1 == Color.RED || colorDiff1 == Color.YELLOW) && (colorDiff2 != Color.BLUE || colorDiff2 != Color.BLUEVIOLET)))) {
            if (colorDiff1 != Color.BROWN && colorDiff2 != Color.BROWN) {
                System.out.println("Pintando...");
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUE) {
                    carga = -1;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.RED) {
                    carga = 1;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUE && resistencia) {
                    carga = -2;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.RED && resistencia) {
                    carga = 2;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUEVIOLET) {
                    carga = -2;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.YELLOW) {
                    carga = 2;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUEVIOLET && resistencia) {
                    carga = -2;
                }
                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.YELLOW && resistencia) {
                    carga = 2;
                }

                if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUE
                        || ((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.RED
                        || ((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUEVIOLET
                        || ((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.YELLOW) {
                    if (fila == 0 || fila == 1 || fila == 12 || fila == 13) {
                        CargasBuses(fila, carga);
                    } else if (fila > 1 && fila <= 6) {
                        CargarPistas(columna, carga, 1);
                    } else if (fila > 6 && fila < 12) {
                        CargarPistas(columna, carga, 2);
                    }
                }
            }

        } else if ((!(colorDiff1 == Color.GREEN || colorDiff2 == Color.GREEN))) {
            System.out.println("Quemando");
            if (((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUE
                    || ((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.RED
                    || ((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.BLUEVIOLET
                    || ((Circle) AnchorPane.getChildren().get(diff)).getStroke() == Color.YELLOW) {
                if (fila == 0 || fila == 1 || fila == 12 || fila == 13) {
                    CargasBuses(fila, 3);
                } else if (fila > 1 && fila <= 6) {
                    CargarPistas(columna, 3, 1);
                } else if (fila > 6 && fila < 12) {
                    CargarPistas(columna, 3, 2);
                }
            }
            pararTodo();
        }


    }
    @FXML
    public void Recargar() {
        //Resetea dejando todo el neutro excepto lo quemado
        for (int i = lastMod; i< lastInt; i++){
            if(((Circle)AnchorPane.getChildren().get(i)).getStroke() != Color.BROWN){
                ((Circle)AnchorPane.getChildren().get(i)).setStroke(Color.BLACK);
                ((Circle)AnchorPane.getChildren().get(i)).setStrokeWidth(1);
            }
        }
        //Carga los buses de vuelta (Bateria a buses)
        for (int i = 0; i < cables.size(); i++) {

            if (cables.get(i).getHoyitosConectados()[0][1] == 15 || cables.get(i).getHoyitosConectados()[1][1] == 15) {
                if (cables.get(i).getHoyitosConectados()[0][1] == 1) {
                    //System.out.println("Hay un cable de bateria al primer bus -> a");
                    CargasBuses(0, cables.get(i).getCarga());
                } else if (cables.get(i).getHoyitosConectados()[1][1] == 1) {
                    //System.out.println("Hay un cable de bateria al primer bus -> b");
                    CargasBuses(0, cables.get(i).getCarga());
                }

                if (cables.get(i).getHoyitosConectados()[0][1] == 2) {
                    //System.out.println("Hay un cable de bateria al primer bus -> a");
                    CargasBuses(1, cables.get(i).getCarga());
                } else if (cables.get(i).getHoyitosConectados()[1][1] == 2) {
                    //System.out.println("Hay un cable de bateria al primer bus -> b");
                    CargasBuses(1, cables.get(i).getCarga());
                }

                if (cables.get(i).getHoyitosConectados()[0][1] == 13) {
                    //System.out.println("Hay un cable de bateria al primer bus -> a");
                    CargasBuses(12, cables.get(i).getCarga());
                } else if (cables.get(i).getHoyitosConectados()[1][1] == 13) {
                    //System.out.println("Hay un cable de bateria al primer bus -> b");
                    CargasBuses(12, cables.get(i).getCarga());
                }
                if (cables.get(i).getHoyitosConectados()[0][1] == 14) {
                    //System.out.println("Hay un cable de bateria al primer bus -> a");
                    CargasBuses(13, cables.get(i).getCarga());
                } else if (cables.get(i).getHoyitosConectados()[1][1] == 14) {
                    //System.out.println("Hay un cable de bateria al primer bus -> b");
                    CargasBuses(13, cables.get(i).getCarga());
                }
            }
        }
        //Carga de Buses a puertos
        for (int i = 1; i <= 14 ; i++){
            if (i == 3){
                i = 13;
            }
            for (int j = 0; j < cables.size(); j++) {

                if(((cables.get(j).getHoyitosConectados()[0][1]== i && cables.get(j).getHoyitosConectados()[1][1] >= i) ||
                        (cables.get(j).getHoyitosConectados()[0][1] >= i && cables.get(j).getHoyitosConectados()[1][1]== i)||
                        i == 13)

                        && (cables.get(j).getHoyitosConectados()[0][1] != 15 && cables.get(j).getHoyitosConectados()[1][1] != 15)){

                    int target = 2;

                    int diff = ubicador(cables.get(j).getHoyitosConectados()[0][1],cables.get(j).getHoyitosConectados()[0][0]);
                    int diff2 = ubicador(cables.get(j).getHoyitosConectados()[1][1],cables.get(j).getHoyitosConectados()[1][0]);

                    int carga = 0;
                    int carga2 = 0;

                    Color colorAux = (Color) ((Circle)AnchorPane.getChildren().get(diff)).getStroke();
                    Color colorAux2 = (Color) ((Circle)AnchorPane.getChildren().get(diff2)).getStroke();
                    target = 0;
                    if (cables.get(j).getHoyitosConectados()[0][1] > cables.get(j).getHoyitosConectados()[1][1]){
                        target = 1;
                    }
                    if(colorAux == Color.RED){
                        carga = 1;
                    }else if(colorAux == Color.BLUE){
                        carga = -1;
                    }

                    if(colorAux2 == Color.RED){
                        carga2 = 1;

                    }else if(colorAux2 == Color.BLUE){
                        carga2 = -1;
                    }
                    if (carga == carga2*-1 && carga != 0) {
                        target = 2;
                        carga = 3;
                    }else if (carga == 0 && carga2 != 0){
                        target = 1;
                        carga = carga2;
                    }
                    System.out.println("Cable GET [0][1]: "+cables.get(j).getHoyitosConectados()[0][1]);
                    System.out.println("Cable GET [0][0]: "+cables.get(j).getHoyitosConectados()[0][0]);
                    System.out.println("Cable GET [1][1]: "+cables.get(j).getHoyitosConectados()[1][1]);
                    System.out.println("Cable GET [1][0]: "+cables.get(j).getHoyitosConectados()[1][0]);
                    if (i == 13 && cables.get(j).getHoyitosConectados()[1][1] == i){
                        System.out.println("Cambio");
                        target = 0;
                    }
                    if (i == 13 && cables.get(j).getHoyitosConectados()[1][1] < i ){
                        System.out.println("Cambio2");
                        target = 0;
                    }else if (i == 13 && cables.get(j).getHoyitosConectados()[0][1] < i){
                        target = 1;
                    }
                    if (target == 0){
                        System.out.println(cables.get(j).getHoyitosConectados()[1][1]);
                        if(cables.get(j).getHoyitosConectados()[1][1] >= 1 && cables.get(j).getHoyitosConectados()[1][1] < 3){
                            CargasBuses(cables.get(j).getHoyitosConectados()[1][1]-1,carga);
                        }else if(cables.get(j).getHoyitosConectados()[1][1] >= 3 && cables.get(j).getHoyitosConectados()[1][1] < 7){
                            CargarPistas(cables.get(j).getHoyitosConectados()[1][0]-1,carga,1);
                        }else if(cables.get(j).getHoyitosConectados()[1][1] >= 7 && cables.get(j).getHoyitosConectados()[1][1] < 13){
                            CargarPistas(cables.get(j).getHoyitosConectados()[1][0]-1,carga,2);
                        }else if(cables.get(j).getHoyitosConectados()[1][1] >= 13 && cables.get(j).getHoyitosConectados()[1][1] <= 14){
                            CargasBuses(cables.get(j).getHoyitosConectados()[1][1]-1,carga);
                        }
                    }else if (target == 1){
                        System.out.println(cables.get(j).getHoyitosConectados()[0][1]);
                        if(cables.get(j).getHoyitosConectados()[0][1] >= 1 && cables.get(j).getHoyitosConectados()[0][1] < 3){
                            CargasBuses(cables.get(j).getHoyitosConectados()[0][1]-1,carga);
                        }else if(cables.get(j).getHoyitosConectados()[0][1] >= 3 && cables.get(j).getHoyitosConectados()[0][1] < 7){
                            CargarPistas(cables.get(j).getHoyitosConectados()[0][0]-1,carga,1);
                        }else if(cables.get(j).getHoyitosConectados()[0][1] >= 7 && cables.get(j).getHoyitosConectados()[0][1] < 13){
                            CargarPistas(cables.get(j).getHoyitosConectados()[0][0]-1,carga,2);
                        }else if(cables.get(j).getHoyitosConectados()[0][1] >= 13 && cables.get(j).getHoyitosConectados()[0][1] <= 14){
                            CargasBuses(cables.get(j).getHoyitosConectados()[0][1]-1,carga);
                        }
                    }
                }
            }
        }
        //Carga entre puertos medios
        boolean proceso = true;
        int count = 0;
        while(proceso && count<10){
            proceso = false;
            count++;
            for(int i = 0; i < cables.size(); i++){

                if (cables.get(i).hoyitosConectados[0][1] != 1 && cables.get(i).hoyitosConectados[1][1] != 1
                && cables.get(i).hoyitosConectados[0][1] != 2 && cables.get(i).hoyitosConectados[1][1] != 2
                && cables.get(i).hoyitosConectados[0][1] != 13 && cables.get(i).hoyitosConectados[1][1] != 13
                && cables.get(i).hoyitosConectados[0][1] != 14 && cables.get(i).hoyitosConectados[1][1] != 14){
                    int target = 2;

                    int diff = ubicador(cables.get(i).getHoyitosConectados()[0][1],cables.get(i).getHoyitosConectados()[0][0]);
                    int diff2 = ubicador(cables.get(i).getHoyitosConectados()[1][1],cables.get(i).getHoyitosConectados()[1][0]);

                    int carga = 0;
                    int carga2 = 0;

                    Color colorAux = (Color) ((Circle)AnchorPane.getChildren().get(diff)).getStroke();
                    Color colorAux2 = (Color) ((Circle)AnchorPane.getChildren().get(diff2)).getStroke();
                    target = 0;
                    if(colorAux == Color.RED){
                        carga = 1;
                    }else if(colorAux == Color.BLUE){
                        carga = -1;
                    }

                    if(colorAux2 == Color.RED){
                        carga2 = 1;

                    }else if(colorAux2 == Color.BLUE){
                        carga2 = -1;
                    }
                    if (carga == carga2 && carga != 0) {
                        System.out.println("Same confirmed");
                        target = 2;
                        carga = 3;
                    }else if (carga == 0 && carga2 != 0){

                        target = 1;
                        carga = carga2;
                    }

                    if (i == 13 && cables.get(i).getHoyitosConectados()[0][0] < cables.get(i).getHoyitosConectados()[1][0]
                            && cables.get(i).getHoyitosConectados()[0][1] == cables.get(i).getHoyitosConectados()[1][1]
                    ){

                        target = 1;
                    }
                    if (target == 0){
                        System.out.println(cables.get(i).getHoyitosConectados()[1][1]);
                        if(cables.get(i).getHoyitosConectados()[1][1] >= 1 && cables.get(i).getHoyitosConectados()[1][1] < 3){
                            CargasBuses(cables.get(i).getHoyitosConectados()[1][1]-1,carga);
                        }else if(cables.get(i).getHoyitosConectados()[1][1] >= 3 && cables.get(i).getHoyitosConectados()[1][1] < 8){
                            CargarPistas(cables.get(i).getHoyitosConectados()[1][0]-1,carga,1);
                        }else if(cables.get(i).getHoyitosConectados()[1][1] >= 8 && cables.get(i).getHoyitosConectados()[1][1] < 13){
                            CargarPistas(cables.get(i).getHoyitosConectados()[1][0]-1,carga,2);
                        }else if(cables.get(i).getHoyitosConectados()[1][1] >= 13 && cables.get(i).getHoyitosConectados()[1][1] <= 14){
                            CargasBuses(cables.get(i).getHoyitosConectados()[1][1]-1,carga);
                        }
                        proceso = true;
                    }else if (target == 1){
                        System.out.println(cables.get(i).getHoyitosConectados()[0][1]);
                        if(cables.get(i).getHoyitosConectados()[0][1] >= 1 && cables.get(i).getHoyitosConectados()[0][1] < 3){
                            CargasBuses(cables.get(i).getHoyitosConectados()[0][1]-1,carga);
                        }else if(cables.get(i).getHoyitosConectados()[0][1] >= 3 && cables.get(i).getHoyitosConectados()[0][1] < 8){
                            CargarPistas(cables.get(i).getHoyitosConectados()[0][0]-1,carga,1);
                        }else if(cables.get(i).getHoyitosConectados()[0][1] >= 8 && cables.get(i).getHoyitosConectados()[0][1] < 13){
                            CargarPistas(cables.get(i).getHoyitosConectados()[0][0]-1,carga,2);
                        }else if(cables.get(i).getHoyitosConectados()[0][1] >= 13 && cables.get(i).getHoyitosConectados()[0][1] <= 14){
                            CargasBuses(cables.get(i).getHoyitosConectados()[0][1]-1,carga);
                        }
                        proceso = true;
                    }
                }

            }


        }
    }

    public void CargasBuses(int fila, int carga) {

        Color color = null;


        //Los numeros representan la carga (-2 carga negativa resistencia,-1 carga negativa, 0 no carga, 1 carga positiva, 2 carga positiva resistencia)

        if (carga == -1) {
            color = Color.BLUE;
        } else if (carga == 1) {
            color = Color.RED;
        } else if(carga == 0) {
            color = Color.BLACK;
        } else if (carga == -2) {
            color = Color.BLUEVIOLET;
        } else if (carga == 2) {
            color = Color.YELLOW;
        }else {
            color = Color.BROWN;
        }

        for (int i = 0; i < 30; i++) {

            int diff = lastInt - (14 * i) - (14 - fila);

            ((Circle) AnchorPane.getChildren().get(diff)).setStroke(color);
            if (color != Color.BLACK){
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(2);
            }else{
                ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
            }

        }
    }

    public void CargarPistas(int columna, int carga, int op) {

        Color color = null;

        //Los numeros representan la carga (-2 carga negativa resistencia,-1 carga negativa, 0 no carga, 1 carga positiva, 2 carga positiva resistencia)

        if (carga == -1){
            color = Color.BLUE;     // carga negativa
        }else if (carga == 1){
            color = Color.RED;      // carga posivita
        } else if (carga==0) {
            color = Color.BLACK;
        } else if (carga == -2) {
            color = Color.BLUEVIOLET;
        } else if (carga == 2) {
            color = Color.YELLOW;
        } else {
            color = Color.BROWN;
        }

        if (op == 2) {
            for (int i = 0; i < 5; i++) {

                int diff = (lastInt - (i) - (14 * (29 - columna)) - 3);
                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(color);
                if (carga != 0) {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(2);
                } else {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
                }


            }
        } else {
            for (int i = 5; i < 10; i++) {

                int diff = (lastInt - (i) - (14 * (29 - columna)) - 3);

                ((Circle) AnchorPane.getChildren().get(diff)).setStroke(color);
                if (carga != 0) {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(2);
                } else {
                    ((Circle) AnchorPane.getChildren().get(diff)).setStrokeWidth(1);
                }
            }
        }
    }


    @FXML
    public void reset() {

        for (int i = AnchorPane.getChildren().size(); i > lastMod; i--) {
            AnchorPane.getChildren().removeLast();
        }
        for (int i = 0; i < 32; i++){
            for (int j = 0; j < 16; j++){
                status_hoyitos[i][j] = false;
            }
        }

        btnLed.setDisable(false);

        btnCable.setDisable(false);
        btnResistencia.setDisable(false);
        btnReset.setDisable(false);
        btnChipAND.setDisable(false);
        btnChipOR.setDisable(false);
        btnChipNOT.setDisable(false);
        btnSwitch.setDisable(false);
        Boton_encendido.setDisable(false);

        colocarHoyitos();
    }

    public void colocarHoyitos() {
        //ArCircles.length-4 debe ser restado con 4 en vez de 2 o da error de que ArCircles[i][j] son nulos
        //ACTUALIZACION: ArCircles.length-6 debe ser restado con 6, porque? no se
        //porque es eso asi, pues me gustaria saber
        for (int i = 0; i < ArCircles.length-6; i++) {
            for (int j = 0; j < ArCircles[i].length - 2; j++) {
                ArCircles[i][j].setStroke(Color.BLACK);
                ArCircles[i][j].setStrokeWidth(1);
                AnchorPane.getChildren().add(ArCircles[i][j]);
            }
        }
    }

    //primero el espacio 1 seria la fila, y luego el 0 columna
    public int ubicador(int fila, int columna) {
        int diff = lastInt - 1 - (14 - fila) - (14 * (30 - columna));
        return diff;
    }

    public void pararTodo() {
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

        Protoboard2.CrearProtoboard(tamano_filas, tamano_columnas);


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

        Label label13 = new Label();
        label13.setLayoutX(1200);
        label13.setLayoutY(150);
        label13.setText("9v");
        label13.setFont(Font.font(50));
        AnchorPane.getChildren().addAll(label13);

        //creacion del dibujo del motor
        Rectangle superficie;
        boolean motorEncendido;
        Circle boton_en_ap;
        Circle circulo_der;
        Circle circulo_izq;
        Rectangle cambiar_Cargas;

        superficie = new Rectangle();
        superficie.setX(1150);
        superficie.setY(450);
        superficie.setHeight(80);
        superficie.setWidth(100);
        superficie.setFill(Color.rgb(43, 123, 228));
        superficie.setStroke(Color.rgb(0, 0, 0));

        boton_en_ap = new Circle();
        boton_en_ap.setCenterX(1200);
        boton_en_ap.setCenterY(490);
        boton_en_ap.setRadius(12);
        boton_en_ap.setFill(Color.rgb(224, 60, 28));
        boton_en_ap.setStroke(Color.rgb(0, 0, 0));

        circulo_der = new Circle();
        circulo_der.setCenterX(1160);
        circulo_der.setCenterY(540);
        circulo_der.setRadius(12);
        circulo_der.setFill(Color.rgb(43, 123, 228));
        circulo_der.setStroke(Color.rgb(0, 0, 0));

        circulo_izq = new Circle();
        circulo_izq.setCenterX(1240);
        circulo_izq.setCenterY(540);
        circulo_izq.setRadius(12);
        circulo_izq.setFill(Color.rgb(43, 123, 228));
        circulo_izq.setStroke(Color.rgb(0, 0, 0));

        cambiar_Cargas=new Rectangle();
        cambiar_Cargas.setY(480);
        cambiar_Cargas.setX(1230);
        cambiar_Cargas.setHeight(10);
        cambiar_Cargas.setWidth(10);
        cambiar_Cargas.setFill(Color.RED);
        cambiar_Cargas.setStroke(Color.BLACK);

        superficie.setOnMouseClicked(mouseEvent -> CapturarMotor(35, superficie));

        int[] Memoria_estado_original = new int[2];
        boton_en_ap.setOnMouseClicked(event -> {
            int carga = 0;
            int columna_motor=registro[registro.length-2][0]-1;
            int fila_motor=registro[registro.length-2][1];

            Color colorFinal = (Color) ArCircles[registro[registro.length - 2][0] - 1][registro[registro.length - 2][1] - 1].getStroke();

            if((colorFinal==Color.RED && Memoria_estado_original[0]==0)|| Memoria_estado_original[0]==1){

                Memoria_estado_original[0] = 1;

                if(3<=fila_motor && fila_motor<=7){
                    CargarPistas(columna_motor,0,1);
                    boton_en_ap.setFill(Color.RED);
                }else if(8<=fila_motor && fila_motor<=12){
                    CargarPistas(columna_motor,0,2);
                    boton_en_ap.setFill(Color.RED);
                } else if (fila_motor==1 || fila_motor==2 || fila_motor==13 || fila_motor==14) {
                    CargasBuses(fila_motor-1,0);
                }

                if(colorFinal==Color.BLACK){
                    if(3<=fila_motor && fila_motor<=7){
                        boton_en_ap.setFill(Color.BLACK);
                        CargarPistas(columna_motor, 1, 1);

                    }else if(8<=fila_motor && fila_motor<=12){
                        boton_en_ap.setFill(Color.BLACK);
                        CargarPistas(columna_motor,1,2);

                    }else if (fila_motor==1 || fila_motor==2 || fila_motor==13 || fila_motor==14) {
                        CargasBuses(fila_motor-1,1);

                    }
                }
             } else if ((colorFinal == Color.BLUE && Memoria_estado_original[0] == 0) || Memoria_estado_original[0] == 2) {

                Memoria_estado_original[0] = 2;

                if(3<=fila_motor && fila_motor<=7){
                    CargarPistas(columna_motor,0,1);
                    boton_en_ap.setFill(Color.BLUE);
                }else if(8<=fila_motor && fila_motor<=12){
                    CargarPistas(columna_motor,0,2);
                    boton_en_ap.setFill(Color.BLUE);
                } else if (fila_motor==1 || fila_motor==2 || fila_motor==13 || fila_motor==14) {
                    CargasBuses(fila_motor-1,0);
                }

                if(colorFinal==Color.BLACK){
                    if(3<=fila_motor && fila_motor<=7){
                        boton_en_ap.setFill(Color.BLACK);
                        CargarPistas(columna_motor, -1, 1);

                    }else if(8<=fila_motor && fila_motor<=12){
                        boton_en_ap.setFill(Color.BLACK);
                        CargarPistas(columna_motor,-1,2);

                    }else if (fila_motor==1 || fila_motor==2 || fila_motor==13 || fila_motor==14) {
                        CargasBuses(fila_motor-1,-1);

                    }
                }
            }

        });

        AnchorPane.getChildren().addAll(superficie, boton_en_ap, circulo_der, circulo_izq,cambiar_Cargas);


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

                circle.setCenterX((i + 2) * 30);
                circle.setCenterY((j + 2) * 30 + AuxSpace);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setOnMouseClicked(event -> ClickCirculo(circle));

                ArCircles[i][j] = circle;

                AnchorPane.getChildren().add(ArCircles[i][j]);
            }

            AuxSpace = 0;
        }
        ohm.setText("1");
        ohm.setPrefColumnCount(2);

        lastInt = AnchorPane.getChildren().size(); // Tamano del anchorpane, cantidad de cosas que es 518 (DEBE ESTAR AL FINAL, PUES SI ESTA EN EL PRINCIPIO, NO HAY NADA; ENTONCES EL PROGRAMA SE MUERE)

    }

}
