package objetosNegocio;
import objetosServicio.*;
import java.util.Objects;

/**
 * La clase Movimiento contiene los atributos y métodos comunes a un movimiento (una compra o venta) de un producto empacado o un producto a granel. 
 * El atributo procesado indica si el movimiento ya fue procesado, modificando el inventario.
 * 
 * @author Alejandra García Preciado
 */
public class Movimiento {
    
    // Atributos
    private String claveMov;
    private Fecha fecha;
    private boolean status;
    
    // Constructores
    /**
     * Constructor por defecto que inicializa los atributos con valores predeterminados.
     */
    public Movimiento(){
        this.claveMov = "";
        this.fecha = null;
        this.status = false;
    }
    
    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     * 
     * @param cveMovimiento La clave del movimiento.
     * @param fecha La fecha del movimiento.
     * @param procesado Indica si el movimiento ha sido procesado o no.
     */
    public Movimiento(String claveMov, Fecha fecha, boolean status){
        this.claveMov = claveMov;
        this.fecha = fecha;
        this.status = status;
    }
    
    /**
     * Constructor que inicializa la clave del movimiento.
     * 
     * @param cveMovimiento La clave del movimiento.
     */
    public Movimiento(String claveMov){
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
    public String getClaveMov(){
        return claveMov;
    }
    
    /**
     * Establece la clave del movimiento.
     * 
     * @param cveMovimiento La nueva clave del movimiento.
     */
    public void setClaveMov(String claveMov){
        this.claveMov = claveMov;
    }
    
    /**
     * Obtiene la fecha del movimiento.
     * 
     * @return La fecha del movimiento.
     */
    public Fecha getFecha(){
        return fecha;
    }
    
    /**
     * Establece la fecha del movimiento.
     * 
     * @param fecha La nueva fecha del movimiento.
     */
    public void setFecha(Fecha fecha){
        this.fecha = fecha;
    }
    
    /**
     * Obtiene el estado de procesamiento del movimiento.
     * 
     * @return true si el movimiento ha sido procesado, false si el movimiento no ha sido procesado.
     */
    public boolean getStatus(){
        return status;
    }
    
    /**
     * Establece el estado de procesamiento del movimiento.
     * 
     * @param procesado El nuevo estado de procesamiento del movimiento.
     */
    public void setStatus(boolean status){
        this.status = status;
    }
    
    // Método equals
    /**
     * Compara este Movimiento con otro objeto para determinar si son iguales.
     * 
     * @param objeto El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object objeto){
        /*
        El método equals en Java se utiliza para comparar si dos objetos son iguales en cuanto a su contenido, no en referencia. 
        Este método está definido en la clase Object.
        */
        // Comprobar si los objetos son la misma instancia
        if (this == objeto) return true;

        // Comprobar si el objeto pasado es nulo o no es de la misma clase
        if (objeto == null || getClass() != objeto.getClass()) return false;

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
    public int hashCode(){
        /*
        El método hashCode en Java se utiliza para calcular un valor numérico que representa de forma única un objeto. 
        La implementación de hashCode debería garantizar que dos objetos que son iguales según el método equals devuelvan el mismo valor de hashCode. 
        */
        return Objects.hash(claveMov);
    }
    
    // Método toString
    /**
         * Devuelve una representación en forma de cadena de este Movimiento.
     * 
     * @return Una cadena que contiene los valores de los atributos del Movimiento.
     */
    @Override
    public String toString(){
        return claveMov + "," + fecha + "," + status;
    }
    
}
