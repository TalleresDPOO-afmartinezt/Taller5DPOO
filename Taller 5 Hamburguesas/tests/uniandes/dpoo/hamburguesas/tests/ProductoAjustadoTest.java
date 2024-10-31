package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoAjustadoTest {

    private ProductoMenu productoBase;
    private ProductoAjustado productoAjustado;
    private Ingrediente ingredienteExtra;
    private Ingrediente ingredienteEliminado;

    @BeforeEach
    public void setUp() {
        productoBase = new ProductoMenu("Burgir", 10000);
        productoAjustado = new ProductoAjustado(productoBase);
        
        ingredienteExtra = new Ingrediente("Queso", 2000);
        ingredienteEliminado = new Ingrediente("Cebolla", 0);
    }


    @Test
    public void testGetNombre() {
        assertEquals("Burgir", productoAjustado.getNombre(), "El nombre del producto ajustado no es el esperado.");
    }

    
    @Test
    public void testGetPrecio() {
        // Sin ingredientes adicionales o eliminados
        assertEquals(10000, productoAjustado.getPrecio(), "El precio sin agregados ni eliminados no es el esperado.");

        // Con un ingrediente adicional
        productoAjustado.getAgregados().add(ingredienteExtra);
        assertEquals(12000, productoAjustado.getPrecio(), "El precio con un ingrediente adicional no es el esperado.");
    }

    @Test
    public void testGenerarTextoFactura() {
        // Sin modificaciones
        String facturaEsperada = "Burgir\n            10000\n";
        assertEquals(facturaEsperada, productoAjustado.generarTextoFactura(), "El texto de factura normal no es el esperado.");

        // Con un ingrediente adicional y uno eliminado
        productoAjustado.getAgregados().add(ingredienteExtra);
        productoAjustado.getEliminados().add(ingredienteEliminado);
        facturaEsperada = "Burgir\n    +Queso                2000\n    -Cebolla\n            12000\n";
        assertEquals(facturaEsperada, productoAjustado.generarTextoFactura(), "El texto de factura modificado no es el esperado.");
    }

   
    @Test
    public void testConstructor() {
        assertEquals("Burgir", productoAjustado.getNombre(), "El nombre del producto base no es el esperado.");
        assertEquals(10000, productoAjustado.getPrecio(), "El precio base del producto no es el esperado.");
        assertTrue(productoAjustado.getAgregados().isEmpty(), "La lista de agregados debería estar vacía.");
        assertTrue(productoAjustado.getEliminados().isEmpty(), "La lista de eliminados debería estar vacía.");
    }
}
