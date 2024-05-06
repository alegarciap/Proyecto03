package pruebas;

import abarrotesPersistencia.MovimientosGranel;
import abarrotesPersistencia.Productos;
import abarrotesPersistencia.ProductosGranel;
import excepciones.MovimientoInvalidoException;
import excepciones.ProductoExistenteException;
import excepciones.ProductoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import objetosNegocio.MovimientoGranel;
import objetosNegocio.Producto;
import objetosNegocio.ProductoGranel;
import objetosServicio.Fecha;

/**
 *
 * @author Alejandra Garcia Preciado
 */
public class PruebasProyecto3 {

    public static void main(String[] args) throws ProductoInvalidoException, ProductoExistenteException, ProductoNoEncontradoException, MovimientoInvalidoException {
        
        try {
        // 11. Crear un objeto de cada una de las clases persistencia
        Productos productos = new Productos();
        ProductosGranel productosGranel = new ProductosGranel();
        MovimientosGranel movimientosGranel = new MovimientosGranel();

        // 12. Agregar seis productos al catálogo de productos 
        Producto producto1 = new Producto("AT001", "Atún en Aceite", "E", "Kg");
        productos.agregarProducto(producto1);
        
        Producto producto2 = new Producto("EL002", "Elotitos Amarillos", "E", "Kg");
        productos.agregarProducto(producto2);
        
        Producto producto3 = new Producto("CH001", "Chiles Jalapeños", "E", "Kg");
        productos.agregarProducto(producto3);
        
        Producto producto4 = new Producto("FA001", "Frijol Azufrado", "G", "Kg");
        productos.agregarProducto(producto4);
        
        Producto producto5 = new Producto("AR013", "Arroz Grano Largo", "G", "Kg");
        productos.agregarProducto(producto5);
        
        Producto producto6 = new Producto("AZ002", "Azúcar Refinada", "G", "Kg");
        productos.agregarProducto(producto6);
        
        // 13. Agregar de nuevo el producto1
        // Al ejecutar el programa hasta esta sentencia aparece la siguiente excepción: El producto ya existe.
        // productos.agregarProducto(producto1);
        
        // 14. Agrega un nuevo producto con datos de tu elección y tipo: M
        // Al ejecutar el programa hasta esta sentencia aparece la siguiente excepción: El tipo del producto debe ser E o G.
        // Producto producto7 = new Producto("AM045", "Azúcar Morena", "M", "Kg");
        // productos.agregarProducto(producto7);
        
        // 15. Agrega un nuevo producto con datos de tu elección y Unidad: Mol.
        // Al ejecutar el programa hasta esta sentencia aparece la siguiente excepción: La unidad del producto debe ser kg o l
        // Producto producto8 = new Producto("AI230", "Avena Integral", "E", "Mol");
        // productos.agregarProducto(producto8);
        
        // 16. Muestra el contenido del catálogo de productos.
        System.out.println("Catálogo de productos:");
        for (Producto producto : productos.consultarProductos()) {
            System.out.println(producto);
        }
        System.out.println();

        // 17. Modifica el nombre del producto de clave "AR013" a "Arroz Grano Corto".
        Producto productoActualizado = new Producto("AR013", "Arroz Grano Corto", "G", "Kg");
        productos.actualizarProducto(productoActualizado);

        System.out.println("Catálogo de productos:");
        for (Producto producto : productos.consultarProductos()) {
            System.out.println(producto);
        }
        System.out.println();

        // 18. Elimina producto de clave "EL002" del catálogo de productos.
        productos.eliminarProducto("EL002");
        
        // 19. Consulta el contenido del catálogo de productos
        System.out.println("Catálogo de productos:");
        for (Producto producto : productos.consultarProductos()) {
            System.out.println(producto);
        }
        System.out.println();
        
        // 20. Consulta el contenido del catálogo de productos a granel.
        System.out.println("Catálogo de productos a granel:");
        for (ProductoGranel productoGranel : productosGranel.consultarProductosGranel()) {
            System.out.println(productoGranel);
        }
        System.out.println();
        
        // 21. Agrégale al registro de compras los movimientos compra1, compra2 y compra3 
        MovimientoGranel compra1 = new MovimientoGranel(); 
        compra1.setClaveMov("C0001");
        Fecha fechaCompra1 = new Fecha(03, 05, 2024);
        compra1.setFecha(fechaCompra1);
        compra1.setTipoMov(0);
        compra1.setCantidad(15.0f);
        compra1.setClaveProducto(producto4.getClave());
        movimientosGranel.agregarComprasGranel(compra1);
        
        MovimientoGranel compra2 = new MovimientoGranel(); 
        compra2.setClaveMov("C0002");
        Fecha fechaCompra2 = new Fecha(04, 05, 2024);
        compra2.setFecha(fechaCompra2);
        compra2.setTipoMov(0);
        compra2.setCantidad(12.5f);
        compra2.setClaveProducto(producto5.getClave());
        movimientosGranel.agregarComprasGranel(compra2);
        
        MovimientoGranel compra3 = new MovimientoGranel(); 
        compra3.setClaveMov("C0003");
        Fecha fechaCompra3 = new Fecha(04, 05, 2024);
        compra3.setFecha(fechaCompra3);
        compra3.setTipoMov(0);
        compra3.setCantidad(18.5f);
        compra3.setClaveProducto(producto6.getClave());
        movimientosGranel.agregarComprasGranel(compra3);
        
        // 22. Muestra los movimientos del registro de compras
        System.out.println("Movimientos de compra:");
        for (MovimientoGranel movimiento : movimientosGranel.consultarRegistroCompras()) {
            System.out.println(movimiento);
        }
        System.out.println();
        
        // 23. Elimina el movimiento compra2 del registro de compras
        // MovimientosGranel.compras.remove(compra2);
        
        // 24. Muestra el contenido del registro de compras
        System.out.println("Movimientos de compra:");
        for (MovimientoGranel movimiento : movimientosGranel.consultarRegistroCompras()) {
            System.out.println(movimiento);
        }
        System.out.println();
        
        // 25. Muestra el contenido del registro de compras del día de ayer
        System.out.println("Movimientos de compra:");
        for (MovimientoGranel movimiento : movimientosGranel.consultarComprasPorPeriodo(new Fecha(02, 05, 2024), new Fecha(04, 05, 2024))) {
            System.out.println(movimiento);
        }
        System.out.println();
        
        // 26. Muestra el inventario de productos a granel
        System.out.println("Catálogo de productos a granel:");
        for (ProductoGranel productoGranel : productosGranel.consultarProductosGranel()) {
            System.out.println(productoGranel);
        }
        System.out.println();
        
        // 27. Actualiza los inventarios de productos a granel con las compras
        movimientosGranel.procesarComprasGranel();
        
        // 28. Muestra el inventario de productos a granel
        System.out.println("Catálogo de productos a granel:");
        for (ProductoGranel productoGranel : productosGranel.consultarProductosGranel()) {
            System.out.println(productoGranel);
        }
        System.out.println();
        
        // 29. Agrégale al registro de ventas los movimientos venta1 y venta2
        MovimientoGranel venta1 = new MovimientoGranel(); 
        venta1.setClaveMov("V0001");
        Fecha fechaVenta1 = new Fecha(05, 05, 2024);
        venta1.setFecha(fechaVenta1);
        venta1.setTipoMov(1);
        venta1.setCantidad(15.0f);
        venta1.setClaveProducto(producto4.getClave());
        movimientosGranel.agregarVentasGranel(venta1);
        
        MovimientoGranel venta2 = new MovimientoGranel(); 
        venta2.setClaveMov("V0002");
        Fecha fechaVenta2 = new Fecha(05, 05, 2024);
        venta2.setFecha(fechaVenta2);
        venta2.setTipoMov(1);
        venta2.setCantidad(12.5f);
        venta2.setClaveProducto(producto6.getClave());
        movimientosGranel.agregarVentasGranel(venta2);
        
        // 30. Lista el contenido del registro de ventas
        System.out.println("Movimientos de venta:");
        for (MovimientoGranel movimiento : movimientosGranel.consultarRegistroVentas()) {
            System.out.println(movimiento);
        }
        System.out.println();
        
        // 31. Actualiza los inventarios de productos a granel con las ventas
        movimientosGranel.procesarVentasGranel();
        
        // 32. Muestra el inventario de productos a granel
        // Al ejecutar el programa hasta esta sentencia aparece la siguiente excepción: La cantidad total del producto granel no puede ser menor al límite permitido.
        System.out.println("Catálogo de productos a granel:");
        for (ProductoGranel productoGranel : productosGranel.consultarProductosGranel()) {
            System.out.println(productoGranel);
        }
        System.out.println();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
