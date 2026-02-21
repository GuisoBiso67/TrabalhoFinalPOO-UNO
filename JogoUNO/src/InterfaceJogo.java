import baralho.*;
import regras.*;
import entities.*;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceJogo {
    Scanner input = new Scanner(System.in);
    Controle controle;
    int opcao;

    public void execucao(){
        this.exibirMenu();
        opcao = this.escolhaMenu();
        do {
            if(opcao == 1){
                comecarJogo();
            }
        }while(opcao!=0);
    }

    private void exibirMenu(){
        System.out.println("\n------ BEM-VINDO ------");
        System.out.println("1 - Jogar");
        System.out.println("0 - Sair");
    }

    private int escolhaMenu(){
        String escolhaMenu;
        do{
            System.out.println("OPCAO: ");
            escolhaMenu = input.next();
            //System.out.print("Opcao Invalida. Selecione outra!");
        }while(!(escolhaMenu.equals("1")) && !(escolhaMenu.equals("0")));
        return Integer.parseInt(escolhaMenu);
    }

    private int numJogadores(){
        String numJogadores;
        System.out.println("Numero de Jogadores (2 a 10): ");
        do {
            numJogadores = input.next();
            //System.out.print("Opcao Invalida. Selecione outra!");
        } while (Integer.parseInt(numJogadores) > 10 || Integer.parseInt(numJogadores) < 2);
        return Integer.parseInt(numJogadores);
    }

    private int escolhaBaralho(){
        String escolhaBaralho;

        System.out.println("---------------------------------------");
        System.out.println("-> Qual tipo de baralho você quer usar?");
        System.out.println("1 - Uno Oficial");
        System.out.println("2 - Baralho Tradicional");
        do{
            System.out.println("-> : ");
            escolhaBaralho = input.next();
        }while(!escolhaBaralho.equals("1") && !escolhaBaralho.equals("2"));
        System.out.println("---------------------------------------");

        return Integer.parseInt(escolhaBaralho);
    }

    private ArrayList<Jogador> gerarBaralho_e_Jogadores(int tipoBaralho, int numJogadores){
        controle = new Controle(tipoBaralho, numJogadores);
        ArrayList<Jogador> jogadores = controle.getJogadores();
        System.out.println("Baralho gerado com sucesso");
        return jogadores;
    }

    private void informacoesBasicas(Controle controle, int tipoBaralho){
        String direcao;
        if(controle.getDirecao() == 1){
            direcao = "HORARIO";
        }else{
            direcao = "ANTI-HORARIO";
        }
        System.out.println("Indice: " + controle.getIndice() + " | Direção: " + direcao);
        System.out.println("QUANTIDADE MONTE DE COMPRAS: " + controle.acessaMonteCompra().getQuant());
        System.out.println("Ultima carta jogada: " + controle.acessaMonteDescarte().getFirstCarta().formatarNomeGeral(tipoBaralho));
    }

    private int imprimirCartas(Jogador j){
        System.out.println("Vez de: " + j.getNome());
        System.out.println("Escolha uma carta: ");
        int i = 0;
        for (Carta c : j.getBaralho().getCartas()) {
            System.out.println(i + " - " + controle.getBaralho().formatarNomeCarta(c));
            i++;
        }
        System.out.println(i + " - Comprar carta");
        return i;
    }

    private Grupo compraCarta(Controle controle, Jogador j, int tipoBaralho, int op, Grupo g, int i){
        if(op == i){
            controle.compraCartaSeguro(j);
        }else{
            while(!(controle.validarCarta(j.getBaralho().getCartas().get(op), controle.acessaMonteDescarte().getFirstCarta(), g))){
                System.out.println("Carta invalida! Escolha outra: ");
                op = input.nextInt();
            }

            if(j.getBaralho().getCartas().get(op).getGrupo().equals(Grupo.PR) || j.getBaralho().getCartas().get(op).getGrupo().equals(Grupo.VE)){
                g = controle.escolherCor(tipoBaralho);
            }else{
                g = null;
            }

            controle.jogarCarta(j, j.getBaralho().getCartas().get(op), controle.acessaMonteDescarte().getFirstCarta());
        }

        return g;
    }

    private void retornaGrupo(Grupo g, int tipoBaralho){ // usada após escolher a cor de curinga ou +4;
        if(g != null){
            if(tipoBaralho == 1){
                System.out.println("Cor escolhida: " + g.formatar(tipoBaralho));
            }else{
                System.out.println("Naipe escolhido: " + g.formatar(tipoBaralho));
            }
        }else{
            System.out.println("Ultima carta jogada: " + controle.acessaMonteDescarte().getFirstCarta().formatarNomeGeral(tipoBaralho));
        }
    }

    private void comecarJogo(){
        int numJogadores = this.numJogadores();
        int tipoBaralho = this.escolhaBaralho();
        ArrayList<Jogador> jogadores =  gerarBaralho_e_Jogadores(tipoBaralho, numJogadores);

        controle.getBaralho().embaralhaBaralho();
        controle.distribuirCartas(controle.getBaralho(), numJogadores, jogadores, tipoBaralho);

        jogando(controle, numJogadores, tipoBaralho, jogadores);
    }

    private void jogando(Controle controle, int numJogadores, int tipoBaralho, ArrayList<Jogador> jogadores){
        Compra monteCompra = controle.acessaMonteCompra();
        Descarte monteDescarte = controle.acessaMonteDescarte();
        Jogador jogador;
        Grupo grupo = null;

        while(!controle.jogadorGanhou(jogadores)){

            informacoesBasicas(controle, tipoBaralho); // exibe direção da roda e ultima carta jogada;

            jogador = jogadores.get(controle.getIndice());

            int i = imprimirCartas(jogador);
            int op = input.nextInt();
            while((op > i) || (op < 0)){
                System.out.println("Acao invalida! Escolha outra: ");
                op = input.nextInt();
            }

            grupo = compraCarta(controle, jogador, tipoBaralho, op, grupo, i);

            System.out.println("------------------------------------------");

            retornaGrupo(grupo, tipoBaralho);
        }

        Jogador vencedor = controle.jogadorGanhouNome(jogadores);
        System.out.println("Vencedor: " + vencedor.getNome());
    }

}
