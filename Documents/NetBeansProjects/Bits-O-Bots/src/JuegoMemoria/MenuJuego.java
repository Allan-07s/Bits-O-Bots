package JuegoMemoria;

import Tipografias.Fuentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuJuego extends JFrame {

    private final String jugadorC;
    private final String seccionC;
    private final Runnable accionVolverPrincipal;

    private BotonIconoMusica btnMusica;

    /*
     * =====================================================
     * CONSTRUCTOR NORMAL
     * =====================================================
     */
    public MenuJuego(
            String jugador,
            String seccion
    ) {

        this(
                jugador,
                seccion,
                null
        );
    }

    /*
     * =====================================================
     * CONSTRUCTOR CON ACCIÓN PARA VOLVER
     * =====================================================
     */
    public MenuJuego(
            String jugador,
            String seccion,
            Runnable accionVolverPrincipal
    ) {

        String nombreLimpio
                = jugador == null
                ? "Jugador"
                : jugador.trim();

        if (nombreLimpio.isEmpty()) {

            nombreLimpio
                    = "Jugador";
        }

        String seccionLimpia
                = seccion == null
                ? "Sin sección"
                : seccion.trim();

        if (seccionLimpia.isEmpty()) {

            seccionLimpia
                    = "Sin sección";
        }

        this.jugadorC
                = nombreLimpio;

        this.seccionC
                = seccionLimpia;

        this.accionVolverPrincipal
                = accionVolverPrincipal;

        configurarVentana();

        construirInterfaz();

        GestorMusica.reproducirFondo(
                "/audio/musica_menu.wav"
        );
    }

    /*
     * =====================================================
     * CONFIGURAR VENTANA
     * =====================================================
     */
    private void configurarVentana() {

        setTitle(
                "Juego de Cartas - Menú"
        );

        setUndecorated(true);

        /*
         * Pantalla completa.
         */
        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );

        setResizable(true);
    }

    /*
     * =====================================================
     * CONSTRUIR INTERFAZ
     * =====================================================
     */
    private void construirInterfaz() {

        /*
         * =================================================
         * FONDO GENERAL
         * =================================================
         *
         * Ya no usamos el morado oscuro.
         * Ahora usamos el mismo estilo claro
         * del Login / Quiz.
         */
        PanelDegradado fondo
                = new PanelDegradado(
                        ColoresBitsOBots.FONDO_SUPERIOR,
                        ColoresBitsOBots.FONDO_INFERIOR
                );

        fondo.setLayout(
                new BorderLayout(
                        18,
                        18
                )
        );

        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        24,
                        44,
                        35,
                        44
                )
        );

        setContentPane(
                fondo
        );

        /*
         * =================================================
         * BARRA SUPERIOR
         * =================================================
         */
        JPanel barraSuperior
                = new JPanel(
                        new BorderLayout()
                );

        barraSuperior.setOpaque(false);

        /*
         * JUGADOR.
         */
        JLabel lblJugador
                = new JLabel(
                        "JUGADOR: "
                        + jugadorC
                );

        lblJugador.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        18f
                )
        );

        /*
         * Antes era casi blanco.
         * Ahora azul oscuro.
         */
        lblJugador.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        /*
         * =================================================
         * BOTÓN DE MÚSICA
         * =================================================
         */
        btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(
                        58,
                        58
                )
        );

        btnMusica.addActionListener(
                e -> {

                    GestorMusica
                            .alternarSilencio();

                    btnMusica.repaint();
                }
        );

        barraSuperior.add(
                lblJugador,
                BorderLayout.WEST
        );

        barraSuperior.add(
                btnMusica,
                BorderLayout.EAST
        );

        fondo.add(
                barraSuperior,
                BorderLayout.NORTH
        );

        /*
         * =================================================
         * TARJETA PRINCIPAL
         * =================================================
         *
         * Blanca, limpia y ligeramente transparente.
         */
        PanelRedondeado panelPrincipal
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

        panelPrincipal.setLayout(
                new BorderLayout(
                        14,
                        14
                )
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        70,
                        30,
                        70
                )
        );

        /*
         * =================================================
         * LOGO
         * =================================================
         */
        LogoMemoria logo
                = new LogoMemoria();

        logo.setPreferredSize(
                new Dimension(
                        420,
                        180
                )
        );

        panelPrincipal.add(
                logo,
                BorderLayout.NORTH
        );

        /*
         * =================================================
         * CENTRO
         * =================================================
         */
        JPanel centro
                = new JPanel(
                        new BorderLayout(
                                8,
                                18
                        )
                );

        centro.setOpaque(false);

        /*
         * =================================================
         * TÍTULO
         * =================================================
         */
        JLabel lblTitulo
                = new JLabel(
                        "BITS O BOTS",
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        40f
                )
        );

        lblTitulo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        /*
         * =================================================
         * SUBTÍTULO
         * =================================================
         */
        JLabel lblSubtitulo
                = new JLabel(
                        "MEMORIZA COMPONENTES DE INFORMÁTICA Y ROBÓTICA",
                        SwingConstants.CENTER
                );

        /*
         * Antes estaba en 40f y era enorme.
         * Lo dejamos más parecido al diseño
         * limpio del Quiz.
         */
        lblSubtitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        18f
                )
        );

        lblSubtitulo.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        /*
         * Contenedor de textos.
         */
        JPanel textos
                = new JPanel(
                        new GridLayout(
                                2,
                                1,
                                2,
                                2
                        )
                );

        textos.setOpaque(false);

        textos.add(
                lblTitulo
        );

        textos.add(
                lblSubtitulo
        );

        centro.add(
                textos,
                BorderLayout.NORTH
        );

        /*
         * =================================================
         * PANEL DE BOTONES
         * =================================================
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

        /*
         * =================================================
         * BOTÓN JUGAR
         * =================================================
         *
         * Robótica:
         * #24D1D4
         *
         * Usamos una versión ligeramente
         * más oscura para que el texto blanco
         * se lea perfectamente.
         */
        BotonRedondeado btnJugar
                = new BotonRedondeado(
                        "JUGAR",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        btnJugar.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        22f
                )
        );

        btnJugar.setForeground(
                Color.WHITE
        );

        /*
         * Mantenemos tus medidas.
         */
        Dimension tamanoJugar
                = new Dimension(
                        800,
                        160
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

        btnJugar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnJugar.addActionListener(
                e -> {

                    iniciarJuego();
                }
        );

        /*
         * =================================================
         * BOTÓN SALIR
         * =================================================
         *
         * Ya no usamos rojo porque queremos
         * mantener la misma identidad del Quiz.
         *
         * Utilizamos azul.
         */
        BotonRedondeado btnSalir
                = new BotonRedondeado(
                        "SALIR",
                        ColoresBitsOBots.AZUL_PRINCIPAL,
                        ColoresBitsOBots.AZUL_HOVER
                );

        btnSalir.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        22f
                )
        );

        btnSalir.setForeground(
                Color.WHITE
        );

        /*
         * Mantenemos tus medidas.
         */
        Dimension tamanoSalir
                = new Dimension(
                        800,
                        160
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

        btnSalir.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnSalir.addActionListener(
                e -> {

                    salirDelJuego();
                }
        );

        /*
         * =================================================
         * ORGANIZACIÓN VERTICAL
         * =================================================
         */

        botones.add(
                Box.createVerticalGlue()
        );

        /*
         * JUGAR
         */
        botones.add(
                btnJugar
        );

        /*
         * Separación.
         */
        botones.add(
                Box.createVerticalStrut(
                        25
                )
        );

        /*
         * SALIR
         */
        botones.add(
                btnSalir
        );

        botones.add(
                Box.createVerticalGlue()
        );

        /*
         * Agregamos los botones
         * al centro.
         */
        centro.add(
                botones,
                BorderLayout.CENTER
        );

        panelPrincipal.add(
                centro,
                BorderLayout.CENTER
        );

        /*
         * =================================================
         * AGREGAR TARJETA PRINCIPAL
         * =================================================
         */
        fondo.add(
                panelPrincipal,
                BorderLayout.CENTER
        );

        /*
         * =================================================
         * PIE
         * =================================================
         */
        JLabel lblPie
                = new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        lblPie.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        lblPie.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        fondo.add(
                lblPie,
                BorderLayout.SOUTH
        );

        /*
         * Refrescar.
         */
        fondo.revalidate();
        fondo.repaint();
    }

    /*
     * =====================================================
     * INICIAR JUEGO
     * =====================================================
     */
    private void iniciarJuego() {

        ProgresoJuego progreso
                = new ProgresoJuego(
                        jugadorC,
                        seccionC,
                        accionVolverPrincipal
                );

        new Nivel1Form(
                progreso
        ).setVisible(true);

        dispose();
    }

    /*
     * =====================================================
     * SALIR DEL JUEGO
     * =====================================================
     */
    private void salirDelJuego() {

        GestorMusica.detenerFondo();

        dispose();

        if (
                accionVolverPrincipal
                != null
        ) {

            accionVolverPrincipal.run();
        }
    }
}