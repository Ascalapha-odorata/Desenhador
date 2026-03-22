package circulo;
import java.awt.Color;
import java.awt.Graphics;
/**
 * Escreva uma descrição da classe FiguraCirculos aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraCirculos
{
    /**
     * Método desenharCirculo
     *
     * @param g Um parâmetro
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param xr Um parâmetro
     * @param yr Um parâmetro
     * @param nome Um parâmetro
     * @param esp Um parâmetro
     * @param cor Um parâmetro
     */
    public static void desenharCirculo(Graphics g, int x1, int y1, int xr, int yr, String nome, int esp, Color cor){
       CirculoGr cir = new CirculoGr(x1, y1, xr, yr, cor, nome, esp);
       cir.desenharCirculo_MelhorDefinicao(g);
    }
    
    /**
     * Método desenharCirculo
     *
     * @param g Um parâmetro
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param raio Um parâmetro
     * @param nome Um parâmetro
     * @param esp Um parâmetro
     * @param cor Um parâmetro
     */
    public static void desenharCirculo(Graphics g, int x1, int y1, int raio, String nome, int esp, Color cor){
        CirculoGr cir = new CirculoGr(x1, y1, raio, cor, nome, esp);
        cir.desenharCirculo_MelhorDefinicao(g);
    }
    
}
