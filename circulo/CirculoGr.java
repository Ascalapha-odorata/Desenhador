package circulo;
import java.awt.Color;
import java.awt.Graphics;
import ponto.PontoGr;
/**
 * Escreva uma descrição da classe CirculoGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class CirculoGr extends Circulo
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    Color corCirculo = Color.BLACK;   // cor do circulo
    String nomeCirculo = ""; // nome do circulo
    Color corNomeCirculo  = Color.BLACK;
    int espCirculo = 1; // espessura do circulo

    /**
     * Construtor para objetos da classe CirculoGr
     */
    public CirculoGr(int x1, int y1, int xr, int yr, Color cor, String nome, int esp)
    {
        // inicializa variáveis de instância
        super (x1, y1, xr, yr);
        setCorCirculo(cor);
        setNomeCirculo(nome);
        setEspCirculo(esp);
    }
    
    /**
     * CirculoGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param raio Um parâmetro
     * @param cor Um parâmetro
     * @param nome Um parâmetro
     * @param esp Um parâmetro
     */
    public CirculoGr(int x1, int y1, int raio, Color cor, String nome, int esp)
    {
        // inicializa variáveis de instância
        super(x1, y1, raio);
        setCorCirculo(cor);
        setNomeCirculo(nome);
        setEspCirculo(esp);
    }
    
    /**
     * CirculoGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param xr Um parâmetro
     * @param yr Um parâmetro
     * @param cor Um parâmetro
     */
    public CirculoGr(int x1, int y1, int xr, int yr, Color cor){
        super (x1, y1, xr, yr);
        setCorCirculo(cor);
        setNomeCirculo("");
    }
    
    /**
     * CirculoGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param xr Um parâmetro
     * @param yr Um parâmetro
     * @param cor Um parâmetro
     * @param esp Um parâmetro
     */
    public CirculoGr(int x1, int y1, int xr, int yr, Color cor, int esp){
        super (x1, y1, xr, yr);
        setCorCirculo(cor);
        setNomeCirculo("");
        setEspCirculo(esp);
    }
    
    /**
     * CirculoGr Construtor
     *
     * @param x1 Um parâmetro
     * @param y1 Um parâmetro
     * @param xr Um parâmetro
     * @param yr Um parâmetro
     */
    public CirculoGr(int x1, int y1, int xr, int yr){
        super (x1, y1, xr, yr);
        setCorCirculo(Color.black);
        setNomeCirculo("");
    }
    
    /*
    public CirculoGr(PontoGr p1, int xr, int yr){
        super(p1, xr, yr);
        setCorCirculo(Color.black);
        setNomeCirculo("");
    } 
    
    public CirculoGr(PontoGr p1, int xr, int yr, Color cor){
        super(p1, xr, yr);
        setCorCirculo(cor);
        setNomeCirculo("");
    }
    
     public CirculoGr(PontoGr p1, int xr, int yr, Color cor, String str){
        super(p1, xr, yr);
        setCorCirculo(cor);
        setNomeCirculo(str);
    }
    */
   
    /**
     * Método setCorCirculo
     *
     * @param cor Um parâmetro
     */
    public void setCorCirculo(Color cor) {
        this.corCirculo = cor;
    }
    
    /**
     * Método setNomeCirculo
     *
     * @param str Um parâmetro
     */
    public void setNomeCirculo(String str) {
        this.nomeCirculo = str;
    }
    
     /**
      * Método setEspCirculo
      *
      * @param esp Um parâmetro
      */
     public void setEspCirculo(int esp) {
        this.espCirculo = esp;
    }
    
    /**
     * Método getEspCirculo
     *
     * @return O valor de retorno
     */
    public int getEspCirculo() {
        return(this.espCirculo);
    }
    
    /**
     * Método getCorCirculo
     *
     * @return O valor de retorno
     */
    public Color getCorCirculo() {
        return corCirculo;
    }
    
    /**
     * Método getNomeCirculo
     *
     * @return O valor de retorno
     */
    public String getNomeCirculo() {
        return nomeCirculo;
    }
    
    /**
     * Método getCorNomeCirculo
     *
     * @return O valor de retorno
     */
    public Color getCorNomeCirculo() {
        return corNomeCirculo;
    }
    
    /**
     * Método setCorNomeCirculo
     *
     * @param corNomeCirculo Um parâmetro
     */
    public void setCorNomeCirculo(Color corNomeCirculo) {
        this.corNomeCirculo = corNomeCirculo;
    }
    
    /**
     * Method desenharCirculoQuadraticas desenha o circulo a partir das equaçoes quadraticas
     *
     * @param g A parameter
     */
    public void desenharCirculoParametricas(Graphics g){
        // desenha nome do ponto
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), (int)getCentro().getX() + getEspCirculo(), (int)getCentro().getY());
        PontoGr ponto;
        int x, y, gr;
        for(gr = 0; gr < 360; gr++){
            x = (int)Math.round(quadX(gr));
            y = (int)Math.round(quadY(gr));
            //if(x < 700 && y < 600){ //dimensoes da tela
                // Define ponto grafico
                ponto = new PontoGr(x, y, getCorCirculo(), getEspCirculo());
                // Desenha ponto grafico
                ponto.desenharPonto(g);
            //}
        }
    }
    
    /**
     * Method desenharCirculoReduzida_v1 desenha o circulo a partir da equaçao reduzida, em que apenas o x varia, e sao encontrados dois valores de y
     *
     * @param g A parameter
     */
    public void desenharCirculoReduzida_v1(Graphics g){
        // desenha nome do ponto
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), (int)getCentro().getX() + getEspCirculo(), (int)getCentro().getY());
        PontoGr ponto;
        int x, y1, y2;
        int valorX_inicio = (int)Math.round(Math.abs((getCentro().getX() - super.raio)));
        int valorX_final = (int)Math.round(Math.abs(getCentro().getX() + super.raio));
        for(x = valorX_inicio; x <= valorX_final; x++){
            
            y1 = (int)Math.round(achaY_pos(x));
                // Define ponto grafico
                ponto = new PontoGr(x, y1, getCorCirculo(), getEspCirculo());
                // Desenha ponto grafico
                ponto.desenharPonto(g);
                
            y2 = (int)Math.round(achaY_neg(x));
                // Define ponto grafico
                ponto = new PontoGr(x, y2, getCorCirculo(), getEspCirculo());
                // Desenha ponto grafico
                ponto.desenharPonto(g);
            
        }
    }
    
        public void desenharCirculoReduzida_v2(Graphics g){
        // desenha nome do ponto
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), (int)getCentro().getX() + getEspCirculo(), (int)getCentro().getY());
        PontoGr ponto;
        double theta = 45;
        int x1, x2, y1, xc, yc;
        
        xc = (int) Math.round(super.centro.getX());
        yc = (int) Math.round(super.centro.getY());
        
        int valorX_inicio = xc;
        int valorX_final = (int) Math.round(super.centro.getX() + (Math.cos(Math.toRadians(theta)) * super.raio));
        //int valorX_final = (int) Math.round(super.raio/(Math.sqrt(2)));
        
        for(x1 = valorX_inicio; x1 <= valorX_final ; x1++){
            y1 = (int)Math.round(achaY_pos(x1));
            //y2 = (int)Math.round(achaY_neg(x1));
            //x2 = (int)Math.round(achaX_neg(y1));
            
            
            //1
            ponto = new PontoGr(x1, y1, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //2
            ponto = new PontoGr((2 * xc) - x1, y1, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //3
            ponto = new PontoGr(x1, (2 * yc) - y1, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //4
            ponto = new PontoGr((2 * xc) - x1, (2*yc) - y1, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //5
            ponto = new PontoGr(y1 + xc - yc, x1 + yc - xc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //6
            ponto = new PontoGr(-y1 + xc + yc, -x1 + xc + yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //7
            ponto = new PontoGr(yc - y1 + xc, xc - x1 + yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            //8
            //ponto = new PontoGr(y1 + xc - yc, x1 + yc - xc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            
        }
    }
    
    /**
     * Método desenharCirculo_MelhorDefinicao desenha o círculo por Bresenham
     *
     * @param g Um parâmetro
     */
    public void desenharCirculo_MelhorDefinicao(Graphics g){
        // por Bresenham
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), (int)getCentro().getX() + getEspCirculo(), (int)getCentro().getY());
        PontoGr ponto;
        
        double d = 3 - (2 * super.raio);
        int x, y, xc, yc;
        
        xc = (int) Math.round(super.centro.getX());
        yc = (int) Math.round(super.centro.getY());

        x = 0;
        y = (int) Math.round(super.raio);
        while(y>=x){ //x <= super.raio/Math.sqrt(2)  
            if(d>0){
                y = y - 1;
                d = d + (4*(x-y))+10;
            }else{
                d = d + (4*x)+6;
            }
            x = x + 1;
            ponto = new PontoGr(x+xc, y+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
        
            ponto = new PontoGr(-x+xc, y+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            
            ponto = new PontoGr(x+xc, -y+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
        
            ponto = new PontoGr(-x+xc, -y+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            
            ponto = new PontoGr(y+xc, x+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
        
            ponto = new PontoGr(-y+xc, x+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            
            ponto = new PontoGr(y+xc, -x+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
            
            ponto = new PontoGr(-y+xc, -x+yc, getCorCirculo(), getEspCirculo());
            ponto.desenharPonto(g);
        } 
    }    
    
}
