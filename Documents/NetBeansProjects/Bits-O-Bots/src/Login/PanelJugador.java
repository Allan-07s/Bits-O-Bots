/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alvar
 */

public class PanelJugador extends JPanel {

    private String nombre = "Jugador";
    private String seccion = "";

    private float pulso = 0f;
    private boolean subiendo = true;

    public PanelJugador() {
        setOpaque(false);

        Timer timer = new Timer(90, e -> {

            if (subiendo) {
                pulso += 0.03f;

                if (pulso >= 1f) {
                    pulso = 1f;
                    subiendo = false;
                }

            } else {

                pulso -= 0.03f;

                if (pulso <= 0f) {
                    pulso = 0f;
                    subiendo = true;
                }
            }

            repaint();
        });

        timer.start();
    }

    public void setDatos(String nombre, String seccion) {

        this.nombre =
                nombre == null || nombre.isBlank()
                ? "Jugador"
                : nombre;

        this.seccion =
                seccion == null
                ? ""
                : seccion;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();

        // ===========================
        // SOMBRA
        // ===========================

        g2.setColor(
                new Color(0, 70, 140, 28)
        );

        g2.fill(new RoundRectangle2D.Double(
                8,
                10,
                w - 16,
                h - 18,
                30,
                30
        ));

        // ===========================
        // FONDO
        // ===========================

        GradientPaint fondo =
                new GradientPaint(
                        0,
                        0,
                        new Color(255, 255, 255, 245),
                        w,
                        h,
                        new Color(235, 248, 255, 235)
                );

        g2.setPaint(fondo);

        g2.fill(new RoundRectangle2D.Double(
                5,
                5,
                w - 15,
                h - 18,
                30,
                30
        ));

        // ===========================
        // BORDE
        // ===========================

        int alphaBorde =
                (int) (110 + pulso * 90);

        g2.setStroke(new BasicStroke(
                2.2f
        ));

        g2.setColor(new Color(
                0,
                125,
                255,
                Math.min(alphaBorde, 200)
        ));

        g2.draw(new RoundRectangle2D.Double(
                5,
                5,
                w - 15,
                h - 18,
                30,
                30
        ));

        // ===========================
        // PUNTO ONLINE
        // ===========================

        int alpha =
                (int) (120 + pulso * 120);

        g2.setColor(new Color(
                0,
                225,
                185,
                Math.min(alpha, 240)
        ));

        int punto =
                (int) (10 + pulso * 4);

        g2.fillOval(
                24 - punto / 2,
                27 - punto / 2,
                punto,
                punto
        );

        // Glow
        g2.setColor(new Color(
                0,
                225,
                185,
                35
        ));

        g2.fillOval(
                12,
                15,
                25,
                25
        );

        // ===========================
        // TITULO
        // ===========================

        g2.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                10
        ));

        g2.setColor(
                new Color(70, 105, 130)
        );

        g2.drawString(
                "JUGADOR ACTIVO",
                43,
                30
        );

        // ===========================
        // NOMBRE
        // ===========================

        g2.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                19
        ));

        g2.setColor(
                new Color(15, 80, 175)
        );

        g2.drawString(
                nombre,
                24,
                59
        );

        // ===========================
        // SECCIÓN
        // ===========================

        g2.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                12
        ));

        g2.setColor(
                new Color(75, 105, 125)
        );

        g2.drawString(
                seccion,
                24,
                80
        );

        // ===========================
        // MINI ESTADO
        // ===========================

        g2.setFont(new Font(
                "Monospaced",
                Font.BOLD,
                8
        ));

        g2.setColor(new Color(
                0,
                180,
                190,
                130
        ));

        g2.drawString(
                "ONLINE // READY",
                w - 120,
                h - 27
        );

        // ===========================
        // ESQUINA TECNOLÓGICA
        // ===========================

        g2.setStroke(new BasicStroke(2f));

        g2.setColor(new Color(
                0,
                165,
                255,
                160
        ));

        g2.drawLine(
                w - 50,
                h - 23,
                w - 20,
                h - 23
        );

        g2.drawLine(
                w - 20,
                h - 23,
                w - 20,
                h - 45
        );

        g2.dispose();
    }
}