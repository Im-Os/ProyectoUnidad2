package asiento;

import static menu.menuAdmin.sc;
import static menu.menuCliente.leerEntero;

public class Asiento {
    private int numero;
    private boolean disponible;
    private boolean reservado;
    private String tipo; // VIP, PREMIUM, REGULAR
    private double precio;
    private int fila;
    private int columna;
    private String asi; // Variable para el asiento
    private String tip; // Variable para el tipo de asiento
    private String fil; // Variable para la fila (asumo que te refieres a fila)
    private String colum;
    private int res=0;
    private int pre=0;

    public Asiento(int numero, String tipo, int fila, int columna) {

        this.numero = numero;
        this.tipo = tipo;
        this.disponible = true;
        this.reservado = false;
        this.fila = fila;
        this.columna = columna;
        switch (tipo.toUpperCase()) {
            case "VIP":
                this.precio = 400.00;
                break;
            case "PREMIUM":
                this.precio = 200.00;
                break;
            default:
                this.precio = 100.00; // Precio por defecto para asientos regulares
        }
    }

    public Asiento() {

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Object mostrarInfoAsiento() {

        System.out.println("Ingrese el asiento que usted guste tener");
        System.out.print("Asiento: ");
        asi= sc.nextLine();
        System.out.print("Tipo de asiento: Normal(1), VIP(2) O PREMIUM(3): ");
        tip =sc.nextLine();
        System.out.print("Columna: ");
        colum = sc.nextLine();
        System.out.print("Fila: ");
        fil= sc.nextLine();
        do if(res>1){
            System.out.print("Este lugar ya ha sido apartado, por favor elija otro");
        } else{System.out.print("Reservado con exito");
        }
        while (res>0);
        if(pre==1) {
            pre=100;
        } else if (pre==2) {
            pre=200;
        }else if (pre==3) {
            pre=400;
        }


        return "Asiento: " + asi + " - Tipo: " + tip + " - Precio: $" + pre +
               " - Disponible: " + (isDisponible() ? "Sí" : "No") +
               " - Reservado: " + (isReservado() ? "Sí" : "No") +
               " - Fila: " + fil + " - Columna: " + colum;
    }

    private int leerEntero(String s) {
        return 0;
    }
}