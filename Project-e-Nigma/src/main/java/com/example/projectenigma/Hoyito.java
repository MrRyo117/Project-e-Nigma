package com.example.projectenigma;

public class Hoyito {


    //Atributos
    private int Volt;
    private int carga;
    private boolean estado;


    //Contructor
    public Hoyito(){
        this.Volt = 0;
        this.carga = 0;
        this.estado = false;
    }

    public Hoyito(int Volt, int carga, boolean estado){
        this.Volt = Volt;
        this.carga = carga;
        this.estado = estado;
    }

    //Getters y Setters

    public int getVolt() {
        return Volt;
    }

    public void setVolt(int Volt) {
        this.Volt = Volt;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }



}
