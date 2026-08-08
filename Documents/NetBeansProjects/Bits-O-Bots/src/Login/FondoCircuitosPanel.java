/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author zoeca
 */
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FondoCircuitosPanel extends JPanel {
    
    private final Color fondoSuperior =
            new Color(255, 255, 255);

    private final Color fondoInferior =
            new Color(238, 247, 255);

    private final Color azulOscuro =
            new Color(21, 78, 160);

    private final Color azulPrincipal =
            new Color(21, 101, 216);

    private final Color azulClaro =
            new Color(47, 128, 237);

    private final Color azulSuave =
            new Color(116, 185, 255);

    private final Color turquesa =
            new Color(54, 207, 201);

    // Control de la animación
    private float faseAnimacion = 0f;

    public FondoCircuitosPanel() {
         setOpaque(true);

        // Animación suave del brillo tecnológico
        Timer temporizador = new Timer(50, evento -> {

            faseAnimacion += 0.07f;

            if (faseAnimacion >= Math.PI * 2) {
                faseAnimacion = 0f;
            }

            repaint();
        });

        temporizador.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

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

        dibujarFondoDegradado(g2, ancho, alto);
        dibujarBrillosDeFondo(g2, ancho, alto);
        dibujarEsquinasTecnologicas(g2, ancho, alto);
        dibujarCircuitos(g2, ancho, alto);
        dibujarPixeles(g2, ancho, alto);
        dibujarPuntos(g2, ancho, alto);
        dibujarHexagonos(g2, ancho, alto);
        dibujarRombos(g2, ancho, alto);
        dibujarCodigoBinario(g2, ancho, alto);
        dibujarLineasDecorativas(g2, ancho, alto);
        dibujarLucesAnimadas(g2, ancho, alto);

        g2.dispose();
    }

    // =====================================================
    // FONDO
    // =====================================================

    private void dibujarFondoDegradado(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        GradientPaint degradado =
                new GradientPaint(
                        0,
                        0,
                        fondoSuperior,
                        ancho,
                        alto,
                        fondoInferior
                );

        g2.setPaint(degradado);
        g2.fillRect(0, 0, ancho, alto);
    }

    private void dibujarBrillosDeFondo(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        0.10f
                )
        );

        GradientPaint brilloIzquierdo =
                new GradientPaint(
                        0,
                        alto / 2,
                        new Color(47, 128, 237),
                        ancho / 3,
                        alto / 2,
                        new Color(255, 255, 255, 0)
                );

        g2.setPaint(brilloIzquierdo);
        g2.fillOval(
                -250,
                alto / 2 - 350,
                700,
                700
        );

        GradientPaint brilloDerecho =
                new GradientPaint(
                        ancho,
                        alto / 2,
                        new Color(54, 207, 201),
                        ancho * 2 / 3,
                        alto / 2,
                        new Color(255, 255, 255, 0)
                );

        g2.setPaint(brilloDerecho);
        g2.fillOval(
                ancho - 450,
                alto / 2 - 300,
                700,
                700
        );

        g2.setComposite(AlphaComposite.SrcOver);
    }

    // =====================================================
    // ESQUINAS TECNOLÓGICAS
    // =====================================================

    private void dibujarEsquinasTecnologicas(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setStroke(
                new BasicStroke(
                        3f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.setColor(new Color(
                azulPrincipal.getRed(),
                azulPrincipal.getGreen(),
                azulPrincipal.getBlue(),
                185
        ));

        int largo = 60;
        int margen = 35;


        // Inferior izquierda
        g2.drawLine(
                margen,
                alto - margen,
                margen + largo,
                alto - margen
        );

        g2.drawLine(
                margen,
                alto - margen,
                margen,
                alto - margen - largo
        );

        // Inferior derecha
        g2.drawLine(
                ancho - margen,
                alto - margen,
                ancho - margen - largo,
                alto - margen
        );

        g2.drawLine(
                ancho - margen,
                alto - margen,
                ancho - margen,
                alto - margen - largo
        );

        // Pequeños puntos en las esquinas
        dibujarNodo(g2, margen + largo, margen, 10);
        dibujarNodo(g2, ancho - margen - largo, margen, 10);
        dibujarNodo(g2, margen + largo, alto - margen, 10);
        dibujarNodo(
                g2,
                ancho - margen - largo,
                alto - margen,
                10
        );
    }

    // =====================================================
    // CIRCUITOS
    // =====================================================

    private void dibujarCircuitos(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setStroke(
                new BasicStroke(
                        2.7f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        // Superior izquierda
        dibujarRutaCircuito(
                g2,
                new int[]{
                    0, 95,
                    85, 95,
                    130, 140,
                    275, 140
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    0, 70,
                    60, 70,
                    90, 100,
                    180, 100
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    35, 255,
                    100, 255,
                    135, 290,
                    245, 290
                }
        );

        // Superior derecha
        dibujarRutaCircuito(
                g2,
                new int[]{
                    ancho, 100,
                    ancho - 90, 100,
                    ancho - 135, 145,
                    ancho - 275, 145
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    ancho, 185,
                    ancho - 70, 185,
                    ancho - 115, 230,
                    ancho - 235, 230
                }
        );

        // Lateral izquierdo
        dibujarRutaCircuito(
                g2,
                new int[]{
                    0, alto / 2 - 40,
                    70, alto / 2 - 40,
                    115, alto / 2 + 5,
                    210, alto / 2 + 5
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    0, alto / 2 + 90,
                    85, alto / 2 + 90,
                    125, alto / 2 + 130,
                    215, alto / 2 + 130
                }
        );

        // Lateral derecho
        dibujarRutaCircuito(
                g2,
                new int[]{
                    ancho, alto / 2 - 50,
                    ancho - 75, alto / 2 - 50,
                    ancho - 115, alto / 2 - 10,
                    ancho - 215, alto / 2 - 10
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    ancho, alto / 2 + 100,
                    ancho - 85, alto / 2 + 100,
                    ancho - 125, alto / 2 + 140,
                    ancho - 220, alto / 2 + 140
                }
        );

        // Inferior izquierda
        dibujarRutaCircuito(
                g2,
                new int[]{
                    0, alto - 90,
                    100, alto - 90,
                    145, alto - 135,
                    290, alto - 135
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    0, alto - 180,
                    70, alto - 180,
                    110, alto - 220,
                    235, alto - 220
                }
        );

        // Inferior derecha
        dibujarRutaCircuito(
                g2,
                new int[]{
                    ancho, alto - 90,
                    ancho - 100, alto - 90,
                    ancho - 145, alto - 135,
                    ancho - 290, alto - 135
                }
        );

        dibujarRutaCircuito(
                g2,
                new int[]{
                    ancho, alto - 185,
                    ancho - 70, alto - 185,
                    ancho - 110, alto - 225,
                    ancho - 240, alto - 225
                }
        );
    }

    private void dibujarRutaCircuito(
            Graphics2D g2,
            int[] puntos
    ) {

        g2.setColor(new Color(
                azulPrincipal.getRed(),
                azulPrincipal.getGreen(),
                azulPrincipal.getBlue(),
                190
        ));

        for (int i = 0; i < puntos.length - 2; i += 2) {

            g2.drawLine(
                    puntos[i],
                    puntos[i + 1],
                    puntos[i + 2],
                    puntos[i + 3]
            );
        }

        // Dibuja nodos en uniones y extremos
        for (int i = 2; i < puntos.length; i += 2) {

            dibujarNodo(
                    g2,
                    puntos[i],
                    puntos[i + 1],
                    7
            );
        }
    }

    private void dibujarNodo(
            Graphics2D g2,
            int x,
            int y,
            int tamano
    ) {

        // Círculo exterior azul
        g2.setColor(new Color(
                azulOscuro.getRed(),
                azulOscuro.getGreen(),
                azulOscuro.getBlue(),
                220
        ));

        g2.fillOval(
                x - tamano / 2,
                y - tamano / 2,
                tamano,
                tamano
        );

        // Centro claro más pequeño
        int centro = Math.max(3, tamano / 3);

        g2.setColor(new Color(225, 242, 255));

        g2.fillOval(
                x - centro / 2,
                y - centro / 2,
                centro,
                centro
        );
    }

    // =====================================================
    // PÍXELES
    // =====================================================

    private void dibujarPixeles(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setColor(new Color(
                azulClaro.getRed(),
                azulClaro.getGreen(),
                azulClaro.getBlue(),
                145
        ));

        int[][] pixeles = {
            {310, 75, 14},
            {345, 105, 10},
            {285, 125, 8},

            {ancho - 325, 75, 14},
            {ancho - 355, 110, 10},
            {ancho - 285, 135, 8},

            {265, alto - 120, 12},
            {315, alto - 90, 9},

            {ancho - 265, alto - 130, 14},
            {ancho - 315, alto - 95, 9},

            {ancho / 2 - 410, 80, 10},
            {ancho / 2 + 400, 105, 12}
        };

        for (int[] pixel : pixeles) {

            g2.fillRoundRect(
                    pixel[0],
                    pixel[1],
                    pixel[2],
                    pixel[2],
                    3,
                    3
            );
        }
    }

    // =====================================================
    // PUNTOS
    // =====================================================

    private void dibujarPuntos(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setColor(new Color(
                azulClaro.getRed(),
                azulClaro.getGreen(),
                azulClaro.getBlue(),
                160
        ));

        int[][] puntos = {
            {330, 195},
            {ancho - 330, 200},

            {275, alto - 280},
            {ancho - 285, alto - 275},

            {95, alto / 2 - 145},
            {ancho - 95, alto / 2 - 145},

            {350, alto / 2 + 230},
            {ancho - 350, alto / 2 + 215}
        };

        for (int[] punto : puntos) {

            g2.fillOval(
                    punto[0] - 5,
                    punto[1] - 5,
                    10,
                    10
            );
        }
    }

    // =====================================================
    // HEXÁGONOS
    // =====================================================

    private void dibujarHexagonos(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setColor(new Color(
                turquesa.getRed(),
                turquesa.getGreen(),
                turquesa.getBlue(),
                135
        ));

        g2.setStroke(new BasicStroke(2f));

        dibujarHexagono(g2, 340, alto - 190, 24);
        dibujarHexagono(g2, 395, alto - 145, 14);

        dibujarHexagono(
                g2,
                ancho - 340,
                alto - 195,
                26
        );

        dibujarHexagono(
                g2,
                ancho - 395,
                alto - 145,
                15
        );

        dibujarHexagono(
                g2,
                ancho - 295,
                265,
                18
        );

        dibujarHexagono(
                g2,
                295,
                345,
                16
        );
    }

    private void dibujarHexagono(
            Graphics2D g2,
            int centroX,
            int centroY,
            int radio
    ) {

        Polygon hexagono = new Polygon();

        for (int i = 0; i < 6; i++) {

            double angulo =
                    Math.toRadians(60 * i - 30);

            int x =
                    centroX
                    + (int) (radio * Math.cos(angulo));

            int y =
                    centroY
                    + (int) (radio * Math.sin(angulo));

            hexagono.addPoint(x, y);
        }

        g2.drawPolygon(hexagono);
    }

    // =====================================================
    // ROMBOS
    // =====================================================

    private void dibujarRombos(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setColor(new Color(
                azulClaro.getRed(),
                azulClaro.getGreen(),
                azulClaro.getBlue(),
                145
        ));

        g2.setStroke(new BasicStroke(1.8f));

        dibujarRombo(g2, 390, 165, 14);
        dibujarRombo(g2, ancho - 390, 175, 15);

        dibujarRombo(
                g2,
                ancho / 2 - 470,
                alto - 95,
                13
        );

        dibujarRombo(
                g2,
                ancho / 2 + 470,
                alto - 105,
                13
        );
    }

    private void dibujarRombo(
            Graphics2D g2,
            int x,
            int y,
            int tamano
    ) {

        Polygon rombo = new Polygon();

        rombo.addPoint(x, y - tamano);
        rombo.addPoint(x + tamano, y);
        rombo.addPoint(x, y + tamano);
        rombo.addPoint(x - tamano, y);

        g2.drawPolygon(rombo);
    }

    // =====================================================
    // CÓDIGO BINARIO
    // =====================================================

    private void dibujarCodigoBinario(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setFont(new Font(
                Font.MONOSPACED,
                Font.BOLD,
                13
        ));

        g2.setColor(new Color(
                azulOscuro.getRed(),
                azulOscuro.getGreen(),
                azulOscuro.getBlue(),
                75
        ));

        g2.drawString(
                "0101 1010 0110",
                75,
                alto - 285
        );

        g2.drawString(
                "1100 0101 1011",
                ancho - 220,
                alto - 295
        );

        g2.drawString(
                "BITS",
                305,
                55
        );

        g2.drawString(
                "BOTS",
                ancho - 430,
                55
        );
    }

    // =====================================================
    // LÍNEAS DECORATIVAS
    // =====================================================

    private void dibujarLineasDecorativas(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        g2.setStroke(new BasicStroke(2f));

        g2.setColor(new Color(
                azulClaro.getRed(),
                azulClaro.getGreen(),
                azulClaro.getBlue(),
                135
        ));

        int centro = ancho / 2;

        // Líneas alrededor del título
        g2.drawLine(
                centro - 360,
                200,
                centro - 270,
                200
        );

        g2.drawLine(
                centro + 270,
                200,
                centro + 360,
                200
        );

        dibujarNodo(g2, centro - 360, 200, 7);
        dibujarNodo(g2, centro + 360, 200, 7);

        // Línea inferior central con cortes
        g2.drawLine(
                centro - 240,
                alto - 40,
                centro - 70,
                alto - 40
        );

        g2.drawLine(
                centro + 70,
                alto - 40,
                centro + 240,
                alto - 40
        );

        // Pequeños cortes diagonales
        g2.drawLine(
                centro - 70,
                alto - 40,
                centro - 45,
                alto - 65
        );

        g2.drawLine(
                centro + 45,
                alto - 65,
                centro + 70,
                alto - 40
        );
    }

    // =====================================================
    // ANIMACIÓN
    // =====================================================

    private void dibujarLucesAnimadas(
            Graphics2D g2,
            int ancho,
            int alto
    ) {

        float brillo =
                (float) ((Math.sin(faseAnimacion) + 1) / 2);

        int alpha = 80 + (int) (brillo * 150);

        Color colorBrillante =
                new Color(
                        0,
                        170,
                        255,
                        Math.min(alpha, 230)
                );

        g2.setColor(colorBrillante);

        int tamano = 10 + (int) (brillo * 7);

        // Luces que pulsan
        dibujarLuz(
                g2,
                275,
                140,
                tamano
        );

        dibujarLuz(
                g2,
                ancho - 275,
                145,
                tamano
        );

        dibujarLuz(
                g2,
                290,
                alto - 135,
                tamano
        );

        dibujarLuz(
                g2,
                ancho - 290,
                alto - 135,
                tamano
        );

        dibujarLuz(
                g2,
                ancho - 215,
                alto / 2 - 10,
                tamano
        );
    }

    private void dibujarLuz(
            Graphics2D g2,
            int x,
            int y,
            int tamano
    ) {

        // Halo exterior
        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        0.20f
                )
        );

        g2.fillOval(
                x - tamano,
                y - tamano,
                tamano * 2,
                tamano * 2
        );

        // Centro brillante
        g2.setComposite(AlphaComposite.SrcOver);

        g2.fillOval(
                x - 4,
                y - 4,
                8,
                8
        );
    }
}
