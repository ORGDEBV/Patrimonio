package com.entidad;

import java.io.Serializable;

public class Marc250 implements Serializable{

    private int ID_250;
    private String A;
    private int ID_DOCUMENTAL;

    public Marc250() {
    }

    public int getID_250() {
        return ID_250;
    }

    public void setID_250(int ID_250) {
        this.ID_250 = ID_250;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

}
