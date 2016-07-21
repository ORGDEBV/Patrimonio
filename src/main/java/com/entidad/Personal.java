package com.entidad;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author virtual
 */
public class Personal implements Serializable{

    private Integer ID_PERSONAL;
    private String NOMBRE;
    private String PATERNO;
    private String DNI;
    private String MATERNO;
    private String CARGO;
    private String CORREO;
    private String ACTIVO;
    private Date FECHA_REGISTRO;
    private Integer ID_AREA;

    public Personal() {
    }
    
    
    // auxiliares
    private Integer ID_PERFIL;

    public Integer getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(Integer ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }



    public Integer getID_PERSONAL() {
        return ID_PERSONAL;
    }

    public void setID_PERSONAL(Integer ID_PERSONAL) {
        this.ID_PERSONAL = ID_PERSONAL;
    }


    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getPATERNO() {
        return PATERNO;
    }

    public void setPATERNO(String PATERNO) {
        this.PATERNO = PATERNO;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getMATERNO() {
        return MATERNO;
    }

    public void setMATERNO(String MATERNO) {
        this.MATERNO = MATERNO;
    }

    public String getCARGO() {
        return CARGO;
    }

    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
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

    public Integer getID_AREA() {
        return ID_AREA;
    }

    public void setID_AREA(Integer ID_AREA) {
        this.ID_AREA = ID_AREA;
    }

    

}
