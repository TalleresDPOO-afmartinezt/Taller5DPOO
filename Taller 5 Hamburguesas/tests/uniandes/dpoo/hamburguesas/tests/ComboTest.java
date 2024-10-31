package uniandes.dpoo.hamburguesas.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


public class ComboTest {

    private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private ArrayList<ProductoMenu> itemsCombo;

    @BeforeEach
    public void setUp() {
        producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas", 5000);

        itemsCombo = new ArrayList<>();
        itemsCombo.add(producto1);
        itemsCombo.add(producto2);

        combo = new Combo("Combo Unico", 0.75, itemsCombo);
    }

  
    @Test
    public void testGetNombre() {
        assertEquals("Combo Unico", combo.getNombre(), "El nombre del combo no es el esperado.");
    }

   
    @Test
    public void testGetPrecio() {
        int precioEsperado = (int) ((10000 + 5000) * 0.75); // Se aplica el descuento
        assertEquals(precioEsperado, combo.getPrecio(), "El precio del combo no es el esperado con el descuento aplicado.");
    }


    @Test
    public void testGenerarTextoFactura() {
        String facturaEsperada = "Combo Combo Unico\n Descuento: 0.75\n            " + combo.getPrecio() + "\n";
        assertEquals(facturaEsperada, combo.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }

 
    @Test
    public void testConstructor() {
        assertEquals("Combo Especial", combo.getNombre(), "El nombre del combo no es el esperado.");
        assertEquals(2, itemsCombo.size(), "El tamaño de la lista de productos en el combo no es el esperado.");
        assertEquals(0.75, combo.getDescuento(), "El descuento del combo no es el esperado.");
    }
}
