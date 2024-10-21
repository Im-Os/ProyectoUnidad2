package menu;

import admin.Admin;
import dulceria.Dulceria;
import horario.Horario;
import gestionsistema.gestionSistema;
import pelicula.Pelicula;
import sala.Sala;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class menuAdmin {
    public static Scanner sc = new Scanner(System.in);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static gestionSistema sistema = new gestionSistema();

    public static void mostrarMenuAdmin(Admin admin) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nBienvenido, " + admin.getName());
            System.out.println("1. Gestionar películas");
            System.out.println("2. Gestionar salas");
            System.out.println("3. Gestionar dulcería");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero("Opción no válida. Por favor, ingrese un número del 1 al 4: ");

            switch (opcion) {
                case 1:
                    gestionarPeliculas();
                    break;
                case 2:
                    gestionarSalas();
                    break;
                case 3:
                    gestionarDulceria();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void gestionarPeliculas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\nGestión de Películas");
            System.out.println("1. Ver películas");
            System.out.println("2. Agregar película");
            System.out.println("3. Editar película");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero("Opción no válida. Por favor, ingrese un número del 1 al 4: ");

            switch (opcion) {
                case 1:
                    sistema.mostrarPeliculas();
                    break;
                case 2:
                    agregarPelicula();
                    break;
                case 3:
                    editarPelicula();
                    break;
                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void agregarPelicula() {
        System.out.println("Ingrese los datos de la película a registrar: ");
        System.out.print("Género: ");
        String genero = sc.nextLine();
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Clasificación: ");
        String clasificacion = sc.nextLine();
        System.out.print("Duración (en minutos): ");
        int duracion = leerEntero("Duración no válida. Por favor, ingrese un número: ");
        System.out.print("Sinopsis: ");
        String sinopsis = sc.nextLine();

        Pelicula pelicula = new Pelicula(0, genero, titulo, clasificacion, duracion, sinopsis);
        sistema.registrarPelicula(pelicula);

        System.out.println("Película agregada con éxito. ID: " + pelicula.getId());

        // Pedir al usuario que ingrese horarios
        while (true) {
            try {
                System.out.print("Ingrese la hora de inicio (HH:mm): ");
                Date inicio = timeFormat.parse(sc.nextLine());

                // Calcular la hora de fin en base a la duración de la película
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(inicio);
                calendar.add(Calendar.MINUTE, pelicula.getDuracion());
                Date fin = calendar.getTime();

                Horario horario = new Horario(inicio, fin);
                pelicula.agregarHorario(horario);
                sistema.registrarHorario(horario);

                System.out.print("¿Desea agregar otro horario? (s/n): ");
                if (sc.nextLine().equalsIgnoreCase("n")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Formato de hora incorrecto. Intente de nuevo.");
            }
        }
        
        System.out.println("\nInformación de la Película:");
        System.out.println(pelicula.mostrarDatos());
    }

    private static void editarPelicula() {
        System.out.print("Ingrese el ID de la película a modificar: ");
        int id = leerEntero("ID no válido. Por favor, ingrese un número: ");
        
        Pelicula pelicula = gestionSistema.obtenerPeliculaPorId(id);
        if (pelicula == null) {
            System.out.println("No se encontró la película con el ID proporcionado.");
            return;
        }

        System.out.println("Datos actuales de la película:");
        System.out.println(pelicula.mostrarDatos());

        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");

        System.out.print("Nuevo título: ");
        String nuevoTitulo = sc.nextLine();
        if (!nuevoTitulo.isEmpty()) {
            pelicula.setTitulo(nuevoTitulo);
        }

        System.out.print("Nuevo género: ");
        String nuevoGenero = sc.nextLine();
        if (!nuevoGenero.isEmpty()) {
            pelicula.setGenero(nuevoGenero);
        }

        System.out.print("Nueva clasificación: ");
        String nuevaClasificacion = sc.nextLine();
        if (!nuevaClasificacion.isEmpty()) {
            pelicula.setClasificacion(nuevaClasificacion);
        }

        System.out.print("Nueva duración (en minutos): ");
        String nuevaDuracionStr = sc.nextLine();
        if (!nuevaDuracionStr.isEmpty()) {
            int nuevaDuracion = Integer.parseInt(nuevaDuracionStr);
            pelicula.setDuracion(nuevaDuracion);
        }

        System.out.print("Nueva sinopsis: ");
        String nuevaSinopsis = sc.nextLine();
        if (!nuevaSinopsis.isEmpty()) {
            pelicula.setSinopsis(nuevaSinopsis);
        }

        System.out.println("Película actualizada con éxito.");
        System.out.println(pelicula.mostrarDatos());
    }

    private static void gestionarSalas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\nGestión de Salas");
            System.out.println("1. Ver salas");
            System.out.println("2. Agregar sala");
            System.out.println("3. Asignar película a sala");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero("Opción no válida. Por favor, ingrese un número del 1 al 4: ");

            switch (opcion) {
                case 1:
                    sistema.mostrarSalas();
                    break;
                case 2:
                    agregarSala();
                    break;
                case 3:
                    asignarPeliculaASala();
                    break;
                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void agregarSala() {
        System.out.print("Ingrese el número de la sala: ");
        int numeroSala = leerEntero("Número de sala no válido. Por favor, ingrese un número: ");
        System.out.print("Ingrese la capacidad total de la sala: ");
        int capacidad = leerEntero("Capacidad no válida. Por favor, ingrese un número: ");
        
        int filas = 0;
        int columnas = 0;
        boolean distribucionValida = false;

        while (!distribucionValida) {
            System.out.print("Ingrese el número de filas: ");
            filas = leerEntero("Número de filas no válido. Por favor, ingrese un número: ");
            System.out.print("Ingrese el número de columnas: ");
            columnas = leerEntero("Número de columnas no válido. Por favor, ingrese un número: ");

            if (filas * columnas == capacidad) {
                distribucionValida = true;
            } else {
                System.out.println("Error: La distribución no coincide con la capacidad total. Por favor, intente de nuevo.");
            }
        }

        System.out.print("Ingrese la cantidad de asientos VIP: ");
        int cantidadAsientosVip = leerEntero("Cantidad no válida. Por favor, ingrese un número: ");
        System.out.print("Ingrese la cantidad de asientos Premium: ");
        int cantidadAsientosPremium = leerEntero("Cantidad no válida. Por favor, ingrese un número: ");

        int idSala = sistema.generarIdSala();
        Sala sala = new Sala(idSala, numeroSala, capacidad, cantidadAsientosVip, cantidadAsientosPremium, filas, columnas);
        sistema.registrarSala(sala);

        System.out.println("\nSala registrada exitosamente:");
        System.out.println(sala.mostrarInfoSala());
        sala.imprimirMatrizAsientos();
    }

    private static void asignarPeliculaASala() {
        sistema.mostrarPeliculasSalas();
        System.out.print("Seleccione el ID de la película: ");
        int idPelicula = leerEntero("ID de película no válido. Por favor, ingrese un número: ");

        Pelicula pelicula = gestionSistema.obtenerPeliculaPorId(idPelicula);
        if (pelicula == null) {
            System.out.println("Película no encontrada.");
            return;
        }

        sistema.mostrarSalas();
        System.out.print("Seleccione el número de sala: ");
        int numeroSala = leerEntero("Número de sala no válido. Por favor, ingrese un número: ");

        Sala sala = sistema.obtenerSalaPorNumero(numeroSala);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        System.out.println("Horarios disponibles para la película " + pelicula.getTitulo() + ":");
        List<Horario> horarios = pelicula.getHorarios();
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println(i + ": " + horarios.get(i).mostrarInfoHorario());
        }

        System.out.print("Seleccione el índice del horario: ");
        int horarioIndex = leerEntero("Índice de horario no válido. Por favor, ingrese un número: ");

        if (horarioIndex >= 0 && horarioIndex < horarios.size()) {
            Horario horario = horarios.get(horarioIndex);
            sistema.asignarPeliculaYHorarioASala(pelicula, horario, sala);
            System.out.println("Película y horario asignados a la sala con éxito.");
        } else {
            System.out.println("Índice de horario inválido.");
        }
    }

    private static void gestionarDulceria() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\nGestión de Dulcería");
            System.out.println("1. Ver productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero("Opción no válida. Por favor, ingrese un número del 1 al 5: ");

            switch (opcion) {
                case 1:
                    verProductosDulceria();
                    break;
                case 2:
                    agregarProductoDulceria();
                    break;
                case 3:
                    editarProductoDulceria();
                    break;
                case 4:
                    eliminarProductoDulceria();
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void verProductosDulceria() {
        System.out.println("\nProductos en la Dulcería:");
        sistema.mostrarProductosDulceria();
    }

    private static void agregarProductoDulceria() {
        System.out.print("Ingrese el nombre del nuevo producto: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = leerDouble("Precio no válido. Por favor, ingrese un número: ");

        Dulceria nuevoDulce = new Dulceria(nombre, precio);
        sistema.registrarProductoDulceria(nuevoDulce);
        System.out.println("Producto agregado con éxito.");
    }

    private static void editarProductoDulceria() {
        System.out.print("Ingrese el nombre del producto a editar: ");
        String nombre = sc.nextLine();

        Dulceria producto = sistema.obtenerProductoDulceriaPorNombre(nombre);
        if (producto == null) {
            System.out.println("No se encontró el producto con el nombre proporcionado.");
            return;
        }

        System.out.println("Datos actuales del producto:");
        System.out.println(producto);

        System.out.print("Nuevo nombre (deje en blanco para mantener el actual): ");
        String nuevoNombre = sc.nextLine();
        if (!nuevoNombre.isEmpty()) {
            producto.setNombre(nuevoNombre);
        }

        System.out.print("Nuevo precio (deje en blanco para mantener el actual): ");
        String nuevoPrecioStr = sc.nextLine();
        if (!nuevoPrecioStr.isEmpty()) {
            double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
            producto.setPrecio(nuevoPrecio);
        }

        System.out.println("Producto actualizado con éxito.");
        System.out.println(producto);
    }

    private static void eliminarProductoDulceria() {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombre = sc.nextLine();

        boolean eliminado = sistema.eliminarProductoDulceria(nombre);
        if (eliminado) {
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("No se encontró el producto con el nombre proporcionado.");
        }
    }

    private static int leerEntero(String mensajeError) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(mensajeError);
            }
        }
    }

    private static double leerDouble(String mensajeError) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(mensajeError);
            }
        }
    }
}