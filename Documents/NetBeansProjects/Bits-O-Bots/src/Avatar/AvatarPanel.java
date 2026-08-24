/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Avatar;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author zoeca
 */
public class AvatarPanel extends JPanel{
     public enum Expression {
        HAPPY,
        NEUTRAL,
        CORRECT,
        WIN,
        LOSE
    }

    /*
     * CONFIGURACIÓN DE LA ANIMACIÓN
     */
    private static final int FPS = 60;
    private static final int FRAME_MS = 1000 / FPS;

    /*
     * IMÁGENES DEL AVATAR
     */
    private final BufferedImage bodyHappy;
    private final BufferedImage fullHappy;
    private final BufferedImage fullBlink;
    private final BufferedImage fullNeutral;

    private final BufferedImage leftArm;
    private final BufferedImage rightArm;
    private final BufferedImage leftLeg;
    private final BufferedImage rightLeg;

    /*
     * IMÁGENES DE EXPRESIONES
     */
    private final Map<Expression, BufferedImage> expressionImages
            = new EnumMap<>(Expression.class);

    /*
     * CONTROL DE ANIMACIÓN
     */
    private final Timer animationTimer;
    private final Random random = new Random();

    private Expression expression = Expression.HAPPY;

    private long animationStartNanos;
    private long lastFrameNanos;
    private double elapsedSeconds;

    /*
     * CONTROL DEL PARPADEO
     */
    private boolean blinking;
    private double blinkRemaining;
    private double nextBlinkIn;

    /*
     * CONTROL DEL MOVIMIENTO
     */
    private boolean waving = true;
    private boolean running;

    /*
     * REACCIÓN TEMPORAL
     */
    private double reactionRemaining;
    private Expression expressionBeforeReaction = Expression.HAPPY;

    /*
     * TAMAÑO DEL COMPONENTE
     */
    private int avatarWidth = 350;
    private int avatarHeight = 350;

    /*
     * ESCALA DEL DIBUJO
     *
     * 1.0 = tamaño normal
     * 0.8 = más pequeño
     * 1.3 = más grande
     */
    private double avatarScale = 1.0;

    /*
     * POSICIÓN DEL AVATAR DENTRO DEL PANEL
     */
    private int offsetX = 0;
    private int offsetY = 0;

    /*
     * PANEL EXTERNO DONDE SE COLOCA EL AVATAR
     */
    private JPanel containerPanel;

    private String mensajeCompleto = "";
    private String mensajeActual = "";
    private Timer timerEscritura;
    private int indiceTexto = 0;
    private boolean mostrandoMensaje = false;

    // Controles de animación del globo
    private float alphaGlobo = 0f;            // Opacidad de 0.0f a 1.0f
    private long tiempoInicioMensaje = 2000;     // Cuándo se mostró el mensaje
    private int duracionMensajeMs = 6000;     // Duración total visible
    private final int FADE_DURATION = 500;   // Tiempo de fade in/out en ms

    public void decirMensaje(String texto, int velocidadMs) {
        // 1. Detener el timer previo inmediatamente para evitar ejecuciones concurrentes
        if (timerEscritura != null) {
            timerEscritura.stop();
        }

        // 2. Reiniciar todas las variables de estado
        this.mensajeCompleto = (texto != null) ? texto : "";
        this.mensajeActual = "";
        this.indiceTexto = 0;
        this.mostrandoMensaje = true;
        this.tiempoInicioMensaje = System.currentTimeMillis();
        this.alphaGlobo = 0f;

        // Redibujar el estado inicial (pantalla limpia antes de escribir)
        repaint();

        // Si el texto está vacío, no creamos el timer de escritura
        if (this.mensajeCompleto.isEmpty()) {
            return;
        }

        // 3. Crear e iniciar el nuevo Timer de máquina de escribir
        timerEscritura = new Timer(velocidadMs, e -> {
            if (indiceTexto < mensajeCompleto.length()) {
                mensajeActual += mensajeCompleto.charAt(indiceTexto);
                indiceTexto++;
                repaint();
            } else {
                if (timerEscritura != null) {
                    timerEscritura.stop();
                }
            }
        });

        // Iniciar de forma segura en el hilo de eventos de Swing (EDT)
        timerEscritura.start();
    }

    /**
     * Oculta el bocadillo de texto y limpia el estado del timer.
     */
    public void ocultarMensaje() {
        if (timerEscritura != null) {
            timerEscritura.stop();
        }
        this.mostrandoMensaje = false;
        this.mensajeActual = "";
        this.mensajeCompleto = "";
        this.indiceTexto = 0;
        repaint();
    }
    
    /*
     * CONSTRUCTOR
     */
    public AvatarPanel() {

        setOpaque(false);

        setPreferredSize(
                new Dimension(avatarWidth, avatarHeight)
        );

        setMinimumSize(
                new Dimension(avatarWidth, avatarHeight)
        );

        /*
         * CARGA DE IMÁGENES
         *
         * Las imágenes deben encontrarse en:
         *
         * src/assets/avatar/
         */
        bodyHappy = loadRequired(
                "/assets/avatar/cuerpo_feliz.png"
        );

        fullHappy = loadRequired(
                "/assets/avatar/avatar_completo_feliz.png"
        );

        fullBlink = loadRequired(
                "/assets/avatar/avatar_completo_parpado.png"
        );

        fullNeutral = loadRequired(
                "/assets/avatar/avatar_completo_neutral.png"
        );

        leftArm = loadRequired(
                "/assets/avatar/brazo_izquierdo.png"
        );

        rightArm = loadRequired(
                "/assets/avatar/brazo_derecho.png"
        );

        leftLeg = loadRequired(
                "/assets/avatar/pierna_izquierda.png"
        );

        rightLeg = loadRequired(
                "/assets/avatar/pierna_derecha.png"
        );

        /*
         * ASIGNACIÓN DE EXPRESIONES
         */
        expressionImages.put(
                Expression.HAPPY,
                fullHappy
        );

        expressionImages.put(
                Expression.NEUTRAL,
                fullNeutral
        );

        expressionImages.put(
                Expression.CORRECT,
                fullHappy
        );

        expressionImages.put(
                Expression.WIN,
                fullHappy
        );

        expressionImages.put(
                Expression.LOSE,
                fullNeutral
        );

        nextBlinkIn = randomBlinkDelay();

        /*
         * TIMER PRINCIPAL
         */
        animationTimer = new Timer(
                FRAME_MS,
                this::updateAnimation
        );

        animationTimer.setCoalesce(true);
    }

    /*
     * CARGAR UNA IMAGEN DESDE SRC
     */
    private BufferedImage loadRequired(String resourcePath) {

        try (InputStream input
                = getClass().getResourceAsStream(resourcePath)) {

            if (input == null) {
                throw new IllegalStateException(
                        "No se encontró la imagen: "
                        + resourcePath
                );
            }

            BufferedImage image = ImageIO.read(input);

            if (image == null) {
                throw new IllegalStateException(
                        "El archivo no es una imagen válida: "
                        + resourcePath
                );
            }

            return image;

        } catch (IOException ex) {

            throw new IllegalStateException(
                    "No se pudo cargar la imagen: "
                    + resourcePath,
                    ex
            );
        }
    }

    /*
     * INSTALAR EL AVATAR DENTRO DE UN JPANEL
     *
     * Ejemplo:
     *
     * avatar.instalarEn(panelAvatar, 300, 300);
     */
    public void instalarEn(
            JPanel contenedor,
            int ancho,
            int alto) {

        if (contenedor == null) {
            throw new IllegalArgumentException(
                    "El panel contenedor no puede ser null."
            );
        }

        this.containerPanel = contenedor;

        /*
         * Elimina componentes anteriores del panel.
         */
        contenedor.removeAll();

        /*
         * FlowLayout respeta el tamaño preferido
         * del AvatarPanel.
         */
        contenedor.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        0,
                        0
                )
        );

        /*
         * Ajusta el avatar y el contenedor.
         */
        setAvatarSize(ancho, alto);

        contenedor.add(this);

        contenedor.revalidate();
        contenedor.repaint();
    }

    /*
     * CAMBIA EL TAMAÑO DEL AVATAR Y DEL PANEL
     *
     * Ejemplos:
     *
     * avatar.setAvatarSize(200, 200);
     * avatar.setAvatarSize(400, 400);
     */
    public void setAvatarSize(
            int width,
            int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "El ancho y el alto deben ser mayores que cero."
            );
        }

        avatarWidth = width;
        avatarHeight = height;

        Dimension dimension
                = new Dimension(width, height);

        /*
         * Cambia el tamaño de este AvatarPanel.
         */
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setSize(dimension);

        /*
         * También cambia el panel externo.
         */
        if (containerPanel != null) {

            containerPanel.setPreferredSize(dimension);
            containerPanel.setMinimumSize(dimension);
            containerPanel.setSize(dimension);

            containerPanel.revalidate();
            containerPanel.repaint();
        }

        revalidate();
        repaint();
    }

    /*
     * CAMBIA SOLAMENTE LA ESCALA DEL DIBUJO
     *
     * El panel conserva su tamaño.
     *
     * 0.60 = avatar pequeño
     * 0.80 = avatar un poco pequeño
     * 1.00 = tamaño normal
     * 1.30 = avatar grande
     */
    public void setAvatarScale(double scale) {

        if (scale <= 0) {
            throw new IllegalArgumentException(
                    "La escala debe ser mayor que cero."
            );
        }

        avatarScale = scale;
        repaint();
    }

    /*
     * OBTENER LA ESCALA ACTUAL
     */
    public double getAvatarScale() {
        return avatarScale;
    }

    /*
     * MOVER EL AVATAR DENTRO DEL PANEL
     *
     * X positivo = derecha
     * X negativo = izquierda
     *
     * Y positivo = abajo
     * Y negativo = arriba
     */
    public void setAvatarPosition(
            int x,
            int y) {

        offsetX = x;
        offsetY = y;

        repaint();
    }

    /*
     * INICIAR ANIMACIÓN
     */
    public void startAnimation() {

        if (animationTimer.isRunning()) {
            return;
        }

        animationStartNanos
                = System.nanoTime();

        lastFrameNanos
                = animationStartNanos;

        running = true;

        animationTimer.start();
    }

    /*
     * DETENER ANIMACIÓN
     */
    public void stopAnimation() {

        animationTimer.stop();

        running = false;

        repaint();
    }

    /*
     * SABER SI ESTÁ ANIMADO
     */
    public boolean isAnimationRunning() {
        return running;
    }

    /*
     * ACTIVAR O DESACTIVAR EL SALUDO
     */
    public void setWaving(boolean waving) {

        this.waving = waving;

        repaint();
    }

    /*
     * CAMBIAR EXPRESIÓN PERMANENTE
     */
    public void setExpression(
            Expression newExpression) {

        if (newExpression == null) {
            throw new IllegalArgumentException(
                    "La expresión no puede ser null."
            );
        }

        expression = newExpression;

        repaint();
    }

    /*
     * MOSTRAR UNA REACCIÓN TEMPORAL
     *
     * Ejemplo:
     *
     * avatar.react(
     *     Expression.WIN,
     *     2000
     * );
     */
    public void react(
            Expression temporaryExpression,
            int milliseconds) {

        if (temporaryExpression == null) {
            return;
        }

        expressionBeforeReaction
                = expression;

        expression
                = temporaryExpression;

        reactionRemaining
                = Math.max(0, milliseconds)
                / 1000.0;

        repaint();
    }

    /*
     * PARPADEAR INMEDIATAMENTE
     */
    public void blinkNow() {

        blinking = true;

        blinkRemaining = 0.20;

        nextBlinkIn = randomBlinkDelay();

        repaint();
    }

    /*
     * ACTUALIZAR ANIMACIÓN
     */
    private void updateAnimation(
            ActionEvent event) {

        long now = System.nanoTime();

        double delta
                = (now - lastFrameNanos)
                / 1_000_000_000.0;

        lastFrameNanos = now;

        /*
         * Evita movimientos bruscos si el programa
         * se congela momentáneamente.
         */
        delta = Math.min(delta, 0.05);

        elapsedSeconds
                = (now - animationStartNanos)
                / 1_000_000_000.0;

        updateBlink(delta);

        updateReaction(delta);

        repaint();
    }

    /*
     * ACTUALIZAR PARPADEO
     */
    private void updateBlink(double delta) {

        if (blinking) {

            blinkRemaining -= delta;

            if (blinkRemaining <= 0) {
                blinking = false;
            }

            return;
        }

        nextBlinkIn -= delta;

        if (nextBlinkIn <= 0) {

            blinking = true;

            blinkRemaining = 0.13;

            nextBlinkIn = randomBlinkDelay();
        }
    }

    /*
     * ACTUALIZAR REACCIÓN TEMPORAL
     */
    private void updateReaction(double delta) {

        if (reactionRemaining <= 0) {
            return;
        }

        reactionRemaining -= delta;

        if (reactionRemaining <= 0) {

            expression
                    = expressionBeforeReaction;
        }
    }

    /*
     * TIEMPO ALEATORIO ENTRE PARPADEOS
     */
    private double randomBlinkDelay() {

        return 2.2
                + random.nextDouble() * 1.8;
    }

   /*
     * DIBUJAR AVATAR
     */
    @Override
    protected void paintComponent(
            Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D g2
                = (Graphics2D) graphics.create();

        try {

            /*
             * CALIDAD DE DIBUJO
             */
            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR
            );

            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
            );

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            /*
             * TAMAÑO REAL DEL COMPONENTE
             */
            int ancho = getWidth();
            int alto = getHeight();

            if (ancho <= 0 || alto <= 0) {
                return;
            }

            /*
             * TAMAÑO REAL DE LAS IMÁGENES
             *
             * Ya no usamos 900 ni 1200 manualmente.
             * Java detecta automáticamente el tamaño.
             */
            int baseWidth = bodyHappy.getWidth();
            int baseHeight = bodyHappy.getHeight();

            /*
             * ESCALA AUTOMÁTICA
             */
            double scaleX
                    = (double) ancho / baseWidth;

            double scaleY
                    = (double) alto / baseHeight;

            /*
             * Mantiene la proporción y aplica
             * la escala elegida.
             */
            double scale
                    = Math.min(scaleX, scaleY)
                    * avatarScale
                    * 0.95;

            /*
             * ANIMACIÓN DE RESPIRACIÓN
             */
            double breath
                    = Math.sin(
                            elapsedSeconds
                            * Math.PI
                            * 1.15
                    );

            double bodyY
                    = breath * 3.0;

            double breatheScaleY
                    = 1.0
                    + breath * 0.008;

            /*
             * MOVIMIENTO SUAVE DEL CUERPO
             */
            double headTilt
                    = Math.sin(
                            elapsedSeconds * 0.72
                    )
                    * Math.toRadians(1.2);

            /*
             * REACCIÓN AL GANAR O ACERTAR
             */
            double bounce = 0;

            if (expression == Expression.WIN
                    || expression
                    == Expression.CORRECT) {

                bounce
                        = -Math.abs(
                                Math.sin(
                                        elapsedSeconds
                                        * 7.5
                                )
                        ) * 7.0;

            } else if (
                    expression
                    == Expression.LOSE) {

                bodyY += 5.0;

                headTilt
                        -= Math.toRadians(2.2);
            }

            /*
             * CENTRAR AVATAR
             */
            double x
                    = (ancho
                    - baseWidth * scale)
                    / 2.0
                    + offsetX;

            double y
                    = (alto
                    - baseHeight * scale)
                    / 2.0
                    + offsetY
                    + bodyY
                    + bounce;

            /*
             * TRANSFORMACIÓN GENERAL
             */
            AffineTransform world
                    = new AffineTransform();

            world.translate(
                    x
                    + (baseWidth / 2.0)
                    * scale,
                    y
                    + (baseHeight / 2.0)
                    * scale
            );

            world.rotate(headTilt);

            world.scale(
                    scale,
                    scale * breatheScaleY
            );

            world.translate(
                    -baseWidth / 2.0,
                    -baseHeight / 2.0
            );

            g2.transform(world);

            /*
             * DIBUJAR SEGÚN ESTADO
             */
            if (blinking) {

                g2.drawImage(
                        fullBlink,
                        0,
                        0,
                        null
                );

            } else if (
                    expression
                    == Expression.HAPPY
                    && waving) {

                paintLayeredAvatar(g2);

            } else {

                BufferedImage image
                        = expressionImages.get(
                                expression
                        );

                g2.drawImage(
                        image != null
                                ? image
                                : fullHappy,
                        0,
                        0,
                        null
                );
            }

        } finally {

            g2.dispose();
        }

        /*
         * DIBUJAR GLOBO DE TEXTO (MENSAJE CON FADE IN/OUT Y MÁS ARRIBA)
         */
        if (mostrandoMensaje && mensajeActual != null && !mensajeActual.isEmpty()) {

            long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicioMensaje;

            // 1. Calcular el Alpha (Desvanecimiento / Aparición)
            if (tiempoTranscurrido < FADE_DURATION) {
                // Fade In (Aparición)
                alphaGlobo = (float) tiempoTranscurrido / FADE_DURATION;
            } else if (tiempoTranscurrido > (duracionMensajeMs - FADE_DURATION)) {
                // Fade Out (Desvanecimiento)
                alphaGlobo = 1.0f - ((float) (tiempoTranscurrido - (duracionMensajeMs - FADE_DURATION)) / FADE_DURATION);
            } else {
                // Totalmente visible
                alphaGlobo = 1.0f;
            }

            // Clampar valores de alpha entre 0.0 y 1.0
            alphaGlobo = Math.max(0.0f, Math.min(1.0f, alphaGlobo));

            // Si ya terminó la animación por completo, ocultar
            if (tiempoTranscurrido >= duracionMensajeMs) {
                mostrandoMensaje = false;
                alphaGlobo = 0f;
            } else {

                Graphics2D g2Globe = (Graphics2D) graphics.create();

                try {
                    // Aplicar el nivel de opacidad global a todo el globo y texto
                    g2Globe.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaGlobo));

                    g2Globe.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON
                    );
                    g2Globe.setRenderingHint(
                            RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                    );

                    Font font = new Font("SansSerif", Font.BOLD, 18);
                    g2Globe.setFont(font);

                    int anchoMaximo = Math.max(180, getWidth() - 40);
                    FontMetrics fm = g2Globe.getFontMetrics();
                    List<String> lineas = envolverTexto(mensajeActual, fm, anchoMaximo);

                    int altoLinea = fm.getHeight();
                    int altoGlobo = (lineas.size() * altoLinea) + 20;
                    int anchoGlobo = 0;

                    for (String linea : lineas) {
                        int anchoLinea = fm.stringWidth(linea);
                        if (anchoLinea > anchoGlobo) {
                            anchoGlobo = anchoLinea;
                        }
                    }
                    anchoGlobo += 35;

                    int xGlobo = (getWidth() - anchoGlobo) / 2;
                    
                    int yGlobo = 0; 

                    // Sombra (se atenúa proporcionalmente)
                    g2Globe.setColor(new Color(0, 0, 0, (int) (40 * alphaGlobo)));
                    g2Globe.fill(new RoundRectangle2D.Float(xGlobo + 3, yGlobo + 3, anchoGlobo, altoGlobo, 20, 20));

                    // Fondo
                    g2Globe.setColor(new Color(255, 255, 255, 240));
                    g2Globe.fill(new RoundRectangle2D.Float(xGlobo, yGlobo, anchoGlobo, altoGlobo, 20, 20));

                    // Borde
                    g2Globe.setColor(new Color(40, 40, 40));
                    g2Globe.setStroke(new BasicStroke(2f));
                    g2Globe.draw(new RoundRectangle2D.Float(xGlobo, yGlobo, anchoGlobo, altoGlobo, 20, 20));

                    // Pico del globo
                    int[] picosX = {xGlobo + (anchoGlobo / 2) - 10, xGlobo + (anchoGlobo / 2) + 10, xGlobo + (anchoGlobo / 2)};
                    int[] picosY = {yGlobo + altoGlobo - 1, yGlobo + altoGlobo - 1, yGlobo + altoGlobo + 12};

                    g2Globe.setColor(new Color(255, 255, 255, 240));
                    g2Globe.fillPolygon(picosX, picosY, 3);

                    g2Globe.setColor(new Color(40, 40, 40));
                    g2Globe.drawLine(picosX[0], picosY[0], picosX[2], picosY[2]);
                    g2Globe.drawLine(picosX[1], picosY[1], picosX[2], picosY[2]);

                    // Texto
                    g2Globe.setColor(new Color(30, 30, 30));
                    int textoY = yGlobo + fm.getAscent() + 10;
                    for (String linea : lineas) {
                        int textoX = xGlobo + (anchoGlobo - fm.stringWidth(linea)) / 2;
                        g2Globe.drawString(linea, textoX, textoY);
                        textoY += altoLinea;
                    }

                } finally {
                    g2Globe.dispose();
                }
            }
        }
    }
    
    // Auxiliar para dividir el texto en varias líneas si supera el ancho permitido
    private List<String> envolverTexto(String texto, FontMetrics fm, int anchoMax) {
        List<String> lineas = new ArrayList<>();
        String[] palabras = texto.split(" ");
        StringBuilder lineaActual = new StringBuilder();

        for (String palabra : palabras) {
            if (fm.stringWidth(lineaActual + palabra) < anchoMax) {
                if (lineaActual.length() > 0) lineaActual.append(" ");
                lineaActual.append(palabra);
            } else {
                lineas.add(lineaActual.toString());
                lineaActual = new StringBuilder(palabra);
            }
        }
        if (lineaActual.length() > 0) {
            lineas.add(lineaActual.toString());
        }
        return lineas;
    }

    /*
     * DIBUJAR AVATAR POR PARTES
     */
    private void paintLayeredAvatar(
            Graphics2D g2) {

        /*
         * TAMAÑO REAL DE LAS IMÁGENES
         */
        double imageWidth
                = bodyHappy.getWidth();

        double imageHeight
                = bodyHappy.getHeight();

        /*
         * PUNTOS DE ROTACIÓN CALCULADOS
         * PROPORCIONALMENTE.
         */
        double centerX
                = imageWidth * 0.50;

        double legPivotY
                = imageHeight * 0.56;

        double leftShoulderX
                = imageWidth * 0.47;

        double rightShoulderX
                = imageWidth * 0.57;

        double leftShoulderY
                = imageHeight * 0.43;

        double rightShoulderY
                = imageHeight * 0.38;

        /*
         * PIERNA IZQUIERDA
         */
        drawRotatedLayer(
                g2,
                leftLeg,
                0,
                0,
                centerX,
                legPivotY,
                Math.sin(
                        elapsedSeconds * 1.8
                ) * Math.toRadians(1.3)
        );

        /*
         * PIERNA DERECHA
         */
        drawRotatedLayer(
                g2,
                rightLeg,
                0,
                0,
                centerX,
                legPivotY,
                -Math.sin(
                        elapsedSeconds * 1.8
                ) * Math.toRadians(1.3)
        );

        /*
         * BRAZO IZQUIERDO
         */
        drawRotatedLayer(
                g2,
                leftArm,
                0,
                0,
                leftShoulderX,
                leftShoulderY,
                Math.sin(
                        elapsedSeconds * 1.5
                ) * Math.toRadians(2.0)
        );

        /*
         * BRAZO DERECHO SALUDANDO (Allan was here)
         */
        double waveAngle
                = Math.sin(
                        elapsedSeconds * 4.2
                ) * Math.toRadians(9.0);
        
        drawRotatedLayer(
                g2,
                rightArm,
                0,
                0,
                rightShoulderX,
                rightShoulderY,
                waveAngle
        );
        
        /*
         * CUERPO PRINCIPAL
         */
        g2.drawImage(
                bodyHappy,
                0,
                0,
                null
        );
    }

    /*
     * DIBUJAR UNA PARTE ROTADA
     */
    private void drawRotatedLayer(
            Graphics2D g2,
            BufferedImage image,
            double x,
            double y,
            double pivotX,
            double pivotY,
            double angle) {

        Graphics2D layer = (Graphics2D) g2.create();
        
        try {
            layer.setComposite(
                    AlphaComposite.SrcOver
            );

            layer.rotate(
                    angle,
                    pivotX,
                    pivotY
            );

            layer.drawImage(
                    image,
                    (int) x,
                    (int) y,
                    null
            );
        } finally {
            layer.dispose();
        }
    }
}