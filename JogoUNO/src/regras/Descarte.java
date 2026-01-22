package regras;

import baralho.*;

import java.util.*;

public class Descarte extends Monte {

    public Descarte(Baralho baralho) {
        super(baralho);
    }

    public void recebeCarta1(Carta cartaInicial){
        this.addCartaNoInicio(cartaInicial); // adiciona sempre a carta no topo;
        this.aumentaQuantCartas();
    }

    public List<Object> reembaralhar(){
        Carta cartaTopo = this.getCartas().getFirst(); // salva ultima carta que foi jogada;

        this.getCartas().removeFirst();
        this.embaralhaBaralho();

        Baralho novoMonteDescarte = new Baralho(); // cria novo descarte
        novoMonteDescarte.addCartaNoInicio(cartaTopo);
        novoMonteDescarte.aumentaQuantCartas();

        return List.of(this, novoMonteDescarte); // preciso retornar os dois valores, entao uso um List.of (verificar pq é static)
    }
}
