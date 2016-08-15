/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.EstadoDao;
import com.entidad.EstadoProceso;
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
public class EstadoDaoImpl implements EstadoDao {
    
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<EstadoProceso> listarEstado() {
        List<EstadoProceso> lEstado = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        EstadoProceso estado = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_ESTADO_LISTAR}");
            rs = cs.executeQuery();
            while (rs.next()) {
                estado = new EstadoProceso();
                estado.setID_ESTADO(rs.getInt(1));
                estado.setDESCRIPCION(rs.getString(2));
                lEstado.add(estado);
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
        return lEstado;
    }

}
