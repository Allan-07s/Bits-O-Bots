package JuegoMemoria;

import Tipografias.Fuentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class RankingForm extends JFrame {

    private DefaultTableModel modelo;

    /*
     * Panel donde estarán los
     * tres primeros lugares.
     */
    private JPanel panelPodio;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RankingForm() {

        configurarVentana();
        construirInterfaz();

        cargarRanking();
    }

    // =====================================================
    // CONFIGURACIÓN DE VENTANA
    // =====================================================

    private void configurarVentana() {

        setTitle(
                "Memory Tech - Ranking general"
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
    // CONSTRUIR INTERFAZ
    // =====================================================

    private void construirInterfaz() {

        // =================================================
        // FONDO
        // =================================================

        /*
         * AQUÍ ESTABA EL PRIMER FONDO OSCURO.
         *
         * Antes:
         *
         * new Color(19, 27, 67)
         * new Color(92, 49, 145)
         *
         * Ahora usamos el fondo claro
         * del resto del proyecto.
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
                        20,
                        38,
                        25,
                        38
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
         * Espacio izquierdo equivalente
         * al botón de música.
         *
         * Así el título permanece centrado.
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
        // TÍTULOS
        // =================================================

        JPanel panelTitulos
                = new JPanel();

        panelTitulos.setLayout(
                new BoxLayout(
                        panelTitulos,
                        BoxLayout.Y_AXIS
                )
        );

        panelTitulos.setOpaque(false);

        JLabel titulo
                = new JLabel(
                        "RANKING GENERAL"
                );

        titulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        38f
                )
        );

        /*
         * Ya no blanco.
         *
         * Fondo claro = texto azul oscuro.
         */
        titulo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitulo
                = new JLabel(
                        "LOS MEJORES RESULTADOS DE MEMORY TECH"
                );

        subtitulo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        subtitulo.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelTitulos.add(
                titulo
        );

        panelTitulos.add(
                Box.createVerticalStrut(
                        5
                )
        );

        panelTitulos.add(
                subtitulo
        );

        encabezado.add(
                panelTitulos,
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
        // TARJETA CENTRAL
        // =================================================

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
                        18,
                        18
                )
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        28,
                        20,
                        28
                )
        );

        fondo.add(
                panelPrincipal,
                BorderLayout.CENTER
        );

        // =================================================
        // PODIO
        // =================================================

        JPanel zonaPodio
                = new JPanel(
                        new BorderLayout()
                );

        zonaPodio.setOpaque(false);

        JLabel lblPodio
                = new JLabel(
                        "TOP 3",
                        SwingConstants.CENTER
                );

        lblPodio.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        17f
                )
        );

        lblPodio.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        zonaPodio.add(
                lblPodio,
                BorderLayout.NORTH
        );

        /*
         * Aquí se agregarán
         * los tres primeros lugares.
         */
        panelPodio
                = new JPanel(
                        new GridBagLayout()
                );

        panelPodio.setOpaque(false);

        panelPodio.setPreferredSize(
                new Dimension(
                        1050,
                        245
                )
        );

        zonaPodio.add(
                panelPodio,
                BorderLayout.CENTER
        );

        panelPrincipal.add(
                zonaPodio,
                BorderLayout.NORTH
        );

        // =================================================
        // ZONA DE LA TABLA
        // =================================================

        JPanel zonaTabla
                = new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        zonaTabla.setOpaque(false);

        JLabel lblResto
                = new JLabel(
                        "RESTO DEL RANKING",
                        SwingConstants.CENTER
                );

        lblResto.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        15f
                )
        );

        lblResto.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        zonaTabla.add(
                lblResto,
                BorderLayout.NORTH
        );

        // =================================================
        // MODELO DE TABLA
        // =================================================

        modelo
                = new DefaultTableModel(
                        new Object[]{
                            "PUESTO",
                            "JUGADOR",
                            "PUNTOS",
                            "MOV.",
                            "TIEMPO",
                            "FECHA"
                        },
                        0
                ) {

            @Override
            public boolean isCellEditable(
                    int fila,
                    int columna
            ) {

                return false;
            }
        };

        // =================================================
        // TABLA
        // =================================================

        JTable tabla
                = new JTable(
                        modelo
                );

        tabla.setRowHeight(
                43
        );

        tabla.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        12f
                )
        );

        /*
         * Fondo blanco.
         */
        tabla.setBackground(
                Color.WHITE
        );

        /*
         * Texto azul oscuro.
         */
        tabla.setForeground(
                ColoresBitsOBots.TEXTO_OSCURO
        );

        /*
         * Selección:
         *
         * Informática #B2FEFF
         */
        tabla.setSelectionBackground(
                ColoresBitsOBots.INFORMATICA
        );

        tabla.setSelectionForeground(
                ColoresBitsOBots.TEXTO_OSCURO
        );

        /*
         * Líneas suaves.
         */
        tabla.setGridColor(
                new Color(
                        206,
                        234,
                        236
                )
        );

        tabla.setShowVerticalLines(
                false
        );

        tabla.setShowHorizontalLines(
                true
        );

        tabla.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        /*
         * Si hay pocas filas,
         * el resto también queda blanco.
         */
        tabla.setFillsViewportHeight(
                true
        );

        // =================================================
        // ENCABEZADO DE TABLA
        // =================================================

        JTableHeader header
                = tabla.getTableHeader();

        header.setPreferredSize(
                new Dimension(
                        header.getPreferredSize().width,
                        42
                )
        );

        header.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        12f
                )
        );

        /*
         * Aquí sí usamos un color fuerte,
         * pero solamente como acento pequeño.
         */
        header.setBackground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        header.setForeground(
                Color.WHITE
        );

        header.setOpaque(
                true
        );

        header.setReorderingAllowed(
                false
        );

        // =================================================
        // RENDERER DE LAS CELDAS
        // =================================================

        /*
         * AQUÍ ESTABA EL PRINCIPAL CULPABLE.
         *
         * Antes tenías:
         *
         * centro.setBackground(
         *     new Color(20, 34, 78)
         * );
         *
         * Eso pintaba TODA la tabla
         * azul oscuro aunque arriba dijera
         * tabla.setBackground(Color.WHITE).
         *
         * Ahora usamos filas blancas
         * y celestes alternadas.
         */
        DefaultTableCellRenderer centro
                = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
            ) {

                Component componente
                        = super.getTableCellRendererComponent(
                                table,
                                value,
                                isSelected,
                                hasFocus,
                                row,
                                column
                        );

                setHorizontalAlignment(
                        SwingConstants.CENTER
                );

                /*
                 * Si está seleccionado.
                 */
                if (isSelected) {

                    componente.setBackground(
                            ColoresBitsOBots.INFORMATICA
                    );

                    componente.setForeground(
                            ColoresBitsOBots.TEXTO_OSCURO
                    );

                } else {

                    /*
                     * Filas alternadas.
                     */
                    if (row % 2 == 0) {

                        componente.setBackground(
                                Color.WHITE
                        );

                    } else {

                        componente.setBackground(
                                new Color(
                                        240,
                                        253,
                                        254
                                )
                        );
                    }

                    componente.setForeground(
                            ColoresBitsOBots.TEXTO_OSCURO
                    );
                }

                return componente;
            }
        };

        /*
         * Aplicar el renderer
         * a todas las columnas.
         */
        for (
                int i = 0;
                i < tabla.getColumnCount();
                i++
        ) {

            tabla.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centro
                    );
        }

        // =================================================
        // ANCHURA DE COLUMNAS
        // =================================================

        tabla.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        75
                );

        tabla.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        220
                );

        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(
                        120
                );

        tabla.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(
                        100
                );

        tabla.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(
                        110
                );

        tabla.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(
                        190
                );

        // =================================================
        // SCROLL
        // =================================================

        JScrollPane scroll
                = new JScrollPane(
                        tabla
                );

        /*
         * Borde suave turquesa/celeste.
         */
        scroll.setBorder(
                BorderFactory.createLineBorder(
                        ColoresBitsOBots.BORDE_SUAVE,
                        1
                )
        );

        /*
         * ANTES ESTO TAMBIÉN ERA
         * new Color(20, 34, 78).
         *
         * Ahora completamente claro.
         */
        scroll.getViewport()
                .setBackground(
                        Color.WHITE
                );

        scroll.setBackground(
                Color.WHITE
        );

        zonaTabla.add(
                scroll,
                BorderLayout.CENTER
        );

        panelPrincipal.add(
                zonaTabla,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTONES
        // =================================================

        JPanel botones
                = new JPanel();

        botones.setLayout(
                new BoxLayout(
                        botones,
                        BoxLayout.X_AXIS
                )
        );

        botones.setOpaque(false);

        // =================================================
        // ACTUALIZAR
        // =================================================

        /*
         * Robótica / turquesa.
         */
        BotonRedondeado btnActualizar
                = crearBoton(
                        "ACTUALIZAR",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        // =================================================
        // LIMPIAR
        // =================================================

        /*
         * Dejamos rojo únicamente
         * para una acción destructiva.
         */
        BotonRedondeado btnLimpiar
                = crearBoton(
                        "LIMPIAR RANKING",
                        new Color(
                                196,
                                76,
                                92
                        ),
                        new Color(
                                220,
                                94,
                                111
                        )
                );

        // =================================================
        // VOLVER
        // =================================================

        /*
         * Azul principal.
         */
        BotonRedondeado btnCerrar
                = crearBoton(
                        "VOLVER",
                        ColoresBitsOBots.AZUL_PRINCIPAL,
                        ColoresBitsOBots.AZUL_HOVER
                );

        // =================================================
        // EVENTOS
        // =================================================

        btnActualizar.addActionListener(
                e -> {

                    cargarRanking();
                }
        );

        btnLimpiar.addActionListener(
                e -> {

                    int respuesta
                            = JOptionPane
                                    .showConfirmDialog(
                                            this,
                                            "¿Deseas borrar todo el ranking general?",
                                            "Confirmar",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.WARNING_MESSAGE
                                    );

                    if (
                            respuesta
                            == JOptionPane.YES_OPTION
                    ) {

                        RankingManager
                                .limpiarRanking();

                        cargarRanking();
                    }
                }
        );

        btnCerrar.addActionListener(
                e -> {

                    dispose();
                }
        );

        // =================================================
        // CENTRAR BOTONES
        // =================================================

        botones.add(
                Box.createHorizontalGlue()
        );

        botones.add(
                btnActualizar
        );

        botones.add(
                Box.createHorizontalStrut(
                        18
                )
        );

        botones.add(
                btnLimpiar
        );

        botones.add(
                Box.createHorizontalStrut(
                        18
                )
        );

        botones.add(
                btnCerrar
        );

        botones.add(
                Box.createHorizontalGlue()
        );

        fondo.add(
                botones,
                BorderLayout.SOUTH
        );

        // =================================================
        // REFRESCAR
        // =================================================

        fondo.revalidate();
        fondo.repaint();
    }

    // =====================================================
    // CREAR TARJETA DEL PODIO
    // =====================================================

    private JPanel crearTarjetaPodio(
            RegistroRanking registro,
            int puesto
    ) {

        Color fondoTarjeta;
        Color bordeTarjeta;
        Color colorPuesto;

        // =================================================
        // 1° LUGAR
        // =================================================

        if (
                puesto == 1
        ) {

            /*
             * Oro CLARO.
             *
             * Antes el fondo era
             * marrón muy oscuro.
             */
            fondoTarjeta
                    = new Color(
                            255,
                            249,
                            221,
                            248
                    );

            bordeTarjeta
                    = new Color(
                            225,
                            181,
                            56
                    );

            colorPuesto
                    = new Color(
                            178,
                            126,
                            18
                    );

        // =================================================
        // 2° LUGAR
        // =================================================

        } else if (
                puesto == 2
        ) {

            /*
             * Plata/celeste CLARO.
             *
             * Aquí antes había otro
             * azul oscuro.
             */
            fondoTarjeta
                    = new Color(
                            239,
                            250,
                            251,
                            248
                    );

            bordeTarjeta
                    = new Color(
                            143,
                            181,
                            190
                    );

            colorPuesto
                    = new Color(
                            83,
                            119,
                            128
                    );

        // =================================================
        // 3° LUGAR
        // =================================================

        } else {

            /*
             * Bronce CLARO.
             */
            fondoTarjeta
                    = new Color(
                            255,
                            241,
                            230,
                            248
                    );

            bordeTarjeta
                    = new Color(
                            205,
                            131,
                            79
                    );

            colorPuesto
                    = new Color(
                            169,
                            90,
                            46
                    );
        }

        PanelRedondeado tarjeta
                = new PanelRedondeado(
                        28,
                        fondoTarjeta,
                        bordeTarjeta
                );

        tarjeta.setLayout(
                new BoxLayout(
                        tarjeta,
                        BoxLayout.Y_AXIS
                )
        );

        // =================================================
        // TAMAÑO
        // =================================================

        Dimension tamano;

        if (
                puesto == 1
        ) {

            tamano
                    = new Dimension(
                            330,
                            205
                    );

        } else {

            tamano
                    = new Dimension(
                            295,
                            180
                    );
        }

        tarjeta.setPreferredSize(
                tamano
        );

        tarjeta.setMinimumSize(
                tamano
        );

        tarjeta.setMaximumSize(
                tamano
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =================================================
        // PUESTO
        // =================================================

        JLabel lblPuesto
                = new JLabel(
                        puesto
                        + "° LUGAR"
                );

        lblPuesto.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        puesto == 1
                                ? 22f
                                : 18f
                )
        );

        lblPuesto.setForeground(
                colorPuesto
        );

        lblPuesto.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // JUGADOR
        // =================================================

        JLabel lblJugador
                = new JLabel(
                        registro.getJugador()
                );

        lblJugador.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        puesto == 1
                                ? 21f
                                : 17f
                )
        );

        /*
         * Antes blanco.
         *
         * Ahora azul oscuro porque
         * las tarjetas son claras.
         */
        lblJugador.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        lblJugador.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // PUNTOS
        // =================================================

        JLabel lblPuntos
                = new JLabel(
                        registro.getPuntos()
                        + " PTS"
                );

        lblPuntos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        puesto == 1
                                ? 27f
                                : 22f
                )
        );

        lblPuntos.setForeground(
                colorPuesto
        );

        lblPuntos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // MOVIMIENTOS
        // =================================================

        JLabel lblMovimientos
                = new JLabel(
                        registro.getMovimientos()
                        + " MOV."
                );

        lblMovimientos.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        12f
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
                                registro.getSegundos()
                        )
                );

        lblTiempo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        /*
         * Turquesa.
         */
        lblTiempo.setForeground(
                ColoresBitsOBots.TURQUESA_OSCURO
        );

        lblTiempo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // ORDEN
        // =================================================

        tarjeta.add(
                Box.createVerticalGlue()
        );

        tarjeta.add(
                lblPuesto
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        8
                )
        );

        tarjeta.add(
                lblJugador
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        8
                )
        );

        tarjeta.add(
                lblPuntos
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        10
                )
        );

        tarjeta.add(
                lblMovimientos
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        5
                )
        );

        tarjeta.add(
                lblTiempo
        );

        tarjeta.add(
                Box.createVerticalGlue()
        );

        return tarjeta;
    }

    // =====================================================
    // TARJETA VACÍA
    // =====================================================

    private JPanel crearTarjetaVacia(
            int puesto
    ) {

        /*
         * AQUÍ TAMBIÉN HABÍA AZUL OSCURO.
         *
         * Ahora queda blanco/celeste.
         */
        PanelRedondeado tarjeta
                = new PanelRedondeado(
                        28,
                        new Color(
                                244,
                                254,
                                254,
                                245
                        ),
                        ColoresBitsOBots.BORDE_SUAVE
                );

        Dimension tamano;

        if (
                puesto == 1
        ) {

            tamano
                    = new Dimension(
                            330,
                            205
                    );

        } else {

            tamano
                    = new Dimension(
                            295,
                            180
                    );
        }

        tarjeta.setPreferredSize(
                tamano
        );

        tarjeta.setMinimumSize(
                tamano
        );

        tarjeta.setMaximumSize(
                tamano
        );

        tarjeta.setLayout(
                new GridBagLayout()
        );

        JLabel texto
                = new JLabel(
                        puesto
                        + "° LUGAR"
                        + "  ·  SIN REGISTRO",
                        SwingConstants.CENTER
                );

        texto.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        texto.setForeground(
                ColoresBitsOBots.TEXTO_SECUNDARIO
        );

        tarjeta.add(
                texto
        );

        return tarjeta;
    }

    // =====================================================
    // CREAR BOTÓN
    // =====================================================

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
                        12f
                )
        );

        boton.setForeground(
                Color.WHITE
        );

        Dimension tamano
                = new Dimension(
                        260,
                        55
                );

        boton.setPreferredSize(
                tamano
        );

        boton.setMinimumSize(
                tamano
        );

        boton.setMaximumSize(
                tamano
        );

        return boton;
    }

    // =====================================================
    // CARGAR RANKING
    // =====================================================

    private void cargarRanking() {

        List<RegistroRanking> registros
                = RankingManager.obtenerTop(
                        30
                );

        // =================================================
        // ACTUALIZAR PODIO
        // =================================================

        panelPodio.removeAll();

        /*
         * Orden visual:
         *
         * 2°      1°      3°
         */
        agregarPuestoAlPodio(
                registros,
                2,
                0
        );

        agregarPuestoAlPodio(
                registros,
                1,
                1
        );

        agregarPuestoAlPodio(
                registros,
                3,
                2
        );

        panelPodio.revalidate();
        panelPodio.repaint();

        // =================================================
        // ACTUALIZAR TABLA
        // =================================================

        modelo.setRowCount(
                0
        );

        /*
         * El índice 3 corresponde
         * al cuarto lugar.
         */
        for (
                int i = 3;
                i < registros.size();
                i++
        ) {

            RegistroRanking registro
                    = registros.get(
                            i
                    );

            modelo.addRow(
                    new Object[]{
                        i + 1,
                        registro.getJugador(),
                        registro.getPuntos(),
                        registro.getMovimientos(),
                        formatearTiempo(
                                registro.getSegundos()
                        ),
                        registro.getFecha()
                    }
            );
        }
    }

    // =====================================================
    // AGREGAR PUESTO AL PODIO
    // =====================================================

    private void agregarPuestoAlPodio(
            List<RegistroRanking> registros,
            int puesto,
            int columna
    ) {

        GridBagConstraints gbc
                = new GridBagConstraints();

        gbc.gridx
                = columna;

        gbc.gridy
                = 0;

        gbc.weightx
                = 1.0;

        gbc.weighty
                = 1.0;

        gbc.anchor
                = GridBagConstraints.SOUTH;

        gbc.insets
                = new Insets(
                        5,
                        12,
                        5,
                        12
                );

        JPanel tarjeta;

        int indice
                = puesto - 1;

        if (
                indice >= 0
                && indice < registros.size()
        ) {

            tarjeta
                    = crearTarjetaPodio(
                            registros.get(
                                    indice
                            ),
                            puesto
                    );

        } else {

            tarjeta
                    = crearTarjetaVacia(
                            puesto
                    );
        }

        panelPodio.add(
                tarjeta,
                gbc
        );
    }

    // =====================================================
    // FORMATEAR TIEMPO
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