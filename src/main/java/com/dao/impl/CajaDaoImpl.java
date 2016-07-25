package com.dao.impl;

import com.dao.CajaDao;
import com.entidad.Caja;
import com.entidad.Ejemplar;
import com.entidad.Marc001;
import com.entidad.Marc017;
import com.entidad.Marc100;
import com.entidad.Marc245;
import com.entidad.Marc250;
import com.entidad.Marc260;
import com.entidad.Marc300;
import com.entidad.Marc500;
import com.entidad.Marc504;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class CajaDaoImpl implements CajaDao {

    CallableStatement cs = null;
    ResultSet rs = null;
    
    @Override
    public String insertarCaja(Caja objCaja) {
        try {
            Connection cn = cnSQL.getConnection();
            String procedure = "{call [PT].[SP_CAJA_Insertar](?,?,?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(procedure);
            cs.setString(1, objCaja.getNRO_CAJA());
            cs.setString(2, objCaja.getCODIGO_LISTADO());
            cs.setInt(3, objCaja.getNRO_EJEMPLARES());
            cs.setInt(4,objCaja.getID_DEPOSITO());
            cs.setString(5, objCaja.getSALA());
            cs.setString(6, objCaja.getCODIGO_MEMO());
            cs.setInt(7, objCaja.getID_USUARIO());
            
        } catch (Exception e) {
        }
        
        return "";
    }

   
}
