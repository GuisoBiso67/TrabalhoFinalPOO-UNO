import baralho.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        Baralho baralho = new Baralho();
        System.out.println("Baralho gerado com sucesso");
        for(Carta c : baralho.getCartas()){
            System.out.println(c.valor + " " + c.grupo + " " + c.funcao);
        }
        System.out.println("Quantidade: " + baralho.getQuant());

        // valete, dama e rei tem sempre a msm função: ver se dá pra fazer algo com isso;
    }
}