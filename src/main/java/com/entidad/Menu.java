package com.entidad;

import java.io.Serializable;

/**
 *
 * @author virtual
 */
public class Menu implements Serializable{

    private int ID_MENU;
    private String MENU;
    private int ID_MENU_PADRE;
    private String URL;
    private int MENU_HABILITADO;
    private String DESCRIPCION_MENU;
    private String ICONO;
    private int ASIGNADO;

    public int getID_MENU() {
        return ID_MENU;
    }

    public void setID_MENU(int ID_MENU) {
        this.ID_MENU = ID_MENU;
    }

    public String getMENU() {
        return MENU;
    }

    public void setMENU(String MENU) {
        this.MENU = MENU;
    }

    public int getID_MENU_PADRE() {
        return ID_MENU_PADRE;
    }

    public void setID_MENU_PADRE(int ID_MENU_PADRE) {
        this.ID_MENU_PADRE = ID_MENU_PADRE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getMENU_HABILITADO() {
        return MENU_HABILITADO;
    }

    public void setMENU_HABILITADO(int MENU_HABILITADO) {
        this.MENU_HABILITADO = MENU_HABILITADO;
    }

    public String getDESCRIPCION_MENU() {
        return DESCRIPCION_MENU;
    }

    public void setDESCRIPCION_MENU(String DESCRIPCION_MENU) {
        this.DESCRIPCION_MENU = DESCRIPCION_MENU;
    }

    public String getICONO() {
        return ICONO;
    }

    public void setICONO(String ICONO) {
        this.ICONO = ICONO;
    }

    public int getASIGNADO() {
        return ASIGNADO;
    }

    public void setASIGNADO(int ASIGNADO) {
        this.ASIGNADO = ASIGNADO;
    }
}
