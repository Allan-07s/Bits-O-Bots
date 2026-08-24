package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

    /*
     * Indica si el mouse está encima
     * del botón.
     */
    private boolean encima = false;

    /*
     * Indica si el botón está siendo
     * presionado.
     */
    private boolean presionado = false;

    // =====================================================
    // COLORES
    // =====================================================

    /*
     * Color normal del círculo.
     */
    private static final Color COLOR_NORMAL
            = ColoresBitsOBots.TURQUESA_OSCURO;

    /*
     * Color cuando el mouse pasa encima.
     */
    private static final Color COLOR_HOVER
            = ColoresBitsOBots.TURQUESA_HOVER;

    /*
     * Color mientras se presiona.
     */
    private static final Color COLOR_PRESIONADO
            = ColoresBitsOBots.AZUL_OSCURO;

    /*
     * Borde del círculo.
     */
    private static final Color COLOR_BORDE
            = ColoresBitsOBots.ROBOTICA;

    /*
     * Color del icono.
     */
    private static final Color COLOR_ICONO
            = Color.WHITE;

    /*
     * Color de la X cuando
     * la música está apagada.
     */
    private static final Color COLOR_SILENCIADO
            = new Color(
                    255,
                    110,
                    125
            );

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BotonIconoMusica() {

        setToolTipText(
                "Activar o desactivar música"
        );

        /*
         * Tamaño predeterminado.
         *
         * Si algún formulario usa 65x65,
         * también funcionará perfectamente.
         */
        Dimension tamano
                = new Dimension(
                        62,
                        62
                );

        setPreferredSize(
                tamano
        );

        setMinimumSize(
                tamano
        );

        /*
         * Quitamos completamente
         * el aspecto normal de JButton.
         */
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        /*
         * Evita el rectángulo visual
         * cuando recibe focus.
         */
        setFocusable(false);

        /*
         * Cursor de mano.
         */
        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        /*
         * Detectar hover y presión.
         */
        addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseEntered(
                    MouseEvent e
            ) {

                encima = true;

                repaint();
            }

            @Override
            public void mouseExited(
                    MouseEvent e
            ) {

                encima = false;

                presionado = false;

                repaint();
            }

            @Override
            public void mousePressed(
                    MouseEvent e
            ) {

                presionado = true;

                repaint();
            }

            @Override
            public void mouseReleased(
                    MouseEvent e
            ) {

                presionado = false;

                repaint();
            }
        });
    }

    // =====================================================
    // DIBUJAR BOTÓN
    // =====================================================

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        Graphics2D g2
                = (Graphics2D) g.create();

        /*
         * Dibujar todo suavemente.
         */
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        /*
         * =================================================
         * TAMAÑO DEL CÍRCULO
         * =================================================
         */

        int margen = 4;

        int lado
                = Math.min(
                        getWidth(),
                        getHeight()
                )
                - margen * 2;

        int x
                = (
                        getWidth()
                        - lado
                ) / 2;

        int y
                = (
                        getHeight()
                        - lado
                ) / 2;

        /*
         * =================================================
         * COLOR SEGÚN ESTADO
         * =================================================
         */

        Color fondo;

        if (presionado) {

            fondo
                    = COLOR_PRESIONADO;

        } else if (encima) {

            fondo
                    = COLOR_HOVER;

        } else {

            fondo
                    = COLOR_NORMAL;
        }

        /*
         * =================================================
         * SOMBRA SUAVE
         * =================================================
         */

        g2.setColor(
                new Color(
                        20,
                        80,
                        95,
                        45
                )
        );

        g2.fill(
                new Ellipse2D.Double(
                        x + 2,
                        y + 3,
                        lado,
                        lado
                )
        );

        /*
         * =================================================
         * CÍRCULO PRINCIPAL
         * =================================================
         */

        g2.setColor(
                fondo
        );

        g2.fill(
                new Ellipse2D.Double(
                        x,
                        y,
                        lado,
                        lado
                )
        );

        /*
         * =================================================
         * BORDE
         * =================================================
         */

        g2.setColor(
                COLOR_BORDE
        );

        g2.setStroke(
                new BasicStroke(
                        2.2f
                )
        );

        g2.draw(
                new Ellipse2D.Double(
                        x,
                        y,
                        lado,
                        lado
                )
        );

        /*
         * =================================================
         * CENTRO
         * =================================================
         */

        int cx
                = getWidth() / 2;

        int cy
                = getHeight() / 2;

        /*
         * El tamaño del icono se adapta
         * al tamaño del botón.
         */
        double escala
                = lado / 57.0;

        /*
         * Limitar la escala para evitar
         * iconos exageradamente grandes.
         */
        escala
                = Math.max(
                        0.80,
                        Math.min(
                                escala,
                                1.10
                        )
                );

        /*
         * =================================================
         * CAJA DEL ALTAVOZ
         * =================================================
         */

        g2.setColor(
                COLOR_ICONO
        );

        int cajaX
                = cx
                - (int) (15 * escala);

        int cajaY
                = cy
                - (int) (7 * escala);

        int cajaAncho
                = (int) (9 * escala);

        int cajaAlto
                = (int) (14 * escala);

        g2.fillRoundRect(
                cajaX,
                cajaY,
                cajaAncho,
                cajaAlto,
                3,
                3
        );

        /*
         * =================================================
         * CONO DEL ALTAVOZ
         * =================================================
         */

        Polygon cono
                = new Polygon();

        cono.addPoint(
                cx - (int) (6 * escala),
                cy - (int) (7 * escala)
        );

        cono.addPoint(
                cx + (int) (2 * escala),
                cy - (int) (14 * escala)
        );

        cono.addPoint(
                cx + (int) (2 * escala),
                cy + (int) (14 * escala)
        );

        cono.addPoint(
                cx - (int) (6 * escala),
                cy + (int) (7 * escala)
        );

        g2.fillPolygon(
                cono
        );

        /*
         * =================================================
         * LÍNEAS DE SONIDO / X
         * =================================================
         */

        g2.setStroke(
                new BasicStroke(
                        (float) (2.4 * escala),
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        /*
         * =================================================
         * MÚSICA SILENCIADA
         * =================================================
         */

        if (
                GestorMusica.estaSilenciado()
        ) {

            g2.setColor(
                    COLOR_SILENCIADO
            );

            /*
             * X más gruesa y visible.
             */
            g2.setStroke(
                    new BasicStroke(
                            (float) (3.0 * escala),
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    cx + (int) (7 * escala),
                    cy - (int) (9 * escala),
                    cx + (int) (19 * escala),
                    cy + (int) (9 * escala)
            );

            g2.drawLine(
                    cx + (int) (19 * escala),
                    cy - (int) (9 * escala),
                    cx + (int) (7 * escala),
                    cy + (int) (9 * escala)
            );

        // =================================================
        // MÚSICA ACTIVA
        // =================================================

        } else {

            g2.setColor(
                    COLOR_ICONO
            );

            /*
             * Primera onda.
             */
            g2.draw(
                    new QuadCurve2D.Double(
                            cx + 5 * escala,
                            cy - 8 * escala,

                            cx + 12 * escala,
                            cy,

                            cx + 5 * escala,
                            cy + 8 * escala
                    )
            );

            /*
             * Segunda onda.
             */
            g2.draw(
                    new QuadCurve2D.Double(
                            cx + 9 * escala,
                            cy - 13 * escala,

                            cx + 20 * escala,
                            cy,

                            cx + 9 * escala,
                            cy + 13 * escala
                    )
            );
        }

        g2.dispose();
    }

    // =====================================================
    // ÁREA CLICKEABLE CIRCULAR
    // =====================================================

    @Override
    public boolean contains(
            int x,
            int y
    ) {

        /*
         * Incluso el área clickeable
         * se comporta como círculo,
         * no como un cuadrado invisible.
         */

        int margen = 4;

        int lado
                = Math.min(
                        getWidth(),
                        getHeight()
                )
                - margen * 2;

        int circuloX
                = (
                        getWidth()
                        - lado
                ) / 2;

        int circuloY
                = (
                        getHeight()
                        - lado
                ) / 2;

        Ellipse2D circulo
                = new Ellipse2D.Double(
                        circuloX,
                        circuloY,
                        lado,
                        lado
                );

        return circulo.contains(
                x,
                y
        );
    }
}