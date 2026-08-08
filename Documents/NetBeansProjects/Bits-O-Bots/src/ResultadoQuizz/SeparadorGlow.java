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

public class SeparadorGlow extends JPanel {

    public SeparadorGlow() {
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

        int centro = w / 2;

        // RESPLANDOR EXTERIOR
        g2.setColor(
                new Color(50, 145, 225, 35)
        );

        g2.fillRoundRect(
                Math.max(0, centro - 3),
                5,
                6,
                h - 10,
                6,
                6
        );

        // RESPLANDOR MEDIO
        g2.setColor(
                new Color(30, 125, 210, 80)
        );

        g2.fillRoundRect(
                Math.max(0, centro - 2),
                8,
                4,
                h - 16,
                4,
                4
        );

        // LÍNEA CENTRAL
        GradientPaint linea = new GradientPaint(
                centro,
                0,
                new Color(80, 180, 255),
                centro,
                h,
                new Color(0, 55, 93)
        );

        g2.setPaint(linea);

        g2.fillRoundRect(
                Math.max(0, centro - 1),
                10,
                3,
                h - 20,
                4,
                4
        );

        // PUNTO SUPERIOR
        g2.setColor(
                new Color(80, 180, 255)
        );

        g2.fillOval(
                Math.max(0, centro - 3),
                3,
                6,
                6
        );

        // PUNTO INFERIOR
        g2.fillOval(
                Math.max(0, centro - 3),
                h - 9,
                6,
                6
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
