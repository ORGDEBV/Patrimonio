package com.bean;

import com.dao.AreaDao;
import com.dao.MenuDao;
import com.dao.PersonalDao;
import com.dao.UsuarioDao;
import com.dao.impl.DaoFactory;
import com.entidad.Area;
import com.entidad.Menu;
import com.entidad.Personal;
import com.entidad.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import static com.util.Constantes.*;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private final UsuarioDao uDao;
    private final PersonalDao pDao;
    private final AreaDao aDao;
    private final MenuDao mDao;
    private Usuario u;
    private Personal p;
    private List<Menu> lmenu;
    private String contrasenaActual;
    private String contrasenaNueva;
    private String contrasenaConf;
    private String usuarioNuevo;

    private boolean bPersonal;
    private String bContActual;

    List<Object[]> lstUsuario = new ArrayList<>();

    public UsuarioBean() {
        DaoFactory factory = DaoFactory.getInstance();
        uDao = factory.getUsuarioDao(USUARIO);
        pDao = factory.getPersonalDao(PERSONAL);
        aDao = factory.getAreaDao(AREA);
        mDao = factory.getMenuDao(MENU);
        u = new Usuario();
        p = new Personal();
    }

    public void logIn() throws IOException {
        Usuario usuario = uDao.logIn(u);
        if (usuario != null) {
            Personal personal = pDao.buscar(usuario.getID_PERSONAL());
            if (personal != null) {
                Area area = aDao.buscar(personal.getID_AREA());
                if (area != null) {
                    ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
                    ex.getSessionMap().put("USUARIO_ID_USUARIO", usuario.getID_USUARIO());
                    ex.getSessionMap().put("PERSONAL_NOMBRE", personal.getNOMBRE());
                    ex.getSessionMap().put("PERSONAL_PATERNO", personal.getPATERNO());
                    ex.getSessionMap().put("PERSONAL_MATERNO", personal.getMATERNO());
                    ex.getSessionMap().put("PERSONAL_CARGO", personal.getCARGO());
                    ex.getSessionMap().put("PERSONAL_ID_AREA", personal.getID_AREA());
                    ex.redirect("/Patrimonio/depdb/inicio.xhtml");
                }
            } else {
                System.out.println("USUARIO NO ENCONTRADO PAPU");
            }
        } else {
            System.out.println("USUARIO Y/O CONTRASEÑA INCORRECTA PRRO");
        }
    }

    public void validaLogIn() throws IOException {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Object ID_USUARIO = ex.getSessionMap().get("USUARIO_ID_USUARIO");
        if (ID_USUARIO != null) {
            p.setNOMBRE((String) ex.getSessionMap().get("PERSONAL_NOMBRE"));
            p.setPATERNO((String) ex.getSessionMap().get("PERSONAL_PATERNO"));
            p.setMATERNO((String) ex.getSessionMap().get("PERSONAL_MATERNO"));
            p.setCARGO((String) ex.getSessionMap().get("PERSONAL_CARGO"));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/");
        }
    }

    public String getContrasenaActual() {
        return contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(String usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public String getContrasenaConf() {
        return contrasenaConf;
    }

    public void setContrasenaConf(String contrasenaConf) {
        this.contrasenaConf = contrasenaConf;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    public void validaLogOut() throws IOException {
        Object ID_USUARIO = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO_ID_USUARIO");
        if (ID_USUARIO != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/inicio.xhtml");
        }
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public Personal getP() {
        return p;
    }

    public void setP(Personal p) {
        this.p = p;
    }

    public List<Menu> getLmenu() throws IOException {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Object ID_USUARIO = ex.getSessionMap().get("USUARIO_ID_USUARIO");
        if (ID_USUARIO != null) {
            lmenu = mDao.buscarIdUsuario(Integer.parseInt(ID_USUARIO.toString()));
        }
        return lmenu;
    }

    public void setLmenu(List<Menu> lmenu) {
        this.lmenu = lmenu;
    }

    public List<Object[]> getLstUsuario() {
        lstUsuario = uDao.listarUsuariosObject();
        return lstUsuario;
    }

    public void setLstUsuario(List<Object[]> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public ArrayList<String> listValidacion() {
        ArrayList<String> arrayErrores = new ArrayList<>();

        if (u.getID_USUARIO() != null) {//caso de edicion
            if (usuarioNuevo.trim().length() > 0) {
                if (!usuarioNuevo.trim().equals(u.getUSUARIO())) {
                    if (uDao.validaUsuario(usuarioNuevo)) {
                        arrayErrores.add("Usuario ya existe, Intente con otro");
                    }
                }
            } else {
                arrayErrores.add("El campo usuario no puede estar vacio");
            }
            if (contrasenaActual.trim().length() > 0) {
                if (!validaContrasenaAnterior()) {
                    arrayErrores.add("Contraseña Actual no coincide,intentelo de nuevo");
                }
            }
            if (contrasenaNueva.trim().length() > 0 && contrasenaConf.trim().length() > 0) {
                if (!validaContrasenaNueva()) {
                    arrayErrores.add("No se a confirmado correctamente la contraseña,intentelo de nuevo");
                }
            }
        } else // caso de usuarios nuevos
        {
            if (uDao.validaUsuario(usuarioNuevo.trim())) {
                arrayErrores.add("Usuario ya existe, Intente con otro");
            }
            if (contrasenaNueva.trim().length() == 0) {
                arrayErrores.add("Se requiere una Contraseña");
            }
            if (contrasenaConf.trim().length() == 0) {
                arrayErrores.add("Se requiere confirmar la Contraseña");
            }
            if (contrasenaNueva.trim().length() > 0 && contrasenaConf.trim().length() > 0) {
                if (!validaContrasenaNueva()) {
                    arrayErrores.add("No se a confirmado correctamente la contraseña,intentelo de nuevo");
                }
            }
        }

        return arrayErrores;
    }

    public boolean validaContrasenaAnterior() {
        boolean out = false;
        if (u.getCONTRASENA().equals(contrasenaActual)) {
            out = true;
        } else {
            out = false;
        }
        return out;
    }

    public boolean validaContrasenaNueva() {
        boolean out = false;
        if (contrasenaNueva.equals(contrasenaConf)) {
            out = true;

        } else {
            out = false;
        }
        return out;
    }

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    public void registrarUsuario() {

        ArrayList<String> arrayErrores = listValidacion();
        if (arrayErrores.isEmpty()) {
            //codigo para registrar
            if (contrasenaNueva.trim().length() > 0) {
                u.setCONTRASENA(contrasenaNueva.trim());
            }
            if (usuarioNuevo.trim().length() > 0) {
                u.setUSUARIO(usuarioNuevo);
            }
            int out = uDao.insertar(u);
            if (out == 1) {
                limpiarCampos();
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "PROCESO COMPLETADO", "Usuario se procesado con éxito."));
                RequestContext.getCurrentInstance().update("frmPersonalUnsertUpd");
                RequestContext.getCurrentInstance().update("gMensaje");
                System.out.println(" registro exitoso");
            } else {
                limpiarCampos();
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ocurrió un error "));
                RequestContext.getCurrentInstance().update("frmPersonalUnsertUpd");
                RequestContext.getCurrentInstance().update("gMensaje");
            }
        } else {
            String mensaje = "No se pudo insertar el documento.\nPor los motivos:<br/>";
            for (int i = 0; i < arrayErrores.size(); i++) {
                String motivo = "-" + arrayErrores.get(i) + "<br/>";
                mensaje = mensaje + motivo;
            }
            msjError("gMensaje", mensaje);
            RequestContext.getCurrentInstance().update("gMensaje");
        }

    }

    public void limpiarCampos() {
        u.setID_PERSONAL(-1);
        u.setID_USUARIO(0);
        u.setUSUARIO("");
        usuarioNuevo = "";
        u.setCONTRASENA("");
        contrasenaActual = "";
        contrasenaNueva = "";
        contrasenaConf = "";
        u.setID_PERFIL(-1);

    }

    public void listarUsuarioId() {
        if (u.getID_USUARIO() != null) {
            u = uDao.buscar(u.getID_USUARIO());
            bPersonal = true;
            bContActual = "true";
            setUsuarioNuevo(u.getUSUARIO());

        } else {
            bPersonal = false;
            bContActual = "false";

        }

    }
    private String strConActual;

    public String getStrConActual() {
        return strConActual;
    }

    public void setStrConActual(String strConActual) {
        this.strConActual = strConActual;
    }

    public boolean isbPersonal() {
        return bPersonal;
    }

    public void setbPersonal(boolean bPersonal) {
        this.bPersonal = bPersonal;
    }

    public String getbContActual() {
        return bContActual;
    }

    public void setbContActual(String bContActual) {
        this.bContActual = bContActual;
    }

    public void redireccionar(String Id) {
        try {
//            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta!", "Modificando DOCUMENTAL: " + ID_DOCUMENTAL));
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/usuario/usuarioInsertUpd.xhtml?ID=" + Id);

        } catch (IOException ex) {
            System.out.println("error" + ex);
        }

    }

    public void redireccionarPersMenu(String Id) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/usuario/menuPersonalizado.xhtml?ID_USUARIO=" + Id);
        } catch (IOException ex) {
            System.out.println("error" + ex);
        }
    }

}
