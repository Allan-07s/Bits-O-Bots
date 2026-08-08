/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResultadoQuizz;

/**
 *
 * @author zoeca
 */
import java.awt.*;
import javax.swing.JLabel;

public class LabelTitulo extends JLabel {

    public LabelTitulo() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        g2.setColor(new Color(20, 100, 200, 55));

        g2.drawString(
            getText(),
            getInsets().left + 3,
            getBaseline(getWidth(), getHeight()) + 3
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
