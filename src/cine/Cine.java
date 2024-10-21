package cine;

import sala.Sala;

import java.util.List;

public class Cine {
    private List<Sala> salas;

    public Cine(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public void agregarSala(Sala sala) {
        this.salas.add(sala);
    }

    public void eliminarSala(Sala sala) {
        this.salas.remove(sala);
    }
}