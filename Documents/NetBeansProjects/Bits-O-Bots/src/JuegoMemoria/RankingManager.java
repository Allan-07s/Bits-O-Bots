package JuegoMemoria;

import BaseDeDatos.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RankingManager {

    private RankingManager() {
    }

    // =====================================================
    // GUARDAR RESULTADO
    // =====================================================

    public static void guardar(
            RegistroRanking registro
    ) {

        String sql
                = "INSERT INTO ranking "
                + "(jugador, puntos, movimientos, segundos, grado, seccion) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con
                = conexion.crearConexion()
        ) {

            if (con == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo conectar con la base de datos.",
                        "Error de conexión",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            con.setAutoCommit(true);

            try (
                    PreparedStatement ps
                    = con.prepareStatement(sql)
            ) {

                ps.setString(
                        1,
                        registro.getJugador()
                );

                ps.setInt(
                        2,
                        registro.getPuntos()
                );

                ps.setInt(
                        3,
                        registro.getMovimientos()
                );

                ps.setInt(
                        4,
                        registro.getSegundos()
                );

                ps.setString(
                        5,
                        registro.getGrado()
                );

                ps.setString(
                        6,
                        registro.getSeccion()
                );

                int filasAfectadas
                        = ps.executeUpdate();

                System.out.println(
                        "Filas insertadas en ranking: "
                        + filasAfectadas
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar ranking:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // =====================================================
    // OBTENER TOP
    // =====================================================

    public static List<RegistroRanking> obtenerTop(
            int limite
    ) {

        List<RegistroRanking> registros
                = new ArrayList<>();

        /*
         * Por seguridad:
         *
         * mínimo 1
         * máximo 100
         */
        int limiteSeguro
                = Math.max(
                        1,
                        Math.min(
                                limite,
                                100
                        )
                );

        /*
         * CONSULTA DIRECTA A MYSQL.
         *
         * Eliminamos el parámetro del LIMIT
         * para evitar cualquier problema
         * con distintas versiones del driver.
         */
        String sql
                = "SELECT "
                + "jugador, "
                + "puntos, "
                + "movimientos, "
                + "segundos, "
                + "grado, "
                + "seccion "
                + "FROM ranking "
                + "ORDER BY "
                + "puntos DESC, "
                + "movimientos ASC, "
                + "segundos ASC "
                + "LIMIT "
                + limiteSeguro;

        try (
                Connection con
                = conexion.crearConexion()
        ) {

            if (con == null) {

                System.out.println(
                        "RankingManager: conexión NULL."
                );

                return registros;
            }

            System.out.println(
                    "RankingManager: conexión correcta."
            );

            try (
                    PreparedStatement ps
                    = con.prepareStatement(sql);

                    ResultSet rs
                    = ps.executeQuery()
            ) {

                while (rs.next()) {

                    String jugador
                            = rs.getString(
                                    "jugador"
                            );

                    int puntos
                            = rs.getInt(
                                    "puntos"
                            );

                    int movimientos
                            = rs.getInt(
                                    "movimientos"
                            );

                    int segundos
                            = rs.getInt(
                                    "segundos"
                            );

                    String grado
                            = rs.getString(
                                    "grado"
                            );

                    String seccion
                            = rs.getString(
                                    "seccion"
                            );

                    /*
                     * Evitar null visual.
                     */
                    if (grado == null) {
                        grado = "";
                    }

                    if (seccion == null) {
                        seccion = "";
                    }

                    RegistroRanking registro
                            = new RegistroRanking(
                                    jugador,
                                    puntos,
                                    movimientos,
                                    segundos,
                                    grado,
                                    seccion
                            );

                    registros.add(
                            registro
                    );

                    /*
                     * Esto nos permite comprobar
                     * desde Output qué está leyendo.
                     */
                    System.out.println(
                            "LEÍDO -> "
                            + jugador
                            + " | "
                            + puntos
                            + " pts | "
                            + movimientos
                            + " mov | "
                            + segundos
                            + " seg | "
                            + grado
                            + "-"
                            + seccion
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR AL CONSULTAR ranking:"
            );

            e.printStackTrace();
        }

        System.out.println(
                "TOTAL LEÍDO DESDE MYSQL: "
                + registros.size()
        );

        return registros;
    }

    // =====================================================
    // LIMPIAR RANKING
    // =====================================================

    public static void limpiarRanking() {

        String sql
                = "DELETE FROM ranking";

        try (
                Connection con
                = conexion.crearConexion()
        ) {

            if (con == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo conectar con la base de datos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            con.setAutoCommit(true);

            try (
                    PreparedStatement ps
                    = con.prepareStatement(sql)
            ) {

                int eliminados
                        = ps.executeUpdate();

                System.out.println(
                        "Registros eliminados: "
                        + eliminados
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al limpiar ranking:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}