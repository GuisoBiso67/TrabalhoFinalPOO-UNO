package regras;

import baralho.*;

import java.util.*;

public class Descarte /*extends Controle*/ {
    private Baralho monteDescarte;

    public Descarte(){
        this.monteDescarte = new Baralho();
    }

    public void recebeCarta1(Carta cartaInicial){ // aumenta monte de descarte
        monteDescarte.addCartaNoInicio(cartaInicial); // adiciona sempre a carta no topo;
        monteDescarte.aumentaQuantCartas();
    }

    public List<Object> reembaralhar(){
        Carta cartaTopo = monteDescarte.getCartas().getFirst(); // salva ultima carta que foi jogada;

        monteDescarte.getCartas().removeFirst();
        monteDescarte.embaralhaBaralho();

        Baralho novoMonteDescarte = new Baralho(); // cria novo descarte
        novoMonteDescarte.addCartaNoInicio(cartaTopo);
        novoMonteDescarte.aumentaQuantCartas();

        return List.of(monteDescarte, novoMonteDescarte); // preciso retornar os dois valores, entao uso um List.of (verificar pq é static)
    }

    public int getQuant(){
        return monteDescarte.getQuant();
    }

    public void setQuant(int quant){
        monteDescarte.setQuant(quant);
    }
}
