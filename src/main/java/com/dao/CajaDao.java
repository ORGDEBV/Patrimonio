package com.dao;

import com.dto.BandejaDto;
import com.dto.EjemplarDocumentalDto;
import com.entidad.AreaCajaEstado;
import com.entidad.Caja;
import java.util.ArrayList;
import java.util.List;

public interface CajaDao {

    String[] insertarCaja(Caja objCaja);

    ArrayList<BandejaDto> bandejaPattrimonio();
ArrayList<BandejaDto> bandejaDeposito();
    ArrayList<BandejaDto> bandejaCreado();
    
    Caja buscarCaja(int ID_CAJA);
    
    List<EjemplarDocumentalDto> listarCajaEjemplarDocumental(int ID_CAJA);

    int insertarAreaCajaEstado(AreaCajaEstado ace);
}
