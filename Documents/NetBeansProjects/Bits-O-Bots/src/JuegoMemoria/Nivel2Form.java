public class Nivel2Form extends JuegoMemoriaBase {

    private static final String[] NOMBRES = {
        "Memoria RAM",
        "Disco duro",
        "Procesador",
        "Fuente de poder",
        "Servomotor",
        "Rueda robótica",
        "Protoboard",
        "Arduino"
    };

    private static final String[] IMAGENES = {
        "/img/n2_memoria_ram.PNG",
        "/img/n2_disco_duro.PNG",
        "/img/n2_procesador.PNG",
        "/img/n2_fuente_poder.PNG",
        "/img/n2_servomotor.PNG",
        "/img/n2_rueda_robotica.PNG",
        "/img/n2_protoboard.PNG",
        "/img/n2_arduino.PNG"
    };

    public Nivel2Form(ProgresoJuego progreso) {

        super(
                progreso,
                2,
                16,
                4,
                4,
                10,
                NOMBRES,
                IMAGENES
        );
    }
}
