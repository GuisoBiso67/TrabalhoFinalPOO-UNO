package baralho;

import regras.*;

public class Carta {
    public Valor valor;
    public Grupo grupo;
    public Funcao funcao;
    public Baralho baralho;
    public Carta(Grupo g, Valor v, Funcao f){
        this.valor = v;
        this.grupo = g;
        this.funcao = f;
    }

    public Grupo getGrupo(){
        return grupo;
    }
    public Funcao getFuncao(){
        return funcao;
    }
    public Valor getValor(){
        return valor;
    }


    public void aplicarEfeito(Controle controle){
        this.getFuncao().executar(controle, this);
    }

}
