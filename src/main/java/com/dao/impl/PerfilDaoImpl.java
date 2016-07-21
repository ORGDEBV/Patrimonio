/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.PerfilDao;
import com.entidad.Perfil;
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
public class PerfilDaoImpl implements PerfilDao {

    cnSQL SQL = new cnSQL();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<Object[]> listarPerfilCombo() {
        Connection cn = SQL.getConnection();
        List<Object[]> lstPerfil = new ArrayList<>();
        try {
            cs = cn.prepareCall("{CALL PT._SP_PERFIL_Listar(?)}");
            cs.setString(1, "LISTADO_COMBO");
            rs = cs.executeQuery();
            while (rs.next()) {
                lstPerfil.add(new Object[]{rs.getInt(1), rs.getString(2)});
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
        return lstPerfil;
    }

}
