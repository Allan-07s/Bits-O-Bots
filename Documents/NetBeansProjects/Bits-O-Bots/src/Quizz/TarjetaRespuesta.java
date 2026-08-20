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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author alvar
 */

public class TarjetaRespuesta extends JPanel {

    private boolean hover = false;
    private boolean seleccionada = false;

    private String letra = "A";
    private String titulo = "Respuesta";

    private Color colorPrincipal = new Color(47, 128, 237);
    private Color colorFinal = new Color(91, 155, 255);

    private float intensidadHover = 0f;
    private Timer animacion;

    public TarjetaRespuesta() {

        setOpaque(false);
        setBorder(null);

        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hover = true;
                iniciarAnimacion();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hover = false;
                iniciarAnimacion();
            }
        });
    }

    public void configurar(
            String letra,
            String titulo,
            Color colorPrincipal,
            Color colorFinal
    ) {

        this.letra = letra;
        this.titulo = titulo;
        this.colorPrincipal = colorPrincipal;
        this.colorFinal = colorFinal;

        repaint();
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        repaint();
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionada = seleccionado;
        iniciarAnimacion();
        repaint();
    }

    public boolean isSeleccionado() {
        return seleccionada;
    }

    private void iniciarAnimacion() {

        if (animacion != null && animacion.isRunning()) {
            animacion.stop();
        }

        animacion = new Timer(16, evento -> {

            float objetivo =
                    (hover || seleccionada) ? 1f : 0f;

            if (intensidadHover < objetivo) {

                intensidadHover += 0.10f;

                if (intensidadHover > objetivo) {
                    intensidadHover = objetivo;
                }

            } else if (intensidadHover > objetivo) {

                intensidadHover -= 0.10f;

                if (intensidadHover < objetivo) {
                    intensidadHover = objetivo;
                }
            }

            repaint();

            if (intensidadHover == objetivo) {
                animacion.stop();
            }
        });

        animacion.start();
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

        int elevacion = (int) (intensidadHover * 4);

        int x = 10;
        int y = 10 - elevacion;
        int w = ancho - 20;
        int h = alto - 22;

        int radio = 34;

        // Sombra exterior
        int alphaSombra =
                28 + (int) (intensidadHover * 35);

        g2.setColor(new Color(
                24,
                73,
                145,
                alphaSombra
        ));

        g2.fillRoundRect(
                x + 5,
                y + 9,
                w,
                h,
                radio,
                radio
        );

        // Halo exterior cuando está seleccionada
        if (seleccionada) {

            g2.setComposite(
                    AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER,
                            0.18f
                    )
            );

            g2.setColor(colorPrincipal);

            g2.fillRoundRect(
                    x - 5,
                    y - 5,
                    w + 10,
                    h + 10,
                    radio + 8,
                    radio + 8
            );

            g2.setComposite(AlphaComposite.SrcOver);
        }

        // Fondo de la tarjeta
        GradientPaint fondo = new GradientPaint(
                0,
                y,
                Color.WHITE,
                0,
                y + h,
                seleccionada
                        ? new Color(232, 244, 255)
                        : new Color(246, 250, 255)
        );

        g2.setPaint(fondo);

        g2.fillRoundRect(
                x,
                y,
                w,
                h,
                radio,
                radio
        );

        // Brillo superior
        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        0.45f
                )
        );

        GradientPaint brillo = new GradientPaint(
                0,
                y,
                Color.WHITE,
                0,
                y + 100,
                new Color(255, 255, 255, 0)
        );

        g2.setPaint(brillo);

        g2.fillRoundRect(
                x + 3,
                y + 3,
                w - 6,
                110,
                radio - 4,
                radio - 4
        );

        g2.setComposite(AlphaComposite.SrcOver);

        // Borde
        Color borde;

        if (seleccionada) {

            borde = colorPrincipal;

        } else if (hover) {

            borde = new Color(
                    colorPrincipal.getRed(),
                    colorPrincipal.getGreen(),
                    colorPrincipal.getBlue(),
                    190
            );

        } else {

            borde = new Color(194, 213, 237);
        }

        g2.setColor(borde);

        g2.setStroke(new BasicStroke(
                seleccionada ? 3.2f : hover ? 2.4f : 1.6f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        g2.drawRoundRect(
                x,
                y,
                w,
                h,
                radio,
                radio
        );

        g2.dispose();
    }

    @Override
    protected void paintChildren(Graphics g) {

        super.paintChildren(g);

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

        int elevacion = (int) (intensidadHover * 4);

        int x = 10;
        int y = 10 - elevacion;
        int w = ancho - 20;
        int h = alto - 22;

        // Insignia A/B
        GradientPaint insignia = new GradientPaint(
                x,
                y,
                colorPrincipal,
                x + 58,
                y + 58,
                colorFinal
        );

        g2.setPaint(insignia);

        g2.fillRoundRect(
                x,
                y,
                58,
                58,
                34,
                34
        );

            g2.fillRect(x + 20, y, 38, 58);
            g2.fillRect(x, y + 20, 58, 38);

            // Brillo sutil sobre la insignia
            g2.setColor(new Color(255, 255, 255, 70));
            g2.fillRoundRect(
                    x + 4,
                    y + 4,
                    50,
                    24,
                    14,
                    14
            );

            // Texto de la letra centrado en el badge (A / B)
            g2.setFont(new Font("SansSerif", Font.BOLD, 28));
            g2.setColor(Color.WHITE);

            FontMetrics fmLetra = g2.getFontMetrics();
            int xLetra = x + (58 - fmLetra.stringWidth(letra)) / 2;
            int yLetra = y + (58 + fmLetra.getAscent() - fmLetra.getDescent()) / 2 - 2;

            g2.drawString(letra, xLetra, yLetra);

               g2.drawString(
                       letra,
                       xLetra,
                       yLetra
         );

        // Franja inferior
        int altoFranja = 75;
        int yFranja = y + h - altoFranja;

        GradientPaint franja = new GradientPaint(
                x,
                yFranja,
                colorPrincipal,
                x + w,
                yFranja,
                colorFinal
        );

        g2.setPaint(franja);

        g2.fillRoundRect(
                x,
                yFranja,
                w,
                altoFranja,
                25,
                25
        );

        // Corrige las esquinas superiores de la franja
        g2.fillRect(
                x,
                yFranja,
                w,
                25
        );

        // Texto inferior
        g2.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                16
        ));

        g2.setColor(Color.WHITE);

        dibujarTextoCentrado(
                g2,
                titulo,
                x + 25,
                yFranja + 8,
                w - 50,
                altoFranja - 18
        );

        // Check de selección
        if (seleccionada) {

            int checkX = x + w - 48;
            int checkY = y + 22;

            g2.setColor(colorPrincipal);

            g2.fillOval(
                    checkX - 7,
                    checkY - 7,
                    36,
                    36
            );

            g2.setColor(Color.WHITE);

            g2.setStroke(new BasicStroke(
                    3.3f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND
            ));

            g2.drawLine(
                    checkX + 2,
                    checkY + 10,
                    checkX + 9,
                    checkY + 17
            );

            g2.drawLine(
                    checkX + 9,
                    checkY + 17,
                    checkX + 21,
                    checkY + 3
            );
        }

        g2.dispose();
    }

    private void dibujarTextoCentrado(
            Graphics2D g2,
            String texto,
            int x,
            int y,
            int ancho,
            int alto
    ) {
        if (texto == null || texto.trim().isEmpty()) {
            return;
        }

        FontMetrics fm = g2.getFontMetrics();
        String[] palabras = texto.split("\\s+");

        List<String> lineas = new ArrayList<>();
        StringBuilder lineaActual = new StringBuilder();

        for (String palabra : palabras) {
            // Probamos cómo quedaría la línea actual si le añadimos la palabra
            String prueba = lineaActual.length() == 0 
                    ? palabra 
                    : lineaActual + " " + palabra;

            // Si sobrepasa el ancho y ya tenemos algo escrito, guardamos la línea actual e iniciamos una nueva
            if (fm.stringWidth(prueba) > ancho && lineaActual.length() > 0) {
                lineas.add(lineaActual.toString());
                lineaActual = new StringBuilder(palabra);
            } else {
                if (lineaActual.length() > 0) {
                    lineaActual.append(" ");
                }
                lineaActual.append(palabra);
            }
        }

        // Agregamos la última línea pendiente
        if (lineaActual.length() > 0) {
            lineas.add(lineaActual.toString());
        }

        // Si quieres limitar estrictamente a 3 líneas como máximo:
        // if (lineas.size() > 3) lineas = lineas.subList(0, 3);

        // Cálculo dinámico para centrar verticalmente todas las líneas
        int cantidadLineas = lineas.size();
        int alturaTotal = cantidadLineas * fm.getHeight();
        int baseY = y + (alto - alturaTotal) / 2 + fm.getAscent();

        // Dibujamos cada línea calculando su posición Y
        for (int i = 0; i < lineas.size(); i++) {
            dibujarLineaCentrada(
                    g2,
                    lineas.get(i),
                    x,
                    ancho,
                    baseY + (i * fm.getHeight())
            );
        }
    }

    private void dibujarLineaCentrada(
            Graphics2D g2,
            String texto,
            int x,
            int ancho,
            int y
    ) {

        FontMetrics fm = g2.getFontMetrics();

        int posicionX =
                x + (ancho - fm.stringWidth(texto)) / 2;

        g2.drawString(
                texto,
                posicionX,
                y
        );
    }
}
   