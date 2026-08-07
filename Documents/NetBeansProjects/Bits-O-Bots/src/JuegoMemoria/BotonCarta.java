package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotonCarta extends JButton {

    private final int radio;

    private Color colorFondo
            = new Color(250, 250, 253);

    private Color colorBorde
            = new Color(75, 76, 100);

    public BotonCarta(int radio) {

        this.radio = radio;

        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setIconTextGap(0);

        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
        repaint();
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = Math.max(1, getWidth() - 7);
        int alto = Math.max(1, getHeight() - 9);

        Shape sombra = new RoundRectangle2D.Double(
                5,
                7,
                ancho,
                alto,
                radio,
                radio
        );

        g2.setColor(new Color(0, 0, 0, 55));
        g2.fill(sombra);

        Shape forma = new RoundRectangle2D.Double(
                2,
                2,
                ancho,
                alto,
                radio,
                radio
        );

        Color fondo = colorFondo;

        if (getModel().isRollover() && isEnabled()) {
            fondo = new Color(238, 241, 255);
        }

        g2.setColor(fondo);
        g2.fill(forma);

        Shape clipAnterior = g2.getClip();
        g2.clip(forma);
        super.paintComponent(g2);
        g2.setClip(clipAnterior);

        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(3f));
        g2.draw(forma);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // El borde se dibuja manualmente.
    }

    @Override
    public boolean contains(int x, int y) {

        Shape forma = new RoundRectangle2D.Double(
                2,
                2,
                Math.max(1, getWidth() - 7),
                Math.max(1, getHeight() - 9),
                radio,
                radio
        );

        return forma.contains(x, y);
    }
}
