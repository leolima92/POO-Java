package com.example.Model;

public class Carta {
    private String naipe;
    private String numero;

    public Carta(String naipe, String numero) {
        this.naipe = naipe;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public int getValor() {
        switch (numero) {
            case "A":
                return 11;
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(numero);
        }
    }
     public String imagePath() {
        return "/cartas/" + naipe + "_" + numero + ".png";
    }

    @Override
    public String toString() {
        return numero + " de " + naipe;
    }

}
