package com.entidad;

import java.io.Serializable;

public class Documental implements Serializable{

    private int ID_DOCUMENTAL;
    private int ID_CAJA;
    private String FECHA_REGISTRO;

    public Documental() {
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public int getID_CAJA() {
        return ID_CAJA;
    }

    public void setID_CAJA(int ID_CAJA) {
        this.ID_CAJA = ID_CAJA;
    }

    public String getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(String FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

}
