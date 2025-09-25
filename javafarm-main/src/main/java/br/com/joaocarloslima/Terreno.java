package br.com.joaocarloslima;

public class Terreno {
    private Produto produto;     // pode ser Batata, Cenoura ou Morango
    private final int x, y;

    public Terreno(int x, int y) { this.x = x; this.y = y; }

    public boolean estaOcupado() { return produto != null; }

    // Plantio genérico (polimorfismo)
    public void plantar(Produto p) {
        if (estaOcupado()) throw new IllegalStateException("Terreno ocupado");
        this.produto = p;
    }
    // Sobrecargas por tipo (se você preferir chamar por tipo)
    public void plantar(Batata b)  { plantar((Produto) b); }
    public void plantar(Cenoura c) { plantar((Produto) c); }
    public void plantar(Morango m) { plantar((Produto) m); }

    public void colher(Celeiro celeiro) {
        if (produto == null) throw new IllegalStateException("Nada para colher");
        if (!produto.podeColher()) throw new IllegalStateException("Ainda não pode colher");

        switch (produto.getTipo()) {
            case "batata":  celeiro.armazenarBatata();  break;
            case "cenoura": celeiro.armazenarCenoura(); break;
            case "morango": celeiro.armazenarMorango(); break;
            default: throw new IllegalStateException("Tipo desconhecido");
        }
        produto = null; // terreno fica livre
    }

    public void ciclo() { if (produto != null) produto.crescer(); }

    // Getters de conveniência para manter seu Controller atual
    public Batata getBatata()   { return (produto instanceof Batata)  ? (Batata)  produto : null; }
    public Cenoura getCenoura() { return (produto instanceof Cenoura) ? (Cenoura) produto : null; }
    public Morango getMorango() { return (produto instanceof Morango) ? (Morango) produto : null; }

    // Opcional: versão polimórfica
    public Produto getProduto() { return produto; }

    public int getX() { return x; }
    public int getY() { return y; }
}
