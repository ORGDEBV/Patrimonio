package com.dao;

import com.dto.BandejaPatrimonioDto;
import com.entidad.Caja;
import java.util.ArrayList;

public interface CajaDao {
     
    String insertarCaja(Caja objCaja);
   
     ArrayList<BandejaPatrimonioDto> bandejaPattrimonio();
    
}
