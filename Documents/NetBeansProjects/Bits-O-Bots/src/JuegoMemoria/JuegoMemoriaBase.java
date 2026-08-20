package JuegoMemoria;

import Tipografias.Fuentes;
import Avatar.AvatarPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;

import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.Box;

public abstract class JuegoMemoriaBase extends JFrame {

    // =====================================================
    // MEDIDAS DE LAS CARTAS
    // =====================================================

    private static final int ANCHO_CARTA = 120;
    private static final int ALTO_CARTA = 176;
    private static final int ESPACIO = 18;
    private static final int MARGEN = 22;

    // =====================================================
    // TIEMPOS
    // =====================================================

    private static final int TIEMPO_OBSERVACION = 1000;
    private static final int PAUSA_FINAL = 500;
    private static final int TIEMPO_INCORRECTAS = 850;

    // =====================================================
    // ANIMACIONES
    // =====================================================

    private static final int PASOS_MOVIMIENTO = 17;
    private static final int VELOCIDAD_MOVIMIENTO = 2;
    private static final int PAUSA_ENTRE_MOVIMIENTOS = 24;

    // =====================================================
    // COLORES DE LAS CARTAS
    // =====================================================

    /*
     * Ahora el borde normal utiliza
     * la identidad turquesa del proyecto.
     */
    private static final Color COLOR_BORDE
            = ColoresBitsOBots.BORDE_TURQUESA;

    /*
     * Verde cuando una pareja fue encontrada.
     */
    private static final Color COLOR_PAREJA
            = new Color(45, 205, 123);

    // =====================================================
    // DATOS DEL NIVEL
    // =====================================================

    private final ProgresoJuego progreso;
    private final int numeroNivel;
    private final int cantidadCartas;
    private final int cantidadParejas;
    private final int filas;
    private final int columnas;
    private final int totalIntercambios;

    /*
     * TEMPORAL.
     *
     * Antes de entregar el proyecto cambiar a:
     *
     * false
     */
    private static final boolean MODO_PRUEBA = true;

    // =====================================================
    // DATOS DE LAS CARTAS
    // =====================================================

    private final ImageIcon[] imagenesFrente;
    private final String[] nombresCartas;
    private final String[] rutasImagenes;
    private final String[] categoriasCartas;

    private ImageIcon reversoInformatica;
    private ImageIcon reversoRobotica;

    private BotonCarta[] cartas;
    private int[] valores;
    private boolean[] encontradas;

    private int primeraCarta = -1;
    private int segundaCarta = -1;

    // =====================================================
    // ESTADO DEL JUEGO
    // =====================================================

    private int parejasEncontradas;
    private int movimientos;
    private int puntosNivel;
    private int segundos;

    // =====================================================
    // PANEL DERECHO
    // =====================================================

    private static final int ANCHO_PANEL_DERECHO = 360;
    private static final int ALTO_PANEL_DERECHO = 500;

    private static final int ANCHO_AVATAR = 350;
    private static final int ALTO_AVATAR = 370;

    private static final int ANCHO_BOTON_DERECHO
            = ANCHO_PANEL_DERECHO - 60;

    private boolean bloqueado = true;
    private boolean partidaFinalizada;

    private int numeroPartida;

    private final Random aleatorio
            = new Random();

    // =====================================================
    // COMPONENTES
    // =====================================================

    private JPanel panelTablero;
    private JPanel panelAvatar;

    private JLabel lblMovimientos;
    private JLabel lblPuntosNivel;
    private JLabel lblPuntosTotal;
    private JLabel lblTiempo;
    private JLabel lblMensaje;

    private AvatarPanel avatar;

    private JPanel panelCartas;
    private JPanel contenedorCartas;

    private Timer cronometro;
    private Timer timerTexto;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    protected JuegoMemoriaBase(
            ProgresoJuego progreso,
            int numeroNivel,
            int cantidadCartas,
            int filas,
            int columnas,
            int totalIntercambios,
            String[] nombresCartas,
            String[] rutasImagenes,
            String[] categoriasCartas
    ) {

        this.progreso = progreso;
        this.numeroNivel = numeroNivel;
        this.cantidadCartas = cantidadCartas;
        this.cantidadParejas = cantidadCartas / 2;
        this.filas = filas;
        this.columnas = columnas;
        this.totalIntercambios = totalIntercambios;

        this.nombresCartas = nombresCartas;
        this.rutasImagenes = rutasImagenes;
        this.categoriasCartas = categoriasCartas;

        this.imagenesFrente
                = new ImageIcon[
                        this.cantidadParejas
                ];

        configurarVentana();
        construirInterfaz();
        cargarImagenes();

        GestorMusica.reproducirFondo(
                "/audio/musica_juego.wav"
        );

        iniciarNivelAutomaticamente();
    }

    // =====================================================
    // CONFIGURACIÓN DE LA VENTANA
    // =====================================================

    private void configurarVentana() {

        setTitle(
                "Memory Tech - Nivel "
                + numeroNivel
        );

        setUndecorated(true);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );

        setResizable(true);
    }

    // =====================================================
    // VALOR DE LAS ESTADÍSTICAS
    // =====================================================

    private JLabel crearLabelValor(
            String texto
    ) {

        JLabel label
                = new JLabel(
                        texto,
                        SwingConstants.CENTER
                );

        label.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        22f
                )
        );

        /*
         * Antes era blanco.
         * Ahora azul oscuro para el diseño claro.
         */
        label.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        return label;
    }

    // =====================================================
    // TARJETA DE ESTADÍSTICA
    // =====================================================

    private JPanel crearTarjetaEstadistica(
            String titulo,
            JLabel labelValor
    ) {

        PanelRedondeado tarjeta
                = new PanelRedondeado(
                        22,
                        new Color(
                                255,
                                255,
                                255,
                                235
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        tarjeta.setLayout(
                new BorderLayout(
                        0,
                        3
                )
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        JLabel lblTituloDato
                = new JLabel(
                        titulo,
                        SwingConstants.CENTER
                );

        lblTituloDato.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        22f
                )
        );

        lblTituloDato.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        tarjeta.add(
                lblTituloDato,
                BorderLayout.NORTH
        );

        tarjeta.add(
                labelValor,
                BorderLayout.CENTER
        );

        return tarjeta;
    }

    // =====================================================
    // ACTUALIZAR MARCADORES
    // =====================================================

    private void actualizarMarcadores() {

        lblMovimientos.setText(
                String.valueOf(
                        movimientos
                )
        );

        lblPuntosNivel.setText(
                puntosNivel
                + " pts"
        );

        int totalActual
                = progreso.getPuntosTotales()
                + puntosNivel;

        lblPuntosTotal.setText(
                totalActual
                + " pts"
        );

        lblTiempo.setText(
                formatearTiempo(
                        segundos
                )
        );
    }

    // =====================================================
    // FORMATEAR TIEMPO
    // =====================================================

    private String formatearTiempo(
            int segundosTotales
    ) {

        int minutos
                = segundosTotales / 60;

        int segundosRestantes
                = segundosTotales % 60;

        return String.format(
                "%02d:%02d",
                minutos,
                segundosRestantes
        );
    }

    // =====================================================
    // CONSTRUIR INTERFAZ
    // =====================================================

    private void construirInterfaz() {

        // =================================================
        // FONDO GENERAL
        // =================================================

        PanelDegradado fondo
                = new PanelDegradado(
                        ColoresBitsOBots.FONDO_SUPERIOR,
                        ColoresBitsOBots.FONDO_INFERIOR
                );

        fondo.setLayout(
                new BorderLayout(
                        14,
                        14
                )
        );

        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        20,
                        18,
                        20
                )
        );

        setContentPane(
                fondo
        );

        // =================================================
        // TÍTULO
        // =================================================

        JLabel lblTitulo
                = new JLabel(
                        "NIVEL "
                        + numeroNivel
                        + " · MEMORIA TECNOLÓGICA",
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        22f
                )
        );

        lblTitulo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        PanelRedondeado panelTitulo
                = new PanelRedondeado(
                        50,
                        new Color(
                                255,
                                255,
                                255,
                                235
                        ),
                        ColoresBitsOBots.BORDE_AZUL
                );

        panelTitulo.setLayout(
                new BorderLayout()
        );

        panelTitulo.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        25,
                        8,
                        25
                )
        );

        panelTitulo.add(
                lblTitulo,
                BorderLayout.CENTER
        );

        JPanel contenedorTitulo
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorTitulo.setOpaque(false);

        contenedorTitulo.add(
                panelTitulo
        );

        fondo.add(
                contenedorTitulo,
                BorderLayout.NORTH
        );

        // =================================================
        // PANEL PRINCIPAL
        // =================================================

        PanelRedondeado panelPrincipal
                = new PanelRedondeado(
                        32,
                        new Color(
                                255,
                                255,
                                255,
                                215
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        panelPrincipal.setLayout(
                new BorderLayout(
                        18,
                        18
                )
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        22,
                        20,
                        22
                )
        );

        fondo.add(
                panelPrincipal,
                BorderLayout.CENTER
        );

        // =================================================
        // PANEL VIEJO DE BOTONES
        // =================================================
        /*
         * Lo conservamos porque estaba en tu código
         * original, aunque actualmente no se muestra.
         */

        JPanel botones
                = new JPanel();

        botones.setLayout(
                new BoxLayout(
                        botones,
                        BoxLayout.Y_AXIS
                )
        );

        botones.setOpaque(false);

        BotonRedondeado btnJugar
                = new BotonRedondeado(
                        "JUGAR",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        BotonRedondeado btnSalir
                = new BotonRedondeado(
                        "SALIR",
                        ColoresBitsOBots.AZUL_PRINCIPAL,
                        ColoresBitsOBots.AZUL_HOVER
                );

        btnJugar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        btnSalir.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        btnJugar.setForeground(
                Color.WHITE
        );

        btnSalir.setForeground(
                Color.WHITE
        );

        Dimension tamanoJugar
                = new Dimension(
                        50,
                        64
                );

        Dimension tamanoSalir
                = new Dimension(
                        50,
                        56
                );

        btnJugar.setPreferredSize(
                tamanoJugar
        );

        btnJugar.setMinimumSize(
                tamanoJugar
        );

        btnJugar.setMaximumSize(
                tamanoJugar
        );

        btnSalir.setPreferredSize(
                tamanoSalir
        );

        btnSalir.setMinimumSize(
                tamanoSalir
        );

        btnSalir.setMaximumSize(
                tamanoSalir
        );

        btnJugar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnSalir.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botones.add(
                Box.createVerticalGlue()
        );

        botones.add(
                btnJugar
        );

        botones.add(
                Box.createVerticalStrut(
                        5
                )
        );

        botones.add(
                btnSalir
        );

        botones.add(
                Box.createVerticalGlue()
        );

        // =================================================
        // ESTADÍSTICAS
        // =================================================

        JPanel barraEstadisticas
                = new JPanel(
                        new GridLayout(
                                1,
                                5,
                                14,
                                0
                        )
                );

        barraEstadisticas.setOpaque(false);

        lblMovimientos
                = crearLabelValor(
                        "0"
                );

        lblPuntosNivel
                = crearLabelValor(
                        "0 pts"
                );

        lblPuntosTotal
                = crearLabelValor(
                        "0 pts"
                );

        lblTiempo
                = crearLabelValor(
                        "00:00"
                );

        barraEstadisticas.add(
                crearTarjetaEstadistica(
                        "MOVIMIENTOS",
                        lblMovimientos
                )
        );

        barraEstadisticas.add(
                crearTarjetaEstadistica(
                        "NIVEL "
                        + numeroNivel,
                        lblPuntosNivel
                )
        );

        barraEstadisticas.add(
                crearTarjetaEstadistica(
                        "TOTAL",
                        lblPuntosTotal
                )
        );

        barraEstadisticas.add(
                crearTarjetaEstadistica(
                        "TIEMPO",
                        lblTiempo
                )
        );

        // =================================================
        // BOTÓN MÚSICA
        // =================================================

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(
                        68,
                        68
                )
        );

        btnMusica.addActionListener(
                e -> {

                    GestorMusica
                            .alternarSilencio();

                    btnMusica.repaint();
                }
        );

        JPanel panelMusica
                = new JPanel(
                        new GridBagLayout()
                );

        panelMusica.setOpaque(false);

        panelMusica.add(
                btnMusica
        );

        barraEstadisticas.add(
                panelMusica
        );

        panelPrincipal.add(
                barraEstadisticas,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLERO + AVATAR
        // =================================================

        JPanel contenidoCentral
                = new JPanel(
                        new BorderLayout(
                                25,
                                0
                        )
                );

        contenidoCentral.setOpaque(false);

        // =================================================
        // TABLERO
        // =================================================

        int anchoTablero
                = MARGEN * 2
                + columnas * ANCHO_CARTA
                + (columnas - 1) * ESPACIO;

        int altoTablero
                = MARGEN * 2
                + filas * ALTO_CARTA
                + (filas - 1) * ESPACIO;

        panelCartas
                = new JPanel(null);

        panelCartas.setOpaque(false);

        panelCartas.setPreferredSize(
                new Dimension(
                        anchoTablero,
                        altoTablero
                )
        );

        panelTablero
                = panelCartas;

        contenedorCartas
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorCartas.setOpaque(false);

        contenedorCartas.add(
                panelCartas
        );

        contenidoCentral.add(
                contenedorCartas,
                BorderLayout.CENTER
        );

        // =================================================
        // PANEL DERECHO
        // =================================================

        PanelRedondeado panelDerecho
                = new PanelRedondeado(
                        28,
                        new Color(
                                255,
                                255,
                                255,
                                210
                        ),
                        new Color(
                                ColoresBitsOBots.ROBOTICA.getRed(),
                                ColoresBitsOBots.ROBOTICA.getGreen(),
                                ColoresBitsOBots.ROBOTICA.getBlue(),
                                170
                        )
                );

        Dimension tamanoPanelDerecho
                = new Dimension(
                        ANCHO_PANEL_DERECHO,
                        ALTO_PANEL_DERECHO
                );

        panelDerecho.setPreferredSize(
                tamanoPanelDerecho
        );

        panelDerecho.setMinimumSize(
                tamanoPanelDerecho
        );

        panelDerecho.setLayout(
                new BoxLayout(
                        panelDerecho,
                        BoxLayout.Y_AXIS
                )
        );

        panelDerecho.setBorder(
                BorderFactory.createEmptyBorder(
                        16,
                        18,
                        16,
                        18
                )
        );

        // =================================================
        // BURBUJA
        // =================================================

        BurbujaMensaje burbujaMensaje
                = new BurbujaMensaje(
                        new Color(
                                255,
                                255,
                                255,
                                248
                        ),
                        ColoresBitsOBots.BORDE_TURQUESA
                );

        burbujaMensaje.setTamanoPunta(
                35,
                25
        );

        burbujaMensaje.setLayout(
                new BorderLayout()
        );

        Dimension tamanoBurbuja
                = new Dimension(
                        ANCHO_PANEL_DERECHO - 50,
                        100
                );

        burbujaMensaje.setPreferredSize(
                tamanoBurbuja
        );

        burbujaMensaje.setMinimumSize(
                tamanoBurbuja
        );

        burbujaMensaje.setMaximumSize(
                tamanoBurbuja
        );

        /*
         * SIN HTML.
         *
         * Esto ayuda a que Pixel Digivolve
         * se respete mejor entre computadoras.
         */
        lblMensaje
                = new JLabel(
                        "¡Encuentra todas las parejas!",
                        SwingConstants.CENTER
                );

        lblMensaje.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        lblMensaje.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        burbujaMensaje.add(
                lblMensaje,
                BorderLayout.CENTER
        );

        burbujaMensaje.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelDerecho.add(
                burbujaMensaje
        );

        panelDerecho.add(
                Box.createVerticalStrut(
                        10
                )
        );

        // =================================================
        // AVATAR
        // =================================================

        panelAvatar
                = new JPanel(
                        new GridBagLayout()
                );

        panelAvatar.setOpaque(false);

        Dimension tamanoPanelAvatar
                = new Dimension(
                        ANCHO_PANEL_DERECHO - 10,
                        ALTO_AVATAR + 10
                );

        panelAvatar.setPreferredSize(
                tamanoPanelAvatar
        );

        panelAvatar.setMinimumSize(
                tamanoPanelAvatar
        );

        panelAvatar.setMaximumSize(
                tamanoPanelAvatar
        );

        panelAvatar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        avatar
                = new AvatarPanel();

        Dimension tamanoAvatar
                = new Dimension(
                        ANCHO_AVATAR,
                        ALTO_AVATAR
                );

        avatar.setPreferredSize(
                tamanoAvatar
        );

        avatar.setMinimumSize(
                tamanoAvatar
        );

        avatar.setMaximumSize(
                tamanoAvatar
        );

        panelAvatar.add(
                avatar
        );

        avatar.startAnimation();

        panelDerecho.add(
                Box.createVerticalGlue()
        );

        panelDerecho.add(
                panelAvatar
        );

        panelDerecho.add(
                Box.createVerticalGlue()
        );

        // =================================================
        // VOLVER AL MENÚ
        // =================================================

        BotonRedondeado btnVolver
                = new BotonRedondeado(
                        "VOLVER AL MENÚ",
                        ColoresBitsOBots.AZUL_PRINCIPAL,
                        ColoresBitsOBots.AZUL_HOVER
                );

        btnVolver.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        15f
                )
        );

        btnVolver.setForeground(
                Color.WHITE
        );

        Dimension tamanoVolver
                = new Dimension(
                        230,
                        52
                );

        btnVolver.setPreferredSize(
                tamanoVolver
        );

        btnVolver.setMinimumSize(
                tamanoVolver
        );

        btnVolver.setMaximumSize(
                tamanoVolver
        );

        btnVolver.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnVolver.addActionListener(
                e -> {

                    abandonarPartida();
                }
        );

        // =================================================
        // BOTÓN TEMPORAL DE PRUEBA
        // =================================================

        if (MODO_PRUEBA) {

            BotonRedondeado btnSaltarNivel
                    = new BotonRedondeado(
                            "SALTAR NIVEL",
                            new Color(
                                    218,
                                    133,
                                    43
                            ),
                            new Color(
                                    244,
                                    157,
                                    61
                            )
                    );

            btnSaltarNivel.setFont(
                    Fuentes.cargar(
                            "Pixel Digivolve.otf",
                            13f
                    )
            );

            btnSaltarNivel.setForeground(
                    Color.WHITE
            );

            Dimension tamanoSaltar
                    = new Dimension(
                            300,
                            50
                    );

            btnSaltarNivel.setPreferredSize(
                    tamanoSaltar
            );

            btnSaltarNivel.setMinimumSize(
                    tamanoSaltar
            );

            btnSaltarNivel.setMaximumSize(
                    tamanoSaltar
            );

            btnSaltarNivel.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            btnSaltarNivel.addActionListener(
                    e -> {

                        saltarNivelPrueba();
                    }
            );

            panelDerecho.add(
                    btnSaltarNivel
            );

            panelDerecho.add(
                    Box.createVerticalStrut(
                            14
                    )
            );
        }

        panelDerecho.add(
                btnVolver
        );

        contenidoCentral.add(
                panelDerecho,
                BorderLayout.EAST
        );

        panelPrincipal.add(
                contenidoCentral,
                BorderLayout.CENTER
        );

        fondo.revalidate();
        fondo.repaint();
    }

    // =====================================================
    // ENCABEZADO ALTERNATIVO
    // =====================================================

    private JPanel crearEncabezado() {

        JPanel superior
                = new JPanel(
                        new BorderLayout(
                                10,
                                8
                        )
                );

        superior.setOpaque(false);

        JPanel filaTitulo
                = new JPanel(
                        new BorderLayout()
                );

        filaTitulo.setOpaque(false);

        JLabel lblJugador
                = new JLabel(
                        progreso.getJugador()
                );

        lblJugador.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        15f
                )
        );

        lblJugador.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        JLabel lblNivel
                = new JLabel(
                        "NIVEL "
                        + numeroNivel
                        + "  ·  "
                        + cantidadCartas
                        + " CARTAS",
                        SwingConstants.CENTER
                );

        lblNivel.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        22f
                )
        );

        lblNivel.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        JPanel acciones
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                7,
                                0
                        )
                );

        acciones.setOpaque(false);

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(
                        51,
                        51
                )
        );

        btnMusica.addActionListener(
                e -> {

                    GestorMusica
                            .alternarSilencio();

                    btnMusica.repaint();
                }
        );

        BotonRedondeado btnAbandonar
                = new BotonRedondeado(
                        "ABANDONAR",
                        ColoresBitsOBots.AZUL_PRINCIPAL,
                        ColoresBitsOBots.AZUL_HOVER
                );

        btnAbandonar.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        btnAbandonar.setPreferredSize(
                new Dimension(
                        140,
                        45
                )
        );

        btnAbandonar.addActionListener(
                e -> {

                    abandonarPartida();
                }
        );

        acciones.add(
                btnMusica
        );

        acciones.add(
                btnAbandonar
        );

        filaTitulo.add(
                lblJugador,
                BorderLayout.WEST
        );

        filaTitulo.add(
                lblNivel,
                BorderLayout.CENTER
        );

        filaTitulo.add(
                acciones,
                BorderLayout.EAST
        );

        superior.add(
                filaTitulo,
                BorderLayout.NORTH
        );

        PanelRedondeado panelDatos
                = new PanelRedondeado(
                        25,
                        new Color(
                                255,
                                255,
                                255,
                                230
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        panelDatos.setLayout(
                new GridLayout(
                        1,
                        4,
                        8,
                        0
                )
        );

        panelDatos.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        14,
                        10,
                        14
                )
        );

        lblMovimientos
                = crearEtiquetaDato(
                        "Movimientos: 0"
                );

        lblPuntosNivel
                = crearEtiquetaDato(
                        "Nivel: 0 pts"
                );

        lblPuntosTotal
                = crearEtiquetaDato(
                        "Total: "
                        + progreso.getPuntosTotales()
                        + " pts"
                );

        lblTiempo
                = crearEtiquetaDato(
                        "Tiempo: 00:00"
                );

        panelDatos.add(
                lblMovimientos
        );

        panelDatos.add(
                lblPuntosNivel
        );

        panelDatos.add(
                lblPuntosTotal
        );

        panelDatos.add(
                lblTiempo
        );

        superior.add(
                panelDatos,
                BorderLayout.SOUTH
        );

        return superior;
    }

    // =====================================================
    // ETIQUETA DE DATOS
    // =====================================================

    private JLabel crearEtiquetaDato(
            String texto
    ) {

        JLabel etiqueta
                = new JLabel(
                        texto,
                        SwingConstants.CENTER
                );

        etiqueta.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        15f
                )
        );

        etiqueta.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        return etiqueta;
    }

    // =====================================================
    // TEXTO ANIMADO
    // =====================================================

    private void escribirTextoAnimado(
            String texto,
            int velocidadMs
    ) {

        if (timerTexto != null) {

            timerTexto.stop();
            timerTexto = null;
        }

        lblMensaje.setText("");

        final int partidaDelTexto
                = numeroPartida;

        final int[] indice
                = {0};

        timerTexto
                = new Timer(
                        velocidadMs,
                        e -> {

                            if (
                                    partidaDelTexto
                                    != numeroPartida
                            ) {

                                ((Timer) e.getSource())
                                        .stop();

                                return;
                            }

                            if (
                                    indice[0]
                                    < texto.length()
                            ) {

                                indice[0]++;

                                lblMensaje.setText(
                                        texto.substring(
                                                0,
                                                indice[0]
                                        )
                                );

                            } else {

                                ((Timer) e.getSource())
                                        .stop();

                                timerTexto = null;
                            }
                        }
                );

        timerTexto.start();
    }

    // =====================================================
    // INICIAR NIVEL
    // =====================================================

    private void iniciarNivelAutomaticamente() {

        numeroPartida++;

        int partidaActual
                = numeroPartida;

        primeraCarta = -1;
        segundaCarta = -1;

        parejasEncontradas = 0;
        movimientos = 0;
        puntosNivel = 0;
        segundos = 0;

        actualizarMarcadores();

        partidaFinalizada = false;
        bloqueado = true;

        crearValores();
        crearTablero();

        escribirTextoAnimado(
                "¡Memoriza todas las cartas!",
                10
        );

        SwingUtilities.invokeLater(
                () -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {

                        return;
                    }

                    iniciarPresentacion(
                            partidaActual
                    );
                }
        );
    }

    // =====================================================
    // CREAR VALORES
    // =====================================================

    private void crearValores() {

        ArrayList<Integer> parejas
                = new ArrayList<>();

        for (
                int i = 1;
                i <= cantidadParejas;
                i++
        ) {

            parejas.add(i);
            parejas.add(i);
        }

        Collections.shuffle(
                parejas
        );

        valores
                = new int[
                        cantidadCartas
                ];

        encontradas
                = new boolean[
                        cantidadCartas
                ];

        for (
                int i = 0;
                i < cantidadCartas;
                i++
        ) {

            valores[i]
                    = parejas.get(i);
        }
    }

    // =====================================================
    // SALTAR NIVEL DE PRUEBA
    // =====================================================

    private void saltarNivelPrueba() {

        if (partidaFinalizada) {
            return;
        }

        partidaFinalizada = true;
        bloqueado = true;

        numeroPartida++;

        detenerCronometro();

        GestorMusica.detenerFondo();

        int bonificacionPrueba = 0;

        progreso.agregarResultadoNivel(
                numeroNivel,
                puntosNivel,
                movimientos,
                segundos
        );

        NivelCompletadoForm completado
                = new NivelCompletadoForm(
                        progreso,
                        numeroNivel,
                        cantidadParejas,
                        puntosNivel,
                        bonificacionPrueba,
                        movimientos,
                        segundos
                );

        completado.setLocationRelativeTo(
                null
        );

        completado.setVisible(
                true
        );

        dispose();
    }

    // =====================================================
    // CREAR TABLERO
    // =====================================================

    private void crearTablero() {

        panelCartas.removeAll();

        cartas
                = new BotonCarta[
                        cantidadCartas
                ];

        int anchoPanel
                = (MARGEN * 2)
                + (columnas * ANCHO_CARTA)
                + ((columnas - 1) * ESPACIO);

        int altoPanel
                = (MARGEN * 2)
                + (filas * ALTO_CARTA)
                + ((filas - 1) * ESPACIO);

        panelCartas.setPreferredSize(
                new Dimension(
                        anchoPanel,
                        altoPanel
                )
        );

        for (
                int i = 0;
                i < cantidadCartas;
                i++
        ) {

            BotonCarta carta
                    = new BotonCarta(
                            18
                    );

            carta.setColorBorde(
                    COLOR_BORDE
            );

            int fila
                    = i / columnas;

            int columna
                    = i % columnas;

            int x
                    = MARGEN
                    + columna
                    * (
                            ANCHO_CARTA
                            + ESPACIO
                    );

            int y
                    = MARGEN
                    + fila
                    * (
                            ALTO_CARTA
                            + ESPACIO
                    );

            carta.setBounds(
                    x,
                    y,
                    ANCHO_CARTA,
                    ALTO_CARTA
            );

            final int posicion
                    = i;

            carta.addActionListener(
                    e -> {

                        seleccionarCarta(
                                posicion
                        );
                    }
            );

            cartas[i]
                    = carta;

            panelCartas.add(
                    carta
            );

            mostrarFrente(
                    i
            );
        }

        panelCartas.revalidate();
        panelCartas.repaint();

        contenedorCartas.revalidate();
        contenedorCartas.repaint();
    }

    // =====================================================
    // CARGAR IMÁGENES
    // =====================================================

    private void cargarImagenes() {

        int anchoImagen
                = ANCHO_CARTA - 8;

        int altoImagen
                = ALTO_CARTA - 8;

        reversoInformatica
                = cargarIcono(
                        "/img/reverso_informatica.PNG",
                        anchoImagen,
                        altoImagen
                );

        reversoRobotica
                = cargarIcono(
                        "/img/reverso_robotica.PNG",
                        anchoImagen,
                        altoImagen
                );

        for (
                int i = 0;
                i < rutasImagenes.length;
                i++
        ) {

            imagenesFrente[i]
                    = cargarIcono(
                            rutasImagenes[i],
                            anchoImagen,
                            altoImagen
                    );
        }
    }

    // =====================================================
    // OBTENER REVERSO
    // =====================================================

    private ImageIcon obtenerReverso(
            int posicion
    ) {

        int indiceComponente
                = valores[posicion] - 1;

        String categoria
                = categoriasCartas[
                        indiceComponente
                ];

        if (
                "ROBOTICA"
                        .equalsIgnoreCase(
                                categoria
                        )
        ) {

            return reversoRobotica;
        }

        return reversoInformatica;
    }

    // =====================================================
    // CARGAR ICONO
    // =====================================================

    private ImageIcon cargarIcono(
            String ruta,
            int anchoMaximo,
            int altoMaximo
    ) {

        URL url
                = getClass()
                        .getResource(
                                ruta
                        );

        if (url == null) {

            System.out.println(
                    "No se encontró: "
                    + ruta
            );

            return null;
        }

        ImageIcon original
                = new ImageIcon(
                        url
                );

        int anchoOriginal
                = original.getIconWidth();

        int altoOriginal
                = original.getIconHeight();

        if (
                anchoOriginal <= 0
                || altoOriginal <= 0
        ) {

            return null;
        }

        double escala
                = Math.min(
                        anchoMaximo
                        / (double) anchoOriginal,
                        altoMaximo
                        / (double) altoOriginal
                );

        int nuevoAncho
                = Math.max(
                        1,
                        (int) (
                                anchoOriginal
                                * escala
                        )
                );

        int nuevoAlto
                = Math.max(
                        1,
                        (int) (
                                altoOriginal
                                * escala
                        )
                );

        Image escalada
                = original
                        .getImage()
                        .getScaledInstance(
                                nuevoAncho,
                                nuevoAlto,
                                Image.SCALE_SMOOTH
                        );

        return new ImageIcon(
                escalada
        );
    }

    // =====================================================
    // PRESENTACIÓN INICIAL
    // =====================================================

    private void iniciarPresentacion(
            int partidaActual
    ) {

        animarEntradaCartas(
                partidaActual,
                () -> {

                    Timer observacion
                            = new Timer(
                                    TIEMPO_OBSERVACION,
                                    e -> {

                                        if (
                                                partidaActual
                                                != numeroPartida
                                        ) {

                                            return;
                                        }

                                        escribirTextoAnimado(
                                                "¡Sigue el movimiento de las cartas!",
                                                50
                                        );

                                        animarIntercambios(
                                                partidaActual,
                                                0
                                        );
                                    }
                            );

                    observacion.setRepeats(
                            false
                    );

                    observacion.start();
                }
        );
    }

    // =====================================================
    // ANIMACIÓN DE ENTRADA
    // =====================================================

    private void animarEntradaCartas(
            int partidaActual,
            Runnable alTerminar
    ) {

        Rectangle[] originales
                = new Rectangle[
                        cartas.length
                ];

        for (
                int i = 0;
                i < cartas.length;
                i++
        ) {

            originales[i]
                    = cartas[i]
                            .getBounds();
        }

        final int totalPasos = 32;

        final int[] paso
                = {0};

        Timer animacion
                = new Timer(
                        10,
                        null
                );

        animacion.addActionListener(
                e -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {

                        animacion.stop();
                        return;
                    }

                    paso[0]++;

                    for (
                            int i = 0;
                            i < cartas.length;
                            i++
                    ) {

                        double progresoLocal
                                = (
                                        paso[0]
                                        - i * 0.42
                                )
                                / 18.0;

                        progresoLocal
                                = Math.max(
                                        0,
                                        Math.min(
                                                1,
                                                progresoLocal
                                        )
                                );

                        double escala
                                = 0.28
                                + 0.72
                                * progresoLocal
                                + 0.10
                                * Math.sin(
                                        Math.PI
                                        * progresoLocal
                                );

                        cambiarTamanoDesdeCentro(
                                cartas[i],
                                originales[i],
                                escala
                        );
                    }

                    panelCartas.repaint();

                    if (
                            paso[0]
                            >= totalPasos
                    ) {

                        animacion.stop();

                        for (
                                int i = 0;
                                i < cartas.length;
                                i++
                        ) {

                            cartas[i].setBounds(
                                    originales[i]
                            );
                        }

                        panelCartas.repaint();

                        alTerminar.run();
                    }
                }
        );

        animacion.start();
    }

    // =====================================================
    // ANIMAR INTERCAMBIOS
    // =====================================================

    private void animarIntercambios(
            int partidaActual,
            int numeroIntercambio
    ) {

        if (
                partidaActual
                != numeroPartida
        ) {

            return;
        }

        if (
                numeroIntercambio
                >= totalIntercambios
        ) {

            escribirTextoAnimado(
                    "Memoriza el orden final...",
                    50
            );

            Timer pausa
                    = new Timer(
                            PAUSA_FINAL,
                            e -> {

                                voltearTodasAlReverso(
                                        partidaActual,
                                        () -> {

                                            if (
                                                    partidaActual
                                                    != numeroPartida
                                            ) {

                                                return;
                                            }

                                            bloqueado = false;

                                            escribirTextoAnimado(
                                                    "Encuentra todas las parejas",
                                                    50
                                            );

                                            iniciarCronometro(
                                                    partidaActual
                                            );
                                        }
                                );
                            }
                    );

            pausa.setRepeats(
                    false
            );

            pausa.start();

            return;
        }

        int cartaA
                = aleatorio.nextInt(
                        cartas.length
                );

        int cartaB
                = elegirCartaLejana(
                        cartaA
                );

        intercambiarCartasAnimadas(
                partidaActual,
                cartaA,
                cartaB,
                () -> {

                    Timer pausa
                            = new Timer(
                                    PAUSA_ENTRE_MOVIMIENTOS,
                                    e -> {

                                        animarIntercambios(
                                                partidaActual,
                                                numeroIntercambio + 1
                                        );
                                    }
                            );

                    pausa.setRepeats(
                            false
                    );

                    pausa.start();
                }
        );
    }

    // =====================================================
    // ELEGIR CARTA LEJANA
    // =====================================================

    private int elegirCartaLejana(
            int cartaA
    ) {

        int cartaB
                = cartaA;

        int intentos
                = 0;

        while (
                intentos < 40
                && (
                        cartaA == cartaB
                        || cartas[cartaA]
                                .getLocation()
                                .distance(
                                        cartas[cartaB]
                                                .getLocation()
                                )
                                < 140
                )
        ) {

            cartaB
                    = aleatorio.nextInt(
                            cartas.length
                    );

            intentos++;
        }

        if (
                cartaA
                == cartaB
        ) {

            cartaB
                    = (
                            cartaA + 1
                    )
                    % cartas.length;
        }

        return cartaB;
    }

    // =====================================================
    // INTERCAMBIAR CARTAS
    // =====================================================

    private void intercambiarCartasAnimadas(
            int partidaActual,
            int cartaA,
            int cartaB,
            Runnable alTerminar
    ) {

        Rectangle inicioA
                = cartas[cartaA]
                        .getBounds();

        Rectangle inicioB
                = cartas[cartaB]
                        .getBounds();

        panelCartas.setComponentZOrder(
                cartas[cartaA],
                0
        );

        panelCartas.setComponentZOrder(
                cartas[cartaB],
                0
        );

        final int[] paso
                = {0};

        double ax
                = inicioA.x
                + ANCHO_CARTA / 2.0;

        double ay
                = inicioA.y
                + ALTO_CARTA / 2.0;

        double bx
                = inicioB.x
                + ANCHO_CARTA / 2.0;

        double by
                = inicioB.y
                + ALTO_CARTA / 2.0;

        double diferenciaX
                = bx - ax;

        double diferenciaY
                = by - ay;

        double distancia
                = Math.sqrt(
                        diferenciaX * diferenciaX
                        + diferenciaY * diferenciaY
                );

        if (
                distancia == 0
        ) {

            distancia = 1;
        }

        double perpendicularX
                = -diferenciaY
                / distancia;

        double perpendicularY
                = diferenciaX
                / distancia;

        Timer animacion
                = new Timer(
                        VELOCIDAD_MOVIMIENTO,
                        null
                );

        animacion.addActionListener(
                e -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {

                        animacion.stop();
                        return;
                    }

                    paso[0]++;

                    double progresoPaso
                            = paso[0]
                            / (double) PASOS_MOVIMIENTO;

                    double suave
                            = 0.5
                            - Math.cos(
                                    progresoPaso
                                    * Math.PI
                            ) / 2.0;

                    double curva
                            = 30
                            * Math.sin(
                                    Math.PI
                                    * progresoPaso
                            );

                    double escala
                            = 1.0
                            + 0.12
                            * Math.sin(
                                    Math.PI
                                    * progresoPaso
                            );

                    int nuevoAncho
                            = (int) (
                                    ANCHO_CARTA
                                    * escala
                            );

                    int nuevoAlto
                            = (int) (
                                    ALTO_CARTA
                                    * escala
                            );

                    double centroAX
                            = interpolar(
                                    ax,
                                    bx,
                                    suave
                            )
                            + perpendicularX
                            * curva;

                    double centroAY
                            = interpolar(
                                    ay,
                                    by,
                                    suave
                            )
                            + perpendicularY
                            * curva;

                    double centroBX
                            = interpolar(
                                    bx,
                                    ax,
                                    suave
                            )
                            - perpendicularX
                            * curva;

                    double centroBY
                            = interpolar(
                                    by,
                                    ay,
                                    suave
                            )
                            - perpendicularY
                            * curva;

                    cartas[cartaA]
                            .setBounds(
                                    (int) centroAX
                                    - nuevoAncho / 2,
                                    (int) centroAY
                                    - nuevoAlto / 2,
                                    nuevoAncho,
                                    nuevoAlto
                            );

                    cartas[cartaB]
                            .setBounds(
                                    (int) centroBX
                                    - nuevoAncho / 2,
                                    (int) centroBY
                                    - nuevoAlto / 2,
                                    nuevoAncho,
                                    nuevoAlto
                            );

                    panelCartas.repaint();

                    if (
                            paso[0]
                            >= PASOS_MOVIMIENTO
                    ) {

                        animacion.stop();

                        cartas[cartaA]
                                .setBounds(
                                        inicioB.x,
                                        inicioB.y,
                                        ANCHO_CARTA,
                                        ALTO_CARTA
                                );

                        cartas[cartaB]
                                .setBounds(
                                        inicioA.x,
                                        inicioA.y,
                                        ANCHO_CARTA,
                                        ALTO_CARTA
                                );

                        panelCartas.repaint();

                        alTerminar.run();
                    }
                }
        );

        animacion.start();
    }

    // =====================================================
    // INTERPOLACIÓN
    // =====================================================

    private double interpolar(
            double inicio,
            double finalPosicion,
            double progresoAnimacion
    ) {

        return inicio
                + (
                        finalPosicion
                        - inicio
                )
                * progresoAnimacion;
    }

    // =====================================================
    // VOLTEAR TODAS AL REVERSO
    // =====================================================

    private void voltearTodasAlReverso(
            int partidaActual,
            Runnable alTerminar
    ) {

        final int[] terminadas
                = {0};

        for (
                int i = 0;
                i < cartas.length;
                i++
        ) {

            final int posicion
                    = i;

            Timer retraso
                    = new Timer(
                            i * 18,
                            e -> {

                                animarVolteoCarta(
                                        partidaActual,
                                        posicion,
                                        false,
                                        () -> {

                                            terminadas[0]++;

                                            if (
                                                    terminadas[0]
                                                    == cartas.length
                                            ) {

                                                alTerminar.run();
                                            }
                                        }
                                );
                            }
                    );

            retraso.setRepeats(
                    false
            );

            retraso.start();
        }
    }

    // =====================================================
    // SELECCIONAR CARTA
    // =====================================================

    private void seleccionarCarta(
            int posicion
    ) {

        if (
                bloqueado
                || partidaFinalizada
                || posicion == primeraCarta
                || encontradas[posicion]
        ) {

            return;
        }

        int partidaActual
                = numeroPartida;

        bloqueado = true;

        animarVolteoCarta(
                partidaActual,
                posicion,
                true,
                () -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {

                        return;
                    }

                    if (
                            primeraCarta
                            == -1
                    ) {

                        primeraCarta
                                = posicion;

                        bloqueado
                                = false;

                    } else {

                        segundaCarta
                                = posicion;

                        movimientos++;

                        actualizarMarcadores();

                        comprobarPareja(
                                partidaActual
                        );
                    }
                }
        );
    }

    // =====================================================
    // ANIMAR VOLTEO
    // =====================================================

    private void animarVolteoCarta(
            int partidaActual,
            int posicion,
            boolean mostrarFrente,
            Runnable alTerminar
    ) {

        Rectangle original
                = cartas[posicion]
                        .getBounds();

        final int totalPasos
                = 12;

        final int[] paso
                = {0};

        final boolean[] cambio
                = {false};

        Timer animacion
                = new Timer(
                        12,
                        null
                );

        animacion.addActionListener(
                e -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {

                        animacion.stop();
                        return;
                    }

                    paso[0]++;

                    double escalaHorizontal;

                    if (
                            paso[0]
                            <= totalPasos / 2
                    ) {

                        escalaHorizontal
                                = 1
                                - paso[0]
                                / (double) (
                                        totalPasos / 2
                                );

                    } else {

                        escalaHorizontal
                                = (
                                        paso[0]
                                        - totalPasos / 2
                                )
                                / (double) (
                                        totalPasos / 2
                                );
                    }

                    if (
                            paso[0]
                            >= totalPasos / 2
                            && !cambio[0]
                    ) {

                        cambio[0]
                                = true;

                        if (
                                mostrarFrente
                        ) {

                            mostrarFrente(
                                    posicion
                            );

                        } else {

                            mostrarReverso(
                                    posicion
                            );
                        }
                    }

                    int nuevoAncho
                            = Math.max(
                                    2,
                                    (int) (
                                            ANCHO_CARTA
                                            * escalaHorizontal
                                    )
                            );

                    int centroX
                            = original.x
                            + ANCHO_CARTA / 2;

                    cartas[posicion]
                            .setBounds(
                                    centroX
                                    - nuevoAncho / 2,
                                    original.y,
                                    nuevoAncho,
                                    ALTO_CARTA
                            );

                    panelCartas.repaint();

                    if (
                            paso[0]
                            >= totalPasos
                    ) {

                        animacion.stop();

                        cartas[posicion]
                                .setBounds(
                                        original
                                );

                        panelCartas.repaint();

                        alTerminar.run();
                    }
                }
        );

        animacion.start();
    }

    // =====================================================
    // COMPROBAR PAREJA
    // =====================================================

    private void comprobarPareja(
            int partidaActual
    ) {

        if (
                valores[primeraCarta]
                == valores[segundaCarta]
        ) {

            int cartaA
                    = primeraCarta;

            int cartaB
                    = segundaCarta;

            encontradas[cartaA]
                    = true;

            encontradas[cartaB]
                    = true;

            parejasEncontradas++;

            puntosNivel
                    += 100;

            actualizarMarcadores();

            String nombre
                    = nombresCartas[
                            valores[cartaA] - 1
                    ];

            lblMensaje.setText(
                    "¡Encontraste "
                    + nombre
                    + "!  +100 puntos"
            );

            GestorMusica.reproducirEfecto(
                    "/audio/pareja.wav"
            );

            primeraCarta = -1;
            segundaCarta = -1;

            animarParejaEncontrada(
                    partidaActual,
                    cartaA,
                    cartaB,
                    () -> {

                        if (
                                parejasEncontradas
                                == cantidadParejas
                        ) {

                            finalizarNivel(
                                    partidaActual
                            );

                        } else {

                            bloqueado
                                    = false;

                            Timer mensaje
                                    = new Timer(
                                            780,
                                            e -> {

                                                if (
                                                        partidaActual
                                                        == numeroPartida
                                                ) {

                                                    escribirTextoAnimado(
                                                            "Continúa buscando las parejas",
                                                            50
                                                    );
                                                }
                                            }
                                    );

                            mensaje.setRepeats(
                                    false
                            );

                            mensaje.start();
                        }
                    }
            );

        } else {

            int cartaA
                    = primeraCarta;

            int cartaB
                    = segundaCarta;

            GestorMusica.reproducirEfecto(
                    "/audio/incorrecto.wav"
            );

            escribirTextoAnimado(
                    "Estas cartas no coinciden",
                    50
            );

            Timer tiempo
                    = new Timer(
                            TIEMPO_INCORRECTAS,
                            e -> {

                                ocultarDosCartas(
                                        partidaActual,
                                        cartaA,
                                        cartaB
                                );
                            }
                    );

            tiempo.setRepeats(
                    false
            );

            tiempo.start();
        }
    }

    // =====================================================
    // OCULTAR DOS CARTAS
    // =====================================================

    private void ocultarDosCartas(
            int partidaActual,
            int cartaA,
            int cartaB
    ) {

        final int[] terminadas
                = {0};

        Runnable terminar
                = () -> {

                    terminadas[0]++;

                    if (
                            terminadas[0]
                            == 2
                    ) {

                        primeraCarta = -1;
                        segundaCarta = -1;

                        bloqueado
                                = false;

                        escribirTextoAnimado(
                                "Intenta encontrar otra pareja",
                                50
                        );
                    }
                };

        animarVolteoCarta(
                partidaActual,
                cartaA,
                false,
                terminar
        );

        animarVolteoCarta(
                partidaActual,
                cartaB,
                false,
                terminar
        );
    }

    // =====================================================
    // ANIMACIÓN DE PAREJA ENCONTRADA
    // =====================================================

    private void animarParejaEncontrada(
            int partidaActual,
            int cartaA,
            int cartaB,
            Runnable alTerminar
    ) {

        Rectangle originalA
                = cartas[cartaA]
                        .getBounds();

        Rectangle originalB
                = cartas[cartaB]
                        .getBounds();

        cartas[cartaA]
                .setColorBorde(
                        COLOR_PAREJA
                );

        cartas[cartaB]
                .setColorBorde(
                        COLOR_PAREJA
                );

        final int totalPasos
                = 14;

        final int[] paso
                = {0};

        Timer animacion
                = new Timer(
                        15,
                        null
                );

        animacion.addActionListener(
                e -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {

                        animacion.stop();
                        return;
                    }

                    paso[0]++;

                    double progresoAnimacion
                            = paso[0]
                            / (double) totalPasos;

                    double escala
                            = 1.0
                            + 0.10
                            * Math.sin(
                                    Math.PI
                                    * progresoAnimacion
                            );

                    cambiarTamanoDesdeCentro(
                            cartas[cartaA],
                            originalA,
                            escala
                    );

                    cambiarTamanoDesdeCentro(
                            cartas[cartaB],
                            originalB,
                            escala
                    );

                    panelCartas.repaint();

                    if (
                            paso[0]
                            >= totalPasos
                    ) {

                        animacion.stop();

                        cartas[cartaA]
                                .setBounds(
                                        originalA
                                );

                        cartas[cartaB]
                                .setBounds(
                                        originalB
                                );

                        panelCartas.repaint();

                        alTerminar.run();
                    }
                }
        );

        animacion.start();
    }

    // =====================================================
    // CAMBIAR TAMAÑO DESDE EL CENTRO
    // =====================================================

    private void cambiarTamanoDesdeCentro(
            java.awt.Component componente,
            Rectangle original,
            double escala
    ) {

        int nuevoAncho
                = Math.max(
                        2,
                        (int) (
                                ANCHO_CARTA
                                * escala
                        )
                );

        int nuevoAlto
                = Math.max(
                        2,
                        (int) (
                                ALTO_CARTA
                                * escala
                        )
                );

        int centroX
                = original.x
                + ANCHO_CARTA / 2;

        int centroY
                = original.y
                + ALTO_CARTA / 2;

        componente.setBounds(
                centroX
                - nuevoAncho / 2,
                centroY
                - nuevoAlto / 2,
                nuevoAncho,
                nuevoAlto
        );
    }

    // =====================================================
    // MOSTRAR FRENTE
    // =====================================================

    private void mostrarFrente(
            int posicion
    ) {

        int valor
                = valores[posicion];

        ImageIcon icono
                = imagenesFrente[
                        valor - 1
                ];

        if (
                icono != null
        ) {

            cartas[posicion]
                    .setIcon(
                            icono
                    );

            cartas[posicion]
                    .setText("");

        } else {

            cartas[posicion]
                    .setIcon(null);

            cartas[posicion]
                    .setText(
                            String.valueOf(
                                    valor
                            )
                    );

            cartas[posicion]
                    .setFont(
                            new Font(
                                    "Arial",
                                    Font.BOLD,
                                    30
                            )
                    );
        }
    }

    // =====================================================
    // MOSTRAR REVERSO
    // =====================================================

    private void mostrarReverso(
            int posicion
    ) {

        cartas[posicion]
                .setColorBorde(
                        COLOR_BORDE
                );

        ImageIcon reverso
                = obtenerReverso(
                        posicion
                );

        if (
                reverso != null
        ) {

            cartas[posicion]
                    .setIcon(
                            reverso
                    );

            cartas[posicion]
                    .setText("");

        } else {

            cartas[posicion]
                    .setIcon(null);

            cartas[posicion]
                    .setText("?");

            cartas[posicion]
                    .setFont(
                            new Font(
                                    "Arial",
                                    Font.BOLD,
                                    30
                            )
                    );
        }
    }

    // =====================================================
    // CRONÓMETRO
    // =====================================================

    private void iniciarCronometro(
            int partidaActual
    ) {

        detenerCronometro();

        cronometro
                = new Timer(
                        1000,
                        e -> {

                            if (
                                    partidaActual
                                    != numeroPartida
                                    || partidaFinalizada
                            ) {

                                cronometro.stop();
                                return;
                            }

                            segundos++;

                            actualizarMarcadores();
                        }
                );

        cronometro.start();
    }

    private void detenerCronometro() {

        if (
                cronometro != null
        ) {

            cronometro.stop();
            cronometro = null;
        }
    }

    // =====================================================
    // FINALIZAR NIVEL
    // =====================================================

    private void finalizarNivel(
            int partidaActual
    ) {

        if (
                partidaFinalizada
        ) {

            return;
        }

        partidaFinalizada
                = true;

        bloqueado
                = true;

        detenerCronometro();

        int bonificacion
                = Math.max(
                        0,
                        cantidadParejas * 100
                        - movimientos * 8
                        - segundos * 2
                );

        puntosNivel
                += bonificacion;

        progreso.agregarResultadoNivel(
                numeroNivel,
                puntosNivel,
                movimientos,
                segundos
        );

        escribirTextoAnimado(
                "¡Nivel completado!",
                50
        );

        GestorMusica.reproducirEfecto(
                "/audio/victoria.wav"
        );

        if (
                numeroNivel == 3
        ) {

            guardarRankingGeneral();
        }

        Timer abrirResumen
                = new Timer(
                        350,
                        e -> {

                            if (
                                    partidaActual
                                    != numeroPartida
                            ) {

                                return;
                            }

                            numeroPartida++;

                            new NivelCompletadoForm(
                                    progreso,
                                    numeroNivel,
                                    cantidadParejas,
                                    puntosNivel,
                                    bonificacion,
                                    movimientos,
                                    segundos
                            ).setVisible(
                                    true
                            );

                            dispose();
                        }
                );

        abrirResumen.setRepeats(
                false
        );

        abrirResumen.start();
    }

    // =====================================================
    // GUARDAR RANKING
    // =====================================================

    private void guardarRankingGeneral() {

        if (
                progreso.isRankingGuardado()
        ) {

            return;
        }

        String fecha
                = LocalDateTime
                        .now()
                        .format(
                                DateTimeFormatter
                                        .ofPattern(
                                                "dd/MM/yyyy HH:mm"
                                        )
                        );

        RankingManager.guardar(
                new RegistroRanking(
                        progreso.getJugador(),
                        progreso.getPuntosTotales(),
                        progreso.getMovimientosTotales(),
                        progreso.getSegundosTotales(),
                        fecha
                )
        );

        progreso.setRankingGuardado(
                true
        );
    }

    // =====================================================
    // ABANDONAR PARTIDA
    // =====================================================

    private void abandonarPartida() {

        int respuesta
                = JOptionPane
                        .showConfirmDialog(
                                this,
                                "Se perderá el progreso de los niveles.\n"
                                + "¿Deseas volver al menú del juego?",
                                "Abandonar partida",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

        if (
                respuesta
                != JOptionPane.YES_OPTION
        ) {

            return;
        }

        numeroPartida++;

        detenerCronometro();

        new MenuJuego(
                progreso.getJugador(),
                progreso.getSeccion(),
                progreso
                        .getAccionVolverPrincipal()
        ).setVisible(
                true
        );

        dispose();
    }
}