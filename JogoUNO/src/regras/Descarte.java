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

    public Descarte reembaralhar(Compra monteCompra){
        Carta cartaTopo = this.getCartas().getFirst(); // salva ultima carta que foi jogada;
        //monteCompra.getCartas().add(cartaTopo);

        this.getCartas().removeFirst();
        this.embaralhaBaralho(); // descarte foi embaralhado

        for(Carta carta : this.getCartas()){
            monteCompra.getCartas().add(carta); // constroi monte de compra
            monteCompra.aumentaQuantCartas();
        }
        this.getCartas().clear(); // limpa arrayList do descarte
        this.addCartaNoInicio(cartaTopo); // adiciona a ultima carta jogada
        this.setQuant(1); // descarte tem só 1 carta;

        return this;
    }
}
