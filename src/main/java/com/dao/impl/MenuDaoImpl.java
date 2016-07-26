/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.MenuDao;
import com.entidad.Menu;
import com.util.cnSQL;
import java.io.Serializable;
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
public class MenuDaoImpl implements MenuDao, Serializable {

    cnSQL SQL = new cnSQL();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<Menu> buscarIdUsuario(int ID_USUARIO) {
        Connection cn = SQL.getConnection();
        List<Menu> lMenu = new ArrayList<>();
        Menu menu = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_MENU_BUSCAR_ID_USUARIO(?)}");
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                menu = new Menu();
                menu.setID_MENU(rs.getInt(1));
                menu.setMENU(rs.getString(2));
                menu.setID_MENU_PADRE(rs.getInt(3));
                menu.setURL(rs.getString(4));
                menu.setMENU_HABILITADO(rs.getInt(5));
                menu.setICONO(rs.getString(6));
                menu.setASIGNADO(rs.getInt(7));
                lMenu.add(menu);
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
        return lMenu;
    }

    @Override
    public List<Menu> buscarMenuPadre(int ID_USUARIO) {
        Connection cn = SQL.getConnection();
        List<Menu> lMenu = new ArrayList<>();
        Menu menu = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_MENU_BUSCAR_PADRE(?)}");
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                menu = new Menu();
                menu.setID_MENU(rs.getInt(1));
                menu.setMENU(rs.getString(2));
                menu.setID_MENU_PADRE(rs.getInt(3));
                menu.setURL(rs.getString(4));
                menu.setMENU_HABILITADO(rs.getInt(5));
                menu.setICONO(rs.getString(6));
                menu.setASIGNADO(rs.getInt(7));
                menu.setPERSONALIZADO(rs.getInt(8));
                lMenu.add(menu);
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
        return lMenu;
    }

    @Override
    public List<Menu> buscarMenuHijo(int ID_USUARIO, int ID_MENU_PADRE) {
        Connection cn = SQL.getConnection();
        List<Menu> lMenu = new ArrayList<>();
        Menu menu = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_MENU_BUSCAR_HIJO(?,?)}");
            cs.setInt(1, ID_USUARIO);
            cs.setInt(2, ID_MENU_PADRE);
            rs = cs.executeQuery();
            while (rs.next()) {
                menu = new Menu();
                menu.setID_MENU(rs.getInt(1));
                menu.setMENU(rs.getString(2));
                menu.setID_MENU_PADRE(rs.getInt(3));
                menu.setURL(rs.getString(4));
                menu.setMENU_HABILITADO(rs.getInt(5));
                menu.setICONO(rs.getString(6));
                menu.setASIGNADO(rs.getInt(7));
                menu.setPERSONALIZADO(rs.getInt(8));
                lMenu.add(menu);
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
        return lMenu;
    }

    @Override
    public int restablecerPerfil(int ID_USUARIO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
