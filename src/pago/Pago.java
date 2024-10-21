package pago;

public class Pago {
    private double monto;
    private String metodoPago;

    public Pago(double monto, String metodoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public boolean procesarPago() {
        // Aquí se implementaría la lógica real de procesamiento de pago
        // Por ahora, simplemente simularemos que el pago siempre es exitoso
        System.out.println("Procesando pago de $" + monto + " con " + metodoPago);
        return true;
    }

    public String mostrarInfoPago() {
        return "Monto: $" + getMonto() + " - Método de Pago: " + getMetodoPago() + " - Pago Procesado: " + (procesarPago() ? "Sí" : "No");
    }
}
