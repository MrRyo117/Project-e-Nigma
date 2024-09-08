package com.example.projectenigma;

public class Hoyito {


    //Atributos
    private int cargaVolt;
    private int cargaAmp;
    private boolean estado;

    

    //Contructor
    public Hoyito(){
        this.cargaVolt = 0;
        this.cargaAmp = 0;
        this.estado = false;
    }

    public Hoyito(int cargaVolt, int cargaAmp, boolean estado){
        this.cargaVolt = cargaVolt;
        this.cargaAmp = cargaAmp;
        this.estado = estado;
    }

    //Getters y Setters

    public int getCargaVolt() {
        return cargaVolt;
    }

    public void setCargaVolt(int cargaVolt) {
        this.cargaVolt = cargaVolt;
    }

    public int getCargaAmp() {
        return cargaAmp;
    }

    public void setCargaAmp(int cargaAmp) {
        this.cargaAmp = cargaAmp;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }



}
