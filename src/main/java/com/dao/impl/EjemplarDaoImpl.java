/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.dao.EjemplarDao;
import com.dto.FichaEjemplarDto;
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
public class EjemplarDaoImpl implements EjemplarDao{
    
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public FichaEjemplarDto buscarFichaEjemplar(int ID_EJEMPLAR) {
        Connection cn = cnSQL.getConnection();
        FichaEjemplarDto ficha = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_FICHA_EJEMPLAR_BUSCAR(?)}");
            cs.setInt(1, ID_EJEMPLAR);
            rs = cs.executeQuery();
            if (rs.next()) {
                ficha = new FichaEjemplarDto();
                ficha.setM852i(rs.getString(1));
                ficha.setM583b(rs.getString(2));
                ficha.setM583c(rs.getString(3));
                ficha.setM583k(rs.getString(4));
                ficha.setM852j(rs.getString(5));
                ficha.setM852_3(rs.getString(6));
                ficha.setM852c(rs.getString(7));
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
        return ficha;
    }
    
}
