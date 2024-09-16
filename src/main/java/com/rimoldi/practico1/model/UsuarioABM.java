package com.rimoldi.practico1.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioABM {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static int siguienteId = 1;

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static Usuario crearUsuario(String nombre, String email) {
        Usuario usuario = new Usuario(siguienteId++, nombre, email);
        usuarios.add(usuario);
        return usuario;
    }

    public static Usuario eliminarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarios.remove(usuario);
                return usuario;
            }
        }
        return null;
    }

    public static Usuario modificarUsuario(int id, String nombre, String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setNombre(nombre);
                usuario.setEmail(email);
                return usuario;
            }
        }
        return null;
    }
}