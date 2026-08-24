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
import javax.swing.Timer;

public class BarraNivel extends JPanel {

    private int porcentaje = 0;
    private int porcentajeObjetivo;

    private final int radio = 22;

    public BarraNivel() {
        setOpaque(false);
        iniciarAnimacion();
    }

    private void iniciarAnimacion() {

        Timer timer = new Timer(10, null);

        timer.addActionListener(e -> {

            if (porcentaje < porcentajeObjetivo) {

                porcentaje++;
                repaint();

            } else {

                timer.stop();
            }
        });

        timer.start();
    }

    public void setPorcentaje(int porcentaje) {

        if (porcentaje < 0) {
            porcentaje = 0;
        }

        if (porcentaje > 100) {
            porcentaje = 100;
        }

        this.porcentajeObjetivo = porcentaje;
        this.porcentaje = 0;

        iniciarAnimacion();
    }

    public int getPorcentaje() {
        return porcentajeObjetivo;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();

        int margenDerecho = 90;

        int barraX = 5;
        int barraY = 12;

        int barraW = w - margenDerecho - 10;
        int barraH = h - 24;

        // FONDO DE LA BARRA
        g2.setColor(new Color(198, 210, 220));

        g2.fillRoundRect(
                barraX,
                barraY,
                barraW,
                barraH,
                radio,
                radio
        );

        // CANTIDAD DE BLOQUES
        int bloques = 20;
        int separacion = 4;

        int anchoBloque
                = (barraW - ((bloques - 1) * separacion))
                / bloques;

        int bloquesActivos
                = (int) Math.round((porcentaje / 100.0) * bloques);

        for (int i = 0; i < bloques; i++) {

            int x = barraX
                    + i * (anchoBloque + separacion);

            if (i < bloquesActivos) {

                // DEGRADADO AZUL
                GradientPaint azul = new GradientPaint(
                        x,
                        barraY,
                        new Color(40, 135, 230),
                        x,
                        barraY + barraH,
                        new Color(0, 66, 126)
                );

                g2.setPaint(azul);

            } else {

                g2.setColor(
                        new Color(205, 215, 223)
                );
            }

            g2.fillRoundRect(
                    x,
                    barraY,
                    anchoBloque,
                    barraH,
                    8,
                    8
            );

            // BRILLO
            if (i < bloquesActivos) {

                g2.setColor(
                        new Color(255, 255, 255, 80)
                );

                g2.fillRoundRect(
                        x + 2,
                        barraY + 2,
                        anchoBloque - 4,
                        Math.max(3, barraH / 4),
                        5,
                        5
                );
            }
        }

        // PORCENTAJE
        g2.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        g2.setColor(
                new Color(0, 66, 126)
        );

        String texto = porcentaje + "%";

        FontMetrics fm = g2.getFontMetrics();

        int textX
                = w - fm.stringWidth(texto) - 12;

        int textY
                = (h + fm.getAscent()
                - fm.getDescent()) / 2;

        g2.drawString(
                texto,
                textX,
                textY
        );

        g2.dispose();
    }
}
