package sala;

import asiento.Asiento;
import horario.Horario;
import pelicula.Pelicula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sala {
    private int id;
    private int numeroSala;
    private int capacidad;
    private int cantidadAsientosVip;
    private int cantidadAsientosPremium;
    private List<Asiento> asientos;
    private Map<Pelicula, List<Horario>> peliculasYHorarios;
    private int filas;
    private int columnas;
    private Pelicula peliculaActual;

    public Sala(int id, int numeroSala, int capacidad, int cantidadAsientosVip, int cantidadAsientosPremium, int filas, int columnas) {
        this.id = id;
        this.numeroSala = numeroSala;
        this.capacidad = capacidad;
        this.cantidadAsientosVip = cantidadAsientosVip;
        this.cantidadAsientosPremium = cantidadAsientosPremium;
        this.asientos = new ArrayList<>();
        this.peliculasYHorarios = new HashMap<>();
        this.filas = filas;
        this.columnas = columnas;
        inicializarAsientos();
    }

    public void inicializarAsientos() {
        int numeroAsiento = 1;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String tipo;
                if (numeroAsiento <= cantidadAsientosVip) {
                    tipo = "VIP";
                } else if (numeroAsiento <= cantidadAsientosVip + cantidadAsientosPremium) {
                    tipo = "PREMIUM";
                } else {
                    tipo = "REGULAR";
                }
                asientos.add(new Asiento(numeroAsiento, tipo, i + 1, j + 1));
                numeroAsiento++;
            }
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCantidadAsientosVip() {
        return cantidadAsientosVip;
    }

    public void setCantidadAsientosVip(int cantidadAsientosVip) {
        this.cantidadAsientosVip = cantidadAsientosVip;
    }

    public int getCantidadAsientosPremium() {
        return cantidadAsientosPremium;
    }

    public void setCantidadAsientosPremium(int cantidadAsientosPremium) {
        this.cantidadAsientosPremium = cantidadAsientosPremium;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void agregarPelicula(Pelicula pelicula) {
        if (!this.peliculasYHorarios.containsKey(pelicula)) {
            this.peliculasYHorarios.put(pelicula, new ArrayList<>());
        }
        this.peliculaActual = pelicula;
    }

    public void agregarHorario(Horario horario) {
        if (this.peliculaActual != null) {
            this.peliculasYHorarios.get(this.peliculaActual).add(horario);
        } else {
            System.out.println("Error: No se ha agregado ninguna película a la sala.");
        }
    }

    public boolean tienePeliculaYHorario(Pelicula pelicula, Horario horario) {
        return this.peliculasYHorarios.containsKey(pelicula) &&
                this.peliculasYHorarios.get(pelicula).contains(horario);
    }

    public String mostrarInfoSala() {
        StringBuilder info = new StringBuilder();
        info.append("ID de Sala: ").append(getId()).append("\n")
                .append("Sala Nº: ").append(getNumeroSala()).append("\n")
                .append("Capacidad: ").append(getCapacidad()).append("\n")
                .append("Cantidad de Asientos VIP: ").append(getCantidadAsientosVip()).append("\n")
                .append("Cantidad de Asientos Premium: ").append(getCantidadAsientosPremium()).append("\n")
                .append("Distribución: ").append(getFilas()).append(" filas, ").append(getColumnas()).append(" columnas\n")
                .append("Películas y Horarios:\n");
        for (Map.Entry<Pelicula, List<Horario>> entry : peliculasYHorarios.entrySet()) {
            info.append(entry.getKey().getTitulo()).append(":\n");
            for (Horario horario : entry.getValue()) {
                info.append("\t").append(horario.mostrarInfoHorario()).append("\n");
            }
        }
        return info.toString();
    }

    public void imprimirMatrizAsientos() {
        System.out.println("                                  PANTALLA");
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Asiento asiento = asientos.get(i * columnas + j);
                char fila = (char) ('A' + i);
                String estado = asiento.isDisponible() ? "O" : "X";
                System.out.printf("%s%d:%s\t", fila, j + 1, estado);
            }
            System.out.println();
        }
        System.out.println("O: Disponible, X: No disponible");
    }

    public Asiento obtenerAsiento(char fila, int columna) {
        int filaIndex = fila - 'A';
        int columnaIndex = columna - 1;

        if (filaIndex >= 0 && filaIndex < filas && columnaIndex >= 0 && columnaIndex < columnas) {
            return asientos.get(filaIndex * columnas + columnaIndex);
        }
        return null;
    }

    public List<Pelicula> getPeliculas() {
        return new ArrayList<>(peliculasYHorarios.keySet());
    }

    public List<Horario> getHorarios(Pelicula pelicula) {
        return peliculasYHorarios.getOrDefault(pelicula, new ArrayList<>());
    }

    public void eliminarPelicula(Pelicula pelicula) {
        peliculasYHorarios.remove(pelicula);
        if (peliculaActual != null && peliculaActual.equals(pelicula)) {
            peliculaActual = null;
        }
    }

    public void eliminarHorario(Pelicula pelicula, Horario horario) {
        List<Horario> horarios = peliculasYHorarios.get(pelicula);
        if (horarios != null) {
            horarios.remove(horario);
            if (horarios.isEmpty()) {
                peliculasYHorarios.remove(pelicula);
                if (peliculaActual != null && peliculaActual.equals(pelicula)) {
                    peliculaActual = null;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Sala " + numeroSala + " (Capacidad: " + capacidad + " asientos)";
    }
}
