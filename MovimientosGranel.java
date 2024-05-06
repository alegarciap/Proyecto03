package abarrotesPersistencia;

import excepciones.MovimientoInvalidoException;
import excepciones.ProductoExistenteException;
import excepciones.ProductoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.MovimientoGranel;
import objetosNegocio.Producto;
import objetosNegocio.ProductoGranel;
import objetosServicio.Fecha;

/**
 * Clase que gestiona los movimientos de productos a granel (compras y ventas).
 * Permite agregar, consultar y eliminar movimientos de compras y ventas de
 * productos a granel. También permite consultar los registros de compras y
 * ventas y realizar consultas por período. Además, guarda y carga los
 * movimientos de compras y ventas desde archivos de datos.
 *
 * @author Alejandra García Preciado
 */
public class MovimientosGranel {

    /**
     * Lista que almacena los movimientos de compras de productos a granel.
     */
    public static List<MovimientoGranel> compras;

    /**
     * Lista que almacena los movimientos de ventas de productos a granel.
     */
    public static List<MovimientoGranel> ventas;

    /**
     * Límite mínimo de cantidad de productos a granel en kilogramos para
     * realizar una venta.
     */
    private static final float LIMITE_MIN_KG = 300.0f;

    /**
     * Límite máximo de cantidad de productos a granel en kilogramos para
     * realizar una compra.
     */
    private static final float LIMITE_MAX_KG = 1500.0f;

    /**
     * Límite mínimo de cantidad de productos a granel en litros para realizar
     * una venta.
     */
    private static final float LIMITE_MIN_LT = 900.0f;

    /**
     * Límite máximo de cantidad de productos a granel en litros para realizar
     * una compra.
     */
    private static final float LIMITE_MAX_LT = 3000.0f;

    /**
     * Nombre del archivo que contiene los movimientos de compras de productos a
     * granel.
     */
    private static final String archivoCompras = "comprasGranel.dat";

    /**
     * Nombre del archivo que contiene los movimientos de ventas de productos a
     * granel.
     */
    private static final String archivoVentas = "ventasGranel.dat";

    /**
     * Constructor que inicializa las listas de movimientos de compras y ventas
     * de productos a granel. Carga los movimientos de compras y ventas desde
     * los archivos al iniciar la instancia.
     */
    public MovimientosGranel() {
        this.compras = new ArrayList<>();
        this.ventas = new ArrayList<>();
        // this.compras = cargarMovimientos(archivoCompras);
        // this.ventas = cargarMovimientos(archivoVentas);
    }

    /**
     * Guarda los movimientos de compras o ventas de productos a granel en un
     * archivo de datos.
     *
     * @param movimientos La lista de movimientos a guardar.
     * @param archivo El nombre del archivo en el que se guardarán los
     * movimientos.
     */
//    private void guardarMovimientos(List<MovimientoGranel> movimientos, String archivo) {
//        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
//            salida.writeObject(movimientos);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    /**
     * Carga los movimientos de compras o ventas de productos a granel desde un
     * archivo de datos.
     *
     * @param archivo El nombre del archivo del que se cargarán los movimientos.
     * @return La lista de movimientos cargados.
     */
//    private List<MovimientoGranel> cargarMovimientos(String archivo) {
//        List<MovimientoGranel> movimientos = new ArrayList<>();
//        File archivoMovimientos = new File(archivo);
//        if (archivoMovimientos.exists()) {
//            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
//                movimientos = (List<MovimientoGranel>) entrada.readObject();
//            } catch (IOException | ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return movimientos;
//    }

    /**
     * Agrega un nuevo movimiento de compra de un producto a granel al registro
     * de compras.
     *
     * @param movimientoGranel El movimiento de compra a agregar.
     * @throws MovimientoInvalidoException Si el movimiento de compra es
     * inválido.
     */
    public void agregarComprasGranel(MovimientoGranel movimientoGranel) throws MovimientoInvalidoException { // cambiar a (String clave, fecha, cantidad)
        // verificar que el producto esté en el catálogo. si no se encuentra en el catálogo no se puede hacer el movmiento
        // System.out.println(movimientoGranel.getClaveProducto());
        if (!Productos.existeProductoConClave(movimientoGranel.getClaveProducto())) {
            throw new MovimientoInvalidoException("El producto no se encuentra en el catálogo.");
        }

        // verificar la fecha del registro
        if (movimientoGranel.getFecha().getAnio() != LocalDate.now().getYear() || movimientoGranel.getFecha().getMes() != LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }

        // verificar que no haya otro movimiento de ese producto ese día 
        for (MovimientoGranel movimiento : compras) {
            if (movimiento.getClaveProducto().equals(movimientoGranel.getClaveProducto()) && movimiento.getFecha().equals(movimientoGranel.getFecha())) {
                throw new MovimientoInvalidoException("Ya hay un movimiento de ese producto en la fecha especificada.");
            }
        }

        // agregar el movimiento a la lista de compras
        movimientoGranel.setStatus(false);
        compras.add(movimientoGranel);
        // guardarMovimientos(compras, archivoCompras);

    }

    /**
     * Procesa las compras de productos a granel.
     *
     * @throws MovimientoInvalidoException Si la cantidad total del producto
     * granel excede el límite permitido.
     * @throws ProductoNoEncontradoException Si no se encuentra el producto en
     * la lista de productos.
     * @throws ProductoExistenteException Si el producto ya existe en la lista
     * de productos.
     * @throws ProductoInvalidoException Si el producto es inválido.
     */
    public void procesarComprasGranel() throws MovimientoInvalidoException, ProductoNoEncontradoException, ProductoExistenteException, ProductoInvalidoException {
        for (MovimientoGranel m : compras) {
            String claveProducto = m.getClaveProducto();
            Producto producto = Productos.consultarProducto(claveProducto);
            ProductoGranel productoGranel = verificarExistaProducto(producto);
        }

        for (ProductoGranel p : ProductosGranel.productosGranel) {
            float cantidadTotal = p.getCantidad();
            for (MovimientoGranel movimientoGranel : compras) {
                if (movimientoGranel.getClaveProducto().equals(p.getClave())) {
                    if (movimientoGranel.getStatus() == false) {
                        if ("kg".equalsIgnoreCase(Productos.consultarProducto(p.getClave()).getUnidad())) {
                            if (cantidadTotal > LIMITE_MAX_KG) {
                                throw new MovimientoInvalidoException("La cantidad total del producto granel excede el límite permitido.");
                            }
                        } else if ("l".equalsIgnoreCase(Productos.consultarProducto(p.getClave()).getUnidad())) {
                            if (cantidadTotal > LIMITE_MAX_LT) {
                                throw new MovimientoInvalidoException("La cantidad total del producto granel excede el límite permitido.");
                            }

                        }
                        cantidadTotal += movimientoGranel.getCantidad();
                        movimientoGranel.setStatus(true);

                    }
                }

            }
            p.setCantidad(cantidadTotal);

        }

        // guardarMovimientos(compras, archivoCompras);
    }

    /**
     * Agrega un nuevo movimiento de venta de un producto a granel al registro
     * de ventas.
     *
     * @param movimientoGranel El movimiento de venta a agregar.
     * @throws MovimientoInvalidoException Si el movimiento de venta es
     * inválido.
     */
    public void agregarVentasGranel(MovimientoGranel movimientoGranel) throws MovimientoInvalidoException {
        // verificar que el producto esté en el catálogo
        if (!Productos.existeProductoConClave(movimientoGranel.getClaveProducto())) {
            throw new MovimientoInvalidoException("El producto no se encuentra en el catálogo.");
        }

        // verificar la fecha del registro
        if (movimientoGranel.getFecha().getAnio() != LocalDate.now().getYear() || movimientoGranel.getFecha().getMes() != LocalDate.now().getMonthValue() || movimientoGranel.getFecha().after(LocalDate.now())) {
            throw new MovimientoInvalidoException("La fecha del movimiento debe estar dentro del mes actual y no después de la fecha actual.");
        }
        
        // agregar el movimiento a la lista de ventas
        movimientoGranel.setStatus(false);
        ventas.add(movimientoGranel);
        // guardarMovimientos(compras, archivoVentas);
    }
    
    /**
     * Procesa las ventas de productos a granel.
     *
     * @throws MovimientoInvalidoException Si la cantidad total del producto
     * granel es menor al límite permitido o si se intenta vender más cantidad
     * de la disponible en el inventario.
     * @throws ProductoNoEncontradoException Si no se encuentra el producto en
     * la lista de productos.
     * @throws ProductoExistenteException Si el producto ya existe en la lista
     * de productos.
     * @throws ProductoInvalidoException Si el producto es inválido.
     */
    public void procesarVentasGranel() throws MovimientoInvalidoException, ProductoNoEncontradoException, ProductoExistenteException, ProductoInvalidoException {

        for (ProductoGranel p : ProductosGranel.productosGranel) {
            float cantidadTotal = p.getCantidad();
            for (MovimientoGranel movimientoGranel : ventas) {
                if (movimientoGranel.getClaveProducto().equals(p.getClave())) {
                    cantidadTotal -= movimientoGranel.getCantidad();
                    if ("kg".equalsIgnoreCase(Productos.consultarProducto(p.getClave()).getUnidad())) {
                        if (cantidadTotal < LIMITE_MIN_KG) {
                            throw new MovimientoInvalidoException("La cantidad total del producto granel no puede ser menor al límite permitido.");
                        }
                    } else if ("l".equalsIgnoreCase(Productos.consultarProducto(p.getClave()).getUnidad())) {
                        if (cantidadTotal < LIMITE_MIN_LT) {
                            throw new MovimientoInvalidoException("La cantidad total del producto granel no puede ser menor al límite permitido.");
                        }
                    }
                    // verificar que la cantidad a vender no sea mayor a la cantidad disponible
                    if (cantidadTotal < 0) {
                        throw new MovimientoInvalidoException("No es posible vender una cantidad mayor a la disponible en el inventario.");
                    }
                    if (cantidadTotal == 0) {
                        ProductosGranel.productosGranel.remove(p); // nunca llegará a está línea porque siempre estará primero la excepción de MovimientoInvalidoException
                    } else {
                        p.setCantidad(cantidadTotal);
                        movimientoGranel.setStatus(true);
                    }
                }
            }
        }
    }
    
    /**
     * Verifica si un producto granel ya existe en la lista de productos a
     * granel. Si el producto no existe, lo agrega a la lista de productos a
     * granel.
     *
     * @param producto El producto a verificar.
     * @return El producto granel si ya existe en la lista, de lo contrario,
     * agrega el producto a la lista y lo devuelve.
     * @throws ProductoExistenteException Si el producto granel ya existe en la
     * lista de productos a granel.
     * @throws ProductoInvalidoException Si el producto es inválido.
     */
    public ProductoGranel verificarExistaProducto(Producto producto) throws ProductoExistenteException, ProductoInvalidoException {
        for (ProductoGranel p : ProductosGranel.productosGranel) {
            if (p.getClave().equalsIgnoreCase(producto.getClave())) {
                return p;
            }
        }
        ProductoGranel productoGranel = new ProductoGranel(producto.getClave(), producto.getNombre(), producto.getTipo(), producto.getUnidad());
        ProductosGranel.agregarProductoGranel(productoGranel);
        return productoGranel;
    }
    
    /**
     * Consulta el registro de compras de productos a granel.
     *
     * @return La lista de movimientos de compras de productos a granel.
     */
    public List<MovimientoGranel> consultarRegistroCompras() {
        return compras;
    }

    /**
     * Consulta el registro de ventas de productos a granel.
     *
     * @return La lista de movimientos de ventas de productos a granel.
     */
    public List<MovimientoGranel> consultarRegistroVentas() {
        return ventas;
    }

    /**
     * Consulta los movimientos de compras de productos a granel realizados
     * dentro de un período específico.
     *
     * @param fechaInicio La fecha de inicio del período.
     * @param fechaFin La fecha de fin del período.
     * @return La lista de movimientos de compras de productos a granel dentro
     * del período especificado.
     */
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

    /**
     * Consulta los movimientos de ventas de productos a granel realizados
     * dentro de un período específico.
     *
     * @param fechaInicio La fecha de inicio del período.
     * @param fechaFin La fecha de fin del período.
     * @return La lista de movimientos de ventas de productos a granel dentro
     * del período especificado.
     */
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
