package triangulo;
import ponto.Ponto;
import reta.Reta;
/**
 * Escreva uma descrição da classe Triangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Triangulo
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    public Ponto p1, p2, p3;
    /**
     * Constroi um triangulo com valores (int) de x1, y1, x2, y2, x3, y3
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param x3 coordenada x de p3
     * @param y3 coordenada y de p3
     */
    public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        // inicializa variáveis de instância
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(x3, y3));
    }

     /**
     * Constroi um triangulo com valores (double) de x1, y1, x2, y2, x3, y3
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param x3 coordenada x de p3
     * @param y3 coordenada y de p3
     */
    public Triangulo(double x1, double y1, double x2, double y2, double x3, double y3) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(x3, y3));
    }
    
    /**
     * Controi um triangulo com valores de p1, p2 e p3(externos)
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     * @param p3 Um parâmetro
     */
    public Triangulo(Ponto p1, Ponto p2, Ponto p3) {
        setP1(p1);
        setP2(p2);
        setP3(p3);
    }
    
    /**
     * Constroi um triangulo com dados de outro (externo)
     *
     * @param t triangulo externo
     */
    public Triangulo(Triangulo t){
        setP1(t.getP1());
        setP2(t.getP2());
        setP3(t.getP3());
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
     * Retorna p3
     *
     * @return valor de p3
     */
    public Ponto getP3(){
        return this.p3;
    }
    
    public double calcularDistancia(Ponto p){
        double dist1, dist2, dist3, menor_dist;
        
        Reta r1, r2, r3;
        r1 = new Reta(this.p1, this.p2);
        r2 = new Reta(this.p2, this.p3);
        r3 = new Reta(this.p1, this.p3);
        
        double xp = p.getX();
        double yp = p.getY();
        
        //RETA 1
        double m = r1.calcularM();
        
        double a = this.p1.getX();
        double b = this.p1.getY();
        double c = this.p2.getX();
        double d = this.p2.getY();
        
        double x = b-d;
        double y = c-a;
        double u = (a*d)-(c*b);
        
        dist1 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        
        menor_dist = dist1;
        
        //RETA 2        
        m = r2.calcularM();
        a = this.p2.getX();
        b = this.p2.getY();
        c = this.p3.getX();
        d = this.p3.getY();
        
        x = b-d;
        y = c-a;
        u = (a*d)-(c*b);
        
        dist2 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        if(dist2 < menor_dist){
            menor_dist = dist2;
        }
        
        //RETA 3        
        m = r3.calcularM();
        a = this.p1.getX();
        b = this.p1.getY();
        c = this.p3.getX();
        d = this.p3.getY();
        
        x = b-d;
        y = c-a;
        u = (a*d)-(c*b);
        
        dist3 = Math.abs((x*xp)+(y*yp)+u)/Math.sqrt((x*x + y*y));
        if(dist3 < menor_dist){
            menor_dist = dist3;
        }
                
        return menor_dist;
    }
    
    public Ponto calcularBaricentro(){
        double bx = (p1.getX() + p2.getX() + p3.getX())/3;
        double by = (p1.getY() + p2.getY() + p3.getY())/3;
        Ponto pb = new Ponto(bx, by);
        return pb;
    }
    
    public Triangulo translacaoBaricentro(Ponto pg){
        Ponto bar = new Ponto(calcularBaricentro());
        double vet_x = pg.getX() - bar.getX();
        double vet_y = pg.getY() - bar.getY();
        
        double pa_x = p1.getX() + vet_x;
        double pa_y = p1.getY() + vet_y;
        
        double pb_x = p2.getX() + vet_x;
        double pb_y = p2.getY() + vet_y;
        
        double pc_x = p3.getX() + vet_x;
        double pc_y = p3.getY() + vet_y;
        
        Triangulo tri = new Triangulo(pa_x,  pa_y, pb_x,  pb_y, pc_x,  pc_y);
        return tri;
    }
    
    public Triangulo rotacaoTrianguloPontoQualquer(Ponto q, int ang){
        double xa = q.getX() + ((p1.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p1.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double ya = q.getY() + ((p1.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p1.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        double xb = q.getX() + ((p2.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p2.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yb = q.getY() + ((p2.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p2.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        double xc = q.getX() + ((p3.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((p3.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yc = q.getY() + ((p3.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((p3.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        
        Triangulo tri = new Triangulo(xa, ya, xb, yb, xc, yc);
        return tri;        
    }
    
    public Triangulo rotacaoTrianguloProprioEixo(int ang){
        Ponto bar = new Ponto(calcularBaricentro());
        Ponto orig = new Ponto(0, 0);
        //centro vai para ponto (0, 0)
        Ponto p1_rot = new Ponto(p1.getX()- bar.getX(), p1.getY() - bar.getY());
        Ponto p2_rot = new Ponto(p2.getX()- bar.getX(), p2.getY() - bar.getY());
        Ponto p3_rot = new Ponto(p3.getX()- bar.getX(), p3.getY() - bar.getY());
        //rotaciona ao redor do centro
        p1_rot = p1_rot.rotacaoPontoQualquer(orig, ang);
        p2_rot = p2_rot.rotacaoPontoQualquer(orig, ang);
        p3_rot = p3_rot.rotacaoPontoQualquer(orig, ang);
        //volta para local original
        Ponto p1_f = new Ponto(p1_rot.getX()+ bar.getX(), p1_rot.getY() + bar.getY());
        Ponto p2_f = new Ponto(p2_rot.getX()+ bar.getX(), p2_rot.getY() + bar.getY());
        Ponto p3_f = new Ponto(p3_rot.getX()+ bar.getX(), p3_rot.getY() + bar.getY());
        
        Triangulo tri = new Triangulo(p1_f, p2_f, p3_f);
        return tri;
    }
    
    public Triangulo escalaTrianguloProprioEixo(double sx, double sy){
        Ponto bar = new Ponto(calcularBaricentro());
        Ponto orig = new Ponto(0, 0);

        Reta r1 = new Reta(this.p1, this.p2);
        Reta r2 = new Reta(this.p2, this.p3);
        Reta r3 = new Reta(this.p1, this.p3);
        
        Reta ra = r1.escalaRetaProprioEixo(sx, sy);
        Reta rb = r2.escalaRetaProprioEixo(sx, sy);
        Reta rc = r3.escalaRetaProprioEixo(sx, sy);
        
        Ponto pa, pb, pc;
        pa = ra.getP1();
        pb = ra.getP2();
        if((rc.getP2() != pa) && (rc.getP2() != pb) ){
            pc = rc.getP2();
        } else {
            pc = rc.getP1();
        }
        Triangulo tri = new Triangulo(pa, pb, pc);
        return tri;
    }
    
    public Triangulo escalaTrianguloPontoQualquer(Ponto q, double sx, double sy){
        double xa = (p1.getX() * sx) + (q.getX() * (1 - sx) );
        double ya = (p1.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xb = (p2.getX() * sx) + (q.getX() * (1 - sx) );
        double yb = (p2.getY() * sy) + (q.getY() * (1 - sy) );
        
        double xc = (p3.getX() * sx) + (q.getX() * (1 - sx) );
        double yc = (p3.getY() * sy) + (q.getY() * (1 - sy) );
        
        Ponto pa = new Ponto(xa, ya);
        Ponto pb = new Ponto(xb, yb);
        Ponto pc = new Ponto(xc, yc);
        
        Triangulo tri = new Triangulo(pa, pb, pc);
        return tri;
    }
}
