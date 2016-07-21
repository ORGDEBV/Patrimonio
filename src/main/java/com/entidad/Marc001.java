package com.entidad;

import java.io.Serializable;

public class Marc001 implements Serializable{

    private int ID_001;
    private String A;
    private int ID_DOCUMENTAL;

    public Marc001() {
    }

    public int getID_001() {
        return ID_001;
    }

    public void setID_001(int ID_001) {
        this.ID_001 = ID_001;
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
