package objetosNegocio;

import java.util.Objects;

/**
 * La clase Producto representa un producto comercializado en la abarrotera.
 * Contiene los atributos clave, nombre, tipo y unidad del producto. Proporciona
 * métodos para acceder y modificar estos atributos, así como para comparar dos
 * productos, calcular su valor hash y obtener una representación en forma de
 * cadena.
 *
 * @author Alejandra García Preciado
 */
public class Producto {

    // Atributos
    /**
     * La clave del producto.
     */
    private String clave;

    /**
     * El nombre del producto.
     */
    private String nombre;

    /**
     * El tipo del producto.
     */
    private String tipo;

    /**
     * La unidad del producto.
     */
    private String unidad;

    // Constructores
    /**
     * Constructor por defecto que inicializa los atributos con valores
     * predeterminados.
     */
    public Producto() {
        this.clave = "";
        this.nombre = "";
        this.tipo = "";
        this.unidad = "";
    }

    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     *
     * @param clave La clave del producto.
     * @param nombre El nombre del producto.
     * @param tipo El tipo del producto.
     * @param unidad La unidad del producto.
     */
    public Producto(String clave, String nombre, String tipo, String unidad) {
        this.clave = clave;
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidad = unidad;
    }

    /**
     * Constructor que inicializa la clave del producto y los demás atributos en
     * null o ''.
     *
     * @param clave La clave del producto.
     */
    public Producto(String clave) {
        this.clave = clave;
        this.nombre = null;
        this.tipo = "";
        this.unidad = null;
    }

    /**
     * Constructor de copia que inicializa los atributos con los valores de otro
     * Producto.
     *
     * @param copiaProducto El Producto del cual se copian los valores.
     */
    public Producto(Producto copiaProducto) {
        this.clave = copiaProducto.clave;
        this.nombre = copiaProducto.nombre;
        this.tipo = copiaProducto.tipo;
        this.unidad = copiaProducto.unidad;
    }

    // Métodos de acceso
    /**
     * Obtiene la clave del producto.
     *
     * @return La clave del producto.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la clave del producto.
     *
     * @param clave La nueva clave del producto.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo del producto.
     *
     * @return El tipo del producto.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del producto.
     *
     * @param tipo El nuevo tipo del producto.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la unidad del producto.
     *
     * @return La unidad del producto.
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * Establece la unidad del producto.
     *
     * @param unidad La nueva unidad del producto.
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    // Método equals
    /**
     * Compara este Producto con otro objeto para determinar si son iguales.
     *
     * @param objeto El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        Producto producto = (Producto) objeto;
        return Objects.equals(clave, producto.clave);
    }

    // Método hashcode
    /**
     * Calcula el valor hash de este Producto.
     *
     * @return El valor hash del Producto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(clave);
    }

    // Método toString
    /**
     * Devuelve una representación en forma de cadena de este Producto.
     *
     * @return Una cadena que contiene los valores de los atributos del
     * Producto.
     */
    @Override
    public String toString() {
        return clave + "," + nombre + "," + tipo + "," + unidad;
    }

}
