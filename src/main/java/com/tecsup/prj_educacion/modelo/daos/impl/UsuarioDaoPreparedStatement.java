package com.tecsup.prj_educacion.modelo.daos.impl;

import com.tecsup.prj_educacion.modelo.daos.UsuarioDao;
import com.tecsup.prj_educacion.modelo.entities.Usuario;
import com.tecsup.prj_educacion.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoPreparedStatement implements UsuarioDao {
    private Connection con;
    private ResultSet rs;
    private CallableStatement cst;

    @Override
    public void create(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (Nombre, Correo, Contraseña, Estadocuenta) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setString(3, usuario.getContraseña());
            pst.setString(4, usuario.getEstadocuenta());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción");
        }
    }

    @Override
    public Usuario find(Integer id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuarios WHERE idUsuario = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("Nombre"),
                        rs.getString("Correo"), rs.getString("Contraseña"),
                        rs.getString("Estadocuenta"), rs.getTimestamp("FechaCreacion"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
        }
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("Nombre"),
                        rs.getString("Correo"), rs.getString("Contraseña"),
                        rs.getString("Estadocuenta"), rs.getTimestamp("FechaCreacion"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
        }
        return usuarios;
    }

    @Override
    public void update(Usuario usuario) {
        String sql = "UPDATE Usuarios SET Nombre = ?, Correo = ?, Contraseña = ?, Estadocuenta = ? WHERE idUsuario = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setString(3, usuario.getContraseña());
            pst.setString(4, usuario.getEstadocuenta());
            pst.setInt(5, usuario.getIdUsuario());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario findByCorreo(String correo) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuarios WHERE Correo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, correo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("Nombre"),
                        rs.getString("Correo"), rs.getString("Contraseña"),
                        rs.getString("Estadocuenta"), rs.getTimestamp("FechaCreacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> findByEstadoCuenta(String estado) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios WHERE Estadocuenta = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, estado);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("Nombre"),
                        rs.getString("Correo"), rs.getString("Contraseña"),
                        rs.getString("Estadocuenta"), rs.getTimestamp("FechaCreacion"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario findByCorreoYPassword(String correo, String contraseña) {
        Usuario usuario = null;
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM Usuarios WHERE Correo = ? AND Contraseña = ?")) {
            pst.setString(1, correo);
            pst.setString(2, contraseña);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("Nombre"), rs.getString("Correo"), rs.getString("Contraseña"), rs.getString("Estadocuenta"), rs.getTimestamp("FechaCreacion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public List<PermisoUsuario> findPermisosByIdUsuario(int idUsuario) {
        return List.of();
    }
}