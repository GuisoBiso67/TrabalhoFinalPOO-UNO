import baralho.*;
import entities.*;
import regras.Controle;

import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = null;
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Controle controle = new Controle();

        int numJogadores = -1;
        while(numJogadores > 10 || numJogadores < 0){
            System.out.println("Numero de Jogadores (2-10): ");
            numJogadores = scanner.nextInt();
        }

        System.out.println("Qual tipo de baralho você vai usar?");
        System.out.println("1 - Uno Oficial");
        System.out.println("2 - Baralho Tradicional");
        System.out.println("-> : ");
        int escolha = scanner.nextInt();

        if(escolha == 1){
            if(numJogadores >= 7){ // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                baralho = new BaralhoOficial();
                baralho.criaBaralho();
                Baralho baralho2 = new BaralhoOficial();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            }else{
                baralho = new BaralhoOficial();
                baralho.criaBaralho();
            }
        }else if (escolha == 2){
            if(numJogadores >= 7){ // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                baralho = new BaralhoTradicional();
                baralho.criaBaralho();
                Baralho baralho2 = new BaralhoTradicional();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            }else{
                baralho = new BaralhoTradicional();
                baralho.criaBaralho();
            }
        }
        System.out.println("Baralho gerado com sucesso");

        baralho.embaralhaBaralho();

        controle.distribuirCartas(baralho, numJogadores, jogadores, escolha);

        for(Jogador jogador : jogadores){
            jogador.showCartas();
        }

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        for(Carta c : baralho.getCartas()){
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());

        /*
        for(Carta c : baralho.getCartas()){
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        baralho.embaralhaBaralho();

        for(Carta c : baralho.getCartas()){
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());
        */
    }
}