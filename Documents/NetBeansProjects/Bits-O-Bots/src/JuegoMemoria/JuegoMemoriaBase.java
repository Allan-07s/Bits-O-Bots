package JuegoMemoria;


import Avatar.AvatarPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public abstract class JuegoMemoriaBase extends JFrame {

    private static final int ANCHO_CARTA = 90;
    private static final int ALTO_CARTA = 135;
    private static final int ESPACIO = 10;
    private static final int MARGEN = 16;

    private static final int TIEMPO_OBSERVACION = 1500;
    private static final int PAUSA_FINAL = 500;
    private static final int TIEMPO_INCORRECTAS = 210;

    private static final int PASOS_MOVIMIENTO = 17;
    private static final int VELOCIDAD_MOVIMIENTO = 7;
    private static final int PAUSA_ENTRE_MOVIMIENTOS = 24;

    private static final Color COLOR_BORDE
            = new Color(84, 88, 132);

    private static final Color COLOR_PAREJA
            = new Color(45, 205, 123);

    private final ProgresoJuego progreso;
    private final int numeroNivel;
    private final int cantidadCartas;
    private final int cantidadParejas;
    private final int filas;
    private final int columnas;
    private final int totalIntercambios;

    private final ImageIcon[] imagenesFrente;
    private final String[] nombresCartas;
    private final String[] rutasImagenes;
    private final String[] categoriasCartas;

    private ImageIcon reversoInformatica;
    private ImageIcon reversoRobotica;

    private BotonCarta[] cartas;
    private int[] valores;
    private boolean[] encontradas;

    private int primeraCarta = -1;
    private int segundaCarta = -1;

    private int parejasEncontradas;
    private int movimientos;
    private int puntosNivel;
    private int segundos;

    private boolean bloqueado = true;
    private boolean partidaFinalizada;

    private int numeroPartida;

    private final Random aleatorio = new Random();

    private JPanel panelTablero;
    private JPanel panelAvatar;

    private JLabel lblMovimientos; 
    private JLabel lblPuntosNivel;
    private JLabel lblPuntosTotal;
    private JLabel lblTiempo;
    private JLabel lblMensaje;

    private AvatarPanel avatar;

    private JPanel panelCartas;
    private JPanel contenedorCartas;

    private Timer cronometro;

    protected JuegoMemoriaBase(
            ProgresoJuego progreso,
            int numeroNivel,
            int cantidadCartas,
            int filas,
            int columnas,
            int totalIntercambios,
            String[] nombresCartas,
            String[] rutasImagenes,
            String[] categoriasCartas
    ) {

        this.progreso = progreso;
        this.numeroNivel = numeroNivel;
        this.cantidadCartas = cantidadCartas;
        this.cantidadParejas = cantidadCartas / 2;
        this.filas = filas;
        this.columnas = columnas;
        this.totalIntercambios = totalIntercambios;

        this.nombresCartas = nombresCartas;
        this.rutasImagenes = rutasImagenes;
        this.categoriasCartas = categoriasCartas;

        this.imagenesFrente = new ImageIcon[this.cantidadParejas];

        configurarVentana();
        construirInterfaz();
        cargarImagenes();

        GestorMusica.reproducirFondo(
                "/audio/musica_juego.wav"
        );

        iniciarNivelAutomaticamente();
    }

    private void configurarVentana() {

        setTitle(
                "Memory Tech - Nivel " + numeroNivel
        );

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(true);

    }

    private void construirInterfaz() {

        PanelDegradado fondo = new PanelDegradado(
                new Color(20, 27, 58),
                new Color(76, 47, 126)
        );

        fondo.setLayout(new BorderLayout(10, 10));
        fondo.setBorder(
                BorderFactory.createEmptyBorder(
                        13,
                        19,
                        16,
                        19
                )
        );

        setContentPane(fondo);

        fondo.add(
                crearEncabezado(),
                BorderLayout.NORTH
        );

        panelCartas = new JPanel(null);
        panelCartas.setOpaque(false);

        contenedorCartas = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        0,
                        8
                )
        );

        contenedorCartas.setOpaque(false);
        contenedorCartas.add(panelCartas);

        fondo.add(
                contenedorCartas,
                BorderLayout.CENTER
        );

        lblMensaje = new JLabel(
                "Preparando nivel...",
                SwingConstants.CENTER
        );

        lblMensaje.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        lblMensaje.setForeground(Color.WHITE);
        lblMensaje.setPreferredSize(
                new Dimension(810, 45)
        );

        fondo.add(lblMensaje, BorderLayout.SOUTH);
    }

    private JPanel crearEncabezado() {

        JPanel superior = new JPanel(
                new BorderLayout(10, 8)
        );

        superior.setOpaque(false);

        JPanel filaTitulo = new JPanel(
                new BorderLayout()
        );

        filaTitulo.setOpaque(false);

        JLabel lblJugador = new JLabel(
                progreso.getJugador()
        );

        lblJugador.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        lblJugador.setForeground(
                new Color(226, 228, 244)
        );

        JLabel lblNivel = new JLabel(
                "NIVEL " + numeroNivel
                + "  ·  " + cantidadCartas
                + " CARTAS",
                SwingConstants.CENTER
        );

        lblNivel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        lblNivel.setForeground(Color.WHITE);

        JPanel acciones = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        7,
                        0
                )
        );

        acciones.setOpaque(false);

        BotonIconoMusica btnMusica
                = new BotonIconoMusica();

        btnMusica.setPreferredSize(
                new Dimension(51, 51)
        );

        btnMusica.addActionListener(e -> {
            GestorMusica.alternarSilencio();
            btnMusica.repaint();
        });

        BotonRedondeado btnAbandonar
                = new BotonRedondeado(
                        "Abandonar",
                        new Color(165, 63, 87),
                        new Color(199, 77, 103)
                );

        btnAbandonar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        btnAbandonar.setPreferredSize(
                new Dimension(120, 45)
        );

        btnAbandonar.addActionListener(e -> {
            abandonarPartida();
        });

        acciones.add(btnMusica);
        acciones.add(btnAbandonar);

        filaTitulo.add(lblJugador, BorderLayout.WEST);
        filaTitulo.add(lblNivel, BorderLayout.CENTER);
        filaTitulo.add(acciones, BorderLayout.EAST);

        superior.add(filaTitulo, BorderLayout.NORTH);

        PanelRedondeado panelDatos
                = new PanelRedondeado(
                        25,
                        new Color(255, 255, 255, 25),
                        new Color(255, 255, 255, 55)
                );

        panelDatos.setLayout(
                new GridLayout(1, 4, 8, 0)
        );

        panelDatos.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        14,
                        10,
                        14
                )
        );

        lblMovimientos = crearEtiquetaDato(
                "Movimientos: 0"
        );

        lblPuntosNivel = crearEtiquetaDato(
                "Nivel: 0 pts"
        );

        lblPuntosTotal = crearEtiquetaDato(
                "Total: "
                + progreso.getPuntosTotales()
                + " pts"
        );

        lblTiempo = crearEtiquetaDato(
                "Tiempo: 00:00"
        );

        panelDatos.add(lblMovimientos);
        panelDatos.add(lblPuntosNivel);
        panelDatos.add(lblPuntosTotal);
        panelDatos.add(lblTiempo);

        superior.add(panelDatos, BorderLayout.SOUTH);

        return superior;
    }

    private JLabel crearEtiquetaDato(String texto) {

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

    private void iniciarNivelAutomaticamente() {

        numeroPartida++;
        int partidaActual = numeroPartida;

        primeraCarta = -1;
        segundaCarta = -1;
        parejasEncontradas = 0;
        movimientos = 0;
        puntosNivel = 0;
        segundos = 0;
        partidaFinalizada = false;
        bloqueado = true;

        crearValores();
        crearTablero();

        lblMensaje.setText(
                "¡Memoriza todas las cartas!"
        );

        SwingUtilities.invokeLater(() -> {

            if (partidaActual != numeroPartida) {
                return;
            }

            iniciarPresentacion(partidaActual);
        });
    }

    private void crearValores() {

        ArrayList<Integer> parejas
                = new ArrayList<>();

        for (
                int i = 1;
                i <= cantidadParejas;
                i++
        ) {
            parejas.add(i);
            parejas.add(i);
        }

        Collections.shuffle(parejas);

        valores = new int[cantidadCartas];
        encontradas = new boolean[cantidadCartas];

        for (
                int i = 0;
                i < cantidadCartas;
                i++
        ) {
            valores[i] = parejas.get(i);
        }
    }

    private void crearTablero() {

        panelCartas.removeAll();
        cartas = new BotonCarta[cantidadCartas];

        int anchoPanel
                = (MARGEN * 2)
                + (columnas * ANCHO_CARTA)
                + ((columnas - 1) * ESPACIO);

        int altoPanel
                = (MARGEN * 2)
                + (filas * ALTO_CARTA)
                + ((filas - 1) * ESPACIO);

        panelCartas.setPreferredSize(
                new Dimension(
                        anchoPanel,
                        altoPanel
                )
        );

        for (
                int i = 0;
                i < cantidadCartas;
                i++
        ) {

            BotonCarta carta = new BotonCarta(27);
            carta.setColorBorde(COLOR_BORDE);

            int fila = i / columnas;
            int columna = i % columnas;

            int x = MARGEN
                    + columna
                    * (ANCHO_CARTA + ESPACIO);

            int y = MARGEN
                    + fila
                    * (ALTO_CARTA + ESPACIO);

            carta.setBounds(
                    x,
                    y,
                    ANCHO_CARTA,
                    ALTO_CARTA
            );

            final int posicion = i;

            carta.addActionListener(e -> {
                seleccionarCarta(posicion);
            });

            cartas[i] = carta;
            panelCartas.add(carta);
            mostrarFrente(i);
        }

        panelCartas.revalidate();
        panelCartas.repaint();
        contenedorCartas.revalidate();
        contenedorCartas.repaint();
    }

    private void cargarImagenes() {

        int anchoImagen = ANCHO_CARTA - 8;
        int altoImagen = ALTO_CARTA - 8;
        
        reversoInformatica = cargarIcono(
            "/img/reverso_informatica.PNG",
            anchoImagen,
            altoImagen
        );
        
        reversoRobotica = cargarIcono(
            "/img/reverso_robotica.PNG",
            anchoImagen,
            altoImagen
        );
        
        for (
            int i = 0;
            i < rutasImagenes.length;
            i++
        ) {

        imagenesFrente[i] = cargarIcono(
                rutasImagenes[i],
                anchoImagen,
                altoImagen
            );
        }
    }

    private javax.swing.ImageIcon obtenerReverso( int posicion)
    { 
        int indiceComponente = valores[posicion] - 1;
        String categoria = categoriasCartas[indiceComponente];
        
        if ("ROBOTICA".equalsIgnoreCase(categoria)
                ) { 
            return reversoRobotica;
        }
        
        return reversoInformatica;
    }  
    private ImageIcon cargarIcono(
            String ruta,
            int anchoMaximo,
            int altoMaximo
    ) {

        URL url = getClass().getResource(ruta);

        if (url == null) {
            System.out.println(
                    "No se encontró: " + ruta
            );
            return null;
        }

        ImageIcon original = new ImageIcon(url);

        int anchoOriginal = original.getIconWidth();
        int altoOriginal = original.getIconHeight();

        if (
                anchoOriginal <= 0
                || altoOriginal <= 0
        ) {
            return null;
        }

        double escala = Math.min(
                anchoMaximo
                / (double) anchoOriginal,
                altoMaximo
                / (double) altoOriginal
        );

        int nuevoAncho = Math.max(
                1,
                (int) (anchoOriginal * escala)
        );

        int nuevoAlto = Math.max(
                1,
                (int) (altoOriginal * escala)
        );

        Image escalada = original
                .getImage()
                .getScaledInstance(
                        nuevoAncho,
                        nuevoAlto,
                        Image.SCALE_SMOOTH
                );

        return new ImageIcon(escalada);
    }

    private void iniciarPresentacion(
            int partidaActual
    ) {

        animarEntradaCartas(
                partidaActual,
                () -> {

                    Timer observacion = new Timer(
                            TIEMPO_OBSERVACION,
                            e -> {

                                if (
                                        partidaActual
                                        != numeroPartida
                                ) {
                                    return;
                                }

                                lblMensaje.setText(
                                        "¡Sigue el movimiento de las cartas!"
                                );

                                animarIntercambios(
                                        partidaActual,
                                        0
                                );
                            }
                    );

                    observacion.setRepeats(false);
                    observacion.start();
                }
        );
    }

    private void animarEntradaCartas(
            int partidaActual,
            Runnable alTerminar
    ) {

        Rectangle[] originales
                = new Rectangle[cartas.length];

        for (
                int i = 0;
                i < cartas.length;
                i++
        ) {
            originales[i] = cartas[i].getBounds();
        }

        final int totalPasos = 32;
        final int[] paso = {0};

        Timer animacion = new Timer(10, null);

        animacion.addActionListener(e -> {

            if (partidaActual != numeroPartida) {
                animacion.stop();
                return;
            }

            paso[0]++;

            for (
                    int i = 0;
                    i < cartas.length;
                    i++
            ) {

                double progresoLocal
                        = (paso[0] - i * 0.42)
                        / 18.0;

                progresoLocal = Math.max(
                        0,
                        Math.min(1, progresoLocal)
                );

                double escala
                        = 0.28
                        + 0.72 * progresoLocal
                        + 0.10
                        * Math.sin(
                                Math.PI
                                * progresoLocal
                        );

                cambiarTamanoDesdeCentro(
                        cartas[i],
                        originales[i],
                        escala
                );
            }

            panelCartas.repaint();

            if (paso[0] >= totalPasos) {

                animacion.stop();

                for (
                        int i = 0;
                        i < cartas.length;
                        i++
                ) {
                    cartas[i].setBounds(originales[i]);
                }

                panelCartas.repaint();
                alTerminar.run();
            }
        });

        animacion.start();
    }

    private void animarIntercambios(
            int partidaActual,
            int numeroIntercambio
    ) {

        if (partidaActual != numeroPartida) {
            return;
        }

        if (numeroIntercambio >= totalIntercambios) {

            lblMensaje.setText(
                    "Memoriza el orden final..."
            );

            Timer pausa = new Timer(
                    PAUSA_FINAL,
                    e -> {

                        voltearTodasAlReverso(
                                partidaActual,
                                () -> {

                                    if (
                                            partidaActual
                                            != numeroPartida
                                    ) {
                                        return;
                                    }

                                    bloqueado = false;

                                    lblMensaje.setText(
                                            "¡Comienza! Encuentra todas las parejas"
                                    );

                                    iniciarCronometro(
                                            partidaActual
                                    );
                                }
                        );
                    }
            );

            pausa.setRepeats(false);
            pausa.start();
            return;
        }

        int cartaA = aleatorio.nextInt(
                cartas.length
        );

        int cartaB = elegirCartaLejana(cartaA);

        intercambiarCartasAnimadas(
                partidaActual,
                cartaA,
                cartaB,
                () -> {

                    Timer pausa = new Timer(
                            PAUSA_ENTRE_MOVIMIENTOS,
                            e -> {

                                animarIntercambios(
                                        partidaActual,
                                        numeroIntercambio + 1
                                );
                            }
                    );

                    pausa.setRepeats(false);
                    pausa.start();
                }
        );
    }

    private int elegirCartaLejana(int cartaA) {

        int cartaB = cartaA;
        int intentos = 0;

        while (
                intentos < 40
                && (
                        cartaA == cartaB
                        || cartas[cartaA]
                                .getLocation()
                                .distance(
                                        cartas[cartaB]
                                                .getLocation()
                                ) < 140
                )
        ) {

            cartaB = aleatorio.nextInt(
                    cartas.length
            );

            intentos++;
        }

        if (cartaA == cartaB) {
            cartaB = (cartaA + 1)
                    % cartas.length;
        }

        return cartaB;
    }

    private void intercambiarCartasAnimadas(
            int partidaActual,
            int cartaA,
            int cartaB,
            Runnable alTerminar
    ) {

        Rectangle inicioA
                = cartas[cartaA].getBounds();

        Rectangle inicioB
                = cartas[cartaB].getBounds();

        panelCartas.setComponentZOrder(
                cartas[cartaA],
                0
        );

        panelCartas.setComponentZOrder(
                cartas[cartaB],
                0
        );

        final int[] paso = {0};

        double ax = inicioA.x
                + ANCHO_CARTA / 2.0;

        double ay = inicioA.y
                + ALTO_CARTA / 2.0;

        double bx = inicioB.x
                + ANCHO_CARTA / 2.0;

        double by = inicioB.y
                + ALTO_CARTA / 2.0;

        double diferenciaX = bx - ax;
        double diferenciaY = by - ay;

        double distancia = Math.sqrt(
                diferenciaX * diferenciaX
                + diferenciaY * diferenciaY
        );

        if (distancia == 0) {
            distancia = 1;
        }

        double perpendicularX
                = -diferenciaY / distancia;

        double perpendicularY
                = diferenciaX / distancia;

        Timer animacion = new Timer(
                VELOCIDAD_MOVIMIENTO,
                null
        );

        animacion.addActionListener(e -> {

            if (partidaActual != numeroPartida) {
                animacion.stop();
                return;
            }

            paso[0]++;

            double progresoPaso
                    = paso[0]
                    / (double) PASOS_MOVIMIENTO;

            double suave
                    = 0.5
                    - Math.cos(
                            progresoPaso * Math.PI
                    ) / 2.0;

            double curva
                    = 30
                    * Math.sin(
                            Math.PI * progresoPaso
                    );

            double escala
                    = 1.0
                    + 0.12
                    * Math.sin(
                            Math.PI * progresoPaso
                    );

            int nuevoAncho = (int) (
                    ANCHO_CARTA * escala
            );

            int nuevoAlto = (int) (
                    ALTO_CARTA * escala
            );

            double centroAX
                    = interpolar(ax, bx, suave)
                    + perpendicularX * curva;

            double centroAY
                    = interpolar(ay, by, suave)
                    + perpendicularY * curva;

            double centroBX
                    = interpolar(bx, ax, suave)
                    - perpendicularX * curva;

            double centroBY
                    = interpolar(by, ay, suave)
                    - perpendicularY * curva;

            cartas[cartaA].setBounds(
                    (int) centroAX
                    - nuevoAncho / 2,
                    (int) centroAY
                    - nuevoAlto / 2,
                    nuevoAncho,
                    nuevoAlto
            );

            cartas[cartaB].setBounds(
                    (int) centroBX
                    - nuevoAncho / 2,
                    (int) centroBY
                    - nuevoAlto / 2,
                    nuevoAncho,
                    nuevoAlto
            );

            panelCartas.repaint();

            if (paso[0] >= PASOS_MOVIMIENTO) {

                animacion.stop();

                cartas[cartaA].setBounds(
                        inicioB.x,
                        inicioB.y,
                        ANCHO_CARTA,
                        ALTO_CARTA
                );

                cartas[cartaB].setBounds(
                        inicioA.x,
                        inicioA.y,
                        ANCHO_CARTA,
                        ALTO_CARTA
                );

                panelCartas.repaint();
                alTerminar.run();
            }
        });

        animacion.start();
    }

    private double interpolar(
            double inicio,
            double finalPosicion,
            double progresoAnimacion
    ) {

        return inicio
                + (finalPosicion - inicio)
                * progresoAnimacion;
    }

    private void voltearTodasAlReverso(
            int partidaActual,
            Runnable alTerminar
    ) {

        final int[] terminadas = {0};

        for (
                int i = 0;
                i < cartas.length;
                i++
        ) {

            final int posicion = i;

            Timer retraso = new Timer(
                    i * 18,
                    e -> {

                        animarVolteoCarta(
                                partidaActual,
                                posicion,
                                false,
                                () -> {

                                    terminadas[0]++;

                                    if (
                                            terminadas[0]
                                            == cartas.length
                                    ) {
                                        alTerminar.run();
                                    }
                                }
                        );
                    }
            );

            retraso.setRepeats(false);
            retraso.start();
        }
    }

    private void seleccionarCarta(int posicion) {

        if (
                bloqueado
                || partidaFinalizada
                || posicion == primeraCarta
                || encontradas[posicion]
        ) {
            return;
        }

        int partidaActual = numeroPartida;
        bloqueado = true;

        animarVolteoCarta(
                partidaActual,
                posicion,
                true,
                () -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {
                        return;
                    }

                    if (primeraCarta == -1) {

                        primeraCarta = posicion;
                        bloqueado = false;

                    } else {

                        segundaCarta = posicion;
                        movimientos++;

                        lblMovimientos.setText(
                                "Movimientos: "
                                + movimientos
                        );

                        comprobarPareja(
                                partidaActual
                        );
                    }
                }
        );
    }

    private void animarVolteoCarta(
            int partidaActual,
            int posicion,
            boolean mostrarFrente,
            Runnable alTerminar
    ) {

        Rectangle original
                = cartas[posicion].getBounds();

        final int totalPasos = 12;
        final int[] paso = {0};
        final boolean[] cambio = {false};

        Timer animacion = new Timer(12, null);

        animacion.addActionListener(e -> {

            if (partidaActual != numeroPartida) {
                animacion.stop();
                return;
            }

            paso[0]++;

            double escalaHorizontal;

            if (paso[0] <= totalPasos / 2) {

                escalaHorizontal
                        = 1
                        - paso[0]
                        / (double) (
                                totalPasos / 2
                        );

            } else {

                escalaHorizontal
                        = (paso[0]
                        - totalPasos / 2)
                        / (double) (
                                totalPasos / 2
                        );
            }

            if (
                    paso[0] >= totalPasos / 2
                    && !cambio[0]
            ) {

                cambio[0] = true;

                if (mostrarFrente) {
                    mostrarFrente(posicion);
                } else {
                    mostrarReverso(posicion);
                }
            }

            int nuevoAncho = Math.max(
                    2,
                    (int) (
                            ANCHO_CARTA
                            * escalaHorizontal
                    )
            );

            int centroX = original.x
                    + ANCHO_CARTA / 2;

            cartas[posicion].setBounds(
                    centroX - nuevoAncho / 2,
                    original.y,
                    nuevoAncho,
                    ALTO_CARTA
            );

            panelCartas.repaint();

            if (paso[0] >= totalPasos) {

                animacion.stop();
                cartas[posicion].setBounds(original);
                panelCartas.repaint();
                alTerminar.run();
            }
        });

        animacion.start();
    }

    private void comprobarPareja(
            int partidaActual
    ) {

        if (
                valores[primeraCarta]
                == valores[segundaCarta]
        ) {

            int cartaA = primeraCarta;
            int cartaB = segundaCarta;

            encontradas[cartaA] = true;
            encontradas[cartaB] = true;

            parejasEncontradas++;
            puntosNivel += 100;

            lblPuntosNivel.setText(
                    "Nivel: " + puntosNivel + " pts"
            );

            lblPuntosTotal.setText(
                    "Total: "
                    + (progreso.getPuntosTotales()
                    + puntosNivel)
                    + " pts"
            );

            String nombre = nombresCartas[
                    valores[cartaA] - 1
            ];

            lblMensaje.setText(
                    "¡Encontraste "
                    + nombre
                    + "!  +100 puntos"
            );

            GestorMusica.reproducirEfecto(
                    "/audio/pareja.wav"
            );

            primeraCarta = -1;
            segundaCarta = -1;

            animarParejaEncontrada(
                    partidaActual,
                    cartaA,
                    cartaB,
                    () -> {

                        if (
                                parejasEncontradas
                                == cantidadParejas
                        ) {

                            finalizarNivel(
                                    partidaActual
                            );

                        } else {

                            bloqueado = false;

                            Timer mensaje = new Timer(
                                    780,
                                    e -> {

                                        if (
                                                partidaActual
                                                == numeroPartida
                                        ) {

                                            lblMensaje.setText(
                                                    "Continúa buscando las parejas"
                                            );
                                        }
                                    }
                            );

                            mensaje.setRepeats(false);
                            mensaje.start();
                        }
                    }
            );

        } else {

            int cartaA = primeraCarta;
            int cartaB = segundaCarta;

            GestorMusica.reproducirEfecto(
            "/audio/incorrecto.wav"
            );
            
            lblMensaje.setText(
                    "Estas cartas no coinciden"
            );

            Timer tiempo = new Timer(
                    TIEMPO_INCORRECTAS,
                    e -> {

                        ocultarDosCartas(
                                partidaActual,
                                cartaA,
                                cartaB
                        );
                    }
            );

            tiempo.setRepeats(false);
            tiempo.start();
        }
    }

    private void ocultarDosCartas(
            int partidaActual,
            int cartaA,
            int cartaB
    ) {

        final int[] terminadas = {0};

        Runnable terminar = () -> {

            terminadas[0]++;

            if (terminadas[0] == 2) {

                primeraCarta = -1;
                segundaCarta = -1;
                bloqueado = false;

                lblMensaje.setText(
                        "Intenta encontrar otra pareja"
                );
            }
        };

        animarVolteoCarta(
                partidaActual,
                cartaA,
                false,
                terminar
        );

        animarVolteoCarta(
                partidaActual,
                cartaB,
                false,
                terminar
        );
    }

    private void animarParejaEncontrada(
            int partidaActual,
            int cartaA,
            int cartaB,
            Runnable alTerminar
    ) {

        Rectangle originalA
                = cartas[cartaA].getBounds();

        Rectangle originalB
                = cartas[cartaB].getBounds();

        cartas[cartaA].setColorBorde(COLOR_PAREJA);
        cartas[cartaB].setColorBorde(COLOR_PAREJA);

        final int totalPasos = 14;
        final int[] paso = {0};

        Timer animacion = new Timer(15, null);

        animacion.addActionListener(e -> {

            if (partidaActual != numeroPartida) {
                animacion.stop();
                return;
            }

            paso[0]++;

            double progresoAnimacion
                    = paso[0]
                    / (double) totalPasos;

            double escala
                    = 1.0
                    + 0.10
                    * Math.sin(
                            Math.PI
                            * progresoAnimacion
                    );

            cambiarTamanoDesdeCentro(
                    cartas[cartaA],
                    originalA,
                    escala
            );

            cambiarTamanoDesdeCentro(
                    cartas[cartaB],
                    originalB,
                    escala
            );

            panelCartas.repaint();

            if (paso[0] >= totalPasos) {

                animacion.stop();
                cartas[cartaA].setBounds(originalA);
                cartas[cartaB].setBounds(originalB);
                panelCartas.repaint();
                alTerminar.run();
            }
        });

        animacion.start();
    }

    private void cambiarTamanoDesdeCentro(
            java.awt.Component componente,
            Rectangle original,
            double escala
    ) {

        int nuevoAncho = Math.max(
                2,
                (int) (ANCHO_CARTA * escala)
        );

        int nuevoAlto = Math.max(
                2,
                (int) (ALTO_CARTA * escala)
        );

        int centroX = original.x
                + ANCHO_CARTA / 2;

        int centroY = original.y
                + ALTO_CARTA / 2;

        componente.setBounds(
                centroX - nuevoAncho / 2,
                centroY - nuevoAlto / 2,
                nuevoAncho,
                nuevoAlto
        );
    }

    private void mostrarFrente(int posicion) {

        int valor = valores[posicion];

        ImageIcon icono = imagenesFrente[
                valor - 1
        ];

        if (icono != null) {

            cartas[posicion].setIcon(icono);
            cartas[posicion].setText("");

        } else {

            cartas[posicion].setIcon(null);
            cartas[posicion].setText(
                    String.valueOf(valor)
            );

            cartas[posicion].setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            30
                    )
            );
        }
    }

    private void mostrarReverso(int posicion) {

        cartas[posicion].setColorBorde(COLOR_BORDE);

        ImageIcon reverso = obtenerReverso(posicion);
        
        if (reverso != null) {

            cartas[posicion].setIcon(reverso);
            
            cartas[posicion].setText("");

        } else {

            cartas[posicion].setIcon(null);
            cartas[posicion].setText("?");

            cartas[posicion].setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            30
                    )
            );
        }
    }

    private void iniciarCronometro(
            int partidaActual
    ) {

        detenerCronometro();

        cronometro = new Timer(
                1000,
                e -> {

                    if (
                            partidaActual
                            != numeroPartida
                            || partidaFinalizada
                    ) {

                        cronometro.stop();
                        return;
                    }

                    segundos++;

                    lblTiempo.setText(
                            "Tiempo: "
                            + formatearTiempo(segundos)
                    );
                }
        );

        cronometro.start();
    }

    private void detenerCronometro() {

        if (cronometro != null) {
            cronometro.stop();
            cronometro = null;
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

    private void finalizarNivel(
            int partidaActual
    ) {

        if (partidaFinalizada) {
            return;
        }

        partidaFinalizada = true;
        bloqueado = true;
        detenerCronometro();

        int bonificacion = Math.max(
                0,
                cantidadParejas * 100
                - movimientos * 8
                - segundos * 2
        );

        puntosNivel += bonificacion;

        progreso.agregarResultadoNivel(
                numeroNivel,
                puntosNivel,
                movimientos,
                segundos
        );

        lblPuntosNivel.setText(
                "Nivel: " + puntosNivel + " pts"
        );

        lblPuntosTotal.setText(
                "Total: "
                + progreso.getPuntosTotales()
                + " pts"
        );

        lblMensaje.setText(
                "¡Nivel completado!"
        );

        GestorMusica.reproducirEfecto(
                "/audio/victoria.wav"
        );

        if (numeroNivel == 3) {
            guardarRankingGeneral();
        }

        Timer abrirResumen = new Timer(
                350,
                e -> {

                    if (
                            partidaActual
                            != numeroPartida
                    ) {
                        return;
                    }

                    numeroPartida++;

                    new NivelCompletadoForm(
                            progreso,
                            numeroNivel,
                            cantidadParejas,
                            puntosNivel,
                            bonificacion,
                            movimientos,
                            segundos
                    ).setVisible(true);

                    dispose();
                }
        );

        abrirResumen.setRepeats(false);
        abrirResumen.start();
    }

    private void guardarRankingGeneral() {

        if (progreso.isRankingGuardado()) {
            return;
        }

        String fecha = LocalDateTime
                .now()
                .format(
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy HH:mm"
                        )
                );

        RankingManager.guardar(
                new RegistroRanking(
                        progreso.getJugador(),
                        progreso.getPuntosTotales(),
                        progreso.getMovimientosTotales(),
                        progreso.getSegundosTotales(),
                        fecha
                )
        );

        progreso.setRankingGuardado(true);
    }

    private void abandonarPartida() {

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "Se perderá el progreso de los niveles.\n"
                + "¿Deseas volver al menú del juego?",
                "Abandonar partida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        numeroPartida++;
        detenerCronometro();

        new MenuJuego(
                progreso.getJugador(),
                progreso.getSeccion(),
                progreso.getAccionVolverPrincipal()
        ).setVisible(true);

        dispose();
    }
}
