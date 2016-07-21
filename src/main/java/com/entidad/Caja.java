package com.entidad;

import java.io.Serializable;

public class Caja implements Serializable{

    private int ID_CAJA;
    private String NRO_CAJA;
    private String CODIGO_LISTADO;
    private int NRO_EJEMPLARES;
    private int ID_DEPOSITO;
    private String SALA;
    private String CODIGO_MEMO;
    private int ID_USUARIO;
    private String FECHA_REGISTRO;

    public Caja() {
    }
      

    public int getID_CAJA() {
        return ID_CAJA;
    }

    public void setID_CAJA(int ID_CAJA) {
        this.ID_CAJA = ID_CAJA;
    }

    public String getNRO_CAJA() {
        return NRO_CAJA;
    }

    public void setNRO_CAJA(String NRO_CAJA) {
        this.NRO_CAJA = NRO_CAJA;
    }

    public String getCODIGO_LISTADO() {
        return CODIGO_LISTADO;
    }

    public void setCODIGO_LISTADO(String CODIGO_LISTADO) {
        this.CODIGO_LISTADO = CODIGO_LISTADO;
    }

    public int getNRO_EJEMPLARES() {
        return NRO_EJEMPLARES;
    }

    public void setNRO_EJEMPLARES(int NRO_EJEMPLARES) {
        this.NRO_EJEMPLARES = NRO_EJEMPLARES;
    }

    public int getID_DEPOSITO() {
        return ID_DEPOSITO;
    }

    public void setID_DEPOSITO(int ID_DEPOSITO) {
        this.ID_DEPOSITO = ID_DEPOSITO;
    }

    public String getSALA() {
        return SALA;
    }

    public void setSALA(String SALA) {
        this.SALA = SALA;
    }

    public String getCODIGO_MEMO() {
        return CODIGO_MEMO;
    }

    public void setCODIGO_MEMO(String CODIGO_MEMO) {
        this.CODIGO_MEMO = CODIGO_MEMO;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(String FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

}
