package baralho;

import regras.Controle;

public enum Funcao {
    NUMERICA{
        public void executar(Controle controle, Carta carta){

        }
    }, // para cartas simples;
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

    }
}
