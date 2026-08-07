/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public final class Estilos {

    private Estilos() {
        // Evita crear objetos de esta clase.
    }

    public static void imagen150x150(
            JLabel label,
            String ruta
    ) {

        if (label == null || ruta == null || ruta.isBlank()) {
            System.out.println("Label o ruta de imagen inválida.");
            return;
        }

        URL recurso = Estilos.class.getResource(ruta);

        if (recurso == null) {
            System.out.println(
                    "NO SE ENCONTRÓ LA IMAGEN: " + ruta
            );

            label.setIcon(null);
            label.setText("Imagen no encontrada");
            return;
        }

        ImageIcon iconoOriginal = new ImageIcon(recurso);

        int anchoOriginal = iconoOriginal.getIconWidth();
        int altoOriginal = iconoOriginal.getIconHeight();

        if (anchoOriginal <= 0 || altoOriginal <= 0) {
            System.out.println(
                    "La imagen no tiene dimensiones válidas: " + ruta
            );
            return;
        }

        /*
         * Se obtiene el tamaño real del JLabel.
         * Si todavía no fue dibujado, se usa su tamaño preferido.
         */
        int anchoLabel = label.getWidth();
        int altoLabel = label.getHeight();

        if (anchoLabel <= 0) {
            anchoLabel = label.getPreferredSize().width;
        }

        if (altoLabel <= 0) {
            altoLabel = label.getPreferredSize().height;
        }

        /*
         * Dejamos un margen interior para que la imagen
         * no quede pegada a los bordes de la tarjeta.
         */
        int margenHorizontal = 0;
        int margenVertical = 0;

        int anchoDisponible =
                Math.max(1, anchoLabel - margenHorizontal * 2);

        int altoDisponible =
                Math.max(1, altoLabel - margenVertical * 2);

        /*
         * Math.min conserva la proporción completa.
         * La imagen nunca se corta ni se deforma.
         */
        double escala = Math.min(
                (double) anchoDisponible / anchoOriginal,
                (double) altoDisponible / altoOriginal
        );

        /*
         * Evita aumentar exageradamente imágenes pequeñas.
         * Puedes borrar esta condición si quieres que siempre
         * ocupen el máximo espacio posible.
         */
        

        int nuevoAncho = Math.max(
                1,
                (int) Math.round(anchoOriginal * escala)
        );

        int nuevoAlto = Math.max(
                1,
                (int) Math.round(altoOriginal * escala)
        );

        Image imagenEscalada =
                iconoOriginal.getImage().getScaledInstance(
                        nuevoAncho,
                        nuevoAlto,
                        Image.SCALE_SMOOTH
                );

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);

        label.setIcon(new ImageIcon(imagenEscalada));
        label.setText("");
        label.setOpaque(false);
        label.setBorder(null);

        label.revalidate();
        label.repaint();
    }
}