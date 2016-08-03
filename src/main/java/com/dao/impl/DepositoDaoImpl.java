/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.DepositoDao;
import com.entidad.Deposito;
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
public class DepositoDaoImpl implements DepositoDao {
    
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<Deposito> listarDeposito() {
        List<Deposito> lDeposito = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        Deposito deposito = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_DEPOSITO_LISTAR}");
            rs = cs.executeQuery();
            while (rs.next()) {
                deposito = new Deposito();
                deposito.setID_DEPOSITO(rs.getInt(1));
                deposito.setDEPOSITO(rs.getString(2));
                deposito.setNRO_PISO(rs.getInt(3));
                lDeposito.add(deposito);
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
        return lDeposito;
    }

}
