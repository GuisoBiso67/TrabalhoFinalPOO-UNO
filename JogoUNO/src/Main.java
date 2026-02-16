import baralho.*;
import entities.*;
import regras.*;

import java.util.*;

class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = null;
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Controle controle;

        int numJogadores;
        do {
            System.out.println("Numero de Jogadores (2-10): ");
            numJogadores = scanner.nextInt();
        } while (numJogadores > 10 || numJogadores < 2);

        int escolha;
        System.out.println("Qual tipo de baralho você vai usar?");
        System.out.println("1 - Uno Oficial");
        System.out.println("2 - Baralho Tradicional");
        do{
            System.out.println("-> : ");
            escolha = scanner.nextInt();
        }while(escolha != 1 && escolha != 2);

        if (escolha == 1) {
            baralho = new BaralhoOficial();
            baralho.criaBaralho();
            if (numJogadores >= 7) { // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                Baralho baralho2 = new BaralhoOficial();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            }
            // --------------------------------------
        } else {
            baralho = new BaralhoTradicional();
            baralho.criaBaralho();
            if (numJogadores >= 7) { // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                Baralho baralho2 = new BaralhoTradicional();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            }
        }
        controle = new Controle(escolha);
        System.out.println("Baralho gerado com sucesso");


        // INICIA JOGO;
        baralho.embaralhaBaralho();
        controle.distribuirCartas(baralho, numJogadores, jogadores, escolha);
        //controle.criaMonteCompra(baralho);
        //controle.iniciaMonteDescarte(baralho.getCartas().getFirst());

        // ---------------------------

        for (Jogador jogador : jogadores) {
            jogador.showCartas();
            System.out.println("Quantidade de Cartas: " + jogador.getQuant());
        }

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        /*
        for (Carta c : baralho.getCartas()) {
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());


        System.out.println("------------------------------------------");
        System.out.println("-------------bjinmuytvygbhnjuyy-----------");
        System.out.println("------------------------------------------");
        */

        Compra monteCompra = controle.acessaMonteCompra();
        Descarte monteDescarte = controle.acessaMonteDescarte();

        System.out.println("MONTE DE COMPRAS: ");
        for (Carta c : monteCompra.getCartas()) {
            System.out.println(monteCompra.formatarNomeCarta(c));
        }
        System.out.println("QUANTIDADE MONTE DE COMPRAS: " + monteCompra.getQuant());

        System.out.println("------------------------------------------");

        System.out.println("MONTE DE DESCARTE: ");
        for (Carta c : monteDescarte.getCartas()) {
            System.out.println(monteDescarte.formatarNomeCarta(c));
        }
        System.out.println("QUANTIDADE MONTE DE DESCARTE: " + monteDescarte.getQuant());

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        System.out.println("Ultima carta jogada: " + monteDescarte.formatarNomeCarta(monteDescarte.getFirstCarta()));

        do {
            for (Jogador jogador : jogadores) {
                if (controle.jogadorGanhou(jogadores)) {
                    break;
                }
                System.out.println("Vez de: " + jogador.getNome());
                System.out.println("Escolha uma carta: ");
                int i = 0;
                for (Carta c : jogador.getBaralho().getCartas()) {
                    System.out.println(i + " - " + baralho.formatarNomeCarta(c));
                    i++;
                }
                System.out.println(i + " - Comprar carta");
                int op = scanner.nextInt();
                if(op == i){
                    controle.compraCartaSeguro(jogador);
                }else{
                    while(!controle.validarCarta(jogador.getBaralho().getCartas().get(op), monteDescarte.getFirstCarta())){
                        System.out.println("Carta invalida! Escolha outra: ");
                        op = scanner.nextInt();
                    }
                    controle.jogarCarta(jogador, jogador.getBaralho().getCartas().get(op), monteDescarte.getFirstCarta());
                }
                System.out.println("------------------------------------------");

                System.out.println("QUANTIDADE MONTE DE COMPRAS: " + monteCompra.getQuant());
                System.out.println("Ultima carta jogada: " + monteDescarte.formatarNomeCarta(monteDescarte.getFirstCarta()));
            }
        }while(!controle.jogadorGanhou(jogadores));

        Jogador vencedor = controle.jogadorGanhouNome(jogadores);
        System.out.println("Vencedor: " + vencedor.getNome());
    }
}