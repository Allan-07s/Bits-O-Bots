package JuegoMemoria;

public class Nivel1Form extends JuegoMemoriaBase {

    private static final String[] NOMBRES = {
        "Teclado",
        "Mouse",
        "Monitor",
        "Engranaje",
        "Motor",
        "Sensor"
    };

    private static final String[] IMAGENES = {
        "/img/n1_teclado.PNG",
        "/img/n1_mouse.PNG",
        "/img/n1_monitor.PNG",
        "/img/n1_engranaje.PNG",
        "/img/n1_motor.PNG",
        "/img/n1_sensor.PNG"
    };
    
    private static final String[] CATEGORIAS = {
        "INFORMATICA",
        "INFORMATICA",
        "INFORMATICA",
        "ROBOTICA",
        "ROBOTICA",
        "ROBOTICA"
    };

    public Nivel1Form(ProgresoJuego progreso) {

        super(
                progreso,
                1,
                12,
                3,
                4,
                8,
                NOMBRES,
                IMAGENES,
                CATEGORIAS
        );
    }
}
