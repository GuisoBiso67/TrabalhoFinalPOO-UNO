import baralho.*;
import entities.*;
import regras.*;

import java.util.*;

class Main{
    public static void main(String[] args) {
        InterfaceJogo interfaceJogo = new InterfaceJogo();
        interfaceJogo.execucao();
        /*
        Scanner scanner = new Scanner(System.in);
        //Baralho baralho = null;
        //ArrayList<Jogador> jogadores = new ArrayList<>();
        Controle controle;

        int numJogadores;
        do {
            System.out.println("Numero de Jogadores (2-10): ");
            numJogadores = scanner.nextInt();
        } while (numJogadores > 10 || numJogadores < 2);

        String escolhaBaralho;
        System.out.println("Qual tipo de baralho você vai usar?");
        System.out.println("1 - Uno Oficial");
        System.out.println("2 - Baralho Tradicional");
        do{
            System.out.println("-> : ");
            escolhaBaralho = scanner.next();
        }while(!escolhaBaralho.equals("1") && !escolhaBaralho.equals("2"));

        int escolhaBaralhoInt = Integer.parseInt(escolhaBaralho);

        */
        /*
        if (escolhaBaralhoInt == 1) {
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

         */

        /*
        controle = new Controle(escolhaBaralhoInt, numJogadores);
        ArrayList<Jogador> jogadores = controle.getJogadores();
        System.out.println("Baralho gerado com sucesso");


        // INICIA JOGO;
        controle.getBaralho().embaralhaBaralho();
        controle.distribuirCartas(controle.getBaralho(), numJogadores, jogadores, escolhaBaralhoInt);
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

         */

        /*
        for (Carta c : baralho.getCartas()) {
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());


        System.out.println("------------------------------------------");
        System.out.println("-------------bjinmuytvygbhnjuyy-----------");
        System.out.println("------------------------------------------");
        */

        /*
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
        */

        /*
        controle.esvaziaMonteCompra();
        controle.compraCartaSeguro(jogadores.getFirst());
        System.out.println("QUANTIDADE MONTE DE COMPRAS: " + monteCompra.getQuant());
        System.out.println("QUANTIDADE MONTE DE DESCARTE: " + monteDescarte.getQuant());
        System.out.println("Total sistema: " +
                (jogadores.getFirst().getBaralho().getQuant() +
                        jogadores.getLast().getBaralho().getQuant() +
                        monteCompra.getCartas().size() +
                        monteDescarte.getCartas().size())
        );
         */

        //System.out.println("Ultima carta jogada: " + monteDescarte.getFirstCarta().formatarNomeGeral(escolhaBaralhoInt));

        /*
        Jogador jogador;
        Grupo grupo = null;

        while(!controle.jogadorGanhou(jogadores)){
            System.out.println("Indice: " + controle.getIndice() + " | Direção: " + controle.getDirecao());
            jogador = jogadores.get(controle.getIndice());
            System.out.println("Vez de: " + jogador.getNome());
            System.out.println("Escolha uma carta: ");
            int i = 0;
            for (Carta c : jogador.getBaralho().getCartas()) {
                System.out.println(i + " - " + controle.getBaralho().formatarNomeCarta(c));
                i++;
            }
            System.out.println(i + " - Comprar carta");
            int op = scanner.nextInt();

            while((op > i) || (op < 0)){
                System.out.println("Acao invalida! Escolha outra: ");
                op = scanner.nextInt();
            }

            if(op == i){
                controle.compraCartaSeguro(jogador);
            }else{
                while(!(controle.validarCarta(jogador.getBaralho().getCartas().get(op), monteDescarte.getFirstCarta(), grupo))){
                    System.out.println("Carta invalida! Escolha outra: ");
                    op = scanner.nextInt();
                }

                if(jogador.getBaralho().getCartas().get(op).getGrupo().equals(Grupo.PR) || jogador.getBaralho().getCartas().get(op).getGrupo().equals(Grupo.VE)){
                    grupo = controle.escolherCor(escolhaBaralhoInt);
                }else{
                    grupo = null;
                }

                controle.jogarCarta(jogador, jogador.getBaralho().getCartas().get(op), monteDescarte.getFirstCarta());
            }

            System.out.println("------------------------------------------");

            System.out.println("QUANTIDADE MONTE DE COMPRAS: " + monteCompra.getQuant());

            if(grupo != null){
                if(escolhaBaralhoInt == 1){
                    System.out.println("Cor escolhida: " + grupo.formatar(escolhaBaralhoInt));
                }else{
                    System.out.println("Naipe escolhido: " + grupo.formatar(escolhaBaralhoInt));
                }
            }else{
                System.out.println("Ultima carta jogada: " + monteDescarte.getFirstCarta().formatarNomeGeral(escolhaBaralhoInt));
            }
        }

        Jogador vencedor = controle.jogadorGanhouNome(jogadores);
        System.out.println("Vencedor: " + vencedor.getNome());
        */

    }
}