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

    private boolean encima = false;
    private boolean presionado = false;

    // =====================================================
    // TAMAÑO OFICIAL DEL BOTÓN
    // =====================================================

    /*
     * TODOS los formularios usarán visualmente
     * este mismo tamaño.
     */
    private static final int TAMANO_BOTON = 60;

    /*
     * Diámetro real del círculo.
     */
    private static final int DIAMETRO_CIRCULO = 52;

    // =====================================================
    // COLORES
    // =====================================================

    private static final Color COLOR_NORMAL
            = ColoresBitsOBots.TURQUESA_OSCURO;

    private static final Color COLOR_HOVER
            = ColoresBitsOBots.TURQUESA_HOVER;

    private static final Color COLOR_PRESIONADO
            = ColoresBitsOBots.AZUL_OSCURO;

    private static final Color COLOR_BORDE
            = ColoresBitsOBots.ROBOTICA;

    private static final Color COLOR_ICONO
            = Color.WHITE;

    private static final Color COLOR_SILENCIADO
            = new Color(
                    255,
                    105,
                    120
            );

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BotonIconoMusica() {

        setToolTipText(
                "Activar o desactivar música"
        );

        /*
         * Tamaño fijo oficial.
         */
        Dimension tamano
                = new Dimension(
                        TAMANO_BOTON,
                        TAMANO_BOTON
                );

        setPreferredSize(
                tamano
        );

        setMinimumSize(
                tamano
        );

        setMaximumSize(
                tamano
        );

        /*
         * Eliminar apariencia normal del JButton.
         */
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusable(false);

        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        // =================================================
        // HOVER / PRESIÓN
        // =================================================

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
    // DIBUJAR
    // =====================================================

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        Graphics2D g2
                = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        // =================================================
        // CENTRO REAL DEL COMPONENTE
        // =================================================

        /*
         * Aunque algún Layout haga el componente
         * más alto o más ancho, el dibujo SIEMPRE
         * conserva exactamente el mismo tamaño.
         */
        int cx
                = getWidth() / 2;

        int cy
                = getHeight() / 2;

        int lado
                = DIAMETRO_CIRCULO;

        int x
                = cx
                - lado / 2;

        int y
                = cy
                - lado / 2;

        // =================================================
        // COLOR SEGÚN ESTADO
        // =================================================

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

        // =================================================
        // SOMBRA
        // =================================================

        g2.setColor(
                new Color(
                        22,
                        91,
                        105,
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

        // =================================================
        // CÍRCULO
        // =================================================

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

        // =================================================
        // BORDE
        // =================================================

        g2.setColor(
                COLOR_BORDE
        );

        g2.setStroke(
                new BasicStroke(
                        2.0f
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

        // =================================================
        // ALTAVOZ
        // =================================================

        g2.setColor(
                COLOR_ICONO
        );

        /*
         * Caja del altavoz.
         *
         * La hacemos 1-2 píxeles más ancha
         * para que se SUPERPONGA con el cono.
         *
         * Esto elimina la línea rara que
         * se podía ver en medio.
         */
        g2.fillRoundRect(
                cx - 15,
                cy - 7,
                11,
                14,
                3,
                3
        );

        /*
         * Cono del altavoz.
         *
         * Se mete ligeramente dentro
         * de la caja anterior.
         */
        Polygon cono
                = new Polygon();

        cono.addPoint(
                cx - 7,
                cy - 7
        );

        cono.addPoint(
                cx + 2,
                cy - 14
        );

        cono.addPoint(
                cx + 2,
                cy + 14
        );

        cono.addPoint(
                cx - 7,
                cy + 7
        );

        g2.fillPolygon(
                cono
        );

        // =================================================
        // SONIDO / SILENCIO
        // =================================================

        if (
                GestorMusica.estaSilenciado()
        ) {

            // =============================================
            // SILENCIADO
            // =============================================

            g2.setColor(
                    COLOR_SILENCIADO
            );

            g2.setStroke(
                    new BasicStroke(
                            3.0f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    cx + 7,
                    cy - 9,
                    cx + 19,
                    cy + 9
            );

            g2.drawLine(
                    cx + 19,
                    cy - 9,
                    cx + 7,
                    cy + 9
            );

        } else {

            // =============================================
            // MÚSICA ACTIVA
            // =============================================

            g2.setColor(
                    COLOR_ICONO
            );

            g2.setStroke(
                    new BasicStroke(
                            2.4f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            /*
             * Primera onda.
             */
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

            /*
             * Segunda onda.
             */
            g2.draw(
                    new QuadCurve2D.Double(
                            cx + 10,
                            cy - 13,

                            cx + 21,
                            cy,

                            cx + 10,
                            cy + 13
                    )
            );
        }

        g2.dispose();
    }

    // =====================================================
    // ÁREA CLICKEABLE
    // =====================================================

    @Override
    public boolean contains(
            int mouseX,
            int mouseY
    ) {

        int cx
                = getWidth() / 2;

        int cy
                = getHeight() / 2;

        int lado
                = DIAMETRO_CIRCULO;

        int x
                = cx
                - lado / 2;

        int y
                = cy
                - lado / 2;

        Ellipse2D circulo
                = new Ellipse2D.Double(
                        x,
                        y,
                        lado,
                        lado
                );

        return circulo.contains(
                mouseX,
                mouseY
        );
    }
}