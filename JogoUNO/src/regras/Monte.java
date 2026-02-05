package regras;

import baralho.*;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.LinkedList;

public class Monte {
    private int quant;
    private ArrayList<Carta> monte;

    //protected LinkedList<Carta> cartas = new LinkedList<>();
    //protected Baralho baralho;

    public Monte(Baralho baralho){
        monte = new ArrayList<Carta>();
    }

    // ------------------------------------------------------------

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

    // melhorar isso depois;
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
