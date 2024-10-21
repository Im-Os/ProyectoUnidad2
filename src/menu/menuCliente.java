package menu;

import cliente.Cliente;
import gestionsistema.gestionSistema;
import pelicula.Pelicula;
import sala.Sala;
import asiento.Asiento;
import reservacion.Reservacion;
import boleto.Boleto;
import compra.Compra;
import dulceria.Dulceria;
import horario.Horario;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class menuCliente {
    private static Scanner sc = new Scanner(System.in);
    private static gestionSistema sistema = new gestionSistema();

    public static void mostrarMenuCliente(Cliente cliente) {

    }

    private static void verCartelera() {
        List<Pelicula> peliculas = sistema.getPeliculas();
        System.out.println("\nCartelera actual:");
        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula.mostrarDatos());
            System.out.println("--------------------");
        }
    }

    public void crearReservacion(Cliente cliente) {
        verCartelera();
        Pelicula pelicula = seleccionarPelicula();

        Horario horario = seleccionarHorario(pelicula);
        if (horario == null) return;

        List<Sala> salasDisponibles = sistema.getSalasDisponibles(pelicula, horario);
        if (salasDisponibles.isEmpty()) {
            System.out.println("No hay salas disponibles para esta película y horario.");
            return;
        }

        System.out.println("Salas disponibles:");
        for (Sala sala : salasDisponibles) {
            System.out.println(sala.mostrarInfoSala());
        }

        Sala salaSeleccionada = seleccionarSala(salasDisponibles);
        if (salaSeleccionada == null) return;

        List<Asiento> asientosSeleccionados = seleccionarAsientos(salaSeleccionada);
        if (asientosSeleccionados.isEmpty()) {
            System.out.println("No se seleccionaron asientos. La reservación ha sido cancelada.");
            return;
        }

        Reservacion reservacion = sistema.crearReservacion(cliente, pelicula, salaSeleccionada, horario, asientosSeleccionados);
        System.out.println("Reservación realizada con éxito. Detalles de la reservación:");
        System.out.println(reservacion.mostrarInfoReservacion());
    }

    private static Pelicula seleccionarPelicula() {
        while (true) {
            System.out.print("Seleccione el ID de la película: ");
            int idPelicula = leerEntero();
            Pelicula pelicula = sistema.obtenerPeliculaPorId(idPelicula);
            if (pelicula != null) {
                // Mostrar información de la película y horarios disponibles
                System.out.println(pelicula.mostrarDatos());
                return pelicula;
            }
            System.out.println("Película no encontrada. Por favor, intente de nuevo.");
        }
    }

    private static Horario seleccionarHorario(Pelicula pelicula) {
        List<Horario> horarios = pelicula.getHorarios();
        if (horarios.isEmpty()) {
            System.out.println("No hay horarios disponibles para esta película.");
            return null;
        }

        System.out.println("Horarios disponibles:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ". " + horarios.get(i).mostrarInfoHorario());
        }

        while (true) {
            System.out.print("Seleccione el número del horario: ");
            int seleccion = leerEntero();
            if (seleccion >= 1 && seleccion <= horarios.size()) {
                return horarios.get(seleccion - 1);
            }
            System.out.println("Selección no válida. Por favor, intente de nuevo.");
        }
    }

    private static Sala seleccionarSala(List<Sala> salasDisponibles) {
        while (true) {
            System.out.print("Seleccione el número de sala: ");
            int numeroSala = leerEntero();
            for (Sala sala : salasDisponibles) {
                if (sala.getNumeroSala() == numeroSala) {
                    return sala;
                }
            }
            System.out.println("Sala no válida. Por favor, intente de nuevo.");
        }
    }

    private static List<Asiento> seleccionarAsientos(Sala sala) {
        List<Asiento> asientosSeleccionados = new ArrayList<>();
        while (true) {
            sala.imprimirMatrizAsientos();
            System.out.print("Ingrese el número de asiento a reservar (0 para terminar): ");
            int numeroAsiento = leerEntero();

            if (numeroAsiento == 0) {
                break;
            }

            if (sistema.verificarDisponibilidadAsiento(sala, numeroAsiento)) {
                Asiento asiento = sala.getAsientos().get(numeroAsiento - 1);
                asientosSeleccionados.add(asiento);
                asiento.setDisponible(false);
                System.out.println("Asiento " + numeroAsiento + " reservado.");
            } else {
                System.out.println("El asiento " + numeroAsiento + " no está disponible.");
            }
        }
        return asientosSeleccionados;
    }

    private static void verReservaciones(Cliente cliente) {
        List<Reservacion> reservacionesCliente = sistema.obtenerReservacionesPorCliente(cliente);
        if (reservacionesCliente.isEmpty()) {
            System.out.println("No tienes reservaciones activas.");
        } else {
            System.out.println("Tus reservaciones:");
            for (Reservacion reservacion : reservacionesCliente) {
                System.out.println(reservacion.mostrarInfoReservacion());
                System.out.println("--------------------");
            }
        }
    }

    private static void comprarBoletos(Cliente cliente) {
        verCartelera();
        System.out.print("Seleccione el ID de la película: ");
        int idPelicula = leerEntero();

        Pelicula pelicula = gestionSistema.obtenerPeliculaPorId(idPelicula);
        if (pelicula == null) {
            System.out.println("Película no encontrada.");
            return;
        }

        List<Sala> salasDisponibles = sistema.getSalasPorPelicula(pelicula);
        if (salasDisponibles.isEmpty()) {
            System.out.println("No hay salas disponibles para esta película.");
            return;
        }

        System.out.println("Salas disponibles:");
        for (Sala sala : salasDisponibles) {
            System.out.println(sala.getNumeroSala() + ": " + sala.getCapacidad() + " asientos");
        }

        System.out.print("Seleccione el número de sala: ");
        int numeroSala = leerEntero();

        Sala salaSeleccionada = sistema.obtenerSalaPorNumero(numeroSala);
        if (salaSeleccionada == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        List<Boleto> boletosComprados = new ArrayList<>();
        List<Asiento> asientosSeleccionados = seleccionarAsientos(salaSeleccionada);

        for (Asiento asiento : asientosSeleccionados) {
            Boleto boleto = sistema.crearBoleto(cliente, pelicula, salaSeleccionada, asiento);
            boletosComprados.add(boleto);
            System.out.println("Boleto para el asiento " + asiento.getNumero() + " comprado.");
        }

        if (!boletosComprados.isEmpty()) {
            System.out.println("\n¿Desea agregar productos de dulcería? (s/n)");
            String respuesta = sc.nextLine();
            List<Dulceria> productosComprados = new ArrayList<>();

            if (respuesta.equalsIgnoreCase("s")) {
                while (true) {
                    sistema.mostrarProductosDulceria();
                    System.out.print("Ingrese el nombre del producto a comprar (0 para terminar): ");
                    String nombreProducto = sc.nextLine();

                    if (nombreProducto.equals("0")) {
                        break;
                    }

                    Dulceria producto = sistema.obtenerProductoDulceriaPorNombre(nombreProducto);
                    if (producto != null) {
                        productosComprados.add(producto);
                        System.out.println("Producto agregado: " + producto);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                }
            }

            System.out.print("Ingrese el tipo de pago (Efectivo/Tarjeta): ");
            String tipoPago = sc.nextLine();

            Compra compra = sistema.crearCompra(boletosComprados, productosComprados, tipoPago);
            System.out.println("Compra realizada con éxito. ID de compra: " + compra.getId());
            System.out.println(compra.mostrarInfoCompra());
        } else {
            System.out.println("No se compraron boletos. La compra ha sido cancelada.");
        }
    }

    public static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
    }
}