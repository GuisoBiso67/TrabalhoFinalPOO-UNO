package baralho;

import java.util.ArrayList;

public class BaralhoTradicional extends Baralho{
    public BaralhoTradicional(){
        super();
    }

    @Override
    public int getQuant() {
        return super.getQuant();
    }

    ArrayList<Carta> baralho;

    public String getTipo(Carta c){
        return switch (c.getGrupo()) {
            case CR -> "copas";
            case OY -> "ouros";
            case PG -> "paus";
            case EB -> "espadas";
            case PR -> "preto";
            case VE -> "vermelho";
        };
    }

    public String getValor(Carta c){
        return switch (c.getValor()) {
            case AS -> "as";
            case DOIS -> "dois";
            case TRES -> "tres";
            case QUATRO -> "quatro";
            case CINCO -> "cinco";
            case SEIS -> "seis";
            case SETE -> "sete";
            case OITO -> "oito";
            case NOVE -> "nove";
            case DEZ -> "dez";
            case VALETE -> "valete";
            case DAMA -> "dama";
            case REI -> "rei";
            case EXTRA1 -> "curingaPR";
            case EXTRA2 -> "curingaVE";
        };
    }

    public String getFuncao(Carta c){
        return switch (c.getFuncao()){
            case NUMERICA -> "numerica";
            case BLOQUEIO -> "bloqueio";
            case REVERSO -> "reverso";
            case MAIS_DOIS -> "+2";
            case CURINGA -> "curinga";
            case MAIS_QUATRO -> "+4";
        };
    }
    // valete, dama e rei tem sempre a msm função: ver se dá pra fazer algo com isso;


    @Override
    public String formatarNomeCarta(Carta c){
        return getValor(c) + " " + getTipo(c) + " " + getFuncao(c);
    }
}
