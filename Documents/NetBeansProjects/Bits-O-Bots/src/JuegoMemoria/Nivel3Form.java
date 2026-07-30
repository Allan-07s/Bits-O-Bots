public class Nivel3Form extends JuegoMemoriaBase {

    private static final String[] NOMBRES = {
        "Tarjeta madre",
        "Tarjeta gráfica",
        "Ventilador",
        "Memoria USB",
        "Router",
        "Brazo robótico",
        "Microcontrolador",
        "Sensor ultrasónico",
        "Pinza robótica",
        "Dron"
    };

    private static final String[] IMAGENES = {
        "/img/n3_tarjeta_madre.PNG",
        "/img/n3_tarjeta_grafica.PNG",
        "/img/n3_ventilador.PNG",
        "/img/n3_memoria_usb.PNG",
        "/img/n3_router.PNG",
        "/img/n3_brazo_robotico.PNG",
        "/img/n3_microcontrolador.PNG",
        "/img/n3_sensor_ultrasonico.PNG",
        "/img/n3_pinza_robotica.PNG",
        "/img/n3_dron.PNG"
    };

    public Nivel3Form(ProgresoJuego progreso) {

        super(
                progreso,
                3,
                20,
                4,
                5,
                12,
                NOMBRES,
                IMAGENES
        );
    }
}
