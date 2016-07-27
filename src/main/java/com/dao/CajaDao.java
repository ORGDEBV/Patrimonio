package com.dao;

import com.dto.BandejaDto;
import com.entidad.Caja;
import java.util.ArrayList;

public interface CajaDao {

    String insertarCaja(Caja objCaja);

    ArrayList<BandejaDto> bandejaPattrimonio();

    ArrayList<BandejaDto> bandejaCreado();

}
