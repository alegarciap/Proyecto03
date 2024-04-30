package abarrotesPersistencia;

import excepciones.InventarioInvalidoException;
import excepciones.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.ProductoGranel;

/**
 *
 * @author alega
 */
public class ProductosGranel {
    
    private List<ProductoGranel> productosGranel; // inventario
    
    public ProductosGranel() {
        this.productosGranel = new ArrayList<>();
    }
    
    public void agregarProductosGranel(ProductoGranel productoGranel) {
        // Aquí falta la parte de verificar que el producto cumpla con las condiciones
        productosGranel.add(productoGranel);
    }
    
    public ProductoGranel consultarProductoGranel(String clave) throws ProductoNoEncontradoException {
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getClave().equals(clave)) {
                return productoGranel;
            }
        }
        throw new ProductoNoEncontradoException("El producto granel no existe");
    }
    
    public void actualizarInventario(ProductoGranel productoGranel, float cantidad) throws ProductoNoEncontradoException, InventarioInvalidoException {
        // Aquí falta la parte de verificar que el producto cumpla con las condiciones
        
        int indiceProducto = productosGranel.indexOf(productoGranel);
        if (indiceProducto < 0) {
            throw new ProductoNoEncontradoException("El producto granel no existe.");
        }
        
        if (cantidad < 0) {
            throw new InventarioInvalidoException("La cantidad a agregar no puede ser negativa.");
        }
        
        ProductoGranel productoGranelActualizado = new ProductoGranel(productoGranel.getClave(), productoGranel.getNombre(), productoGranel.getTipo(), productoGranel.getUnidad(), productoGranel.getCantidad());
        productosGranel.set(indiceProducto, productoGranelActualizado);
    }
    
    public void eliminarProductoGranel(String clave) throws ProductoNoEncontradoException {
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getClave().equals(clave)) {
                productosGranel.remove(productoGranel);
                return;
            }
        }
        throw new ProductoNoEncontradoException("El producto granel no existe.");
    }
    
    public List<ProductoGranel> consultarProductosGranel() {
        return productosGranel;
    }
    
    public List<ProductoGranel> consultarProductosGranelPorTipo(String tipo) {
        List<ProductoGranel> productosGranelPorTipo = new ArrayList<>();
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getTipo().equals(tipo)) {
                productosGranelPorTipo.add(productoGranel);
            }
        }
        return productosGranelPorTipo;
    }
    
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
