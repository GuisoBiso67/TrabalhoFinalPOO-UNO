package regras;

import baralho.*;

public class Descarte extends Monte {

    public Descarte() {
        super();
    }

    // método usado após distribuir baralho para os jogadores, é a primeira carta para iniciar o jogo;
    public void recebeCarta1(Carta cartaInicial){
        this.addCartaNoInicio(cartaInicial); // adiciona sempre a carta no topo;
        this.aumentaQuantCartas();
    }

    // método retonar primeira carta do monte
    public Carta getFirstCarta(){
        return this.getCartas().getFirst();
    }

    // método que reembaralha o monte de descarte e manda as cartas para o monte de compra;
    // usado quando acaba as cartas do monte de compras;
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
