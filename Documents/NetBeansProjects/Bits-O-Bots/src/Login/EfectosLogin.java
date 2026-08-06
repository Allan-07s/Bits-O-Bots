/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author zoeca
 */
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public final class EfectosLogin {

    private EfectosLogin() {
    }

    public static void aplicarCampoNombre(JTextField campo) {
        campo.putClientProperty(
                "FlatLaf.style",
                "arc:22;"
                + "borderWidth:1;"
                + "focusWidth:2;"
                + "innerFocusWidth:1;"
                + "margin:8,14,8,14;"
                + "borderColor:#7DBDE8;"
                + "focusedBorderColor:#168DD2;"
                + "background:#FFFFFF;"
                + "foreground:#00375D;"
                + "placeholderForeground:#91A4B2"
        );

        campo.putClientProperty(
                "JTextField.placeholderText",
                "Tu nombre"
        );
    }

    public static void aplicarComboBox(JComboBox<?> comboBox) {
        comboBox.putClientProperty(
                "FlatLaf.style",
                "arc:24;"
                + "borderWidth:1;"
                + "focusWidth:2;"
                + "innerFocusWidth:1;"
                + "borderColor:#9EC9E5;"
                + "focusedBorderColor:#168DD2;"
                + "background:#FFFFFF;"
                + "foreground:#00375D;"
                + "buttonBackground:#FFFFFF;"
                + "buttonArrowColor:#027D92;"
                + "buttonHoverArrowColor:#015689"
        );
    }

    public static void aplicarBotonFlatLaf(JButton boton) {
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        boton.putClientProperty(
                "FlatLaf.style",
                "arc:35;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#018981;"
                + "hoverBackground:#00777D;"
                + "pressedBackground:#005F70;"
                + "foreground:#FFFFFF;"
        );
    }

    public static void aplicarColoresTexto(JTextField campo) {
        campo.setCaretColor(new Color(0, 91, 145));
        campo.setSelectionColor(new Color(117, 201, 239));
        campo.setSelectedTextColor(new Color(0, 55, 93));
    }
}
