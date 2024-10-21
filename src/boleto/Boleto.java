
package boleto;

import asiento.Asiento;
import cliente.Cliente;
import pelicula.Pelicula;
import sala.Sala;

public class Boleto {
    private int id;
    private Pelicula pelicula;
    private Sala sala;
    private double precio;
    private Asiento asiento;
    private String tipoAsiento; // VIP, PREMIUM, REGULAR
    private Cliente cliente;
    private boolean tieneDescuento;

    public Boleto(int id, Pelicula pelicula, Sala sala, double precio, Asiento asiento, Cliente cliente, boolean tieneDescuento) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.precio = precio;
        this.asiento = asiento;
        this.tipoAsiento = asiento.getTipo();
        this.cliente = cliente;
        this.tieneDescuento = tieneDescuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isTieneDescuento() {
        return tieneDescuento;
    }

    public void setTieneDescuento(boolean tieneDescuento) {
        this.tieneDescuento = tieneDescuento;
    }

    public String mostrarInfoBoleto() {
        return "Boleto ID: " + getId() +
                "\nPelícula: " + pelicula.getTitulo() +
                "\nSala: " + sala.getNumeroSala() +
                "\nPrecio: $" + getPrecio() +
                "\nAsiento: " + asiento.getNumero() +
                "\nTipo de Asiento: " + getTipoAsiento() +
                "\nCliente: " + cliente.getNombre() + " " + cliente.getApellidos() +
                "\nTiene Descuento: " + (isTieneDescuento() ? "Sí" : "No");
    }
}