import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

public final class GestorMusica {

    private static Clip musicaFondo;
    private static boolean silenciado;
    private static String rutaActual = "";

    private GestorMusica() {
    }

    public static synchronized void reproducirFondo(
            String ruta
    ) {

        rutaActual = ruta;
        detenerFondo();

        if (silenciado) {
            return;
        }

        try {

            URL recurso = GestorMusica.class.getResource(ruta);

            if (recurso == null) {
                System.out.println(
                        "No se encontró el audio: " + ruta
                );
                return;
            }

            AudioInputStream audio
                    = AudioSystem.getAudioInputStream(recurso);

            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audio);

            ajustarVolumen(musicaFondo, -9.0f);

            musicaFondo.loop(
                    Clip.LOOP_CONTINUOUSLY
            );

            musicaFondo.start();

        } catch (Exception ex) {

            System.out.println(
                    "No se pudo reproducir la música: "
                    + ex.getMessage()
            );
        }
    }

    public static synchronized void reproducirEfecto(
            String ruta
    ) {

        if (silenciado) {
            return;
        }

        try {

            URL recurso = GestorMusica.class.getResource(ruta);

            if (recurso == null) {
                System.out.println(
                        "No se encontró el efecto: " + ruta
                );
                return;
            }

            AudioInputStream audio
                    = AudioSystem.getAudioInputStream(recurso);

            Clip efecto = AudioSystem.getClip();
            efecto.open(audio);

            ajustarVolumen(efecto, -5.0f);

            efecto.addLineListener(evento -> {

                if (
                        evento.getType()
                        == LineEvent.Type.STOP
                ) {
                    efecto.close();
                }
            });

            efecto.start();

        } catch (Exception ex) {

            System.out.println(
                    "No se pudo reproducir el efecto: "
                    + ex.getMessage()
            );
        }
    }

    public static synchronized void detenerFondo() {

        if (musicaFondo != null) {
            musicaFondo.stop();
            musicaFondo.close();
            musicaFondo = null;
        }
    }

    public static synchronized boolean alternarSilencio() {

        silenciado = !silenciado;

        if (silenciado) {

            detenerFondo();

        } else if (!rutaActual.isEmpty()) {

            reproducirFondo(rutaActual);
        }

        return silenciado;
    }

    public static synchronized boolean estaSilenciado() {
        return silenciado;
    }

    private static void ajustarVolumen(
            Clip clip,
            float decibeles
    ) {

        if (
                clip.isControlSupported(
                        FloatControl.Type.MASTER_GAIN
                )
        ) {

            FloatControl volumen
                    = (FloatControl) clip.getControl(
                            FloatControl.Type.MASTER_GAIN
                    );

            float valor = Math.max(
                    volumen.getMinimum(),
                    Math.min(
                            decibeles,
                            volumen.getMaximum()
                    )
            );

            volumen.setValue(valor);
        }
    }
}
