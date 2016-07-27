package com.entidad;

import java.io.Serializable;

public class MenuPersonalizado implements Serializable{

    private Integer ID_USUARIO;
    private Integer ID_MENU;
    private String LECTURA;
    private String ESCRITURA;
    private String ELIMINACION;
    private Integer ID_USUARIO_REGISTRO;
    private String FECHA_REGISTRO;
    private Integer FLAG;

    public MenuPersonalizado() {
    }

    public Integer getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(Integer ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
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

    public Integer getID_USUARIO_REGISTRO() {
        return ID_USUARIO_REGISTRO;
    }

    public void setID_USUARIO_REGISTRO(Integer ID_USUARIO_REGISTRO) {
        this.ID_USUARIO_REGISTRO = ID_USUARIO_REGISTRO;
    }

    public String getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(String FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public Integer getFLAG() {
        return FLAG;
    }

    public void setFLAG(Integer FLAG) {
        this.FLAG = FLAG;
    }
    
}
