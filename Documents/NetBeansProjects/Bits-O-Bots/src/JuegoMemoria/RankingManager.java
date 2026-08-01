package JuegoMemoria;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class RankingManager {

    private static final Path ARCHIVO = Paths.get(
            System.getProperty("user.home"),
            "GameHubRankingGeneral.txt"
    );

    private RankingManager() {
    }

    public static synchronized void guardar(
            RegistroRanking nuevoRegistro
    ) {

        List<RegistroRanking> registros = leerTodos();

        registros.add(nuevoRegistro);
        ordenar(registros);

        if (registros.size() > 100) {
            registros = new ArrayList<>(
                    registros.subList(0, 100)
            );
        }

        List<String> lineas = registros
                .stream()
                .map(RegistroRanking::convertirEnLinea)
                .collect(Collectors.toList());

        try {

            Files.write(
                    ARCHIVO,
                    lineas,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException ex) {

            System.err.println(
                    "No se pudo guardar el ranking: "
                    + ex.getMessage()
            );
        }
    }

    public static synchronized List<RegistroRanking> obtenerTop(
            int limite
    ) {

        List<RegistroRanking> registros = leerTodos();
        ordenar(registros);

        int cantidad = Math.min(
                limite,
                registros.size()
        );

        return new ArrayList<>(
                registros.subList(0, cantidad)
        );
    }

    public static synchronized void limpiarRanking() {

        try {
            Files.deleteIfExists(ARCHIVO);
        } catch (IOException ex) {
            System.err.println(
                    "No se pudo limpiar el ranking: "
                    + ex.getMessage()
            );
        }
    }

    public static Path getRutaArchivo() {
        return ARCHIVO;
    }

    private static List<RegistroRanking> leerTodos() {

        List<RegistroRanking> registros
                = new ArrayList<>();

        if (!Files.exists(ARCHIVO)) {
            return registros;
        }

        try {

            List<String> lineas = Files.readAllLines(
                    ARCHIVO,
                    StandardCharsets.UTF_8
            );

            for (String linea : lineas) {

                RegistroRanking registro
                        = RegistroRanking.desdeLinea(linea);

                if (registro != null) {
                    registros.add(registro);
                }
            }

        } catch (IOException ex) {

            System.err.println(
                    "No se pudo leer el ranking: "
                    + ex.getMessage()
            );
        }

        return registros;
    }

    private static void ordenar(
            List<RegistroRanking> registros
    ) {

        registros.sort(
                Comparator
                        .comparingInt(
                                RegistroRanking::getPuntos
                        )
                        .reversed()
                        .thenComparingInt(
                                RegistroRanking::getMovimientos
                        )
                        .thenComparingInt(
                                RegistroRanking::getSegundos
                        )
        );
    }
}
