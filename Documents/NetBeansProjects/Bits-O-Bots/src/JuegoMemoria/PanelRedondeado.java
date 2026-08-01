package JuegoMemoria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class PanelRedondeado extends JPanel {

    private final int radio;
    private final Color colorFondo;
    private final Color colorBorde;

    public PanelRedondeado(
            int radio,
            Color colorFondo,
            Color colorBorde
    ) {

        this.radio = radio;
        this.colorFondo = colorFondo;
        this.colorBorde = colorBorde;

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        RoundRectangle2D forma = new RoundRectangle2D.Double(
                2,
                2,
                Math.max(1, getWidth() - 5),
                Math.max(1, getHeight() - 5),
                radio,
                radio
        );

        g2.setColor(colorFondo);
        g2.fill(forma);

        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(forma);

        g2.dispose();
        super.paintComponent(g);
    }
}
