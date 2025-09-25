package br.com.joaocarloslima;

public class Batata extends Produto {
    public Batata() { super(3); } // cresce a cada 3 ciclos

    @Override
    public String getImagem() {
        return "/br/com/joaocarloslima/images/batata_" + tamanho + ".png";
    }

    @Override
    public String getTipo() { return "batata"; }
}
