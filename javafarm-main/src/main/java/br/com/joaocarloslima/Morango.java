package br.com.joaocarloslima;

public class Morango extends Produto {
    public Morango() { super(4); } // cresce a cada 4 ciclos

    @Override
    public String getImagem() {
        return "/br/com/joaocarloslima/images/morango_" + tamanho + ".png";
    }

    @Override
    public String getTipo() { return "morango"; }
}
