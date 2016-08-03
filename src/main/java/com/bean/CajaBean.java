package com.bean;

import com.dao.CajaDao;
import com.dao.DocumentalDao;
import com.dao.impl.DaoFactory;
import com.dto.BandejaDto;
import com.entidad.Caja;
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
import static com.util.Constantes.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CajaBean {

    private final CajaDao cajaDao;
    private final DocumentalDao documentalDao;
    private Marc001 marc001;
    private Marc017 marc017;
    private Marc100 marc100;
    private Marc245 marc245;
    private Marc250 marc250;
    private Marc260 marc260;
    private Marc300 marc300;
    private Marc504 marc504;
    ArrayList<Object[]> lstFilter = new ArrayList<>();
    List<Marc500> listaMarc500;
    List<Ejemplar> listaEjemplar;
    private Caja objCaja = new Caja();
    private boolean renderInputFile = false;

    //lista para bandeja registro
    private List<BandejaDto> lbandejacreado;

    public CajaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        cajaDao = factory.getCajaDao(CAJA);
        documentalDao = factory.getDocumentalDao(DOCUMENTAL);
        lbandejacreado = cajaDao.bandejaCreado();
    }

    public Caja getObjCaja() {
        return objCaja;
    }

    public void setObjCaja(Caja objCaja) {
        this.objCaja = objCaja;
    }

    public boolean isRenderInputFile() {
        return renderInputFile;
    }

    public void setRenderInputFile(boolean renderInputFile) {
        this.renderInputFile = renderInputFile;
    }

    public ArrayList<Object[]> getLstFilter() {
        return lstFilter;
    }

    public void setLstFilter(ArrayList<Object[]> lstFilter) {
        this.lstFilter = lstFilter;
    }

    public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException {
        InputStream in = null;

        try {
            UploadedFile archivo = (UploadedFile) event.getFile();
            in = archivo.getInputstream();
            MarcReader reader = new MarcXmlReader(in);
            while (reader.hasNext()) {
                Record record = reader.next();

                ControlField campo001 = (ControlField) record.getVariableField("001");
                DataField campo17 = (DataField) record.getVariableField("017");
                DataField campo100 = (DataField) record.getVariableField("100");
                DataField campo245 = (DataField) record.getVariableField("245");
                DataField campo250 = (DataField) record.getVariableField("250");
                DataField campo260 = (DataField) record.getVariableField("260");
                DataField campo300 = (DataField) record.getVariableField("300");
                List campo500 = record.getVariableFields("500");
                DataField campo504 = (DataField) record.getVariableField("504");
                List campo583 = record.getVariableFields("583");
                List campo852 = record.getVariableFields("852");

                marc001 = new Marc001();
                marc017 = new Marc017();
                marc100 = new Marc100();
                marc245 = new Marc245();
                marc250 = new Marc250();
                marc260 = new Marc260();
                marc300 = new Marc300();
                marc504 = new Marc504();
                listaMarc500 = new ArrayList<>();
                listaEjemplar = new ArrayList<>();

                System.out.println("*************************************************FICHA***********************************************************");
                if (campo100 != null) {
                    Subfield subcampo100a = campo100.getSubfield('a');
                    Subfield subcampo100d = campo100.getSubfield('d');
                    if (subcampo100a != null) {
                        marc100.setA(subcampo100a.getData().trim());
                        System.out.print("Campo 100 : " + marc100.getA());
                    }
                    if (subcampo100d != null) {
                        marc100.setD(subcampo100d.getData().trim());
                        System.out.print(" " + marc100.getD());
                    }
                    System.out.println(" ");
                } else {
                    System.out.print("Campo 100 : ");
                    System.out.println("--");
                }
                if (campo245 != null) {
                    Subfield subcampo245a = campo245.getSubfield('a');
                    Subfield subcampo245b = campo245.getSubfield('b');
                    Subfield subcampo245c = campo245.getSubfield('c');
                    if (subcampo245a != null) {
                        marc245.setA(subcampo245a.getData().trim());
                        System.out.print("Campo 245 : " + marc245.getA());
                    }
                    if (subcampo245b != null) {
                        marc245.setB(subcampo245b.getData().trim());
                        System.out.print(" " + marc245.getB());
                    }
                    if (subcampo245c != null) {
                        marc245.setC(subcampo245c.getData().trim());
                        System.out.print(" " + marc245.getC() + " --");
                    }
                    System.out.println(" ");
                } else {
                    System.out.print("Campo 245 : ");
                    System.out.println("--");
                }
                if (campo250 != null) {
                    Subfield subcampo250a = campo250.getSubfield('a');
                    if (subcampo250a != null) {
                        marc250.setA(subcampo250a.getData().trim());
                        System.out.println("Campo 250 : " + marc250.getA() + " -- ");
                    }
                } else {
                    System.out.print("Campo 250 : ");
                    System.out.println("--");
                }
                if (campo260 != null) {
                    Subfield subcampo260a = campo260.getSubfield('a');
                    Subfield subcampo260b = campo260.getSubfield('b');
                    Subfield subcampo260c = campo260.getSubfield('c');
                    if (subcampo260a != null) {
                        marc260.setA(subcampo260a.getData().trim());
                        System.out.print("Campo 260 : " + marc260.getA());
                    }
                    if (subcampo260b != null) {
                        marc260.setB(subcampo260b.getData().trim());
                        System.out.print(" " + marc260.getB());
                    }
                    if (subcampo260c != null) {
                        marc260.setC(subcampo260c.getData().trim());
                        System.out.println(" " + marc260.getC() + " ");
                    }
                } else {
                    System.out.print("Campo 260 : ");
                    System.out.println("--");
                }
                if (campo300 != null) {
                    Subfield subcampo300a = campo300.getSubfield('a');
                    Subfield subcampo300b = campo300.getSubfield('b');
                    Subfield subcampo300c = campo300.getSubfield('c');
                    if (subcampo300a != null) {
                        marc300.setA(subcampo300a.getData().trim());
                        System.out.print("Campo 300 : " + marc300.getA());
                    }
                    if (subcampo300b != null) {
                        marc300.setB(subcampo300b.getData().trim());
                        System.out.print(" " + marc300.getB());
                    }
                    if (subcampo300c != null) {
                        marc300.setC(subcampo300c.getData().trim());
                        System.out.println(" " + marc300.getC());
                    }
                } else {
                    System.out.print("Campo 300 : ");
                    System.out.println("--");
                }

                if (!campo500.isEmpty()) {
                    Marc500 marc500 = new Marc500();
                    for (int i = 0; i < campo500.size(); i++) {
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
                if (campo504 != null) {
                    Subfield subcampo504a = campo504.getSubfield('a');
                    if (subcampo504a != null) {
                        marc504.setA(subcampo504a.toString().substring(2));
                        System.out.println("Campo 504 : " + marc504.getA());
                    }
                } else {
                    System.out.print("Campo 504 : ");
                    System.out.println("--");
                }
                if (campo17 != null) {
                    Subfield subcampo17a = campo17.getSubfield('a');
                    if (subcampo17a != null) {
                        marc017.setA(subcampo17a.getData().trim());
                        System.out.println("Campo 017 : " + "D.L. " + marc017.getA());
                    }
                } else {
                    System.out.print("Campo 017 : ");
                    System.out.println("--");
                }

                if (!campo583.isEmpty() && !campo852.isEmpty()) {
                    Ejemplar ejemplar = new Ejemplar();
                    if (campo583.size() < campo852.size()) {
                        System.out.println("Error en campo 583..Faltan " + (campo852.size() - campo583.size()) + " registros");
                    } else {
                        for (int i = 0; i < campo852.size(); i++) {
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
                        System.out.println("hay " + listaEjemplar.size() + " registros ... deben haber " + campo852.size() + " registros");
                    }
                } else {
                    System.out.print("Campo ejemplar : ");
                    System.out.println("--");
                }
                if (campo001 != null) {
                    marc001.setA(campo001.getData().trim());
                    System.out.println("MFN = " + campo001.getData().trim().substring(1));
                } else {
                    System.out.print("MFN =");
                    System.out.println("--");
                }

                System.out.println("*********************************************FIN-FICHA***********************************************************");

                String msg = grabarFicha();
            }

        } catch (IOException ex) {
            Logger.getLogger(CajaBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(CajaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void grabarCaja() {
        if (objCaja != null) {
            String msg = cajaDao.insertarCaja(objCaja);
            FacesMessage message = new FacesMessage("Correcto", msg);
            FacesContext.getCurrentInstance().addMessage("gMensaje", message);
        } else {
            FacesMessage message = new FacesMessage("Incorrecto", "Ingrese los datos de caja");
            FacesContext.getCurrentInstance().addMessage("gMensaje", message);
        }
    }

    public String grabarFicha() {
        String msgRespuesta = "";
        return msgRespuesta;
    }

    public ArrayList<BandejaDto> listarBandejaPatrimonioDto() {
        ArrayList<BandejaDto> lst = new ArrayList<>();
        lst = cajaDao.bandejaPattrimonio();
        return lst;
    }

    public void redireccionar(String Id) {

        try {
            String red = "/Patrimonio/depdb/patrimonio/controlPatrimonio.xhtml?ID=" + Id;
//            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta!", "Modificando DOCUMENTAL: " + ID_DOCUMENTAL));
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(red);

        } catch (IOException ex) {
            System.out.println("error" + ex);
        }

    }

    public void procesarCaja(int ID_CAJA) {
        int insert = cajaDao.insertarAreaCajaEstado(ID_CAJA);
    }

    public List<BandejaDto> getLbandejacreado() {
        return lbandejacreado;
    }

    public void setLbandejacreado(List<BandejaDto> lbandejacreado) {
        this.lbandejacreado = lbandejacreado;
    }

}
