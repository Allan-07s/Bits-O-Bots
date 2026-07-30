import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class LogoMemoria extends JPanel {

    public LogoMemoria() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        dibujarCarta(
                g2,
                centroX - 86,
                centroY - 48,
                -0.10,
                new Color(99, 119, 255)
        );

        dibujarCarta(
                g2,
                centroX + 8,
                centroY - 48,
                0.10,
                new Color(145, 94, 255)
        );

        g2.dispose();
    }

    private void dibujarCarta(
            Graphics2D g2,
            int x,
            int y,
            double rotacion,
            Color color
    ) {

        Graphics2D carta = (Graphics2D) g2.create();

        carta.rotate(
                rotacion,
                x + 42,
                y + 54
        );

        carta.setColor(new Color(0, 0, 0, 50));
        carta.fill(
                new RoundRectangle2D.Double(
                        x + 5,
                        y + 7,
                        84,
                        108,
                        24,
                        24
                )
        );

        carta.setColor(color);
        carta.fill(
                new RoundRectangle2D.Double(
                        x,
                        y,
                        84,
                        108,
                        24,
                        24
                )
        );

        carta.setColor(new Color(255, 255, 255, 75));
        carta.fillOval(x + 12, y + 12, 60, 60);

        carta.setColor(Color.WHITE);
        carta.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        42
                )
        );

        carta.drawString("?", x + 29, y + 69);
        carta.dispose();
    }
}
