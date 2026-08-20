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
import ResultadoQuizz.Informatica;
import ResultadoQuizz.Robotica;
import java.awt.Font;
import Login.Menu;
import BaseDeDatos.conexion;

/**
 *
 * @author alvar
 */
public class Quizz_Vocacional extends javax.swing.JFrame {
    
    private final String jugadorC;
    private final String seccionC;
    
    private String ganadora;
    private int porcentajeGanadora;
    
    private List<Preguntas> listaPreguntas = new ArrayList<>();
    private int indiceActual = 0;
    private int puntosInformatica = 0;
    private int puntosRobotica = 0;
    
    private AvatarPanel avatar;
    private final Menu menuC;
    
    //Toca cambiar TODO esto
    private final String[] MENSAJES_AVATAR = {
        // Preguntas 1 a 10 (Bloque de imágenes)
        "¡Bienvenido! Empecemos analizando cómo te orientas al ver un mapa.",
        "Acomodar objetos en un espacio reducido: ¿lógica o rotación física?",
        "Al contemplar un edificio: ¿piensas en la planificación o en los soportes?",
        "Secuencia de figuras: ¿notaste la regla matemática o el giro 3D?",
        "Frente a un gráfico denso: ¿te enfocas en los datos o en las trayectorias?",
        "Si un aparato falla: ¿culpas a la configuración o a una pieza floja?",
        "¿Cómo mapeas un problema: esquematizas el mecanismo o creas reglas?",
        "Si una pieza no encaja: ¿relees el manual o la mides y giras manualmente?",
        "Juegos de estrategia: ¿calculas decisiones o dominas el espacio físico?",
        "Para memorizar: ¿prefieres listas ordenadas o asociar movimientos?",

        // Preguntas 11 a 20 (Bloque de videos y pensamiento)
        "¡Pasamos a los videos! ¿Entorno en pantalla o taller con herramientas?",
        "Ante un desafío: ¿te motiva un logro físico visible o hallar la falla lógica?",
        "¿Cómo aprendes mejor: comprendiendo la teoría o probando con las manos?",
        "Retos mentales: ¿rompecabezas 3D o acertijos y patrones de datos?",
        "¿Qué error te molesta más: un dato mal calculado o un desalineamiento?",
        "Tu forma de pensar: ¿es analítica con reglas o visual basada en fuerzas?",
        "En la ciencia ficción: ¿te atraen los datos de la red o los motores del robot?",
        "Clasificar 100 objetos: ¿por categorías digitales o por propiedades físicas?",
        "Concentración máxima: ¿atención a texto/símbolos o coordinación mano-ojo?",
        "¡Llegamos a la 20! ¿Orgullo por un código elegante o por un mecanismo fluido?",

        // Preguntas 21 a 30 (Casos prácticos de aplicación)
        "Control de acceso: ¿reglas lógicas de contraseña o la cerradura física?",
        "Falla en almacén: ¿revisas el sensor mecánico o la base de datos?",
        "Pizzería automatizada: ¿el menú interactivo o el brazo robótico?",
        "Invernadero inteligente: ¿soldar los sensores o programar alertas por correo?",
        "Vehículo autónomo: ¿el algoritmo de ruta o la potencia de los motores?",
        "Mantenimiento: ¿seguridad y registros de red o tarjetas de circuitos?",
        "Dron de rescate: ¿reconocimiento de imágenes o balance de las hélices?",
        "Pruebas de calidad: ¿resistencia a caídas o ingresar datos extremos?",
        "Prótesis médica: ¿traducir impulsos neuronales o la mecánica de agarre?",
        "¡Última pregunta! ¿Procesamiento en milisegundos o precisión milimétrica?"
    };
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Quizz_Vocacional.class.getName());

    /**
     * Creates new form Quizz_Vocacional
     * @param jugador
     * @param seccion
     */
    
    public Quizz_Vocacional(String jugador, String seccion, Menu menu) {
        initComponents();
        menuC = menu;
        
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

        // Estilizado moderno para el enunciado de la pregunta
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
        
        // Instanciamos el avatar Bug
        this.avatar = new AvatarPanel();
        panelAvatar.setLayout(new BorderLayout());
        panelAvatar.add(avatar);
        avatar.startAnimation();

        // --- AGREGAR ESTAS LÍNEAS PARA FORZAR EL RENDERIZADO ---
        panelAvatar.revalidate();
        panelAvatar.repaint();
    }
    
    private void dimensiones(){
        jPanel2.setPreferredSize(new Dimension(1200, 700));
        jPanel2.setMinimumSize(new Dimension(1200, 700));
        jPanel2.setMaximumSize(new Dimension(1200, 700));
    }
    
    private void cargarListaPreguntas() {
        // Pregunta 1 (Mapeo visual vs. Secuencia lógica)
        listaPreguntas.add(new Preguntas(
            "1. Cuando miras un plano o croquis sencillo para llegar a un lugar nuevo, ¿qué se te facilita más?",
            "Imaginar mentalmente la ruta completa en 3D como si la estuvieras viendo desde arriba.", "/img_quizz/", "Robótica",
            "Recordar los nombres de las calles, referencias escritas y la secuencia exacta de pasos.", "/img_quizz/", "Informática"
        ));

        // Pregunta 2 (Optimización conceptual vs. Manipulación física)
        listaPreguntas.add(new Preguntas(
            "2. Al ordenar cajas u objetos de distintos tamaños en un espacio reducido, ¿cuál es tu fuerte?",
            "Calcular mentalmente el volumen total y organizar la secuencia lógica de acomodo antes de moverlas.", "/img_quizz/", "Informática",
            "Rotar, acomodar y presionar físicamente cada caja hasta sentir que encajaron perfectamente.", "/img_quizz/", "Robótica"
        ));

        // Pregunta 3 (Rotación 3D vs. Patrón matemático)
        listaPreguntas.add(new Preguntas(
            "3. Si ves una serie de figuras geométricas ordenadas en secuencia, ¿qué te resulta más sencillo notar?",
            "La rotación espacial y cómo se vería la figura al girarla en tres dimensiones.", "/img_quizz/", "Robótica",
            "La regla matemática o el patrón numérico bajo el cual cambian los elementos.", "/img_quizz/", "Informática"
        ));

        // Pregunta 4 (Diagnóstico de software vs. Diagnóstico de hardware)
        listaPreguntas.add(new Preguntas(
            "4. Si un aparato en tu casa empieza a fallar de repente, ¿qué intuyes primero?",
            "Que hubo un error en la configuración, en las órdenes guardadas o en el sistema interno.", "/img_quizz/", "Informática",
            "Que hay una pieza floja, un cable desgastado o un engrane haciendo falso contacto.", "/img_quizz/", "Robótica"
        ));

        // Pregunta 5 (Diagramas de flujo vs. Diagramas físicos)
        listaPreguntas.add(new Preguntas(
            "5. Cuando se te presenta un problema complejo, ¿cómo prefieres estructurarlo?",
            "Dibujando un esquema del mecanismo o mapeando visualmente cómo se conectan las partes físicas.", "/img_quizz/", "Robótica",
            "Dividiendo el problema en una lista de reglas, instrucciones paso a paso y condiciones lógicas.", "/img_quizz/", "Informática"
        ));

        // Pregunta 6 (Paso a paso lógico vs. Ensayo visual/físico)
        listaPreguntas.add(new Preguntas(
            "6. Si estás armando algo y una pieza no encaja donde creías que iba, ¿cuál es tu primera acción?",
            "Releer el instructivo desde el inicio para encontrar en qué punto de la secuencia estuvo el error.", "/img_quizz/", "Informática",
            "Inspeccionar la pieza físicamente, medir los encajes y probar girándola en distintos ángulos.", "/img_quizz/", "Robótica"
        ));

        // Pregunta 7 (Mnemotecnia conceptual vs. Memoria quinestésica)
        listaPreguntas.add(new Preguntas(
            "7. Si tienes que memorizar un procedimiento largo, ¿qué técnica te funciona mejor?",
            "Crear un acrónimo, regla nemotécnica o lista ordenada de términos.", "/img_quizz/", "Informática",
            "Asociar cada paso con un movimiento corporal, gesto o manipulación física.", "/img_quizz/", "Robótica"
        ));

        // Pregunta 8 (Lógica pura vs. Acción física)
        listaPreguntas.add(new Preguntas(
            "8. Si llevas bastante tiempo intentando resolver un problema sin éxito, ¿qué te devuelve la motivación?",
            "Ver que un intento manual finalmente produce un movimiento o resultado físico visible.", "/video_quizz/", "Robótica",
            "Descubrir la falla lógica invisible o la regla mal aplicada que nadie más había notado.", "/video_quizz/", "Informática"
        ));

        // Pregunta 9 (Acertijos lógicos vs. Retos tridimensionales)
        listaPreguntas.add(new Preguntas(
            "9. ¿Qué tipo de reto mental te produce mayor satisfacción?",
            "Resolver rompecabezas tridimensionales, armar modelos a escala o manipular el cubo Rubik.", "/video_quizz/", "Robótica",
            "Descifrar acertijos numéricos, resolver crucigramas o encontrar patrones de datos.", "/video_quizz/", "Informática"
        ));

        // Pregunta 10 (Validación de datos vs. Actuación electromecánica)
        listaPreguntas.add(new Preguntas(
            "10. En la creación de una aplicación para control de acceso, ¿qué fase del desarrollo te atrae más?",
            "Escribir las reglas lógicas que verifican si la contraseña del usuario es correcta antes de darle paso.", "/video_quizz/", "Informática",
            "Conectar la cerradura eléctrica a la placa de control para que el pulso libere el pestillo físico.", "/video_quizz/", "Robótica"
        ));

        // Pregunta 11 (Bases de datos vs. Sensores y actuadores)
        listaPreguntas.add(new Preguntas(
            "11. Si un sistema de inventario comete un error al registrar productos en un almacén, ¿dónde buscas la causa?",
            "En el escáner de la banda de transporte, revisando si el sensor óptico o el brazo mecánico fallaron.", "/video_quizz/", "Robótica",
            "En la base de datos, revisando si las tablas guardaron mal el número de identificación del producto.", "/video_quizz/", "Informática"
        ));

        // Pregunta 12 (Redes y protocolos vs. Electrónica de potencia)
        listaPreguntas.add(new Preguntas(
            "12. Al implementar un sistema de monitoreo ambiental en un invernadero, ¿qué parte disfrutas resolver?",
            "Soldar y ubicar los sensores de humedad en la tierra conectándolos con los cables a la fuente de poder.", "/video_quizz/", "Robótica",
            "Configurar el envío de alertas automáticas por correo cuando la temperatura supera el límite permitido.", "/video_quizz/", "Informática"
        ));

        // Pregunta 13 (Ciberseguridad y registros vs. Mantenimiento físico de circuitos)
        listaPreguntas.add(new Preguntas(
            "13. Al dar mantenimiento a la infraestructura de una empresa, ¿qué trabajo te resulta más interesante?",
            "Inspeccionar las tarjetas de circuitos, limpiar los componentes y reemplazar piezas quemadas.", "/video_quizz/", "Robótica",
            "Analizar los registros de red para detectar accesos no autorizados y corregir fallas de seguridad.", "/video_quizz/", "Informática"
        ));

        // Pregunta 14 (Traducción de señales en software vs. Mecánica de articulaciones)
        listaPreguntas.add(new Preguntas(
            "14. Si vas a construir una prótesis médica moderna, ¿cuál sería tu aporte principal?",
            "Programar la traducción de los impulsos neuronales o musculares en instrucciones digitales claras.", "/video_quizz/", "Informática",
            "Seleccionar los servosistemas, engranajes y articulaciones para que la mano tenga agarre y fuerza.", "/video_quizz/", "Robótica"
        ));

        // Pregunta 15 (Rendimiento de procesamiento vs. Precisión de movimiento)
        listaPreguntas.add(new Preguntas(
            "15. Al finalizar un sistema interactivo, ¿cuál de estas dos mejoras te genera más satisfacción haber logrado?",
            "Reducir el tiempo de respuesta del sistema para que procese miles de instrucciones en milisegundos.", "/video_quizz/", "Informática",
            "Lograr que la respuesta física del mecanismo sea fluida, suave y responda exactamente en los milímetros deseados.", "/video_quizz/", "Robótica"
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
            ));

        int porcentaje = ((indiceActual + 1) * 100) / totalPreguntas;

        jpbProgreso.setMinimum(0);
        jpbProgreso.setMaximum(100);
        jpbProgreso.setValue(porcentaje);
        jpbProgreso.setString(porcentaje + "%");

        lbEnunciado.setText(p.getEnunciado());
        
        // --- ACTUALIZAR MENSAJE DEL AVATAR ---
        if (avatar != null && indiceActual < MENSAJES_AVATAR.length) {
            avatar.decirMensaje(MENSAJES_AVATAR[indiceActual], 30);
        }
            try {
                Estilos.multimedia(lbImagenzquierda, p.getRutaOpA()
                );
                Estilos.multimedia(lbImagenDerecha, p.getRutaOpB()
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
        int totalPreguntas = listaPreguntas.size();
        
        if (puntosInformatica > puntosRobotica) {
            ganadora = "Informática";
            porcentajeGanadora = (puntosInformatica * 100) / totalPreguntas;
        } else if (puntosRobotica > puntosInformatica) {
            ganadora = "Robótica";
            porcentajeGanadora = (puntosRobotica * 100) / totalPreguntas;
        }
        JOptionPane.showMessageDialog(
            this,
            "¡Quiz Finalizado con Éxito!\n\n" +
            "Puntos Informática: " + puntosInformatica + "\n" +
            "Puntos Robótica: " + puntosRobotica + "\n\n" +
            "Resultado Sugerido: " + ganadora,
            "Resultados Vocacionales",
            JOptionPane.INFORMATION_MESSAGE
        );
        btnResultados.setVisible(true);
        lbImagenzquierda.setToolTipText(null);
        lbImagenDerecha.setToolTipText(null);
        
        insertarDatos();
    }
    
    private void insertarDatos() {
        String insertar = "INSERT INTO tbl_registro(Nombre, Grado, Seccion, puntosInformatica, puntosRobotica, Recomendada) VALUES (?, ?, ?, ?, ?, ?)";

        conexion objetoConexion = new conexion();

        try (java.sql.Connection con = objetoConexion.crearConexion();
             java.sql.PreparedStatement ps = con.prepareStatement(insertar)) {

            // Asegurar que guarde de inmediato en el servidor
            con.setAutoCommit(true);

            String[] partesSeccion = seccionC.split(" - ");
            String grado = partesSeccion[0];
            String seccion = partesSeccion.length > 1 ? partesSeccion[1] : "";

            ps.setString(1, jugadorC);
            ps.setString(2, grado);
            ps.setString(3, seccion);

            // Asignar enteros si las columnas en MySQL son INT
            ps.setInt(4, puntosInformatica); 
            ps.setInt(5, puntosRobotica);
            ps.setString(6, ganadora);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Registro completado con éxito en la BD");
            } else {
                JOptionPane.showMessageDialog(null, "No se insertó ningún registro.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar en la BD: " + e.getMessage());
            e.printStackTrace(); // Revisa la consola de NetBeans para ver el error exacto si ocurre
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
        btnResultados.addActionListener(this::btnResultadosActionPerformed);

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
        if (menuC != null) {
            menuC.setVisible(true);
        } 
        else {
            Menu menu = new Menu(jugadorC, seccionC); 
            menu.setLocationRelativeTo(null); 
            menu.setVisible(true);
        }
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadosActionPerformed
        // TODO add your handling code here:
        if ("Informática".equals(ganadora)) {
            Informatica info = new Informatica(ganadora, porcentajeGanadora);
            info.setVisible(true);
            this.dispose();
        } else if ("Robótica".equals(ganadora)) {
            Robotica rob = new Robotica(ganadora, porcentajeGanadora);
            rob.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnResultadosActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Quizz_Vocacional(null, null, null).setVisible(true));
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
