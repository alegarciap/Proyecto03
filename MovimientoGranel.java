package objetosNegocio;

import objetosServicio.Fecha;

/**
 * Clase que representa un movimiento de un producto a granel (comprado o
 * vendido). Hereda de la clase Movimiento.
 *
 * @author Alejandra García Preciado
 */
public class MovimientoGranel extends Movimiento {

    // Atributos 
    /**
     * El tipo de movimiento (compra o venta).
     */
    private int tipoMov; // 0 si es compra, 1 si es venta
    
    
    private float cantidad;
    
    // Constructores

    /**
     * Constructor por defecto que inicializa los atributos con valores
     * predeterminados.
     */
    public MovimientoGranel() {
        this.tipoMov = tipoMov;
        this.cantidad = 0.0f;
    }
    
    /**
     * Constructor que inicializa el movimiento con los valores dados.
     *
     * @param claveMov La clave del movimiento.
     * @param fecha La fecha del movimiento.
     * @param status El status del movimiento.
     * @param claveProducto La clave del producto.
     */
    public MovimientoGranel(String claveMov, Fecha fecha, boolean status, String claveProducto) {
        super(claveProducto, fecha, status, claveProducto);
        this.tipoMov = tipoMov;
        this.cantidad = cantidad;
    }

    // Métodos de acceso
    /**
     * Obtiene el tipo de movimiento asociado al movimiento de granel.
     *
     * @return El tipo de movimiento asociado al movimiento de granel.
     */
    public int getTipoMov() {
        return tipoMov;
    }

    /**
     * Establece el tipo de movimiento asociado al movimiento de granel.
     *
     * @param tipoMov El nuevo tipo de movimiento asociado al movimiento de
     * granel.
     */
    public void setTipoMov(int tipoMov) {
        this.tipoMov = tipoMov;
    }
    
    /**
     * Obtiene la cantidad del movimiento granel.
     *
     * @return La cantidad del movimiento.
     */
    @Override
    public float getCantidad() {
        return cantidad;
    }
    
    /**
     * Establece la cantidad del movimiento.
     *
     * @param cantidad La nueva cantidad del movimiento granel.
     */
    @Override
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    // Método toString
    /**
     * Devuelve una representación en forma de cadena de este MovimientoGranel.
     *
     * @return Una cadena que contiene la representación en cadena del producto
     * a granel asociado.
     */
    @Override
    public String toString() {
        return tipoMov + super.toString() + "," + cantidad + ",";
    }

}
