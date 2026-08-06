package JuegoMemoria;

import Avatar.AvatarPanel;
import Tipografias.Fuentes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

        setTitle("Bits o Bots - Nivel completado");

         setUndecorated(true);

    /*
     * Pantalla completa.
     */
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setDefaultCloseOperation(
      JFrame.DO_NOTHING_ON_CLOSE
    );

        
        setResizable(true);

    
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
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                50f
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
// CONTENIDO CENTRAL: PUNTOS + AVATAR
// =================================================

JPanel contenidoCentral = new JPanel(
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
                new Color(18, 24, 55, 145),
                new Color(255, 255, 255, 42)
        );

panelPuntos.setLayout(
        new BorderLayout()
);

/*
 * TAMAÑO DEL CUADRO DE PUNTOS.
 *
 * 650 = ancho
 * 320 = alto
 */
Dimension tamanoPanelPuntos = new Dimension(
        650,
        320
);

panelPuntos.setPreferredSize(tamanoPanelPuntos);
panelPuntos.setMinimumSize(tamanoPanelPuntos);
panelPuntos.setMaximumSize(tamanoPanelPuntos);

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
        Fuentes.cargar(
                "Pixel Digivolve.otf",
                50f
        )
);

lblPuntos.setForeground(
        new Color(255, 222, 103)
);

panelPuntos.add(
        lblPuntos,
        BorderLayout.CENTER
);

/*
 * Centra el cuadro de puntos sin estirarlo.
 */
JPanel contenedorPuntos = new JPanel(
        new GridBagLayout()
);

contenedorPuntos.setOpaque(false);
contenedorPuntos.add(panelPuntos);

contenidoCentral.add(
        contenedorPuntos,
        BorderLayout.CENTER
);

// =================================================
// PANEL DERECHO: MENSAJE + AVATAR
// =================================================

JPanel panelAsistente = new JPanel();

panelAsistente.setLayout(
        new BoxLayout(
                panelAsistente,
                BoxLayout.Y_AXIS
        )
);

panelAsistente.setOpaque(false);

/*
 * TAMAÑO DE TODA LA ZONA DERECHA.
 */
Dimension tamanoAsistente = new Dimension(
        380,
        450
);

panelAsistente.setPreferredSize(tamanoAsistente);
panelAsistente.setMinimumSize(tamanoAsistente);
panelAsistente.setMaximumSize(tamanoAsistente);

// =================================================
// BURBUJA DEL MENSAJE
// =================================================

PanelRedondeado burbujaMensaje
        = new PanelRedondeado(
                28,
                new Color(248, 250, 255, 245),
                new Color(160, 177, 230)
        );

Dimension tamanoBurbuja = new Dimension(
        345,
        125
);

burbujaMensaje.setPreferredSize(tamanoBurbuja);
burbujaMensaje.setMinimumSize(tamanoBurbuja);
burbujaMensaje.setMaximumSize(tamanoBurbuja);

burbujaMensaje.setAlignmentX(
        Component.CENTER_ALIGNMENT
);

burbujaMensaje.setLayout(
        new BorderLayout()
);

burbujaMensaje.setBorder(
        BorderFactory.createEmptyBorder(
                15,
                18,
                15,
                18
        )
);

JLabel lblMensajeRobot = new JLabel(
        "<html>"
        + "<div style='text-align:center;'>"
        + "¡NIVEL "
        + numeroNivel
        + " COMPLETADO!"
        + "<br>"
        + "¡Excelente trabajo!"
        + "</div>"
        + "</html>",
        SwingConstants.CENTER
);

lblMensajeRobot.setFont(
          Fuentes.cargar(
                "Pixel Digivolve.otf",
                22f
        )
);

lblMensajeRobot.setForeground(
        new Color(30, 40, 85)
);

burbujaMensaje.add(
        lblMensajeRobot,
        BorderLayout.CENTER
);

// =================================================
// AVATAR
// =================================================

JPanel panelAvatar = new JPanel(
        new BorderLayout()
);

panelAvatar.setOpaque(false);

Dimension tamanoPanelAvatar = new Dimension(
        345,
        270
);

panelAvatar.setPreferredSize(tamanoPanelAvatar);
panelAvatar.setMinimumSize(tamanoPanelAvatar);
panelAvatar.setMaximumSize(tamanoPanelAvatar);

panelAvatar.setAlignmentX(
        Component.CENTER_ALIGNMENT
);

/*
 * Crear el robot.
 */
AvatarPanel avatar = new AvatarPanel();

Dimension tamanoAvatar = new Dimension(
        325,
        295
);

avatar.setPreferredSize(tamanoAvatar);
avatar.setMinimumSize(tamanoAvatar);
avatar.setMaximumSize(tamanoAvatar);

/*
 * SOUTH coloca el robot hacia la parte
 * inferior de su panel.
 */
panelAvatar.add(
        avatar,
        BorderLayout.SOUTH
);

avatar.startAnimation();

// =================================================
// ORDENAR LA ZONA DERECHA
// =================================================

panelAsistente.add(
        Box.createVerticalGlue()
);

panelAsistente.add(
        burbujaMensaje
);

panelAsistente.add(
        Box.createVerticalStrut(10)
);

panelAsistente.add(
        panelAvatar
);

panelAsistente.add(
        Box.createVerticalGlue()
);

/*
 * Agregar el robot al lado derecho.
 */
contenidoCentral.add(
        panelAsistente,
        BorderLayout.EAST
);

/*
 * Agregar puntos y avatar al centro
 * de la tarjeta principal.
 */
tarjeta.add(
        contenidoCentral,
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
                 Fuentes.cargar(
                "Pixel Digivolve.otf",
                17f
        )
);

   

      btnContinuar.setForeground(
        Color.WHITE
);

/*
 * MEDIDAS DEL BOTÓN CONTINUAR
 *
 * Primer número = ancho.
 * Segundo número = alto.
 */
     Dimension tamanoContinuar = new Dimension(
        470,
        72
);

    btnContinuar.setPreferredSize(tamanoContinuar);
    btnContinuar.setMinimumSize(tamanoContinuar);
    btnContinuar.setMaximumSize(tamanoContinuar);

    btnContinuar.addActionListener(e -> {

    abrirSiguientePantalla();
});

/*
 * Panel auxiliar para centrar el botón
 * y evitar que ocupe todo el ancho.
 */
    JPanel contenedorBoton = new JPanel(
        new GridBagLayout()
    );

    contenedorBoton.setOpaque(false);

/*
 * Espacio arriba del botón.
 */
    contenedorBoton.setBorder(
        BorderFactory.createEmptyBorder(
                15,
                0,
                0,
                0
        )
);





    contenedorBoton.add(btnContinuar);

    tarjeta.add(
        contenedorBoton,
        BorderLayout.SOUTH
);

    fondo.add(
        tarjeta,
        BorderLayout.CENTER
);

    fondo.revalidate();
    fondo.repaint();
    
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