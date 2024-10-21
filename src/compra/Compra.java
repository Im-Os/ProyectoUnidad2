package compra;

import boleto.Boleto;
import java.util.Date;
import java.util.List;

public class Compra {
    private int id;
    private double precioTotal;
    private Date fechaCompra;
    private String tipoPago;
    private List<Boleto> boletos;
    private List<String> productosAdicionales;

    public Compra(int id, double precioTotal, Date fechaCompra, String tipoPago, List<Boleto> boletos, List<String> productosAdicionales) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
        this.tipoPago = tipoPago;
        this.boletos = boletos;
        this.productosAdicionales = productosAdicionales;
    }

    public Compra() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public List<String> getProductosAdicionales() {
        return productosAdicionales;
    }

    public void setProductosAdicionales(List<String> productosAdicionales) {
        this.productosAdicionales = productosAdicionales;
    }

    public String mostrarInfoCompra() {
        StringBuilder info = new StringBuilder();
        info.append("ID de Compra: ").append(id).append("\n");
        info.append("Fecha de Compra: ").append(fechaCompra).append("\n");
        info.append("Precio Total: $").append(String.format("%.2f", precioTotal)).append("\n");
        info.append("Tipo de Pago: ").append(tipoPago).append("\n");
        info.append("Boletos:\n");
        for (Boleto boleto : boletos) {
            info.append("  ").append(boleto.mostrarInfoBoleto()).append("\n");
        }
        info.append("Productos Adicionales:\n");
        for (String producto : productosAdicionales) {
            info.append("  ").append(producto).append("\n");
        }
        return info.toString();
    }
}