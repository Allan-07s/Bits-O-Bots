public class RegistroRanking {

    private final String jugador;
    private final int puntos;
    private final int movimientos;
    private final int segundos;
    private final String fecha;

    public RegistroRanking(
            String jugador,
            int puntos,
            int movimientos,
            int segundos,
            String fecha
    ) {

        this.jugador = jugador;
        this.puntos = puntos;
        this.movimientos = movimientos;
        this.segundos = segundos;
        this.fecha = fecha;
    }

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

    public String getFecha() {
        return fecha;
    }

    public String convertirEnLinea() {

        String nombreSeguro = jugador
                .replace("|", " ")
                .replace("\n", " ")
                .replace("\r", " ");

        return nombreSeguro
                + "|" + puntos
                + "|" + movimientos
                + "|" + segundos
                + "|" + fecha;
    }

    public static RegistroRanking desdeLinea(
            String linea
    ) {

        if (
                linea == null
                || linea.trim().isEmpty()
        ) {
            return null;
        }

        String[] partes = linea.split("\\|", -1);

        if (partes.length != 5) {
            return null;
        }

        try {

            return new RegistroRanking(
                    partes[0],
                    Integer.parseInt(partes[1]),
                    Integer.parseInt(partes[2]),
                    Integer.parseInt(partes[3]),
                    partes[4]
            );

        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
