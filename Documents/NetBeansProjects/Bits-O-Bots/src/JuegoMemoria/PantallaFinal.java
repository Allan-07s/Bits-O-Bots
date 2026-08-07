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

public class PantallaFinal extends JFrame {

    private final ProgresoJuego progreso;

    public PantallaFinal(ProgresoJuego progreso) {

        this.progreso = progreso;

        setTitle("Memory Tech - Resultado final");
        setSize(780, 690);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        construirInterfaz();

        GestorMusica.reproducirFondo(
                "/audio/musica_final.wav"
        );
    }

    private void construirInterfaz() {

        PanelDegradado fondo = new PanelDegradado(
                new Color(22, 29, 65),
                new Color(93, 54, 151)
        );

        fondo.setLayout(new BorderLayout(16, 16));
        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        42,
                        30,
                        42
                )
        );

        setContentPane(fondo);

        JPanel encabezado = new JPanel(
                new BorderLayout()
        );

        encabezado.setOpaque(false);

        JPanel textos = new JPanel(
                new GridLayout(3, 1, 2, 2)
        );

        textos.setOpaque(false);

        JLabel titulo = new JLabel(
                "¡RETO COMPLETADO!",
                SwingConstants.CENTER
        );

        titulo.setFont(
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                35f
        )
);

        titulo.setForeground(Color.WHITE);

        JLabel jugador = new JLabel(
                progreso.getJugador(),
                SwingConstants.CENTER
        );

        jugador.setFont(
                Fuentes.cargar(
                "Pixel Digivolve.otf",
                18f
        )
);

        jugador.setForeground(
                new Color(232, 233, 248)
        );

        JLabel subtitulo = new JLabel(
                "Completaste los tres niveles de Memory Tech",
                SwingConstants.CENTER
        );

        subtitulo.setFont(
                Fuentes.cargar(
                "Pixel Digivolve.otf",
                15f
        )
);

        subtitulo.setForeground(
                new Color(216, 217, 237)
        );

        textos.add(titulo);
        textos.add(jugador);
        textos.add(subtitulo);

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(55, 55)
        );

        btnMusica.addActionListener(e -> {
            GestorMusica.alternarSilencio();
            btnMusica.repaint();
        });

        encabezado.add(textos, BorderLayout.CENTER);
        encabezado.add(btnMusica, BorderLayout.EAST);

        fondo.add(encabezado, BorderLayout.NORTH);

        PanelRedondeado tarjeta
                = new PanelRedondeado(
                        34,
                        new Color(255, 255, 255, 28),
                        new Color(255, 255, 255, 68)
                );

        tarjeta.setLayout(
                new BorderLayout(14, 18)
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        24,
                        35,
                        27,
                        35
                )
        );

        JLabel total = new JLabel(
                progreso.getPuntosTotales()
                + " PUNTOS",
                SwingConstants.CENTER
        );

        total.setFont(
                Fuentes.cargar(
                "Pixel Digivolve.otf",
                18f
        )
);

        total.setForeground(
                new Color(255, 221, 105)
        );

        tarjeta.add(total, BorderLayout.NORTH);

        JPanel niveles = new JPanel(
                new GridLayout(3, 1, 0, 10)
        );

        niveles.setOpaque(false);

        for (int nivel = 1; nivel <= 3; nivel++) {
            niveles.add(crearFilaNivel(nivel));
        }

        tarjeta.add(niveles, BorderLayout.CENTER);

        JLabel resumen = new JLabel(
                "Movimientos totales: "
                + progreso.getMovimientosTotales()
                + "     •     Tiempo total: "
                + formatearTiempo(
                        progreso.getSegundosTotales()
                ),
                SwingConstants.CENTER
        );

        resumen.setFont(
                Fuentes.cargar(
                "Pixel Digivolve.otf",
                15f
        )
);

        resumen.setForeground(Color.WHITE);

        tarjeta.add(resumen, BorderLayout.SOUTH);
        fondo.add(tarjeta, BorderLayout.CENTER);

        JPanel botones = new JPanel(
                new GridLayout(1, 3, 12, 0)
        );

        botones.setOpaque(false);

        BotonRedondeado btnJugarOtraVez
                = crearBoton("Jugar otra vez");

        BotonRedondeado btnRanking
                = crearBoton("Ver ranking");

        BotonRedondeado btnMenu
                = crearBoton("Menú del juego");

        btnJugarOtraVez.addActionListener(e -> {

            ProgresoJuego nuevo = new ProgresoJuego(
                    progreso.getJugador(),
                    progreso.getSeccion(),
                    progreso.getAccionVolverPrincipal()
            );

            new Nivel1Form(nuevo).setVisible(true);
            dispose();
        });

        btnRanking.addActionListener(e -> {
            new RankingForm().setVisible(true);
        });

        btnMenu.addActionListener(e -> {

            new MenuJuego(
                    progreso.getJugador(),
                    progreso.getSeccion(),
                    progreso.getAccionVolverPrincipal()
            ).setVisible(true);

            dispose();
        });

        botones.add(btnJugarOtraVez);
        botones.add(btnRanking);
        botones.add(btnMenu);

        fondo.add(botones, BorderLayout.SOUTH);
    }

    private JPanel crearFilaNivel(int nivel) {

        PanelRedondeado fila = new PanelRedondeado(
                22,
                new Color(255, 255, 255, 21),
                new Color(255, 255, 255, 45)
        );

        fila.setLayout(new GridLayout(1, 4, 8, 0));
        fila.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        fila.add(crearDato("Nivel " + nivel));

        fila.add(
                crearDato(
                        progreso.getPuntosNivel(nivel)
                        + " pts"
                )
        );

        fila.add(
                crearDato(
                        progreso.getMovimientosNivel(nivel)
                        + " mov."
                )
        );

        fila.add(
                crearDato(
                        formatearTiempo(
                                progreso.getSegundosNivel(nivel)
                        )
                )
        );

        return fila;
    }

    private JLabel crearDato(String texto) {

        JLabel etiqueta = new JLabel(
                texto,
                SwingConstants.CENTER
        );

        etiqueta.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        etiqueta.setForeground(Color.WHITE);
        return etiqueta;
    }

    private BotonRedondeado crearBoton(String texto) {

        BotonRedondeado boton
                = new BotonRedondeado(
                        texto,
                        new Color(83, 102, 233),
                        new Color(105, 124, 255)
                );

        boton.setFont(
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                12f
        )
);

        return boton;
    }

    private String formatearTiempo(
            int totalSegundos
    ) {

        return String.format(
                "%02d:%02d",
                totalSegundos / 60,
                totalSegundos % 60
        );
    }
}
