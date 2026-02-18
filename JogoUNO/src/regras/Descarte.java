package regras;

import baralho.*;

public class Descarte extends Monte {

    public Descarte() {
        super();
    }

    public void recebeCarta1(Carta cartaInicial){
        this.addCartaNoInicio(cartaInicial); // adiciona sempre a carta no topo;
        this.aumentaQuantCartas();
    }

    public Carta getFirstCarta(){
        return this.getCartas().getFirst();
    }

    public void reembaralhar(Compra monteCompra){
        Carta cartaTopo = this.getCartas().removeFirst(); // salva ultima carta que foi jogada;
        //monteCompra.getCartas().add(cartaTopo);

        //this.getCartas().removeFirst();
        this.embaralhaBaralho(); // descarte foi embaralhado

        while(!this.getCartas().isEmpty()){
            monteCompra.getCartas().add(this.getCartas().removeFirst());
        }

        this.addCartaNoInicio(cartaTopo); // adiciona a ultima carta jogada

        //return this;
    }
}
