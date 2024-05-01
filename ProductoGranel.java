package objetosNegocio;

/**
 * Clase que representa un producto a granel, que es un tipo de producto.
 * Hereda de la clase Producto.
 * 
 * @author Alejandra García Preciado
 */
public class ProductoGranel extends Producto {
    
    // Atributos
    private double existencia;
    private float cantidad;
    
    // Constructores
    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     * @param clave La clave del producto a granel.
     * @param nombre El nombre del producto a granel.
     * @param tipo El tipo del producto a granel.
     * @param unidad La unidad del producto a granel.
     * @param cantidad La cantidad del producto a granel.
     * @param existencia
     */
    public ProductoGranel(String clave, String nombre, String tipo, String unidad, float cantidad, double existencia) {
        super(clave, nombre, tipo, unidad);
        this.cantidad = cantidad;
        this.existencia = existencia;
    }
    
    public float getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getExistencia() {
        return existencia;
    }
    
    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }
    
    // Método toString
    /**
     * Devuelve una representación en forma de cadena de este ProductoGranel.
     * 
     * @return Una cadena que contiene los valores de los atributos del ProductoGranel.
     */
    @Override
    public String toString(){
        return super.toString() + "," + existencia;
    }
    
}
