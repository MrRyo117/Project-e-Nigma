package com.example.projectenigma;

import javafx.scene.shape.Line;

import java.util.ArrayList;


public class Cable implements Insertables{
    //Atributos

    public int volt;
    public int carga;
    public int hoyitosConectados[][] = new int[2][2];
    public Line linea;


    //Metodos

    public Cable(Line linea, int[] hoyito1, int[] hoyito2, int carga) {
        this.linea = linea;
        this.hoyitosConectados[0][0] = hoyito1[0];
        this.hoyitosConectados[0][1] = hoyito1[1];
        this.hoyitosConectados[1][0] = hoyito2[0];
        this.hoyitosConectados[1][1] = hoyito2[1];
        this.carga = carga;
    }

    public void Conecion(){

    }

    public Cable(int volt, int carga, int hoyitoF, int hoyitoC) {
        this.volt = volt;
        this.carga = carga;
        this.hoyitosConectados[0][0] = hoyitoF;
        this.hoyitosConectados[0][1] = hoyitoC;
    }

    public void eliminarLinea(int index){

    }
    public int[][] getHoyitosConectados() {
        return hoyitosConectados;
    }

    public int getCarga() {
        return carga ;
    }



    @Override
    public ArrayList<Integer> CoordColumna() {
        return null;
    }

    @Override
    public ArrayList<Integer> CoordFilas() {
        return null;
    }

    public Line getLinea() {
        return linea;
    }

    public void setLinea(Line linea) {
        this.linea = linea;
    }
}
