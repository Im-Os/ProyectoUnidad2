package asiento;

public class Asiento {
    private int numero;
    private boolean disponible;
    private boolean reservado;
    private String tipo; // VIP, PREMIUM, REGULAR
    private double precio;
    private int fila;
    private int columna;

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

    public String mostrarInfoAsiento() {
        return "Asiento: " + getNumero() + " - Tipo: " + getTipo() + " - Precio: $" + getPrecio() + 
               " - Disponible: " + (isDisponible() ? "Sí" : "No") + 
               " - Reservado: " + (isReservado() ? "Sí" : "No") +
               " - Fila: " + getFila() + " - Columna: " + getColumna();
    }
}