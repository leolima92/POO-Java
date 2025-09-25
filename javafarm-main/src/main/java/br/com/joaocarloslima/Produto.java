package br.com.joaocarloslima;

public abstract class Produto {
    protected int tamanho = 1;       // [1..4]
    protected int tempoDeVida = 0;
    private final int passoCrescimento; // de quantos em quantos ciclos cresce

    protected Produto(int passoCrescimento) {
        this.passoCrescimento = passoCrescimento;
    }

    public void crescer() {
        tempoDeVida++;
        if (tempoDeVida % passoCrescimento == 0 && tamanho < 4) {
            tamanho++;
        }
    }

    public boolean podeColher() { return tamanho >= 4; }
    public int getTamanho() { return tamanho; }

    /** Caminho da imagem no classpath conforme tipo/tamanho (para getResourceAsStream). */
    public abstract String getImagem();

    /** "batata" | "cenoura" | "morango" */
    public abstract String getTipo();
}
