package JuegoMemoria;

public class RegistroRanking {

    private final String jugador;
    private final int puntos;
    private final int movimientos;
    private final int segundos;
    private final String grado;
    private final String seccion;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RegistroRanking(
            String jugador,
            int puntos,
            int movimientos,
            int segundos,
            String grado,
            String seccion
    ) {

        this.jugador = jugador;
        this.puntos = puntos;
        this.movimientos = movimientos;
        this.segundos = segundos;
        this.grado = grado;
        this.seccion = seccion;
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public String getJugador() {

        return jugador;
    }

    public int getPuntos() {

        return puntos;
    }

    public int getMovimientos() {

        return movimientos;
    }

    public int getSegundos() {

        return segundos;
    }

    public String getGrado() {

        return grado;
    }

    public String getSeccion() {

        return seccion;
    }
}