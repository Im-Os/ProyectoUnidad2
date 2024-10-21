package admin;

import utils.Rol;

import java.time.LocalDate;

public class Admin {
    private String id;
    private String name;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String sueldo;
    private String password;
    private Rol rol;

    public Admin(String id, String name, String apellidos, String fechaNacimiento, String telefono, String sueldo, String password) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.password = password;
        this.rol = Rol.ADMINISTRATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public String mostrarInformacion() {
        return "ID: " + id +
               "\nNombre: " + name +
               "\nApellidos: " + apellidos +
               "\nFecha de Nacimiento: " + fechaNacimiento +
               "\nTeléfono: " + telefono +
               "\nSueldo: " + sueldo +
               "\nRol: " + rol;
    }
}