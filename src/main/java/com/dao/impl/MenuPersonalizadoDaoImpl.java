/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.MenuPersonalizadoDao;
import com.entidad.MenuPersonalizado;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author virtual
 */
public class MenuPersonalizadoDaoImpl implements MenuPersonalizadoDao {
    
    cnSQL SQL = new cnSQL();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public int insert(MenuPersonalizado mp) {
        int out = 0;
        String msg = "";
        Connection cn = SQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL [PT].[SP_USUARIO_MENU_PERSONALIZADO_Insertar](?,?,?,?,?,?,?)}");
            cs.setInt(1, mp.getID_USUARIO());
            cs.setInt(2, mp.getID_MENU());
            cs.setString(3, mp.getLECTURA());
            cs.setString(4, mp.getESCRITURA());
            cs.setString(5, mp.getELIMINACION());
            cs.setInt(6, mp.getID_USUARIO_REGISTRO());
            cs.setInt(7, mp.getFLAG());
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

}
