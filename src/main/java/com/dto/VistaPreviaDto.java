package com.dto;


public class VistaPreviaDto {
    
    private int ID;
    private String MFN;
    private String CANTIDAD_EJEMPLARES;

    public VistaPreviaDto() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
        
    public String getMFN() {
        return MFN;
    }

    public void setMFN(String MFN) {
        this.MFN = MFN;
    }

    public String getCANTIDAD_EJEMPLARES() {
        return CANTIDAD_EJEMPLARES;
    }

    public void setCANTIDAD_EJEMPLARES(String CANTIDAD_EJEMPLARES) {
        this.CANTIDAD_EJEMPLARES = CANTIDAD_EJEMPLARES;
    }   
    
    
}
