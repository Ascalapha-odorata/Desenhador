package retangulo;
import java.awt.Color;
import java.awt.Graphics;
import reta.RetaGr;
import ponto.PontoGr;
/**
 * Escreva uma descrição da classe RetanguloGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class RetanguloGr extends Retangulo
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    Color corRetangulo = Color.BLACK;   // cor do Retangulo
    String nomeRetangulo = ""; // nome do Retangulo
    Color corNomeRetangulo  = Color.BLACK;
    int espRetangulo = 1; // espessura do Retangulo
    /**
     * Construtor para objetos da classe TrianguloGr
     */
        
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp)
    {
        // inicializa variáveis de instância
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo(nome);
        setEspRetangulo(esp);
    }
    
    public RetanguloGr(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color cor, String nome, int esp)
    {
        // inicializa variáveis de instância
        super (x1, y1, x2, y2, x3, y3, x4, y4);
        setCorRetangulo(cor);
        setNomeRetangulo(nome);
        setEspRetangulo(esp);
    }
    
    /**
     * RetanguloGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param x2 Um parâmetro
     * @param y2 Um parâmetro
     * @param cor Um parâmetro
     */
    public RetanguloGr(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color cor){
        super (x1, y1, x2, y2, x3, y3, x4, y4);
        setCorRetangulo(cor);
        setNomeRetangulo("");
    }
    
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor){
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
    }  
    
    /**
     * RetanguloGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param x2 Um parâmetro
     * @param y2 Um parâmetro
     * @param cor Um parâmetro
     * @param esp Um parâmetro
     */
    public RetanguloGr(int x1, int y1, int x2, int y2,  Color cor, int esp){
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
        setEspRetangulo(esp);
    }
    
    public RetanguloGr(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color cor, int esp){
        super (x1, y1, x2, y2, x3, y3, x4, y4);
        setCorRetangulo(cor);
        setNomeRetangulo("");
        setEspRetangulo(esp);
    } 
    
    /**
     * RetanguloGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param x2 Um parâmetro
     * @param y2 Um parâmetro
     */
    public RetanguloGr(int x1, int y1, int x2, int y2){
        super (x1, y1, x2, y2);
        setCorRetangulo(Color.black);
        setNomeRetangulo("");
    }
    
    public RetanguloGr(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        super (x1, y1, x2, y2, x3, y3, x4, y4);
        setCorRetangulo(Color.black);
        setNomeRetangulo("");
    }
    
    /**
     * RetanguloGr Construtor
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     * @param cor Um parâmetro
     */
    public RetanguloGr(PontoGr p1, PontoGr p2, Color cor){
        super(p1, p2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
    }  
    
    public RetanguloGr(PontoGr p1, PontoGr p2, PontoGr p3, PontoGr p4, Color cor){
        super(p1, p2, p3, p4);
        setCorRetangulo(cor);
        setNomeRetangulo("");
    }  
    
    /**
     * RetanguloGr Construtor
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     * @param cor Um parâmetro
     * @param str nome do retângulo
     */
    public RetanguloGr(PontoGr p1, PontoGr p2, Color cor, String str){
        super(p1, p2);
        setCorRetangulo(cor);
        setNomeRetangulo(str);
    }
    
    public RetanguloGr(PontoGr p1, PontoGr p2, PontoGr p3, PontoGr p4, Color cor, String str){
        super(p1, p2, p3, p4);
        setCorRetangulo(cor);
        setNomeRetangulo(str);
    }
    
    /**
     * Método setCorRetangulo
     *
     * @param cor Um parâmetro
     */
    public void setCorRetangulo(Color cor) {
        this.corRetangulo = cor;
    }
    
    /**
     * Método setNomeRetangulo
     *
     * @param str Um parâmetro
     */
    public void setNomeRetangulo(String str) {
        this.nomeRetangulo = str;
    }
    
    /**
     * Método setEspRetangulo
     *
     * @param esp Um parâmetro
     */
    public void setEspRetangulo(int esp) {
        this.espRetangulo = esp;
    }
    
    /**
     * Método getEspRetangulo
     *
     * @return O valor de retorno
     */
    public int getEspRetangulo() {
        return(this.espRetangulo);
    }
    
    /**
     * Método getCorRetangulo
     *
     * @return O valor de retorno
     */
    public Color getCorRetangulo() {
        return corRetangulo;
    }
    
    /**
     * Método getNomeRetangulo
     *
     * @return O valor de retorno
     */
    public String getNomeRetangulo() {
        return nomeRetangulo;
    }
    
    /**
     * Método getCorNomeRetangulo
     *
     * @return O valor de retorno
     */
    public Color getCorNomeRetangulo() {
        return corNomeRetangulo;
    }
    
    /**
     * Método setCorNomeRetangulo
     *
     * @param corNomeRetangulo Um parâmetro
     */
    public void setCorNomeRetangulo(Color corNomeRetangulo) {
        this.corNomeRetangulo = corNomeRetangulo;
    }
    
    /**
     * Método desenharRetangulo desenha o retângulo na tela
     *
     * @param g Um parâmetro
     */
    public void desenharRetangulo(Graphics g){

        PontoGr p1, p2, p3, p4;
        RetaGr r1, r2, r3, r4, r5, r6;
        
        g.setColor(getCorNomeRetangulo()); 
        g.drawString(getNomeRetangulo(), (int)getP1().getX() + getEspRetangulo(), (int)getP1().getY()); // desenha nome 
        
        p1 = new PontoGr( (int)Math.round(super.p1.getX()), (int)Math.round(super.p1.getY() ), getCorRetangulo(), getEspRetangulo());
        p2 = new PontoGr( (int)Math.round(super.p2.getX()), (int)Math.round(super.p2.getY() ), getCorRetangulo(), getEspRetangulo());
        p3 = new PontoGr( (int)Math.round(super.p3.getX()), (int)Math.round(super.p3.getY() ), getCorRetangulo(), getEspRetangulo());
        p4 = new PontoGr( (int)Math.round(super.p4.getX()), (int)Math.round(super.p4.getY() ), getCorRetangulo(), getEspRetangulo());
        
        r1 = new RetaGr(p1, p4, getCorRetangulo(), getNomeRetangulo(), getEspRetangulo());
        r2 = new RetaGr(p4, p2, getCorRetangulo(), getNomeRetangulo(), getEspRetangulo());
        r3 = new RetaGr(p2, p3, getCorRetangulo(), getNomeRetangulo(), getEspRetangulo());
        r4 = new RetaGr(p3, p1, getCorRetangulo(), getNomeRetangulo(), getEspRetangulo());
        
        r5 = new RetaGr(p1, p3, getCorRetangulo(), getNomeRetangulo(), getEspRetangulo());
        r6 = new RetaGr(p2, p4, getCorRetangulo(), getNomeRetangulo(), getEspRetangulo());
        
        r1.desenharReta(g);
        r2.desenharReta(g);
        r3.desenharReta(g);
        r4.desenharReta(g);
        
        
        r5.desenharReta(g);
        r6.desenharReta(g);
        
    }
    
} 
    
    