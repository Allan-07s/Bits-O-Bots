package JuegoMemoria;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PruebaJuegoCartas {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ex) {
            System.out.println(
                    "No se pudo aplicar el estilo del sistema."
            );
        }

        SwingUtilities.invokeLater(() -> {

            /*
             * CLASE OPCIONAL PARA PROBAR SOLO EL JUEGO DE CARTAS.
             *
             * Cuando lo conectes con el menú principal del proyecto,
             * sustituye "Jugador de prueba" por el nombre que ya tengas
             * registrado.
             */
            new MenuJuego("Jugador de prueba").setVisible(true);
        });
    }
}
