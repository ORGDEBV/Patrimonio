package com.entidad;

import java.io.Serializable;

public class Marc100 implements Serializable{

    private int ID_100;
    private String A;
    private String D;
    private int ID_DOCUMENTAL;

    public Marc100() {
    }

    public int getID_100() {
        return ID_100;
    }

    public void setID_100(int ID_100) {
        this.ID_100 = ID_100;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    

}
