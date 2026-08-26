package JuegoMemoria;

import Tipografias.Fuentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PantallaFinal extends JFrame {

    private final ProgresoJuego progreso;

    /*
     * =====================================================
     * COLOR DE LOS PANELES DE RESULTADOS
     * =====================================================
     *
     * Este es el color que tenía NIVEL 3.
     *
     * Ahora también lo usarán:
     *
     * - Puntuación total
     * - Nivel 1
     * - Nivel 2
     * - Nivel 3
     */
    private static final Color COLOR_RESULTADO
            = new Color(
                    231,
                    253,
                    254,
                    235
            );

    private static final Color BORDE_RESULTADO
            = ColoresBitsOBots.BORDE_SUAVE;

    public PantallaFinal(
            ProgresoJuego progreso
    ) {

        this.progreso = progreso;

        configurarVentana();
        construirInterfaz();

        GestorMusica.reproducirFondo(
                "/audio/musica_final.wav"
        );
    }

    // =====================================================
    // CONFIGURACIÓN DE LA VENTANA
    // =====================================================

    private void configurarVentana() {

        setTitle(
                "Memory Tech - Resultado final"
        );

        setUndecorated(true);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setResizable(false);

        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );
    }

    // =====================================================
    // INTERFAZ
    // =====================================================

    private void construirInterfaz() {

        // =================================================
        // FONDO PRINCIPAL
        // =================================================

        PanelDegradado fondo
                = new PanelDegradado(
                        ColoresBitsOBots.FONDO_SUPERIOR,
                        ColoresBitsOBots.FONDO_INFERIOR
                );

        fondo.setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        35,
                        22,
                        35
                )
        );

        setContentPane(
                fondo
        );

        // =================================================
        // ENCABEZADO
        // =================================================

        JPanel encabezado
                = new JPanel(
                        new BorderLayout()
                );

        encabezado.setOpaque(false);

        /*
         * Espacio falso izquierdo.
         *
         * Tiene el mismo ancho que el botón
         * de música para mantener el título centrado.
         */
        JPanel espacioIzquierdo
                = new JPanel();

        espacioIzquierdo.setOpaque(false);

        espacioIzquierdo.setPreferredSize(
                new Dimension(
                        65,
                        65
                )
        );

        encabezado.add(
                espacioIzquierdo,
                BorderLayout.WEST
        );

        // =================================================
        // TEXTOS DEL ENCABEZADO
        // =================================================

        JPanel panelTextos
                = new JPanel();

        panelTextos.setLayout(
                new BoxLayout(
                        panelTextos,
                        BoxLayout.Y_AXIS
                )
        );

        panelTextos.setOpaque(false);

        // =================================================
        // TÍTULO
        // =================================================

        JLabel lblTitulo
                = new JLabel(
                        "¡RETO COMPLETADO!"
                );

        lblTitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        42f
                )
        );

        lblTitulo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblTitulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // =================================================
        // JUGADOR
        // =================================================

        JLabel lblJugador
                = new JLabel(
                        progreso.getJugador()
                );

        lblJugador.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        19f
                )
        );

        lblJugador.setForeground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        lblJugador.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblJugador.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // =================================================
        // SUBTÍTULO
        // =================================================

        JLabel lblSubtitulo
                = new JLabel(
                        "Completaste los tres niveles de Bits o Bots"
                );

        lblSubtitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        14f
                )
        );

        lblSubtitulo.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        lblSubtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblSubtitulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        panelTextos.add(
                lblTitulo
        );

        panelTextos.add(
                Box.createVerticalStrut(
                        6
                )
        );

        panelTextos.add(
                lblJugador
        );

        panelTextos.add(
                Box.createVerticalStrut(
                        5
                )
        );

        panelTextos.add(
                lblSubtitulo
        );

        encabezado.add(
                panelTextos,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTÓN DE MÚSICA
        // =================================================

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        Dimension tamanoMusica
                = new Dimension(
                        65,
                        65
                );

        btnMusica.setPreferredSize(
                tamanoMusica
        );

        btnMusica.setMinimumSize(
                tamanoMusica
        );

        btnMusica.setMaximumSize(
                tamanoMusica
        );

        btnMusica.addActionListener(
                e -> {

                    GestorMusica.alternarSilencio();

                    btnMusica.repaint();
                }
        );

        encabezado.add(
                btnMusica,
                BorderLayout.EAST
        );

        fondo.add(
                encabezado,
                BorderLayout.NORTH
        );

        // =================================================
        // CONTENEDOR CENTRAL
        // =================================================

        JPanel contenedorCentral
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorCentral.setOpaque(false);

        // =================================================
        // TARJETA PRINCIPAL
        // =================================================

        /*
         * ESTA NO LA CAMBIAMOS.
         *
         * Sigue blanca como estaba.
         */
        PanelRedondeado tarjetaPrincipal
                = new PanelRedondeado(
                        38,
                        new Color(
                                255,
                                255,
                                255,
                                225
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        Dimension tamanoTarjeta
                = new Dimension(
                        1120,
                        500
                );

        tarjetaPrincipal.setPreferredSize(
                tamanoTarjeta
        );

        tarjetaPrincipal.setMinimumSize(
                tamanoTarjeta
        );

        tarjetaPrincipal.setMaximumSize(
                tamanoTarjeta
        );

        tarjetaPrincipal.setLayout(
                new BorderLayout(
                        18,
                        18
                )
        );

        tarjetaPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        32,
                        25,
                        32
                )
        );

        // =================================================
        // PUNTUACIÓN TOTAL
        // =================================================

        /*
         * AHORA TIENE EXACTAMENTE
         * EL COLOR DEL NIVEL 3.
         */
        PanelRedondeado panelPuntuacion
                = new PanelRedondeado(
                        28,
                        COLOR_RESULTADO,
                        BORDE_RESULTADO
                );

        Dimension tamanoPuntuacion
                = new Dimension(
                        520,
                        115
                );

        panelPuntuacion.setPreferredSize(
                tamanoPuntuacion
        );

        panelPuntuacion.setMinimumSize(
                tamanoPuntuacion
        );

        panelPuntuacion.setMaximumSize(
                tamanoPuntuacion
        );

        panelPuntuacion.setLayout(
                new BoxLayout(
                        panelPuntuacion,
                        BoxLayout.Y_AXIS
                )
        );

        panelPuntuacion.setBorder(
                BorderFactory.createEmptyBorder(
                        14,
                        15,
                        14,
                        15
                )
        );

        // =================================================
        // TEXTO PUNTUACIÓN TOTAL
        // =================================================

        JLabel lblTextoPuntuacion
                = new JLabel(
                        "PUNTUACIÓN TOTAL"
                );

        lblTextoPuntuacion.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        15f
                )
        );

        lblTextoPuntuacion.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        lblTextoPuntuacion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // VALOR DE PUNTUACIÓN
        // =================================================

        JLabel lblPuntuacion
                = new JLabel(
                        progreso.getPuntosTotales()
                        + " PTS"
                );

        lblPuntuacion.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        39f
                )
        );

        lblPuntuacion.setForeground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        lblPuntuacion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPuntuacion.add(
                Box.createVerticalGlue()
        );

        panelPuntuacion.add(
                lblTextoPuntuacion
        );

        panelPuntuacion.add(
                Box.createVerticalStrut(
                        7
                )
        );

        panelPuntuacion.add(
                lblPuntuacion
        );

        panelPuntuacion.add(
                Box.createVerticalGlue()
        );

        JPanel contenedorPuntuacion
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorPuntuacion.setOpaque(false);

        contenedorPuntuacion.add(
                panelPuntuacion
        );

        tarjetaPrincipal.add(
                contenedorPuntuacion,
                BorderLayout.NORTH
        );

        // =================================================
        // CENTRO DE RESULTADOS
        // =================================================

        JPanel centroResultados
                = new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centroResultados.setOpaque(false);

        // =================================================
        // NIVELES
        // =================================================

        JPanel panelNiveles
                = new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        panelNiveles.setOpaque(false);

        for (
                int nivel = 1;
                nivel <= 3;
                nivel++
        ) {

            panelNiveles.add(
                    crearTarjetaNivel(
                            nivel
                    )
            );
        }

        centroResultados.add(
                panelNiveles,
                BorderLayout.CENTER
        );

        // =================================================
        // RESUMEN GENERAL
        // =================================================

        JPanel panelResumen
                = new JPanel(
                        new GridLayout(
                                1,
                                2,
                                18,
                                0
                        )
                );

        panelResumen.setOpaque(false);

        panelResumen.setPreferredSize(
                new Dimension(
                        900,
                        70
                )
        );

        panelResumen.add(
                crearDatoResumen(
                        "MOVIMIENTOS TOTALES",
                        String.valueOf(
                                progreso
                                        .getMovimientosTotales()
                        )
                )
        );

        panelResumen.add(
                crearDatoResumen(
                        "TIEMPO TOTAL",
                        formatearTiempo(
                                progreso
                                        .getSegundosTotales()
                        )
                )
        );

        centroResultados.add(
                panelResumen,
                BorderLayout.SOUTH
        );

        tarjetaPrincipal.add(
                centroResultados,
                BorderLayout.CENTER
        );

        contenedorCentral.add(
                tarjetaPrincipal
        );

        fondo.add(
                contenedorCentral,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTONES INFERIORES
        // =================================================

        JPanel panelBotones
                = new JPanel();

        panelBotones.setLayout(
                new BoxLayout(
                        panelBotones,
                        BoxLayout.X_AXIS
                )
        );

        panelBotones.setOpaque(false);

        /*
         * =============================================
         * JUGAR OTRA VEZ
         * =============================================
         *
         * MISMO ESTILO DEL BOTÓN JUGAR DEL MENÚ.
         */
        BotonRedondeado btnJugarOtraVez
                = crearBoton(
                        "JUGAR OTRA VEZ",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        /*
         * =============================================
         * VER RANKING
         * =============================================
         *
         * Azul como el segundo botón del menú.
         */
        BotonRedondeado btnRanking
                = crearBoton(
                        "VER RANKING",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        /*
         * =============================================
         * MENÚ DEL JUEGO
         * =============================================
         *
         * También azul para que se vea
         * conectado al botón SALIR del menú.
         */
        BotonRedondeado btnMenu
                = crearBoton(
                        "MENÚ DEL JUEGO",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        // =================================================
        // EVENTO JUGAR OTRA VEZ
        // =================================================

        btnJugarOtraVez.addActionListener(
                e -> {

                    GestorMusica.detenerFondo();

                    ProgresoJuego nuevo
                            = new ProgresoJuego(
                                    progreso.getJugador(),
                                    progreso.getSeccion(),
                                    progreso
                                            .getAccionVolverPrincipal()
                            );

                    new Nivel1Form(
                            nuevo
                    ).setVisible(true);

                    dispose();
                }
        );

        // =================================================
        // EVENTO RANKING
        // =================================================

        btnRanking.addActionListener(
                e -> {

                    new RankingForm()
                            .setVisible(true);
                }
        );

        // =================================================
        // EVENTO MENÚ
        // =================================================

        btnMenu.addActionListener(
                e -> {

                    GestorMusica.detenerFondo();

                    new MenuJuego(
                            progreso.getJugador(),
                            progreso.getSeccion(),
                            progreso
                                    .getAccionVolverPrincipal()
                    ).setVisible(true);

                    dispose();
                }
        );

        // =================================================
        // CENTRAR BOTONES
        // =================================================

        panelBotones.add(
                Box.createHorizontalGlue()
        );

        panelBotones.add(
                btnJugarOtraVez
        );

        panelBotones.add(
                Box.createHorizontalStrut(
                        20
                )
        );

        panelBotones.add(
                btnRanking
        );

        panelBotones.add(
                Box.createHorizontalStrut(
                        20
                )
        );

        panelBotones.add(
                btnMenu
        );

        panelBotones.add(
                Box.createHorizontalGlue()
        );

        fondo.add(
                panelBotones,
                BorderLayout.SOUTH
        );

        fondo.revalidate();
        fondo.repaint();
    }

    // =====================================================
    // TARJETA INDIVIDUAL DE NIVEL
    // =====================================================

    private JPanel crearTarjetaNivel(
            int nivel
    ) {

        /*
         * =============================================
         * AQUÍ ESTÁ EL CAMBIO PRINCIPAL.
         * =============================================
         *
         * Ya NO hay colores diferentes
         * dependiendo del nivel.
         *
         * NIVEL 1
         * NIVEL 2
         * NIVEL 3
         *
         * usan exactamente el color que
         * antes tenía solamente NIVEL 3.
         */
        PanelRedondeado tarjetaNivel
                = new PanelRedondeado(
                        26,
                        COLOR_RESULTADO,
                        BORDE_RESULTADO
                );

        tarjetaNivel.setLayout(
                new BoxLayout(
                        tarjetaNivel,
                        BoxLayout.Y_AXIS
                )
        );

        tarjetaNivel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        12,
                        15,
                        12
                )
        );

        // =================================================
        // NIVEL
        // =================================================

        JLabel lblNivel
                = new JLabel(
                        "NIVEL "
                        + nivel
                );

        lblNivel.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        18f
                )
        );

        lblNivel.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblNivel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // PUNTOS
        // =================================================

        JLabel lblPuntos
                = new JLabel(
                        progreso
                                .getPuntosNivel(
                                        nivel
                                )
                        + " PTS"
                );

        lblPuntos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        25f
                )
        );

        lblPuntos.setForeground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        lblPuntos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // MOVIMIENTOS
        // =================================================

        JLabel lblMovimientos
                = new JLabel(
                        progreso
                                .getMovimientosNivel(
                                        nivel
                                )
                        + " MOV."
                );

        lblMovimientos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        lblMovimientos.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        lblMovimientos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // TIEMPO
        // =================================================

        JLabel lblTiempo
                = new JLabel(
                        formatearTiempo(
                                progreso
                                        .getSegundosNivel(
                                                nivel
                                        )
                        )
                );

        lblTiempo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        17f
                )
        );

        lblTiempo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblTiempo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // ORDEN
        // =================================================

        tarjetaNivel.add(
                Box.createVerticalGlue()
        );

        tarjetaNivel.add(
                lblNivel
        );

        tarjetaNivel.add(
                Box.createVerticalStrut(
                        9
                )
        );

        tarjetaNivel.add(
                lblPuntos
        );

        tarjetaNivel.add(
                Box.createVerticalStrut(
                        12
                )
        );

        tarjetaNivel.add(
                lblMovimientos
        );

        tarjetaNivel.add(
                Box.createVerticalStrut(
                        8
                )
        );

        tarjetaNivel.add(
                lblTiempo
        );

        tarjetaNivel.add(
                Box.createVerticalGlue()
        );

        return tarjetaNivel;
    }

    // =====================================================
    // RESUMEN GENERAL
    // =====================================================

    private JPanel crearDatoResumen(
            String titulo,
            String valor
    ) {

        /*
         * ESTE PANEL LO DEJAMOS COMO ESTABA
         * EN EL DISEÑO CLARO.
         */
        PanelRedondeado panel
                = new PanelRedondeado(
                        20,
                        new Color(
                                247,
                                255,
                                255,
                                245
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );

        JLabel lblTitulo
                = new JLabel(
                        titulo
                );

        lblTitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        12f
                )
        );

        lblTitulo.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblValor
                = new JLabel(
                        valor
                );

        lblValor.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        19f
                )
        );

        lblValor.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblValor.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                Box.createVerticalGlue()
        );

        panel.add(
                lblTitulo
        );

        panel.add(
                Box.createVerticalStrut(
                        4
                )
        );

        panel.add(
                lblValor
        );

        panel.add(
                Box.createVerticalGlue()
        );

        return panel;
    }

    // =====================================================
    // CREAR BOTÓN
    // =====================================================

    /*
     * Ahora los botones funcionan visualmente
     * igual que en MenuJuego.
     */
    private BotonRedondeado crearBoton(
            String texto,
            Color colorNormal,
            Color colorHover
    ) {

        BotonRedondeado boton
                = new BotonRedondeado(
                        texto,
                        colorNormal,
                        colorHover
                );

        boton.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        boton.setForeground(
                Color.WHITE
        );

        Dimension tamanoBoton
                = new Dimension(
                        300,
                        58
                );

        boton.setPreferredSize(
                tamanoBoton
        );

        boton.setMinimumSize(
                tamanoBoton
        );

        boton.setMaximumSize(
                tamanoBoton
        );

        return boton;
    }

    // =====================================================
    // FORMATO DE TIEMPO
    // =====================================================

    private String formatearTiempo(
            int totalSegundos
    ) {

        int minutos
                = totalSegundos / 60;

        int segundos
                = totalSegundos % 60;

        return String.format(
                "%02d:%02d",
                minutos,
                segundos
        );
    }
}