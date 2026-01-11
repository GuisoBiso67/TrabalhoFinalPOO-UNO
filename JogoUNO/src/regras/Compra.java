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
            monteCompra.aumentaQuantCartas();
        }
    }

    public Carta compraCarta(){
        Carta cartaComprada = monteCompra.getCartas().getFirst();
        monteCompra.getCartas().removeFirst();
        monteCompra.diminuiQuantCartas(); // subtrai quantidade
        return cartaComprada;
    }

    // metodos auxiliares repetidos;
    public int getQuant(){
        return monteCompra.getQuant();
    }
}
