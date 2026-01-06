package regras;

import baralho.*;
import java.util.*;

public class Compra extends Controle {
    private Baralho monteCompra;

    public Compra(){
        this.monteCompra = new Baralho();
    }

    public void recebeCartas(Baralho monteCompra){
        for(Carta c : monteCompra.getCartas()){
            monteCompra.addCarta(c);
        }
    }

    public void compraCarta(){
        if(monteCompra == null){ // se o monte de compras acaba, tem q reembaralhar o descarte;

        }
    }
}
