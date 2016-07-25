package com.dao;

import com.entidad.Caja;
import java.util.ArrayList;

public interface CajaDao {
     
    String insertarCaja(Caja objCaja);
    ArrayList<Object[]> bandejaPattrimonio();
    
}
