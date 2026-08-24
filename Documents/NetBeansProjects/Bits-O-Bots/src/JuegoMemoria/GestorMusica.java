package JuegoMemoria;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

public final class GestorMusica {

    /*
     * =====================================================
     * MÚSICA DE FONDO
     * =====================================================
     */
    private static Clip musicaFondo;

    /*
     * Estado global de sonido.
     */
    private static boolean silenciado = false;

    /*
     * Última canción solicitada.
     *
     * Esto permite que si cambiamos de pantalla
     * mientras está silenciado, al volver a activar
     * suene la canción correcta.
     */
    private static String rutaActual = "";

    /*
     * Posición donde se pausó la música.
     *
     * Se guarda en microsegundos.
     */
    private static long posicionPausa = 0L;

    /*
     * Evita crear instancias.
     */
    private GestorMusica() {
    }

    // =====================================================
    // REPRODUCIR MÚSICA DE FONDO
    // =====================================================

    public static synchronized void reproducirFondo(
            String ruta
    ) {

        /*
         * Ruta inválida.
         */
        if (
                ruta == null
                || ruta.trim().isEmpty()
        ) {

            return;
        }

        /*
         * Si nos están pidiendo exactamente
         * la misma canción que ya está cargada,
         * no hace falta destruirla y crearla otra vez.
         */
        if (
                musicaFondo != null
                && musicaFondo.isOpen()
                && ruta.equals(rutaActual)
        ) {

            /*
             * Si está silenciado,
             * simplemente dejamos el Clip pausado.
             */
            if (
                    silenciado
            ) {

                return;
            }

            /*
             * Si por alguna razón está abierto
             * pero detenido, lo reanudamos.
             */
            if (
                    !musicaFondo.isRunning()
            ) {

                musicaFondo.loop(
                        Clip.LOOP_CONTINUOUSLY
                );
            }

            return;
        }

        /*
         * Guardar nueva canción.
         */
        rutaActual = ruta;

        /*
         * Si había otra canción,
         * la cerramos completamente.
         */
        cerrarClipFondo();

        posicionPausa = 0L;

        /*
         * Si estamos silenciados NO abrimos
         * todavía el archivo.
         *
         * La ruta queda guardada y se abrirá
         * cuando el usuario reactive el sonido.
         */
        if (
                silenciado
        ) {

            return;
        }

        /*
         * Abrir nueva canción.
         */
        abrirYReproducirFondo(
                rutaActual,
                0L
        );
    }

    // =====================================================
    // ABRIR Y REPRODUCIR FONDO
    // =====================================================

    private static void abrirYReproducirFondo(
            String ruta,
            long posicion
    ) {

        try {

            URL recurso
                    = GestorMusica.class
                            .getResource(
                                    ruta
                            );

            if (
                    recurso == null
            ) {

                System.out.println(
                        "No se encontró el audio: "
                        + ruta
                );

                return;
            }

            /*
             * Abrir archivo.
             */
            try (
                    AudioInputStream audio
                    = AudioSystem
                            .getAudioInputStream(
                                    recurso
                            )
            ) {

                musicaFondo
                        = AudioSystem.getClip();

                musicaFondo.open(
                        audio
                );
            }

            /*
             * Volumen de música de fondo.
             */
            ajustarVolumen(
                    musicaFondo,
                    -9.0f
            );

            /*
             * Si tenemos una posición guardada,
             * regresamos a ella.
             */
            if (
                    posicion > 0
            ) {

                long duracion
                        = musicaFondo
                                .getMicrosecondLength();

                /*
                 * Evitar posición fuera del audio.
                 */
                if (
                        posicion < duracion
                ) {

                    musicaFondo
                            .setMicrosecondPosition(
                                    posicion
                            );
                }
            }

            /*
             * IMPORTANTE:
             *
             * loop() ya inicia la reproducción.
             *
             * No necesitamos llamar start()
             * después porque puede generar
             * comportamientos inconsistentes
             * dependiendo del Mixer de Java.
             */
            musicaFondo.loop(
                    Clip.LOOP_CONTINUOUSLY
            );

        } catch (Exception ex) {

            System.out.println(
                    "No se pudo reproducir la música: "
                    + ex.getMessage()
            );

            ex.printStackTrace();

            cerrarClipFondo();
        }
    }

    // =====================================================
    // REPRODUCIR EFECTO
    // =====================================================

    public static synchronized void reproducirEfecto(
            String ruta
    ) {

        /*
         * Si está silenciado,
         * tampoco reproducimos efectos.
         */
        if (
                silenciado
        ) {

            return;
        }

        if (
                ruta == null
                || ruta.trim().isEmpty()
        ) {

            return;
        }

        try {

            URL recurso
                    = GestorMusica.class
                            .getResource(
                                    ruta
                            );

            if (
                    recurso == null
            ) {

                System.out.println(
                        "No se encontró el efecto: "
                        + ruta
                );

                return;
            }

            final Clip efecto
                    = AudioSystem.getClip();

            try (
                    AudioInputStream audio
                    = AudioSystem
                            .getAudioInputStream(
                                    recurso
                            )
            ) {

                efecto.open(
                        audio
                );
            }

            /*
             * Volumen de efectos.
             */
            ajustarVolumen(
                    efecto,
                    -5.0f
            );

            /*
             * Cuando termina el efecto,
             * liberar recursos.
             */
            efecto.addLineListener(
                    evento -> {

                        if (
                                evento.getType()
                                == LineEvent.Type.STOP
                        ) {

                            /*
                             * Solo cerrarlo si realmente
                             * llegó al final.
                             *
                             * Evita cierres prematuros.
                             */
                            if (
                                    efecto.getMicrosecondPosition()
                                    >= efecto.getMicrosecondLength()
                            ) {

                                efecto.close();
                            }
                        }
                    }
            );

            efecto.start();

        } catch (Exception ex) {

            System.out.println(
                    "No se pudo reproducir el efecto: "
                    + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // DETENER COMPLETAMENTE LA MÚSICA
    // =====================================================

    public static synchronized void detenerFondo() {

        /*
         * Esta función SÍ destruye el Clip.
         *
         * Se utiliza cuando cambiamos de pantalla,
         * de canción o terminamos una sección.
         */
        cerrarClipFondo();

        posicionPausa = 0L;
    }

    // =====================================================
    // CERRAR CLIP INTERNO
    // =====================================================

    private static void cerrarClipFondo() {

        if (
                musicaFondo != null
        ) {

            try {

                if (
                        musicaFondo.isRunning()
                ) {

                    musicaFondo.stop();
                }

                if (
                        musicaFondo.isOpen()
                ) {

                    musicaFondo.close();
                }

            } catch (Exception ex) {

                System.out.println(
                        "Error cerrando música: "
                        + ex.getMessage()
                );

            } finally {

                musicaFondo = null;
            }
        }
    }

    // =====================================================
    // ALTERNAR SILENCIO
    // =====================================================

    public static synchronized boolean alternarSilencio() {

        /*
         * =================================================
         * ACTIVAR SILENCIO
         * =================================================
         */
        if (
                !silenciado
        ) {

            silenciado = true;

            /*
             * NO cerramos el Clip.
             *
             * Solo guardamos posición
             * y lo pausamos.
             */
            if (
                    musicaFondo != null
                    && musicaFondo.isOpen()
            ) {

                posicionPausa
                        = musicaFondo
                                .getMicrosecondPosition();

                if (
                        musicaFondo.isRunning()
                ) {

                    musicaFondo.stop();
                }
            }

            return true;
        }

        /*
         * =================================================
         * QUITAR SILENCIO
         * =================================================
         */

        silenciado = false;

        /*
         * Caso 1:
         *
         * El Clip sigue abierto.
         * Simplemente continuamos desde donde quedó.
         */
        if (
                musicaFondo != null
                && musicaFondo.isOpen()
        ) {

            try {

                long duracion
                        = musicaFondo
                                .getMicrosecondLength();

                if (
                        posicionPausa >= 0
                        && posicionPausa < duracion
                ) {

                    musicaFondo
                            .setMicrosecondPosition(
                                    posicionPausa
                            );
                }

                musicaFondo.loop(
                        Clip.LOOP_CONTINUOUSLY
                );

                return false;

            } catch (Exception ex) {

                System.out.println(
                        "No se pudo reanudar la música: "
                        + ex.getMessage()
                );

                /*
                 * Si falló el Clip viejo,
                 * lo destruimos y creamos uno nuevo.
                 */
                cerrarClipFondo();
            }
        }

        /*
         * Caso 2:
         *
         * La pantalla cambió mientras estaba
         * silenciado y por eso no existe Clip.
         *
         * Abrimos la ruta actual.
         */
        if (
                rutaActual != null
                && !rutaActual.isEmpty()
        ) {

            abrirYReproducirFondo(
                    rutaActual,
                    0L
            );
        }

        posicionPausa = 0L;

        return false;
    }

    // =====================================================
    // CONSULTAR ESTADO
    // =====================================================

    public static synchronized boolean estaSilenciado() {

        return silenciado;
    }

    // =====================================================
    // CONSULTAR SI HAY MÚSICA
    // =====================================================

    public static synchronized boolean estaReproduciendo() {

        return musicaFondo != null
                && musicaFondo.isOpen()
                && musicaFondo.isRunning()
                && !silenciado;
    }

    // =====================================================
    // AJUSTAR VOLUMEN
    // =====================================================

    private static void ajustarVolumen(
            Clip clip,
            float decibeles
    ) {

        if (
                clip == null
        ) {

            return;
        }

        if (
                clip.isControlSupported(
                        FloatControl.Type.MASTER_GAIN
                )
        ) {

            FloatControl volumen
                    = (FloatControl) clip
                            .getControl(
                                    FloatControl.Type.MASTER_GAIN
                            );

            /*
             * Asegurarnos de no salir
             * del rango soportado.
             */
            float valor
                    = Math.max(
                            volumen.getMinimum(),
                            Math.min(
                                    decibeles,
                                    volumen.getMaximum()
                            )
                    );

            volumen.setValue(
                    valor
            );
        }
    }
}