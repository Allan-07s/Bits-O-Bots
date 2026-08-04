package JuegoMemoria;

public class Nivel2Form extends JuegoMemoriaBase {

    private static final String[] NOMBRES = {
        "Memoria RAM",
        "Disco duro",
        "Procesador",
        "Fuente de poder",
        "Servomotor",
        "Modulo Bluetooth",
        "Protoboard",
        "Arduino"
    };

    private static final String[] IMAGENES = {
        "/img/n2_memoria_ram.PNG",
        "/img/n2_disco_duro.PNG",
        "/img/n2_procesador.PNG",
        "/img/n2_fuente_poder.PNG",
        "/img/n2_servomotor.PNG",
        "/img/n2_modulo_b.PNG",
        "/img/n2_protoboard.PNG",
        "/img/n2_arduino.PNG"
    };

    private static final String[] CATEGORIAS = {
        "INFORMATICA",
        "INFORMATICA",
        "INFORMATICA",
        "INFORMATICA",
        "ROBOTICA",
        "ROBOTICA",
        "ROBOTICA",
        "ROBOTICA"
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
                IMAGENES,
                CATEGORIAS
        );
    }
}
