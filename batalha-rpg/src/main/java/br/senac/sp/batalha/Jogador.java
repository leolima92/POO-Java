

package br.senac.sp.batalha;



import java.util.concurrent.ThreadLocalRandom;

public class Jogador {
    private final String nome;
    private int vida;
    private final int ataque;
    private final int defesa;

    public Jogador(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String atacar(Jogador inimigo) {
        int base = Math.max(0, this.ataque - inimigo.getDefesa());
        boolean critico = ThreadLocalRandom.current().nextInt(100) < 10; // 10%
        int dano = critico ? base * 2 : base;

        inimigo.receberDano(dano);

        if (critico) {
            return String.format("ATAQUE CRITICO! %s atacou %s e causou %d de dano",
                    this.nome, inimigo.getNome(), dano);
        }
        return String.format("%s atacou %s e causou %d de dano",
                this.nome, inimigo.getNome(), dano);
    }

    public void receberDano(int dano) {
        if (dano <= 0) return;
        this.vida = Math.max(0, this.vida - dano);
    }

    // Getters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
}
