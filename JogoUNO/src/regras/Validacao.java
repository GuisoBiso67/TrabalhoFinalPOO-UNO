package regras;

import baralho.*;

public class Validacao {
    public boolean validarCarta(Carta cAtual, Carta cAnterior, Grupo g){
        if(g != null){
            return cAtual.getGrupo().equals(g);
        }else{
            if (cAtual.getGrupo().equals(Grupo.VE) || cAtual.getGrupo().equals(Grupo.PR)) return true; // curinga e +4 sempre podem ser jogados a qualquer momento;
            else{
                return cAtual.getGrupo() == cAnterior.getGrupo() || cAnterior.getValor() == cAtual.getValor();
            }
        }
    }
}
