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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class RankingForm extends JFrame {

    private DefaultTableModel modelo;
    private JPanel panelPodio;
    private JTable tabla;

    /*
     * Timer para actualizar automáticamente.
     */
    private Timer actualizadorRanking;

    /*
     * Worker que consulta MySQL.
     */
    private SwingWorker<List<RegistroRanking>, Void> workerRanking;

    /*
     * Evita lanzar varias consultas
     * al mismo tiempo.
     */
    private boolean cargandoRanking = false;

    /*
     * Evita intentar actualizar componentes
     * después de cerrar la ventana.
     */
    private boolean cerrando = false;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RankingForm() {

        configurarVentana();

        construirInterfaz();

        cargarRanking();

        iniciarActualizacionAutomatica();
    }

    // =====================================================
    // CONFIGURAR VENTANA
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

        // =================================================
        // ESPACIO IZQUIERDO
        // =================================================

        JPanel espacioIzquierdo
                = new JPanel();

        espacioIzquierdo.setOpaque(false);

        Dimension tamanoLateral
                = new Dimension(
                        74,
                        74
                );

        espacioIzquierdo.setPreferredSize(
                tamanoLateral
        );

        espacioIzquierdo.setMinimumSize(
                tamanoLateral
        );

        espacioIzquierdo.setMaximumSize(
                tamanoLateral
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

        titulo.setForeground(
                ColoresBitsOBots.TEXTO_PRINCIPAL
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitulo
                = new JLabel(
                        "LOS MEJORES RESULTADOS DE BITS O BOTS"
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
                        62,
                        62
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

        encabezado.add(
                zonaMusica,
                BorderLayout.EAST
        );

        fondo.add(
                encabezado,
                BorderLayout.NORTH
        );

        // =================================================
        // PANEL PRINCIPAL
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
        // ZONA TABLA
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
        // MODELO
        // =================================================

        modelo
                = new DefaultTableModel(
                        new Object[]{
                            "PUESTO",
                            "JUGADOR",
                            "GRADO",
                            "SECCIÓN",
                            "PUNTOS",
                            "MOV.",
                            "TIEMPO"
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

        tabla
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

        tabla.setBackground(
                Color.WHITE
        );

        tabla.setForeground(
                ColoresBitsOBots.TEXTO_OSCURO
        );

        tabla.setSelectionBackground(
                ColoresBitsOBots.INFORMATICA
        );

        tabla.setSelectionForeground(
                ColoresBitsOBots.TEXTO_OSCURO
        );

        tabla.setGridColor(
                new Color(
                        206,
                        234,
                        236
                )
        );

        tabla.setShowVerticalLines(false);

        tabla.setShowHorizontalLines(true);

        tabla.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        tabla.setFillsViewportHeight(
                true
        );

        // =================================================
        // ENCABEZADO DE LA TABLA
        // =================================================

        JTableHeader header
                = tabla.getTableHeader();

        header.setPreferredSize(
                new Dimension(
                        header
                                .getPreferredSize()
                                .width,
                        50
                )
        );

        header.setReorderingAllowed(false);

        header.setResizingAllowed(false);

        DefaultTableCellRenderer rendererHeader
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

                JLabel label
                        = (JLabel) super
                                .getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                label.setOpaque(true);

                label.setHorizontalAlignment(
                        SwingConstants.CENTER
                );

                label.setVerticalAlignment(
                        SwingConstants.CENTER
                );

                label.setBackground(
                        ColoresBitsOBots.TURQUESA_OSCURO
                );

                label.setForeground(
                        Color.WHITE
                );

                label.setFont(
                        Fuentes.cargar(
                                "Pixel Digivolve.otf",
                                13f
                        )
                );

                label.setBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                0,
                                1,
                                new Color(
                                        255,
                                        255,
                                        255,
                                        90
                                )
                        )
                );

                return label;
            }
        };

        header.setDefaultRenderer(
                rendererHeader
        );

        // =================================================
        // RENDERER DE CELDAS
        // =================================================

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
                        = super
                                .getTableCellRendererComponent(
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

                if (isSelected) {

                    componente.setBackground(
                            ColoresBitsOBots.INFORMATICA
                    );

                    componente.setForeground(
                            ColoresBitsOBots.TEXTO_OSCURO
                    );

                } else {

                    if (
                            row % 2 == 0
                    ) {

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

        for (
                int i = 0;
                i < tabla.getColumnCount();
                i++
        ) {

            tabla
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centro
                    );
        }

        // =================================================
        // ANCHOS
        // =================================================

        tabla.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(80);

        tabla.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(220);

        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(100);

        tabla.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(100);

        tabla.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(120);

        tabla.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(100);

        tabla.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(120);

        // =================================================
        // SCROLL
        // =================================================

        JScrollPane scroll
                = new JScrollPane(
                        tabla
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        ColoresBitsOBots.BORDE_SUAVE,
                        1
                )
        );

        scroll
                .getViewport()
                .setBackground(
                        Color.WHITE
                );

        scroll.setBackground(
                Color.WHITE
        );

        scroll.setColumnHeaderView(
                tabla.getTableHeader()
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

        /*
         * AHORA SOLO QUEDAN:
         *
         * ACTUALIZAR
         * VOLVER
         */

        BotonRedondeado btnActualizar
                = crearBoton(
                        "ACTUALIZAR",
                        ColoresBitsOBots.TURQUESA_OSCURO,
                        ColoresBitsOBots.TURQUESA_HOVER
                );

        BotonRedondeado btnCerrar
                = crearBoton(
                        "VOLVER",
                        ColoresBitsOBots.AZUL_PRINCIPAL,
                        ColoresBitsOBots.AZUL_HOVER
                );

        // =================================================
        // ACTUALIZAR
        // =================================================

        btnActualizar.addActionListener(
                e -> cargarRanking()
        );

        // =================================================
        // VOLVER
        // =================================================

        btnCerrar.addActionListener(
                e -> dispose()
        );

        // =================================================
        // AGREGAR BOTONES
        // =================================================

        botones.add(
                Box.createHorizontalGlue()
        );

        botones.add(
                btnActualizar
        );

        botones.add(
                Box.createHorizontalStrut(
                        24
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

        fondo.revalidate();

        fondo.repaint();
    }

    // =====================================================
    // CARGAR RANKING SIN CONGELAR SWING
    // =====================================================

    private void cargarRanking() {

        if (
                cerrando
        ) {

            return;
        }

        if (
                cargandoRanking
        ) {

            return;
        }

        cargandoRanking = true;

        workerRanking
                = new SwingWorker<
                        List<RegistroRanking>,
                        Void
                        >() {

            @Override
            protected List<RegistroRanking>
                    doInBackground() {

                return RankingManager
                        .obtenerTop(
                                30
                        );
            }

            @Override
            protected void done() {

                cargandoRanking = false;

                if (
                        cerrando
                        || isCancelled()
                ) {

                    return;
                }

                try {

                    List<RegistroRanking> registros
                            = get();

                    actualizarInterfazRanking(
                            registros
                    );

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo actualizar visualmente el ranking:"
                    );

                    System.out.println(
                            e.getMessage()
                    );
                }
            }
        };

        workerRanking.execute();
    }

    // =====================================================
    // ACTUALIZAR INTERFAZ
    // =====================================================

    private void actualizarInterfazRanking(
            List<RegistroRanking> registros
    ) {

        if (
                cerrando
        ) {

            return;
        }

        // =================================================
        // PODIO
        // =================================================

        panelPodio.removeAll();

        /*
         * Orden visual:
         *
         * 2°    1°    3°
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
        // TABLA
        // =================================================

        modelo.setRowCount(
                0
        );

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
                        limpiarTexto(
                                registro.getGrado()
                        ),
                        limpiarTexto(
                                registro.getSeccion()
                        ),
                        registro.getPuntos(),
                        registro.getMovimientos(),
                        formatearTiempo(
                                registro.getSegundos()
                        )
                    }
            );
        }

        tabla.revalidate();

        tabla.repaint();
    }

    // =====================================================
    // ACTUALIZACIÓN AUTOMÁTICA
    // =====================================================

    private void iniciarActualizacionAutomatica() {

        actualizadorRanking
                = new Timer(
                        2000,
                        e -> cargarRanking()
                );

        actualizadorRanking.start();
    }

    // =====================================================
    // CREAR TARJETA PODIO
    // =====================================================

    private JPanel crearTarjetaPodio(
            RegistroRanking registro,
            int puesto
    ) {

        Color fondoTarjeta;
        Color bordeTarjeta;
        Color colorPuesto;

        // =================================================
        // 1° LUGAR - DORADO SUAVE
        // =================================================

        if (
                puesto == 1
        ) {

            fondoTarjeta
                    = new Color(
                            255,
                            247,
                            215,
                            248
                    );

            bordeTarjeta
                    = new Color(
                            224,
                            190,
                            96
                    );

            colorPuesto
                    = new Color(
                            151,
                            111,
                            27
                    );

        // =================================================
        // 2° Y 3° LUGAR
        // =================================================
        /*
         * AHORA LOS DOS USAN
         * EXACTAMENTE LOS MISMOS COLORES.
         */

        } else {

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

            /*
             * SEGUNDO Y TERCERO
             * también conservan el mismo tamaño.
             */
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
                                ? 23f
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

        if (
                puesto == 1
        ) {

            lblJugador.setForeground(
                    new Color(
                            93,
                            76,
                            32
                    )
            );

        } else {

            lblJugador.setForeground(
                    ColoresBitsOBots.TEXTO_PRINCIPAL
            );
        }

        lblJugador.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // GRADO Y SECCIÓN
        // =================================================

        JLabel lblGrupo
                = new JLabel(
                        construirGrupo(
                                registro
                        )
                );

        lblGrupo.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        11f
                )
        );

        if (
                puesto == 1
        ) {

            lblGrupo.setForeground(
                    new Color(
                            124,
                            104,
                            55
                    )
            );

        } else {

            lblGrupo.setForeground(
                    ColoresBitsOBots.TEXTO_SECUNDARIO
            );
        }

        lblGrupo.setAlignmentX(
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
                                ? 28f
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

        if (
                puesto == 1
        ) {

            lblMovimientos.setForeground(
                    new Color(
                            124,
                            104,
                            55
                    )
            );

        } else {

            lblMovimientos.setForeground(
                    ColoresBitsOBots.TEXTO_SECUNDARIO
            );
        }

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

        if (
                puesto == 1
        ) {

            lblTiempo.setForeground(
                    colorPuesto
            );

        } else {

            lblTiempo.setForeground(
                    ColoresBitsOBots.TURQUESA_OSCURO
            );
        }

        lblTiempo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // AGREGAR
        // =================================================

        tarjeta.add(
                Box.createVerticalGlue()
        );

        tarjeta.add(
                lblPuesto
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        6
                )
        );

        tarjeta.add(
                lblJugador
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        4
                )
        );

        tarjeta.add(
                lblGrupo
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        7
                )
        );

        tarjeta.add(
                lblPuntos
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        8
                )
        );

        tarjeta.add(
                lblMovimientos
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        4
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

        Color fondoTarjeta;
        Color bordeTarjeta;
        Color colorTexto;

        if (
                puesto == 1
        ) {

            fondoTarjeta
                    = new Color(
                            255,
                            249,
                            224,
                            245
                    );

            bordeTarjeta
                    = new Color(
                            224,
                            196,
                            118
                    );

            colorTexto
                    = new Color(
                            151,
                            111,
                            27
                    );

        } else {

            /*
             * SEGUNDO Y TERCERO VACÍOS
             * también quedan iguales.
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

            colorTexto
                    = new Color(
                            83,
                            119,
                            128
                    );
        }

        PanelRedondeado tarjeta
                = new PanelRedondeado(
                        28,
                        fondoTarjeta,
                        bordeTarjeta
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
                        + "° LUGAR · SIN REGISTRO",
                        SwingConstants.CENTER
                );

        texto.setFont(
                Fuentes.cargar(
                        "Pixel Digivolve.otf",
                        13f
                )
        );

        texto.setForeground(
                colorTexto
        );

        tarjeta.add(
                texto
        );

        return tarjeta;
    }

    // =====================================================
    // AGREGAR PODIO
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

        int indice
                = puesto - 1;

        JPanel tarjeta;

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
    // GRADO - SECCIÓN
    // =====================================================

    private String construirGrupo(
            RegistroRanking registro
    ) {

        String grado
                = limpiarTexto(
                        registro.getGrado()
                );

        String seccion
                = limpiarTexto(
                        registro.getSeccion()
                );

        if (
                grado.isEmpty()
                && seccion.isEmpty()
        ) {

            return "";
        }

        if (
                grado.isEmpty()
        ) {

            return seccion;
        }

        if (
                seccion.isEmpty()
        ) {

            return grado;
        }

        return grado
                + " - "
                + seccion;
    }

    // =====================================================
    // LIMPIAR TEXTO
    // =====================================================

    private String limpiarTexto(
            String texto
    ) {

        if (
                texto == null
        ) {

            return "";
        }

        return texto.trim();
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

    // =====================================================
    // CERRAR
    // =====================================================

    @Override
    public void dispose() {

        cerrando = true;

        if (
                actualizadorRanking != null
        ) {

            actualizadorRanking.stop();

            actualizadorRanking = null;
        }

        if (
                workerRanking != null
                && !workerRanking.isDone()
        ) {

            workerRanking.cancel(
                    true
            );
        }

        super.dispose();
    }
}