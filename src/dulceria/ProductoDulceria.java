package dulceria;

public class ProductoDulceria {
    private int id;
    private String nombre;
    private double precio;

    public ProductoDulceria(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", Precio: $" + String.format("%.2f", precio);
    }
}