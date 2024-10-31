package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Andy Phillip", "Direccion 1");
        assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso debería existir después de iniciarlo.");
    }

    @Test
    public void testIniciarPedidoException() {
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Andy Phillip", "Direccion 1");
            restaurante.iniciarPedido("Andres Felipe", "Direccion 2");
        }, "Debería lanzar una Exception si ya hay un pedido en curso.");
    }

    @Test
    public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, IOException, NoHayPedidoEnCursoException {
        restaurante.iniciarPedido("Andy Phillip", "Direccion 1");
        restaurante.cerrarYGuardarPedido();
        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debería ser nulo después de cerrarlo.");
        assertTrue(restaurante.getPedidos().size() > 0, "El pedido cerrado debería guardarse en la lista de pedidos.");
    }

    @Test
    public void testCerrarYGuardarPedidoException() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Debería lanzar una Exception si no hay un pedido en curso.");
    }

    @Test
    public void testGetMenuBase() {
        ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
        assertNotNull(menuBase, "El menú base no debería ser nulo.");
        assertEquals(0, menuBase.size(), "El menú base debería estar vacío al inicio.");
    }

    @Test
    public void testGetMenuCombos() {
        ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
        assertNotNull(menuCombos, "El menú de combos no debería ser nulo.");
        assertEquals(0, menuCombos.size(), "El menú de combos debería estar vacío al inicio.");
    }

    @Test
    public void testGetIngredientes() {
        ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
        assertNotNull(ingredientes, "La lista de ingredientes no debería ser nula.");
        assertEquals(0, ingredientes.size(), "La lista de ingredientes debería estar vacía al inicio.");
    }

    @Test
    public void testCargarInformacionRestaurante() throws IOException, HamburguesaException {
        File archivoIngredientes = new File("ingredientes.txt");
        File archivoMenu = new File("menu.txt");
        File archivoCombos = new File("combos.txt");

        // Suponiendo que los archivos contienen datos válidos.
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

        assertTrue(restaurante.getIngredientes().size() > 0, "La lista de ingredientes debería contener elementos después de cargarlos.");
        assertTrue(restaurante.getMenuBase().size() > 0, "El menú base debería contener elementos después de cargarlos.");
        assertTrue(restaurante.getMenuCombos().size() > 0, "El menú de combos debería contener elementos después de cargarlos.");
    }

  
}
