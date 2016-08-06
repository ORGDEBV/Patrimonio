package com.dao;

import com.dto.BandejaDto;
import com.dto.VistaPreviaDto;
import com.dto.EjemplarDocumentalDto;
import com.entidad.AreaCajaEstado;
import com.entidad.Caja;
import java.util.ArrayList;
import java.util.List;

public interface CajaDao {

    String[] insertarCaja(Caja objCaja);

    ArrayList<BandejaDto> bandejaPattrimonio();

    ArrayList<BandejaDto> bandejaCreado();
    
    public ArrayList<VistaPreviaDto> vistaPreviaCaja(Caja objCaja);

    Caja buscarCaja(int ID_CAJA);
    
    List<EjemplarDocumentalDto> listarCajaEjemplarDocumental(int ID_CAJA);

    int insertarAreaCajaEstado(AreaCajaEstado ace);
    
    int cajaDeposito(AreaCajaEstado ace, int ID_DEPOSITO);
    
    public String[] cajaActualizarVolumenEjemplar(Caja objCaja);
    
}
