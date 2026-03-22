package triangulo;
import java.awt.Color;
import java.awt.Graphics;
/**
 * Escreva uma descrição da classe FiguraTriangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraTriangulos
{
    /**
     * Construtor para objetos da classe FiguraTriangulo
     */
    public static void desenharTriangulo(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor)
    {
       TrianguloGr tri = new TrianguloGr(x1, y1, x2, y2, x3, y3, cor, nome, esp);
       tri.desenharTriangulo(g);
    }
}
