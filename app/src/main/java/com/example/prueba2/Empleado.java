package com.example.prueba2;

public class Empleado extends Usuario {
    private String nombre;
    private int edad;
    private String RFC;
    private String puesto;

    public Empleado(String username, String password, String tipo, boolean exists) {
        super(username, password, tipo, exists);
    }

    public Empleado(String username, String password, String tipo, boolean exists, String nombre, int edad, String RFC, String puesto) {
        super(username, password, tipo, exists);
        this.nombre = nombre;
        this.edad = edad;
        this.RFC = RFC;
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
