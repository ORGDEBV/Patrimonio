package com.entidad;

import java.io.Serializable;

public class AreaCajaEstado implements Serializable {

    private int ID_CAJA;
    private int ID_AREA;
    private int ID_ESTADO_PROCESO;
    private String FECHA;
    private int ID_USUARIO;

    public AreaCajaEstado() {
    }

    public int getID_CAJA() {
        return ID_CAJA;
    }

    public void setID_CAJA(int ID_CAJA) {
        this.ID_CAJA = ID_CAJA;
    }

    public int getID_AREA() {
        return ID_AREA;
    }

    public void setID_AREA(int ID_AREA) {
        this.ID_AREA = ID_AREA;
    }

    public int getID_ESTADO_PROCESO() {
        return ID_ESTADO_PROCESO;
    }

    public void setID_ESTADO_PROCESO(int ID_ESTADO_PROCESO) {
        this.ID_ESTADO_PROCESO = ID_ESTADO_PROCESO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

}
