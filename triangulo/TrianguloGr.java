package triangulo;
import java.awt.Color;
import java.awt.Graphics;
import reta.RetaGr;
import ponto.PontoGr;
/**
 * Escreva uma descrição da classe TrianguloGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class TrianguloGr extends Triangulo
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    Color corTriangulo = Color.BLACK;   // cor do Triangulo
    String nomeTriangulo = ""; // nome do Triangulo
    Color corNomeTriangulo  = Color.BLACK;
    int espTriangulo = 1; // espessura do Triangulo
    /**
     * Construtor para objetos da classe TrianguloGr
     */
        
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor, String nome, int esp)
    {
        // inicializa variáveis de instância
        super (x1, y1, x2, y2, x3, y3);
        setCorTriangulo(cor);
        setNomeTriangulo(nome);
        setEspTriangulo(esp);
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param x2 Um parâmetro
     * @param y2 Um parâmetro
     * @param x3 Um parâmetro
     * @param y3 Um parâmetro
     * @param cor Um parâmetro
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor){
        super (x1, y1, x2, y2, x3, y3);
        setCorTriangulo(cor);
        setNomeTriangulo("");
    }   
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param x2 Um parâmetro
     * @param y2 Um parâmetro
     * @param x3 Um parâmetro
     * @param y3 Um parâmetro
     * @param cor Um parâmetro
     * @param esp Um parâmetro
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor, int esp){
        super (x1, y1, x2, y2, x3, y3);
        setCorTriangulo(cor);
        setNomeTriangulo("");
        setEspTriangulo(esp);
    } 
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param x2 Um parâmetro
     * @param y2 Um parâmetro
     * @param x3 Um parâmetro
     * @param y3 Um parâmetro
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3){
        super (x1, y1, x2, y2, x3, y3);
        setCorTriangulo(Color.black);
        setNomeTriangulo("");
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     * @param p3 Um parâmetro
     * @param cor Um parâmetro
     */
    public TrianguloGr(PontoGr p1, PontoGr p2, PontoGr p3, Color cor){
        super(p1, p2, p3);
        setCorTriangulo(cor);
        setNomeTriangulo("");
    }    
    
    /**
     * TrianguloGr Construtor
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     * @param p3 Um parâmetro
     * @param cor Um parâmetro
     * @param str Um parâmetro
     */
    public TrianguloGr(PontoGr p1, PontoGr p2, PontoGr p3, Color cor, String str){
        super(p1, p2, p3);
        setCorTriangulo(cor);
        setNomeTriangulo(str);
    }
    
    /**
     * Método setCorTriangulo
     *
     * @param cor Um parâmetro
     */
    public void setCorTriangulo(Color cor) {
        this.corTriangulo = cor;
    }
    
    /**
     * Método setNomeTriangulo
     *
     * @param str Um parâmetro
     */
    public void setNomeTriangulo(String str) {
        this.nomeTriangulo = str;
    }
    
    /**
     * Método setEspTriangulo
     *
     * @param esp Um parâmetro
     */
    public void setEspTriangulo(int esp) {
        this.espTriangulo = esp;
    }
    
    /**
     * Método getEspTriangulo
     *
     * @return O valor de retorno
     */
    public int getEspTriangulo() {
        return(this.espTriangulo);
    }
    
    /**
     * Método getCorTriangulo
     *
     * @return O valor de retorno
     */
    public Color getCorTriangulo() {
        return corTriangulo;
    }
    
    /**
     * Método getNomeTriangulo
     *
     * @return O valor de retorno
     */
    public String getNomeTriangulo() {
        return nomeTriangulo;
    }
    
    /**
     * Método getCorNomeTriangulo
     *
     * @return O valor de retorno
     */
    public Color getCorNomeTriangulo() {
        return corNomeTriangulo;
    }
    
    /**
     * Método setCorNomeTriangulo
     *
     * @param corNomeTriangulo Um parâmetro
     */
    public void setCorNomeTriangulo(Color corNomeTriangulo) {
        this.corNomeTriangulo = corNomeTriangulo;
    }
    
    /**
     * Método desenharTriangulo desenha o triângulo na tela
     *
     * @param g Um parâmetro
     */
    public void desenharTriangulo(Graphics g){
        
        g.setColor(getCorNomeTriangulo()); 
        g.drawString(getNomeTriangulo(), (int)getP1().getX() + getEspTriangulo(), (int)getP1().getY()); // desenha nome do triangulo
        
        PontoGr a, b, c;
        a = new PontoGr( (int)Math.round(super.p1.getX()), (int)Math.round(super.p1.getY() ), getCorTriangulo(), getEspTriangulo());
        b = new PontoGr( (int)Math.round(super.p2.getX()), (int)Math.round(super.p2.getY() ), getCorTriangulo(), getEspTriangulo());
        c = new PontoGr( (int)Math.round(super.p3.getX()), (int)Math.round(super.p3.getY() ), getCorTriangulo(), getEspTriangulo());
        
        RetaGr r1, r2, r3;
        r1 = new RetaGr(a, b, getCorTriangulo(), getNomeTriangulo(), getEspTriangulo());
        r2 = new RetaGr(a, c, getCorTriangulo(), getNomeTriangulo(), getEspTriangulo());
        r3 = new RetaGr(b, c, getCorTriangulo(), getNomeTriangulo(), getEspTriangulo());
        
        r1.desenharReta(g);
        r2.desenharReta(g);
        r3.desenharReta(g);
        
    }
}
