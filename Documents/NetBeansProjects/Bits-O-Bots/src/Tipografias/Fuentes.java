/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tipografias;
import java.awt.Font;
import java.io.InputStream;
/**
 *
 * @author Usuario
 */
public class Fuentes {
      private Fuentes() {
        // Evita crear objetos innecesarios.
    }

    public static Font cargar(
            String nombreArchivo,
            float tamaño
    ) {

        String ruta = "/Tipografias/"
                + nombreArchivo;

        try (
                InputStream archivoFuente
                = Fuentes.class
                        .getResourceAsStream(ruta)
        ) {

            if (archivoFuente == null) {

                System.out.println(
                        "No se encontró la tipografía: "
                        + ruta
                );

                return new Font(
                        "Arial",
                        Font.PLAIN,
                        Math.round(tamaño)
                );
            }

            /*
             * TRUETYPE_FONT funciona tanto con
             * archivos .ttf como con muchos .otf.
             */
            Font fuenteOriginal = Font.createFont(
                    Font.TRUETYPE_FONT,
                    archivoFuente
            );

            return fuenteOriginal.deriveFont(tamaño);

        } catch (Exception ex) {

            System.out.println(
                    "Error cargando la tipografía "
                    + nombreArchivo
                    + ": "
                    + ex.getMessage()
            );

            return new Font(
                    "Arial",
                    Font.PLAIN,
                    Math.round(tamaño)
            );
        }
    }
}
