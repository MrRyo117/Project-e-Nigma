package com.example.projectenigma;

import java.util.ArrayList;

public class Resistencia implements Insertables{
    int carga;
    int ValorResistencia;

    public Resistencia(int valorResistencia) {
        ValorResistencia = valorResistencia;
    }

    public int getCarga() {
        return carga;
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