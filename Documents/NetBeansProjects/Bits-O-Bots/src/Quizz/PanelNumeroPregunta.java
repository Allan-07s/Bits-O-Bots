/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class PanelNumeroPregunta extends JLabel {

    private final Color azulInicio = new Color(21, 101, 216);
    private final Color azulFinal = new Color(47, 128, 237);

    public PanelNumeroPregunta() {
        setOpaque(false);
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        int ancho = getWidth();
        int alto = getHeight();

        // Sombra
        g2.setColor(new Color(20, 70, 150, 45));
        g2.fillRoundRect(
                4,
                6,
                ancho - 8,
                alto - 9,
                28,
                28
        );

        // Fondo azul degradado
        GradientPaint degradado = new GradientPaint(
                0,
                0,
                azulInicio,
                ancho,
                alto,
                azulFinal
        );

        g2.setPaint(degradado);
        g2.fillRoundRect(
                2,
                2,
                ancho - 8,
                alto - 10,
                28,
                28
        );

        // Brillo superior
        g2.setColor(new Color(255, 255, 255, 35));
        g2.fillRoundRect(
                6,
                5,
                ancho - 16,
                alto / 2,
                22,
                22
        );

        // Texto "Pregunta"
        Font fuenteTitulo = new Font(
                "SansSerif",
                Font.BOLD,
                13
        );

        g2.setFont(fuenteTitulo);
        g2.setColor(new Color(225, 240, 255));

        FontMetrics fmTitulo = g2.getFontMetrics();

        String titulo = "Pregunta";

        int xTitulo =
                (ancho - fmTitulo.stringWidth(titulo)) / 2;

        g2.drawString(
                titulo,
                xTitulo,
                25
        );

        // Texto "01 / 10"
        Font fuenteNumero = new Font(
                "SansSerif",
                Font.BOLD,
                21
        );

        g2.setFont(fuenteNumero);
        g2.setColor(Color.WHITE);

        FontMetrics fmNumero = g2.getFontMetrics();

        String numero = getText();

        int xNumero =
                (ancho - fmNumero.stringWidth(numero)) / 2;

        g2.drawString(
                numero,
                xNumero,
                52
        );

        g2.dispose();
    }
}
    

