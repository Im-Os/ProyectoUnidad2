package pelicula;

import horario.Horario;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private int id;
    private String genero;
    private String titulo;
    private String clasificacion;
    private int duracion; // En minutos
    private String sinopsis;
    private List<Horario> horarios;

    public Pelicula(int id, String genero, String titulo, String clasificacion, int duracion, String sinopsis) {
        this.id = id;
        this.genero = genero;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.horarios = new ArrayList<>();
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public void agregarHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public String mostrarDatos() {
        StringBuilder datos = new StringBuilder();
        datos.append(String.format(
                "ID: %d\nGénero: %s\nTítulo: %s\nClasificación: %s\nDuración: %d min\nSinopsis: %s\nHorarios:\n",
                id, genero, titulo, clasificacion, duracion, sinopsis));
        if (horarios != null && !horarios.isEmpty()) {
            for (Horario horario : horarios) {
                datos.append(horario.mostrarInfoHorario()).append("\n");
            }
        } else {
            datos.append("No hay horarios disponibles.\n");
        }
        return datos.toString();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo;
    }
}