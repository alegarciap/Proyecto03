package abarrotesPersistencia;

import excepciones.MovimientoInvalidoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.MovimientoGranel;
import objetosNegocio.ProductoGranel;

/**
 *
 * @author alega
 */
public class MovimientosGranel {
    
    private List<MovimientoGranel> compras;
    private List<MovimientoGranel> ventas;
    private ProductoGranel productosGranel;
    
    private static final float LIMITE_MIN_KG = 300.0f;
    private static final float LIMITE_MAX_KG = 1500.0f;
    private static final float LIMITE_MIN_LT = 900.0f;
    private static final float LIMITE_MAX_LT = 3000.0f;
    
    public MovimientosGranel(ProductoGranel productosGranel) {
        this.compras = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.productosGranel = productosGranel;
    }
    
    // metodo para serializar la informacion
    
    public void agregarComprasGranel(MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {

        // verificar que el producto esté en el catálogo
        if (!ProductosGranel.productosGranel.contains(movimientoGranel.getProductoGranel())) {
            throw new MovimientoInvalidoException("El producto no se encuentra en el catálogo.");
        }
        
        // verificar la fecha del registro
        if (movimientoGranel.getFecha().getAnio() != LocalDate.now().getYear() || movimientoGranel.getFecha().getMes() != LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }

        // verificar que no haya otro movimiento de ese producto ese día 
        for (MovimientoGranel movimiento : compras) {
            if (movimiento.getProductoGranel().equals(movimientoGranel.getProductoGranel()) && movimiento.getFecha().equals(movimientoGranel.getFecha())) {
                throw new MovimientoInvalidoException("Ya hay un movimiento de ese producto en la fecha especificada.");
            }
        }

        // verificar que al comprar el producto granel la cantidad total no sobrepase de 1500 o 3000
        int cantidadTotal = 0;
        for (MovimientoGranel movimiento : compras) {
            if (movimiento.getProductoGranel().equals(movimientoGranel.getProductoGranel())) {
                cantidadTotal += movimiento.getProductoGranel().getCantidad();
            }
        }
        cantidadTotal += movimientoGranel.getProductoGranel().getCantidad();

        if ("kg".equals(movimientoGranel.getProductoGranel().getUnidad())) {
            if (cantidadTotal > LIMITE_MAX_KG) {
                throw new MovimientoInvalidoException("La cantidad total del producto granel excede el límite permitido.");
            }
        } else if ("l".equals(movimientoGranel.getProductoGranel().getUnidad())) {
            if (cantidadTotal > LIMITE_MAX_LT) {
                throw new MovimientoInvalidoException("La cantidad total del producto granel excede el límite permitido.");
            }
        }

        // actualizar el inventario
        ProductoGranel producto = movimientoGranel.getProductoGranel();
        if (ProductosGranel.productosGranel.contains(producto)) {
            ProductoGranel productoExistente = ProductosGranel.productosGranel.get(ProductosGranel.productosGranel.indexOf(producto));
            productoExistente.setCantidad(productoExistente.getCantidad() + movimientoGranel.getProductoGranel().getCantidad());
        } else {
            producto.setCantidad(movimientoGranel.getProductoGranel().getCantidad());
            ProductosGranel.productosGranel.add(producto);
        }

        // agregar el movimiento a la lista de compras
        movimientoGranel.setStatus(false);
        compras.add(movimientoGranel);
    }
    
    public void agregarVentaGranel (MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        // Aquí falta la parte de verificar que el movimiento cumpla con las condiciones
        
        if (movimientoGranel.getFecha().getAnio() != LocalDate.now().getYear() || movimientoGranel.getFecha().getMes() != LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }
        
        ventas.add(movimientoGranel); 
    }
    
    public void agregarVentasGranel (MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        // verificar que el producto esté en el inventario
        if (!ProductosGranel.productosGranel.contains(movimientoGranel.getProductoGranel())) {
            throw new MovimientoInvalidoException("El producto no se encuentra en el catálogo.");
        }
        // verificar la fecha del registro
        if (movimientoGranel.getFecha().getAnio() != LocalDate.now().getYear() || movimientoGranel.getFecha().getMes() != LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }
        // verificar que no haya otro movimiento de ese producto ese día 
        for (MovimientoGranel movimiento : ventas) {
            if (movimiento.getProductoGranel().equals(movimientoGranel.getProductoGranel()) && movimiento.getFecha().equals(movimientoGranel.getFecha())) {
                throw new MovimientoInvalidoException("Ya hay un movimiento de ese producto en la fecha especificada.");
            }
        }
        // verificar que al vender el producto no quede menos del límite mínimo
        int cantidadTotal = 0;
        for (MovimientoGranel movimiento : ventas) {
        if ("kg".equals(movimientoGranel.getProductoGranel().getUnidad())) {
            if ( (movimientoGranel.getProductoGranel.getCantidad - movimiento.getProductoGranel.getCantidad)> LIMITE_MIN_KG) {
                throw new MovimientoInvalidoException("Cantidad insuficiente para el inventario.");
            }
        } else if ("l".equals(movimientoGranel.getProductoGranel().getUnidad())) {
            if (movimientoGranel.getProductoGranel.getCantidad - movimiento.getProductoGranel.getCantidad > LIMITE_MIN_LT) {
                throw new MovimientoInvalidoException("Cantidad insuficiente para el inventario.");
            }
        }

        // actualizar el inventario
        ProductoGranel producto = movimientoGranel.getProductoGranel();
        if (ProductosGranel.productosGranel.contains(producto)) {
            ProductoGranel productoExistente = ProductosGranel.productosGranel.get(ProductosGranel.productosGranel.indexOf(producto));
            productoExistente.setCantidad(productoExistente.getCantidad()- movimientoGranel.getProductoGranel().getCantidad());
        } else {
            producto.setCantidad(movimientoGranel.getProductoGranel().getCantidad());
            ProductosGranel.productosGranel.add(producto);
        }

        // agregar el movimiento a la lista de compras
        movimientoGranel.setStatus(false);
        ventas.add(movimientoGranel);
    }

    public List<MovimientoGranel> consultarRegistroCompras() {
        return compras;
    }
    
    public List<MovimientoGranel> consultarRegistroVentas() {
        return ventas;
    }
    
    public List<MovimientoGranel> consultarComprasPorPeriodo(Fecha fechaInicio, Fecha fechaFin) {
        List<MovimientoGranel> comprasPorPeriodo = new ArrayList<>();
        for (MovimientoGranel movimientoGranel : compras) {
            Fecha fechaMovimiento = movimientoGranel.getFecha();
            if (fechaMovimiento.after(fechaInicio) && fechaMovimiento.before(fechaFin)) {
                comprasPorPeriodo.add(movimientoGranel);
            }
        }
        return comprasPorPeriodo;
    }
    
    public List<MovimientoGranel> consultarVentasPorPeriodo(Fecha fechaInicio, Fecha fechaFin) {
        List<MovimientoGranel> ventasPorPeriodo = new ArrayList<>();
        for (MovimientoGranel movimientoGranel : ventas) {
            Fecha fechaMovimiento = movimientoGranel.getFecha();
            if (fechaMovimiento.after(fechaInicio) && fechaMovimiento.before(fechaFin)) {
                ventasPorPeriodo.add(movimientoGranel);
            }
        }
        return ventasPorPeriodo;
    }
    
}
