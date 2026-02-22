package baralho;

import regras.Controle;

public enum Funcao {
    NUMERICA{
        @Override
        public void executar(Controle controle, Carta carta){
            controle.avancarTurno(1);
        }
    }, // para cartas simples;
    BLOQUEIO{
        @Override
        public void executar(Controle controle, Carta carta){
            controle.avancarTurno(2);
            System.out.println("O próximo jogador foi bloqueado!");
        }
    },
    REVERSO{
        @Override
        public void executar(Controle controle, Carta carta){
            if (controle.getJogadores().size() == 2) {
                controle.avancarTurno(2); // se tem só dois jogadores, ele volta para o jogador que jogou o reverso;
            } else {
                controle.inverteDirecao();
                controle.avancarTurno(1);
            }

            System.out.println("Sentido do jogo foi invertido!");
        }
    },
    MAIS_DOIS{
        @Override
        public void executar(Controle controle, Carta carta){
            int proximo = controle.getProximoIndice(1);
            controle.compraCartaSeguro(controle.getJogadores().get(proximo));
            controle.compraCartaSeguro(controle.getJogadores().get(proximo));
            System.out.println(controle.getJogadores().get(proximo).getNome() + " compra duas cartas e perde a vez!");
            controle.avancarTurno(2);;
        }
    },
    CURINGA{
        @Override
        public void executar(Controle controle, Carta carta){
            controle.avancarTurno(1);
        }
    },
    MAIS_QUATRO{ // *tem a msm função do curinga
        @Override
        public void executar(Controle controle, Carta carta){
            int proximo = controle.getProximoIndice(1);
            controle.compraCartaSeguro(controle.getJogadores().get(proximo));
            controle.compraCartaSeguro(controle.getJogadores().get(proximo));
            controle.compraCartaSeguro(controle.getJogadores().get(proximo));
            controle.compraCartaSeguro(controle.getJogadores().get(proximo));
            System.out.println(controle.getJogadores().get(proximo).getNome() + " compra quatro cartas e perde a vez!");
            controle.avancarTurno(2);
        }
    };
    public abstract void executar(Controle controle, Carta carta);
}
