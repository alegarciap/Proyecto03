package objetosNegocio;

import objetosServicio.*;
import java.util.Objects;

/**
 * La clase Movimiento contiene los atributos y métodos comunes a un movimiento
 * (una compra o venta) de un producto empacado o un producto a granel. El
 * atributo status indica si el movimiento ya fue procesado, modificando el
 * inventario.
 *
 * @author Alejandra García Preciado
 */
public class Movimiento {

    // Atributos
    /**
     * La clave del movimiento.
     */
    private String claveMov;

    /**
     * La fecha del movimiento.
     */
    private Fecha fecha;

    /**
     * Indica si el movimiento ha sido procesado o no.
     */
    private boolean status;
    
    /**
     * La cantidad del movimiento.
     */
    private float cantidad;
    
    /**
     * La clave del producto relacionado al movimiento.
     */
    private String claveProducto;

    // Constructores
    /**
     * Constructor por defecto que inicializa los atributos con valores
     * predeterminados.
     */
    public Movimiento() {
        this.claveMov = claveMov;
        this.fecha = fecha;
        this.status = status;
        this.claveProducto = claveProducto;
    }

    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     *
     * @param claveMov La clave del movimiento.
     * @param fecha La fecha del movimiento.
     * @param status Indica si el movimiento ha sido procesado o no.
     */
    public Movimiento(String claveMov, Fecha fecha, boolean status, String claveProducto) {
        this.claveMov = claveMov;
        this.fecha = fecha;
        this.status = status;
        this.claveProducto = claveProducto;
    }

    /**
     * Constructor que inicializa la clave del movimiento.
     *
     * @param claveMov La clave del movimiento.
     */
    public Movimiento(String claveMov) {
        this.claveMov = claveMov;
        this.fecha = null;
        this.status = false;
    }

    // Métodos de acceso
    /**
     * Obtiene la clave del movimiento.
     *
     * @return La clave del movimiento.
     */
    public String getClaveMov() {
        return claveMov;
    }

    /**
     * Establece la clave del movimiento.
     *
     * @param claveMov La nueva clave del movimiento.
     */
    public void setClaveMov(String claveMov) {
        this.claveMov = claveMov;
    }

    /**
     * Obtiene la fecha del movimiento.
     *
     * @return La fecha del movimiento.
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del movimiento.
     *
     * @param fecha La nueva fecha del movimiento.
     */
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado de procesamiento del movimiento.
     *
     * @return true si el movimiento ha sido procesado, false si el movimiento
     * no ha sido procesado.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Establece el estado de procesamiento del movimiento.
     *
     * @param status El nuevo estado de procesamiento del movimiento.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * Obtiene la cantidad del movimiento.
     *
     * @return La cantidad del movimiento.
     */
    public float getCantidad() {
        return cantidad;
    }
    
    /**
     * Establece la cantidad del movimiento.
     *
     * @param cantidad La nueva cantidad del movimiento.
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Obtiene la clave del movimiento.
     *
     * @return La clave del movimiento.
     */
    public String getClaveProducto() {
        return claveProducto;
    }
    
    /**
     * Establece la clave del producto.
     *
     * @param claveProducto La nueva clave del producto.
     */
    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    // Método equals
    /**
     * Compara este Movimiento con otro objeto para determinar si son iguales.
     *
     * @param objeto El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object objeto) {
        // Comprobar si los objetos son la misma instancia
        if (this == objeto) {
            return true;
        }

        // Comprobar si el objeto pasado es nulo o no es de la misma clase
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }

        // Convertir el objeto pasado en un objeto de tipo Movimiento
        Movimiento movimiento = (Movimiento) objeto;

        // Comparar los atributos relevantes para determinar la igualdad
        return Objects.equals(claveMov, movimiento.claveMov);
    }

    // Método hashcode
    /**
     * Calcula el valor hash de este Movimiento.
     *
     * @return El valor hash del Movimiento.
     */
    @Override
    public int hashCode() {
        return Objects.hash(claveMov);
    }

    // Método toString
    /**
     * Devuelve una representación en forma de cadena de este Movimiento.
     *
     * @return Una cadena que contiene los valores de los atributos del
     * Movimiento.
     */
    @Override
    public String toString() {
        return claveMov + "," + fecha + "," + status + "," + claveProducto;
    }

}
