package com.entidad;

import java.io.Serializable;

public class Marc260 implements Serializable{

    private int ID_260;
    private String A;
    private String B;
    private String C;
    private String E;
    private int ID_DOCUMENTAL;

    public Marc260() {
    }

    public int getID_260() {
        return ID_260;
    }

    public void setID_260(int ID_260) {
        this.ID_260 = ID_260;
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

    public String getE() {
        return E;
    }

    public void setE(String E) {
        this.E = E;
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

}
