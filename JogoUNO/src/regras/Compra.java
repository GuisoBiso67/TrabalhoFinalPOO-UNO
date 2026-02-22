package regras;

import baralho.*;

//
public class Compra extends Monte {

    public Compra() {
        super();
    }

    // método recebe o baralho que vai ser usado e monta o monte de compras
    public void recebeCartas(Baralho baralho){
        for(Carta c : baralho.getCartas()){
            this.addCarta(c);
            this.aumentaQuantCartas();
        }
    }

    // método para jogador comprar carta;
    public Carta compraCarta(){
        Carta cartaComprada = this.getCartas().getFirst();
        this.getCartas().removeFirst();
        this.diminuiQuantCartas(); // subtrai quantidade
        return cartaComprada;
    }
}
