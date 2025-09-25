package br.com.joaocarloslima;

public class Celeiro {
    private final int capacidade; 
    private int batatas, cenouras, morangos;

    public Celeiro(int capacidade) {
        if (capacidade <= 0) throw new IllegalArgumentException("Capacidade inválida");
        this.capacidade = capacidade;
    }

    // ==== ARMAZENAR: sempre +2 ====
    public void armazenarBatata()  { armazenar("batata", 2); }
    public void armazenarCenoura() { armazenar("cenoura", 2); }
    public void armazenarMorango() { armazenar("morango", 2); }

    // ==== CONSUMIR: sempre -1 ====
    public void consumirBatata()  { consumir("batata", 1); }
    public void consumirCenoura() { consumir("cenoura", 1); }
    public void consumirMorango() { consumir("morango", 1); }

    private void armazenar(String tipo, int unidades) {
        if (getEspacoDisponivel() < unidades) throw new IllegalStateException("Celeiro cheio");
        switch (tipo) {
            case "batata":  batatas  += unidades; break;
            case "cenoura": cenouras += unidades; break;
            case "morango": morangos += unidades; break;
            default: throw new IllegalArgumentException("Tipo inválido");
        }
    }

    private void consumir(String tipo, int unidades) {
        switch (tipo) {
            case "batata":
                if (batatas < unidades) throw new IllegalStateException("Sem batata");
                batatas -= unidades; break;
            case "cenoura":
                if (cenouras < unidades) throw new IllegalStateException("Sem cenoura");
                cenouras -= unidades; break;
            case "morango":
                if (morangos < unidades) throw new IllegalStateException("Sem morango");
                morangos -= unidades; break;
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }

    public int getEspacoDisponivel() { return capacidade - (batatas + cenouras + morangos); }
    /** Ocupação como decimal 0..1 (ex.: 0.75 = 75%). */
    public double getOcupacao() { return (double)(batatas + cenouras + morangos) / capacidade; }
    public boolean celeiroCheio() { return getEspacoDisponivel() == 0; }

    public int getQtdeBatatas()  { return batatas; }
    public int getQtdeCenouras() { return cenouras; }
    public int getQtdeMorangos() { return morangos; }
    public int getCapacidade()   { return capacidade; }
}
