package br.com.joaocarloslima.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {

    private List<Asset> assets = new ArrayList<>();
    private Nave nave;
    private int pontos;
    private int nivel;

    public Jogo() {
    
        this.nave = new Nave(280, 400, 10, Direcao.DIREITA);
        this.assets.add(nave);
        this.pontos = 0;
        this.nivel = 1;
    }

 
    public Tiro atirar() {

        Tiro tiro = nave.atirar(this.nivel); 
        this.assets.add(tiro);
        return tiro;
    }

    public Meteoro criarMeteoro() {
        Random rand = new Random();
        

        int x = rand.nextInt(590); 

        int y = 0; 

        int velocidade = rand.nextInt(5) + 1; 
        
        Meteoro meteoro = new Meteoro(x, y, velocidade, Direcao.BAIXO);
        this.assets.add(meteoro);
        return meteoro;
    }

    public void pontuar() {
        this.pontos++;

        if (this.pontos % 10 == 0) {

            if (this.nivel < 4) { 
                this.nivel++;
            }
        }
    }


    public List<Asset> getAssets() {
        return assets;
    }

    public Nave getNave() {
        return nave;
    }

    public int getPontos() {
        return pontos;
    }

    public int getNivel() {
        return nivel;
    }
}