package com.entidad;

import java.io.Serializable;

public class PerfilMenu implements Serializable{

    private Integer ID_PERFIL;
    private Integer ID_MENU;
    private String LECTURA;
    private String ESCRITURA;
    private String ELIMINACION;

    public PerfilMenu() {
    }

    public Integer getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(Integer ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }

    public Integer getID_MENU() {
        return ID_MENU;
    }

    public void setID_MENU(Integer ID_MENU) {
        this.ID_MENU = ID_MENU;
    }



    public String getLECTURA() {
        return LECTURA;
    }

    public void setLECTURA(String LECTURA) {
        this.LECTURA = LECTURA;
    }

    public String getESCRITURA() {
        return ESCRITURA;
    }

    public void setESCRITURA(String ESCRITURA) {
        this.ESCRITURA = ESCRITURA;
    }

    public String getELIMINACION() {
        return ELIMINACION;
    }

    public void setELIMINACION(String ELIMINACION) {
        this.ELIMINACION = ELIMINACION;
    }

}
