package objetosNegocio;

/**
 * Clase que representa un movimiento de un producto a granel (comprado o vendido).
 * 
 * @author Alejandra García Preciado
 */
public class MovimientoGranel extends Movimiento {
    
    // Atributos 
    private Movimiento tipoMov; // compra o venta
    private ProductoGranel productoGranel;
    
    // Constructores
    /**
     * Constructor por defecto que inicializa el producto a granel en null.
     */
    public MovimientoGranel(){
        this.productoGranel = null;
    }
    
    /**
     * Constructor que inicializa el movimiento con un producto a granel dado.
     *
     * @param productoGranel El producto a granel asociado al movimiento.
     */
    public MovimientoGranel(ProductoGranel productoGranel) {
        this.productoGranel = productoGranel;
    }
    
    /**
     * Constructor que inicializa el movimiento con una clave de movimiento.
     * 
     * @param cveMovimiento La clave del movimiento.
     */
    public MovimientoGranel(String claveMov){
        this.tipoMov = null;
    }
    
    // Métodos de acceso
    
    public Movimiento getTipoMov() {
        return tipoMov;
    }
    
    public void setTipoMov(Movimiento tipoMov) {
        this.tipoMov = tipoMov;
    }
    
    /**
     * Obtiene el producto a granel asociado al movimiento.
     * 
     * @return El producto a granel asociado al movimiento.
     */
    public ProductoGranel getProductoGranel(){
        return productoGranel;
    }
    
    /**
     * Establece el producto a granel asociado al movimiento.
     * 
     * @param productoGranel El nuevo producto a granel asociado al movimiento.
     */
    public void setProductoGranel(ProductoGranel productoGranel){
        this.productoGranel = productoGranel;
    }
    
    // Método toString
    /**
     * Devuelve una representación en forma de cadena de este MovimientoGranel.
     * 
     * @return Una cadena que contiene la representación en cadena del producto a granel asociado.
     */
    @Override
    public String toString(){
        return productoGranel.toString();
    }
    
}
