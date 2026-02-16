package entities;

import baralho.*;

public class Jogador {
    private String nome;
    private Baralho baralhoJog;

    public Jogador(String nome, Baralho baralho, int tipoBaralho) { // Jogador já é criado distribuindo o baralho;
        this.nome = nome;
        if(tipoBaralho == 1){
            this.baralhoJog = new BaralhoOficial();
            for(int j = 0; j < 7; j++){ // criar esse 7 como constante
                this.baralhoJog.addCarta(baralho.getCartas().get(j)); // ver se dá problema depois;
                this.baralhoJog.aumentaQuantCartas();
                baralho.getCartas().removeFirst(); // java reindexa a lista automaticamente;
                baralho.diminuiQuantCartas();
            }
        }else{
            this.baralhoJog = new BaralhoTradicional();
            for(int j = 0; j < 7; j++){ // criar esse 7 como constante
                this.baralhoJog.addCarta(baralho.getCartas().get(j)); // ver se dá problema depois;
                this.baralhoJog.aumentaQuantCartas();
                baralho.getCartas().removeFirst(); // java reindexa a lista automaticamente;
                baralho.diminuiQuantCartas();
            }
        }
    }

    public void jogarCarta(Carta c){
        baralhoJog.getCartas().remove(c); // remove carta da mao;
        baralhoJog.diminuiQuantCartas();
        System.out.println("Carta jogada = " + baralhoJog.formatarNomeCarta(c));
    }

    // funções basicas
    public String getNome() {
        return nome.toUpperCase();
    }

    public void showCartas(){
        System.out.println("CARTAS DE " + getNome());
        for(Carta c : this.baralhoJog.getCartas()){
            System.out.println(baralhoJog.formatarNomeCarta(c));
        }
    }

    public int getQuant(){
        return baralhoJog.getQuant();
    }

    public Baralho getBaralho(){
        return baralhoJog;
    }
}
