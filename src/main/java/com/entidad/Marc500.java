package com.entidad;

import java.io.Serializable;

public class Marc500 implements Serializable{

    private int ID_500;
    private String A;
    private int ID_DOCUMENTAL;

    public Marc500() {
    }

    public int getID_500() {
        return ID_500;
    }

    public void setID_500(int ID_500) {
        this.ID_500 = ID_500;
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
