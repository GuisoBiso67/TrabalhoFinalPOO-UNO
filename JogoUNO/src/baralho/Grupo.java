package baralho;

// função desse enum é diferenciar as cores/naipe, dependendo da escolha do usuario;
public enum Grupo {
    // copas: red, ouros: yellow, paus: green, espadas: blue;
    // PR = curinga preto, VE = curinga vermelho;
    CR,
    OY,
    PG,
    EB,
    PR,
    VE;

    // método usada quando o jogador lança curinga ou +4, para exibir e escolher a cor para continuar;
    public String formatar(int tipo) {
        if (tipo == 1) {
            return switch (this) {
                case CR -> "vermelho";
                case OY -> "amarelo";
                case PG -> "verde";
                case EB -> "azul";
                case PR, VE -> "";
            };
        } else {
            return switch (this) {
                case CR -> "copas";
                case OY -> "ouros";
                case PG -> "paus";
                case EB -> "espada";
                case PR, VE -> "";
            };
        }
    }
}
