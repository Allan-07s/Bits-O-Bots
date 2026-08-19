package JuegoMemoria;

import Avatar.AvatarPanel;
import Tipografias.Fuentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

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

        setResizable(true);

        construirInterfaz(
                puntosNivel
        );
    }

    private void construirInterfaz(
            int puntosNivel
    ) {

        // =================================================
        // FONDO
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
                        20,
                        42,
                        35,
                        42
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

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(
                        52,
                        52
                )
        );

        btnMusica.addActionListener(
                e -> {

                    GestorMusica.alternarSilencio();

                    btnMusica.repaint();
                }
        );

        barraSuperior.add(
                btnMusica,
                BorderLayout.EAST
        );

        fondo.add(
                barraSuperior,
                BorderLayout.NORTH
        );

        // =================================================
        // TARJETA CENTRAL
        // =================================================

        PanelRedondeado tarjeta
                = new PanelRedondeado(
                        40,
                        new Color(
                                255,
                                255,
                                255,
                                225
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        tarjeta.setLayout(
                new BorderLayout(
                        15,
                        25
                )
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        32,
                        35,
                        32,
                        35
                )
        );

        // =================================================
        // TÍTULO
        // =================================================

        JLabel lblTitulo
                = new JLabel(
                        "¡NIVEL "
                        + numeroNivel
                        + " COMPLETADO!",
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        50f
                )
        );

        lblTitulo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        tarjeta.add(
                lblTitulo,
                BorderLayout.NORTH
        );

        // =================================================
        // CONTENIDO CENTRAL
        // =================================================

        JPanel contenidoCentral
                = new JPanel(
                        new BorderLayout(
                                30,
                                0
                        )
                );

        contenidoCentral.setOpaque(false);

        // =================================================
        // CUADRO DE PUNTOS
        // =================================================

        PanelRedondeado panelPuntos
                = new PanelRedondeado(
                        30,
                        new Color(
                                234,
                                253,
                                254
                        ),
                        ColoresBitsOBots.BORDE_TURQUESA
                );

        panelPuntos.setLayout(
                new BorderLayout()
        );

        Dimension tamanoPanelPuntos
                = new Dimension(
                        650,
                        320
                );

        panelPuntos.setPreferredSize(
                tamanoPanelPuntos
        );

        panelPuntos.setMinimumSize(
                tamanoPanelPuntos
        );

        panelPuntos.setMaximumSize(
                tamanoPanelPuntos
        );

        panelPuntos.setBorder(
                BorderFactory.createEmptyBorder(
                        28,
                        20,
                        28,
                        20
                )
        );

        JLabel lblPuntos
                = new JLabel(
                        puntosNivel
                        + " PUNTOS",
                        SwingConstants.CENTER
                );

        lblPuntos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        50f
                )
        );

        lblPuntos.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        panelPuntos.add(
                lblPuntos,
                BorderLayout.CENTER
        );

        JPanel contenedorPuntos
                = new JPanel(
                        new GridBagLayout()
                );

        contenedorPuntos.setOpaque(false);

        contenedorPuntos.add(
                panelPuntos
        );

        contenidoCentral.add(
                contenedorPuntos,
                BorderLayout.CENTER
        );

        // =================================================
        // PANEL DERECHO
        // =================================================

        JPanel panelAsistente
                = new JPanel();

        panelAsistente.setLayout(
                new BoxLayout(
                        panelAsistente,
                        BoxLayout.Y_AXIS
                )
        );

        panelAsistente.setOpaque(false);

        /*
         * AUMENTADO:
         * Antes era 380 x 450.
         */
        Dimension tamanoAsistente
                = new Dimension(
                        410,
                        500
                );

        panelAsistente.setPreferredSize(
                tamanoAsistente
        );

        panelAsistente.setMinimumSize(
                tamanoAsistente
        );

        panelAsistente.setMaximumSize(
                tamanoAsistente
        );

        // =================================================
        // BURBUJA
        // =================================================

        BurbujaMensaje burbujaNivel
                = new BurbujaMensaje(
                        new Color(
                                255,
                                255,
                                255,
                                245
                        ),
                        ColoresBitsOBots.BORDE_TURQUESA
                );

        burbujaNivel.setTamanoPunta(
                35,
                25
        );

        Dimension tamanoBurbuja
                = new Dimension(
                        345,
                        125
                );

        burbujaNivel.setPreferredSize(
                tamanoBurbuja
        );

        burbujaNivel.setMinimumSize(
                tamanoBurbuja
        );

        burbujaNivel.setMaximumSize(
                tamanoBurbuja
        );

        burbujaNivel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        burbujaNivel.setLayout(
                new BorderLayout()
        );

        burbujaNivel.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        16,
                        24,
                        16
                )
        );

        // =================================================
        // TEXTO DE LA BURBUJA
        // =================================================

        JPanel panelMensaje
                = new JPanel();

        panelMensaje.setLayout(
                new BoxLayout(
                        panelMensaje,
                        BoxLayout.Y_AXIS
                )
        );

        panelMensaje.setOpaque(false);

        JLabel lblNivelRobot
                = new JLabel(
                        "¡NIVEL "
                        + numeroNivel
                        + " COMPLETADO!"
                );

        lblNivelRobot.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        18f
                )
        );

        lblNivelRobot.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblNivelRobot.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblNivelRobot.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        JLabel lblExcelente
                = new JLabel(
                        "¡EXCELENTE TRABAJO!"
                );

        lblExcelente.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        14f
                )
        );

        lblExcelente.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        lblExcelente.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblExcelente.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        panelMensaje.add(
                Box.createVerticalGlue()
        );

        panelMensaje.add(
                lblNivelRobot
        );

        panelMensaje.add(
                Box.createVerticalStrut(
                        8
                )
        );

        panelMensaje.add(
                lblExcelente
        );

        panelMensaje.add(
                Box.createVerticalGlue()
        );

        burbujaNivel.add(
                panelMensaje,
                BorderLayout.CENTER
        );

        // =================================================
        // AVATAR
        // =================================================

        JPanel panelAvatar
                = new JPanel(
                        new BorderLayout()
                );

        panelAvatar.setOpaque(false);

        /*
         * AUMENTADO:
         *
         * Antes:
         * 345 x 270
         *
         * Ahora:
         * 380 x 330
         */
        Dimension tamanoPanelAvatar
                = new Dimension(
                        380,
                        330
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

        // =================================================
        // ROBOT
        // =================================================

        AvatarPanel avatar
                = new AvatarPanel();

        /*
         * AUMENTADO:
         *
         * Antes:
         * 325 x 295
         *
         * Ahora:
         * 365 x 325
         */
        Dimension tamanoAvatar
                = new Dimension(
                        365,
                        325
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

        /*
         * Lo mantenemos abajo
         * como ya lo teníamos.
         */
        panelAvatar.add(
                avatar,
                BorderLayout.SOUTH
        );

        avatar.startAnimation();

        // =================================================
        // ORDENAR ZONA DEL ASISTENTE
        // =================================================

        panelAsistente.add(
                Box.createVerticalGlue()
        );

        panelAsistente.add(
                burbujaNivel
        );

        panelAsistente.add(
                Box.createVerticalStrut(
                        8
                )
        );

        panelAsistente.add(
                panelAvatar
        );

        panelAsistente.add(
                Box.createVerticalGlue()
        );

        contenidoCentral.add(
                panelAsistente,
                BorderLayout.EAST
        );

        tarjeta.add(
                contenidoCentral,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTÓN CONTINUAR
        // =================================================

        String textoBoton;

        if (numeroNivel == 1) {

            textoBoton
                    = "CONTINUAR AL NIVEL 2";

        } else if (numeroNivel == 2) {

            textoBoton
                    = "CONTINUAR AL NIVEL 3";

        } else {

            textoBoton
                    = "VER RESULTADO FINAL";
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
                        17f
                )
        );

        btnContinuar.setForeground(
                Color.WHITE
        );

        Dimension tamanoContinuar
                = new Dimension(
                        470,
                        72
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

        contenedorBoton.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

        contenedorBoton.add(
                btnContinuar
        );

        tarjeta.add(
                contenedorBoton,
                BorderLayout.SOUTH
        );

        // =================================================
        // FINAL
        // =================================================

        fondo.add(
                tarjeta,
                BorderLayout.CENTER
        );

        fondo.revalidate();
        fondo.repaint();
    }

    // =====================================================
    // SIGUIENTE PANTALLA
    // =====================================================

    private void abrirSiguientePantalla() {

        if (numeroNivel == 1) {

            new Nivel2Form(
                    progreso
            ).setVisible(true);

        } else if (numeroNivel == 2) {

            new Nivel3Form(
                    progreso
            ).setVisible(true);

        } else {

            new PantallaFinal(
                    progreso
            ).setVisible(true);
        }

        dispose();
    }
}