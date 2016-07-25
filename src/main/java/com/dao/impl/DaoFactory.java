package com.dao.impl;

import com.dao.*;
import static com.util.Constantes.*;

/**
 *
 * @author virtual
 */
public class DaoFactory {

    private DaoFactory() {
    }

    public AreaDao getAreaDao(int tipo) {
        AreaDao dao;
        switch (tipo) {
            case AREA:
                dao = new AreaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public MenuDao getMenuDao(int tipo) {
        MenuDao dao;
        switch (tipo) {
            case MENU:
                dao = new MenuDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public PerfilDao getPerfilDao(int tipo) {
        PerfilDao dao;
        switch (tipo) {
            case PERFIL:
                dao = new PerfilDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public PersonalDao getPersonalDao(int tipo) {
        PersonalDao dao;
        switch (tipo) {
            case PERSONAL:
                dao = new PersonalDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public UsuarioDao getUsuarioDao(int tipo) {
        UsuarioDao dao;
        switch (tipo) {
            case USUARIO:
                dao = new UsuarioDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public CajaDao getCajaDao(int tipo) {
        CajaDao dao;
        switch (tipo) {
            case CAJA:
                dao = new CajaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public DocumentalDao getDocumentalDao(int tipo) {
        DocumentalDao dao;
        switch (tipo) {
            case DOCUMENTAL:
                dao = new DocumentalDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public static DaoFactory getInstance() {
        return DaoFactoryHolder.INSTANCE;
    }

    private static class DaoFactoryHolder {

        private static final DaoFactory INSTANCE = new DaoFactory();
    }
}
