package abarrotesPersistencia;

import excepciones.ProductoExistenteException;
import excepciones.ProductoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.Producto;

/**
 *
 * @author alega prueba
 */
public class Productos {
    
    private List<Producto> productos;
    
    public Productos() {
        this.productos = new ArrayList<>();
    }
    
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
        
    }
    
    public Producto consultarProducto(String clave) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getClave().equals(clave)) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException("El producto no existe.");
    }
    
    public void actualizarProducto(Producto producto) throws ProductoNoEncontradoException, ProductoInvalidoException {
        if (!productos.contains(producto)) {
            throw new ProductoNoEncontradoException("El producto no existe.");
        }
        
        // Aquí falta la parte de verificar que el producto cumpla con las condiciones
        productos.set(productos.indexOf(producto), producto);
    }
    
    public void eliminarProducto(String clave) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getClave().equals(clave)) {
                productos.remove(producto);
                return;
            }
        }
        throw new ProductoNoEncontradoException("El producto no existe");
    }
    
    public List<Producto> consultarProductos() {
        return productos;
    }
    
    public List<Producto> consultarProductosPorTipo(String tipo) {
        List<Producto> productosPorTipo = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getTipo().equals(tipo)) {
                productosPorTipo.add(producto);
            }
        }
        return productosPorTipo;
    }
    
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
