package abarrotesPersistencia;

import excepciones.ProductoExistenteException;
import excepciones.ProductoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.ProductoGranel;

/**
 *
 * @author alega
 */
public class ProductosGranel {
    
    public static List<ProductoGranel> productosGranel; // catálogo productos granel
    
    // metodo para serializar la informacion
    
    public ProductosGranel() {
        this.productosGranel = new ArrayList<>();
    }
    
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
    }
    
    public ProductoGranel consultarProductoGranel(String clave) throws ProductoNoEncontradoException {
        for (ProductoGranel productoGranel : productosGranel) {
            if (productoGranel.getClave().equals(clave)) {
                return productoGranel;
            }
        }
        throw new ProductoNoEncontradoException("El producto granel no existe");
    }
    
//    public void actualizarInventario(ProductoGranel productoGranel, float cantidad) throws ProductoNoEncontradoException, InventarioInvalidoException {
//        int indiceProducto = productosGranel.indexOf(productoGranel);
//        if (indiceProducto < 0) {
//            throw new ProductoNoEncontradoException("El producto granel no existe.");
//        }
//        
//        ProductoGranel productoGranelActualizado = new ProductoGranel(productoGranel.getClave(), productoGranel.getNombre(), productoGranel.getTipo(), productoGranel.getUnidad(), productoGranel.getCantidad() + cantidad);
//        productosGranel.set(indiceProducto, productoGranelActualizado);
//    }
    
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
