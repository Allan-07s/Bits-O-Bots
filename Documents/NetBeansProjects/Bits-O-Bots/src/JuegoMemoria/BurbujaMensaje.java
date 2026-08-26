package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BurbujaMensaje extends JPanel {

    private final Color colorFondo;
    private final Color colorBorde;

    private int radio = 24;
    private int altoPunta = 18;
    private int anchoPunta = 26;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BurbujaMensaje(
            Color colorFondo,
            Color colorBorde
    ) {

        this.colorFondo = colorFondo;
        this.colorBorde = colorBorde;

        setOpaque(false);

        /*
         * Dejamos espacio inferior
         * para la punta de la burbuja.
         */
        actualizarBordeInterno();
    }

    // =====================================================
    // DIBUJAR BURBUJA
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

        int ancho
                = getWidth();

        int alto
                = getHeight();

        /*
         * Altura que ocupa el cuerpo
         * sin contar la punta.
         */
        int altoCuerpo
                = alto - altoPunta;

        // =================================================
        // CUERPO REDONDEADO
        // =================================================

        RoundRectangle2D cuerpo
                = new RoundRectangle2D.Double(
                        2,
                        2,
                        Math.max(
                                1,
                                ancho - 5
                        ),
                        Math.max(
                                1,
                                altoCuerpo - 3
                        ),
                        radio,
                        radio
                );

        // =================================================
        // PUNTA
        // =================================================

        int centroX
                = ancho / 2;

        /*
         * IMPORTANTE:
         *
         * La base del triángulo entra algunos
         * píxeles dentro del cuerpo.
         *
         * Así ambas figuras SE UNEN
         * y no aparece la línea atravesada.
         */
        int yBase
                = altoCuerpo - 7;

        int yPunta
                = alto - 2;

        Polygon punta
                = new Polygon();

        punta.addPoint(
                centroX
                - anchoPunta / 2,
                yBase
        );

        punta.addPoint(
                centroX
                + anchoPunta / 2,
                yBase
        );

        punta.addPoint(
                centroX,
                yPunta
        );

        // =================================================
        // UNIR CUERPO + PUNTA
        // =================================================

        /*
         * Esta es la corrección principal.
         *
         * En lugar de pintar:
         *
         * RECTÁNGULO
         * +
         * TRIÁNGULO
         *
         * por separado...
         *
         * los convertimos en UNA SOLA FORMA.
         */
        Area formaCompleta
                = new Area(
                        cuerpo
                );

        formaCompleta.add(
                new Area(
                        punta
                )
        );

        // =================================================
        // FONDO
        // =================================================

        g2.setColor(
                colorFondo
        );

        g2.fill(
                formaCompleta
        );

        // =================================================
        // BORDE
        // =================================================

        /*
         * Al dibujar el borde de la figura
         * ya unida, desaparece automáticamente
         * la línea horizontal que antes cruzaba
         * la base de la punta.
         */
        g2.setColor(
                colorBorde
        );

        g2.setStroke(
                new BasicStroke(
                        2f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.draw(
                formaCompleta
        );

        g2.dispose();

        /*
         * Pintar los JLabel/JPanel que estén
         * dentro de la burbuja.
         */
        super.paintComponent(
                g
        );
    }

    // =====================================================
    // CAMBIAR RADIO
    // =====================================================

    public void setRadio(
            int radio
    ) {

        this.radio
                = radio;

        repaint();
    }

    // =====================================================
    // CAMBIAR TAMAÑO DE LA PUNTA
    // =====================================================

    public void setTamanoPunta(
            int ancho,
            int alto
    ) {

        this.anchoPunta
                = ancho;

        this.altoPunta
                = alto;

        actualizarBordeInterno();

        revalidate();
        repaint();
    }

    // =====================================================
    // ESPACIO INTERIOR
    // =====================================================

    private void actualizarBordeInterno() {

        setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        16,
                        altoPunta + 10,
                        16
                )
        );
    }
}