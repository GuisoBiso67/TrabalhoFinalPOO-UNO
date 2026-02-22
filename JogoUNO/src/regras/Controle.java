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
    ArrayList<Jogador> jogadores;
    private int tipoBaralho;
    private int indiceAtual;
    private int direcao; // 1 = horario, -1 = anti-horario;

    public Controle(int op, int numJogadores){
        if (op==1){
            baralho = new BaralhoOficial();
            baralho.criaBaralho();
            if (numJogadores >= 7) { // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                Baralho baralho2 = new BaralhoOficial();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            }
        } else{
            baralho = new BaralhoTradicional();
            baralho.criaBaralho();
            if (numJogadores >= 7) { // se o numero de jogadores for maior que 6, precisa de 2 baralhos;
                Baralho baralho2 = new BaralhoTradicional();
                baralho2.criaBaralho();
                baralho.getCartas().addAll(baralho2.getCartas());
            }
        }
        jogadores = new ArrayList<>();
        monteCompra = new Compra();
        monteDescarte = new Descarte();
        indiceAtual = 0;
        direcao = 1;
    }

    public Baralho getBaralho(){
        return baralho;
    }

    public void distribuirCartas(Baralho b, int n, ArrayList<Jogador> jogadores, int tipoBaralho){ // n = numero de jogadores;
        for(int i = 0; i < n; i++){
            System.out.print("Nome Jogador " + (i+1) + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome, b, tipoBaralho));
        }
        this.monteCompra.recebeCartas(b);

        getCarta1();
        indiceAtual = 0;
    }

    public void getCarta1(){ // garante que a primeira carta no jogo seja diferente de curinga ou +4;
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

    public void esvaziaMonteCompra(){
        while(!monteCompra.getCartas().isEmpty()){
            Carta c = this.acessaMonteCompra().getCartas().removeFirst();
            this.acessaMonteDescarte().addCartaNoInicio(c);
        }
        System.out.println("Nro Monte Compras: " + this.acessaMonteCompra().getQuant());
        System.out.println("Nro Monte Descarte: " + this.acessaMonteDescarte().getQuant());
        System.out.println("Monte de Compra Esvaziado!");
    }

    public void compraCartaSeguro(Jogador j){ // mudar esse nome dps, testar se funciona;
        if(monteCompra.getQuant() == 0){
            monteDescarte.reembaralhar(monteCompra);
        }
        j.getBaralho().getCartas().addFirst(monteCompra.compraCarta());
        //this.updateIndice();
    }

    public void jogarCarta(Jogador j, Carta c, Carta cartaAnterior){
        j.jogarCarta(c);
        c.aplicarEfeito(this);
        monteDescarte.addCartaNoInicio(c);
    }

    public boolean validarCarta(Carta c, Carta cAnterior, Grupo g){
        return validacao.validarCarta(c,cAnterior, g);
    }

    public Grupo escolherCor(int tipoBaralho){
        this.tipoBaralho = tipoBaralho; // função aplicada após curinga ou +4;
        int op = 0;
        do{
            System.out.println("Escolha cor/naipe para continuar a partida: ");
            if(tipoBaralho == 1){
                System.out.println("1- Vermelho");
                System.out.println("2- Amarelo");
                System.out.println("3- Verde");
                System.out.println("4- Azul");
            }else{
                System.out.println("1- Copas");
                System.out.println("2- Ouros");
                System.out.println("3- Paus");
                System.out.println("4- Espadas");
            }
            op = scanner.nextInt();
        }while(op<1 || op>4);
        
        return switch (op) {
            case 1 -> Grupo.CR;
            case 2 -> Grupo.OY;
            case 3 -> Grupo.PG;
            case 4 -> Grupo.EB;
            default -> null;
        };
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

    public int getIndice(){
        return indiceAtual;
    }
    /*
    public void setIndice(int novoIndice){
        this.indiceAtual = novoIndice;
    }
     */

    public int getProximoIndice(int passos){
        int tamanho = jogadores.size();
        return (indiceAtual + direcao * passos + tamanho) % tamanho;
    }

    public ArrayList<Jogador> getJogadores(){
        return jogadores;
    }

    /*
    public void pularProximoJogador(){ // mecanica para aplicar os efeitos de bloqueio e de penalidade (+2 e +4);
        int atual = this.getIndice();
        int tamanho = this.getJogadores().size();
        int proximo = (atual + 2) % tamanho; // garante que vai cair sempre em um jogador existente;
        this.setIndice(proximo);
    }
    */

    // mecanica para o reverso;
    public int getDirecao(){
        return direcao;
    }
    public void inverteDirecao(){
        this.direcao *= (-1);
    }

    public void avancarTurno(int passos){
        int tamanho = jogadores.size();
        this.indiceAtual = (this.indiceAtual + direcao * passos + tamanho) % tamanho;
    }
}
