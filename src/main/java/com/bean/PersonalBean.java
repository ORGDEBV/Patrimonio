package com.bean;

import com.dao.PersonalDao;
import com.dao.impl.DaoFactory;
import com.entidad.Personal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import static com.util.Constantes.*;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class PersonalBean {

    private Personal persona;
    private final PersonalDao pDao;
    List<Personal> lstPersonal = new ArrayList<>();
    private List<Object[]> lstPersonalCombo = new ArrayList<>();
    private List<SelectItem> cboPersonal;

    public PersonalBean() {
        persona = new Personal();
        DaoFactory factory = DaoFactory.getInstance();
        pDao = factory.getPersonalDao(PERSONAL);
    }

    public Personal getPersona() {
        return persona;
    }

    public void setPersona(Personal persona) {
        this.persona = persona;
    }

    public List<Personal> getLstPersonal() {
        return lstPersonal = pDao.listar();
    }

    public void setLstPersonal(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public void registrarPersonal() {
        int out = pDao.insertar(persona);
        if (out == 1) {
            limpiarCampos();
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "PROCESO COMPLETADO", "Personal se procesado con éxito."));
            RequestContext.getCurrentInstance().update("frmPersonalUnsertUpd");
            RequestContext.getCurrentInstance().update("gMensaje");
            System.out.println(" registro exitoso");
        } else {
            limpiarCampos();
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ocurrió un error "));
            RequestContext.getCurrentInstance().update("frmPersonalUnsertUpd");
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    public void limpiarCampos() {
        persona.setID_PERSONAL(null);
        persona.setNOMBRE("");
        persona.setPATERNO("");
        persona.setMATERNO("");
        persona.setDNI("");
        persona.setCARGO("");
        persona.setID_AREA(-1);
        persona.setCORREO("");

    }

    public void listarPorId() {
        if (persona.getID_PERSONAL() != null) {
            persona = pDao.buscar(persona.getID_PERSONAL());
        }
    }

    public List<Object[]> getLstPersonalCombo() {
        return lstPersonalCombo;
    }

    public void setLstPersonalCombo(List<Object[]> lstPersonalCombo) {
        this.lstPersonalCombo = lstPersonalCombo;
    }

    public List<SelectItem> getCboPersonal() {
        lstPersonalCombo = pDao.listarCombo();
        cboPersonal = new ArrayList<>();
        if (lstPersonalCombo != null) {

            for (Object[] fila : lstPersonalCombo) {
                cboPersonal.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboPersonal;
    }

    public void setCboPersonal(List<SelectItem> cboPersonal) {
        this.cboPersonal = cboPersonal;
    }

    public void redireccionar(String Id) {
        try {
//            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta!", "Modificando DOCUMENTAL: " + ID_DOCUMENTAL));
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/personal/personalInsertUpd.xhtml?ID_PERSONAL=" + Id);
        } catch (IOException ex) {
            System.out.println("error" + ex);
        }

    }
}
