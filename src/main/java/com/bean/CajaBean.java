package com.bean;

import com.dao.CajaDao;
import com.dao.DocumentalDao;
import com.dao.EjemplarDao;
import com.dao.impl.DaoFactory;
import com.dto.BandejaDto;
import com.dto.VistaPreviaDto;
import com.dto.EjemplarDocumentalDto;
import com.dto.FichaDocumentalDto;
import com.dto.FichaEjemplarDto;
import com.entidad.AreaCajaEstado;
import com.entidad.Caja;
import com.entidad.Deposito;
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
import static com.util.Constantes.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CajaBean {

    private FichaDocumentalDto fichaDocumental;
    private FichaEjemplarDto fichaEjemplar;

    List<EjemplarDocumentalDto> lejedocdto = new ArrayList<>();
    private Deposito selecteddeposito;
    private int ID_DEPOSITO;
    private int ID_CAJA;
    private final CajaDao cajaDao;
    private final DocumentalDao documentalDao;
    private final EjemplarDao ejemplarDao;
//    private Marc017 marc017;
//    private Marc100 marc100;
//    private Marc245 marc245;
//    private Marc250 marc250;
//    private Marc260 marc260;
//    private Marc300 marc300;
//    private Marc504 marc504;
    private Documental documental;
//    private List<Documental> listaDocumental;
    private List<Marc017> listaMarc017;
    private List<Marc100> listaMarc100;
    private List<Marc245> listaMarc245;
    private List<Marc250> listaMarc250;
    private List<Marc260> listaMarc260;
    private List<Marc300> listaMarc300;
    private List<Marc500> listaMarc500;
    private List<Marc504> listaMarc504;
    private List<Ejemplar> listaEjemplar;
    private int SESION_ID_USUARIO = 0;
    private Caja objCaja = new Caja();
    private boolean renderInputFile = false;
    private boolean renderUploadFile = false;
    private boolean renderMensajeIncrustado = true;
    private boolean disabledGrabarCaja = false;
    private boolean renderedBtnReportes = false;
    private boolean renderTablaXml = false;
    private boolean renderTabla = false;
    private String idCaja = "";
    private final int vacio = 0;
    private final int error = 0;
    private final int correcto = 1;
    private final boolean deshabilitado = false;
    private final boolean habilitado = true;
    private int totalEjemplares = 0;
    private int totalVolumenes = 0;
    private String concatEjemplarVolumen = "";
    ArrayList<String> listaErrores = new ArrayList<>();
    ArrayList<VistaPreviaDto> listaVistaPreviaCaja = new ArrayList<>();

    //lista para bandeja registro
    private ArrayList<Object[]> lstFilter = new ArrayList<>();
    private List<BandejaDto> lbandejacreado;
    private List<BandejaDto> lbandejavalidado;
    private List<BandejaDto> lbandejaporalmacenar;
    private List<BandejaDto> lbandejaalmacenado;

    public CajaBean() {
        SESION_ID_USUARIO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO_ID_USUARIO").toString());
        DaoFactory factory = DaoFactory.getInstance();
        cajaDao = factory.getCajaDao(CAJA);
        documentalDao = factory.getDocumentalDao(DOCUMENTAL);
        ejemplarDao = factory.getEjemplarDao(EJEMPLAR);
        lbandejacreado = cajaDao.bandejaCreado();
        lbandejavalidado = cajaDao.bandejaValidado();
        lbandejaporalmacenar = cajaDao.bandejaPorAlmacenar(SESION_ID_USUARIO);
        lbandejaalmacenado = cajaDao.bandejaAlmacenado(SESION_ID_USUARIO);
        selecteddeposito = new Deposito();
        fichaDocumental = new FichaDocumentalDto();
        fichaEjemplar = new FichaEjemplarDto();
    }

    public Caja getObjCaja() {
        return objCaja;
    }

    public void setObjCaja(Caja objCaja) {
        this.objCaja = objCaja;
    }

    public boolean isRenderMensajeIncrustado() {
        return renderMensajeIncrustado;
    }

    public void setRenderMensajeIncrustado(boolean renderMensajeIncrustado) {
        this.renderMensajeIncrustado = renderMensajeIncrustado;
    }

    public boolean isRenderUploadFile() {
        return renderUploadFile;
    }

    public void setRenderUploadFile(boolean renderUploadFile) {
        this.renderUploadFile = renderUploadFile;
    }

    public boolean isRenderTablaXml() {
        return renderTablaXml;
    }

    public void setRenderTablaXml(boolean renderTablaXml) {
        this.renderTablaXml = renderTablaXml;
    }

    public boolean isDisabledGrabarCaja() {
        return disabledGrabarCaja;
    }

    public void setDisabledGrabarCaja(boolean disabledGrabarCaja) {
        this.disabledGrabarCaja = disabledGrabarCaja;
    }

    public boolean isRenderTabla() {
        return renderTabla;
    }

    public void setRenderTabla(boolean renderTabla) {
        this.renderTabla = renderTabla;
    }

    public boolean isRenderedBtnReportes() {
        return renderedBtnReportes;
    }

    public void setRenderedBtnReportes(boolean renderedBtnReportes) {
        this.renderedBtnReportes = renderedBtnReportes;
    }

    public ArrayList<Object[]> getLstFilter() {
        return lstFilter;
    }

    public void setLstFilter(ArrayList<Object[]> lstFilter) {
        this.lstFilter = lstFilter;
    }

    public ArrayList<VistaPreviaDto> getListaVistaPreviaCaja() {
        return listaVistaPreviaCaja;
    }

    public void setListaVistaPreviaCaja(ArrayList<VistaPreviaDto> listaVistaPreviaCaja) {
        this.listaVistaPreviaCaja = listaVistaPreviaCaja;
    }

    public String getConcatEjemplarVolumen() {
        return concatEjemplarVolumen;
    }

    public void setConcatEjemplarVolumen(String concatEjemplarVolumen) {
        this.concatEjemplarVolumen = concatEjemplarVolumen;
    }

    public void grabarCaja() {
        if (objCaja != null) {
            String resp = "No se creo la caja.";
            objCaja.setID_USUARIO(SESION_ID_USUARIO);
            String[] msg = cajaDao.insertarCaja(objCaja);

            if (Integer.parseInt(msg[0]) == correcto) {
                resp = msg[1];
                idCaja = msg[2];
                objCaja.setID_CAJA(Integer.parseInt(idCaja));
                objCaja.setCODIGO_MEMO(msg[3].toUpperCase());
                objCaja.setNRO_CAJA(msg[4]);
                renderMensajeIncrustado = deshabilitado;
                RequestContext.getCurrentInstance().update("frmCaja:msgLista");
                renderUploadFile = habilitado;
                disabledGrabarCaja = habilitado;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", resp);
                FacesContext.getCurrentInstance().addMessage("gMensaje", message);
                RequestContext.getCurrentInstance().update("gMensaje");
                RequestContext.getCurrentInstance().update("frmCaja");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", resp);
                FacesContext.getCurrentInstance().addMessage("gMensaje", message);
                RequestContext.getCurrentInstance().update("gMensaje");
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Ingrese los datos de caja");
            FacesContext.getCurrentInstance().addMessage("gMensaje", message);
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        InputStream in = null;
        MarcReader reader = null;

        try {
            UploadedFile archivo = (UploadedFile) event.getFile();
            in = archivo.getInputstream();
            reader = new MarcXmlReader(in);

            while (reader.hasNext()) {
                Record record = reader.next();
                int estado = correcto;
                String msgEstado = "";

                ControlField campo001 = (ControlField) record.getVariableField("001");
                List campo017 = record.getVariableFields("017");
                List campo082 = record.getVariableFields("082");
                List campo084 = record.getVariableFields("084");
                List campo100 = record.getVariableFields("100");
                List campo245 = record.getVariableFields("245");
                List campo250 = record.getVariableFields("250");
                List campo260 = record.getVariableFields("260");
                List campo300 = record.getVariableFields("300");
                List campo500 = record.getVariableFields("500");
                List campo504 = record.getVariableFields("504");
                List campo583 = record.getVariableFields("583");
                List campo852 = record.getVariableFields("852");

                documental = new Documental();
                documental.setID_CAJA(Integer.parseInt(idCaja));
                //listaDocumental = new ArrayList<>();
                listaMarc017 = new ArrayList<>();
                listaMarc100 = new ArrayList<>();
                listaMarc245 = new ArrayList<>();
                listaMarc250 = new ArrayList<>();
                listaMarc260 = new ArrayList<>();
                listaMarc300 = new ArrayList<>();
                listaMarc504 = new ArrayList<>();
                listaMarc500 = new ArrayList<>();
                listaEjemplar = new ArrayList<>();

                leerFichasXML(estado, msgEstado, campo001, campo017, campo082, campo084, campo100, campo245, campo250, campo260, campo300, campo500, campo504, campo583, campo852);

                int resultado = grabarDocumental(estado, msgEstado, documental, listaMarc017, listaMarc100, listaMarc245, listaMarc250, listaMarc260, listaMarc300, listaMarc504, listaMarc500, listaEjemplar);
                totalVolumenes += resultado;

            }
            //SE CONSULTA LA BASE DE DATOS PARA EL PREVIEW
            listaVistaPreviaCaja = cajaDao.vistaPreviaCaja(objCaja);
            objCaja.setNRO_EJEMPLARES(totalEjemplares);
            objCaja.setNRO_VOLUMENES(totalVolumenes);
            concatEjemplarVolumen = "Nro. de volumenes : " + totalVolumenes + " / Nro. de ejemplares : " + totalEjemplares;
            String[] msgUpd = cajaDao.cajaActualizarVolumenEjemplar(objCaja);
            //una ves que se insertan los ejemplares se deshabilita el upload
            renderUploadFile = deshabilitado;
            renderTabla = habilitado;
            renderedBtnReportes = habilitado;
            RequestContext.getCurrentInstance().update("frmCaja");
            //Mensaje de tantos libros agregados con exito
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", msgUpd[1]);
            FacesContext.getCurrentInstance().addMessage("gMensaje", message);
            RequestContext.getCurrentInstance().update("gMensaje");
            //se muestra una lista con los errores si es que los hubieran

            //se muestra una lista con los libros que se subieron
        } catch (IOException ex) {
            System.out.println("error" + ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                System.out.println("ERROR ** IOException ex ** " + ex);
            }

        }
    }

    public void leerFichasXML(int estado, String msgEstado, ControlField campo001, List campo017, List campo082, List campo084, List campo100, List campo245, List campo250, List campo260, List campo300, List campo500, List campo504, List campo583, List campo852) {
        System.out.println("*************************************************FICHA***********************************************************");
        if (campo001 != null) {
            documental.setMFN(campo001.getData().trim());
            System.out.println("MFN = " + documental.getMFN().substring(1));
        } else {
            System.out.print("MFN =");
            System.out.println("--");
        }

        if (!campo082.isEmpty()) {
            for (int i = 0; i < campo082.size(); i++) {
                DataField datox = (DataField) campo082.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 082 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            documental.setA082(data);
                            System.out.print("Campo 082 : " + documental.getA082());
                            break;
                        case "2":
                            documental.set082_2(data);
                            System.out.print(" " + documental.get082_2());
                            break;
                    }
                }
            }
        } else {
            System.out.print("Campo 082 : ");
            System.out.println("--");
        }
        /* campo 084   */
        if (!campo084.isEmpty()) {
            for (int i = 0; i < campo084.size(); i++) {
                DataField datox = (DataField) campo084.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 084 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            documental.setA084(data);
                            System.out.print("Campo 084 : " + documental.getA084());
                            break;
                    }
                }
            }
        } else {
            System.out.print("Campo 084 : ");
            System.out.println("--");
        }

        if (!campo100.isEmpty()) {
            Marc100 marc100;
            for (int i = 0; i < campo100.size(); i++) {
                marc100 = new Marc100();
                DataField datox = (DataField) campo100.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 100 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc100.setA(data);
                            System.out.print("Campo 100 : " + marc100.getA());
                            break;
                        case "d":
                            marc100.setD(data);
                            System.out.print(" " + marc100.getD());
                            break;
                    }
                    listaMarc100.add(marc100);
                }
            }
        } else {
            System.out.print("Campo 100 : ");
            System.out.println("--");
        }

        if (!campo245.isEmpty()) {
            Marc245 marc245;
            for (int i = 0; i < campo245.size(); i++) {
                marc245 = new Marc245();
                DataField datox = (DataField) campo245.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 245 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc245.setA(data);
                            System.out.print("Campo 245 : " + marc245.getA());
                            break;
                        case "b":
                            marc245.setB(data);
                            System.out.print(" " + marc245.getB());
                            break;
                        case "c":
                            marc245.setC(data);
                            System.out.print(" " + marc245.getC());
                            break;
                    }
                }
                listaMarc245.add(marc245);
            }
        } else {
            System.out.print("Campo 245 : ");
            System.out.println("--");
        }

        if (!campo250.isEmpty()) {
            Marc250 marc250;
            for (int i = 0; i < campo250.size(); i++) {
                marc250 = new Marc250();
                DataField datox = (DataField) campo250.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 250 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc250.setA(data);
                            System.out.print("Campo 250 : " + marc250.getA());
                            break;
                    }
                }
                listaMarc250.add(marc250);
            }
        } else {
            System.out.print("Campo 250 : ");
            System.out.println("--");
        }

        if (!campo260.isEmpty()) {
            Marc260 marc260;
            for (int i = 0; i < campo260.size(); i++) {
                marc260 = new Marc260();
                DataField datox = (DataField) campo260.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 260 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc260.setA(data);
                            System.out.print("Campo 260 : " + marc260.getA());
                            break;
                        case "b":
                            marc260.setB(data);
                            System.out.print(" " + marc260.getB());
                            break;
                        case "c":
                            marc260.setC(data);
                            System.out.print(" " + marc260.getC());
                            break;
                    }
                }
                listaMarc260.add(marc260);
            }
        } else {
            System.out.print("Campo 260 : ");
            System.out.println("--");
        }

        if (!campo300.isEmpty()) {
            Marc300 marc300;
            for (int i = 0; i < campo300.size(); i++) {
                marc300 = new Marc300();
                DataField datox = (DataField) campo300.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 300 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc300.setA(data);
                            System.out.print("Campo 300 : " + marc300.getA());
                            break;
                        case "b":
                            marc300.setB(data);
                            System.out.print(" " + marc300.getB());
                            break;
                        case "c":
                            marc300.setC(data);
                            System.out.print(" " + marc300.getC());
                            break;
                    }
                }
                listaMarc300.add(marc300);
            }
        } else {
            System.out.print("Campo 300 : ");
            System.out.println("--");
        }

        if (!campo500.isEmpty()) {
            Marc500 marc500;
            for (int i = 0; i < campo500.size(); i++) {
                marc500 = new Marc500();
                DataField datox = (DataField) campo500.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 500 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    marc500.setA(data);
                    listaMarc500.add(marc500);
                    if (code.equals("a")) {
                        System.out.println(data);
                    }
                }
            }
        }

        if (!campo504.isEmpty()) {
            Marc504 marc504;
            for (int i = 0; i < campo504.size(); i++) {
                marc504 = new Marc504();
                DataField datox = (DataField) campo504.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 504 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc504.setA(data.substring(2));
                            System.out.print("Campo 504 : " + marc504.getA());
                            break;
                    }
                }
                listaMarc504.add(marc504);
            }
        } else {
            System.out.print("Campo 504 : ");
            System.out.println("--");
        }

        if (!campo017.isEmpty()) {
            Marc017 marc017;
            for (int i = 0; i < campo017.size(); i++) {
                marc017 = new Marc017();
                DataField datox = (DataField) campo017.get(i);
                List subcampos = datox.getSubfields();
                Iterator it = subcampos.iterator();
                System.out.print("Campo 017 : ");
                while (it.hasNext()) {
                    Subfield subfield = (Subfield) it.next();
                    String code = String.valueOf(subfield.getCode());
                    String data = subfield.getData().trim();
                    switch (code) {
                        case "a":
                            marc017.setA(data);
                            System.out.print("Campo 017 : D.L." + marc017.getA());
                            break;
                    }
                }
                listaMarc017.add(marc017);
            }
        } else {
            System.out.print("Campo 017 : ");
            System.out.println("--");
        }

        if (!campo583.isEmpty() && !campo852.isEmpty()) {
            Ejemplar ejemplar;
            if (campo583.size() < campo852.size()) {
                estado = error;
                msgEstado = "Ver ABSYSNET: en campo 583..Faltan " + (campo852.size() - campo583.size()) + " registros";
            } else {
                for (int i = 0; i < campo852.size(); i++) {
                    ejemplar = new Ejemplar();
                    DataField datoi = (DataField) campo852.get(i);
                    List subcampoi = datoi.getSubfields();
                    Iterator it = subcampoi.iterator();
                    while (it.hasNext()) {
                        Subfield subfield = (Subfield) it.next();
                        String code = String.valueOf(subfield.getCode());
                        String data = subfield.getData().trim();
                        switch (code) {
                            case "a":
                                ejemplar.setA852(data);
                                break;
                            case "b":
                                ejemplar.setB852(data);
                                break;
                            case "c":
                                ejemplar.setC852(data);
                                break;
                            case "h":
                                ejemplar.setH852(data);
                                break;
                            case "i":
                                ejemplar.setI852(data);
                                break;
                            case "j":
                                ejemplar.setJ852(data);
                                break;
                            case "p":
                                ejemplar.setP852(data);
                                break;
                            case "q":
                                ejemplar.setQ852(data);
                                break;
                            case "3":
                                ejemplar.set852_3(data);
                                break;
                        }
                    }
                    datoi = (DataField) campo583.get(i);
                    subcampoi = datoi.getSubfields();
                    it = subcampoi.iterator();
                    while (it.hasNext()) {
                        Subfield subfield = (Subfield) it.next();
                        String code = String.valueOf(subfield.getCode());
                        String data = subfield.getData().trim();
                        switch (code) {
                            case "a":
                                ejemplar.setA583(data);
                                break;
                            case "b":
                                ejemplar.setB583(data);
                                break;
                            case "c":
                                ejemplar.setC583(data);
                                break;
                            case "k":
                                ejemplar.setK583(data);
                                break;
                            case "x":
                                ejemplar.setX583(data);
                                break;
                        }
                    }
                    if (ejemplar.getA852().equals("BNP") && ejemplar.getC852().equals("Guillermo Lohmann Villena- Libros peruanos")) {
                        listaEjemplar.add(ejemplar);
                    }
                }
                //sumar los ejemplares 
                totalEjemplares = totalEjemplares + listaEjemplar.size();
                System.out.println("hay " + listaEjemplar.size() + " registros que van a patrimonio de" + campo852.size() + " que ingresaron a la BNP");
            }
        } else {
            System.out.print("Campo ejemplar : ");
            System.out.println("--");
        }
        System.out.println("*********************************************FIN-FICHA***********************************************************");

    }

    public int grabarDocumental(int estado, String msgEstado, Documental documental, List<Marc017> marc017, List<Marc100> listaMarc100, List<Marc245> listaMarc245, List<Marc250> listaMarc250, List<Marc260> listaMarc260, List<Marc300> listaMarc300, List<Marc504> listaMarc504, List<Marc500> listaMarc500, List<Ejemplar> listaEjemplar) {
        int respuesta = vacio;
        if (estado == correcto) {
            respuesta = documentalDao.insertarDocumental(documental, marc017, listaMarc100, listaMarc245, listaMarc250, listaMarc260, listaMarc300, listaMarc504, listaMarc500, listaEjemplar);
        } else {
            listaErrores.add(documental.getMFN() + " " + msgEstado);
        }
        return respuesta;
    }

    //reporte
    public void exportarListadoEjemplaresPorCaja() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/RPT_listadoEjemplaresCaja.jasper");
        String[] param = {String.valueOf(objCaja.getID_CAJA()), objCaja.getNRO_CAJA(), String.valueOf(totalVolumenes), String.valueOf(totalEjemplares)};
        cajaDao.reporteListadoEjemplaresCaja(ruta, param);
        FacesContext.getCurrentInstance().responseComplete();

    }

    //fin reporte
    
    
    public ArrayList<BandejaDto> listarBandejaPatrimonioDto() {
        ArrayList<BandejaDto> lst = new ArrayList<>();
        lst = cajaDao.bandejaPattrimonio();
        return lst;
    }

    public ArrayList<BandejaDto> listarBandejaDepositoDto() {
        ArrayList<BandejaDto> lst = new ArrayList<>();
        lst = cajaDao.bandejaDeposito();
        return lst;
    }

    public void redireccionar(String ID_CAJA) {
        try {
            String red = "/Patrimonio/depdb/patrimonio/controlPatrimonio.xhtml?ID_CAJA=" + ID_CAJA;
//            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta!", "Modificando DOCUMENTAL: " + ID_DOCUMENTAL));
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(red);
        } catch (IOException ex) {
            System.out.println("error" + ex);
        }

    }

    public void procesarCaja(int ID_CAJA) {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        AreaCajaEstado ace = new AreaCajaEstado();
        ace.setID_CAJA(ID_CAJA);
        ace.setID_AREA(Integer.parseInt(ex.getSessionMap().get("PERSONAL_ID_AREA").toString()));
        ace.setID_ESTADO_PROCESO(2);
        ace.setID_USUARIO(SESION_ID_USUARIO);
        int insert = cajaDao.insertarAreaCajaEstado(ace);
        if (insert == 1) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se procesó la caja correctamente."));
            lbandejacreado = cajaDao.bandejaCreado();
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ocurrió un error"));
        }
        RequestContext.getCurrentInstance().update("frmCajaRegistro");
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void detalleCaja() {
        if (ID_CAJA != 0) {
            objCaja = cajaDao.buscarCaja(ID_CAJA);
            lejedocdto = cajaDao.listarCajaEjemplarDocumental(ID_CAJA);
        } else {
            System.out.println("HACER LO QUE TENGA QUE HACER PARA QUE BUSQUE UNA CAJA :)");
        }
    }

    public void infoEjemplar(int ID_DOCUMENTAL, int ID_EJEMPLAR) {
        fichaDocumental = documentalDao.buscarFichaDocumental(ID_DOCUMENTAL);
        fichaEjemplar = ejemplarDao.buscarFichaEjemplar(ID_EJEMPLAR);
        RequestContext.getCurrentInstance().update("pnlFicha");
    }

    public void validaEjemplar(int ID_EJEMPLAR, String CLASS_VALIDADO) {
        if (CLASS_VALIDADO.equals("GreenBack")) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "El ejemplar seleccionado ya ha sido aprobado."));
        } else {
            int ID_USUARIO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO_ID_USUARIO").toString());
            int insert = ejemplarDao.validarEjemplar(ID_EJEMPLAR, ID_USUARIO);
            if (insert == 1) {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se validó el ejemplar correctamente."));
                lbandejacreado = cajaDao.bandejaCreado();
            } else {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ocurrió un error."));
            }
            lejedocdto = cajaDao.listarCajaEjemplarDocumental(ID_CAJA);
        }
        RequestContext.getCurrentInstance().update("frmControlPatrimonio:tblcaja");
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void validaCaja() {
        int aux = 0;
        for (int i = 0; i < lejedocdto.size(); i++) {
            if (lejedocdto.get(i).getCLASS_VALIDADO().equals("RedBack")) {
                aux++;
            }
        }
        if (aux > 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Existen " + aux + " ejemplares sin validar."));
        } else if (aux == 0) {
            if (ID_DEPOSITO != -1) {
                ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
                AreaCajaEstado ace = new AreaCajaEstado();
                ace.setID_CAJA(ID_CAJA);
                ace.setID_AREA(Integer.parseInt(ex.getSessionMap().get("PERSONAL_ID_AREA").toString()));
                ace.setID_ESTADO_PROCESO(3);
                ace.setID_USUARIO(SESION_ID_USUARIO);
                int insert = cajaDao.cajaDeposito(ace, ID_DEPOSITO);
                if (insert == 1) {
                    FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se proceso la caja con éxito."));
                } else {
                    FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "Ocurrió un error."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debe seleccionar un deposito."));
            }
        }
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void enviarDeposito(int ID_CAJA) {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        AreaCajaEstado ace = new AreaCajaEstado();
        ace.setID_CAJA(ID_CAJA);
        ace.setID_AREA(Integer.parseInt(ex.getSessionMap().get("PERSONAL_ID_AREA").toString()));
        ace.setID_ESTADO_PROCESO(4);
        ace.setID_USUARIO(SESION_ID_USUARIO);
        int insert = cajaDao.insertarAreaCajaEstado(ace);
        if (insert == 1) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "La caja fue enviada al encargado de deposito."));
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ocurrió un error"));
        }
        lbandejavalidado = cajaDao.bandejaValidado();
        RequestContext.getCurrentInstance().update("frmCajaValidado");
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void almacenar(int ID_CAJA) {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        AreaCajaEstado ace = new AreaCajaEstado();
        ace.setID_CAJA(ID_CAJA);
        ace.setID_AREA(Integer.parseInt(ex.getSessionMap().get("PERSONAL_ID_AREA").toString()));
        ace.setID_ESTADO_PROCESO(5);
        ace.setID_USUARIO(SESION_ID_USUARIO);
        int insert = cajaDao.insertarAreaCajaEstado(ace);
        if (insert == 1) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "La caja fue almacenada correctamente."));
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ocurrió un error"));
        }
        lbandejaporalmacenar = cajaDao.bandejaPorAlmacenar(SESION_ID_USUARIO);
        RequestContext.getCurrentInstance().update("frmCajaPorAlmacenar");
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public List<BandejaDto> getLbandejacreado() {
        return lbandejacreado;
    }

    public void setLbandejacreado(List<BandejaDto> lbandejacreado) {
        this.lbandejacreado = lbandejacreado;
    }

    public int getID_CAJA() {
        return ID_CAJA;
    }

    public void setID_CAJA(int ID_CAJA) {
        this.ID_CAJA = ID_CAJA;
    }

    public Deposito getSelecteddeposito() {
        return selecteddeposito;
    }

    public void setSelecteddeposito(Deposito selecteddeposito) {
        this.selecteddeposito = selecteddeposito;
    }

    public int getID_DEPOSITO() {
        return ID_DEPOSITO;
    }

    public void setID_DEPOSITO(int ID_DEPOSITO) {
        this.ID_DEPOSITO = ID_DEPOSITO;
    }

    public List<EjemplarDocumentalDto> getLejedocdto() {
        return lejedocdto;
    }

    public void setLejedocdto(List<EjemplarDocumentalDto> lejedocdto) {
        this.lejedocdto = lejedocdto;
    }

    public FichaDocumentalDto getFichaDocumental() {
        return fichaDocumental;
    }

    public void setFichaDocumental(FichaDocumentalDto fichaDocumental) {
        this.fichaDocumental = fichaDocumental;
    }

    public FichaEjemplarDto getFichaEjemplar() {
        return fichaEjemplar;
    }

    public void setFichaEjemplar(FichaEjemplarDto fichaEjemplar) {
        this.fichaEjemplar = fichaEjemplar;
    }

    public List<BandejaDto> getLbandejavalidado() {
        return lbandejavalidado;
    }

    public void setLbandejavalidado(List<BandejaDto> lbandejavalidado) {
        this.lbandejavalidado = lbandejavalidado;
    }

    public List<BandejaDto> getLbandejaporalmacenar() {
        return lbandejaporalmacenar;
    }

    public void setLbandejaporalmacenar(List<BandejaDto> lbandejaporalmacenar) {
        this.lbandejaporalmacenar = lbandejaporalmacenar;
    }

    public List<BandejaDto> getLbandejaalmacenado() {
        return lbandejaalmacenado;
    }

    public void setLbandejaalmacenado(List<BandejaDto> lbandejaalmacenado) {
        this.lbandejaalmacenado = lbandejaalmacenado;
    }


}
