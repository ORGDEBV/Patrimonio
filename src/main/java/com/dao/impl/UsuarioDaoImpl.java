package com.dao.impl;

import com.dao.UsuarioDao;
import com.entidad.Usuario;
import com.util.cnSQL;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDaoImpl implements UsuarioDao {

    cnSQL SQL = new cnSQL();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public int insertar(Usuario u) {
        int out = 0;
        String msg = "";
        Connection cn = SQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL [PT].[SP_USUARIO_InsertarModificar](?,?,?,?,?,?,?)}");
            if (u.getID_USUARIO() == null) {
                u.setID_USUARIO(0);
            }
            cs.setInt(1, u.getID_USUARIO());
            cs.setInt(2, u.getID_PERSONAL());
            cs.setString(3, u.getUSUARIO());
            cs.setString(4, u.getCONTRASENA());
            cs.setInt(5, u.getID_PERFIL());
            if (u.isBoolActivo()) {
                u.setACTIVO("1");
            } else {
                u.setACTIVO("0");
            }
            cs.setString(6, u.getACTIVO());
            cs.setInt(7, u.getID_DEPOSITO());
            rs = cs.executeQuery();
            if (rs.next()) {
                out = rs.getInt(1);
                msg = rs.getString(2);
                System.out.println("error " + msg);
            }
        } catch (Exception e) {
            System.out.println("ERROR EN INSERTAR1" + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR EN INSERTAR2" + ex.getMessage());
            }
        }
        return out;
    }

    @Override
    public Usuario buscar(Integer codigo) {
        Usuario u = new Usuario();
        Connection cn = SQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL PT.SP_USUARIO_ListarId(?)}");
            cs.setInt(1, codigo);
            rs = cs.executeQuery();
            while (rs.next()) {
                u.setID_USUARIO(rs.getInt(1));
                u.setID_PERSONAL(rs.getInt(2));
                u.setUSUARIO(rs.getString(3));
                u.setCONTRASENA(rs.getString(4));
                u.setID_PERFIL(rs.getInt(5));
                if (rs.getString(6).equals("1")) {
                    u.setBoolActivo(true);
                } else {
                    u.setBoolActivo(false);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql :" + e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return u;
    }

    @Override
    public List<Object[]> listarUsuariosObject() {
        List<Object[]> lstUsuario = new ArrayList<>();
        Connection cn = SQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL PT.SP_USUARIO_Listar}");
            rs = cs.executeQuery();
            while (rs.next()) {

                lstUsuario.add(new Object[]{rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)});
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql :" + e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lstUsuario;
    }

    @Override
    public Usuario logIn(Usuario u) {
        Connection cn = SQL.getConnection();
        Usuario usuario = null;
        try {
            cs = cn.prepareCall("{CALL PT.SP_VALIDA_USUARIO(?)}");
            cs.setString(1, u.getUSUARIO());
            rs = cs.executeQuery();
            if (rs.next()) {
                cs = cn.prepareCall("{CALL PT.SP_VALIDA_CONTRASENA(?)}");
                cs.setString(1, u.getCONTRASENA());
                rs = cs.executeQuery();
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setID_USUARIO(rs.getInt(1));
                    usuario.setID_PERSONAL(rs.getInt(2));
                    usuario.setID_PERFIL(rs.getInt(3));
                    usuario.setCAMBIO_CONTRASENA(rs.getInt(4));
                    usuario.setFECHA_REGISTRO(rs.getDate(5));
                } else {
                    System.out.println("Contraseña Incorrecta.");
                }
            } else {
                System.out.println("Usuario Incorrecto.");
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
        return usuario;
    }

    @Override
    public boolean validaUsuario(String usuario) {
        boolean flag = false;
        Connection cn = SQL.getConnection();
        try {
            cs = cn.prepareCall("{CALL PT.SP_VALIDA_USUARIO(?)}");
            cs.setString(1, usuario);
            rs = cs.executeQuery();
            if (rs.next()) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (SQLException e) {
            System.out.println("Erro :" + e);
        }
        return flag;
    }

}
