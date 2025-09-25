package br.com.joaocarloslima;

public class Cenoura extends Produto {
    public Cenoura() { super(2); } // cresce a cada 2 ciclos

    @Override
    public String getImagem() {
        return "/br/com/joaocarloslima/images/cenoura_" + tamanho + ".png";
    }

    @Override
    public String getTipo() { return "cenoura"; }
}
