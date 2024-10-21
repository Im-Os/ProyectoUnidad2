package menu;

import gestionsistema.gestionSistema;
import cliente.Cliente;
import admin.Admin;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class menuLogin {
    private static gestionSistema sistema = new gestionSistema();
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nBienvenido a Cinepolis");
            System.out.println("1. Cliente");
            System.out.println("2. Administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    manejarCliente();
                    break;
                case 2:
                    manejarAdmin();
                    break;
                case 3:
                    System.out.println("Gracias por usar Cinepolis. ¡Hasta pronto!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void manejarCliente() {
        boolean volverAlMenuPrincipal = false;
        while (!volverAlMenuPrincipal) {
            System.out.println("\n--- Área de Cliente ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    iniciarSesionCliente();
                    break;
                case 2:
                    crearCuentaCliente();
                    break;
                case 3:
                    volverAlMenuPrincipal = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void manejarAdmin() {
        boolean volverAlMenuPrincipal = false;
        while (!volverAlMenuPrincipal) {
            System.out.println("\n--- Área de Administrador ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    iniciarSesionAdmin();
                    break;
                case 2:
                    crearCuentaAdmin();
                    break;
                case 3:
                    volverAlMenuPrincipal = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void iniciarSesionCliente() {
        int intentos = 5;
        while (intentos > 0) {
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String contraseña = scanner.nextLine();

            Cliente cliente = sistema.buscarCliente(nombre, contraseña);
            if (cliente != null) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + cliente.getNombre() + "!");
                menuCliente.mostrarMenuCliente(cliente);
                return;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + intentos);
                } else {
                    System.out.println("Número máximo de intentos alcanzado. Volviendo al menú de cliente.");
                }
            }
        }
    }

    private static void crearCuentaCliente() {
        System.out.println("\n--- Crear nueva cuenta de cliente ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Fecha de nacimiento (DD/MM/YYYY): ");
        String fechaNacStr = scanner.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("CURP: ");
        String curp = scanner.nextLine();

        String id = sistema.generarIDCliente();
        Cliente nuevoCliente = new Cliente(id, nombre, apellidos, email, telefono, fechaNacimiento, direccion, contraseña, curp);
        sistema.registrarCliente(nuevoCliente);

        System.out.println("Cuenta creada exitosamente. Su ID de cliente es: " + id);
    }

    private static void iniciarSesionAdmin() {
        int intentos = 5;
        while (intentos > 0) {
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String contraseña = scanner.nextLine();

            Admin admin = sistema.buscarAdmin(nombre, contraseña);
            if (admin != null) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, Administrador " + admin.getName() + "!");
                menuAdmin.mostrarMenuAdmin(admin);
                return;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + intentos);
                } else {
                    System.out.println("Número máximo de intentos alcanzado. Volviendo al menú de administrador.");
                }
            }
        }
    }

    private static void crearCuentaAdmin() {
        System.out.println("\n--- Crear nueva cuenta de administrador ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Fecha de nacimiento (DD/MM/YYYY): ");
        String fechaNacStr = scanner.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Sueldo: ");
        String sueldo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        String id = sistema.generarIDAdmin();
        Admin nuevoAdmin = new Admin(id, nombre, apellidos, fechaNacimiento.toString(), telefono, sueldo, contraseña);
        sistema.registrarAdmin(nuevoAdmin);

        System.out.println("Cuenta de administrador creada exitosamente. Su ID de administrador es: " + id);
    }
}