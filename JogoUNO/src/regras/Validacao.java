package regras;

import baralho.*;

// classe que valida a carta jogada;
public class Validacao {
    public boolean validarCarta(Carta cAtual, Carta cAnterior, Grupo g){
        if(g != null){ // parte usada quando é jogado um curinga ou +4, pq aí só vale carta da cor escolhida;
            return cAtual.getGrupo().equals(g);
        }else{
            if (cAtual.getGrupo().equals(Grupo.VE) || cAtual.getGrupo().equals(Grupo.PR)) return true; // curinga e +4 sempre podem ser jogados a qualquer momento;
            else{
                return cAtual.getGrupo() == cAnterior.getGrupo() || cAnterior.getValor() == cAtual.getValor();
            }
        }
    }
}
