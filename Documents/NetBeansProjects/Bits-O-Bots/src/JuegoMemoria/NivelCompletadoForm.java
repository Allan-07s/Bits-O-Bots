import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
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

        setTitle("Memory Tech - Nivel completado");

        // Ventana más pequeña porque ahora tiene menos información
        setSize(590, 480);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        construirInterfaz(puntosNivel);
    }

    private void construirInterfaz(
            int puntosNivel
    ) {

        // Fondo principal con degradado
        PanelDegradado fondo = new PanelDegradado(
                new Color(19, 27, 62),
                new Color(104, 54, 157)
        );

        fondo.setLayout(
                new BorderLayout(15, 15)
        );

        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        42,
                        35,
                        42
                )
        );

        setContentPane(fondo);

        // =================================================
        // BARRA SUPERIOR
        // =================================================

        JPanel barraSuperior = new JPanel(
                new BorderLayout()
        );

        barraSuperior.setOpaque(false);

        /*
         * Botón circular para activar
         * o desactivar la música.
         */
        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(52, 52)
        );

        btnMusica.addActionListener(e -> {

            GestorMusica.alternarSilencio();

            /*
             * Actualiza el dibujo del altavoz.
             */
            btnMusica.repaint();
        });

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
                        new Color(255, 255, 255, 32),
                        new Color(255, 255, 255, 78)
                );

        tarjeta.setLayout(
                new BorderLayout(15, 25)
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

        JLabel lblTitulo = new JLabel(
                "¡NIVEL "
                + numeroNivel
                + " COMPLETADO!",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        lblTitulo.setForeground(
                Color.WHITE
        );

        tarjeta.add(
                lblTitulo,
                BorderLayout.NORTH
        );

        // =================================================
        // PUNTOS
        // =================================================

        PanelRedondeado panelPuntos
                = new PanelRedondeado(
                        30,
                        new Color(18, 24, 55, 145),
                        new Color(255, 255, 255, 42)
                );

        panelPuntos.setLayout(
                new BorderLayout()
        );

        panelPuntos.setBorder(
                BorderFactory.createEmptyBorder(
                        28,
                        20,
                        28,
                        20
                )
        );

        JLabel lblPuntos = new JLabel(
                puntosNivel + " PUNTOS",
                SwingConstants.CENTER
        );

        lblPuntos.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        43
                )
        );

        lblPuntos.setForeground(
                new Color(255, 222, 103)
        );

        panelPuntos.add(
                lblPuntos,
                BorderLayout.CENTER
        );

        tarjeta.add(
                panelPuntos,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTÓN PARA CONTINUAR
        // =================================================

        String textoBoton;

        if (numeroNivel == 1) {

            textoBoton = "CONTINUAR AL NIVEL 2";

        } else if (numeroNivel == 2) {

            textoBoton = "CONTINUAR AL NIVEL 3";

        } else {

            textoBoton = "VER RESULTADO FINAL";
        }

        BotonRedondeado btnContinuar
                = new BotonRedondeado(
                        textoBoton,
                        new Color(78, 101, 234),
                        new Color(107, 130, 255)
                );

        btnContinuar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        btnContinuar.setPreferredSize(
                new Dimension(400, 58)
        );

        btnContinuar.addActionListener(e -> {

            abrirSiguientePantalla();
        });

        tarjeta.add(
                btnContinuar,
                BorderLayout.SOUTH
        );

        fondo.add(
                tarjeta,
                BorderLayout.CENTER
        );
    }

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

        /*
         * Cierra la pantalla de nivel completado.
         */
        dispose();
    }
}