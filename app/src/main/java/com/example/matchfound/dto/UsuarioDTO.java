package com.example.matchfound.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private String username;
    private String correo;
    private String tag;
    private String descripcion;
    private String agente;
    private String rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String username, String correo, String tag, String descripcion, String agente, String rol) {
        this.username = username;
        this.correo = correo;
        this.tag = tag;
        this.descripcion = descripcion;
        this.agente = agente;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }
}
