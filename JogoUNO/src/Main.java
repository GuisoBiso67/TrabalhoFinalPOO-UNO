import baralho.*;
import entities.*;
import regras.Controle;

import java.util.*;

class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = null;
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Controle controle = new Controle();

        int numJogadores;
        do {
            System.out.println("Numero de Jogadores (2-10): ");
            numJogadores = scanner.nextInt();
        } while (numJogadores > 10 || numJogadores < 2);

        System.out.println("Qual tipo de baralho você vai usar?");
        System.out.println("1 - Uno Oficial");
        System.out.println("2 - Baralho Tradicional");
        System.out.println("-> : ");
        int escolha = scanner.nextInt();

        if (escolha == 1) {
            if (numJogadores >= 7) { // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                baralho = new BaralhoOficial();
                baralho.criaBaralho();
                Baralho baralho2 = new BaralhoOficial();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            } else {
                baralho = new BaralhoOficial();
                baralho.criaBaralho();
            }
        } else if (escolha == 2) {
            if (numJogadores >= 7) { // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                baralho = new BaralhoTradicional();
                baralho.criaBaralho();
                Baralho baralho2 = new BaralhoTradicional();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            } else {
                baralho = new BaralhoTradicional();
                baralho.criaBaralho();
            }
        }
        System.out.println("Baralho gerado com sucesso");


        // INICIA JOGO;
        baralho.embaralhaBaralho();
        controle.distribuirCartas(baralho, numJogadores, jogadores, escolha);
        //controle.criaMonteCompra(baralho); tentar entender pq isso acontece, acho q estou criando dois comtaCompra sem querer
        controle.iniciaMonteDescarte(baralho.getCartas().getFirst());
        // ---------------------------

        for (Jogador jogador : jogadores) {
            jogador.showCartas();
            System.out.println("Quantidade de Cartas: " + jogador.getQuant());
        }

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        for (Carta c : baralho.getCartas()) {
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());

        do {
            for (Jogador jogador : jogadores) {
                if (controle.jogadorGanhou(jogadores)) {
                    break;
                }
                System.out.println("Vez de: " + jogador.getNome());
                System.out.println("Escolha um carta: ");
                int i = 0;
                for (Carta c : jogador.getBaralho().getCartas()) {
                    System.out.println(i + " - " + baralho.formatarNomeCarta(c));
                    i++;
                }
                int op = scanner.nextInt();
                jogador.jogarCarta(jogador.getBaralho().getCartas().get(op));
            }
        }while(!controle.jogadorGanhou(jogadores));

        Jogador vencedor = controle.jogadorGanhouNome(jogadores);
        System.out.println("Vencedor: " + vencedor.getNome());

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