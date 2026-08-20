package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelDegradado extends JPanel {

    private final Color colorInicio;
    private final Color colorFin;

    public PanelDegradado(
            Color colorInicio,
            Color colorFin
    ) {

        this.colorInicio = colorInicio;
        this.colorFin = colorFin;

        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2
                = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = getWidth();
        int alto = getHeight();

        /*
         * ==========================================
         * FONDO CLARO
         * ==========================================
         */

        GradientPaint degradado
                = new GradientPaint(
                        0,
                        0,
                        colorInicio,
                        ancho,
                        alto,
                        colorFin
                );

        g2.setPaint(degradado);

        g2.fillRect(
                0,
                0,
                ancho,
                alto
        );

        /*
         * ==========================================
         * CIRCUITOS
         * ==========================================
         */

        dibujarCircuitos(
                g2,
                ancho,
                alto
        );

        g2.dispose();
    }

    /*
     * ==============================================
     * CIRCUITOS DECORATIVOS
     * ==============================================
     */

    private void dibujarCircuitos(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setStroke(
                new BasicStroke(
                        2.2f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        /*
         * IZQUIERDA ARRIBA
         */

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    0, 85,
                    60, 85,
                    95, 120,
                    180, 120
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    0, 160,
                    45, 160,
                    80, 195,
                    150, 195
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.ROBOTICA,
                new int[]{
                    0, 260,
                    75, 260,
                    110, 225,
                    205, 225
                }
        );


        /*
         * DERECHA ARRIBA
         */

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    ancho, 90,
                    ancho - 60, 90,
                    ancho - 100, 130,
                    ancho - 190, 130
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.ROBOTICA,
                new int[]{
                    ancho, 190,
                    ancho - 70, 190,
                    ancho - 105, 225,
                    ancho - 205, 225
                }
        );


        /*
         * IZQUIERDA CENTRO
         */

        dibujarRuta(
                g2,
                ColoresBitsOBots.ROBOTICA,
                new int[]{
                    0, alto / 2,
                    65, alto / 2,
                    95, alto / 2 + 30,
                    165, alto / 2 + 30
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    0, alto / 2 + 120,
                    80, alto / 2 + 120,
                    115, alto / 2 + 85,
                    185, alto / 2 + 85
                }
        );


        /*
         * DERECHA CENTRO
         */

        dibujarRuta(
                g2,
                ColoresBitsOBots.ROBOTICA,
                new int[]{
                    ancho, alto / 2,
                    ancho - 65, alto / 2,
                    ancho - 100, alto / 2 + 35,
                    ancho - 180, alto / 2 + 35
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    ancho, alto / 2 + 125,
                    ancho - 75, alto / 2 + 125,
                    ancho - 110, alto / 2 + 90,
                    ancho - 190, alto / 2 + 90
                }
        );


        /*
         * ABAJO IZQUIERDA
         */

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    0, alto - 85,
                    70, alto - 85,
                    105, alto - 120,
                    190, alto - 120
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.ROBOTICA,
                new int[]{
                    0, alto - 180,
                    55, alto - 180,
                    90, alto - 215,
                    160, alto - 215
                }
        );


        /*
         * ABAJO DERECHA
         */

        dibujarRuta(
                g2,
                ColoresBitsOBots.AZUL_PRINCIPAL,
                new int[]{
                    ancho, alto - 85,
                    ancho - 70, alto - 85,
                    ancho - 105, alto - 120,
                    ancho - 190, alto - 120
                }
        );

        dibujarRuta(
                g2,
                ColoresBitsOBots.ROBOTICA,
                new int[]{
                    ancho, alto - 185,
                    ancho - 60, alto - 185,
                    ancho - 95, alto - 220,
                    ancho - 170, alto - 220
                }
        );
    }

    /*
     * ==============================================
     * DIBUJAR RUTA
     * ==============================================
     */

    private void dibujarRuta(
            Graphics2D g2,
            Color color,
            int[] puntos
    ) {

        Color transparente
                = new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        145
                );

        g2.setColor(transparente);

        /*
         * Líneas.
         */
        for (
                int i = 0;
                i < puntos.length - 2;
                i += 2
        ) {

            g2.drawLine(
                    puntos[i],
                    puntos[i + 1],
                    puntos[i + 2],
                    puntos[i + 3]
            );
        }

        /*
         * Nodos.
         */
        for (
                int i = 2;
                i < puntos.length;
                i += 2
        ) {

            dibujarNodo(
                    g2,
                    puntos[i],
                    puntos[i + 1],
                    color
            );
        }
    }

    /*
     * ==============================================
     * NODO
     * ==============================================
     */

    private void dibujarNodo(
            Graphics2D g2,
            int x,
            int y,
            Color color
    ) {

        g2.setColor(
                new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        190
                )
        );

        g2.fillOval(
                x - 4,
                y - 4,
                8,
                8
        );

        g2.setColor(
                Color.WHITE
        );

        g2.fillOval(
                x - 1,
                y - 1,
                3,
                3
        );
    }
}