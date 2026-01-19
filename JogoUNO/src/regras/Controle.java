package regras;

import entities.*;
import baralho.*;
import java.util.*;

public class Controle {
    private Scanner scanner = new Scanner(System.in);
    private Compra monteCompra = new Compra();
    private Descarte monteDescarte = new Descarte();

    public void distribuirCartas(Baralho b, int n, ArrayList<Jogador> jogadores, int tipoBaralho){ // n = numero de jogadores;
        for(int i = 0; i < n; i++){
            System.out.print("Nome Jogador " + (i+1) + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome, b, tipoBaralho));
        }
    }

    public void criaMonteCompra(Baralho _monteCompra){
        this.monteCompra.recebeCartas(_monteCompra);
    }

    public void iniciaMonteDescarte(Carta cartaInicial){
        this.monteDescarte.recebeCarta1(cartaInicial);
    }

    public Carta compraCartaSeguro(){ // mudar esse nome dps, testar se funciona;
        if(monteCompra.getQuant() == 0){
            List<Object> resultado = monteDescarte.reembaralhar();
            monteCompra = (Compra) resultado.get(0);
            monteCompra.setQuant(monteCompra.getQuant());
            monteDescarte = (Descarte) resultado.get(1);
            monteDescarte.setQuant(monteDescarte.getQuant());
        }
        return monteCompra.compraCarta();
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
