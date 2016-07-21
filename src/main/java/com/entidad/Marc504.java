package com.entidad;

import java.io.Serializable;

public class Marc504 implements Serializable{

    private int ID_504;
    private String A;
    private int ID_DOCUMENTAL;

    public Marc504() {
    }

    public int getID_504() {
        return ID_504;
    }

    public void setID_504(int ID_504) {
        this.ID_504 = ID_504;
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
