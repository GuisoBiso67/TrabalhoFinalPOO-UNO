package regras;

import baralho.*;
import entities.*;
import java.util.*;

public class Descarte extends Controle {
    private Baralho monteDescarte;

    public Descarte(){
        this.monteDescarte = new Baralho();
    }

    public void recebeCarta1(Carta cartaInicial){
        monteDescarte.addCarta(cartaInicial);
    }

    public void jogarCarta(Jogador jogador, Carta cartaJogada){ // aumenta monte de descarte

    }

    public void reembaralhar(Baralho monteCompraAnterior){

    }
}
