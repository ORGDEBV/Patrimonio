package com.entidad;

import java.io.Serializable;
import java.sql.Date;

public class Usuario implements Serializable{
    
    private Integer ID_USUARIO;
    private Integer ID_PERSONAL;
    private String USUARIO;
    private String CONTRASENA;
    private Integer ID_PERFIL;
    private Integer PERFIL_PERSONALIZADO;
    private Integer CAMBIO_CONTRASENA;
    private String ACTIVO;
    private Date FECHA_REGISTRO;
    
    private boolean boolActivo;

    public Integer getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(Integer ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public Integer getID_PERSONAL() {
        return ID_PERSONAL;
    }

    public void setID_PERSONAL(Integer ID_PERSONAL) {
        this.ID_PERSONAL = ID_PERSONAL;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getCONTRASENA() {
        return CONTRASENA;
    }

    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }

    public Integer getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(Integer ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }

    public Integer getPERFIL_PERSONALIZADO() {
        return PERFIL_PERSONALIZADO;
    }

    public void setPERFIL_PERSONALIZADO(Integer PERFIL_PERSONALIZADO) {
        this.PERFIL_PERSONALIZADO = PERFIL_PERSONALIZADO;
    }

    public Integer getCAMBIO_CONTRASENA() {
        return CAMBIO_CONTRASENA;
    }

    public void setCAMBIO_CONTRASENA(Integer CAMBIO_CONTRASENA) {
        this.CAMBIO_CONTRASENA = CAMBIO_CONTRASENA;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

  
    public Date getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(Date FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public boolean isBoolActivo() {
        return boolActivo;
    }

    public void setBoolActivo(boolean boolActivo) {
        this.boolActivo = boolActivo;
    }
  
    
}
