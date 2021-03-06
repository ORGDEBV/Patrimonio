package com.dao;

import com.dto.BandejaDto;
import com.dto.ConsultaGeneral;
import com.dto.VistaPreviaDto;
import com.dto.EjemplarDocumentalDto;
import com.dto.FiltroDto;
import com.entidad.AreaCajaEstado;
import com.entidad.Caja;
import java.util.ArrayList;
import java.util.List;

public interface CajaDao {

    String[] insertarCaja(Caja objCaja);

    ArrayList<BandejaDto> bandejaPattrimonio();

    ArrayList<BandejaDto> bandejaDeposito();

    ArrayList<BandejaDto> bandejaCreado();

    ArrayList<VistaPreviaDto> vistaPreviaCaja(Caja objCaja);

    Caja buscarCaja(int ID_CAJA);

    List<EjemplarDocumentalDto> listarCajaEjemplarDocumental(int ID_CAJA);

    int insertarAreaCajaEstado(AreaCajaEstado ace);

    int cajaDeposito(AreaCajaEstado ace, int ID_DEPOSITO);    

    String[] cajaActualizarVolumenEjemplar(Caja objCaja);    

    ArrayList<BandejaDto> bandejaValidado();
    
    ArrayList<BandejaDto> bandejaPorAlmacenar(int ID_USUARIO);
    
    ArrayList<BandejaDto> bandejaAlmacenado(int ID_USUARIO);

    void reporteListadoEjemplaresCaja(String ruta, String[] param);
    
    List<ConsultaGeneral> bandejaGeneral(FiltroDto filtro);
    
}
