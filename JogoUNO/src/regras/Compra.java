package regras;

import baralho.*;

public class Compra extends Monte {

    public Compra() {
        super();
    }

    public void recebeCartas(Baralho baralho){
        for(Carta c : baralho.getCartas()){
            this.addCarta(c);
            this.aumentaQuantCartas();
        }
    }

    public Carta compraCarta(){
        Carta cartaComprada = this.getCartas().getFirst();
        this.getCartas().removeFirst();
        this.diminuiQuantCartas(); // subtrai quantidade
        return cartaComprada;
    }
}
