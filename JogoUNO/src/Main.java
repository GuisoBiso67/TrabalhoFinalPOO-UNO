import baralho.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = null;
        System.out.println("Baralho gerado com sucesso");
        System.out.println("Qual tipo de baralho você vai usar?");
        System.out.println("1 - Uno Oficial");
        System.out.println("2 - Baralho Tradicional");
        System.out.println("-> : ");
        int escolha = scanner.nextInt();
        if(escolha == 1){
            baralho = new BaralhoOficial();
        }else if(escolha == 2){
            baralho = new BaralhoTradicional();
        }

        for(Carta c : baralho.getCartas()){
            System.out.println(baralho.formatarNomeCarta(c));
        }
        System.out.println("Quantidade: " + baralho.getQuant());

        // valete, dama e rei tem sempre a msm função: ver se dá pra fazer algo com isso;
    }
}