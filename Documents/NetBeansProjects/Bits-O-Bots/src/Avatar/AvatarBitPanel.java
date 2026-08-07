/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Avatar;

/**
 *
 * @author zoeca
 */
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AvatarBitPanel extends JPanel {

    private static final int FPS = 60;
    private static final int FRAME_MS = 1000 / FPS;

    // Cambia SOLO esta ruta si guardas las imágenes en otra carpeta.
    private static final String BASE_PATH = "/assets/avatar/";

    // Imágenes
    private final BufferedImage caraNormal;
    private final BufferedImage caraParpado;
    private final BufferedImage manoIzquierda;
    private final BufferedImage manoDerecha;
    private final BufferedImage piernas;

    // Animación
    private final Timer timer;
    private final Random random = new Random();
    private long inicioNanos;
    private long ultimoFrameNanos;
    private double tiempo;
    private boolean running;

    // Parpadeo
    private boolean parpadeando;
    private double tiempoParpado;
    private double proximoParpadeo;
    private double blinkMin = 1.3;
    private double blinkMax = 3.0;
    private double blinkDuration = 0.16;

    // Movimiento de manos
    private boolean saludoActivo = true;
    private double intensidadSaludo = 1.0;

    // Tamaño / posición
    private double avatarScale = 1.0;
    private int offsetX = 0;
    private int offsetY = 0;
    private JPanel containerPanel;

    // Reacciones
    private double celebracionRestante = 0.0;
    private double tristezaRestante = 0.0;

    public AvatarBitPanel() {
        setOpaque(false);
        setPreferredSize(new Dimension(350, 350));

        caraNormal = loadRequired(BASE_PATH + "Avatar-Bit.png");
        caraParpado = loadRequired(BASE_PATH + "Avatar-Bit (1).png");
        manoIzquierda = loadRequired(BASE_PATH + "Avatar-Bit (3).png");
        manoDerecha = loadRequired(BASE_PATH + "Avatar-Bit (4).png");
        piernas = loadRequired(BASE_PATH + "Avatar-Bit (5).png");

        proximoParpadeo = randomBlinkDelay();

        timer = new Timer(FRAME_MS, this::updateAnimation);
        timer.setCoalesce(true);
    }

    private BufferedImage loadRequired(String path) {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException(
                        "No se encontró la imagen: " + path
                        + "\nRevisa que esté dentro de src/assets/avatar/"
                );
            }

            BufferedImage img = ImageIO.read(in);
            if (img == null) {
                throw new IllegalStateException("Imagen inválida: " + path);
            }
            return img;

        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo cargar: " + path, ex);
        }
    }

    // =========================================================
    // INSTALACIÓN / TAMAÑO
    // =========================================================

    /**
     * Mete el avatar en un JPanel y ajusta AMBOS al tamaño indicado.
     */
    public void instalarEn(JPanel contenedor, int ancho, int alto) {
        if (contenedor == null) {
            throw new IllegalArgumentException("El contenedor no puede ser null.");
        }

        containerPanel = contenedor;
        contenedor.removeAll();
        contenedor.setOpaque(false);
        contenedor.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        setAvatarSize(ancho, alto);
        contenedor.add(this);

        contenedor.revalidate();
        contenedor.repaint();
    }

    /**
     * Cambia el tamaño del AvatarBitPanel y también del JPanel contenedor.
     */
    public void setAvatarSize(int ancho, int alto) {
        if (ancho <= 0 || alto <= 0) {
            throw new IllegalArgumentException("El tamaño debe ser mayor que cero.");
        }

        Dimension d = new Dimension(ancho, alto);
        setPreferredSize(d);
        setMinimumSize(d);
        setSize(d);

        if (containerPanel != null) {
            containerPanel.setPreferredSize(d);
            containerPanel.setMinimumSize(d);
            containerPanel.setSize(d);
            containerPanel.revalidate();
            containerPanel.repaint();
        }

        revalidate();
        repaint();
    }

    /**
     * Escala solamente el dibujo dentro del panel.
     * 0.70 = pequeño, 1.0 = normal, 1.25 = grande.
     */
    public void setAvatarScale(double scale) {
        if (scale <= 0) {
            throw new IllegalArgumentException("La escala debe ser mayor que cero.");
        }
        avatarScale = scale;
        repaint();
    }

    /**
     * Mueve el avatar dentro del panel.
     * +X derecha, -X izquierda, +Y abajo, -Y arriba.
     */
    public void setAvatarPosition(int x, int y) {
        offsetX = x;
        offsetY = y;
        repaint();
    }

    // =========================================================
    // CONTROL DE ANIMACIÓN
    // =========================================================

    public void startAnimation() {
        if (timer.isRunning()) {
            return;
        }

        inicioNanos = System.nanoTime();
        ultimoFrameNanos = inicioNanos;
        running = true;
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
        running = false;
        repaint();
    }

    public boolean isAnimationRunning() {
        return running;
    }

    // =========================================================
    // PARPADEO
    // =========================================================

    /**
     * Ejemplo: setBlinkFrequency(0.8, 1.8)
     * hará que parpadee bastante seguido.
     */
    public void setBlinkFrequency(double minimoSegundos, double maximoSegundos) {
        if (minimoSegundos <= 0 || maximoSegundos < minimoSegundos) {
            throw new IllegalArgumentException("Rango de parpadeo inválido.");
        }

        blinkMin = minimoSegundos;
        blinkMax = maximoSegundos;
        proximoParpadeo = randomBlinkDelay();
    }

    public void setBlinkDuration(double segundos) {
        if (segundos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que cero.");
        }
        blinkDuration = segundos;
    }

    public void parpadear() {
        parpadeando = true;
        tiempoParpado = blinkDuration;
        proximoParpadeo = randomBlinkDelay();
        repaint();
    }

    // =========================================================
    // MANOS / REACCIONES
    // =========================================================

    public void saludar() {
        saludoActivo = true;
        intensidadSaludo = 1.0;
    }

    public void pararSaludo() {
        saludoActivo = false;
    }

    /** Intensidad aproximada entre 0.2 y 2.0. */
    public void setIntensidadSaludo(double intensidad) {
        intensidadSaludo = Math.max(0.1, Math.min(2.5, intensidad));
    }

    /** Pequeño salto/rebote durante unos segundos. */
    public void celebrar() {
        celebracionRestante = 2.0;
        tristezaRestante = 0.0;
    }

    /** Inclina y baja ligeramente el personaje. */
    public void perder() {
        tristezaRestante = 2.0;
        celebracionRestante = 0.0;
    }

    // Alias cómodos
    public void ganar() {
        celebrar();
    }

    public void correcto() {
        celebracionRestante = 1.2;
        tristezaRestante = 0.0;
    }

    // =========================================================
    // ACTUALIZACIÓN
    // =========================================================

    private void updateAnimation(ActionEvent e) {
        long ahora = System.nanoTime();
        double delta = (ahora - ultimoFrameNanos) / 1_000_000_000.0;
        ultimoFrameNanos = ahora;

        delta = Math.min(delta, 0.05);
        tiempo = (ahora - inicioNanos) / 1_000_000_000.0;

        updateBlink(delta);

        if (celebracionRestante > 0) {
            celebracionRestante -= delta;
        }
        if (tristezaRestante > 0) {
            tristezaRestante -= delta;
        }

        repaint();
    }

    private void updateBlink(double delta) {
        if (parpadeando) {
            tiempoParpado -= delta;
            if (tiempoParpado <= 0) {
                parpadeando = false;
            }
            return;
        }

        proximoParpadeo -= delta;
        if (proximoParpadeo <= 0) {
            parpadeando = true;
            tiempoParpado = blinkDuration;
            proximoParpadeo = randomBlinkDelay();
        }
    }

    private double randomBlinkDelay() {
        return blinkMin + random.nextDouble() * (blinkMax - blinkMin);
    }

    // =========================================================
    // DIBUJO
    // =========================================================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int ancho = getWidth();
            int alto = getHeight();
            if (ancho <= 0 || alto <= 0) {
                return;
            }

            int baseW = caraNormal.getWidth();
            int baseH = caraNormal.getHeight();

            double scaleX = (double) ancho / baseW;
            double scaleY = (double) alto / baseH;
            double scale = Math.min(scaleX, scaleY) * avatarScale * 0.97;

            // Idle: respiración/flotación muy suave.
            double flotacion = Math.sin(tiempo * 1.7) * 7.0;
            double respiracion = 1.0 + Math.sin(tiempo * 1.35) * 0.004;
            double inclinacion = Math.sin(tiempo * 0.8) * Math.toRadians(0.7);

            // Reacción de victoria.
            double rebote = 0.0;
            if (celebracionRestante > 0) {
                rebote = -Math.abs(Math.sin(tiempo * 9.0)) * 35.0;
                inclinacion += Math.sin(tiempo * 10.0) * Math.toRadians(1.3);
            }

            // Reacción de derrota.
            if (tristezaRestante > 0) {
                flotacion += 22.0;
                inclinacion -= Math.toRadians(3.0);
            }

            double x = (ancho - baseW * scale) / 2.0 + offsetX;
            double y = (alto - baseH * scale) / 2.0 + offsetY + flotacion + rebote;

            AffineTransform mundo = new AffineTransform();
            mundo.translate(
                    x + (baseW / 2.0) * scale,
                    y + (baseH / 2.0) * scale
            );
            mundo.rotate(inclinacion);
            mundo.scale(scale * respiracion, scale * respiracion);
            mundo.translate(-baseW / 2.0, -baseH / 2.0);
            g2.transform(mundo);

            pintarAvatarPorCapas(g2, baseW, baseH);

        } finally {
            g2.dispose();
        }
    }

    private void pintarAvatarPorCapas(Graphics2D g2, int baseW, int baseH) {
        // 1) Piernas detrás del monitor. Balanceo suave.
        double legBob = Math.sin(tiempo * 2.0) * 7.0;
        double legAngle = Math.sin(tiempo * 1.35) * Math.toRadians(1.0);
        drawLayer(g2, piernas, 0, legBob,
                baseW * 0.59, baseH * 0.67, legAngle);

        // 2) Monitor/cara.
        BufferedImage cara = parpadeando ? caraParpado : caraNormal;
        g2.drawImage(cara, 0, 0, null);

        // 3) Mano izquierda: pequeño saludo/flotación.
        double leftAngle = Math.sin(tiempo * 2.2) * Math.toRadians(4.0);
        double leftY = Math.sin(tiempo * 1.8) * 5.0;
        drawLayer(g2, manoIzquierda, 0, leftY,
                baseW * 0.225, baseH * 0.44, leftAngle);

        // 4) Mano derecha: saludo más visible.
        double rightAngle;
        double rightY;

        if (saludoActivo) {
            rightAngle = Math.sin(tiempo * 4.4)
                    * Math.toRadians(10.0 * intensidadSaludo);
            rightY = Math.sin(tiempo * 4.4) * 6.0;
        } else {
            rightAngle = Math.sin(tiempo * 1.5) * Math.toRadians(2.0);
            rightY = Math.sin(tiempo * 1.6) * 3.0;
        }

        drawLayer(g2, manoDerecha, 0, rightY,
                baseW * 0.86, baseH * 0.49, rightAngle);
    }

    private void drawLayer(Graphics2D g2,
            BufferedImage image,
            double x,
            double y,
            double pivotX,
            double pivotY,
            double angle) {

        Graphics2D layer = (Graphics2D) g2.create();
        try {
            layer.setComposite(AlphaComposite.SrcOver);
            layer.rotate(angle, pivotX, pivotY);
            layer.drawImage(image, (int) x, (int) y, null);
        } finally {
            layer.dispose();
        }
    }
}
