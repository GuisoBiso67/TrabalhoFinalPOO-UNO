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
        // construtor de Controle que cria o baralho, pois o jogador tem q escolher o tipo antes;
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
        jogadores = new ArrayList<>(); // declara lista de jogadores;
        monteCompra = new Compra(); // cria monte compra;
        monteDescarte = new Descarte(); // cria monte descarte;
        indiceAtual = 0; // começa em zero (1º jogador);
        direcao = 1; // direção 1 pq começa girando horário;
    }

    public Baralho getBaralho(){
        return baralho;
    }

    // método que distribui cartas para os jogadores e constroi monte de compra;
    public void distribuirCartas(Baralho b, int n, ArrayList<Jogador> jogadores, int tipoBaralho){ // n = numero de jogadores;
        for(int i = 0; i < n; i++){
            System.out.print("Nome Jogador " + (i+1) + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome, b, tipoBaralho));
        }
        this.monteCompra.recebeCartas(b); // constroi monte de compra;

        getCarta1(); // primeira carta do monte descarte, pra começar o jogo;
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

    public Compra acessaMonteCompra(){ return monteCompra; }
    public Descarte acessaMonteDescarte(){ return monteDescarte; }

    /*
    // métodos nunca usados;
    public void criaMonteCompra(Baralho _monteCompra){
        this.monteCompra.recebeCartas(_monteCompra);
    }

    public void iniciaMonteDescarte(Carta cartaInicial){
        this.monteDescarte.recebeCarta1(cartaInicial);
    }
    */

    // método usado para testes anteriores para embaralhar descarte após monte de compra ficar vazio;
    public void esvaziaMonteCompra(){
        while(!monteCompra.getCartas().isEmpty()){
            Carta c = this.acessaMonteCompra().getCartas().removeFirst();
            this.acessaMonteDescarte().addCartaNoInicio(c);
        }
        System.out.println("Nro Monte Compras: " + this.acessaMonteCompra().getQuant());
        System.out.println("Nro Monte Descarte: " + this.acessaMonteDescarte().getQuant());
        System.out.println("Monte de Compra Esvaziado!");
    }

    // verifica se tem cartas no monte de comprar antes de comprar
    public void compraCartaSeguro(Jogador j){ // mudar esse nome dps, testar se funciona;
        if(monteCompra.getQuant() == 0){
            monteDescarte.reembaralhar(monteCompra);
        }
        j.getBaralho().getCartas().addFirst(monteCompra.compraCarta());
        //this.updateIndice();
    }

    // método que joga a carta, aplica efeito e coloca ela no monte de descarte;
    public void jogarCarta(Jogador j, Carta c, Carta cartaAnterior){
        j.jogarCarta(c);
        c.aplicarEfeito(this);
        monteDescarte.addCartaNoInicio(c);
    }

    // método para validar carta;
    public boolean validarCarta(Carta c, Carta cAnterior, Grupo g){
        return validacao.validarCarta(c,cAnterior, g);
    }

    // método aplicada após curinga ou +4;
    public Grupo escolherCor(int tipoBaralho){
        this.tipoBaralho = tipoBaralho;
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
        
        return switch (op) { // retorna o valor do grupo;
            case 1 -> Grupo.CR;
            case 2 -> Grupo.OY;
            case 3 -> Grupo.PG;
            case 4 -> Grupo.EB;
            default -> null;
        };
    }

    // jogador ganha quando esvazia sua mão;
    public boolean jogadorGanhou(ArrayList<Jogador> jogadores){
        for(Jogador jogador : jogadores){
            if(jogador.getQuant() == 0){
                return true;
            }
        }
        return false;
    }

    // retorna nome do jogador que ganhou;
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

    // método que calcula de quem é a vez;
    public int getProximoIndice(int passos){
        int tamanho = jogadores.size();
        return (indiceAtual + direcao * passos + tamanho) % tamanho;
    }

    // método que retorna lista de jogadores;
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

    // método que avança o turno
    // 1 = vai para o próximo jogador; 2 = pula um jogador e vai para o próximo (bloqueio, +2, +4);
    public void avancarTurno(int passos){
        int tamanho = jogadores.size();
        this.indiceAtual = (this.indiceAtual + direcao * passos + tamanho) % tamanho;
    }
}
