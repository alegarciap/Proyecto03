package objetosNegocio;
import java.util.Objects;

/**
 * La clase Producto representa un producto comercializado en la abarrotera.
 * 
 * @author Alejandra Garc�a Preciado
 */
public class Producto {
    
    // Atributos
    /** La clave del producto. */
    private String clave;
    
    /** El nombre del producto. */
    private String nombre;
    
    /** El tipo del producto. */
    private String tipo;
    
    /** La unidad del producto. */
    private String unidad;
        
    // Constructores
    /**
     * Constructor por defecto que inicializa los atributos con valores predeterminados.
     */
    public Producto(){  // Constructor por ausencia o por defecto, se asignan cadenas vac�as a los atributos
        this.clave = "";
        this.nombre = "";
        this.tipo = "";
        this.unidad = "";
    }
    
    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     * @param clave La clave del producto.
     * @param nombre El nombre del producto.
     * @param tipo El tipo del producto.
     * @param unidad La unidad del producto.
     */
    public Producto(String clave, String nombre, String tipo, String unidad){
        this.clave = clave;
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidad = unidad;
    }
    
    /**
     * Constructor que inicializa la clave del producto y los dem�s atributos en null o ''.
     * 
     * @param clave La clave del producto.
     */
    public Producto(String clave){
        this.clave = clave;
        this.nombre = null;
        this.tipo = "";
        this.unidad = null;
    }
    
    /**
     * Constructor de copia que inicializa los atributos con los valores de otro Producto.
     * @param copiaProducto El Producto del cual se copian los valores.
     */
    public Producto(Producto copiaProducto){
        this.clave = copiaProducto.clave;
        this.nombre = copiaProducto.nombre;
        this.tipo = copiaProducto.tipo;
        this.unidad = copiaProducto.unidad;
    }
    
    // M�todos de acceso
    /**
     * Obtiene la clave del producto.
     * 
     * @return La clave del producto.
     */
    public String getClave(){
        return clave;
    }
    
    /**
     * Establece la clave del producto.
     * 
     * @param clave La nueva clave del producto.
     */
    public void setClave(String clave){
        this.clave = clave;
    }
    
    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el tipo del producto.
     * 
     * @return El tipo del producto.
     */
    public String getTipo(){
        return tipo;
    }
    
    /**
     * Establece el tipo del producto.
     * 
     * @param tipo El nuevo tipo del producto.
     */
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    /**
     * Obtiene la unidad del producto.
     * 
     * @return La unidad del producto.
     */
    public String getUnidad(){
        return unidad;
    }
    
    /**
     * Establece la unidad del producto.
     * 
     * @param unidad La nueva unidad del producto.
     */
    public void setUnidad(String unidad){
        this.unidad = unidad;
    }
    
    // M�todo equals
    /**
     * Compara este Producto con otro objeto para determinar si son iguales.
     * 
     * @param objeto El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object objeto){
        /*
        El m�todo equals en Java se utiliza para comparar si dos objetos son iguales en cuanto a su contenido, no en referencia. 
        Este m�todo est� definido en la clase Object.
        */
        
        /*
        Esta l�nea comprueba si el objeto que est� llamando al m�todo (this) es igual al objeto pasado como argumento. 
        Si son la misma instancia (es decir, si apuntan a la misma ubicaci�n en la memoria), el m�todo devuelve true.
        */
        if (this == objeto) return true;
        
        
        /*
        Esta l�nea comprueba si el objeto pasado es null o si no es de la misma clase que la clase actual (Producto). 
        Si alguna de estas condiciones se cumple, el m�todo devuelve false, ya que dos objetos de diferentes clases nunca pueden ser iguales.
        */
        if (objeto == null || getClass() != objeto.getClass()) return false;
        
        
        /*
        Aqu� convertimos el objeto pasado como argumento (objeto) en un objeto de tipo Producto. 
        Esto se hace mediante un casting expl�cito.
        */
        Producto producto = (Producto) objeto;
        
        
        /*
        Finalmente, esta l�nea compara los atributos relevantes de los dos objetos para determinar si son iguales. 
        En este caso, se compara el atributo clave de la clase Producto. 
        Se utiliza el m�todo Objects.equals para comparar estos atributos de manera segura, evitando errores si alguno de los atributos es null.
        */
        return Objects.equals(clave, producto.clave);
    }
    
    // M�todo hashcode
    /**
     * Calcula el valor hash de este Producto.
     * 
     * @return El valor hash del Producto.
     */
    @Override
    public int hashCode(){
        /*
        El m�todo hashCode en Java se utiliza para calcular un valor num�rico que representa de forma �nica un objeto. 
        La implementaci�n de hashCode deber�a garantizar que dos objetos que son iguales seg�n el m�todo equals devuelvan el mismo valor de hashCode. 
        */
        return Objects.hash(clave);
    }
    
    // M�todo toString
    /**
     * Devuelve una representaci�n en forma de cadena de este Producto.
     * 
     * @return Una cadena que contiene los valores de los atributos del Producto.
     */
    @Override
    public String toString(){
        return clave + "," + nombre + "," + tipo + "," + unidad;
    }
    
}
