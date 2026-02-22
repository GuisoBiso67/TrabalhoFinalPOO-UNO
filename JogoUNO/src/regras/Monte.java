package regras;

import baralho.*;

import java.util.ArrayList;
import java.util.Collections;

public class Monte {
    private int quant;
    private ArrayList<Carta> monte;

    // Monte é uma lista de cartas também;
    public Monte(){
        monte = new ArrayList<Carta>();
    }

    // ------------------------------------------------------------

    // métodos básicos;
    public ArrayList<Carta> getCartas(){
        return monte;
    }

    public int getQuant(){
        return monte.size();
    }

    public void setQuant(int quant){
        this.quant = quant;
    }

    public void aumentaQuantCartas(){
        quant++;
    }

    public void diminuiQuantCartas(){
        quant--;
    }

    // melhorar isso depois; no fim não usei;
    public String formatarNomeCarta(Carta c){
        return c.getValor().toString() + " " + c.getGrupo().toString() + " " +  c.getFuncao().toString();
    }

    public void embaralhaBaralho(){
        Collections.shuffle(monte);
    }

    public void addCarta(Carta c){
        monte.add(c);
    }

    public void addCartaNoInicio(Carta c){
        monte.addFirst(c);
    }
}
