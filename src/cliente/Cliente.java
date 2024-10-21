
package cliente;

import utils.Rol;
import java.time.LocalDate;

public class Cliente {
    private String id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String contraseña;
    private String curp;
    private Rol rol;

    public Cliente(String id, String nombre, String apellidos, String email, String telefono, LocalDate fechaNacimiento, String direccion, String contraseña, String curp) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.contraseña = contraseña;
        this.curp = curp;
        this.rol = Rol.CLIENTE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


    public boolean esElegibleParaDescuento() {
        LocalDate hoy = LocalDate.now();
        return hoy.getMonth() == fechaNacimiento.getMonth();
    }

    public String mostrarDatos() {
        return "ID: " + id +
               "\nNombre: " + nombre +
               "\nApellidos: " + apellidos +
               "\nEmail: " + email +
               "\nTeléfono: " + telefono +
               "\nFecha de Nacimiento: " + fechaNacimiento +
               "\nDirección: " + direccion +
               "\nCURP: " + curp +
               "\nRol: " + rol;
    }
}