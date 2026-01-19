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

    },
    CURINGA{

    },
    MAIS_QUATRO{ // *tem a msm função do curinga

    },
}
