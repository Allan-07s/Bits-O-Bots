/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;

/**
 *
 * @author alvar
 */
public class Preguntas {
    private String enunciado;
    private String textoOpA;
    private String rutaImgA;
    private String carreraA; 
    
    private String textoOpB;
    private String rutaImgB;
    private String carreraB;

    public Preguntas(String enunciado, String textoOpA, String rutaImgA, String carreraA, 
                    String textoOpB, String rutaImgB, String carreraB) {
        this.enunciado = enunciado;
        this.textoOpA = textoOpA;
        this.rutaImgA = rutaImgA;
        this.carreraA = carreraA;
        this.textoOpB = textoOpB;
        this.rutaImgB = rutaImgB;
        this.carreraB = carreraB;
    }

    public String getEnunciado() { return enunciado; }
    public String getTextoOpA() { return textoOpA; }
    public String getRutaImgA() { return rutaImgA; }
    public String getCarreraA() { return carreraA; }
    public String getTextoOpB() { return textoOpB; }
    public String getRutaImgB() { return rutaImgB; }
    public String getCarreraB() { return carreraB; }
}

