package abarrotesPersistencia;

import excepciones.MovimientoInvalidoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.MovimientoGranel;

/**
 *
 * @author alega
 */
public class MovimientosGranel {
    
    private List<MovimientoGranel> compras;
    private List<MovimientoGranel> ventas;
    
    public MovimientosGranel() {
        this.compras = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }
    
    public void agregarComprasGranel(MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        // Aquí falta la parte de verificar que el movimiento cumpla con las condiciones
        
        compras.add(movimientoGranel); // y se supone que tambien se agrega a productosGranel (el inventario)
    }
    
    public void agregarVentaGranel (MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        // Aquí falta la parte de verificar que el movimiento cumpla con las condiciones
        
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
