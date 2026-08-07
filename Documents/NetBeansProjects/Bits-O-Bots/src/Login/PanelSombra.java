/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author zoeca
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelSombra extends JPanel{
    
    private int redondez = 35;
    private int tamañoSombra = 14;

    private Color colorPanel = Color.WHITE;
    private Color colorSombra = new Color(0, 55, 93, 45);
    private Color colorBorde = new Color(65, 160, 215, 55);

    public PanelSombra() {
        setOpaque(false);

        setBorder(new EmptyBorder(
                12,
                12,
                12 + tamañoSombra,
                12 + tamañoSombra
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = getWidth() - tamañoSombra - 1;
        int alto = getHeight() - tamañoSombra - 1;

        // Sombra exterior suave
        for (int i = tamañoSombra; i >= 1; i--) {
            int alpha = Math.max(
                    2,
                    colorSombra.getAlpha() / (tamañoSombra + 2)
            );

            g2.setColor(new Color(
                    colorSombra.getRed(),
                    colorSombra.getGreen(),
                    colorSombra.getBlue(),
                    alpha
            ));

            g2.fillRoundRect(
                    i / 2,
                    i / 2,
                    ancho + i,
                    alto + i,
                    redondez + i,
                    redondez + i
            );
        }

        // Fondo del panel
        g2.setColor(colorPanel);
        g2.fillRoundRect(
                0,
                0,
                ancho,
                alto,
                redondez,
                redondez
        );

        // Borde suave
        g2.setColor(colorBorde);
        g2.drawRoundRect(
                0,
                0,
                ancho,
                alto,
                redondez,
                redondez
        );

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        Insets borde = super.getInsets();

        return new Insets(
                borde.top,
                borde.left,
                borde.bottom,
                borde.right
        );
    }

    public int getRedondez() {
        return redondez;
    }

    public void setRedondez(int redondez) {
        this.redondez = redondez;
        repaint();
    }

    public int getTamañoSombra() {
        return tamañoSombra;
    }

    public void setTamañoSombra(int tamañoSombra) {
        this.tamañoSombra = tamañoSombra;

        setBorder(new EmptyBorder(
                12,
                12,
                12 + tamañoSombra,
                12 + tamañoSombra
        ));

        revalidate();
        repaint();
    }

    public Color getColorPanel() {
        return colorPanel;
    }

    public void setColorPanel(Color colorPanel) {
        this.colorPanel = colorPanel;
        repaint();
    }

    public Color getColorSombra() {
        return colorSombra;
    }

    public void setColorSombra(Color colorSombra) {
        this.colorSombra = colorSombra;
        repaint();
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
        repaint();
    }
}
    
