/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Quizz;

import Avatar.AvatarPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;




/**
 *
 * @author alvar
 */
public class Quizz_Vocacional extends javax.swing.JFrame {
    
    private final String jugadorC;
    private final String seccionC;
    
    private List<Preguntas> listaPreguntas = new ArrayList<>();
    private int indiceActual = 0;
    private int puntosInformatica = 0;
    private int puntosRobotica = 0;
    
    private AvatarPanel avatar;
    
    private final String[] MENSAJES_AVATAR = {
        "¡Bienvenido! Elige la opción que más despierte tu curiosidad.",
        "Ambas áreas resuelven problemas, pero de formas distintas.",
        "¿Te atraen más los circuitos físicos o las pantallas?",
        "¿Qué hardware te llama más la atención explorar?",
        "Piensa en cómo te gustaría que interactúen los datos.",
        "¡Redes o Automatización! Dos mundos con gran futuro.",
        "¿Entornos virtuales o dispositivos en el mundo real?",
        "Seguridad digital vs. Mecatrónica aplicada a la salud.",
        "Sensórica física frente a procesamiento digital.",
        "¡Última de imágenes! Código binario o mecánica pura.",
        "¡Atención! Observa con cuidado estos videos antes de elegir.",
        "Analiza el movimiento y la lógica mostrada en pantalla.",
        "Compara cómo se aplica la tecnología en cada opción.",
        "¡Ya casi terminamos! Elige la que más te identifique.",
        "¡Última pregunta! Da tu mejor elección final."
    };
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Quizz_Vocacional.class.getName());

    /**
     * Creates new form Quizz_Vocacional
     * @param jugador
     * @param seccion
     */
    
    public Quizz_Vocacional(String jugador, String seccion) {
        initComponents();
        // Estilizado moderno para el enunciado de la pregunta
        // Instanciamos el avatar Bug
        this.avatar = new AvatarPanel();
        panelAvatar.setLayout(new BorderLayout());
        panelAvatar.add(avatar);
        avatar.startAnimation();

        // --- AGREGAR ESTAS LÍNEAS PARA FORZAR EL RENDERIZADO ---
        panelAvatar.revalidate();
        panelAvatar.repaint();
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        lbEnunciado.setOpaque(false);
        lbEnunciado.setBorder(null);
        lbEnunciado.setFont(new Font("SansSerif", Font.BOLD, 22));
        lbEnunciado.setForeground(new Color(29, 53, 87));
        
        jpInfor.setOpaque(false);
        jpRo.setOpaque(false);

        jpInfor.setBorder(null);
        jpRo.setBorder(null);

        lbImagenzquierda.setOpaque(false);
        lbImagenDerecha.setOpaque(false);

        lbImagenzquierda.setBorder(null);
        lbImagenDerecha.setBorder(null);

        jpInfor.setCursor(
                new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
        );

        jpRo.setCursor(
                new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
        );

        ((TarjetaRespuesta) jpInfor).configurar(
                "A",
                "Opción A",
                new Color(47, 128, 237),
                new Color(91, 155, 255)
        );

        ((TarjetaRespuesta) jpRo).configurar(
                "B",
                "Opción B",
                new Color(35, 181, 190),
                new Color(54, 207, 201)
        );
        panelAvatar.setOpaque(false);
        jpInfor.setOpaque(false);
        jpRo.setOpaque(false);

        jpInfor.setBorder(null);
        jpRo.setBorder(null);

        lbImagenzquierda.setOpaque(false);
        lbImagenDerecha.setOpaque(false);

        lbImagenzquierda.setBorder(null);
        lbImagenDerecha.setBorder(null);

        jugadorC = jugador;
        seccionC = seccion;
        
        jpbProgreso.setUI(new BarraProgresoUI());
        jpbProgreso.setOpaque(false);
        jpbProgreso.setBorderPainted(false);
        jpbProgreso.setStringPainted(true);
        jpbProgreso.setFont(new Font(
        "SansSerif",
        Font.BOLD,
        14
));
        jpbProgreso.setPreferredSize(new Dimension(650, 34));
        
        setExtendedState(Quizz_Vocacional.MAXIMIZED_BOTH);
        btnResultados.setVisible(false);
        
        cargarListaPreguntas();
        mostrarPreguntaActual();
        dimensiones();
        
        // Modifica el comportamiento de todos los Tooltips en el formulario
        javax.swing.ToolTipManager manager = javax.swing.ToolTipManager.sharedInstance();
        manager.setInitialDelay(0); 
        manager.setDismissDelay(Integer.MAX_VALUE); 
        manager.setReshowDelay(0);
        javax.swing.UIManager.put("ToolTip.background", new java.awt.Color(0,0,0,0));
        
        //Avatar
        AvatarPanel avatar = new AvatarPanel();
        panelAvatar.setLayout(new BorderLayout());
        panelAvatar.add(avatar);
        avatar.startAnimation();
    }
    
    private void dimensiones(){        
       
        
        jPanel2.setPreferredSize(new Dimension(1200, 700));
        jPanel2.setMinimumSize(new Dimension(1200, 700));
        jPanel2.setMaximumSize(new Dimension(1200, 700));
        
        
    }
    
    private void cargarListaPreguntas() {
        // Pregunta 1
        listaPreguntas.add(new Preguntas(
            "1. ¿Qué tipo de creación tecnológica te gustaría desarollar?",

            "Diseñar máquinas móviles capaces de desplazarse y responder a su entorno.",
            "/img_quizz/ROP1.png",
            "Robótica",

            "Crear experiencias digitales, como videojuegos.",
            "/img_quizz/INP1.png",
            "Informática"
            ));

        // Pregunta 2
        listaPreguntas.add(new Preguntas(
            "2. ¿Qué forma de resolver problemas tecnológicos te interesa más?",

            "Crear instrucciones y soluciones mediante programación.",
            "/img_quizz/INP2.png",
            "Informática",

            "Integrar componentes electrónicos para hacer funcionar un sistema.",
            "/img_quizz/ROP5.png",
            "Robótica"
        ));

        // Pregunta 3
        listaPreguntas.add(new Preguntas(
            "3. ¿En cuál actividad tecnológica te gustaría concentrarte?",

            "Conectar componentes y comprobar el funcionamiento de un circuito.",
            "/img_quizz/ROPPP3.png",
            "Robótica",

            "Analizar y desarrollar soluciones utilizando una computadora.",
            "/img_quizz/INP3.png",
            "Informática"
        ));

        // Pregunta 4
        listaPreguntas.add(new Preguntas(
            "4. ¿Qué equipo te gustaría aprender a diagnosticar y reparar?",

            "Un robot y sus piezas mecánicas y electrónicas.",
            "/img_quizz/ROP4.png",
            "Robótica",
                
            "Una computadora y sus componentes internos.",
            "/img_quizz/INP4.png",
            "Informática"    
        ));
            
        // Pregunta 5
        listaPreguntas.add(new Preguntas(
            "5. ¿Qué funcionamiento te interesa comprender mejor?",

            "Cómo se comunican y comparten información diferentes dispositivos.",
            "/img_quizz/INP5.png",
            "Informática",

            "Cómo un mecanismo recibe órdenes y realiza movimientos.",
            "/img_quizz/ROP5.png",
            "Robótica"
        ));
        
        // Pregunta 6
        listaPreguntas.add(new Preguntas(
            "6. ¿Qué tipo de sistemas te gustaría configurar?",

            "Una red para conectar computadoras y compartir recursos.",
            "/img_quizz/INP6.png",
            "Informática",

            "Un sistema automático que utilice sensores para controlar el riego.",
            "/img_quizz/ROP6.png",
            "Robótica"
        ));

        // Pregunta 7
        listaPreguntas.add(new Preguntas(
            "7. ¿Qué tipo de sistema te gustaría aprender a controlar?",

            "Controlar dispositivos que se desplazan en el espacio físico.",
            "/img_quizz/ROP7.png",
            "Robótica",

            "Explorar y crear entornos digitales inmersivos.",
            "/img_quizz/INP7.png",
            "Informática"
        ));

        // Pregunta 8
        listaPreguntas.add(new Preguntas(
            "8. ¿Qué propósito tecnológico te interesa más?",

            "Proteger información y sistemas frente a amenazas digitales.",
            "/img_quizz/INP8.png",
            "Informática",

            "Crear dispositivos que apoyen o recuperen el movimiento humano.",
            "/img_quizz/ROP8.png",
            "Robótica"
        ));

        // Pregunta 9
        listaPreguntas.add(new Preguntas(
            "9. ¿Qué proceso tecnológico te gustaría comprender?",

            "Cómo un sistema detecta la presencia de una persona y activa un mecanismo.",
            "/img_quizz/ROP9.png",
            "Robótica",

            "Cómo un dispositivo captura imágenes y las convierte en información digital.",
            "/img_quizz/INP9.png",
            "Informática"
        ));
            
        // Pregunta 10
        listaPreguntas.add(new Preguntas(
            "10. ¿Qué principio tecnológico te causa más curiosidad?",

            "Cómo las computadoras representan y procesan información mediante código binario.",
            "/img_quizz/INP10.png",
            "Informática",

            "Cómo los engranajes transmiten movimiento y fuerza entre diferentes piezas.",
            "/img_quizz/ROP10.png",
            "Robótica"
        ));
    }

private void mostrarPreguntaActual() {

    int totalPreguntas = listaPreguntas.size();

    if (indiceActual < totalPreguntas) {

        Preguntas p = listaPreguntas.get(indiceActual);
        ((TarjetaRespuesta) jpInfor).setTitulo(
        p.getTextoOpA()
    );

        ((TarjetaRespuesta) jpRo).setTitulo(
        p.getTextoOpB()
    );

        ((TarjetaRespuesta) jpInfor).setSeleccionado(false);
        ((TarjetaRespuesta) jpRo).setSeleccionado(false);

     lblPreguntaNumero.setText(
        String.format(
                "%02d / %02d",
                indiceActual + 1,
                totalPreguntas
        )
);

        int porcentaje =
                ((indiceActual + 1) * 100) / totalPreguntas;

        jpbProgreso.setMinimum(0);
        jpbProgreso.setMaximum(100);
        jpbProgreso.setValue(porcentaje);
        jpbProgreso.setString(porcentaje + "%");

        lbEnunciado.setText(p.getEnunciado());
        // --- ACTUALIZAR MENSAJE DEL AVATAR ---
            if (avatar != null && indiceActual < MENSAJES_AVATAR.length) {
                avatar.decirMensaje(MENSAJES_AVATAR[indiceActual], 30);
            }

        String estiloTooltip =
                "<html><body style='width:250px;"
                + "background-color:#333333;"
                + "color:#FFFFFF;"
                + "padding:8px;"
                + "font-size:12px;'>";

        lbImagenzquierda.setToolTipText(
                estiloTooltip
                + p.getTextoOpA()
                + "</body></html>"
        );

        lbImagenDerecha.setToolTipText(
                estiloTooltip
                + p.getTextoOpB()
                + "</body></html>"
        );

        try {

            Estilos.imagen150x150(
                    lbImagenzquierda,
                    p.getRutaImgA()
            );

            Estilos.imagen150x150(
                    lbImagenDerecha,
                    p.getRutaImgB()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al cargar imagen: "
                    + e.getMessage()
            );
        }

    } else {

        mostrarResultadoFinal();
    }
}

    private void sumarPunto(String carrera) {
        if (carrera.equals("Informática")) {
            puntosInformatica++;
        } else if (carrera.equals("Robótica")) {
            puntosRobotica++;
        }
    }

    private void siguientePregunta() {
        indiceActual++;
        mostrarPreguntaActual();
    }

    private void mostrarResultadoFinal() {
        String ganadora = (puntosInformatica >= puntosRobotica) ? "Informática" : "Robótica";

        JOptionPane.showMessageDialog(this, 
            "¡Quiz Finalizado con Éxito!\n\n" +
            "Puntos Informática: " + puntosInformatica + "\n" +
            "Puntos Robótica: " + puntosRobotica + "\n\n" +
            "Resultado Sugerido: " + ganadora,
            "Resultados Vocacionales",
            JOptionPane.INFORMATION_MESSAGE);
        btnResultados.setVisible(true);
        lbImagenzquierda.setToolTipText(null);
        lbImagenDerecha.setToolTipText(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelCircuitos = new Quizz.PanelCircuitos ();
        btnResultados = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelAvatar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jpInfor = new Quizz.TarjetaRespuesta();
        lbImagenzquierda = new javax.swing.JLabel();
        jpRo = new Quizz.TarjetaRespuesta();
        lbImagenDerecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbEnunciado = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jpbProgreso = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblPreguntaNumero = new Quizz.PanelNumeroPregunta();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelCircuitos.setBackground(new java.awt.Color(255, 255, 255));
        PanelCircuitos.setRequestFocusEnabled(false);

        btnResultados.setBackground(new java.awt.Color(235, 243, 250));
        btnResultados.setFont(new java.awt.Font("Pixel Digivolve", 1, 26)); // NOI18N
        btnResultados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosL/ir.png"))); // NOI18N
        btnResultados.setText("Ver Resultados");
        btnResultados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Pixel Digivolve", 1, 58)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(29, 53, 87));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QUIZZ");

        jLabel4.setFont(new java.awt.Font("Super Jello", 0, 44)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("¿A qué carrera perteneces?");

        panelAvatar.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panelAvatarLayout = new javax.swing.GroupLayout(panelAvatar);
        panelAvatar.setLayout(panelAvatarLayout);
        panelAvatarLayout.setHorizontalGroup(
            panelAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );
        panelAvatarLayout.setVerticalGroup(
            panelAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(235, 243, 250));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));

        jpInfor.setBackground(new java.awt.Color(255, 255, 255));
        jpInfor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));
        jpInfor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpInfor.setPreferredSize(new java.awt.Dimension(340, 360));

        lbImagenzquierda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImagenzquierda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        lbImagenzquierda.setPreferredSize(new java.awt.Dimension(270, 250));
        lbImagenzquierda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImagenzquierdaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpInforLayout = new javax.swing.GroupLayout(jpInfor);
        jpInfor.setLayout(jpInforLayout);
        jpInforLayout.setHorizontalGroup(
            jpInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInforLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbImagenzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jpInforLayout.setVerticalGroup(
            jpInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInforLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagenzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpRo.setBackground(new java.awt.Color(255, 255, 255));
        jpRo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));
        jpRo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbImagenDerecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImagenDerecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        lbImagenDerecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImagenDerechaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpRoLayout = new javax.swing.GroupLayout(jpRo);
        jpRo.setLayout(jpRoLayout);
        jpRoLayout.setHorizontalGroup(
            jpRoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbImagenDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpRoLayout.setVerticalGroup(
            jpRoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagenDerecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lbEnunciado.setEditable(false);
        lbEnunciado.setBackground(new java.awt.Color(235, 243, 250));
        lbEnunciado.setColumns(20);
        lbEnunciado.setFont(new java.awt.Font("Super Jello", 1, 24)); // NOI18N
        lbEnunciado.setLineWrap(true);
        lbEnunciado.setRows(5);
        lbEnunciado.setWrapStyleWord(true);
        lbEnunciado.setAutoscrolls(false);
        lbEnunciado.setBorder(null);
        lbEnunciado.setFocusable(false);
        lbEnunciado.setOpaque(false);
        jScrollPane1.setViewportView(lbEnunciado);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));
        jPanel3.setPreferredSize(new java.awt.Dimension(850, 85));

        jpbProgreso.setBackground(new java.awt.Color(235, 243, 250));
        jpbProgreso.setForeground(new java.awt.Color(255, 255, 255));
        jpbProgreso.setMaximum(15);
        jpbProgreso.setPreferredSize(new java.awt.Dimension(600, 26));

        lblPreguntaNumero.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblPreguntaNumero.setText("Pregunta 1 de 15");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblPreguntaNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jpbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 45, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPreguntaNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jpInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpRo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpRo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpInfor, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );

        jLabel3.setFont(new java.awt.Font("Pixel Digivolve", 1, 65)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(29, 53, 87));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("VOCACIONAL");

        btnRegresar.setFont(new java.awt.Font("Pixel Digivolve", 0, 25)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosL/volver.png"))); // NOI18N
        btnRegresar.setText("Regresar al Menú");
        btnRegresar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 51), 2, true));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);

        javax.swing.GroupLayout PanelCircuitosLayout = new javax.swing.GroupLayout(PanelCircuitos);
        PanelCircuitos.setLayout(PanelCircuitosLayout);
        PanelCircuitosLayout.setHorizontalGroup(
            PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addGap(0, 508, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(897, 1115, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelCircuitosLayout.setVerticalGroup(
            PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)))
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCircuitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCircuitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbImagenDerechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagenDerechaMouseClicked
        // TODO add your handling code here:
        ((TarjetaRespuesta) jpInfor).setSeleccionado(false);
    ((TarjetaRespuesta) jpRo).setSeleccionado(true);

    sumarPunto(
            listaPreguntas.get(indiceActual).getCarreraB()
    );

    javax.swing.Timer timer =
            new javax.swing.Timer(350, e -> {

                siguientePregunta();

                ((TarjetaRespuesta) jpInfor)
                        .setSeleccionado(false);

                ((TarjetaRespuesta) jpRo)
                        .setSeleccionado(false);
            });

    timer.setRepeats(false);
    timer.start();
    }//GEN-LAST:event_lbImagenDerechaMouseClicked

    private void lbImagenzquierdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagenzquierdaMouseClicked
        // TODO add your handling code here:
        ((TarjetaRespuesta) jpInfor).setSeleccionado(true);
    ((TarjetaRespuesta) jpRo).setSeleccionado(false);

    sumarPunto(
            listaPreguntas.get(indiceActual).getCarreraA()
    );

    javax.swing.Timer timer =
            new javax.swing.Timer(350, e -> {

                siguientePregunta();

                ((TarjetaRespuesta) jpInfor)
                        .setSeleccionado(false);

                ((TarjetaRespuesta) jpRo)
                        .setSeleccionado(false);
            });

    timer.setRepeats(false);
    timer.start();
       
    }//GEN-LAST:event_lbImagenzquierdaMouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        Login.Menu menu = new Login.Menu(jugadorC, seccionC);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Quizz_Vocacional(null, null).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCircuitos;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnResultados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpInfor;
    private javax.swing.JPanel jpRo;
    private javax.swing.JProgressBar jpbProgreso;
    private javax.swing.JTextArea lbEnunciado;
    private javax.swing.JLabel lbImagenDerecha;
    private javax.swing.JLabel lbImagenzquierda;
    private javax.swing.JLabel lblPreguntaNumero;
    private javax.swing.JPanel panelAvatar;
    // End of variables declaration//GEN-END:variables
}
