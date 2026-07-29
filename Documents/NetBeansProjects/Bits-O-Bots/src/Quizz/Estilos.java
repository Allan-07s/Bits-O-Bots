/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
/**
 *
 * @author allan
 */
public class Estilos {

    public static void imagen150x150(JLabel label, String ruta) {

        java.net.URL recurso = Estilos.class.getResource(ruta);

        if (recurso == null) {
            System.out.println("NO SE ENCONTRÓ: " + ruta);
            return;
        }

        ImageIcon icon = new ImageIcon(recurso);

        int anchoOriginal = icon.getIconWidth();
        int altoOriginal = icon.getIconHeight();

        double escala = Math.min(
            300.0 / anchoOriginal,
            300.0 / altoOriginal
        );

        int nuevoAncho = (int) (anchoOriginal * escala);
        int nuevoAlto = (int) (altoOriginal * escala);

        Image imagenEscalada = icon.getImage().getScaledInstance(
            nuevoAncho,
            nuevoAlto,
            Image.SCALE_SMOOTH
        );

        label.setIcon(new ImageIcon(imagenEscalada));
        label.setText("");
    }
}
