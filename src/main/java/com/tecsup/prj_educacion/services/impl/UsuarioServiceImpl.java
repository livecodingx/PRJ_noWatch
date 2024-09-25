package com.tecsup.prj_educacion.services.impl;

import com.tecsup.prj_educacion.modelo.daos.UsuarioDao;
import com.tecsup.prj_educacion.modelo.daos.impl.DaoFactory;
import com.tecsup.prj_educacion.modelo.entities.Usuario;
import com.tecsup.prj_educacion.services.UsuarioService;
import com.tecsup.prj_educacion.modelo.entities.PermiesoUsuario;
import com.tecsup.prj_educacion.util.Util;


import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDao dao;

    public UsuarioServiceImpl() {
        dao= DaoFactory.getUsuarioDao(Util.opc);
    }

    @Override
    public void grabar(Usuario usuario) {
        dao.create(usuario);
    }

    @Override
    public Usuario buscar(int id) {
        return dao.find(id);
    }

    @Override
    public List<Usuario> listar() {
        return dao.findAll();
    }

    @Override
    public void actualizar(Usuario usuario) {
        dao.update(usuario);
    }

    @Override
    public void borrar(int id) {
        dao.delete(id);
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return dao.findByCorreo(correo);
    }
    @Override
    public Usuario validar(String correo, String contraseña) {
        return dao.findByCorreoYPassword(correo, contraseña);
    }

    @Override
    public boolean tienePermisoAdmin(int idUsuario) {
        List<PermisoUsuario> permisos = dao.findPermisosByIdUsuario(idUsuario);
        for (PermisoUsuario permiso : permisos) {
            if (permiso.getNombrePermiso().equalsIgnoreCase("admin")) {
                return true;
            }
        }
        return false;
    }
}