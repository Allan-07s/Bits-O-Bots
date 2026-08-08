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

public class PanelTituloResultado extends JPanel {

    public PanelTituloResultado() {
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

        // SOMBRA
        g2.setColor(new Color(0, 20, 40, 55));
        g2.fillRoundRect(
                6,
                7,
                w - 12,
                h - 9,
                28,
                28
        );

        // DEGRADADO AZUL OSCURO
        GradientPaint degradado = new GradientPaint(
                0,
                0,
                new Color(0, 66, 126),
                w,
                h,
                new Color(0, 25, 43)
        );

        g2.setPaint(degradado);

        g2.fillRoundRect(
                1,
                1,
                w - 10,
                h - 10,
                28,
                28
        );

        // BORDE AZUL
        g2.setColor(new Color(55, 155, 235, 170));
        g2.setStroke(new BasicStroke(2f));

        g2.drawRoundRect(
                1,
                1,
                w - 10,
                h - 10,
                28,
                28
        );

        // BRILLO EN LA PARTE SUPERIOR
        g2.setColor(new Color(130, 205, 255, 80));
        g2.setStroke(new BasicStroke(2f));

        g2.drawLine(
                25,
                4,
                w - 35,
                4
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
