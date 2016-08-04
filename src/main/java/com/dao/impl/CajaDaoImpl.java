package com.dao.impl;

import com.dao.CajaDao;
import com.dto.BandejaDto;
import com.dto.EjemplarDocumentalDto;
import com.entidad.AreaCajaEstado;
import com.entidad.Caja;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public int insertarAreaCajaEstado(AreaCajaEstado ace) {
        int out = 0;
        String msg = "";
        Connection cn = cnSQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL [PT].[SP_AREA_CAJA_ESTADO_Insertar](?,?,?,?)}");
            cs.setInt(1, ace.getID_CAJA());
            cs.setInt(2, ace.getID_AREA());
            cs.setInt(3, ace.getID_ESTADO_PROCESO());
            cs.setInt(4, ace.getID_USUARIO());
            rs = cs.executeQuery();
            if (rs.next()) {
                out = rs.getInt(1);
                msg = rs.getString(2);
            }
        } catch (Exception e) {
            System.out.println("Error de consulta: " + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return out;
    }

    @Override
    public Caja buscarCaja(int ID_CAJA) {
        Caja caja = new Caja();
        Connection cn = cnSQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL [PT].[SP_CAJA_BUSCAR](?)}");
            cs.setInt(1, ID_CAJA);
            rs = cs.executeQuery();
            if (rs.next()) {
                caja.setID_CAJA(ID_CAJA);
                caja.setNRO_CAJA(rs.getString(2));
                caja.setSALA(rs.getString(3));
                caja.setCODIGO_MEMO(rs.getString(4));
                caja.setCODIGO_LISTADO(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error de consulta:" + e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return caja;
    }

    @Override
    public List<EjemplarDocumentalDto> listarCajaEjemplarDocumental(int ID_CAJA) {
        List<EjemplarDocumentalDto> lEjeDocDto = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        EjemplarDocumentalDto ed = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_CAJA_EJEMPLAR_DOCUMENTAL_LISTAR(?)}");
            cs.setInt(1, ID_CAJA);
            rs = cs.executeQuery();
            int aux = 1;
            while (rs.next()) {
                ed = new EjemplarDocumentalDto();
                ed.setNRO_EJEMPLAR(aux);
                ed.setID_EJEMPLAR(rs.getInt(1));
                ed.setCODIGO_BARRAS(rs.getString(2));
                ed.setMFN(rs.getString(3));
                ed.setID_DOCUMENTAL(rs.getInt(4));
                if (rs.getInt(5) == 1) {
                    ed.setCLASS_VALIDADO("GreenBack");
                } else if (rs.getInt(5) == 0) {
                    ed.setCLASS_VALIDADO("RedBack");
                }
                lEjeDocDto.add(ed);
                aux++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lEjeDocDto;
    }

    @Override

    public int cajaDeposito(AreaCajaEstado ace, int ID_DEPOSITO) {
        int insert = 0;
        Connection cn = cnSQL.getConnection();
        try {
            cn.setAutoCommit(false);
            cs = cn.prepareCall("{CALL [PT].[SP_AREA_CAJA_ESTADO_Insertar](?,?,?,?)}");
            cs.setInt(1, ace.getID_CAJA());
            cs.setInt(2, ace.getID_AREA());
            cs.setInt(3, ace.getID_ESTADO_PROCESO());
            cs.setInt(4, ace.getID_USUARIO());
            cs.executeQuery();

            cs = cn.prepareCall("{CALL [PT].[SP_CAJA_DEPOSITO_UPDATE](?,?)}");
            cs.setInt(1, ace.getID_CAJA());
            cs.setInt(2, ace.getID_AREA());
            cs.executeQuery();

            cn.commit();
            insert = 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }
        } finally {
            try {
                cn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return insert;
        }

    }

    @Override
    public ArrayList<BandejaDto> bandejaDeposito() {
        ArrayList<BandejaDto> lstBandejaPatrimonio = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{call PT.SP_CAJA_ListarPorAreaEstado (?)}";
            cs = cn.prepareCall(procedure);
            cs.setString(1, "BANDEJA_DEPOSITO");
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
                bp.setID_CAJA(rs.getString(9));
                bp.setDEPOSITO(rs.getString(10));
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

}
