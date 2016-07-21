/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.PersonalDao;
import com.entidad.Personal;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author virtual
 */
public class PersonalDaoImpl implements PersonalDao {

    cnSQL SQL = new cnSQL();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public int insertar(Personal p) {
        int flag = 0;
        String msg = "";
        Connection cn = SQL.getConnection();

        try {
            cs = cn.prepareCall("{CALL [PT].[SP_PERSONAL_USUARIO_Insertar](?,?,?,?,?,?,?,?)}");
            if (p.getID_PERSONAL() == null) {
                p.setID_PERSONAL(0);
            }
            cs.setInt(1, p.getID_PERSONAL());
            cs.setString(2, p.getNOMBRE());
            cs.setString(3, p.getPATERNO());
            cs.setString(4, p.getMATERNO());
            cs.setString(5, p.getDNI());
            cs.setString(6, p.getCARGO());
            cs.setString(7, p.getCORREO());
            cs.setInt(8, p.getID_AREA());
            rs = cs.executeQuery();
            if (rs.next()) {
                flag = rs.getInt(1);
                msg = rs.getString(2);
                System.out.println(msg);
            }

        } catch (Exception e) {
            System.out.println("ERROR EN INSERTAR1" + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR EN INSERTAR2" + ex.getMessage());
            }
        }

        return flag;
    }

    @Override
    public int actualizar(Personal p) {
        //PT.SP_PERSONAL_Modificar
        int flag = 0;
        String msg = "";
        Connection cn = SQL.getConnection();

        try {
            cs = cn.prepareCall("{CALL [PT].[PT.SP_PERSONAL_Modificar](?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getID_PERSONAL());
            cs.setString(2, p.getNOMBRE());
            cs.setString(3, p.getPATERNO());
            cs.setString(4, p.getMATERNO());
            cs.setString(5, p.getDNI());
            cs.setString(6, p.getCARGO());
            cs.setString(7, p.getCORREO());
            cs.setInt(8, p.getID_AREA());
            cs.setInt(9, p.getID_PERFIL());
            rs = cs.executeQuery();
            if (rs.next()) {
                flag = rs.getInt(1);
                msg = rs.getString(2);
                System.out.println(msg);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return flag;

    }

    @Override
    public List<Personal> listar() {
        Connection cn = SQL.getConnection();
        List<Personal> lstPersonal = new ArrayList<>();
        Personal p;
        try {
            cs = cn.prepareCall("{CALL PT.SP_PERSONAL_Listar(?)}");

            cs.setString(1, "LISTADO_GENERAL");
            rs = cs.executeQuery();
            //SELECT ID_PERSONAL,DNI,NOMBRE,PATERNO,MATERNO,CARGO,CORREO,ID_AREA 
            while (rs.next()) {
                p = new Personal();
                p.setID_PERSONAL(rs.getInt(1));
                p.setDNI(rs.getString(2));
                p.setNOMBRE(rs.getString(3));
                p.setPATERNO(rs.getString(4));
                p.setMATERNO(rs.getString(5));
                p.setCARGO(rs.getString(6));
                p.setCORREO(rs.getString(7));
                p.setFECHA_REGISTRO(rs.getDate(8));
                p.setID_AREA(rs.getInt(9));
                lstPersonal.add(p);
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
        return lstPersonal;
    }

    @Override
    public Personal buscar(Integer codigo) {
        Connection cn = SQL.getConnection();
        Personal personal = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_PERSONAL_BUSCAR(?)}");
            cs.setInt(1, codigo);
            rs = cs.executeQuery();
            if (rs.next()) {
                personal = new Personal();
                personal.setID_PERSONAL(rs.getInt(1));
                personal.setDNI(rs.getString(2));
                personal.setNOMBRE(rs.getString(3));
                personal.setPATERNO(rs.getString(4));
                personal.setMATERNO(rs.getString(5));
                personal.setCARGO(rs.getString(6));
                personal.setCORREO(rs.getString(7));
                personal.setFECHA_REGISTRO(rs.getDate(8));
                personal.setID_AREA(rs.getInt(9));
            } else {
                System.out.println("No se encontró al personal.");
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
        return personal;
    }

    @Override
    public List<Object[]> listarCombo() {
        Connection cn = SQL.getConnection();
        List<Object[]> lstPersonal = new ArrayList<>();
        try {
            cs = cn.prepareCall("{CALL PT.SP_PERSONAL_Listar(?)}");
            cs.setString(1, "LISTADO_COMBO");
            rs = cs.executeQuery();
            while (rs.next()) {
                lstPersonal.add(new Object[]{rs.getInt(1), rs.getString(2)});
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
        return lstPersonal;
    }

}
