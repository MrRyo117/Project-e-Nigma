package com.example.projectenigma;

import java.util.ArrayList;

public class Resistencia implements Insertables{

    public Resistencia(int valorResistencia) {
        ValorResistencia = valorResistencia;
    }

    int ValorResistencia;
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
