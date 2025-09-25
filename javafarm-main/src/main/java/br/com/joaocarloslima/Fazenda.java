package br.com.joaocarloslima;

import java.util.ArrayList;
import java.util.List;

public class Fazenda {
    private static final int LADO = 13;

    private final List<List<Terreno>> terrenos = new ArrayList<>(LADO);
    private final Celeiro celeiro;

    public Fazenda() { this(200); } // capacidade padrão
    public Fazenda(int capacidadeCeleiro) {
        this.celeiro = new Celeiro(capacidadeCeleiro);

        for (int x = 0; x < LADO; x++) {
            List<Terreno> linha = new ArrayList<>(LADO);
            for (int y = 0; y < LADO; y++) linha.add(new Terreno(x, y));
            terrenos.add(linha);
        }

        // estoque inicial (cada chamada soma +2) — ajuste/remova à vontade
        for (int i = 0; i < 5; i++) {
            celeiro.armazenarBatata();
            celeiro.armazenarCenoura();
            celeiro.armazenarMorango();
        }
    }

    public Terreno getTerreno(int x, int y) { checarCoordenada(x,y); return terrenos.get(x).get(y); }
    public Celeiro getCeleiro() { return celeiro; }

    // Plantar: verifica livre + estoque, planta, consome 1 do celeiro
    public void plantarBatata(int x, int y) {
        verificarLivre(x, y);
        if (celeiro.getQtdeBatatas() <= 0) throw new IllegalStateException("Sem batata no celeiro");
        getTerreno(x, y).plantar(new Batata());
        celeiro.consumirBatata();
    }

    public void plantarCenoura(int x, int y) {
        verificarLivre(x, y);
        if (celeiro.getQtdeCenouras() <= 0) throw new IllegalStateException("Sem cenoura no celeiro");
        getTerreno(x, y).plantar(new Cenoura());
        celeiro.consumirCenoura();
    }

    public void plantarMorango(int x, int y) {
        verificarLivre(x, y);
        if (celeiro.getQtdeMorangos() <= 0) throw new IllegalStateException("Sem morango no celeiro");
        getTerreno(x, y).plantar(new Morango());
        celeiro.consumirMorango();
    }

    public void colher(int x, int y) {
        checarCoordenada(x, y);
        getTerreno(x, y).colher(celeiro); // armazena +2 (lança exceção se cheio)
    }

    // (Se quiser ciclo no modelo também)
    public void ciclo() {
        for (int x = 0; x < LADO; x++) {
            List<Terreno> linha = terrenos.get(x);
            for (int y = 0; y < LADO; y++) {
                linha.get(y).ciclo();
            }
        }
    }

    private void verificarLivre(int x, int y) {
        checarCoordenada(x, y);
        if (getTerreno(x, y).estaOcupado()) throw new IllegalStateException("Terreno ocupado");
    }
    private void checarCoordenada(int x, int y) {
        if (x < 0 || x >= LADO || y < 0 || y >= LADO)
            throw new IllegalArgumentException("Coordenada inválida ("+x+","+y+")");
    }
}
