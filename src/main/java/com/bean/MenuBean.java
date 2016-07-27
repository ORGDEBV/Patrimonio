/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.MenuDao;
import com.dao.MenuPersonalizadoDao;
import com.dao.impl.DaoFactory;
import com.entidad.Menu;
import com.entidad.MenuPersonalizado;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;
import static com.util.Constantes.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

    List<Menu> lMenuInicial;
    List<Menu> lMenuModificado;
    private TreeNode checkMenu;
    private TreeNode[] checkMenuSeleccionado;
    private MenuDao mDao;
    private MenuPersonalizadoDao mpDao;
    private int ID_USUARIO;

    public MenuBean() {
        //ID_USUARIO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO_ID_USUARIO").toString());
    }

    public void cargaPagina() throws IOException {
        DaoFactory factory = DaoFactory.getInstance();
        mDao = factory.getMenuDao(MENU);
        mpDao = factory.getMenuPersonalizadoDao(MENU_PERSONALIZADO);
        if (ID_USUARIO != 0) {
            lMenuInicial = mDao.buscarIdUsuario(ID_USUARIO);
            cargaCheckMenu();
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "Información", "Menús no pudieron ser asignados."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/usuario/usuarioLista.xhtml");
        }
    }

    public void cargaCheckMenu() {
        List<Menu> lMenuRaiz = mDao.buscarMenuPadre(ID_USUARIO);
        this.checkMenu = new DefaultTreeNode("Raiz", null);
        adicionarNos(lMenuRaiz, this.checkMenu);
    }

    private void adicionarNos(List<Menu> lMenu, TreeNode padre) {
        for (Menu menu : lMenu) {
            TreeNode no = new DefaultTreeNode(menu, padre);
            switch (menu.getPERSONALIZADO()) {
                case 2:
                    if (menu.getASIGNADO() == 1) {
                        no.setSelected(true);
                    }
                    break;
                case 1:
                    no.setSelected(true);
                    break;
                case 0:
                    no.setSelected(false);
                    break;
                default:
                    break;
            }
            no.setExpanded(true);
            adicionarNos(mDao.buscarMenuHijo(ID_USUARIO, menu.getID_MENU()), no);
        }
    }

    public void eventCheckBox() {
        lMenuModificado = new ArrayList<>();
        for (TreeNode tree : checkMenuSeleccionado) {
            Menu m = (Menu) tree.getData();
            lMenuModificado.add(m);
        }
    }

    public void grabarCambios() throws IOException {
        limpiarArrays();
        List<MenuPersonalizado> lInsert = new ArrayList<>();
        List<MenuPersonalizado> Eliminados = preparaInsert(lMenuInicial, 0);
        List<MenuPersonalizado> Agregados = preparaInsert(lMenuModificado, 1);
        lInsert.addAll(Agregados);
        lInsert.addAll(Eliminados);
        int aux = 0;
        for (int i = 0; i < lInsert.size(); i++) {
            int j = mpDao.insert(lInsert.get(i));
            if (j == 0) {
                aux++;
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (aux == 0) {
            context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se guardó los cambios con éxito."));
        } else {
            context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "Información", aux + " Menús no pudieron ser asignados."));
        }
        RequestContext.getCurrentInstance().update("gMensaje");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/usuario/menuPersonalizado.xhtml?ID_USUARIO=" + ID_USUARIO);
    }

    public List<MenuPersonalizado> preparaInsert(List<Menu> lArrayMenu, int flag) {
        int ID_USUARIO_REGISTRO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO_ID_USUARIO").toString());
        List<MenuPersonalizado> lMenuPersonalizado = new ArrayList<>();
        for (int i = 0; i < lArrayMenu.size(); i++) {
            if (lArrayMenu.get(i).getID_MENU() != 0) {
                MenuPersonalizado ump = new MenuPersonalizado();
                ump.setID_USUARIO(ID_USUARIO);
                ump.setID_MENU(lArrayMenu.get(i).getID_MENU());
                ump.setLECTURA("1");
                ump.setESCRITURA("1");
                ump.setELIMINACION("1");
                ump.setID_USUARIO_REGISTRO(ID_USUARIO_REGISTRO);
                ump.setFECHA_REGISTRO("");
                ump.setFLAG(flag);
                lMenuPersonalizado.add(ump);
            }
        }
        return lMenuPersonalizado;
    }

    public void limpiarArrays() {
        for (int i = 0; i < lMenuInicial.size(); i++) {
            for (int j = 0; j < lMenuModificado.size(); j++) {
                int idInicial = lMenuInicial.get(i).getID_MENU();
                int idModificado = lMenuModificado.get(j).getID_MENU();
                if (idInicial == idModificado) {
                    Menu men = new Menu();
                    men.setID_MENU(0);
                    lMenuInicial.set(i, men);
                    lMenuModificado.set(j, men);
                }
            }
        }
    }

    public void restablecerPerfil() throws IOException {
        int i = mpDao.restablecerPerfil(ID_USUARIO);
        FacesContext context = FacesContext.getCurrentInstance();
        if (i != 0) {
            context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se reinició el perfíl con éxito."));
        } else {
            context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrió un error."));
        }
        RequestContext.getCurrentInstance().update("gMensaje");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Patrimonio/depdb/usuario/menuPersonalizado.xhtml?ID_USUARIO=" + ID_USUARIO);
    }

    public TreeNode getCheckMenu() {
        return checkMenu;
    }

    public void setCheckMenu(TreeNode checkMenu) {
        this.checkMenu = checkMenu;
    }

    public TreeNode[] getCheckMenuSeleccionado() {
        return checkMenuSeleccionado;
    }

    public void setCheckMenuSeleccionado(TreeNode[] checkMenuSeleccionado) {
        this.checkMenuSeleccionado = checkMenuSeleccionado;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

}
