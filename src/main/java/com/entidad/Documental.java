package com.entidad;

import java.io.Serializable;

public class Documental implements Serializable{

    private int ID_DOCUMENTAL;
    private int ID_CAJA;
    private String FECHA_REGISTRO;
    private String MFN;
    private String A082;
    private String _082_2;

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

    public String getMFN() {
        return MFN;
    }

    public void setMFN(String MFN) {
        this.MFN = MFN;
    }

    public String getA082() {
        return A082;
    }

    public void setA082(String A082) {
        this.A082 = A082;
    }

    public String get082_2() {
        return _082_2;
    }

    public void set082_2(String _082_2) {
        this._082_2 = _082_2;
    }

    
    
    
    

}
