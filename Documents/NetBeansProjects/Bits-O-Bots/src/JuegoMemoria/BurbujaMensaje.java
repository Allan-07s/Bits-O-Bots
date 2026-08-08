package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BurbujaMensaje extends JPanel {

    private final Color colorFondo;
    private final Color colorBorde;

    private int radio = 24;
    private int altoPunta = 18;
    private int anchoPunta = 26;

    public BurbujaMensaje(
            Color colorFondo,
            Color colorBorde
    ) {

        this.colorFondo = colorFondo;
        this.colorBorde = colorBorde;

        setOpaque(false);

        /*
         * El espacio inferior deja lugar
         * para la punta de la burbuja.
         */
        setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        16,
                        altoPunta + 10,
                        16
                )
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = getWidth();
        int altoCuerpo = getHeight() - altoPunta;

        /*
         * Cuerpo principal redondeado.
         */
        g2.setColor(colorFondo);

        g2.fillRoundRect(
                1,
                1,
                ancho - 3,
                altoCuerpo - 2,
                radio,
                radio
        );

        /*
         * Punta inferior que señala al robot.
         */
        int centroX = ancho / 2;

        Polygon punta = new Polygon();

        punta.addPoint(
                centroX - anchoPunta / 2,
                altoCuerpo - 3
        );

        punta.addPoint(
                centroX + anchoPunta / 2,
                altoCuerpo - 3
        );

        punta.addPoint(
                centroX,
                getHeight() - 2
        );

        g2.fillPolygon(punta);

        /*
         * Borde del cuerpo.
         */
        g2.setColor(colorBorde);
        
        

        g2.setStroke(
                new BasicStroke(2f)
        );

        g2.drawRoundRect(
                1,
                1,
                ancho - 3,
                altoCuerpo - 2,
                radio,
                radio
        );

        /*
         * Líneas laterales de la punta.
         * No dibujamos la línea superior para que
         * se una visualmente con el cuerpo.
         */
        g2.drawLine(
                centroX - anchoPunta / 2,
                altoCuerpo - 2,
                centroX,
                getHeight() - 2
        );

        g2.drawLine(
                centroX,
                getHeight() - 2,
                centroX + anchoPunta / 2,
                altoCuerpo - 2
        );

        g2.dispose();

        super.paintComponent(g);
    }

    public void setRadio(int radio) {
        this.radio = radio;
        repaint();
    }

    public void setTamanoPunta(
            int ancho,
            int alto
    ) {

        this.anchoPunta = ancho;
        this.altoPunta = alto;

        setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        16,
                        altoPunta + 10,
                        16
                )
        );

        revalidate();
        repaint();
    }
}