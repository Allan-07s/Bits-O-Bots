/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
/**
 *
 * @author alvar
 */



public class PanelEnunciado extends JPanel {

    public PanelEnunciado() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        int radio = 20;

        // Sombra suave en la parte inferior
        g2.setColor(new Color(29, 53, 87, 20));
        g2.fillRoundRect(2, 4, ancho - 4, alto - 4, radio, radio);

        // Fondo de la tarjeta (blanco limpio)
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, ancho - 4, alto - 6, radio, radio);

        // Borde fino de color azul suave
        g2.setColor(new Color(180, 205, 235));
        g2.drawRoundRect(0, 0, ancho - 4, alto - 6, radio, radio);

        g2.dispose();
    }
}

