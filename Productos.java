package abarrotesPersistencia;

import excepciones.ProductoExistenteException;
import excepciones.ProductoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.Producto;

/**
 * Clase que gestiona la persistencia de los productos. Permite agregar,
 * consultar, actualizar y eliminar productos, así como realizar consultas por
 * clave, tipo y unidad. También guarda y carga los productos desde un archivo
 * de datos.
 *
 * @author Alejandra García Preciado
 */
public class Productos {

    /**
     * Lista que almacena los productos.
     */
    public List<Producto> productos; 
    
    /**
     * Nombre del archivo en el que se guardarán los productos.
     */
    private final String archivo = "productos.dat"; // nombre del archivo en el que se guardarán los productos 

    /**
     * Constructor que inicializa la lista de productos y carga los productos
     * desde el archivo al iniciar la instancia.
     */
    public Productos() {
        this.productos = new ArrayList<>();
        cargarProductos(); // cargar productos al iniciar la instancia
    }

    /**
     * Guarda los productos en el archivo.
     */
    private void guardarProductos() { // guarda los productos en el archivo
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) { // se crea un ObjectOutputStream para escribir en el archivo productos.dat
            salida.writeObject(productos); // se escribe la lista de productos en el archivo
        } catch (IOException ex) {
            ex.printStackTrace(); // si ocurre alguna excepción durante la escritura, se imprime el error
        }
    }

    /**
     * Carga los productos desde el archivo.
     */
    private void cargarProductos() { // carga los productos desde el archivo
        File archivoProductos = new File(archivo); // se crea un objeto File con el nombre del archivo de persistencia
        if (archivoProductos.exists()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) { // se crea un ObjectInputStream para leer desde el archivo productos.dat
                productos = (List<Producto>) entrada.readObject(); // se lee la lista de productos desde el archivo y se asigna a la variable productos
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace(); // si ocurre alguna excepción durante la lectura, se imprime el error
            }
        }
    }

    /**
     * Agrega un nuevo producto al catálogo.
     *
     * @param producto El producto a agregar.
     * @throws ProductoInvalidoException Si el producto es inválido.
     * @throws ProductoExistenteException Si el producto ya existe en el
     * catálogo.
     */
    public void agregarProducto(Producto producto) throws ProductoInvalidoException, ProductoExistenteException {
        if (productos.contains(producto)) {
            throw new ProductoExistenteException("El producto ya existe.");
        }

        if (!producto.getClave().matches("[A-Z]{2}[0-9]{3}")) {
            throw new ProductoInvalidoException("La clave del producto no es válida.");
        }

        if (producto.getNombre() == null || producto.getTipo() == null || producto.getUnidad() == null) {
            throw new ProductoInvalidoException("El producto debe tener nombre, tipo y unidad.");
        }

        if (!producto.getUnidad().equals("kg") && !producto.getUnidad().equals("l")) {
            throw new ProductoInvalidoException("La unidad del producto debe ser kg o l");
        }

        if (!producto.getTipo().equals("E") && !producto.getTipo().equals("G")) {
            throw new ProductoInvalidoException("El tipo del producto debe ser E o G.");
        }

        productos.add(producto);
        guardarProductos();

    }

    /**
     * Consulta un producto por su clave.
     *
     * @param clave La clave del producto a consultar.
     * @return El producto consultado.
     * @throws ProductoNoEncontradoException Si el producto no existe en el
     * catálogo.
     */
    public Producto consultarProducto(String clave) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getClave().equals(clave)) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException("El producto no existe.");
    }

    /**
     * Actualiza la información de un producto en el catálogo.
     *
     * @param producto El producto con la información actualizada.
     * @throws ProductoNoEncontradoException Si el producto no existe en el
     * catálogo.
     * @throws ProductoInvalidoException Si el producto es inválido.
     * @throws ProductoExistenteException Si ya existe un producto con la misma
     * clave en el catálogo.
     */
    public void actualizarProducto(Producto producto) throws ProductoNoEncontradoException, ProductoInvalidoException, ProductoExistenteException {
        if (!productos.contains(producto)) {
            throw new ProductoNoEncontradoException("El producto no existe.");
        }

        if (!producto.getClave().matches("[A-Z]{2}[0-9]{3}")) {
            throw new ProductoInvalidoException("La clave del producto no es válida.");
        }

        if (producto.getNombre() == null || producto.getTipo() == null || producto.getUnidad() == null) {
            throw new ProductoInvalidoException("El producto debe tener nombre, tipo y unidad.");
        }

        if (!producto.getUnidad().equals("kg") && !producto.getUnidad().equals("l")) {
            throw new ProductoInvalidoException("La unidad del producto debe ser kg o l");
        }

        if (!producto.getTipo().equals("E") && !producto.getTipo().equals("G")) {
            throw new ProductoInvalidoException("El tipo del producto debe ser E o G.");
        }

        productos.set(productos.indexOf(producto), producto);
        guardarProductos();
    }

    /**
     * Elimina un producto del catálogo.
     *
     * @param clave La clave del producto a eliminar.
     * @throws ProductoNoEncontradoException Si el producto no existe en el
     * catálogo.
     */
    public void eliminarProducto(String clave) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getClave().equals(clave)) {
                productos.remove(producto);
                guardarProductos();
                return;
            }
        }
        throw new ProductoNoEncontradoException("El producto no existe");
    }

    /**
     * Consulta todos los productos en el catálogo.
     *
     * @return Una lista con todos los productos en el catálogo.
     */
    public List<Producto> consultarProductos() {
        return productos;
    }

    /**
     * Consulta los productos en el catálogo que coinciden con una clave dada.
     *
     * @param clave La clave de los productos a consultar.
     * @return Una lista con los productos que coinciden con la clave dada.
     */
    public List<Producto> consultarProductosPorClave(String clave) {
        List<Producto> productosPorClave = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getClave().equals(clave)) {
                productosPorClave.add(producto);
            }
        }
        return productosPorClave;
    }

    /**
     * Consulta los productos en el catálogo que coinciden con un tipo dado.
     *
     * @param tipo El tipo de los productos a consultar.
     * @return Una lista con los productos que coinciden con el tipo dado.
     */
    public List<Producto> consultarProductosPorTipo(String tipo) {
        List<Producto> productosPorTipo = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getTipo().equals(tipo)) {
                productosPorTipo.add(producto);
            }
        }
        return productosPorTipo;
    }

    /**
     * Consulta los productos en el catálogo que coinciden con una unidad dada.
     *
     * @param unidad La unidad de los productos a consultar.
     * @return Una lista con los productos que coinciden con la unidad dada.
     */
    public List<Producto> consultarProductosPorUnidad(String unidad) {
        List<Producto> productosPorUnidad = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getUnidad().equals(unidad)) {
                productosPorUnidad.add(producto);
            }
        }
        return productosPorUnidad;
    }

}
