package com.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    
    private List<Carta> cartas =  new ArrayList<>();
    private int pontos;
    private boolean parou;

    public List<Carta> getCartas() {
        return cartas;
    }

    public int getPontos() {
        return pontos;
    }
    public boolean isParou() {
        return parou;
    }

       private int calcularPontos() {
        int total = 0;
        int ases = 0;
        for (Carta carta : cartas) {
            total += carta.getValor();
            if (carta.getNumero().equals("A")) {
                ases++;
            }
        }

        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }
        return total;
    }

    public void receberCarta(Carta carta){
        cartas.add(carta);
        this.pontos = calcularPontos();
    }

    public void parar(){
        this.parou = true;
    }

    public void limpar(){
        cartas.clear();
        pontos = 0;
        parou = false;
    }         
}
