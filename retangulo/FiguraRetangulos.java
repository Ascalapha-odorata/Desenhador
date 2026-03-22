package retangulo;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Write a description of class FiguraRetangulos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FiguraRetangulos
{
        /**
     * Construtor para objetos da classe FiguraTriangulo
     */
    public static void desenharRetangulo(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor)
    {
       RetanguloGr ret = new RetanguloGr(x1, y1, x2, y2, cor, nome, esp);
       ret.desenharRetangulo(g);
    }
    
    public static void desenharRetangulo(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, String nome, int esp, Color cor)
    {
       RetanguloGr ret = new RetanguloGr(x1, y1, x2, y2, x3, y3, x4, y4, cor, nome, esp);
       ret.desenharRetangulo(g);
    }
}
