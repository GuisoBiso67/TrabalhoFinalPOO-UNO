package regras;

import entities.*;
import baralho.*;
import java.util.*;

public class Controle {
    Scanner scanner = new Scanner(System.in);
    //ArrayList<Jogador> jogadores = new ArrayList<>();

    public void distribuirCartas(Baralho b, int n, ArrayList<Jogador> jogadores, int tipoBaralho){ // n = numero de jogadores;
        for(int i = 0; i < n; i++){
            System.out.print("Nome Jogador " + (i+1) + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome, b, tipoBaralho));
        }
    }
}
