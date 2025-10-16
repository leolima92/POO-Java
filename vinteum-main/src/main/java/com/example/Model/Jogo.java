package com.example.Model;


public class Jogo {
    public Jogador jogador;
    public Jogador computador; 
    public Baralho baralho; 
    
    public Jogo(){
        baralho = new Baralho();
        jogador = new Jogador();
        computador = new Jogador();
        iniciar();
    }

    private void iniciar(){
        baralho.embaralhar();
        jogador.limpar();
        computador.limpar();

        jogador.receberCarta(baralho.Virar());
        jogador.receberCarta(baralho.Virar());
        computador.receberCarta(baralho.Virar());
        computador.receberCarta(baralho.Virar());
    }

    public boolean isFimDeJogo(){
        return jogador.getPontos() >= 21 || computador.getPontos() >= 21 || (jogador.isParou() && computador.isParou());
    }

    public void VezComputador(){
        while(computador.getPontos() < 17){
            computador.receberCarta(baralho.Virar());
        }
    }

    public String getResultado() {
        if (jogador.getPontos() > 21) {
            return "Você estourou! A mesa venceu.";
        }
        if (computador.getPontos() > 21) {
            return "A mesa estourou! Você venceu.";
        }
        if (jogador.getPontos() > computador.getPontos()) {
            return "Você venceu!";
        }
        if (computador.getPontos() > jogador.getPontos()) {
            return "A mesa venceu!";
        }
        return "Empate!";
    }
}
