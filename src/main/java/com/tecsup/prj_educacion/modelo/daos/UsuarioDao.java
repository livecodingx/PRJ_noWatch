package com.tecsup.prj_educacion.modelo.daos;

import com.tecsup.prj_educacion.modelo.entities.Usuario;
import com.tecsup.prj_educacion.modelo.entities.PermisoUsuario;

import java.util.List;

public interface UsuarioDao extends EntidadDao<Usuario, Integer> {
    public Usuario findByCorreo(String correo);
    public List<Usuario> findByEstadoCuenta(String estado);
    public Usuario findByCorreoYPassword(String correo, String contraseña);
    public List<PermisoUsuario> findPermisosByIdUsuario(int idUsuario);
}

