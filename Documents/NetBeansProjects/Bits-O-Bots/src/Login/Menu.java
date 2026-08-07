/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import Avatar.AvatarPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import Quizz.Quizz_Vocacional;
import JuegoMemoria.MenuJuego;

/**
 *
 * @author allan
 */
public class Menu extends javax.swing.JFrame {

    private final String nombreC;
    private final String seccionC;

    private static final java.util.logging.Logger logger
            = java.util.logging.Logger.getLogger(Menu.class.getName());

    /**
     * Creates new form Menu
     *
     * @param nombre
     * @param seccion
     */
    public Menu(String nombre, String seccion) {

        // Primero guardamos los datos recibidos.
        nombreC = nombre;
        seccionC = seccion;

        initComponents();

        configurarVentana();
        configurarDiseñoMenu();
    }

    private void configurarVentana() {
        setTitle("Bits o Bots - Menú principal");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // Evita que la ventana quede demasiado pequeña.
        setMinimumSize(new java.awt.Dimension(1000, 650));
    }

    private void configurarDiseñoMenu() {
      
        configurarTarjetas();
        configurarAvatar();
        configurarJugador();
        configurarBienvenida();
        PanelCircuitos.revalidate();
         PanelCircuitos.repaint();
    }

  private void configurarBienvenida() {

    String nombreMostrar = nombreC;

    if (nombreMostrar == null || nombreMostrar.isBlank()) {
        nombreMostrar = "Jugador";
    }

    lblBienvenida.setText(
            "¡HOLA, "
            + nombreMostrar.toUpperCase()
            + "! 👋"
    );

    lblBienvenida.setFont(
            new java.awt.Font(
                    "Super Jello",
                    java.awt.Font.BOLD,
                    22
            )
    );

    lblBienvenida.setForeground(
            new java.awt.Color(
                    20,
                    95,
                    190
            )
    );

    lblPreguntaMenu.setText(
            "¿Qué quieres hacer hoy?"
    );

    lblPreguntaMenu.setFont(
            new java.awt.Font(
                    "Stencil",
                    java.awt.Font.BOLD,
                    17
            )
    );

    lblPreguntaMenu.setForeground(
            new java.awt.Color(
                    80,
                    105,
                    125
            )
    );
}

    private void configurarTarjetas() {

        // Tarjeta del Quiz Vocacional
        TarjetaMenu tarjetaQuiz = new TarjetaMenu(
                "QUIZ VOCACIONAL",
                "Descubre qué área va contigo",
                "/iconosL/gambling (1).png",
                new Color(25, 95, 230)
        );

        tarjetaQuiz.setAccion(this::abrirQuiz);

        panelQuiz.setOpaque(false);
        panelQuiz.setLayout(new BorderLayout());
        panelQuiz.removeAll();
        panelQuiz.add(tarjetaQuiz, BorderLayout.CENTER);

        // Tarjeta del Juego de Cartas
        TarjetaMenu tarjetaCartas = new TarjetaMenu(
                "JUEGO DE CARTAS",
                "Pon a prueba tu memoria",
                "/iconosL/gambling (1).png",
                new Color(15, 175, 185)
        );

        tarjetaCartas.setAccion(this::abrirJuegoCartas);

        panelCartas.setOpaque(false);
        panelCartas.setLayout(new BorderLayout());
        panelCartas.removeAll();
        panelCartas.add(tarjetaCartas, BorderLayout.CENTER);
    }

    private void configurarAvatar() {

        PanelPlataformaAvatar plataforma
                = new PanelPlataformaAvatar();

        AvatarPanel avatarAnimado
                = new AvatarPanel();

        plataforma.setOpaque(false);
        plataforma.setLayout(new BorderLayout());
        plataforma.add(avatarAnimado, BorderLayout.CENTER);

        panelAvatar.setOpaque(false);
        panelAvatar.setLayout(new BorderLayout());
        panelAvatar.removeAll();
        panelAvatar.add(plataforma, BorderLayout.CENTER);

        avatarAnimado.startAnimation();

        panelAvatar.revalidate();
        panelAvatar.repaint();
    }

   private void abrirQuiz() {
    try {
        Quizz_Vocacional quiz
                = new Quizz_Vocacional(nombreC, seccionC);

        quiz.setLocationRelativeTo(this);
        quiz.setVisible(true);
        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "No se pudo abrir el Quiz Vocacional.\n"
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        logger.log(
                java.util.logging.Level.SEVERE,
                "Error al abrir el quiz",
                e
        );
    }
}
   private void configurarJugador() {

    PanelJugador jugador
            = (PanelJugador) panelJugador;

    jugador.setDatos(
            nombreC,
            seccionC
    );

    panelJugador.setOpaque(false);
}
   
   private void abrirJuegoCartas() {
    try {
        MenuJuego juegoCartas
                = new MenuJuego(nombreC, seccionC);

        juegoCartas.setLocationRelativeTo(this);
        juegoCartas.setVisible(true);
        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "No se pudo abrir el Juego de Cartas.\n"
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        logger.log(
                java.util.logging.Level.SEVERE,
                "Error al abrir el juego de cartas",
                e
        );
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelCircuitos = new PanelMenuCircuitos();
        panelAvatar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelQuiz = new TarjetaMenu();
        panelCartas = new TarjetaMenu();
        lblSelecciona = new javax.swing.JLabel();
        panelJugador = new PanelJugador();
        lblBienvenida = new javax.swing.JLabel();
        lblPreguntaMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelCircuitos.setBackground(new java.awt.Color(255, 255, 255));

        panelAvatar.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout panelAvatarLayout = new javax.swing.GroupLayout(panelAvatar);
        panelAvatar.setLayout(panelAvatarLayout);
        panelAvatarLayout.setHorizontalGroup(
            panelAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        panelAvatarLayout.setVerticalGroup(
            panelAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Pixel Digivolve", 0, 58)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("MENU");

        jLabel2.setFont(new java.awt.Font("Pixel Digivolve", 0, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 66, 119));
        jLabel2.setText("BITS");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosL/computer-mouse.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Pixel Digivolve", 0, 55)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 66, 119));
        jLabel4.setText("BOTS");

        jLabel5.setFont(new java.awt.Font("Super Jello", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(92, 133, 146));
        jLabel5.setText("APRENDE • JUEGA • DESCUBRE");

        panelQuiz.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout panelQuizLayout = new javax.swing.GroupLayout(panelQuiz);
        panelQuiz.setLayout(panelQuizLayout);
        panelQuizLayout.setHorizontalGroup(
            panelQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelQuizLayout.setVerticalGroup(
            panelQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        panelCartas.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout panelCartasLayout = new javax.swing.GroupLayout(panelCartas);
        panelCartas.setLayout(panelCartasLayout);
        panelCartasLayout.setHorizontalGroup(
            panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        panelCartasLayout.setVerticalGroup(
            panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );

        lblSelecciona.setFont(new java.awt.Font("Super Jello", 0, 20)); // NOI18N
        lblSelecciona.setForeground(new java.awt.Color(92, 133, 146));
        lblSelecciona.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelecciona.setText("SELECCIONA UNO PARA CONTINUAR");

        panelJugador.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout panelJugadorLayout = new javax.swing.GroupLayout(panelJugador);
        panelJugador.setLayout(panelJugadorLayout);
        panelJugadorLayout.setHorizontalGroup(
            panelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        panelJugadorLayout.setVerticalGroup(
            panelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida.setText("¡");

        lblPreguntaMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreguntaMenu.setText("!");

        javax.swing.GroupLayout PanelCircuitosLayout = new javax.swing.GroupLayout(PanelCircuitos);
        PanelCircuitos.setLayout(PanelCircuitosLayout);
        PanelCircuitosLayout.setHorizontalGroup(
            PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addComponent(lblPreguntaMenu)
                        .addGap(789, 789, 789))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addComponent(lblBienvenida)
                        .addGap(817, 817, 817))))
            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel1)
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270))))
            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(lblSelecciona, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelCartas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelQuiz, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(360, Short.MAX_VALUE))
        );
        PanelCircuitosLayout.setVerticalGroup(
            PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCircuitosLayout.createSequentialGroup()
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(panelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCircuitosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblBienvenida)
                .addGap(17, 17, 17)
                .addComponent(lblPreguntaMenu)
                .addGroup(PanelCircuitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(panelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lblSelecciona))
                    .addGroup(PanelCircuitosLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCircuitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCircuitos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new Menu(null, null).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCircuitos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblPreguntaMenu;
    private javax.swing.JLabel lblSelecciona;
    private javax.swing.JPanel panelAvatar;
    private javax.swing.JPanel panelCartas;
    private javax.swing.JPanel panelJugador;
    private javax.swing.JPanel panelQuiz;
    // End of variables declaration//GEN-END:variables
}
