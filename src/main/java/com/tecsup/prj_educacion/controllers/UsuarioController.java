package com.tecsup.prj_educacion.controllers;

import com.tecsup.prj_educacion.modelo.entities.Usuario;
import com.tecsup.prj_educacion.services.UsuarioService;
import com.tecsup.prj_educacion.services.impl.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsuarioController", urlPatterns = {"/sRegistrar", "/sBuscar", "/sListar", "/sActualizar", "/sEliminar", "/sValidar", "/sPermisos"})
public class UsuarioController extends HttpServlet {

    private UsuarioService servicio;

    public UsuarioController() {
        this.servicio = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/sRegistrar":
                registrarUsuario(request, response);
                break;
            case "/sActualizar":
                actualizarUsuario(request, response);
                break;
            case "/sValidar":
                validarUsuario(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/sBuscar":
                buscarUsuario(request, response);
                break;
            case "/sListar":
                listarUsuarios(request, response);
                break;
            case "/sEliminar":
                eliminarUsuario(request, response);
                break;
            case "/sPermisos":
                verificarPermisos(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("txtNombre");
        String correo = request.getParameter("txtCorreo");
        String contraseña = request.getParameter("txtContraseña");
        String estadoCuenta = request.getParameter("txtEstadoCuenta");

        Usuario usuario = new Usuario(nombre, correo, contraseña, estadoCuenta);
        servicio.grabar(usuario);

        response.sendRedirect("exito.jsp");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("txtIdUsuario"));
        String nombre = request.getParameter("txtNombre");
        String correo = request.getParameter("txtCorreo");
        String contraseña = request.getParameter("txtContraseña");
        String estadoCuenta = request.getParameter("txtEstadoCuenta");

        Usuario usuario = new Usuario(id, nombre, correo, contraseña, estadoCuenta);
        servicio.actualizar(usuario);

        response.sendRedirect("exito.jsp");
    }

    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("txtIdUsuario"));
        Usuario usuario = servicio.buscar(id);

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("mostrarUsuario.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = servicio.listar();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("txtIdUsuario"));
        servicio.borrar(id);
        response.sendRedirect("exito.jsp");
    }

    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("txtCorreo");
        String contraseña = request.getParameter("txtContraseña");

        Usuario usuario = servicio.validar(correo, contraseña);

        if (usuario != null) {
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("bienvenidaUsuario.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void verificarPermisos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));
        if (servicio.tienePermisoAdmin(idUsuario)) {
            response.sendRedirect("admin.jsp");
        } else {
            response.sendRedirect("bienvenidaUsuario.jsp");
        }
    }
}