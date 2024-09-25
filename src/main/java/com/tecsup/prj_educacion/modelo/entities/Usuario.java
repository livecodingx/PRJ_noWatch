package com.tecsup.prj_educacion.modelo.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usuario implements Serializable {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private String estadocuenta;

    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String correo, String contraseña, String estadocuenta, Timestamp fechaCreacion) {}

    public Usuario(int idUsuario, String nombre, String correo, String contraseña, String estadocuenta) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estadocuenta = estadocuenta;
    }

    public Usuario(String nombre, String correo, String contraseña, String estadoCuenta) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estadocuenta = estadoCuenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstadocuenta() {
        return estadocuenta;
    }

    public void setEstadocuenta(String estadocuenta) {
        this.estadocuenta = estadocuenta;
    }
}
