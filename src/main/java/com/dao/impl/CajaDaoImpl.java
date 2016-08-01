package com.dao.impl;

import com.dao.CajaDao;
import com.dto.BandejaDto;
import com.entidad.Caja;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CajaDaoImpl implements CajaDao {

    CallableStatement cs = null;
    ResultSet rs = null;


    /**
     *
     * Crea un nuevo registro de una caja
     *
     * @param objCaja Objeto de la entidad Caja
     * @return respuesta de insercion
     */
    @Override
    public String[] insertarCaja(Caja objCaja) {
        String[] arreglo = new String[3];
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{CALL [PT].[SP_CAJA_Insertar](?,?,?,?,?,?,?)}";
            cs = cn.prepareCall(procedure);
            cs.setString(1, objCaja.getNRO_CAJA());
            cs.setString(2, objCaja.getCODIGO_MEMO());
            cs.setInt(3, objCaja.getNRO_EJEMPLARES());
            cs.setInt(4, objCaja.getID_DEPOSITO());
            cs.setString(5, objCaja.getSALA());
            cs.setString(6, objCaja.getCODIGO_MEMO());
            cs.setInt(7, objCaja.getID_USUARIO());
            rs = cs.executeQuery();
            if (rs.next()) {
                arreglo[0] = rs.getString(1);
                arreglo[1] = rs.getString(2);
                arreglo[2] = rs.getString(3);
            }

        } catch (Exception e) {
            System.out.println("ERROR EN insertarCaja" + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR sql EN insertarCaja" + ex.getMessage());
            }
        }
        return arreglo;
    }

    @Override
    public ArrayList<BandejaDto> bandejaPattrimonio() {
        ArrayList<BandejaDto> lstBandejaPatrimonio = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{call PT.SP_CAJA_ListarPorAreaEstado (?)}";
            cs = cn.prepareCall(procedure);
            cs.setString(1, "BANDEJA_PATRIMONIO");
            rs = cs.executeQuery();
            BandejaDto bp;
            while (rs.next()) {
                bp = new BandejaDto();
                bp.setID_CAJA(rs.getString(1));
                bp.setCODIGO_MEMO(rs.getString(2));
                bp.setNRO_CAJA(rs.getString(3));
                bp.setCODIGO_LISTADO(rs.getString(4));
                bp.setNRO_EJEMPLARES(rs.getString(5));
                bp.setAREA(rs.getString(6));
                bp.setSALA(rs.getString(7));
                bp.setFECHA(rs.getString(8));
                lstBandejaPatrimonio.add(bp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lstBandejaPatrimonio;
    }

    @Override
    public ArrayList<BandejaDto> bandejaCreado() {
        ArrayList<BandejaDto> lCreado = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        BandejaDto dto = null;
        try {
            String procedure = "{CALL PT.SP_CAJA_LISTAR_CREADO}";
            cs = cn.prepareCall(procedure);
            rs = cs.executeQuery();
            while (rs.next()) {
                dto = new BandejaDto();
                dto.setID_CAJA(rs.getString(1));
                dto.setCODIGO_MEMO(rs.getString(2));
                dto.setNRO_CAJA(rs.getString(3));
                dto.setCODIGO_LISTADO(rs.getString(4));
                dto.setNRO_EJEMPLARES(rs.getString(5));
                dto.setAREA(rs.getString(6));
                dto.setSALA(rs.getString(7));
                dto.setFECHA(rs.getString(8));
                lCreado.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lCreado;
    }

    @Override
    public int insertarAreaCajaEstado(int ID_CAJA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
