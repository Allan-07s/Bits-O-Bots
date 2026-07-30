public class ProgresoJuego {

    private final String jugador;
    private final Runnable accionVolverPrincipal;

    private final int[] puntosPorNivel = new int[3];
    private final int[] movimientosPorNivel = new int[3];
    private final int[] segundosPorNivel = new int[3];

    private int puntosTotales;
    private int movimientosTotales;
    private int segundosTotales;

    private boolean rankingGuardado;

    public ProgresoJuego(String jugador) {
        this(jugador, null);
    }

    public ProgresoJuego(
            String jugador,
            Runnable accionVolverPrincipal
    ) {

        String nombreLimpio = jugador == null
                ? "Jugador"
                : jugador.trim();

        this.jugador = nombreLimpio.isEmpty()
                ? "Jugador"
                : nombreLimpio;

        this.accionVolverPrincipal = accionVolverPrincipal;
    }

    public void agregarResultadoNivel(
            int nivel,
            int puntos,
            int movimientos,
            int segundos
    ) {

        if (nivel < 1 || nivel > 3) {
            throw new IllegalArgumentException(
                    "El nivel debe estar entre 1 y 3."
            );
        }

        int posicion = nivel - 1;

        /*
         * Si por alguna razón se repite el registro del mismo nivel,
         * primero se resta el resultado anterior para no duplicarlo.
         */
        puntosTotales -= puntosPorNivel[posicion];
        movimientosTotales -= movimientosPorNivel[posicion];
        segundosTotales -= segundosPorNivel[posicion];

        puntosPorNivel[posicion] = Math.max(0, puntos);
        movimientosPorNivel[posicion] = Math.max(0, movimientos);
        segundosPorNivel[posicion] = Math.max(0, segundos);

        puntosTotales += puntosPorNivel[posicion];
        movimientosTotales += movimientosPorNivel[posicion];
        segundosTotales += segundosPorNivel[posicion];
    }

    public String getJugador() {
        return jugador;
    }

    public int getPuntosNivel(int nivel) {
        return puntosPorNivel[nivel - 1];
    }

    public int getMovimientosNivel(int nivel) {
        return movimientosPorNivel[nivel - 1];
    }

    public int getSegundosNivel(int nivel) {
        return segundosPorNivel[nivel - 1];
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public int getMovimientosTotales() {
        return movimientosTotales;
    }

    public int getSegundosTotales() {
        return segundosTotales;
    }

    public boolean isRankingGuardado() {
        return rankingGuardado;
    }

    public void setRankingGuardado(boolean rankingGuardado) {
        this.rankingGuardado = rankingGuardado;
    }

    public void volverAlMenuPrincipal() {

        if (accionVolverPrincipal != null) {
            accionVolverPrincipal.run();
        }
    }

    public Runnable getAccionVolverPrincipal() {
        return accionVolverPrincipal;
    }
}
