package com.entidad;

import java.io.Serializable;

public class Marc245 implements Serializable{

    private int ID_245;
    private String A;
    private String B;
    private String C;
    private int ID_DOCUMENTAL;

    public Marc245() {
    }

    public int getID_245() {
        return ID_245;
    }

    public void setID_245(int ID_245) {
        this.ID_245 = ID_245;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    
}
