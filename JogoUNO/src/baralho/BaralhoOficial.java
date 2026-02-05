package baralho;

import java.util.ArrayList;

public class BaralhoOficial extends Baralho{
    public BaralhoOficial(){ // construtor que depende da classe Baralho.java;
        super();
    }

    @Override
    public int getQuant() {
        return super.getQuant();
    }

    ArrayList<Carta> baralho;

    public String getTipo(Carta c){
        return switch (c.getGrupo()) {
            case CR -> "vermelho";
            case OY -> "amarelo";
            case PG -> "verde";
            case EB -> "azul";
            case PR, VE -> "preto";
        };
    }

    public String getValor(Carta c){
        return switch (c.getValor()) {
            case AS -> "um";
            case DOIS -> "dois";
            case TRES -> "tres";
            case QUATRO -> "quatro";
            case CINCO -> "cinco";
            case SEIS -> "seis";
            case SETE -> "sete";
            case OITO -> "oito";
            case NOVE -> "nove";
            case DEZ -> "dez";
            case VALETE -> "bloqueio";
            case DAMA -> "reverso";
            case REI -> "+2";
            case EXTRA1 -> "curinga";
            case EXTRA2 -> "+4";
        };
    }

    /*
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
    */


    @Override
    public String formatarNomeCarta(Carta c){
        return getValor(c) + " " + getTipo(c);
    }
}
