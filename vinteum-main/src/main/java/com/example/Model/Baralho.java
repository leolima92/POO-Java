package com.example.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> cartas = new ArrayList<>();

    public Baralho() {
        String[] naipes = {"copas", "ouros", "espadas", "paus"};
        String[] numeros = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

         for (String naipe : naipes) {
            for (String numero : numeros) {
                cartas.add(new Carta(naipe, numero));
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta Virar(){
        if(cartas.isEmpty()){
            return null;
        }
        return cartas.remove(0);
    }
}
