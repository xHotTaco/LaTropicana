package com.example.prueba2;

public class Cliente extends Usuario {
    private int id;
    private int mesa;
    private String fecha;
    private String hora;

    public Cliente(String username, String password, String tipo, boolean exists) {
        super(username, password, tipo, exists);
    }

    public Cliente(String username, String password, String tipo, boolean exists, int id, int mesa, String fecha, String hora) {
        super(username, password, tipo, exists);
        this.id = id;
        this.mesa = mesa;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
