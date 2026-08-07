/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
/**
 *
 * @author alvar
 */

public class PanelPlataformaAvatar extends JPanel {
    
private float pulsoTexto = 0f;
private boolean subeTexto = true;

    public PanelPlataformaAvatar() {
        
        setOpaque(false);
        setLayout(new BorderLayout());

        // Tamaño sugerido para que el avatar tenga bastante espacio
        setPreferredSize(new Dimension(380, 350));
        setMinimumSize(new Dimension(300, 280));
        
        javax.swing.Timer timerTexto
        = new javax.swing.Timer(100, e -> {

    if (subeTexto) {
        pulsoTexto += 0.03f;

        if (pulsoTexto >= 1f) {
            pulsoTexto = 1f;
            subeTexto = false;
        }

    } else {

        pulsoTexto -= 0.03f;

        if (pulsoTexto <= 0f) {
            pulsoTexto = 0f;
            subeTexto = true;
        }
    }

    repaint();
});

timerTexto.start();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = getWidth();
        int alto = getHeight();

        /*
         * Círculo principal más grande.
         * Antes restabas 45; ahora solo 15.
         */
        int tamaño = Math.min(ancho, alto) - 45;

        int x = (ancho - tamaño) / 2;

        // Lo subimos un poco para dejar espacio a la plataforma inferior
        int y = (alto - tamaño) / 2 - 5;

        // =========================================================
        // RESPLANDOR EXTERIOR
        // =========================================================

        g2.setColor(new Color(0, 190, 255, 18));
        g2.fillOval(
                x - 15,
                y - 15,
                tamaño + 30,
                tamaño + 30
        );

        g2.setColor(new Color(0, 120, 255, 25));
        g2.fillOval(
                x - 20,
                y - 20,
                tamaño + 40,
                tamaño + 40
        );

        // =========================================================
        // CÍRCULO EXTERIOR
        // =========================================================

        g2.setStroke(new BasicStroke(4f));
        g2.setColor(new Color(0, 145, 255, 180));

        g2.draw(new Ellipse2D.Double(
                x,
                y,
                tamaño,
                tamaño
        ));

        // =========================================================
        // CÍRCULO INTERMEDIO
        // =========================================================

        g2.setStroke(new BasicStroke(
                3.5f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                0,
                new float[]{15, 9},
                0
        ));

        g2.setColor(new Color(0, 220, 255, 190));

        g2.draw(new Ellipse2D.Double(
                x + 24,
                y + 24,
                tamaño - 48,
                tamaño - 48
        ));

        // =========================================================
        // CÍRCULO INTERIOR
        // =========================================================

        g2.setStroke(new BasicStroke(
                2.5f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                0,
                new float[]{5, 12},
                0
        ));

        g2.setColor(new Color(45, 105, 255, 145));

        g2.draw(new Ellipse2D.Double(
                x + 52,
                y + 52,
                tamaño - 104,
                tamaño - 104
        ));

        // =========================================================
        // PUNTOS TECNOLÓGICOS
        // =========================================================

        g2.setColor(new Color(0, 220, 255, 220));

        g2.fillOval(
                x + tamaño - 28,
                y + tamaño / 3,
                9,
                9
        );

        g2.fillOval(
                x + 34,
                y + tamaño / 2,
                7,
                7
        );

        g2.setColor(new Color(40, 95, 255, 220));

        g2.fillOval(
                x + tamaño / 2,
                y + 13,
                8,
                8
        );

        g2.fillOval(
                x + tamaño - 55,
                y + tamaño - 58,
                6,
                6
        );

        // =========================================================
        // PLATAFORMA INFERIOR
        // =========================================================

        int anchoPlataforma = (int) (tamaño * 0.95);

        // Más ancha pero menos exageradamente alta
        int altoPlataforma = 95;

        int plataformaX = (ancho - anchoPlataforma) / 2;

        int plataformaY = alto - altoPlataforma - 20;

        // Resplandor debajo
        g2.setColor(new Color(0, 195, 255, 30));

        g2.fillOval(
                plataformaX - 18,
                plataformaY - 12,
                anchoPlataforma + 36,
                altoPlataforma + 24
        );

        // Plataforma principal
        GradientPaint degradado = new GradientPaint(
                plataformaX,
                plataformaY,
                new Color(0, 135, 255, 75),
                plataformaX,
                plataformaY + altoPlataforma,
                new Color(0, 240, 255, 120)
        );

        g2.setPaint(degradado);

        g2.fillOval(
                plataformaX,
                plataformaY,
                anchoPlataforma,
                altoPlataforma
        );

        // Borde brillante
        g2.setStroke(new BasicStroke(4f));

        g2.setColor(
                new Color(0, 210, 255, 210)
        );

        g2.drawOval(
                plataformaX,
                plataformaY,
                anchoPlataforma,
                altoPlataforma
        );

        // =========================================================
        // ANILLO INTERIOR DE LA PLATAFORMA
        // =========================================================

        g2.setStroke(new BasicStroke(2.5f));

        g2.setColor(
                new Color(45, 95, 255, 175)
        );

        g2.drawOval(
                plataformaX + 22,
                plataformaY + 16,
                anchoPlataforma - 44,
                altoPlataforma - 32
        );

        // =========================================================
        // LÍNEA DE LUZ CENTRAL
        // =========================================================

        g2.setStroke(new BasicStroke(3f));

        g2.setColor(
                new Color(100, 245, 255, 180)
        );

        g2.drawLine(
                plataformaX + 70,
                plataformaY + altoPlataforma / 2,
                plataformaX + anchoPlataforma - 70,
                plataformaY + altoPlataforma / 2
        );
        
        int alphaTexto
        = (int) (50 + pulsoTexto * 120);

g2.setFont(
        new Font(
                "Monospaced",
                Font.BOLD,
                10
        )
);

g2.setColor(
        new Color(
                0,
                125,
                255,
                Math.min(alphaTexto, 170)
        )
);

g2.drawString(
        "0101",
        25,
        60
);

g2.setColor(
        new Color(
                0,
                210,
                210,
                Math.min(alphaTexto, 170)
        )
);

g2.drawString(
        "ONLINE",
        getWidth() - 80,
        85
);

g2.setColor(
        new Color(
                30,
                100,
                235,
                Math.min(alphaTexto, 170)
        )
);

g2.drawString(
        "READY",
        getWidth() - 75,
        getHeight() - 55
);

        g2.dispose();
    }
}
