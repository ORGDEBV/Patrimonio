package com.entidad;

import java.io.Serializable;

public class Perfil implements Serializable{

    private Integer ID_PERFIL;
    private String PERFIL;
    private String DESCRIPCION_PERFIL;
    private String ACTIVO;

    public Perfil() {
    }

    public Integer getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(Integer ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }

    public String getPERFIL() {
        return PERFIL;
    }

    public void setPERFIL(String PERFIL) {
        this.PERFIL = PERFIL;
    }

    public String getDESCRIPCION_PERFIL() {
        return DESCRIPCION_PERFIL;
    }

    public void setDESCRIPCION_PERFIL(String DESCRIPCION_PERFIL) {
        this.DESCRIPCION_PERFIL = DESCRIPCION_PERFIL;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

}
