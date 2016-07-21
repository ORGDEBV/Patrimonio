package com.bean;

import com.entidad.Caja;
import com.entidad.Marc001;
import com.entidad.Marc017;
import com.entidad.Marc100;
import com.entidad.Marc245;
import com.entidad.Marc250;
import com.entidad.Marc260;
import com.entidad.Marc300;
import com.entidad.Marc504;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@ViewScoped
public class CajaBean {

    private Caja objCaja = new Caja();
    private Marc001 marc001;
    private Marc017 marc017;
    private Marc100 marc100;
    private Marc245 marc245;
    private Marc250 marc250;
    private Marc260 marc260;
    private Marc300 marc300;
    private Marc504 marc504;
    private int Error = 0;

    public CajaBean() {
    }

    public Caja getObjCaja() {
        return objCaja;
    }

    public void setObjCaja(Caja objCaja) {
        this.objCaja = objCaja;
    }

    public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException {
        
        
        
        InputStream in = new FileInputStream("C:\\Users\\virtual\\Desktop\\FormatoRaro\\xml-completo.xml");
        MarcReader reader = new MarcXmlReader(in);
        
        
        
        
        
        
        /*
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        */
    }

}
