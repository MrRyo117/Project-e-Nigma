package com.example.projectenigma;

public class Cable {
    //Atributos

    public int volt;
    public int carga;
    public int hoyitosConectados[][] = new int[2][2];


    //Metodos

    public void Conecion(){

    }

    public Cable(int volt, int carga, int hoyitoF, int hoyitoC) {
        this.volt = volt;
        this.carga = carga;
        this.hoyitosConectados[0][0] = hoyitoF;
        this.hoyitosConectados[0][1] = hoyitoC;


    }
}
