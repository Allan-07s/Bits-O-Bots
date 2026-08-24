package JuegoMemoria;

import Avatar.AvatarPanel;
import Login.PanelMenuCircuitos;
import Tipografias.Fuentes;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NivelCompletadoForm extends JFrame {

    private final ProgresoJuego progreso;
    private final int numeroNivel;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public NivelCompletadoForm(
            ProgresoJuego progreso,
            int numeroNivel,
            int cantidadParejas,
            int puntosNivel,
            int bonificacion,
            int movimientos,
            int segundos
    ) {

        this.progreso = progreso;
        this.numeroNivel = numeroNivel;

        setTitle(
                "Bits o Bots - Nivel completado"
        );

        setUndecorated(true);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );

        setResizable(false);

        construirInterfaz(
                puntosNivel
        );
    }

    // =====================================================
    // CONSTRUIR INTERFAZ
    // =====================================================

    private void construirInterfaz(
            int puntosNivel
    ) {

        // =================================================
        // RESOLUCIÓN
        // =================================================

        Dimension pantalla
                = Toolkit
                        .getDefaultToolkit()
                        .getScreenSize();

        int anchoPantalla
                = pantalla.width;

        int altoPantalla
                = pantalla.height;

        // =================================================
        // TAMAÑOS
        // =================================================

        /*
         * PANEL DE PUNTOS
         */
        int anchoPuntos
                = limitar(
                        (int) (anchoPantalla * 0.45),
                        650,
                        850
                );

        int altoPuntos
                = limitar(
                        (int) (altoPantalla * 0.39),
                        335,
                        415
                );

        /*
         * AVATAR MÁS GRANDE.
         */
        int anchoRobot
                = limitar(
                        (int) (anchoPantalla * 0.35),
                        520,
                        680
                );

        int altoRobot
                = limitar(
                        (int) (altoPantalla * 0.45),
                        390,
                        485
                );

        /*
         * BURBUJA
         */
        int anchoBurbuja
                = limitar(
                        (int) (anchoRobot * 0.76),
                        390,
                        480
                );

        int altoBurbuja
                = limitar(
                        (int) (altoPantalla * 0.115),
                        100,
                        120
                );

        /*
         * BOTÓN
         */
        int anchoBoton
                = limitar(
                        (int) (anchoPantalla * 0.37),
                        520,
                        680
                );

        int altoBoton
                = limitar(
                        (int) (altoPantalla * 0.075),
                        66,
                        80
                );

        // =================================================
        // FONDO CON CIRCUITOS
        // =================================================

        PanelMenuCircuitos fondo
                = new PanelMenuCircuitos();

        fondo.setLayout(
                new BorderLayout()
        );

        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        30,
                        18,
                        30
                )
        );

        setContentPane(
                fondo
        );

        // =================================================
        // PARTE SUPERIOR
        // =================================================

        /*
         * AQUÍ SOLAMENTE QUEDA:
         *
         * ¡NIVEL X!
         * COMPLETADO!
         *
         * Todo lo demás queda abajo y centrado.
         */
        JPanel parteSuperior
                = new JPanel(
                        new BorderLayout()
                );

        parteSuperior.setOpaque(false);

        parteSuperior.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        0,
                        0
                )
        );

        // =================================================
        // ESPACIO IZQUIERDO
        // =================================================

        JPanel espacioIzquierdo
                = new JPanel();

        espacioIzquierdo.setOpaque(false);

        Dimension tamanoLateral
                = new Dimension(
                        70,
                        70
                );

        espacioIzquierdo.setPreferredSize(
                tamanoLateral
        );

        parteSuperior.add(
                espacioIzquierdo,
                BorderLayout.WEST
        );

        // =================================================
        // TÍTULO
        // =================================================

        JPanel zonaTitulo
                = new JPanel();

        zonaTitulo.setOpaque(false);

        zonaTitulo.setLayout(
                new BoxLayout(
                        zonaTitulo,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel lblNivel
                = new JLabel(
                        "¡NIVEL "
                        + numeroNivel
                        + "!",
                        SwingConstants.CENTER
                );

        lblNivel.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        30f
                )
        );

        lblNivel.setForeground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        lblNivel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblCompletado
                = new JLabel(
                        "COMPLETADO!",
                        SwingConstants.CENTER
                );

        lblCompletado.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        62f
                )
        );

        lblCompletado.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblCompletado.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        zonaTitulo.add(
                lblNivel
        );

        zonaTitulo.add(
                Box.createVerticalStrut(
                        0
                )
        );

        zonaTitulo.add(
                lblCompletado
        );

        parteSuperior.add(
                zonaTitulo,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTÓN DE MÚSICA
        // =================================================

        JPanel zonaMusica
                = new JPanel(
                        new GridBagLayout()
                );

        zonaMusica.setOpaque(false);

        zonaMusica.setPreferredSize(
                tamanoLateral
        );

        zonaMusica.setMinimumSize(
                tamanoLateral
        );

        zonaMusica.setMaximumSize(
                tamanoLateral
        );

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        Dimension tamanoMusica
                = new Dimension(
                        60,
                        60
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

        parteSuperior.add(
                zonaMusica,
                BorderLayout.EAST
        );

        fondo.add(
                parteSuperior,
                BorderLayout.NORTH
        );

        // =================================================
        // ZONA CENTRAL GENERAL
        // =================================================

        /*
         * ESTA ES LA CORRECCIÓN IMPORTANTE.
         *
         * Antes estaba NORTH y por eso panel
         * y robot se iban demasiado arriba.
         *
         * Ahora GridBagLayout los CENTRA.
         */
        JPanel centroGeneral
                = new JPanel(
                        new GridBagLayout()
                );

        centroGeneral.setOpaque(false);

        fondo.add(
                centroGeneral,
                BorderLayout.CENTER
        );

        // =================================================
        // BLOQUE PRINCIPAL
        // =================================================

        JPanel bloquePrincipal
                = new JPanel();

        bloquePrincipal.setOpaque(false);

        bloquePrincipal.setLayout(
                new BoxLayout(
                        bloquePrincipal,
                        BoxLayout.Y_AXIS
                )
        );

        bloquePrincipal.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // FILA CENTRAL
        // =================================================

        /*
         * PANEL DE PUNTOS y ROBOT
         * viven en LA MISMA FILA.
         */
        JPanel filaCentral
                = new JPanel(
                        new GridBagLayout()
                );

        filaCentral.setOpaque(false);

        filaCentral.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // PANEL DE PUNTOS
        // =================================================

        PanelPuntuacion panelPuntos
                = new PanelPuntuacion();

        Dimension dimensionPuntos
                = new Dimension(
                        anchoPuntos,
                        altoPuntos
                );

        panelPuntos.setPreferredSize(
                dimensionPuntos
        );

        panelPuntos.setMinimumSize(
                dimensionPuntos
        );

        panelPuntos.setMaximumSize(
                dimensionPuntos
        );

        panelPuntos.setLayout(
                new BoxLayout(
                        panelPuntos,
                        BoxLayout.Y_AXIS
                )
        );

        panelPuntos.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        // =================================================
        // NÚMERO DE PUNTOS
        // =================================================

        JLabel lblNumeroPuntos
                = new JLabel(
                        String.valueOf(
                                puntosNivel
                        ),
                        SwingConstants.CENTER
                );

        lblNumeroPuntos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        88f
                )
        );

        lblNumeroPuntos.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblNumeroPuntos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // PALABRA PUNTOS
        // =================================================

        JLabel lblPuntos
                = new JLabel(
                        "PUNTOS",
                        SwingConstants.CENTER
                );

        lblPuntos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        42f
                )
        );

        lblPuntos.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblPuntos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPuntos.add(
                Box.createVerticalGlue()
        );

        panelPuntos.add(
                lblNumeroPuntos
        );

        panelPuntos.add(
                Box.createVerticalStrut(
                        12
                )
        );

        panelPuntos.add(
                lblPuntos
        );

        panelPuntos.add(
                Box.createVerticalGlue()
        );

        // =================================================
        // COLOCAR PANEL DE PUNTOS
        // =================================================

        GridBagConstraints gbcPuntos
                = new GridBagConstraints();

        gbcPuntos.gridx
                = 0;

        gbcPuntos.gridy
                = 0;

        gbcPuntos.weightx
                = 1.0;

        gbcPuntos.weighty
                = 1.0;

        /*
         * CENTER.
         *
         * Ya no NORTH.
         */
        gbcPuntos.anchor
                = GridBagConstraints.CENTER;

        gbcPuntos.insets
                = new Insets(
                        0,
                        0,
                        0,
                        35
                );

        filaCentral.add(
                panelPuntos,
                gbcPuntos
        );

        // =================================================
        // BLOQUE ROBOT
        // =================================================

        JPanel bloqueRobot
                = new JPanel();

        bloqueRobot.setOpaque(false);

        bloqueRobot.setLayout(
                new BoxLayout(
                        bloqueRobot,
                        BoxLayout.Y_AXIS
                )
        );

        /*
         * Como el robot ahora es más alto que
         * el panel de puntos, NO queremos usar
         * toda esa altura para empujarlo arriba.
         */
        int altoBloqueRobot
                = altoBurbuja
                + altoRobot;

        Dimension dimensionBloqueRobot
                = new Dimension(
                        anchoRobot,
                        altoBloqueRobot
                );

        bloqueRobot.setPreferredSize(
                dimensionBloqueRobot
        );

        bloqueRobot.setMinimumSize(
                dimensionBloqueRobot
        );

        bloqueRobot.setMaximumSize(
                dimensionBloqueRobot
        );

        // =================================================
        // BURBUJA
        // =================================================

        BurbujaMensaje burbuja
                = new BurbujaMensaje(
                        Color.WHITE,
                        ColoresBitsOBots.TURQUESA_OSCURO
                );

        burbuja.setRadio(
                28
        );

        burbuja.setTamanoPunta(
                38,
                20
        );

        burbuja.setLayout(
                new BorderLayout()
        );

        Dimension dimensionBurbuja
                = new Dimension(
                        anchoBurbuja,
                        altoBurbuja
                );

        burbuja.setPreferredSize(
                dimensionBurbuja
        );

        burbuja.setMinimumSize(
                dimensionBurbuja
        );

        burbuja.setMaximumSize(
                dimensionBurbuja
        );

        burbuja.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // TEXTO DE BURBUJA
        // =================================================

        JPanel textoBurbuja
                = new JPanel();

        textoBurbuja.setOpaque(false);

        textoBurbuja.setLayout(
                new BoxLayout(
                        textoBurbuja,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel lblRobotNivel
                = new JLabel(
                        "¡NIVEL "
                        + numeroNivel
                        + " COMPLETADO!"
                );

        lblRobotNivel.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        17f
                )
        );

        lblRobotNivel.setForeground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        lblRobotNivel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblExcelente
                = new JLabel(
                        "¡EXCELENTE TRABAJO!"
                );

        lblExcelente.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        15f
                )
        );

        lblExcelente.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        lblExcelente.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        textoBurbuja.add(
                Box.createVerticalGlue()
        );

        textoBurbuja.add(
                lblRobotNivel
        );

        textoBurbuja.add(
                Box.createVerticalStrut(
                        6
                )
        );

        textoBurbuja.add(
                lblExcelente
        );

        textoBurbuja.add(
                Box.createVerticalGlue()
        );

        burbuja.add(
                textoBurbuja,
                BorderLayout.CENTER
        );

        // =================================================
        // PANEL AVATAR
        // =================================================

        JPanel panelAvatar
                = new JPanel(
                        new GridBagLayout()
                );

        panelAvatar.setOpaque(false);

        Dimension dimensionPanelAvatar
                = new Dimension(
                        anchoRobot,
                        altoRobot
                );

        panelAvatar.setPreferredSize(
                dimensionPanelAvatar
        );

        panelAvatar.setMinimumSize(
                dimensionPanelAvatar
        );

        panelAvatar.setMaximumSize(
                dimensionPanelAvatar
        );

        panelAvatar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // AVATAR MÁS GRANDE
        // =================================================

        AvatarPanel avatar
                = new AvatarPanel();

        /*
         * CASI TODO EL PANEL DERECHO
         * lo aprovecha el avatar.
         */
        int anchoAvatar
                = anchoRobot - 2;

        int altoAvatar
                = altoRobot - 2;

        avatar.setAvatarSize(
                anchoAvatar,
                altoAvatar
        );

        /*
         * Lo dejamos solamente un 2% más grande.
         *
         * El crecimiento fuerte viene del
         * COMPONENTE, no de deformar la escala.
         */
        avatar.setAvatarScale(
                1.02
        );

        avatar.setAvatarPosition(
                0,
                0
        );

        Dimension dimensionAvatar
                = new Dimension(
                        anchoAvatar,
                        altoAvatar
                );

        avatar.setPreferredSize(
                dimensionAvatar
        );

        avatar.setMinimumSize(
                dimensionAvatar
        );

        avatar.setMaximumSize(
                dimensionAvatar
        );

        panelAvatar.add(
                avatar
        );

        avatar.startAnimation();

        // =================================================
        // ARMAR BURBUJA + AVATAR
        // =================================================

        bloqueRobot.add(
                burbuja
        );

        /*
         * Prácticamente pegados.
         */
        bloqueRobot.add(
                Box.createVerticalStrut(
                        0
                )
        );

        bloqueRobot.add(
                panelAvatar
        );

        // =================================================
        // COLOCAR ROBOT
        // =================================================

        GridBagConstraints gbcRobot
                = new GridBagConstraints();

        gbcRobot.gridx
                = 1;

        gbcRobot.gridy
                = 0;

        gbcRobot.weightx
                = 1.0;

        gbcRobot.weighty
                = 1.0;

        /*
         * CENTER TAMBIÉN.
         *
         * Esto es lo que hace que panel
         * y robot se vean equilibrados.
         */
        gbcRobot.anchor
                = GridBagConstraints.CENTER;

        gbcRobot.insets
                = new Insets(
                        0,
                        35,
                        0,
                        0
                );

        filaCentral.add(
                bloqueRobot,
                gbcRobot
        );

        // =================================================
        // AGREGAR FILA CENTRAL
        // =================================================

        bloquePrincipal.add(
                filaCentral
        );

        /*
         * Distancia hacia el botón.
         */
        bloquePrincipal.add(
                Box.createVerticalStrut(
                        22
                )
        );

        // =================================================
        // BOTÓN CONTINUAR
        // =================================================

        String textoBoton;

        if (
                numeroNivel == 1
        ) {

            textoBoton
                    = "CONTINUAR AL NIVEL 2  »";

        } else if (
                numeroNivel == 2
        ) {

            textoBoton
                    = "CONTINUAR AL NIVEL 3  »";

        } else {

            textoBoton
                    = "VER RESULTADO FINAL  »";
        }

        BotonRedondeado btnContinuar
                = new BotonRedondeado(
                        textoBoton,
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        btnContinuar.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        19f
                )
        );

        btnContinuar.setForeground(
                Color.WHITE
        );

        Dimension tamanoContinuar
                = new Dimension(
                        anchoBoton,
                        altoBoton
                );

        btnContinuar.setPreferredSize(
                tamanoContinuar
        );

        btnContinuar.setMinimumSize(
                tamanoContinuar
        );

        btnContinuar.setMaximumSize(
                tamanoContinuar
        );

        btnContinuar.addActionListener(
                e -> {

                    abrirSiguientePantalla();
                }
        );

        JPanel contenedorBoton
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorBoton.setOpaque(false);

        contenedorBoton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contenedorBoton.add(
                btnContinuar
        );

        bloquePrincipal.add(
                contenedorBoton
        );

        // =================================================
        // CENTRAR TODO
        // =================================================

        GridBagConstraints gbcPrincipal
                = new GridBagConstraints();

        gbcPrincipal.gridx
                = 0;

        gbcPrincipal.gridy
                = 0;

        gbcPrincipal.weightx
                = 1.0;

        gbcPrincipal.weighty
                = 1.0;

        /*
         * LA CLAVE:
         *
         * CENTER.
         *
         * Ya no NORTH.
         *
         * Por eso el panel de puntos,
         * burbuja, avatar y botón quedan
         * centrados verticalmente.
         */
        gbcPrincipal.anchor
                = GridBagConstraints.CENTER;

        /*
         * Apenas 5px hacia abajo.
         */
        gbcPrincipal.insets
                = new Insets(
                        5,
                        0,
                        0,
                        0
                );

        centroGeneral.add(
                bloquePrincipal,
                gbcPrincipal
        );

        fondo.revalidate();
        fondo.repaint();
    }

    // =====================================================
    // LIMITAR
    // =====================================================

    private int limitar(
            int valor,
            int minimo,
            int maximo
    ) {

        return Math.max(
                minimo,
                Math.min(
                        valor,
                        maximo
                )
        );
    }

    // =====================================================
    // SIGUIENTE PANTALLA
    // =====================================================

    private void abrirSiguientePantalla() {

        if (
                numeroNivel == 1
        ) {

            new Nivel2Form(
                    progreso
            ).setVisible(
                    true
            );

        } else if (
                numeroNivel == 2
        ) {

            new Nivel3Form(
                    progreso
            ).setVisible(
                    true
            );

        } else {

            new PantallaFinal(
                    progreso
            ).setVisible(
                    true
            );
        }

        dispose();
    }

    // =====================================================
    // PANEL DE PUNTUACIÓN
    // =====================================================

    private static class PanelPuntuacion
            extends JPanel {

        public PanelPuntuacion() {

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2
                    = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w
                    = getWidth();

            int h
                    = getHeight();

            // =================================================
            // SOMBRA
            // =================================================

            g2.setColor(
                    new Color(
                            28,
                            107,
                            126,
                            30
                    )
            );

            g2.fillRoundRect(
                    8,
                    10,
                    w - 16,
                    h - 16,
                    44,
                    44
            );

            // =================================================
            // FONDO BLANCO
            // =================================================

            g2.setColor(
                    new Color(
                            255,
                            255,
                            255,
                            248
                    )
            );

            g2.fillRoundRect(
                    4,
                    4,
                    w - 12,
                    h - 14,
                    44,
                    44
            );

            // =================================================
            // CELESTE MUY SUAVE
            // =================================================

            g2.setColor(
                    new Color(
                            178,
                            254,
                            255,
                            28
                    )
            );

            g2.fillRoundRect(
                    14,
                    14,
                    w - 32,
                    h - 34,
                    36,
                    36
            );

            // =================================================
            // BORDE
            // =================================================

            g2.setColor(
                    ColoresBitsOBots.BORDE_TURQUESA
            );

            g2.setStroke(
                    new BasicStroke(
                            2.4f
                    )
            );

            g2.drawRoundRect(
                    4,
                    4,
                    w - 12,
                    h - 14,
                    44,
                    44
            );

            // =================================================
            // BORDE INTERIOR
            // =================================================

            g2.setColor(
                    new Color(
                            188,
                            231,
                            234
                    )
            );

            g2.setStroke(
                    new BasicStroke(
                            1.2f
                    )
            );

            g2.drawRoundRect(
                    14,
                    14,
                    w - 32,
                    h - 34,
                    36,
                    36
            );

            // =================================================
            // DETALLES
            // =================================================

            g2.setColor(
                    new Color(
                            36,
                            209,
                            212,
                            60
                    )
            );

            g2.fillOval(
                    70,
                    70,
                    7,
                    7
            );

            g2.fillOval(
                    w - 80,
                    82,
                    7,
                    7
            );

            g2.fillOval(
                    94,
                    h - 78,
                    6,
                    6
            );

            g2.fillOval(
                    w - 100,
                    h - 90,
                    7,
                    7
            );

            g2.dispose();

            super.paintComponent(
                    g
            );
        }
    }
}