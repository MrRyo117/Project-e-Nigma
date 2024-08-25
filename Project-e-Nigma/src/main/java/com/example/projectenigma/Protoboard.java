package com.example.projectenigma;

public class Protoboard {

    //Atributos
    Hoyito protoboard[][] = new Hoyito[14][30];

    //Metodos
    public Protoboard() {

    }


    public void CambiarCargaBus(int Columna, int Fila){

        switch (Fila){
            case 0, 12:
            {
                //Positivo
                for (int j = 0; j < Columna; j++){
                    Hoyito hoyito = new Hoyito(12,12, true);
                    protoboard[Fila][j] = hoyito;
                }
                break;
            }
            case 1, 13:
            {
                //Negativo
                for (int j = 0; j < Columna; j++) {
                    Hoyito hoyito = new Hoyito(12, 12, false);
                    protoboard[Fila][j] = hoyito;
                }
                break;
            }
        }
    }

    public void CambiarCArgaHoyito(int Columna, int Fila){

    }

    public void EstadoHoyito(int Columna, int Fila){
        System.out.println("Fila:"+ Fila + " Columna:"+ Columna );
        System.out.println("Contenido:");
        System.out.println("Cargavolt : "+ protoboard[Fila][Columna].getCargaVolt());
        System.out.println("Cargaamp : "+ protoboard[Fila][Columna].getCargaAmp());
        System.out.println("Estado : "+ protoboard[Fila][Columna].isEstado());

    }


    public int length (){
        int length1 = protoboard.length;
        return length1;
    }
}