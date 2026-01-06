package regras;

import entities.*;
import baralho.*;
import java.util.*;

public class Controle {
    Scanner scanner = new Scanner(System.in);

    public void distribuirCartas(Baralho b, int n, ArrayList<Jogador> jogadores, int tipoBaralho){ // n = numero de jogadores;
        for(int i = 0; i < n; i++){
            System.out.print("Nome Jogador " + (i+1) + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome, b, tipoBaralho));
        }
    }

    public void criaMonteCompra(Baralho _monteCompra){
        Compra monteCompra = new Compra();
        monteCompra.recebeCartas(_monteCompra);
    }

    public void iniciaMonteDescarte(Carta cartaInicial){
        Descarte monteDescarte = new Descarte();
        monteDescarte.recebeCarta1(cartaInicial);
    }
}
