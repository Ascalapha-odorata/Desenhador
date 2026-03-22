package retangulo;
import ponto.Ponto;
import reta.Reta;
/**
 * Write a description of class Retangulo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Retangulo
{
    public Ponto p1, p2, p3, p4;//diagonais: reta(p1, p2), e reta (p3, p4)
    
    /**
     * Constructor for objects of class Retangulo
     */
    /**
     * Constroi um Retangulo com valores (int) de x1, y1, x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Retangulo(int x1, int y1, int x2, int y2)
    {
        // inicializa variáveis de instância
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(descobreP3()));
        setP4(new Ponto(descobreP4()));
    }

     /**
     * Constroi um Retangulo com valores (double) de x1, y1, x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param x3 coordenada x de p3
     * @param y3 coordenada y de p3
     */
    public Retangulo(double x1, double y1, double x2, double y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(descobreP3()));
        setP4(new Ponto(descobreP4()));
    }
    
    public Retangulo(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(x3, y3));
        setP4(new Ponto(x4, y4));
    }
    
    /**
     * Controi um Retangulo com valores de p1, p2 (externos)
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     */
    public Retangulo(Ponto p1, Ponto p2) {
        setP1(p1);
        setP2(p2);
        setP3(new Ponto(descobreP3()));
        setP4(new Ponto(descobreP4()));
    }
    
    public Retangulo(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        setP1(p1);
        setP2(p2);
        setP3(p3);
        setP4(p4);
    }
    
    /**
     * Constroi um Retangulo com dados de outro (externo)
     *
     * @param t triangulo externo
     */
    public Retangulo(Retangulo t){
        setP1(t.getP1());
        setP2(t.getP2());
        setP3(t.getP3());
        setP4(t.getP4());
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
     * Altera valor de p3 de acordo com o parametro
     *
     * @param p valor de p3 (externo)
     */
    public void setP3(Ponto p){
        this.p3 = p;
    }
    
    /**
     * Altera valor de p1 de acordo com o parametro
     *
     * @param p valor de p1 (externo)
     */
    public void setP4(Ponto p){
        this.p4 = p;
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
     * Retorna valor de p3
     *
     * @return valor de p3
     */
    public Ponto getP3(){
        return this.p3;
    }
    
    /**
     * Retorna valor de p4
     *
     * @return valor de p4
     */
    public Ponto getP4(){
        return this.p4;
    }
    
    /**
     * Método descobreP3
     *
     * @return O valor de retorno
     */
    public Ponto descobreP3(){
        Ponto p3 = new Ponto(this.p1.getX(), this.p2.getY());
        return p3;
    }
    
    /**
     * Método descobreP4
     *
     * @return O valor de retorno
     */
    public Ponto descobreP4(){
        Ponto p4 = new Ponto(this.p2.getX(), this.p1.getY());
        return p4;
    }
    
    /**
     * Method calcularDistancia calcula a distancia de um ponto qualquer ate a borda do retangulo
     *
     * @param p A parameter
     * @return The return value
     */
    public double calcularDistancia(Ponto p){
        double dist1, dist2, dist3, dist4, menor_dist;
        
        Reta r1, r2, r3, r4;
    
        r1 = new Reta(this.p1, this.p4);
        r2 = new Reta(this.p4, this.p2);
        r3 = new Reta(this.p2, this.p3);
        r4 = new Reta(this.p3, this.p1);
    
        double xp = p.getX();
        double yp = p.getY();
        
        //RETA 1
        double m = r1.calcularM();
        
        double a = this.p1.getX();
        double b = this.p1.getY();
        double c = this.p4.getX();
        double d = this.p4.getY();
        
        double x = b-d;
        double y = c-a;
        double u = (a*d)-(c*b);
        
        dist1 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        
        menor_dist = dist1;
        
        //RETA 2        
        m = r2.calcularM();
        a = this.p4.getX();
        b = this.p4.getY();
        c = this.p2.getX();
        d = this.p2.getY();
        
        x = b-d;
        y = c-a;
        u = (a*d)-(c*b);
        
        dist2 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        if(dist2 < menor_dist){
            menor_dist = dist2;
        }
        
        //RETA 3        
        m = r3.calcularM();
        a = this.p2.getX();
        b = this.p2.getY();
        c = this.p3.getX();
        d = this.p3.getY();
        
        x = b-d;
        y = c-a;
        u = (a*d)-(c*b);
        
        dist3 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        if(dist3 < menor_dist){
            menor_dist = dist3;
        }
        
        //RETA 4        
        m = r4.calcularM();
        a = this.p3.getX();
        b = this.p3.getY();
        c = this.p1.getX();
        d = this.p1.getY();
        
        x = b-d;
        y = c-a;
        u = (a*d)-(c*b);
        
        dist4 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        if(dist4 < menor_dist){
            menor_dist = dist4;
        }
                
        return menor_dist;
    }
    
    public Retangulo retanguloTranslacaoCentro(Ponto pg){
        Reta rd = new Reta(p1,p2);//reta diagonal
        Ponto pm1 = new Ponto(rd.calcularPontoMedio());//ponto medio da diagonal
        double vet1_x = pg.getX() - pm1.getX();
        double vet1_y = pg.getY() - pm1.getY();
        
        Reta rg = new Reta(p3,p4);//reta diagonal
        Ponto pm2 = new Ponto(rg.calcularPontoMedio());//ponto medio da diagonal
        double vet2_x = pg.getX() - pm2.getX();
        double vet2_y = pg.getY() - pm2.getY();
        
        double pa_x = p1.getX() + vet1_x;
        double pa_y = p1.getY() + vet1_y;
        double pb_x = p2.getX() + vet1_x;
        double pb_y = p2.getY() + vet1_y;
        double pc_x = p3.getX() + vet2_x;
        double pc_y = p3.getY() + vet2_y;
        double pd_x = p4.getX() + vet2_x;
        double pd_y = p4.getY() + vet2_y;
        
        Retangulo r_novo = new Retangulo(pa_x, pa_y, pb_x, pb_y, pc_x, pc_y, pd_x, pd_y);
        
        return r_novo;
    }
    
    public Retangulo rotacaoRetanguloPontoQualquer(Ponto q, int ang){
        double xa = q.getX() + ((p1.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p1.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double ya = q.getY() + ((p1.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p1.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        double xb = q.getX() + ((p2.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p2.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yb = q.getY() + ((p2.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p2.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        double xc = q.getX() + ((p3.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p3.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yc = q.getY() + ((p3.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p3.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        double xd = q.getX() + ((p4.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p4.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yd = q.getY() + ((p4.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p4.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        Retangulo ret = new Retangulo(xa, ya, xb, yb, xc, yc, xd, yd);
        return ret; 
    }
    
    public Retangulo escalaRetanguloPontoQualquer(Ponto q, double sx, double sy){
        double xa = (p1.getX() * sx) + (q.getX() * (1 - sx) );
        double ya = (p1.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xb = (p2.getX() * sx) + (q.getX() * (1 - sx) );
        double yb = (p2.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xc = (p3.getX() * sx) + (q.getX() * (1 - sx) );
        double yc = (p3.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xd = (p4.getX() * sx) + (q.getX() * (1 - sx) );
        double yd = (p4.getY() * sy) + (q.getY() * (1 - sy) );
        
        Retangulo ret = new Retangulo(xa, ya, xb, yb, xc, yc, xd, yd);
        return ret;
    }
    
    public Retangulo rotacaoRetanguloProprioEixo(int ang){

        double xm1 = (p1.getX() + p2.getX())/2;
        double ym1 = (p1.getY() + p2.getY())/2;
        
        double xm2 = (p3.getX() + p4.getX())/2;
        double ym2 = (p3.getY() + p4.getY())/2;
                
        Ponto orig = new Ponto(0, 0);
        //coloca coordenadas com o ponto medio no centro do plano
        
        Ponto p1_rot = new Ponto(p1.getX()- xm1, p1.getY() - ym1);
        Ponto p2_rot = new Ponto(p2.getX()- xm1, p2.getY() - ym1);
        
        Ponto p3_rot = new Ponto(p3.getX()- xm2, p3.getY() - ym2);
        Ponto p4_rot = new Ponto(p4.getX()- xm2, p4.getY() - ym2);
        
        //rotaciona ao redor do centro
        p1_rot = p1_rot.rotacaoPontoQualquer(orig, ang);
        p2_rot = p2_rot.rotacaoPontoQualquer(orig, ang);
        p3_rot = p3_rot.rotacaoPontoQualquer(orig, ang);
        p4_rot = p4_rot.rotacaoPontoQualquer(orig, ang);
        
        //restaura as coordenadas
        Ponto p1_f = new Ponto(p1_rot.getX()+ xm1, p1_rot.getY() + ym1);
        Ponto p2_f = new Ponto(p2_rot.getX()+ xm1, p2_rot.getY() + ym1);
        Ponto p3_f = new Ponto(p3_rot.getX()+ xm2, p3_rot.getY() + ym2);
        Ponto p4_f = new Ponto(p4_rot.getX()+ xm2, p4_rot.getY() + ym2);
        
        Retangulo ret = new Retangulo(p1_f, p2_f, p3_f, p4_f);
        return ret;
        
    }
}
