package com.dao.impl;

import com.dao.CajaDao;
import com.dto.BandejaDto;
import com.dto.ConsultaGeneral;
import com.dto.VistaPreviaDto;
import com.dto.EjemplarDocumentalDto;
import com.dto.FiltroDto;
import com.entidad.AreaCajaEstado;
import com.entidad.Caja;
import com.util.cnSQL;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class CajaDaoImpl implements CajaDao {

    CallableStatement cs = null;
    ResultSet rs = null;

    public java.sql.Date formatFecha(java.util.Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return sqlDate;
    }
    
    @Override
    public String[] insertarCaja(Caja objCaja) {
        String[] arreglo = new String[5];
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{CALL [PT].[SP_CAJA_Insertar](?,?)}";
            cs = cn.prepareCall(procedure);
            //cs.setString(1, objCaja.getNRO_CAJA());
            //cs.setString(2, objCaja.getCODIGO_MEMO());
            //cs.setInt(3, objCaja.getNRO_EJEMPLARES());
            //cs.setInt(1, objCaja.getID_DEPOSITO());
            cs.setString(1, objCaja.getSALA());
            //cs.setString(6, objCaja.getCODIGO_MEMO());
            cs.setInt(2, objCaja.getID_USUARIO());
            rs = cs.executeQuery();
            if (rs.next()) {
                arreglo[0] = rs.getString(1);
                arreglo[1] = rs.getString(2);
                arreglo[2] = rs.getString(3);
                arreglo[3] = rs.getString(4);
                arreglo[4] = rs.getString(5);

            }

        } catch (Exception e) {
            System.out.println("ERROR EN insertarCaja-->" + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR sql EN insertarCaja-->" + ex.getMessage());
            }
        }
        return arreglo;
    }

    @Override
    public ArrayList<BandejaDto> bandejaPattrimonio() {
        ArrayList<BandejaDto> lstBandejaPatrimonio = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{call PT.SP_CAJA_ListarPorAreaEstado (?)}";
            cs = cn.prepareCall(procedure);
            cs.setString(1, "BANDEJA_PATRIMONIO");
            rs = cs.executeQuery();
            BandejaDto bp;
            while (rs.next()) {
                bp = new BandejaDto();
                bp.setID_CAJA(rs.getString(1));
                bp.setCODIGO_MEMO(rs.getString(2));
                bp.setNRO_CAJA(rs.getString(3));
                bp.setNRO_EJEMPLARES(rs.getString(4));
                bp.setNRO_VOLUMENES(rs.getString(5));
                bp.setAREA(rs.getString(6));
                bp.setSALA(rs.getString(7));
                bp.setFECHA(rs.getString(8));
                lstBandejaPatrimonio.add(bp);
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
        return lstBandejaPatrimonio;
    }

    @Override
    public ArrayList<BandejaDto> bandejaCreado() {
        ArrayList<BandejaDto> lCreado = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        BandejaDto dto = null;
        try {
            String procedure = "{CALL PT.SP_CAJA_LISTAR_CREADO}";
            cs = cn.prepareCall(procedure);
            rs = cs.executeQuery();
            while (rs.next()) {
                dto = new BandejaDto();
                dto.setID_CAJA(rs.getString(1));
                dto.setCODIGO_MEMO(rs.getString(2));
                dto.setNRO_CAJA(rs.getString(3));
                dto.setNRO_EJEMPLARES(rs.getString(4));
                dto.setNRO_VOLUMENES(rs.getString(5));
                dto.setAREA(rs.getString(6));
                dto.setSALA(rs.getString(7));
                dto.setFECHA(rs.getString(8));
                lCreado.add(dto);
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
        return lCreado;
    }

    @Override
    public int insertarAreaCajaEstado(AreaCajaEstado ace) {
        int out = 0;
        String msg = "";
        Connection cn = cnSQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL [PT].[SP_AREA_CAJA_ESTADO_Insertar](?,?,?,?)}");
            cs.setInt(1, ace.getID_CAJA());
            cs.setInt(2, ace.getID_AREA());
            cs.setInt(3, ace.getID_ESTADO_PROCESO());
            cs.setInt(4, ace.getID_USUARIO());
            rs = cs.executeQuery();
            if (rs.next()) {
                out = rs.getInt(1);
                msg = rs.getString(2);
                System.out.println(msg);
                System.out.println(ace.getID_ESTADO_PROCESO());
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

    @Override
    public Caja buscarCaja(int ID_CAJA) {
        Caja caja = new Caja();
        Connection cn = cnSQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL [PT].[SP_CAJA_BUSCAR](?)}");
            cs.setInt(1, ID_CAJA);
            rs = cs.executeQuery();
            if (rs.next()) {
                caja.setID_CAJA(ID_CAJA);
                caja.setNRO_CAJA(rs.getString(2));
                caja.setSALA(rs.getString(3));
                caja.setCODIGO_MEMO(rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Error de consulta:" + e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return caja;
    }

    @Override
    public List<EjemplarDocumentalDto> listarCajaEjemplarDocumental(int ID_CAJA) {
        List<EjemplarDocumentalDto> lEjeDocDto = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        EjemplarDocumentalDto ed = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_CAJA_EJEMPLAR_DOCUMENTAL_LISTAR(?)}");
            cs.setInt(1, ID_CAJA);
            rs = cs.executeQuery();
            int aux = 1;
            while (rs.next()) {
                ed = new EjemplarDocumentalDto();
                ed.setNRO_EJEMPLAR(aux);
                ed.setID_EJEMPLAR(rs.getInt(1));
                ed.setCODIGO_BARRAS(rs.getString(2));
                ed.setMFN(rs.getString(3));
                ed.setID_DOCUMENTAL(rs.getInt(4));
                ed.setNRO_INGRESO(rs.getString(5));
                if (rs.getInt(6) == 1) {
                    ed.setCLASS_VALIDADO("GreenBack");
                } else if (rs.getInt(6) == 0) {
                    ed.setCLASS_VALIDADO("RedBack");
                }
                lEjeDocDto.add(ed);
                aux++;
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
        return lEjeDocDto;
    }

    @Override

    public int cajaDeposito(AreaCajaEstado ace, int ID_DEPOSITO) {
        int insert = 0;
        Connection cn = cnSQL.getConnection();
        try {
            cn.setAutoCommit(false);
            cs = cn.prepareCall("{CALL [PT].[SP_AREA_CAJA_ESTADO_Insertar](?,?,?,?)}");
            cs.setInt(1, ace.getID_CAJA());
            cs.setInt(2, ace.getID_AREA());
            cs.setInt(3, ace.getID_ESTADO_PROCESO());
            cs.setInt(4, ace.getID_USUARIO());
            cs.executeQuery();

            cs = cn.prepareCall("{CALL [PT].[SP_CAJA_DEPOSITO_UPDATE](?,?)}");
            cs.setInt(1, ace.getID_CAJA());
            cs.setInt(2, ID_DEPOSITO);
            cs.executeQuery();

            cn.commit();
            insert = 1;

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
            return insert;
        }

    }

    @Override
    public ArrayList<BandejaDto> bandejaDeposito() {
        ArrayList<BandejaDto> lstBandejaPatrimonio = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{call PT.SP_CAJA_ListarPorAreaEstado (?)}";
            cs = cn.prepareCall(procedure);
            cs.setString(1, "BANDEJA_DEPOSITO");
            rs = cs.executeQuery();
            BandejaDto bp;
            while (rs.next()) {
                bp = new BandejaDto();
                bp.setID_CAJA(rs.getString(1));
                bp.setCODIGO_MEMO(rs.getString(2));
                bp.setNRO_CAJA(rs.getString(3));
                bp.setCODIGO_LISTADO(rs.getString(4));
                bp.setNRO_EJEMPLARES(rs.getString(5));
                bp.setAREA(rs.getString(6));
                bp.setSALA(rs.getString(7));
                bp.setFECHA(rs.getString(8));
                bp.setID_CAJA(rs.getString(9));
                bp.setDEPOSITO(rs.getString(10));
                lstBandejaPatrimonio.add(bp);
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
        return lstBandejaPatrimonio;
    }

    //pasar id caja en ves de objCAja
    @Override
    public ArrayList<VistaPreviaDto> vistaPreviaCaja(Caja objCaja) {
        ArrayList<VistaPreviaDto> previa = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        try {
            VistaPreviaDto dto;
            String procedure = "{CALL [PT].[SP_CAJA_ListarCajaPrevia](?)}";
            cs = cn.prepareCall(procedure);
            cs.setInt(1, objCaja.getID_USUARIO());
            rs = cs.executeQuery();
            int i = 1;
            while (rs.next()) {
                dto = new VistaPreviaDto();
                dto.setID(i);
                dto.setMFN(rs.getString(2));
                dto.setCANTIDAD_EJEMPLARES(rs.getString(3));
                previa.add(dto);
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error DAO - vistaPreviaCaja");
        }

        return previa;
    }

    @Override
    public String[] cajaActualizarVolumenEjemplar(Caja objCaja) {
        String[] arreglo = new String[2];
        Connection cn = cnSQL.getConnection();
        try {
            String procedure = "{CALL [PT].[SP_CAJA_ActualizarVolumenEjemplar] (?,?,?)}";
            cs = cn.prepareCall(procedure);
            cs.setInt(1, objCaja.getID_CAJA());
            cs.setInt(2, objCaja.getNRO_VOLUMENES());
            cs.setInt(3, objCaja.getNRO_EJEMPLARES());
            rs = cs.executeQuery();
            if (rs.next()) {
                arreglo[0] = rs.getString(1);
                arreglo[1] = rs.getString(2);
            }

        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
        return arreglo;
    }

    @Override
    public ArrayList<BandejaDto> bandejaValidado() {
        ArrayList<BandejaDto> lValidado = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        BandejaDto dto = null;
        try {
            String procedure = "{CALL PT.SP_CAJA_VALIDADO_LISTAR}";
            cs = cn.prepareCall(procedure);
            rs = cs.executeQuery();
            while (rs.next()) {
                dto = new BandejaDto();
                dto.setID_CAJA(rs.getString(1));
                dto.setCODIGO_MEMO(rs.getString(2));
                dto.setNRO_CAJA(rs.getString(3));
                dto.setNRO_EJEMPLARES(rs.getString(4));
                dto.setNRO_VOLUMENES(rs.getString(5));
                dto.setAREA(rs.getString(6));
                dto.setSALA(rs.getString(7));
                dto.setFECHA(rs.getString(8));
                lValidado.add(dto);
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
        return lValidado;
    }

    @Override
    public ArrayList<BandejaDto> bandejaPorAlmacenar(int ID_USUARIO) {
        ArrayList<BandejaDto> lPorAlmacenar = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        BandejaDto dto = null;
        try {
            String procedure = "{CALL PT.SP_CAJA_POR_ALMACENAR_LISTAR(?)}";
            cs = cn.prepareCall(procedure);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                dto = new BandejaDto();
                dto.setID_CAJA(rs.getString(1));
                dto.setCODIGO_MEMO(rs.getString(2));
                dto.setNRO_CAJA(rs.getString(3));
                dto.setNRO_EJEMPLARES(rs.getString(4));
                dto.setNRO_VOLUMENES(rs.getString(5));
                dto.setAREA(rs.getString(6));
                dto.setSALA(rs.getString(7));
                dto.setFECHA(rs.getString(8));
                lPorAlmacenar.add(dto);
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
        return lPorAlmacenar;
    }

    @Override
    public ArrayList<BandejaDto> bandejaAlmacenado(int ID_USUARIO) {
        ArrayList<BandejaDto> lPorAlmacenar = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        BandejaDto dto = null;
        try {
            String procedure = "{CALL PT.SP_CAJA_ALMACENADO_LISTAR(?)}";
            cs = cn.prepareCall(procedure);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                dto = new BandejaDto();
                dto.setID_CAJA(rs.getString(1));
                dto.setCODIGO_MEMO(rs.getString(2));
                dto.setNRO_CAJA(rs.getString(3));
                dto.setNRO_EJEMPLARES(rs.getString(4));
                dto.setNRO_VOLUMENES(rs.getString(5));
                dto.setAREA(rs.getString(6));
                dto.setSALA(rs.getString(7));
                dto.setFECHA(rs.getString(8));
                lPorAlmacenar.add(dto);
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
        return lPorAlmacenar;
    }

    @Override
   public void reporteListadoEjemplaresCaja(String ruta, String[] param) {
        Connection cn = cnSQL.getConnection();
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("pID_CAJA", param[0]);
            parametros.put("pMemo", param[1]);
            parametros.put("pTotalVolumenes", param[2]);
            parametros.put("pTotalEjemplares", param[3]);

            File file = new File(ruta);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
             String nombreArchivo = "Reporte_"+param[1]+".pdf";
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "Attachment; filename=\"" + nombreArchivo + "\"");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, cn);

            JRExporter jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

            jrExporter.exportReport();

        } catch (IOException | JRException e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    @Override
    public List<ConsultaGeneral> bandejaGeneral(FiltroDto filtro) {
        List<ConsultaGeneral> lGeneral = new ArrayList<>();
        Connection cn = cnSQL.getConnection();
        ConsultaGeneral dto = null;
        try {
            String procedure = "{CALL [PT].[SP_ADMINISTRADOR_EJEMPLAR_LISTAR](?,?,?,?,?)}";
            cs = cn.prepareCall(procedure);
            cs.setString(1, filtro.getCAMPO());
            cs.setInt(2, filtro.getID_DEPOSITO());
            cs.setInt(3, filtro.getID_ESTADO());
            if(filtro.getFECHA_INI() != null){
                cs.setDate(4, formatFecha(filtro.getFECHA_INI()));
            }else{
                cs.setDate(4, null);
            }
            if(filtro.getFECHA_FIN()!= null){
                cs.setDate(5, formatFecha(filtro.getFECHA_FIN()));
            }else{
                cs.setDate(5, null);
            }
            rs = cs.executeQuery();
            while (rs.next()) {
                dto = new ConsultaGeneral();
                dto.setMFN(rs.getString(1));
                dto.setNRO_INGRESO(rs.getString(2));
                dto.setCODIGO_BARRAS(rs.getString(3));
                dto.setCODIGO_MEMO(rs.getString(4));
                dto.setESTADO(rs.getString(5));
                dto.setAREA(rs.getString(6));
                dto.setDEPOSITO(rs.getString(7));
                dto.setID_CAJA(rs.getInt(8));
                dto.setID_DOCUMENTAL(rs.getInt(9));
                dto.setID_EJEMPLAR(rs.getInt(10));
                dto.setID_ESTADO_PROCESO(rs.getInt(11));
                if (rs.getInt(11) > 1) {
                    dto.setAREA("Patrimonio");
                }
                lGeneral.add(dto);
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
        return lGeneral;
    }

}
