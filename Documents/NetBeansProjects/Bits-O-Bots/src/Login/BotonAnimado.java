/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author zoeca
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.Timer;

public class BotonAnimado extends JButton {

    private Color colorNormal = new Color(1, 137, 129);
    private Color colorHover = new Color(0, 105, 119);
    private Color colorSombra = new Color(0, 55, 93, 55);

    private int redondez = 38;
    private float progresoHover = 0f;
    private boolean mouseEncima = false;

    private final Timer animacion;

    public BotonAnimado() {
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        animacion = new Timer(12, e -> animar());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEncima = true;

                if (!animacion.isRunning()) {
                    animacion.start();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseEncima = false;

                if (!animacion.isRunning()) {
                    animacion.start();
                }
            }
        });
    }

    private void animar() {
        float velocidad = 0.09f;

        if (mouseEncima) {
            progresoHover += velocidad;

            if (progresoHover >= 1f) {
                progresoHover = 1f;
                animacion.stop();
            }
        } else {
            progresoHover -= velocidad;

            if (progresoHover <= 0f) {
                progresoHover = 0f;
                animacion.stop();
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int elevacion = Math.round(3 * progresoHover);
        int margen = 5;

        int x = margen;
        int y = margen - elevacion;
        int ancho = getWidth() - margen * 2;
        int alto = getHeight() - margen * 2 - 3;

        // Sombra
        g2.setColor(colorSombra);
        g2.fillRoundRect(
                x,
                y + 5 + elevacion,
                ancho,
                alto,
                redondez,
                redondez
        );

        // Color interpolado
        g2.setColor(interpolarColor(
                colorNormal,
                colorHover,
                progresoHover
        ));

        g2.fillRoundRect(
                x,
                y,
                ancho,
                alto,
                redondez,
                redondez
        );

        // Brillo superior
        g2.setColor(new Color(255, 255, 255, 42));
        g2.setStroke(new BasicStroke(1.2f));

        g2.drawRoundRect(
                x + 1,
                y + 1,
                ancho - 2,
                alto - 2,
                redondez,
                redondez
        );

        g2.dispose();

        super.paintComponent(g);
    }

    private Color interpolarColor(
            Color inicio,
            Color finalColor,
            float progreso
    ) {
        int rojo = (int) (
                inicio.getRed()
                + (finalColor.getRed() - inicio.getRed()) * progreso
        );

        int verde = (int) (
                inicio.getGreen()
                + (finalColor.getGreen() - inicio.getGreen()) * progreso
        );

        int azul = (int) (
                inicio.getBlue()
                + (finalColor.getBlue() - inicio.getBlue()) * progreso
        );

        return new Color(rojo, verde, azul);
    }

    public Color getColorNormal() {
        return colorNormal;
    }

    public void setColorNormal(Color colorNormal) {
        this.colorNormal = colorNormal;
        repaint();
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
        repaint();
    }

    public int getRedondez() {
        return redondez;
    }

    public void setRedondez(int redondez) {
        this.redondez = redondez;
        repaint();
    }
} 
    

