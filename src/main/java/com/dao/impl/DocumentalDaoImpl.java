package com.dao.impl;

import com.dao.DocumentalDao;
import com.dto.FichaDocumentalDto;
import com.entidad.Documental;
import com.entidad.Ejemplar;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentalDaoImpl implements DocumentalDao {

    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public int insertarDocumental(Documental documental, List<Marc017> listaMarc017, List<Marc100> listaMarc100, List<Marc245> listaMarc245, List<Marc250> listaMarc250, List<Marc260> listaMarc260, List<Marc300> listaMarc300, List<Marc504> listaMarc504, List<Marc500> listaMarc500, List<Ejemplar> listaEjemplar) {
        int respuesta = 0;
        String[] respuestaSQL = new String[3];
        Connection cn = cnSQL.getConnection();
        try {
            cn.setAutoCommit(false);
            String procedure = "{CALL [PT].[SP_DOCUMENTAL_Insertar] (?,?,?,?)}";
            cs = cn.prepareCall(procedure);
            cs.setInt(1, documental.getID_CAJA());
            cs.setString(2, documental.getMFN());
            cs.setString(3, documental.getA082());
            cs.setString(4, documental.get082_2());            
            rs = cs.executeQuery();
            if (rs.next()) {
                respuestaSQL[0] = rs.getString(1);
                respuestaSQL[1] = rs.getString(2);
                respuestaSQL[2] = rs.getString(3);
            }            
            documental.setID_DOCUMENTAL(Integer.parseInt(respuestaSQL[2]));
            
            String procedure2 = "{CALL [PT].[SP_MARC017_Insertar] (?,?)}";
            cs = cn.prepareCall(procedure2);
            for(Marc017 m017:listaMarc017){
                cs.setString(1, m017.getA());
                cs.setInt(2, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure3 = "{CALL [PT].[SP_MARC100_Insertar] (?,?,?)}";
            cs = cn.prepareCall(procedure3);
            for(Marc100 m100:listaMarc100){
                cs.setString(1, m100.getA());
                cs.setString(2, m100.getD());
                cs.setInt(3, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure4 = "{CALL [PT].[SP_MARC245_Insertar] (?,?,?,?)}";
            cs = cn.prepareCall(procedure4);
            for(Marc245 m245:listaMarc245){
                cs.setString(1, m245.getA());
                cs.setString(2, m245.getB());
                cs.setString(3, m245.getC());
                cs.setInt(4, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure5 = "{CALL [PT].[SP_MARC250_Insertar] (?,?)}";
            cs = cn.prepareCall(procedure5);
            for(Marc250 m250:listaMarc250){
                cs.setString(1, m250.getA());
                cs.setInt(2, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure6 = "{CALL [PT].[SP_MARC260_Insertar] (?,?,?,?,?)}";
            cs = cn.prepareCall(procedure6);
            for(Marc260 m260:listaMarc260){
                cs.setString(1, m260.getA());
                cs.setString(2, m260.getB());
                cs.setString(3, m260.getC());
                cs.setString(4, m260.getE());
                cs.setInt(5, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure7 = "{CALL [PT].[SP_MARC300_Insertar] (?,?,?,?)}";
            cs = cn.prepareCall(procedure7);
            for(Marc300 m300:listaMarc300){
                cs.setString(1, m300.getA());
                cs.setString(2, m300.getB());
                cs.setString(3, m300.getC());
                cs.setInt(4, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure8 = "{CALL [PT].[SP_MARC500_Insertar] (?,?)}";
            cs = cn.prepareCall(procedure8);
            for(Marc500 m500:listaMarc500){
                cs.setString(1, m500.getA());
                cs.setInt(2, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure9 = "{CALL [PT].[SP_MARC504_Insertar] (?,?)}";
            cs = cn.prepareCall(procedure9);
            for(Marc504 m504:listaMarc504){
                cs.setString(1, m504.getA());
                cs.setInt(2, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            String procedure10 = "{CALL [PT].[SP_EJEMPLAR_Insertar] (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = cn.prepareCall(procedure10);
            for(Ejemplar ejem:listaEjemplar){
                cs.setString(1, ejem.getA583());
                cs.setString(2, ejem.getB583());
                cs.setString(3, ejem.getC583());
                cs.setString(4, ejem.getK583());
                cs.setString(5, ejem.getX583());
                cs.setString(6, ejem.getA852());
                cs.setString(7, ejem.getB852());
                cs.setString(8, ejem.getC852());
                cs.setString(9, ejem.getH852());
                cs.setString(10, ejem.getI852());
                cs.setString(11, ejem.getJ852());
                cs.setString(12, ejem.getP852());
                cs.setString(13, ejem.getQ852());
                cs.setString(14, ejem.get852_3());
                cs.setInt(15, documental.getID_DOCUMENTAL());
                cs.executeQuery();
            }
            
            cn.commit();
            respuesta = 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }
        } finally {
            try {
                cn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return respuesta;
        }

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
