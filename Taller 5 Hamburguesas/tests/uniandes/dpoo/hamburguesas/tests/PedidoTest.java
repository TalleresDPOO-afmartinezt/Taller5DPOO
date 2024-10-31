package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class PedidoTest {

    private Pedido pedido;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido("Andy Phillip", "Direccion 1");
        producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
    }

    /**
     * Prueba de IDs diferentes
     */
    @Test
    public void testGetIdPedido() {
        Pedido pedido2 = new Pedido("Andres Felipe", "Direccion 2");
        assertNotEquals(pedido.getIdPedido(), pedido2.getIdPedido(), "El ID del pedido no es único.");
    }

  
    @Test
    public void testGetNombreCliente() {
        assertEquals("Andy Phillip", pedido.getNombreCliente(), "El nombre del cliente no es correcto.");
    }

 
    @Test
    public void testAgregarProducto() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        assertEquals(2, pedido.getPrecioNetoPedido() / 7500, "Los productos no fueron agregados correctamente.");
    }

   
    @Test
    public void testGetPrecioTotalPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        int precioNeto = 15000;
        int precioIVA = (int) (precioNeto * 0.19);
        int precioTotalEsperado = precioNeto + precioIVA;

        assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido no es el esperado.");
    }

    @Test
    public void testGenerarTextoFactura() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String facturaEsperada = "Cliente: Andy Phillip\n"
                + "Dirección: Direccion 1\n"
                + "----------------\n"
                + producto1.generarTextoFactura()
                + producto2.generarTextoFactura()
                + "----------------\n"
                + "Precio Neto:  15000\n"
                + "IVA:          " + (int) (15000 * 0.19) + "\n"
                + "Precio Total: " + pedido.getPrecioTotalPedido() + "\n";

        assertEquals(facturaEsperada, pedido.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }


    @Test
    public void testGuardarFactura() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        File archivo = new File("factura_test.txt");
        try {
            pedido.guardarFactura(archivo);
            assertTrue(archivo.exists(), "El archivo de la factura no se generó.");
        } catch (FileNotFoundException e) {
            fail("Se produjo una excepción de archivo no encontrado al guardar la factura.");
        } finally {
            archivo.delete();
        }
    }

  
    @Test
    public void testGetPrecioNetoPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        int precioNetoEsperado = 15000;
        assertEquals(precioNetoEsperado, pedido.getPrecioNetoPedido(), "El precio neto del pedido no es el esperado.");
    }

    
    @Test
    public void testGetPrecioIVAPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        int precioIVAEsperado = (int) (15000 * 0.19);
        assertEquals(precioIVAEsperado, pedido.getPrecioIVAPedido(), "El precio del IVA no es el esperado.");
    }
}
