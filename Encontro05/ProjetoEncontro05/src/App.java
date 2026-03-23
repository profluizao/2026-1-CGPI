import javax.swing.JFrame;
import javax.swing.JPanel;

import Transformacoes.*;

public class App {
    public static void main(String[] args) throws Exception {
        mostrarTranslacao(false);
        mostrarRotacao(false);
        mostrarEscala(true);
    }

    private static void mostrarTranslacao(Boolean exibir){
        if (exibir){
            String titulo = "Translação em Computação Gráfica";
            TranslacaoExemplo painel = new TranslacaoExemplo();
            prepararPainel(titulo, painel);
        }
    }

    private static void mostrarRotacao(Boolean exibir){
        if (exibir){
            String titulo = "Rotação em Computação Gráfica";
            RotacaoExemplo painel = new RotacaoExemplo();
            prepararPainel(titulo, painel);
        }
    }

    private static void mostrarEscala(Boolean exibir){
        if (exibir){
            String titulo = "Rotação em Computação Gráfica";
            EscalaExemplo painel = new EscalaExemplo();
            prepararPainel(titulo, painel);
        }
    }

    private static void prepararPainel(String titulo, JPanel painel){
        JFrame frame = new JFrame(titulo);
        frame.add(painel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        
    }
}
