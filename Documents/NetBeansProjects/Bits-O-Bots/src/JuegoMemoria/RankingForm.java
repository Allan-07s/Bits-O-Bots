package JuegoMemoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RankingForm extends JFrame {

    private DefaultTableModel modelo;

    public RankingForm() {

        setTitle("Memory Tech - Ranking general");
        setSize(820, 590);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        PanelDegradado fondo = new PanelDegradado(
                new Color(22, 29, 65),
                new Color(93, 54, 151)
        );

        fondo.setLayout(new BorderLayout(14, 14));
        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        22,
                        28,
                        22,
                        28
                )
        );

        setContentPane(fondo);

        JPanel encabezado = new JPanel(
                new GridLayout(2, 1, 2, 2)
        );

        encabezado.setOpaque(false);

        JLabel titulo = new JLabel(
                "RANKING GENERAL",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel(
                "Puntaje acumulado de los tres niveles",
                SwingConstants.CENTER
        );

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitulo.setForeground(
                new Color(224, 225, 241)
        );

        encabezado.add(titulo);
        encabezado.add(subtitulo);

        fondo.add(encabezado, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new Object[]{
                    "Puesto",
                    "Jugador",
                    "Puntos totales",
                    "Movimientos",
                    "Tiempo",
                    "Fecha"
                },
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(32);
        tabla.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        tabla.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        tabla.setSelectionBackground(
                new Color(105, 124, 230)
        );

        tabla.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer centro
                = new DefaultTableCellRenderer();

        centro.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (
                int i = 0;
                i < tabla.getColumnCount();
                i++
        ) {

            tabla.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centro);
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        fondo.add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setOpaque(false);

        BotonRedondeado btnActualizar
                = crearBoton("Actualizar");

        BotonRedondeado btnLimpiar
                = new BotonRedondeado(
                        "Limpiar",
                        new Color(175, 64, 89),
                        new Color(207, 78, 104)
                );

        BotonRedondeado btnCerrar
                = crearBoton("Cerrar");

        Dimension medida = new Dimension(135, 43);

        btnActualizar.setPreferredSize(medida);
        btnLimpiar.setPreferredSize(medida);
        btnCerrar.setPreferredSize(medida);

        btnActualizar.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        btnLimpiar.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        btnCerrar.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        btnActualizar.addActionListener(e -> {
            cargarRanking();
        });

        btnLimpiar.addActionListener(e -> {

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas borrar todo el ranking general?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                RankingManager.limpiarRanking();
                cargarRanking();
            }
        });

        btnCerrar.addActionListener(e -> dispose());

        botones.add(btnActualizar);
        botones.add(btnLimpiar);
        botones.add(btnCerrar);

        fondo.add(botones, BorderLayout.SOUTH);
        cargarRanking();
    }

    private BotonRedondeado crearBoton(String texto) {

        return new BotonRedondeado(
                texto,
                new Color(83, 102, 233),
                new Color(105, 124, 255)
        );
    }

    private void cargarRanking() {

        modelo.setRowCount(0);

        List<RegistroRanking> registros
                = RankingManager.obtenerTop(30);

        int puesto = 1;

        for (RegistroRanking registro : registros) {

            modelo.addRow(
                    new Object[]{
                        puesto,
                        registro.getJugador(),
                        registro.getPuntos(),
                        registro.getMovimientos(),
                        formatearTiempo(
                                registro.getSegundos()
                        ),
                        registro.getFecha()
                    }
            );

            puesto++;
        }
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
