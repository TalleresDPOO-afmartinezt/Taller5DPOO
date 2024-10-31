package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {

    private ProductoMenu producto;

    @BeforeEach
    public void setUp() {
        producto = new ProductoMenu("Cangreburger", 25000);
    }

   
    @Test
    public void testGetNombre() {
        assertEquals("Cangreburger", producto.getNombre(), "Este no es el nombre del producto.");
    }

  
    @Test
    public void testGetPrecio() {
        assertEquals(25000, producto.getPrecio(), "Este no es el precio del producto.");
    }

    
    @Test
    public void testGenerarTextoFactura() {
        String expectedFactura = "Cangreburger\n            25000\n";
        assertEquals(expectedFactura, producto.generarTextoFactura(), "La factura no coincide.");
    }

   
    @Test
    public void testConstructor() {
        ProductoMenu productoTest = new ProductoMenu("Papas", 5000);
        assertEquals("Papas", productoTest.getNombre(), "El nombre del producto en el constructor no es el esperado.");
        assertEquals(5000, productoTest.getPrecio(), "El precio del producto en el constructor no es el esperado.");
    }
}
