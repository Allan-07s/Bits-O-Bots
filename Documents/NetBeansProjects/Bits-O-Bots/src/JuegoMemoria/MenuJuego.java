package JuegoMemoria;

import Login.PanelMenuCircuitos;
import Tipografias.Fuentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
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

    // =====================================================
    // CONSTRUCTOR NORMAL
    // =====================================================

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

    // =====================================================
    // CONSTRUCTOR CON ACCIÓN PARA VOLVER
    // =====================================================

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

    // =====================================================
    // CONFIGURAR VENTANA
    // =====================================================

    private void configurarVentana() {

        setTitle(
                "Juego de Cartas - Menú"
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
    // CONSTRUIR INTERFAZ
    // =====================================================

    private void construirInterfaz() {

        // =================================================
        // FONDO GENERAL CON CIRCUITOS
        // =================================================
        /*
         * CAMBIO:
         *
         * Antes:
         * PanelDegradado
         *
         * Ahora:
         * PanelMenuCircuitos
         *
         * Este panel ya trae:
         * - fondo claro
         * - circuitos
         * - hexágonos
         * - binarios
         * - partículas
         * - esquinas tecnológicas
         */

        PanelMenuCircuitos fondo
                = new PanelMenuCircuitos();

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

        // =================================================
        // BARRA SUPERIOR
        // =================================================

        JPanel barraSuperior
                = new JPanel(
                        new BorderLayout()
                );

        barraSuperior.setOpaque(false);

        // =================================================
        // JUGADOR
        // =================================================

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

        lblJugador.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        // =================================================
        // BOTÓN DE MÚSICA
        // =================================================

        JPanel zonaMusica
                = new JPanel(
                        new GridBagLayout()
                );

        zonaMusica.setOpaque(false);

        Dimension tamanoZonaMusica
                = new Dimension(
                        68,
                        68
                );

        zonaMusica.setPreferredSize(
                tamanoZonaMusica
        );

        zonaMusica.setMinimumSize(
                tamanoZonaMusica
        );

        zonaMusica.setMaximumSize(
                tamanoZonaMusica
        );

        btnMusica
                = new BotonIconoMusica();

        Dimension tamanoMusica
                = new Dimension(
                        58,
                        58
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

                    GestorMusica
                            .alternarSilencio();

                    btnMusica.repaint();
                }
        );

        zonaMusica.add(
                btnMusica
        );

        barraSuperior.add(
                lblJugador,
                BorderLayout.WEST
        );

        barraSuperior.add(
                zonaMusica,
                BorderLayout.EAST
        );

        fondo.add(
                barraSuperior,
                BorderLayout.NORTH
        );

        // =================================================
        // CONTENIDO PRINCIPAL
        // =================================================
        /*
         * IMPORTANTE:
         *
         * AQUÍ QUITAMOS COMPLETAMENTE
         * EL PANEL BLANCO GRANDE.
         *
         * Antes existía:
         *
         * PanelRedondeado panelPrincipal = ...
         *
         * Ya NO existe.
         *
         * Todo se coloca directamente
         * sobre el fondo de circuitos.
         */

        JPanel contenidoPrincipal
                = new JPanel(
                        new BorderLayout(
                                14,
                                14
                        )
                );

        contenidoPrincipal.setOpaque(false);

        contenidoPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        70,
                        20,
                        70
                )
        );

        fondo.add(
                contenidoPrincipal,
                BorderLayout.CENTER
        );

        // =================================================
        // LOGO
        // =================================================

        LogoMemoria logo
                = new LogoMemoria();

        logo.setPreferredSize(
                new Dimension(
                        420,
                        180
                )
        );

        /*
         * Lo metemos en un contenedor transparente
         * para mantenerlo centrado.
         */
        JPanel contenedorLogo
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorLogo.setOpaque(false);

        contenedorLogo.add(
                logo
        );

        contenidoPrincipal.add(
                contenedorLogo,
                BorderLayout.NORTH
        );

        // =================================================
        // CENTRO
        // =================================================

        JPanel centro
                = new JPanel(
                        new BorderLayout(
                                8,
                                18
                        )
                );

        centro.setOpaque(false);

        // =================================================
        // TÍTULO
        // =================================================

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

        // =================================================
        // SUBTÍTULO
        // =================================================

        JLabel lblSubtitulo
                = new JLabel(
                        "MEMORIZA COMPONENTES DE INFORMÁTICA Y ROBÓTICA",
                        SwingConstants.CENTER
                );

        lblSubtitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        18f
                )
        );

        lblSubtitulo.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        // =================================================
        // CONTENEDOR DE TEXTOS
        // =================================================

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

        // =================================================
        // PANEL DE BOTONES
        // =================================================

        JPanel botones
                = new JPanel();

        botones.setLayout(
                new BoxLayout(
                        botones,
                        BoxLayout.Y_AXIS
                )
        );

        botones.setOpaque(false);

        // =================================================
        // BOTÓN JUGAR
        // =================================================

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

        // =================================================
        // BOTÓN SALIR
        // =================================================

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

        // =================================================
        // ORGANIZACIÓN VERTICAL
        // =================================================

        botones.add(
                Box.createVerticalGlue()
        );

        botones.add(
                btnJugar
        );

        botones.add(
                Box.createVerticalStrut(
                        25
                )
        );

        botones.add(
                btnSalir
        );

        botones.add(
                Box.createVerticalGlue()
        );

        centro.add(
                botones,
                BorderLayout.CENTER
        );

        contenidoPrincipal.add(
                centro,
                BorderLayout.CENTER
        );

        // =================================================
        // PIE
        // =================================================

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

        // =================================================
        // REFRESCAR
        // =================================================

        fondo.revalidate();
        fondo.repaint();
    }

    // =====================================================
    // INICIAR JUEGO
    // =====================================================

    private void iniciarJuego() {

        ProgresoJuego progreso
                = new ProgresoJuego(
                        jugadorC,
                        seccionC,
                        accionVolverPrincipal
                );

        new Nivel1Form(
                progreso
        ).setVisible(
                true
        );

        dispose();
    }

    // =====================================================
    // SALIR DEL JUEGO
    // =====================================================

    private void salirDelJuego() {

        GestorMusica
                .detenerFondo();

        dispose();

        if (
                accionVolverPrincipal
                != null
        ) {

            accionVolverPrincipal.run();
        }
    }
}