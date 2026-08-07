/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class BarraProgresoUI extends BasicProgressBarUI {

    private final Color fondoBarra =
            new Color(225, 235, 247);

    private final Color bordeBarra =
            new Color(180, 205, 235);

    private final Color azulInicio =
            new Color(21, 101, 216);

    private final Color azulMedio =
            new Color(47, 128, 237);

    private final Color turquesaFinal =
            new Color(54, 207, 201);

    private final Color textoOscuro =
            new Color(29, 53, 87);

    private final Color sombra =
            new Color(20, 70, 130, 35);

    @Override
    protected void paintDeterminate(
            Graphics g,
            JComponent componente
    ) {

        JProgressBar barra = (JProgressBar) componente;
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        int ancho = barra.getWidth();
        int alto = barra.getHeight();

        int margen = 3;
        int radio = alto;

        int anchoUtil = ancho - margen * 2;
        int altoUtil = alto - margen * 2;

        // =========================
        // SOMBRA SUAVE
        // =========================
        g2.setColor(sombra);

        g2.fillRoundRect(
                margen + 2,
                margen + 3,
                anchoUtil,
                altoUtil,
                radio,
                radio
        );

        // =========================
        // FONDO DE LA BARRA
        // =========================
        GradientPaint fondoDegradado =
                new GradientPaint(
                        0,
                        margen,
                        new Color(240, 246, 253),
                        0,
                        alto - margen,
                        fondoBarra
                );

        g2.setPaint(fondoDegradado);

        g2.fillRoundRect(
                margen,
                margen,
                anchoUtil,
                altoUtil,
                radio,
                radio
        );

        // =========================
        // CANTIDAD LLENA
        // =========================
        int progreso = getAmountFull(
                barra.getInsets(),
                anchoUtil,
                altoUtil
        );

        int progresoVisible = Math.max(
                0,
                Math.min(progreso, anchoUtil)
        );

        if (progresoVisible > 0) {

            // Degradado principal
            GradientPaint degradadoProgreso =
                    new GradientPaint(
                            margen,
                            0,
                            azulInicio,
                            margen + progresoVisible,
                            0,
                            turquesaFinal
                    );

            g2.setPaint(degradadoProgreso);

            g2.fillRoundRect(
                    margen,
                    margen,
                    progresoVisible,
                    altoUtil,
                    radio,
                    radio
            );

            // Brillo superior
            g2.setComposite(
                    AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER,
                            0.28f
                    )
            );

            GradientPaint brillo =
                    new GradientPaint(
                            0,
                            margen,
                            Color.WHITE,
                            0,
                            margen + altoUtil / 2,
                            new Color(255, 255, 255, 0)
                    );

            g2.setPaint(brillo);

            g2.fillRoundRect(
                    margen + 2,
                    margen + 2,
                    Math.max(0, progresoVisible - 4),
                    Math.max(1, altoUtil / 2),
                    radio,
                    radio
            );

            g2.setComposite(AlphaComposite.SrcOver);

            // Halo en el extremo del progreso
            int xFinal = margen + progresoVisible;

            g2.setComposite(
                    AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER,
                            0.18f
                    )
            );

            g2.setColor(new Color(54, 207, 201));

            g2.fillOval(
                    xFinal - altoUtil / 2,
                    margen,
                    altoUtil,
                    altoUtil
            );

            g2.setComposite(AlphaComposite.SrcOver);
        }

        // =========================
        // BORDE ELEGANTE
        // =========================
        g2.setColor(bordeBarra);

        g2.setStroke(
                new BasicStroke(
                        1.4f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.drawRoundRect(
                margen,
                margen,
                anchoUtil,
                altoUtil,
                radio,
                radio
        );

        // Línea interior sutil
        g2.setColor(new Color(255, 255, 255, 145));

        g2.drawRoundRect(
                margen + 2,
                margen + 2,
                anchoUtil - 4,
                altoUtil - 4,
                radio,
                radio
        );

        // =========================
        // TEXTO DE PORCENTAJE
        // =========================
        if (barra.isStringPainted()) {

            String texto = barra.getString();

            Font fuente = barra.getFont();

            if (fuente == null) {
                fuente = new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                );
            } else {
                fuente = fuente.deriveFont(
                        Font.BOLD,
                        Math.max(12f, fuente.getSize2D())
                );
            }

            g2.setFont(fuente);

            FontMetrics metricas =
                    g2.getFontMetrics();

            int x =
                    (ancho - metricas.stringWidth(texto)) / 2;

            int y =
                    (alto - metricas.getHeight()) / 2
                    + metricas.getAscent();

            // Sombra del texto
            g2.setColor(new Color(255, 255, 255, 190));

            g2.drawString(
                    texto,
                    x + 1,
                    y + 1
            );

            // Texto principal
            g2.setColor(textoOscuro);

            g2.drawString(
                    texto,
                    x,
                    y
            );
        }

        g2.dispose();
    }
}