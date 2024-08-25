package com.example.projectenigma;

public class Protoboard {

    //Atributos
    Hoyito[][] protoboard = new Hoyito[30][14];

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

    public void CambiarCArgaPistas(int fila, int columna){


    }

    public void EstadoHoyito(int Columna, int Fila){
        System.out.println("Fila:"+ Fila + " Columna:"+ Columna );
        System.out.println("Contenido:");
        System.out.println("Cargavolt : "+ protoboard[Fila][Columna].getCargaVolt());
        System.out.println("Cargaamp : "+ protoboard[Fila][Columna].getCargaAmp());
        System.out.println("Estado : "+ protoboard[Fila][Columna].isEstado());

    }



}