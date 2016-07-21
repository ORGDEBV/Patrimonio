package com.entidad;

import java.io.Serializable;

/**
 *
 * @author virtual
 */
public class Area implements Serializable{
    
    private Integer ID_AREA;
    private String AREA;

    public Integer getID_AREA() {
        return ID_AREA;
    }

    public void setID_AREA(Integer ID_AREA) {
        this.ID_AREA = ID_AREA;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }
    
}
