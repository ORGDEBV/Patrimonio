package com.dao.impl;

import com.dao.AreaDao;
import com.entidad.Area;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author virtual
 */
public class AreaDaoImpl implements AreaDao {

    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<Area> listar() {
        List<Area> lstArea = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        Area area = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_AREA_Listar}");
            rs = cs.executeQuery();
            while (rs.next()) {
                area = new Area();
                area.setID_AREA(rs.getInt(1));
                area.setAREA(rs.getString(2));
                lstArea.add(area);
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
        return lstArea;
    }

    @Override
    public Area buscar(Integer codigo) {
        Connection cn = cnSQL.getConnection();
        Area area = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_AREA_BUSCAR(?)}");
            cs.setInt(1, codigo);
            rs = cs.executeQuery();
            if (rs.next()) {
                area = new Area();
                area.setID_AREA(rs.getInt(1));
                area.setAREA(rs.getString(2));
            } else {
                System.out.println("No se encontró el área.");
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
        return area;
    }

    @Override
    public List<Object[]> listarCombo() {
        List<Object[]> lstArea = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL PT.SP_AREA_Listar}");
            rs = cs.executeQuery();
            while (rs.next()) {
                lstArea.add(new Object[]{rs.getInt(1), rs.getString(2)});
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
        return lstArea;
    }

}
