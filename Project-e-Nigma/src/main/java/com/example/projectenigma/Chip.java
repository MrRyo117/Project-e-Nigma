package com.example.projectenigma;

import java.util.ArrayList;

public class Chip implements Insertables{
    int Carga;

    public Chip() {

    }

    public int getCarga() {
        return Carga;
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
