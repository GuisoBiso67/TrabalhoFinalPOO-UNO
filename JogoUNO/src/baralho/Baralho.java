package baralho;

import java.util.*;

// declarar Baralho como abstrato para usar o formatarCarta corretamente;
public abstract class Baralho {
    private int quant = 0;
    private ArrayList<Carta> baralho;

    public Baralho(){ // construtor de baralho é uma lista vazia de cartas;
        baralho = new ArrayList<Carta>();
    }

    // método que cria o baralho;
    public void criaBaralho(){
        // cria todas as cartas numericas, sem função especifica;
        for(Valor v : Valor.values() ){ // cria 10x4 = 40 cartas;
            if(!(v.equals(Valor.EXTRA1)) && !(v.equals(Valor.EXTRA2)) && !(v.equals(Valor.VALETE)) && !(v.equals(Valor.DAMA)) && !(v.equals(Valor.REI))){
                for(Grupo g : Grupo.values()){
                    if(!(g.equals(Grupo.PR)) && !(g.equals(Grupo.VE))){
                        baralho.add(new Carta(g, v, Funcao.NUMERICA));
                        quant++;
                    }
                }
            }
        }

        // cria todas bloqueio, reverso e +2;
        for(Grupo g : Grupo.values() ){ // cria 4x3 = 12 cartas;
            if(!(g.equals(Grupo.PR)) && !(g.equals(Grupo.VE))){
                for(Funcao f : Funcao.values() ){
                    if(f.equals(Funcao.BLOQUEIO)) {
                        baralho.add(new Carta(g, Valor.VALETE, f));
                        quant++;
                    }
                    else if(f.equals(Funcao.REVERSO)){
                        baralho.add(new Carta(g, Valor.DAMA, f));
                        quant++;
                    }
                    else if(f.equals(Funcao.MAIS_DOIS)){
                        baralho.add(new Carta(g, Valor.REI, f));
                        quant++;
                    }
                }
            }
        }

        // cria curingas e +4;
        for(Grupo g : Grupo.values() ){ // cria 4 cartas;
            if((g.equals(Grupo.PR)) || (g.equals(Grupo.VE))){
                for(Funcao f : Funcao.values() ){
                    if(f.equals(Funcao.MAIS_QUATRO)){
                        baralho.add(new Carta(g, Valor.EXTRA2, f));
                        quant++;
                    }
                    else if(f.equals(Funcao.CURINGA)){
                        baralho.add(new Carta(g, Valor.EXTRA1, f));
                        quant++;
                    }
                }
            }
        }
    }

    public ArrayList<Carta> getCartas(){ // retorna lista de cartas
        return baralho;
    }

    public int getQuant(){
        return baralho.size();
    }

    public void aumentaQuantCartas(){
        quant++;
    }

    public void diminuiQuantCartas(){
        quant--;
    }

    public String formatarNomeCarta(Carta c){ // método "base" para definir depois em Oficial e Tradicional;
        return "";
    }

    public void embaralhaBaralho(){
        Collections.shuffle(baralho);
    }

    public void addCarta(Carta c){ // adiciona carta no baralho
        baralho.add(c);
    }
}
