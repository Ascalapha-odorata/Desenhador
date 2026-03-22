package reta;
import ponto.Ponto;
/**
 * Reta matematica.
 *
 * @author Julio
 * @version 12/08/2020
 */
public class Reta {
    // Atributos da reta
    public Ponto p1, p2;

    /**
     * Constroi uma reta com valores (int) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Reta(int x1, int y1, int x2, int y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
    }
    
    /**
     * Constroi uma reta com valores (double) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Reta(double x1, double y1, double x2, double y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
    }
    
    /**
     * Controi uma reta com valores de p1 e p2 (externos)
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     */
    public Reta(Ponto p1, Ponto p2) {
        setP1(p1);
        setP2(p2);
    }
    
    /**
     * Constroi uma reta com dados de outra (externa)
     *
     * @param r reta externa
     */
    public Reta (Reta r){
        setP1(r.getP1());
        setP2(r.getP2());
    }
    
    /**
     * Altera valor de p1 de acordo com o parametro
     *
     * @param p valor de p1 (externo)
     */
    public void setP1(Ponto p){
        this.p1 = p;
    }
    
    /**
     * Altera valor de p2 de acordo com o parametro
     *
     * @param p valor de p2 (externo)
     */
    public void setP2(Ponto p){
        this.p2 = p;
    }
    
    /**
     * Retorna valor de p1
     *
     * @return valor de p1
     */
    public Ponto getP1(){
        return this.p1;
    }
    
    /**
     * Retorna p2
     *
     * @return valor de p2
     */
    public Ponto getP2(){
        return this.p2;
    }
    
    /**
     * Calcula o valor de m (da equacao reduzida de reta: y = mx + b)
     *
     * @return valor de m
     */
    public double calcularM(){
        // m = (y2-y1)/(x2-x1)
        double m = (getP2().getY() - getP1().getY())/(getP2().getX() - getP1().getX());
        return m;
    }
    
     /**
      * Calcula o valor de b (y = mx + b)
      *
      * @return valor de b
      */
     public double calcularB(){
        //b = y1 - mx1
        double b = getP1().getY() - calcularM()*getP1().getX();
        return b;
    }
    
    public boolean pertence(double x, double y){
        boolean pertence = false;
        double m = calcularM();
        double b = calcularB();
        if( m*x+b == y){ //se y = mx+b
            pertence  = true;
        }
        return pertence;
    }
    
    public boolean pertence(int x, int y){
        boolean pertence = false;
        double m = calcularM();
        double b = calcularB();
        if( m*x+b == y){ //se y = mx+b
            pertence  = true;
        }
        return pertence;
    }
    
    public boolean pertence(Ponto p_qualquer){
        boolean pertence = false;
        double m = calcularM();
        double b = calcularB();
        double x = p_qualquer.getX();
        double y = p_qualquer.getY();
        if( m*x+b == y){ //se y = mx+b
            pertence  = true;
        }
        return pertence;
    }
    
    public double calcularDistancia(Ponto p){ //ax + by + c = 0       y - y0 = m(x - x0)
        double dist;
        double m = calcularM();
        double xp = p.getX();
        double yp = p.getY();
        
        double a = this.p1.getX();
        double b = this.p1.getY();
        double c = this.p2.getX();
        double d = this.p2.getY();
        
        double x = b-d;
        double y = c-a;
        double u = (a*d)-(c*b);
        
        dist = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        return dist;
    }
    
    public Ponto calcularPontoMedio(){
        double xm = (p1.getX() + p2.getX())/2;
        double ym = (p1.getY() + p2.getY())/2;
        Ponto pm = new Ponto(xm, ym);
        return pm;
    }
    
    public double calcularPontoMedio_XM(){
        double xm = (p1.getX() + p2.getX())/2;
        return xm;
    }
    
    public double calcularPontoMedio_YM(){
        double ym = (p1.getY() + p2.getY())/2;
        return ym;
    }
    
    /**
     * Method translacao calcula a nova reta de acordo com o ponto pg, que eh o novo ponto medio da reta
     *
     * @param pg A parameter
     * @return The return value
     */
    public Reta translacaoPontoMedio(Ponto pg){ 
        Ponto pm1 = new Ponto(calcularPontoMedio());
        double vet_x = pg.getX() - pm1.getX();
        double vet_y = pg.getY() - pm1.getY();
        
        double pa_x = p1.getX() + vet_x;
        double pa_y = p1.getY() + vet_y;
        double pb_x = p2.getX() + vet_x;
        double pb_y = p2.getY() + vet_y;
        
        Reta r_nova = new Reta(pa_x, pa_y, pb_x, pb_y);
        
        return r_nova;
    }
    
    public Reta rotacaoPontoQualquer(Ponto q, int ang){
        double xm = q.getX() + ((p1.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p1.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double ym = q.getY() + ((p1.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p1.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        Ponto PA = new Ponto(xm, ym);
        
        double xn = q.getX() + ((p2.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p2.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yn = q.getY() + ((p2.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p2.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        Ponto PB = new Ponto(xn, yn);
        
        Reta nova = new Reta(PA, PB);
        return nova;
    }
    
    public Reta rotacaoRetaProprioEixo(int ang){
        double xm =calcularPontoMedio_XM();
        double ym =calcularPontoMedio_YM();
        Ponto orig = new Ponto(0, 0);
        //coloca coordenadas com o ponto medio no centro do plano
        Ponto p1_rot = new Ponto(p1.getX()- xm, p1.getY() - ym);
        Ponto p2_rot = new Ponto(p2.getX()- xm, p2.getY() - ym);
        //rotaciona ao redor do centro
        p1_rot = p1_rot.rotacaoPontoQualquer(orig, ang);
        p2_rot = p2_rot.rotacaoPontoQualquer(orig, ang);
        //restaura as coordenadas
        Ponto p1_f = new Ponto(p1_rot.getX()+ xm, p1_rot.getY() + ym);
        Ponto p2_f = new Ponto(p2_rot.getX()+ xm, p2_rot.getY() + ym);
        
        Reta ret = new Reta(p1_f, p2_f);
        return ret;
    }
    
    public Reta escalaRetaPontoQualquer(Ponto q, double sx, double sy){
        //double xm =calcularPontoMedio_XM();
        //double ym =calcularPontoMedio_YM();
        
        //Ponto orig = new Ponto(0, 0);
        //coloca coordenadas com o ponto medio no centro do plano
        //Ponto p1_cen = new Ponto(p1.getX()- xm, p1.getY() - ym);
        //Ponto p2_cen = new Ponto(p2.getX()- xm, p2.getY() - ym);
        
        //double xa = (p1_cen.getX() * sx) + (q.getX() * (1 - sx) );
        //double ya = (p1_cen.getY() * sy) + (q.getY() * (1 - sy) );
        
        //double xb = (p2_cen.getX() * sx) + (q.getX() * (1 - sx) );
        //double yb = (p2_cen.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xa = (p1.getX() * sx) + (q.getX() * (1 - sx) );
        double ya = (p1.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xb = (p2.getX() * sx) + (q.getX() * (1 - sx) );
        double yb = (p2.getY() * sy) + (q.getY() * (1 - sy) );
        
        //Ponto pa = new Ponto(xa + xm, ya + ym);
        //Ponto pb = new Ponto(xb + xm, yb + ym);
        Ponto pa = new Ponto(xa , ya );
        Ponto pb = new Ponto(xb , yb );
        
        Reta reta = new Reta(pa, pb);
        return reta;
    }
    
    public Reta escalaRetaProprioEixo(double sx, double sy){
        double xm =calcularPontoMedio_XM();
        double ym =calcularPontoMedio_YM();
        Ponto orig = new Ponto(0, 0);
        //coloca coordenadas com o ponto medio no centro do plano
        Ponto p1_cen = new Ponto(p1.getX()- xm, p1.getY() - ym);
        Ponto p2_cen = new Ponto(p2.getX()- xm, p2.getY() - ym);
        
        double xa = (p1_cen.getX() * sx);
        double ya = (p1_cen.getY() * sy);
        
        double xb = (p2_cen.getX() * sx);
        double yb = (p2_cen.getY() * sy);
        
        Ponto pa = new Ponto(xa + xm, ya + ym);
        Ponto pb = new Ponto(xb + xm, yb + ym);
        
        Reta reta = new Reta(pa, pb);
        return reta;
    }
    
    /**
     * Imprime a equacao de reta no formato (y = mx + b)
     *
     * @return string que representa a reta
     */
    public String toString(){
        String s = "P1: " + getP1().toString() + " P2: " + getP2().toString();
        s = s + "\nEq. da reta: y = " + calcularM() + "*x + " + calcularB();
        return s;
    }
   
}
