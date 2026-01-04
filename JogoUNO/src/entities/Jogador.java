package entities;

import baralho.*;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private Baralho baralhoJog;

    public Jogador(String nome, Baralho baralho, int tipoBaralho) {
        this.nome = nome;
        if(tipoBaralho == 1){
            this.baralhoJog = new BaralhoOficial();
            for(int j = 0; j < 7; j++){ // criar esse 7 como constante
                this.baralhoJog.addCarta(baralho.getCartas().get(j)); // ver se dá problema depois;
                baralho.getCartas().removeFirst(); // java reindexa a lista automaticamente;
                baralho.diminuiQuantCartas();
            }
        }else{
            this.baralhoJog = new BaralhoTradicional();
            for(int j = 0; j < 7; j++){ // criar esse 7 como constante
                this.baralhoJog.addCarta(baralho.getCartas().get(j)); // ver se dá problema depois;
                baralho.getCartas().removeFirst(); // java reindexa a lista automaticamente;
                baralho.diminuiQuantCartas();
            }
        }

    }

    public String getNome() {
        return nome;
    }

    public void showCartas(){
        System.out.println("Cartas de " + getNome());
        for(Carta c : this.baralhoJog.getCartas()){
            System.out.println(baralhoJog.formatarNomeCarta(c));
        }
    }
}
