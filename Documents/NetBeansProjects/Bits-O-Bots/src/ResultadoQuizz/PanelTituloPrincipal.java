/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResultadoQuizz;

/**
 *
 * @author zoeca
 */
import java.awt.*;
import javax.swing.JPanel;

public class PanelTituloPrincipal extends JPanel {

    public PanelTituloPrincipal() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();

        // Glow exterior
        g2.setColor(new Color(25, 118, 210, 18));
        g2.fillRoundRect(
            4, 4,
            w - 8, h - 8,
            35, 35
        );

        // Fondo MUY suave
        GradientPaint fondo = new GradientPaint(
            0, 0,
            new Color(255, 255, 255, 210),
            w, h,
            new Color(225, 239, 250, 170)
        );

        g2.setPaint(fondo);
        g2.fillRoundRect(
            10, 10,
            w - 20, h - 24,
            30, 30
        );

        // Línea azul inferior
        GradientPaint linea = new GradientPaint(
            40, 0,
            new Color(255, 255, 255, 0),
            w / 2, 0,
            new Color(30, 125, 220)
        );

        g2.setPaint(linea);

        g2.fillRoundRect(
            50,
            h - 13,
            w / 2 - 50,
            4,
            4,
            4
        );

        GradientPaint linea2 = new GradientPaint(
            w / 2, 0,
            new Color(30, 125, 220),
            w - 40, 0,
            new Color(255, 255, 255, 0)
        );

        g2.setPaint(linea2);

        g2.fillRoundRect(
            w / 2,
            h - 13,
            w / 2 - 50,
            4,
            4,
            4
        );

        // Puntito central brillante
        g2.setColor(new Color(65, 165, 245));
        g2.fillOval(
            w / 2 - 4,
            h - 15,
            8,
            8
        );

        g2.dispose();

        super.paintComponent(g);
    }
}