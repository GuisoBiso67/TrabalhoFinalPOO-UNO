package regras;

import baralho.*;

public class Validacao {
    public boolean validarCarta(Carta cAtual, Carta cAnterior){
        if (cAtual.getGrupo().equals(Grupo.VE) || cAtual.getGrupo().equals(Grupo.PR)) return true; // curinga e +4 sempre podem ser jogador;
        else{
            return cAtual.getGrupo() == cAnterior.getGrupo() || cAnterior.getValor() == cAtual.getValor();
        }

    }
}
