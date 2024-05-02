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
import objetosNegocio.ProductoGranel;

/**
 * Clase que gestiona la persistencia de los productos a granel. Permite
 * agregar, consultar y eliminar productos a granel, así como realizar consultas
 * por tipo y unidad. También guarda y carga los productos a granel desde un
 * archivo de datos.
 *
 * @author Alejandra García Preciado
 */
public class ProductosGranel {

    /**
     * Lista que almacena los productos a granel.
     */
    public static List<ProductoGranel> productosGranel;

    /**
     * Nombre del archivo en el que se guardarán los productos a granel.
     */
    private final String archivo = "productosGranel.dat"; // nombre del archivo en el que se guardarán los productos 

    /**
     * Constructor que inicializa la lista de productos a granel y carga los
     * productos desde el archivo al iniciar la instancia.
     */
    public ProductosGranel() {
        ProductosGranel.productosGranel = new ArrayList<>();
        cargarProductos(); // cargar productos al iniciar la instancia
    }

    /**
     * Guarda los productos a granel en el archivo.
     */
    private void guardarProductos() { // guarda los productos en el archivo
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) { // se crea un ObjectOutputStream para escribir en el archivo productos.dat
            salida.writeObject(productosGranel); // se escribe la lista de productos en el archivo
        } catch (IOException ex) {
            ex.printStackTrace(); // si ocurre alguna excepción durante la escritura, se imprime el error
        }
    }

    /**
     * Carga los productos a granel desde el archivo.
     */
    private void cargarProductos() { // carga los productos desde el archivo
        File archivoProductos = new File(archivo); // se crea un objeto File con el nombre del archivo de persistencia
        if (archivoProductos.exists()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) { // se crea un ObjectInputStream para leer desde el archivo productos.dat
                productosGranel = (List<ProductoGranel>) entrada.readObject(); // se lee la lista de productos desde el archivo y se asigna a la variable productos
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace(); // si ocurre alguna excepción durante la lectura, se imprime el error
            }
        }
    }

    /**
     * Agrega un nuevo producto a granel al catálogo.
     *
     * @param productoGranel El producto a granel a agregar.
     * @throws ProductoInvalidoException Si el producto es inválido.
     * @throws ProductoExistenteException Si el producto ya existe en el
     * catálogo.
     */
    public void agregarProductoGranel(ProductoGranel productoGranel) throws ProductoExistenteException, ProductoInvalidoException {
        if (productosGranel.contains(productoGranel)) {
            throw new ProductoExistenteException("El producto ya existe.");
        }

        if (!productoGranel.getClave().matches("[A-Z]{2}[0-9]{3}")) {
            throw new ProductoInvalidoException("La clave del producto no es válida.");
        }

        if (productoGranel.getNombre() == null || productoGranel.getTipo() == null || productoGranel.getUnidad() == null) {
            throw new ProductoInvalidoException("El producto debe tener nombre, tipo y unidad.");
        }

        if (!productoGranel.getUnidad().equals("kg") && !productoGranel.getUnidad().equals("l")) {
            throw new ProductoInvalidoException("La unidad del producto debe ser kg o l");
        }

        if (!productoGranel.getTipo().equals("E") && !productoGranel.getTipo().equals("G")) {
            throw new ProductoInvalidoException("El tipo del producto debe ser E o G.");
        }

        productosGranel.add(productoGranel);
        guardarProductos();
    }

    /**
     * Consulta un producto a granel por su clave.
     *
     * @param clave La clave del producto a granel a consultar.
     * @return El producto a granel consultado.
     * @throws ProductoNoEncontradoException Si el producto a granel no existe
     * en el catálogo.
     */
    public ProductoGranel consultarProductoGranel(String clave) throws ProductoNoEncontradoException {
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getClave().equals(clave)) {
                return productoGranel;
            }
        }
        throw new ProductoNoEncontradoException("El producto granel no existe");
    }

    /**
     * Elimina un producto a granel del catálogo.
     *
     * @param clave La clave del producto a granel a eliminar.
     * @throws ProductoNoEncontradoException Si el producto a granel no existe
     * en el catálogo.
     */
    public void eliminarProductoGranel(String clave) throws ProductoNoEncontradoException {
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getClave().equals(clave)) {
                productosGranel.remove(productoGranel);
                guardarProductos();
                return;
            }
        }
        throw new ProductoNoEncontradoException("El producto granel no existe.");
    }

    /**
     * Consulta todos los productos a granel en el catálogo.
     *
     * @return Una lista con todos los productos a granel en el catálogo.
     */
    public List<ProductoGranel> consultarProductosGranel() {
        return productosGranel;
    }

    /**
     * Consulta los productos a granel en el catálogo que coinciden con una
     * unidad dada.
     *
     * @param unidad La unidad de los productos a granel a consultar.
     * @return Una lista con los productos a granel que coinciden con la unidad
     * dada.
     */
    public List<ProductoGranel> consultarProductosGranelPorUnidad(String unidad) {
        List<ProductoGranel> productosGranelPorUnidad = new ArrayList<>();
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getUnidad().equals(unidad)) {
                productosGranelPorUnidad.add(productoGranel);
            }
        }
        return productosGranelPorUnidad;
    }

}
