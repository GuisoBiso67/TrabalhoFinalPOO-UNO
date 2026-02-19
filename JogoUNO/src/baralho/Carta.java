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

    public String formatarNomeValor(Valor v, int tipoBaralho){
        if(tipoBaralho == 1){
            return switch (valor) {
                case AS -> "um";
                case VALETE -> "bloqueio";
                case DAMA -> "reverso";
                case REI -> "+2";
                case EXTRA1 -> "curinga";
                case EXTRA2 -> "+4";
                default -> valor.toString().toLowerCase();
            };
        }else{
            return valor.toString();
        }
    }

    public String formatarNomeGrupo(Grupo g, int tipoBaralho){
        if(tipoBaralho == 1){
            return switch (grupo) {
                case CR -> "vermelho";
                case OY -> "amarelo";
                case PG -> "verde";
                case EB -> "azul";
                case PR, VE -> "preto";
            };
        }else{
            return switch (grupo) {
                case CR -> "de copas";
                case OY -> "de ouros";
                case PG -> "de paus";
                case EB -> "de espada";
                case PR -> "preto";
                case VE -> "vermelho";
            };
        }
    }

    public String formatarNomeGeral(int tipoBaralho){
        if(tipoBaralho == 1){
            return this.formatarNomeValor(valor, tipoBaralho) + " " + this.formatarNomeGrupo(grupo, tipoBaralho);
        }else{
            return this.formatarNomeValor(valor, tipoBaralho) + " " + this.formatarNomeGrupo(grupo, tipoBaralho) + " " + this.getFuncao().toString().toLowerCase();
        }
    }

    public void aplicarEfeito(Controle controle){
        this.getFuncao().executar(controle, this);
    }

}
