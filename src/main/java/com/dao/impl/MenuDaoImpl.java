/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.MenuDao;
import com.entidad.Menu;
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
public class MenuDaoImpl implements MenuDao {

    cnSQL SQL = new cnSQL();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<Menu> listar() {
        Connection cn = SQL.getConnection();
        List<Menu> lMenu = new ArrayList<>();
        Menu menu = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_MENU_LISTAR}");
            rs = cs.executeQuery();
            while (rs.next()) {
                menu = new Menu();
                menu.setID_MENU(rs.getInt(1));
                menu.setMENU(rs.getString(2));
                menu.setID_MENU_PADRE(rs.getInt(3));
                menu.setURL(rs.getString(4));
                menu.setMENU_HABILITADO(rs.getInt(5));
                menu.setICONO(rs.getString(6));
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
    public List<List<Menu>> buscarIdUsuarioCheckbox(int ID_USUARIO) {
        Connection cn = SQL.getConnection();
        List<List<Menu>> lMenu = new ArrayList<>();
        Menu menu = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_MENU_BUSCAR_ID_USUARIO_CHECKBOX(?)}");
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            int x = 0;
            while (rs.next()) {
                List<Menu> lItem = new ArrayList<>();
                menu = new Menu();
                menu.setID_MENU(rs.getInt(1));
                menu.setMENU(rs.getString(2));
                menu.setID_MENU_PADRE(rs.getInt(3));
                menu.setURL(rs.getString(4));
                menu.setMENU_HABILITADO(rs.getInt(5));
                menu.setICONO(rs.getString(6));
                menu.setASIGNADO(rs.getInt(7));
                lItem.add(menu);
                while (x != rs.getInt(3)) {
                    lMenu.add(x, null);
                    x++;
                }
                lMenu.add(x, lItem);
                x++;
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
}
