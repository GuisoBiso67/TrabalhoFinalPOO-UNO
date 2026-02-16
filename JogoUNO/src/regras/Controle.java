package regras;

import entities.*;
import baralho.*;
import java.util.*;

public class Controle {
    private Scanner scanner = new Scanner(System.in);
    private Baralho baralho;
    private Compra monteCompra;
    private Descarte monteDescarte;
    private Validacao validacao = new Validacao();

    public Controle(int op){
        if(op==1){
            baralho = new BaralhoOficial();
            monteCompra = new Compra();
            monteDescarte = new Descarte();
        }else {
            baralho = new BaralhoTradicional();
            monteCompra = new Compra();
            monteDescarte = new Descarte();
        }
    }

    public void distribuirCartas(Baralho b, int n, ArrayList<Jogador> jogadores, int tipoBaralho){ // n = numero de jogadores;
        for(int i = 0; i < n; i++){
            System.out.print("Nome Jogador " + (i+1) + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome, b, tipoBaralho));
        }
        this.monteCompra.recebeCartas(b);

        getCarta1();
    }

    public void getCarta1(){ // garante que a primeira carta no jogo seja diferente de curing ou +4;
        Carta carta1MonteCompra;
        do{
            carta1MonteCompra= this.monteCompra.getCartas().getFirst();
            this.monteDescarte.recebeCarta1(carta1MonteCompra);
            monteCompra.getCartas().removeFirst();
        }while(carta1MonteCompra.getGrupo().equals(Grupo.PR) || carta1MonteCompra.getGrupo().equals(Grupo.VE));
    }

    public Compra acessaMonteCompra(){ return monteCompra;
    }
    public Descarte acessaMonteDescarte(){
        return monteDescarte;
    }

    public void criaMonteCompra(Baralho _monteCompra){
        this.monteCompra.recebeCartas(_monteCompra);
    }

    public void iniciaMonteDescarte(Carta cartaInicial){
        this.monteDescarte.recebeCarta1(cartaInicial);
    }

    public void compraCartaSeguro(Jogador j){ // mudar esse nome dps, testar se funciona;
        if(monteCompra.getQuant() == 0){
            monteDescarte.reembaralhar(monteCompra);
        }
        j.getBaralho().getCartas().addFirst(monteCompra.compraCarta());
    }

    public void jogarCarta(Jogador j, Carta c, Carta cartaAnterior){
        j.jogarCarta(c);
        monteDescarte.addCartaNoInicio(c);
    }

    public boolean validarCarta(Carta c, Carta cAnterior){
        return validacao.validarCarta(c,cAnterior);
    }

    public boolean jogadorGanhou(ArrayList<Jogador> jogadores){
        for(Jogador jogador : jogadores){
            if(jogador.getQuant() == 0){
                return true;
            }
        }
        return false;
    }

    public Jogador jogadorGanhouNome(ArrayList<Jogador> jogadores){ // so chama se a de cima for true;
        for(Jogador jogador : jogadores){
            if(jogador.getQuant() == 0){
                return jogador;
            }
        }
        return null;
    }
}
