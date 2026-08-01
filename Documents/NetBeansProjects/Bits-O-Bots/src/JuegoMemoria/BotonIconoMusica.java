package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JButton;

public class BotonIconoMusica extends JButton {

    private boolean encima;

    public BotonIconoMusica() {

        setToolTipText("Activar o desactivar música");
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                encima = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                encima = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int lado = Math.min(getWidth(), getHeight()) - 5;
        int x = (getWidth() - lado) / 2;
        int y = (getHeight() - lado) / 2;

        Color fondo = encima
                ? new Color(255, 255, 255, 72)
                : new Color(255, 255, 255, 42);

        g2.setColor(fondo);
        g2.fill(new Ellipse2D.Double(x, y, lado, lado));

        g2.setColor(new Color(255, 255, 255, 190));
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(new Ellipse2D.Double(x, y, lado, lado));

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        /* Caja del altavoz. */
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(cx - 15, cy - 7, 9, 14, 3, 3);

        Polygon cono = new Polygon();
        cono.addPoint(cx - 6, cy - 7);
        cono.addPoint(cx + 2, cy - 14);
        cono.addPoint(cx + 2, cy + 14);
        cono.addPoint(cx - 6, cy + 7);
        g2.fillPolygon(cono);

        g2.setStroke(
                new BasicStroke(
                        2.4f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        if (GestorMusica.estaSilenciado()) {

            g2.setColor(new Color(255, 110, 125));
            g2.drawLine(cx + 7, cy - 9, cx + 19, cy + 9);
            g2.drawLine(cx + 19, cy - 9, cx + 7, cy + 9);

        } else {

            g2.setColor(Color.WHITE);

            g2.draw(
                    new QuadCurve2D.Double(
                            cx + 5,
                            cy - 8,
                            cx + 12,
                            cy,
                            cx + 5,
                            cy + 8
                    )
            );

            g2.draw(
                    new QuadCurve2D.Double(
                            cx + 9,
                            cy - 13,
                            cx + 20,
                            cy,
                            cx + 9,
                            cy + 13
                    )
            );
        }

        g2.dispose();
    }
}
