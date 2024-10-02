package com.example.projectenigma;

import java.util.ArrayList;

public class Cable implements Insertables{
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

    @Override
    public void getCarga(int carga) {

    }

    @Override
    public ArrayList<Integer> CoordColumna() {
        return null;
    }

    @Override
    public ArrayList<Integer> CoordFilas() {
        return null;
    }
}
