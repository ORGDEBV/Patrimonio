/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

import java.util.Date;

/**
 *
 * @author virtual
 */
public class FiltroDto {

    private String CAMPO;
    private int ID_DEPOSITO;
    private int ID_ESTADO;
    private Date FECHA_INI;
    private Date FECHA_FIN;

    public String getCAMPO() {
        return CAMPO;
    }

    public void setCAMPO(String CAMPO) {
        this.CAMPO = CAMPO;
    }

    public int getID_DEPOSITO() {
        return ID_DEPOSITO;
    }

    public void setID_DEPOSITO(int ID_DEPOSITO) {
        this.ID_DEPOSITO = ID_DEPOSITO;
    }

    public int getID_ESTADO() {
        return ID_ESTADO;
    }

    public void setID_ESTADO(int ID_ESTADO) {
        this.ID_ESTADO = ID_ESTADO;
    }

    public Date getFECHA_INI() {
        return FECHA_INI;
    }

    public void setFECHA_INI(Date FECHA_INI) {
        this.FECHA_INI = FECHA_INI;
    }

    public Date getFECHA_FIN() {
        return FECHA_FIN;
    }

    public void setFECHA_FIN(Date FECHA_FIN) {
        this.FECHA_FIN = FECHA_FIN;
    }

}
