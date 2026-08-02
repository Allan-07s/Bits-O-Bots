/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Quizz;

import Avatar.AvatarPanel;
import java.awt.BorderLayout;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

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
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Quizz_Vocacional.class.getName());

    /**
     * Creates new form Quizz_Vocacional
     * @param jugador
     * @param seccion
     */
    
    public Quizz_Vocacional(String jugador, String seccion) {
        initComponents();
        
        jugadorC = jugador;
        seccionC = seccion;
        
        jpbProgreso.setUI(new BasicProgressBarUI());
        jpbProgreso.setForeground(new Color(59, 130, 246));
        jpbProgreso.setBackground(new Color(220, 230, 240));
        
        setExtendedState(Quizz_Vocacional.MAXIMIZED_BOTH);
        jButton1.setVisible(false);
        
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
        lbImagenzquierda.setPreferredSize(new Dimension(150, 150));
        lbImagenDerecha.setPreferredSize(new Dimension(150, 150));

        lbImagenzquierda.setMinimumSize(new Dimension(150, 150));
        lbImagenDerecha.setMinimumSize(new Dimension(150, 150));

        lbImagenzquierda.setMaximumSize(new Dimension(150, 150));
        lbImagenDerecha.setMaximumSize(new Dimension(150, 150));
        
        jPanel2.setPreferredSize(new Dimension(1200, 700));
        jPanel2.setMinimumSize(new Dimension(1200, 700));
        jPanel2.setMaximumSize(new Dimension(1200, 700));
        
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void cargarListaPreguntas() {
        // Pregunta 1
        listaPreguntas.add(new Preguntas(
            "1. ¿Qué tipo de creación tecnológica te gustaría desarollar?",

            "Diseñar máquinas móviles capaces de desplazarse y responder a su entorno.",
            "/img_quizz/Pregunta1-R.png",
            "Robótica",

            "Crear experiencias digitales, como videojuegos.",
            "/img_quizz/Pregunta1-I.png",
            "Informática"
            ));

        // Pregunta 2
        listaPreguntas.add(new Preguntas(
            "2. ¿Qué forma de resolver problemas tecnológicos te interesa más?",

            "Crear instrucciones y soluciones mediante programación.",
            "/img_quizz/Pregunta2-I.png",
            "Informática",

            "Integrar componentes electrónicos para hacer funcionar un sistema.",
            "/img_quizz/Pregunta2-R.png",
            "Robótica"
        ));

        // Pregunta 3
        listaPreguntas.add(new Preguntas(
            "3. ¿En cuál actividad tecnológica te gustaría concentrarte?",

            "Conectar componentes y comprobar el funcionamiento de un circuito.",
            "/img_quizz/Pregunta3-R.png",
            "Robótica",

            "Analizar y desarrollar soluciones utilizando una computadora.",
            "/img_quizz/Pregunta3-I.png",
            "Informática"
        ));

        // Pregunta 4
        listaPreguntas.add(new Preguntas(
            "4. ¿Qué equipo te gustaría aprender a diagnosticar y reparar?",

            "Un robot y sus piezas mecánicas y electrónicas.",
            "/img_quizz/Pregunta4-R.png",
            "Robótica",
                
            "Una computadora y sus componentes internos.",
            "/img_quizz/Pregunta4-I.png",
            "Informática"    
        ));
            
        // Pregunta 5
        listaPreguntas.add(new Preguntas(
            "5. ¿Qué funcionamiento te interesa comprender mejor?",

            "Cómo se comunican y comparten información diferentes dispositivos.",
            "/img_quizz/Pregunta5-I.jpeg",
            "Informática",

            "Cómo un mecanismo recibe órdenes y realiza movimientos.",
            "/img_quizz/Pregunta5-R.png",
            "Robótica"
        ));
        
        // Pregunta 6
        listaPreguntas.add(new Preguntas(
            "6. ¿Qué tipo de sistemas te gustaría configurar?",

            "Una red para conectar computadoras y compartir recursos.",
            "/img_quizz/Pregunta6-I.png",
            "Informática",

            "Un sistema automático que utilice sensores para controlar el riego.",
            "/img_quizz/Pregunta6-R.png",
            "Robótica"
        ));

        // Pregunta 7
        listaPreguntas.add(new Preguntas(
            "7. ¿Qué tipo de sistema te gustaría aprender a controlar?",

            "Controlar dispositivos que se desplazan en el espacio físico.",
            "/img_quizz/Pregunta7-R.png",
            "Robótica",

            "Explorar y crear entornos digitales inmersivos.",
            "/img_quizz/Pregunta7-I.png",
            "Informática"
        ));

        // Pregunta 8
        listaPreguntas.add(new Preguntas(
            "8. ¿Qué propósito tecnológico te interesa más?",

            "Proteger información y sistemas frente a amenazas digitales.",
            "/img_quizz/Pregunta8-I.png",
            "Informática",

            "Crear dispositivos que apoyen o recuperen el movimiento humano.",
            "/img_quizz/Pregunta8-R.png",
            "Robótica"
        ));

        // Pregunta 9
        listaPreguntas.add(new Preguntas(
            "9. ¿Qué proceso tecnológico te gustaría comprender?",

            "Cómo un sistema detecta la presencia de una persona y activa un mecanismo.",
            "/img_quizz/Pregunta9-R.png",
            "Robótica",

            "Cómo un dispositivo captura imágenes y las convierte en información digital.",
            "/img_quizz/Pregunta9-I.png",
            "Informática"
        ));
            
        // Pregunta 10
        listaPreguntas.add(new Preguntas(
            "10. ¿Qué principio tecnológico te causa más curiosidad?",

            "Cómo las computadoras representan y procesan información mediante código binario.",
            "/img_quizz/Pregunta10-I.png",
            "Informática",

            "Cómo los engranajes transmiten movimiento y fuerza entre diferentes piezas.",
            "/img_quizz/Pregunta10-R.png",
            "Robótica"
        ));
    }

    private void mostrarPreguntaActual() {
        int totalPreguntas = listaPreguntas.size();

        if (indiceActual < totalPreguntas) {
            Preguntas p = listaPreguntas.get(indiceActual);

            // Actualizar barra y textos de la pregunta
            lblPreguntaNumero.setText(String.format("Pregunta %02d de %02d", indiceActual + 1, totalPreguntas));
            jpbProgreso.setMinimum(0);
            jpbProgreso.setMaximum(totalPreguntas);
            jpbProgreso.setValue(indiceActual + 1);

            lbEnunciado.setText(p.getEnunciado());
            String estiloTooltip = "<html><body style='width: 250px; background-color: #333333; color: #FFFFFF; padding: 8px; font-size: 12px;'>";

            // Asignar el texto formateado a cada imagen
            lbImagenzquierda.setToolTipText(estiloTooltip + p.getTextoOpA() + "</body></html>");
            lbImagenDerecha.setToolTipText(estiloTooltip + p.getTextoOpB() + "</body></html>");


            // Actualizar imágenes
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
                System.out.println("Error al cargar imagen: " + e.getMessage());
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
        jButton1.setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpbProgreso = new javax.swing.JProgressBar();
        lblPreguntaNumero = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpInfor = new javax.swing.JPanel();
        lbImagenzquierda = new javax.swing.JLabel();
        jpRo = new javax.swing.JPanel();
        lbImagenDerecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbEnunciado = new javax.swing.JTextArea();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelAvatar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setRequestFocusEnabled(false);

        jPanel2.setBackground(new java.awt.Color(235, 243, 250));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));

        jpbProgreso.setBackground(new java.awt.Color(235, 243, 250));
        jpbProgreso.setForeground(new java.awt.Color(255, 255, 255));
        jpbProgreso.setMaximum(15);

        lblPreguntaNumero.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblPreguntaNumero.setText("Pregunta 1 de 15");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cpu (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(678, 678, 678))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPreguntaNumero)
                .addGap(60, 60, 60)
                .addComponent(jpbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreguntaNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jpInfor.setBackground(new java.awt.Color(255, 255, 255));
        jpInfor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));
        jpInfor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                .addContainerGap()
                .addComponent(lbImagenzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpInforLayout.setVerticalGroup(
            jpInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInforLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagenzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpRo.setBackground(new java.awt.Color(255, 255, 255));
        jpRo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));
        jpRo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                .addContainerGap()
                .addComponent(lbImagenDerecha, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpRoLayout.setVerticalGroup(
            jpRoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagenDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lbEnunciado.setEditable(false);
        lbEnunciado.setBackground(new java.awt.Color(235, 243, 250));
        lbEnunciado.setColumns(20);
        lbEnunciado.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lbEnunciado.setLineWrap(true);
        lbEnunciado.setRows(5);
        lbEnunciado.setWrapStyleWord(true);
        lbEnunciado.setAutoscrolls(false);
        lbEnunciado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        lbEnunciado.setFocusable(false);
        lbEnunciado.setOpaque(false);
        jScrollPane1.setViewportView(lbEnunciado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jpInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpRo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jpInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jpRo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        lblTitulo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 28)); // NOI18N
        lblTitulo.setText("QUIZZ VOCACIONAL");

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(0, 51, 102));
        lblSubtitulo.setText("¿A qué carrera perteneces?");

        panelAvatar.setBackground(new java.awt.Color(255, 255, 255));
        panelAvatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelAvatarLayout = new javax.swing.GroupLayout(panelAvatar);
        panelAvatar.setLayout(panelAvatarLayout);
        panelAvatarLayout.setHorizontalGroup(
            panelAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelAvatarLayout.setVerticalGroup(
            panelAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jButton1.setText("Ver Resultados");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSubtitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSubtitulo)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(384, 384, 384)
                        .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbImagenzquierdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagenzquierdaMouseClicked
        // TODO add your handling code here:
        sumarPunto(listaPreguntas.get(indiceActual).getCarreraA());
        siguientePregunta();
    }//GEN-LAST:event_lbImagenzquierdaMouseClicked

    private void lbImagenDerechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagenDerechaMouseClicked
        // TODO add your handling code here:
        sumarPunto(listaPreguntas.get(indiceActual).getCarreraB());
        siguientePregunta();
    }//GEN-LAST:event_lbImagenDerechaMouseClicked

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAvatar;
    // End of variables declaration//GEN-END:variables
}
