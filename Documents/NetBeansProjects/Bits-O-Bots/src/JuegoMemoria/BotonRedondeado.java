package JuegoMemoria;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class BotonRedondeado extends JButton {

    private final int radio;
    private final Color colorNormal;
    private final Color colorHover;

    private boolean encima;

    public BotonRedondeado(
            String texto,
            Color colorNormal,
            Color colorHover
    ) {

        super(texto);

        this.radio = 30;
        this.colorNormal = colorNormal;
        this.colorHover = colorHover;

        setForeground(Color.WHITE);
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

        /* Sombra inferior. */
        g2.setColor(new Color(0, 0, 0, 55));
        g2.fill(
                new RoundRectangle2D.Double(
                        3,
                        6,
                        Math.max(1, getWidth() - 6),
                        Math.max(1, getHeight() - 9),
                        radio,
                        radio
                )
        );

        Color fondo;

        if (!isEnabled()) {
            fondo = new Color(110, 112, 128);
        } else if (encima) {
            fondo = colorHover;
        } else {
            fondo = colorNormal;
        }

        g2.setColor(fondo);
        g2.fill(
                new RoundRectangle2D.Double(
                        2,
                        2,
                        Math.max(1, getWidth() - 5),
                        Math.max(1, getHeight() - 8),
                        radio,
                        radio
                )
        );

        g2.dispose();
        super.paintComponent(g);
    }
}
