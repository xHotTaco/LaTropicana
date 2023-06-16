package com.example.prueba2;

public class Usuario {
    private String username;
    private String password;
    private String tipo;
    private boolean exists;

    public Usuario(String username, String password, String tipo, boolean exists) {
        this.username = username;
        this.password = password;
        this.tipo = tipo;
        this.exists = exists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
