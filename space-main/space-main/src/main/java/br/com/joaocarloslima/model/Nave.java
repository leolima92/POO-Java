package br.com.joaocarloslima;

import javafx.scene.image.ImageView;

public class Nave extends Asset {

    
public Nave(int x, int y, int velocidade, Direcao direcao) {
    super(x, y, velocidade, direcao);
    
 
    String path = "images/ships/playerShip1_red.png"; 
    ImageView imagem = new ImageView(App.class.getResource(path).toString());
    setImagem(imagem);
}

public Tiro atirar(int poder) {

    int tiroX = getX() + 20; 

    int tiroY = getY() - 10; 
    
    return new Tiro(tiroX, tiroY, 10, Direcao.CIMA, poder);
}
  
    @Override
    public void mover() {

        super.mover(); 
        
        if (getX() < 0) {
            setX(0);
        }
        
        if (getX() > 590) { 
            setX(590);
        }
    }
}