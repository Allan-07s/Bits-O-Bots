/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
/**
 *
 * @author alvar
 */

public class TarjetaMenu extends JPanel {

    private String titulo = "QUIZ VOCACIONAL";
    private String descripcion = "Descubre qué área va contigo";

    private ImageIcon icono;

    private Color colorPrincipal = new Color(30, 95, 225);
    private Color colorHover = new Color(235, 246, 255);

    private boolean mouseEncima = false;
    private Runnable accion;

    public TarjetaMenu() {
        configurarComponente();
    }

    public TarjetaMenu(
            String titulo,
            String descripcion,
            String rutaIcono,
            Color colorPrincipal
    ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.colorPrincipal = colorPrincipal;

        cargarIcono(rutaIcono);
        configurarComponente();
    }

    private void configurarComponente() {
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(580, 155));
        setMinimumSize(new Dimension(450, 135));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEncima = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseEncima = false;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (accion != null) {
                    accion.run();
                }
            }
        });
    }

    private void cargarIcono(String rutaIcono) {
        try {
            java.net.URL url = getClass().getResource(rutaIcono);

            if (url != null) {
                icono = new ImageIcon(url);
            } else {
                System.err.println(
                        "No se encontró el icono: " + rutaIcono
                );
            }
        } catch (Exception e) {
            System.err.println(
                    "Error al cargar el icono: " + e.getMessage()
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int desplazamiento = mouseEncima ? -3 : 0;

        pintarSombra(g2, desplazamiento);
        pintarTarjeta(g2, desplazamiento);
        pintarIcono(g2, desplazamiento);
        pintarTextos(g2, desplazamiento);
        pintarFlecha(g2, desplazamiento);
        pintarDecoracion(g2, desplazamiento);

        g2.dispose();

        
    }

    private void pintarSombra(Graphics2D g2, int desplazamiento) {
        g2.setColor(new Color(20, 70, 130, 35));

        g2.fill(new RoundRectangle2D.Double(
                12,
                15 + desplazamiento,
                getWidth() - 24,
                getHeight() - 25,
                38,
                38
        ));
    }

    private void pintarTarjeta(Graphics2D g2, int desplazamiento) {
        Color fondo = mouseEncima
                ? colorHover
                : new Color(255, 255, 255, 245);

        g2.setColor(fondo);

        g2.fill(new RoundRectangle2D.Double(
                7,
                7 + desplazamiento,
                getWidth() - 20,
                getHeight() - 25,
                38,
                38
        ));

        g2.setStroke(new BasicStroke(mouseEncima ? 3f : 2f));
        g2.setColor(new Color(
                colorPrincipal.getRed(),
                colorPrincipal.getGreen(),
                colorPrincipal.getBlue(),
                mouseEncima ? 220 : 145
        ));

        g2.draw(new RoundRectangle2D.Double(
                7,
                7 + desplazamiento,
                getWidth() - 20,
                getHeight() - 25,
                38,
                38
        ));
    }

    private void pintarIcono(Graphics2D g2, int desplazamiento) {
        int tamaño = Math.min(115, getHeight() - 50);
        int x = 28;
        int y = (getHeight() - tamaño) / 2 + desplazamiento - 4;

        GradientPaint degradado = new GradientPaint(
                x,
                y,
                colorPrincipal.brighter(),
                x + tamaño,
                y + tamaño,
                colorPrincipal.darker()
        );

        g2.setPaint(degradado);

        g2.fill(new RoundRectangle2D.Double(
                x,
                y,
                tamaño,
                tamaño,
                30,
                30
        ));

        if (icono != null) {
            Image imagen = icono.getImage().getScaledInstance(
                    tamaño - 38,
                    tamaño - 38,
                    Image.SCALE_SMOOTH
            );

            int imagenX = x + 19;
            int imagenY = y + 19;

            g2.drawImage(
                    imagen,
                    imagenX,
                    imagenY,
                    this
            );
        }
    }

    private void pintarTextos(Graphics2D g2, int desplazamiento) {
        int xTexto = 175;

        g2.setColor(colorPrincipal.darker());
        g2.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                getWidth() < 500 ? 23 : 28
        ));

        dibujarTituloVariasLineas(
                g2,
                titulo,
                xTexto,
                63 + desplazamiento,
                getWidth() - xTexto - 80
        );

        g2.setColor(new Color(35, 55, 90));
        g2.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                getWidth() < 500 ? 14 : 16
        ));

        g2.drawString(
                descripcion,
                xTexto,
                getHeight() - 43 + desplazamiento
        );
    }

    private void dibujarTituloVariasLineas(
            Graphics2D g2,
            String texto,
            int x,
            int y,
            int anchoDisponible
    ) {
        FontMetrics fm = g2.getFontMetrics();

        if (fm.stringWidth(texto) <= anchoDisponible) {
            g2.drawString(texto, x, y + 20);
            return;
        }

        String[] palabras = texto.split(" ");

        String linea1 = "";
        String linea2 = "";

        for (String palabra : palabras) {
            String prueba = linea1.isEmpty()
                    ? palabra
                    : linea1 + " " + palabra;

            if (fm.stringWidth(prueba) <= anchoDisponible) {
                linea1 = prueba;
            } else {
                linea2 = linea2.isEmpty()
                        ? palabra
                        : linea2 + " " + palabra;
            }
        }

        g2.drawString(linea1, x, y);
        g2.drawString(linea2, x, y + 37);
    }

    private void pintarFlecha(Graphics2D g2, int desplazamiento) {
        int tamaño = 53;
        int x = getWidth() - 88;
        int y = (getHeight() - tamaño) / 2 + desplazamiento - 3;

        g2.setColor(colorPrincipal);

        g2.fillOval(
                x,
                y,
                tamaño,
                tamaño
        );

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(
                4f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        int centroX = x + tamaño / 2;
        int centroY = y + tamaño / 2;

        g2.drawLine(
                centroX - 5,
                centroY - 10,
                centroX + 5,
                centroY
        );

        g2.drawLine(
                centroX + 5,
                centroY,
                centroX - 5,
                centroY + 10
        );
    }

    private void pintarDecoracion(Graphics2D g2, int desplazamiento) {
        g2.setColor(colorPrincipal);
        g2.setStroke(new BasicStroke(3f));

        int xFinal = getWidth() - 18;
        int yFinal = getHeight() - 34 + desplazamiento;

        g2.drawLine(
                xFinal - 45,
                yFinal,
                xFinal,
                yFinal
        );

        g2.drawLine(
                xFinal,
                yFinal,
                xFinal,
                yFinal - 28
        );
    }

    public void setAccion(Runnable accion) {
        this.accion = accion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        repaint();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        repaint();
    }

    public void setColorPrincipal(Color colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
        repaint();
    }

    public void setRutaIcono(String rutaIcono) {
        cargarIcono(rutaIcono);
        repaint();
    }
}

