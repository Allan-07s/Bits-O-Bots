package JuegoMemoria;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelDegradado extends JPanel {

    private final Color colorInicial;
    private final Color colorFinal;

    public PanelDegradado(
            Color colorInicial,
            Color colorFinal
    ) {
        this.colorInicial = colorInicial;
        this.colorFinal = colorFinal;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        GradientPaint degradado = new GradientPaint(
                0,
                0,
                colorInicial,
                getWidth(),
                getHeight(),
                colorFinal
        );

        g2.setPaint(degradado);
        g2.fillRect(0, 0, getWidth(), getHeight());

        /* Decoración suave del fondo. */
        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        0.10f
                )
        );

        g2.setColor(Color.WHITE);
        g2.fillOval(-90, -70, 300, 300);
        g2.fillOval(
                getWidth() - 250,
                80,
                330,
                330
        );

        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        0.08f
                )
        );

        for (int y = 28; y < getHeight(); y += 42) {
            for (int x = 28; x < getWidth(); x += 42) {
                g2.fillOval(x, y, 3, 3);
            }
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
