package baralho;

public enum Funcao {
    NUMERICA, // para cartas simples;
    BLOQUEIO{

    },
    REVERSO{
        //@Override
        boolean reversoAtivado = false;
        public boolean reverso() {
            reversoAtivado = !reversoAtivado; // inverte estado sempre que jogador;
            return reversoAtivado;
        }
    },
    MAIS_DOIS{
        public void maisDois() {

        }
    },
    CURINGA{

    },
    MAIS_QUATRO{ // *tem a msm função do curinga

    }, f,
}
