package JuegoMemoria;

import Tipografias.Fuentes;
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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import Login.Menu;

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
        setUndecorated(true);

    /*
     * Pantalla completa.
     */
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setDefaultCloseOperation(
      JFrame.DO_NOTHING_ON_CLOSE
    );

        
        setResizable(true); }

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
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                22f
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
                "BITS O BOTS",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                40f
        )
);

        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel(
                "Memoriza componentes de Informática y Robótica",
                SwingConstants.CENTER
        );

        lblSubtitulo.setFont(
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                40f
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

        /*
 * =====================================================
 * PANEL DE LOS BOTONES JUGAR Y SALIR
 * =====================================================
 */
JPanel botones = new JPanel();

botones.setLayout(
        new BoxLayout(
                botones,
                BoxLayout.Y_AXIS
        )
);

botones.setOpaque(false);

/*
 * =====================================================
 * BOTÓN JUGAR
 * =====================================================
 */
BotonRedondeado btnJugar
        = new BotonRedondeado(
                "JUGAR",
                new Color(83, 102, 233),
                new Color(105, 124, 255)
        );

btnJugar.setFont(
        Fuentes.cargar(
                "Pixel Digivolve.otf",
                22f
        )
);

btnJugar.setForeground(Color.WHITE);

/*
 * Primer número: ancho.
 * Segundo número: alto.
 */
Dimension tamanoJugar = new Dimension(
        800,
        160
);

btnJugar.setPreferredSize(tamanoJugar);
btnJugar.setMinimumSize(tamanoJugar);
btnJugar.setMaximumSize(tamanoJugar);

btnJugar.setAlignmentX(
        Component.CENTER_ALIGNMENT
);

btnJugar.addActionListener(e -> {
    iniciarJuego();
});

/*
 * =====================================================
 * BOTÓN SALIR
 * =====================================================
 */
BotonRedondeado btnSalir
        = new BotonRedondeado(
                "SALIR",
                new Color(188, 65, 91),
                new Color(220, 79, 105)
        );

btnSalir.setFont(
        Fuentes.cargar(
                "Pixel Digivolve.otf",
                22f
        )
);

btnSalir.setForeground(Color.WHITE);

Dimension tamanoSalir = new Dimension(
        800,
        160
);

btnSalir.setPreferredSize(tamanoSalir);
btnSalir.setMinimumSize(tamanoSalir);
btnSalir.setMaximumSize(tamanoSalir);

btnSalir.setAlignmentX(
        Component.CENTER_ALIGNMENT
);

btnSalir.addActionListener(e -> {
    salirDelJuego();
});

/*
 * =====================================================
 * AGREGAR Y CENTRAR LOS BOTONES
 * =====================================================
 */

/*
 * Ocupa el espacio superior disponible.
 */
botones.add(
        Box.createVerticalGlue()
);

botones.add(btnJugar);

/*
 * Separación entre JUGAR y SALIR.
 */
botones.add(
        Box.createVerticalStrut(25)
);

botones.add(btnSalir);

/*
 * Ocupa el espacio inferior disponible.
 * Al tener Glue arriba y abajo, los botones
 * quedan centrados verticalmente.
 */
botones.add(
        Box.createVerticalGlue()
);

/*
 * Colocar los botones en la zona central.
 */
centro.add(
        botones,
        BorderLayout.CENTER
);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        fondo.add(
                panelPrincipal,
                BorderLayout.CENTER
        );

        JLabel lblPie = new JLabel(
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
