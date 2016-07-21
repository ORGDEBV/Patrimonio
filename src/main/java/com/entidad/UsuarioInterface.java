package com.entidad;

import java.io.Serializable;

public class UsuarioInterface implements Serializable{

    private Integer ID_USUARIO_INTERFACE;
    private Integer ID_USUARIO;
    private String COLOR_FONDO;
    private String ORIENTACION_MENU;
    private String FOTO_USUARIO;

    public UsuarioInterface() {
    }

    public Integer getID_USUARIO_INTERFACE() {
        return ID_USUARIO_INTERFACE;
    }

    public void setID_USUARIO_INTERFACE(Integer ID_USUARIO_INTERFACE) {
        this.ID_USUARIO_INTERFACE = ID_USUARIO_INTERFACE;
    }

    public Integer getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(Integer ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getCOLOR_FONDO() {
        return COLOR_FONDO;
    }

    public void setCOLOR_FONDO(String COLOR_FONDO) {
        this.COLOR_FONDO = COLOR_FONDO;
    }

    public String getORIENTACION_MENU() {
        return ORIENTACION_MENU;
    }

    public void setORIENTACION_MENU(String ORIENTACION_MENU) {
        this.ORIENTACION_MENU = ORIENTACION_MENU;
    }

    public String getFOTO_USUARIO() {
        return FOTO_USUARIO;
    }

    public void setFOTO_USUARIO(String FOTO_USUARIO) {
        this.FOTO_USUARIO = FOTO_USUARIO;
    }

}
