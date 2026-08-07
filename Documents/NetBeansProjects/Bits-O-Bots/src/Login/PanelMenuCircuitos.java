/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alvar
 */
public class PanelMenuCircuitos extends JPanel {

    // =========================
    // COLORES
    // =========================

    private final Color azulElectrico = new Color(0, 110, 255, 210);
    private final Color azulClaro = new Color(40, 175, 255, 170);
    private final Color turquesa = new Color(0, 225, 220, 170);
    private final Color azulSuave = new Color(100, 180, 255, 95);

    // =========================
    // ANIMACIÓN
    // =========================

    private boolean brilloActivo = true;
    private float pulso = 0f;
    private boolean subiendo = true;

    private final Timer timer;

    public PanelMenuCircuitos() {

        setOpaque(true);
        setBackground(Color.WHITE);

        timer = new Timer(100, e -> {

            // Parpadeo suave
            brilloActivo = !brilloActivo;

            // Pulso progresivo
            if (subiendo) {
                pulso += 0.025f;

                if (pulso >= 1f) {
                    pulso = 1f;
                    subiendo = false;
                }

            } else {

                pulso -= 0.025f;

                if (pulso <= 0f) {
                    pulso = 0f;
                    subiendo = true;
                }
            }

            repaint();
        });

        timer.start();
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
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        pintarFondo(g2);

        pintarCircuitosIzquierda(g2);
        pintarCircuitosDerecha(g2);

        pintarCircuitosPequeños(g2);

        pintarHexagonos(g2);

        pintarBinario(g2);

        pintarParticulas(g2);

        pintarNodosLuminosos(g2);

        pintarEsquinas(g2);

        g2.dispose();
    }

    // =========================================================
    // FONDO
    // =========================================================

    private void pintarFondo(Graphics2D g2) {

        GradientPaint fondo = new GradientPaint(
                0,
                0,
                Color.WHITE,
                0,
                getHeight(),
                new Color(235, 247, 255)
        );

        g2.setPaint(fondo);

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // Resplandor central muy suave
        RadialGradientPaint luzCentral
                = new RadialGradientPaint(
                        new Point(
                                getWidth() / 2,
                                getHeight() / 2
                        ),
                        Math.max(
                                getWidth(),
                                getHeight()
                        ) / 2f,
                        new float[]{0f, 1f},
                        new Color[]{
                            new Color(80, 190, 255, 18),
                            new Color(255, 255, 255, 0)
                        }
                );

        g2.setPaint(luzCentral);

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );
    }

    // =========================================================
    // CIRCUITOS IZQUIERDA
    // =========================================================

    private void pintarCircuitosIzquierda(Graphics2D g2) {

        int h = getHeight();

        dibujarCircuito(
                g2,
                azulElectrico,
                new int[]{
                    0, 55, 95, 175, 225
                },
                new int[]{
                    120, 120, 165, 165, 145
                }
        );

        dibujarCircuito(
                g2,
                azulClaro,
                new int[]{
                    0, 45, 85, 150
                },
                new int[]{
                    230, 230, 270, 270
                }
        );

        dibujarCircuito(
                g2,
                turquesa,
                new int[]{
                    0, 60, 110, 175
                },
                new int[]{
                    h / 2,
                    h / 2,
                    h / 2 + 45,
                    h / 2 + 45
                }
        );

        dibujarCircuito(
                g2,
                azulElectrico,
                new int[]{
                    0, 70, 110, 190
                },
                new int[]{
                    h - 180,
                    h - 180,
                    h - 135,
                    h - 135
                }
        );
    }

    // =========================================================
    // CIRCUITOS DERECHA
    // =========================================================

    private void pintarCircuitosDerecha(Graphics2D g2) {

        int w = getWidth();
        int h = getHeight();

        dibujarCircuito(
                g2,
                azulElectrico,
                new int[]{
                    w,
                    w - 55,
                    w - 100,
                    w - 180,
                    w - 230
                },
                new int[]{
                    115,
                    115,
                    160,
                    160,
                    140
                }
        );

        dibujarCircuito(
                g2,
                azulClaro,
                new int[]{
                    w,
                    w - 45,
                    w - 90,
                    w - 155
                },
                new int[]{
                    230,
                    230,
                    270,
                    270
                }
        );

        dibujarCircuito(
                g2,
                turquesa,
                new int[]{
                    w,
                    w - 65,
                    w - 115,
                    w - 180
                },
                new int[]{
                    h / 2,
                    h / 2,
                    h / 2 + 45,
                    h / 2 + 45
                }
        );

        dibujarCircuito(
                g2,
                azulElectrico,
                new int[]{
                    w,
                    w - 60,
                    w - 105,
                    w - 175
                },
                new int[]{
                    h - 175,
                    h - 175,
                    h - 130,
                    h - 130
                }
        );
    }

    // =========================================================
    // CIRCUITOS PEQUEÑOS DECORATIVOS
    // =========================================================

    private void pintarCircuitosPequeños(Graphics2D g2) {

        g2.setStroke(
                new BasicStroke(
                        1.6f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.setColor(
                new Color(0, 150, 255, 110)
        );

        // Superior izquierda
        g2.drawLine(230, 90, 285, 90);
        g2.drawLine(285, 90, 305, 110);
        g2.drawLine(305, 110, 350, 110);

        // Superior derecha
        int w = getWidth();

        g2.drawLine(w - 350, 90, w - 300, 90);
        g2.drawLine(w - 300, 90, w - 280, 110);
        g2.drawLine(w - 280, 110, w - 230, 110);

        // Inferiores
        g2.drawLine(
                230,
                getHeight() - 90,
                290,
                getHeight() - 90
        );

        g2.drawLine(
                290,
                getHeight() - 90,
                310,
                getHeight() - 110
        );

        g2.drawLine(
                310,
                getHeight() - 110,
                360,
                getHeight() - 110
        );
    }

    // =========================================================
    // CIRCUITO GENÉRICO
    // =========================================================

    private void dibujarCircuito(
            Graphics2D g2,
            Color color,
            int[] puntosX,
            int[] puntosY
    ) {

        float grosor = brilloActivo
                ? 2.8f
                : 2.1f;

        g2.setStroke(
                new BasicStroke(
                        grosor,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.setColor(color);

        for (int i = 0; i < puntosX.length - 1; i++) {

            g2.drawLine(
                    puntosX[i],
                    puntosY[i],
                    puntosX[i + 1],
                    puntosY[i + 1]
            );
        }

        for (int i = 0; i < puntosX.length; i++) {

            int tamañoNodo = brilloActivo
                    ? 9
                    : 7;

            g2.fillOval(
                    puntosX[i] - tamañoNodo / 2,
                    puntosY[i] - tamañoNodo / 2,
                    tamañoNodo,
                    tamañoNodo
            );
        }

        int ultimo = puntosX.length - 1;

        g2.setColor(Color.WHITE);

        g2.fillOval(
                puntosX[ultimo] - 3,
                puntosY[ultimo] - 3,
                6,
                6
        );
    }

    // =========================================================
    // HEXÁGONOS
    // =========================================================

    private void pintarHexagonos(Graphics2D g2) {

        int w = getWidth();
        int h = getHeight();

        dibujarHexagono(
                g2,
                210,
                170,
                16,
                new Color(0, 130, 255, 95)
        );

        dibujarHexagono(
                g2,
                125,
                h - 235,
                22,
                new Color(0, 220, 220, 90)
        );

        dibujarHexagono(
                g2,
                w - 190,
                185,
                18,
                new Color(0, 165, 255, 90)
        );

        dibujarHexagono(
                g2,
                w - 130,
                h - 230,
                24,
                new Color(0, 220, 220, 90)
        );

        dibujarHexagono(
                g2,
                w - 280,
                h / 2,
                12,
                new Color(70, 125, 255, 75)
        );
    }

    private void dibujarHexagono(
            Graphics2D g2,
            int centroX,
            int centroY,
            int radio,
            Color color
    ) {

        Path2D hexagono = new Path2D.Double();

        for (int i = 0; i < 6; i++) {

            double angulo
                    = Math.toRadians(
                            60 * i - 30
                    );

            double x
                    = centroX
                    + radio * Math.cos(angulo);

            double y
                    = centroY
                    + radio * Math.sin(angulo);

            if (i == 0) {
                hexagono.moveTo(x, y);
            } else {
                hexagono.lineTo(x, y);
            }
        }

        hexagono.closePath();

        g2.setStroke(
                new BasicStroke(2f)
        );

        g2.setColor(color);

        g2.draw(hexagono);
    }

    // =========================================================
    // CÓDIGO BINARIO
    // =========================================================

    private void pintarBinario(Graphics2D g2) {

    int w = getWidth();
    int h = getHeight();

    // ==============================
    // OPACIDAD ANIMADA
    // ==============================

    int alphaFuerte = (int) (70 + pulso * 120);
    int alphaSuave = (int) (25 + pulso * 55);

    alphaFuerte = Math.min(alphaFuerte, 190);
    alphaSuave = Math.min(alphaSuave, 90);

    // ==============================
    // BINARIO IZQUIERDA
    // ==============================

    g2.setFont(new Font(
            "Monospaced",
            Font.BOLD,
            13
    ));

    g2.setColor(new Color(
            0, 115, 255, alphaFuerte
    ));

    g2.drawString(
            "10110100 011010",
            85,
            325
    );

    g2.setColor(new Color(
            0, 175, 235, alphaSuave
    ));

    g2.drawString(
            "Emi",
            115,
            350
    );

    g2.setColor(new Color(
            0, 115, 255, alphaFuerte
    ));

    g2.drawString(
            "10110100 011010",
            70,
            375
    );

    // ==============================
    // BINARIO DERECHA
    // ==============================

    g2.setColor(new Color(
            0, 180, 225, alphaSuave
    ));

    g2.drawString(
            "Zowi",
            w - 210,
            330
    );

    g2.setColor(new Color(
            0, 120, 255, alphaFuerte
    ));

    g2.drawString(
            "011010 110101",
            w - 240,
            355
    );

    g2.setColor(new Color(
            0, 200, 220, alphaSuave
    ));

    g2.drawString(
            "Kens",
            w - 190,
            380
    );

    // ==============================
    // PARTE INFERIOR IZQUIERDA
    // ==============================

    g2.setColor(new Color(
            0, 165, 235, alphaSuave
    ));

    g2.drawString(
            "Tacy",
            95,
            h - 100
    );

    g2.setColor(new Color(
            0, 115, 255, alphaFuerte
    ));

    g2.drawString(
            "1010 0011 0101",
            125,
            h - 78
    );

    g2.setColor(new Color(
            0, 200, 225, alphaSuave
    ));

    g2.drawString(
            "Jafet",
            80,
            h - 56
    );

    // ==============================
    // PARTE INFERIOR DERECHA
    // ==============================

    g2.setColor(new Color(
            0, 120, 255, alphaFuerte
    ));

    g2.drawString(
            "1101 0010 0110",
            w - 220,
            h - 105
    );

    g2.setColor(new Color(
            0, 200, 220, alphaSuave
    ));

    g2.drawString(
            "0010 1110 1001",
            w - 250,
            h - 82
    );

    g2.setColor(new Color(
            0, 115, 255, alphaFuerte
    ));

    g2.drawString(
            "1011 0100 0111",
            w - 195,
            h - 59
    );

    // ==============================
    // BINARIO PEQUEÑO SUPERIOR
    // ==============================

    g2.setFont(new Font(
            "Monospaced",
            Font.PLAIN,
            9
    ));

    g2.setColor(new Color(
            0, 130, 255, alphaSuave
    ));

    g2.drawString(
            "01010101",
            270,
            75
    );

    g2.drawString(
            "10110010",
            310,
            95
    );

    g2.setColor(new Color(
            0, 200, 220, alphaFuerte
    ));

    g2.drawString(
            "00110101",
            w - 340,
            75
    );

    g2.drawString(
            "11001010",
            w - 300,
            95
    );

    // ==============================
    // TEXTOS TECNOLÓGICOS
    // ==============================

    g2.setFont(new Font(
            "Monospaced",
            Font.BOLD,
            11
    ));

    g2.setColor(new Color(
            0, 165, 225, alphaFuerte
    ));

    g2.drawString(
            "BITS // SYSTEM ONLINE",
            180,
            h - 30
    );

    g2.setColor(new Color(
            0, 125, 255, alphaSuave
    ));

    g2.drawString(
            "BOT // READY",
            w - 270,
            65
    );

    g2.setColor(new Color(
            0, 190, 220, alphaFuerte
    ));

    g2.drawString(
            "MEMORY // ACTIVE",
            60,
            200
    );

    g2.setColor(new Color(
            0, 115, 255, alphaSuave
    ));

    g2.drawString(
            "Allan",
            w - 180,
            215
    );
}
   
    // =========================================================
    // PARTÍCULAS
    // =========================================================

    private void pintarParticulas(Graphics2D g2) {

        int[][] particulas = {

            {135, 80, 9},
            {195, 125, 6},
            {260, 65, 5},
            {115, 380, 7},
            {245, 510, 8},

            {getWidth() - 140, 85, 8},
            {getWidth() - 215, 145, 6},
            {getWidth() - 105, 385, 8},
            {getWidth() - 250, 530, 7}
        };

        for (int i = 0; i < particulas.length; i++) {

            Color color;

            if (i % 3 == 0) {

                color = azulElectrico;

            } else if (i % 3 == 1) {

                color = azulClaro;

            } else {

                color = turquesa;
            }

            int alpha = (int) (
                    100
                    + pulso * 120
            );

            g2.setColor(
                    new Color(
                            color.getRed(),
                            color.getGreen(),
                            color.getBlue(),
                            Math.min(alpha, 220)
                    )
            );

            int x = particulas[i][0];
            int y = particulas[i][1];
            int tamaño = particulas[i][2];

            g2.fill(
                    new RoundRectangle2D.Double(
                            x,
                            y,
                            tamaño,
                            tamaño,
                            3,
                            3
                    )
            );
        }
    }

    // =========================================================
    // NODOS QUE PULSAN
    // =========================================================

    private void pintarNodosLuminosos(Graphics2D g2) {

        int alpha = (int) (
                90
                + pulso * 140
        );

        Color brillo = new Color(
                0,
                210,
                255,
                Math.min(alpha, 230)
        );

        int tamaño = (int) (
                7
                + pulso * 5
        );

        g2.setColor(brillo);

        g2.fillOval(
                275 - tamaño / 2,
                140 - tamaño / 2,
                tamaño,
                tamaño
        );

        g2.fillOval(
                getWidth() - 280 - tamaño / 2,
                205 - tamaño / 2,
                tamaño,
                tamaño
        );

        g2.fillOval(
                195 - tamaño / 2,
                getHeight() - 205 - tamaño / 2,
                tamaño,
                tamaño
        );

        g2.fillOval(
                getWidth() - 210 - tamaño / 2,
                getHeight() - 175 - tamaño / 2,
                tamaño,
                tamaño
        );
    }

    // =========================================================
    // ESQUINAS
    // =========================================================

    private void pintarEsquinas(Graphics2D g2) {

        int margen = 24;
        int tamaño = 60;

        g2.setStroke(
                new BasicStroke(
                        3.2f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.setColor(
                new Color(
                        0,
                        105,
                        255,
                        220
                )
        );

        // SUPERIOR IZQUIERDA
        g2.drawLine(
                margen,
                margen,
                margen + tamaño,
                margen
        );

        g2.drawLine(
                margen,
                margen,
                margen,
                margen + tamaño
        );

        // pequeño segundo borde
        g2.setColor(
                new Color(
                        0,
                        210,
                        255,
                        150
                )
        );

        g2.drawLine(
                margen + 12,
                margen + 12,
                margen + 42,
                margen + 12
        );

        g2.drawLine(
                margen + 12,
                margen + 12,
                margen + 12,
                margen + 42
        );

        // SUPERIOR DERECHA
        int w = getWidth();

        g2.setColor(
                new Color(
                        0,
                        105,
                        255,
                        220
                )
        );

        g2.drawLine(
                w - margen,
                margen,
                w - margen - tamaño,
                margen
        );

        g2.drawLine(
                w - margen,
                margen,
                w - margen,
                margen + tamaño
        );

        g2.setColor(
                new Color(
                        0,
                        210,
                        255,
                        150
                )
        );

        g2.drawLine(
                w - margen - 12,
                margen + 12,
                w - margen - 42,
                margen + 12
        );

        g2.drawLine(
                w - margen - 12,
                margen + 12,
                w - margen - 12,
                margen + 42
        );

        // INFERIOR IZQUIERDA
        int h = getHeight();

        g2.setColor(
                new Color(
                        0,
                        105,
                        255,
                        220
                )
        );

        g2.drawLine(
                margen,
                h - margen,
                margen + tamaño,
                h - margen
        );

        g2.drawLine(
                margen,
                h - margen,
                margen,
                h - margen - tamaño
        );

        // INFERIOR DERECHA
        g2.drawLine(
                w - margen,
                h - margen,
                w - margen - tamaño,
                h - margen
        );

        g2.drawLine(
                w - margen,
                h - margen,
                w - margen,
                h - margen - tamaño
        );
    }
}
