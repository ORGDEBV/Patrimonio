package com.dao.impl;

import com.dao.DocumentalDao;
import com.dto.FichaDocumentalDto;
import com.entidad.Ejemplar;
import com.entidad.Marc001;
import com.entidad.Marc017;
import com.entidad.Marc100;
import com.entidad.Marc245;
import com.entidad.Marc250;
import com.entidad.Marc260;
import com.entidad.Marc300;
import com.entidad.Marc500;
import com.entidad.Marc504;
import com.util.cnSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DocumentalDaoImpl implements DocumentalDao{
    
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public String insertarDocumental(Marc001 marc001, Marc017 marc017, Marc100 marc100, Marc245 marc245, Marc250 marc250, Marc260 marc260, Marc300 marc300, Marc504 marc504, List<Marc500> listaMarc500, List<Ejemplar> listaEjemplar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FichaDocumentalDto buscarFichaDocumental(int ID_DOCUMENTAL) {
        Connection cn = cnSQL.getConnection();
        FichaDocumentalDto ficha = null;
        List<String> lM500a = new ArrayList<>();
        try {
            cs = cn.prepareCall("{CALL PT.SP_FICHA_BUSCAR(?)}");
            cs.setInt(1, ID_DOCUMENTAL);
            rs = cs.executeQuery();
            while (rs.next()) {
                ficha = new FichaDocumentalDto();
                ficha.setM082a(rs.getString(1));
                ficha.setM100a(rs.getString(2));
                ficha.setM100d(rs.getString(3));
                ficha.setM245a(rs.getString(4));
                ficha.setM245b(rs.getString(5));
                ficha.setM245c(rs.getString(6));
                ficha.setM250a(rs.getString(7));
                ficha.setM260a(rs.getString(8));
                ficha.setM260b(rs.getString(9));
                ficha.setM260c(rs.getString(10));
                ficha.setM300a(rs.getString(11));
                ficha.setM300b(rs.getString(12));
                ficha.setM300c(rs.getString(13));
                lM500a.add(rs.getString(14));
                ficha.setM500a(lM500a);
                ficha.setM504a(rs.getString(15));
                ficha.setM017a(rs.getString(16));
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
