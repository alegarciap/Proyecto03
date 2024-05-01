package abarrotesPersistencia;

import excepciones.MovimientoInvalidoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.MovimientoGranel;
import objetosNegocio.Producto;
import objetosNegocio.ProductoGranel;

/**
 *
 * @author alega
 */
public class MovimientosGranel {
    
    private List<MovimientoGranel> compras;
    private List<MovimientoGranel> ventas;
    private ProductoGranel productosGranel;
    
    public MovimientosGranel(ProductoGranel productosGranel) {
        this.compras = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.productosGranel = productosGranel;
    }
    
    public void agregarComprasGranel(MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        
        // solicitar información del movimiento (clave, producto, fecha, cantidad) el tipo = compra y el status = falso
        // MovimientoGranel compra = new MovimientoGranel();
        // compra.getClaveMov();
        // compra.getProductoGranel();
        // compra.getFecha();
        // compra.getProductoGranel().getCantidad();
        // compra.setTipoMov(compra);
        // compra.setStatus(false);
        
        // verificar la fecha del registro
        if (movimientoGranel.getFecha().getAnio()!= LocalDate.now().getYear() || movimientoGranel.getFecha().getMes()!= LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }
        
        // verificar que el producto granel a comprar esté en el catálogo (lista de productos)
        
        // verificar que no haya otro movimiento de ese producto ese día 
        
        // verificar que al comprar el producto granel la cantidad total no sobrepase de 1500 o 3000
        
        // si el producto a comprar tiene existencia en el inventario lo actualiza (sumarle a la existencia)
        
        // si el producto no tiene existencia, lo agrega e inicializa el conteo (1)
        
        compras.add(movimientoGranel); // y se supone que tambien se agrega a productosGranel (el inventario)
    }
    
    public void agregarVentaGranel (MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        // Aquí falta la parte de verificar que el movimiento cumpla con las condiciones
        
        if (movimientoGranel.getFecha().getAnio() != LocalDate.now().getYear() || movimientoGranel.getFecha().getMes() != LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }
        
        ventas.add(movimientoGranel); // y se supone que tambien se elimina a productosGranel (el inventario)
    }
    
    public List<MovimientoGranel> consultarMovimientosPorFecha (LocalDate fecha) {
        List<MovimientoGranel> movimientosPorFecha = new ArrayList<>();
        for (MovimientoGranel movimientoGranel : compras) {
            if (movimientoGranel.getFecha().equals(fecha)) {
                movimientosPorFecha.add(movimientoGranel);
            }
            
        }
        return movimientosPorFecha;
    }
    
    public List<MovimientoGranel> consultarMovimientosPorProducto(String clave) {
        List<MovimientoGranel> movimientosPorProducto = new ArrayList<>();
        for (MovimientoGranel movimientoGranel : compras) {
            if (movimientoGranel.getProductoGranel().getClave().equals(clave)) {
                movimientosPorProducto.add(movimientoGranel);
            }
        }
        return movimientosPorProducto;
    }
    
}
