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

public class PanelTarjetaResultado extends JPanel {

    private final int radio = 35;

    public PanelTarjetaResultado() {
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

        // SOMBRA SUAVE
        g2.setColor(new Color(0, 25, 43, 35));
        g2.fillRoundRect(
                8,
                10,
                w - 16,
                h - 18,
                radio,
                radio
        );

        // FONDO
        GradientPaint fondo = new GradientPaint(
                0, 0,
                new Color(245, 249, 252),
                0, h,
                new Color(226, 237, 245)
        );

        g2.setPaint(fondo);
        g2.fillRoundRect(
                3,
                3,
                w - 14,
                h - 16,
                radio,
                radio
        );

        // BORDE AZUL CLARO
        g2.setStroke(new BasicStroke(1.8f));
        g2.setColor(new Color(30, 110, 180, 100));
        g2.drawRoundRect(
                3,
                3,
                w - 14,
                h - 16,
                radio,
                radio
        );

        // BRILLO SUPERIOR
        g2.setColor(new Color(255, 255, 255, 150));
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawRoundRect(
                6,
                6,
                w - 20,
                h - 22,
                radio - 5,
                radio - 5
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
