/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quizz;

/**
 *
 * @author alvar
 */

// 1. Pon el enum FUERA de la clase (al inicio o en su propio archivo TipoMultimedia.java)
public class Preguntas {
    private String enunciado;

    private String textoOpA;
    private String rutaOpA;
    private String carreraA;

    private String textoOpB;
    private String rutaOpB;
    private String carreraB;

    // Constructor simplificado (sin tipoOpA ni tipoOpB)
    public Preguntas(String enunciado, String textoOpA, String rutaOpA, String carreraA, 
                     String textoOpB, String rutaOpB, String carreraB) {
        this.enunciado = enunciado;
        this.textoOpA = textoOpA;
        this.rutaOpA = rutaOpA;
        this.carreraA = carreraA;
        this.textoOpB = textoOpB;
        this.rutaOpB = rutaOpB;
        this.carreraB = carreraB;
    }

    // Getters
    public String getEnunciado() { return enunciado; }
    public String getTextoOpA() { return textoOpA; }
    public String getRutaOpA() { return rutaOpA; }
    public String getCarreraA() { return carreraA; }

    public String getTextoOpB() { return textoOpB; }
    public String getRutaOpB() { return rutaOpB; }
    public String getCarreraB() { return carreraB; }
}