package gestionsistema;

import asiento.Asiento;
import boleto.Boleto;
import cine.Cine;
import cliente.Cliente;
import compra.Compra;
import dulceria.Dulceria;
import horario.Horario;
import pelicula.Pelicula;
import reservacion.Reservacion;
import sala.Sala;
import admin.Admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class gestionSistema {
    private static int ultimoIdPelicula = 0;
    private static int ultimoIdSala = 0;
    
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    public static ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    public ArrayList<Sala> listaSalas = new ArrayList<>();
    public ArrayList<Horario> listaHorarios = new ArrayList<>();
    public ArrayList<Reservacion> listaReservaciones = new ArrayList<>();
    public ArrayList<Compra> listaCompras = new ArrayList<>();
    public ArrayList<Cine> listaCines = new ArrayList<>();
    public ArrayList<Boleto> listaBoletos = new ArrayList<>();
    public ArrayList<Asiento> listaAsientos = new ArrayList<>();
    public ArrayList<Admin> listaAdmins = new ArrayList<>();
    public ArrayList<Dulceria> productosDulceria = new ArrayList<>();

    // Métodos para Cliente
    public void registrarCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    public String generarIDCliente() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int numeroAleatorio = random.nextInt(100000) + 1;
        return String.format("C%d%d%05d", anoActual, mesActual, numeroAleatorio);
    }

    public Cliente obtenerClientePorId(String idCliente) {
        return listaClientes.stream()
                .filter(c -> c.getId().equals(idCliente))
                .findFirst()
                .orElse(null);
    }

    public Cliente buscarCliente(String nombre, String contraseña) {
        return listaClientes.stream()
                .filter(c -> c.getNombre().equals(nombre) && c.getContraseña().equals(contraseña))
                .findFirst()
                .orElse(null);
    }

    // Métodos para Película
    public int generarIdPelicula() {
        return ++ultimoIdPelicula;
    }

    public void registrarPelicula(Pelicula pelicula) {
        pelicula.setId(generarIdPelicula());
        this.listaPeliculas.add(pelicula);
    }

    public List<Pelicula> getPeliculas() {
        return listaPeliculas;
    }

    public void mostrarPeliculas() {
        for (Pelicula pelicula : this.listaPeliculas) {
            System.out.println(pelicula.mostrarDatos());
        }
    }

    public void mostrarPeliculasSalas() {
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println("ID: " + pelicula.getId() + " - Título: " + pelicula.getTitulo());
        }
    }

    public static Pelicula obtenerPeliculaPorId(int idPelicula) {
        return listaPeliculas.stream()
                .filter(p -> p.getId() == idPelicula)
                .findFirst()
                .orElse(null);
    }

    // Métodos para Sala
    public int generarIdSala() {
        return ++ultimoIdSala;
    }

    public void registrarSala(Sala sala) {
        this.listaSalas.add(sala);
    }

    public List<Sala> getSalas() {
        return listaSalas;
    }

    public List<Sala> getSalasPorPeliculaYHorario(Pelicula pelicula, Horario horario) {
        return listaSalas.stream()
                .filter(sala -> sala.tienePeliculaYHorario(pelicula, horario))
                .collect(Collectors.toList());
    }

    public List<Sala> getSalasPorPelicula(Pelicula pelicula) {
        return listaSalas.stream()
                .filter(sala -> sala.getPeliculas().contains(pelicula))
                .collect(Collectors.toList());
    }

    public void mostrarSalas() {
        for (Sala sala : this.listaSalas) {
            System.out.println(sala.mostrarInfoSala());
        }
    }

    public Sala obtenerSalaPorNumero(int numeroSala) {
        return listaSalas.stream()
                .filter(sala -> sala.getNumeroSala() == numeroSala)
                .findFirst()
                .orElse(null);
    }

    // Métodos para Horario
    public void registrarHorario(Horario horario) {
        this.listaHorarios.add(horario);
    }

    public Horario obtenerHorarioPorId(Pelicula pelicula, int idHorario) {
        return pelicula.getHorarios().get(idHorario);
    }

    // Métodos para Reservación
    public void registrarReservacion(Reservacion reservacion) {
        this.listaReservaciones.add(reservacion);
    }

    public void mostrarReservaciones() {
        for (Reservacion reservacion : this.listaReservaciones) {
            System.out.println(reservacion.mostrarInfoReservacion());
        }
    }

    public Reservacion crearReservacion(Cliente cliente, Pelicula pelicula, Sala sala, Horario horario, List<Asiento> asientos) {
        int nuevoId = listaReservaciones.size() + 1;
        Reservacion reservacion = new Reservacion(nuevoId, cliente, pelicula, sala, horario, asientos);
        listaReservaciones.add(reservacion);
        return reservacion;
    }

    public List<Reservacion> obtenerReservacionesPorCliente(Cliente cliente) {
        return listaReservaciones.stream()
                .filter(reservacion -> reservacion.getCliente().getId().equals(cliente.getId()))
                .collect(Collectors.toList());
    }

    // Métodos para Compra
    public void registrarCompra(Compra compra) {
        this.listaCompras.add(compra);
    }

    public void mostrarCompras() {
        for (Compra compra : this.listaCompras) {
            System.out.println(compra.mostrarInfoCompra());
        }
    }

    public Compra crearCompra(List<Boleto> boletos, List<Dulceria> productosDulceria, String tipoPago) {
        double precioTotal = boletos.stream().mapToDouble(Boleto::getPrecio).sum() +
                             productosDulceria.stream().mapToDouble(Dulceria::getPrecio).sum();
        List<String> productosAdicionales = productosDulceria.stream()
                                            .map(Dulceria::getNombre)
                                            .collect(Collectors.toList());
        Compra compra = new Compra(listaCompras.size() + 1, precioTotal, new java.util.Date(), tipoPago, boletos, productosAdicionales);
        listaCompras.add(compra);
        return compra;
    }

    // Métodos para Cine
    public void registrarCine(Cine cine) {
        this.listaCines.add(cine);
    }

    public void mostrarCines() {
        for (Cine cine : this.listaCines) {
            System.out.println(cine);
        }
    }

    // Métodos para Boleto
    public void registrarBoleto(Boleto boleto) {
        this.listaBoletos.add(boleto);
    }

    public void mostrarBoletos() {
        for (Boleto boleto : this.listaBoletos) {
            System.out.println(boleto.mostrarInfoBoleto());
        }
    }

    public Boleto crearBoleto(Cliente cliente, Pelicula pelicula, Sala sala, Asiento asiento) {
        double precio = asiento.getPrecio();
        boolean tieneDescuento = false;

        if (cliente.esElegibleParaDescuento()) {
            if (asiento.getTipo().equals("PREMIUM")) {
                precio *= 0.4; // 60% de descuento
                tieneDescuento = true;
            } else if (asiento.getTipo().equals("VIP")) {
                precio *= 0.65; // 35% de descuento
                tieneDescuento = true;
            }
        }

        Boleto boleto = new Boleto(listaBoletos.size() + 1, pelicula, sala, precio, asiento, cliente, tieneDescuento);
        listaBoletos.add(boleto);
        asiento.setDisponible(false);
        return boleto;
    }

    // Métodos para Asiento
    public void registrarAsiento(Asiento asiento) {
        this.listaAsientos.add(asiento);
    }

    public boolean verificarDisponibilidadAsiento(Sala sala, int numeroAsiento) {
        return sala.getAsientos().stream()
                .filter(asiento -> asiento.getNumero() == numeroAsiento)
                .findFirst()
                .map(Asiento::isDisponible)
                .orElse(false);
    }

    // Métodos para Admin
    public void registrarAdmin(Admin admin) {
        this.listaAdmins.add(admin);
    }

    public Admin buscarAdmin(String nombre, String contraseña) {
        return listaAdmins.stream()
                .filter(a -> a.getName().equals(nombre) && a.getPassword().equals(contraseña))
                .findFirst()
                .orElse(null);
    }

    public String generarIDAdmin() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int numeroAleatorio = random.nextInt(10000) + 1;
        return String.format("A%d%d%04d", anoActual, mesActual, numeroAleatorio);
    }

    // Métodos para Dulcería
    public void mostrarProductosDulceria() {
        if (productosDulceria.isEmpty()) {
            System.out.println("No hay productos en la dulcería.");
        } else {
            for (Dulceria producto : productosDulceria) {
                System.out.println(producto);
            }
        }
    }

    public void registrarProductoDulceria(Dulceria producto) {
        productosDulceria.add(producto);
    }

    public Dulceria obtenerProductoDulceriaPorNombre(String nombre) {
        return productosDulceria.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarProductoDulceria(String nombre) {
        return productosDulceria.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    // Método para asignar película y horario a una sala
    public void asignarPeliculaYHorarioASala(Pelicula pelicula, Horario horario, Sala sala) {
        sala.agregarPelicula(pelicula);
        sala.agregarHorario(horario);
        System.out.println("Depuración: Película " + pelicula.getId() + " y horario " + horario.mostrarInfoHorario() + " asignados a sala " + sala.getNumeroSala());
    }

    public List<Sala> getSalasDisponibles(Pelicula pelicula, Horario horario) {
        List<Sala> salasDisponibles = getSalasPorPeliculaYHorario(pelicula, horario);
        if (salasDisponibles.isEmpty()) {
            System.out.println("No hay salas disponibles para esta película y horario.");
        }
        return salasDisponibles;
    }
}