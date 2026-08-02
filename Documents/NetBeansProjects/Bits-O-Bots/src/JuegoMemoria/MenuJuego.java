package JuegoMemoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
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
     * Este constructor permite volver al menú principal real.
     *
     * Ejemplo:
     * new MenuJuego(nombreRegistrado, () -> {
     *     new MenuPrincipal().setVisible(true);
     * }).setVisible(true);
     */
    public MenuJuego(String jugador, String seccion) {
        this(jugador, seccion, null);
    }
    
    public MenuJuego(
        String jugador,
        String seccion,
        Runnable accionVolverPrincipal
        )
    {

        String nombreLimpio = jugador == null
            ? "Jugador"
            : jugador.trim();

        if (nombreLimpio.isEmpty()) {
            nombreLimpio = "Jugador";
        }

        String seccionLimpia = seccion == null
            ? "Sin sección"
            : seccion.trim();

        if (seccionLimpia.isEmpty()) {
           seccionLimpia = "Sin sección";
        }

        this.jugadorC = nombreLimpio;
        this.seccionC = seccionLimpia;
        this.accionVolverPrincipal = accionVolverPrincipal;

        configurarVentana();
        construirInterfaz();

        GestorMusica.reproducirFondo(
            "/audio/musica_menu.wav"
        );
}

    private void configurarVentana() {

        setTitle("Juego de Cartas - Menú");
        setSize(760, 690);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void construirInterfaz() {

        PanelDegradado fondo = new PanelDegradado(
                new Color(22, 29, 65),
                new Color(93, 54, 151)
        );

        fondo.setLayout(new BorderLayout(18, 18));
        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        24,
                        44,
                        35,
                        44
                )
        );

        setContentPane(fondo);

        JPanel barraSuperior = new JPanel(
                new BorderLayout()
        );

        barraSuperior.setOpaque(false);

        JLabel lblJugador = new JLabel(
                "Jugador: " + jugadorC
                
        );

        lblJugador.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        lblJugador.setForeground(
                new Color(232, 233, 248)
        );

        btnMusica = new BotonIconoMusica();
        btnMusica.setPreferredSize(
                new Dimension(58, 58)
        );

        btnMusica.addActionListener(e -> {
            GestorMusica.alternarSilencio();
            btnMusica.repaint();
        });

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

        PanelRedondeado panelPrincipal
                = new PanelRedondeado(
                        38,
                        new Color(255, 255, 255, 28),
                        new Color(255, 255, 255, 65)
                );

        panelPrincipal.setLayout(
                new BorderLayout(14, 14)
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        70,
                        30,
                        70
                )
        );

        LogoMemoria logo = new LogoMemoria();
        logo.setPreferredSize(
                new Dimension(420, 180)
        );

        panelPrincipal.add(
                logo,
                BorderLayout.NORTH
        );

        JPanel centro = new JPanel(
                new BorderLayout(8, 18)
        );

        centro.setOpaque(false);

        JLabel lblTitulo = new JLabel(
                "JUEGO DE CARTAS",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        43
                )
        );

        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel(
                "Memoriza componentes de Informática y Robótica",
                SwingConstants.CENTER
        );

        lblSubtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        lblSubtitulo.setForeground(
                new Color(223, 224, 242)
        );

        JPanel textos = new JPanel(
                new GridLayout(2, 1, 2, 2)
        );

        textos.setOpaque(false);
        textos.add(lblTitulo);
        textos.add(lblSubtitulo);

        centro.add(textos, BorderLayout.NORTH);

        JPanel botones = new JPanel(
                new GridLayout(2, 1, 0, 16)
        );

        botones.setOpaque(false);

        BotonRedondeado btnJugar
                = new BotonRedondeado(
                        "JUGAR",
                        new Color(83, 102, 233),
                        new Color(105, 124, 255)
                );

        BotonRedondeado btnSalir
                = new BotonRedondeado(
                        "SALIR",
                        new Color(188, 65, 91),
                        new Color(220, 79, 105)
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

        btnJugar.addActionListener(e -> iniciarJuego());
        btnSalir.addActionListener(e -> salirDelJuego());

        botones.add(btnJugar);
        botones.add(btnSalir);

        centro.add(botones, BorderLayout.CENTER);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        fondo.add(
                panelPrincipal,
                BorderLayout.CENTER
        );

        JLabel lblPie = new JLabel(
                "Nivel 1: 6 parejas  •  Nivel 2: 8 parejas  •  Nivel 3: 10 parejas",
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
                new Color(215, 216, 235)
        );

        fondo.add(lblPie, BorderLayout.SOUTH);
    }

    private void iniciarJuego() {

        ProgresoJuego progreso = new ProgresoJuego(
                jugadorC,
                seccionC,
                accionVolverPrincipal
        );

        new Nivel1Form(progreso).setVisible(true);
        dispose();
    }

    private void salirDelJuego() {

        GestorMusica.detenerFondo();
        dispose();

        if (accionVolverPrincipal != null) {
            accionVolverPrincipal.run();
        }
    }
}
