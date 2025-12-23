package baralho;

public class Carta {
    public Valor valor;
    public Grupo grupo;
    public Funcao funcao;
    public Carta(Grupo g, Valor v, Funcao f){
        this.valor = v;
        this.grupo = g;
        this.funcao = f;
    }
}
