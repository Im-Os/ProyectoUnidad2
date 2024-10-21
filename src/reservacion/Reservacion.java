package reservacion;

import asiento.Asiento;
import cliente.Cliente;
import pelicula.Pelicula;
import sala.Sala;
import horario.Horario;

import java.util.List;

public class Reservacion {
    private int id;
    private Cliente cliente;
    private Pelicula pelicula;
    private Sala sala;
    private Horario horario;
    private List<Asiento> asientosReservados;
    private boolean confirmada;

    public Reservacion(int id, Cliente cliente, Pelicula pelicula, Sala sala, Horario horario, List<Asiento> asientosReservados) {
        this.id = id;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
        this.asientosReservados = asientosReservados;
        this.confirmada = false;
    }

    public Reservacion() {

    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Asiento> getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(List<Asiento> asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void confirmarReservacion() {
        this.confirmada = true;
    }

    public void cancelarReservacion() {
        this.confirmada = false;
    }

    public String mostrarInfoReservacion() {
        StringBuilder info = new StringBuilder();
        info.append("ID de Reservación: ").append(id).append("\n");
        info.append("Cliente: ").append(cliente.getNombre()).append(" ").append(cliente.getApellidos()).append("\n");
        info.append("Película: ").append(pelicula.getTitulo()).append("\n");
        info.append("Sala: ").append(sala.getNumeroSala()).append("\n");
        info.append("Horario: ").append(horario.mostrarInfoHorario()).append("\n");
        info.append("Asientos Reservados: ");
        for (Asiento asiento : asientosReservados) {
            info.append(asiento.getNumero()).append(" ");
        }
        info.append("\nConfirmada: ").append(confirmada ? "Sí" : "No");
        return info.toString();
    }
}