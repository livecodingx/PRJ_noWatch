package com.tecsup.prj_educacion.services;

import com.tecsup.prj_educacion.modelo.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    public void grabar(Usuario usuario);
    public Usuario buscar(int id);
    public List<Usuario> listar();
    public void actualizar(Usuario usuario);
    public void borrar(int id);
    public Usuario buscarPorCorreo(String correo);
    public Usuario validar(String correo, String contraseña);
    public boolean tienePermisoAdmin(int idUsuario);
}