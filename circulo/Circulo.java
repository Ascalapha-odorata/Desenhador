package circulo;
import ponto.Ponto;
/**
 * Escreva uma descrição da classe Circulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Circulo
{
    // variáveis de instância
    public Ponto centro;
    public double raio;
    /**
     * Construtor para objetos da classe Circulo
     */
    public Circulo(int xc, int yc, int xr, int yr) {
        // inicializa variáveis de instância
        setCentro(new Ponto(xc, yc));
        setRaio(xc, yc, xr, yr);
    }
    
    public Circulo(double xc, double yc, double xr, double yr) {
        // inicializa variáveis de instância
        setCentro(new Ponto(xc, yc));
        setRaio(xc, yc, xr, yr);
    }
    
    public Circulo(int xc, int yc, int raio) {
        // inicializa variáveis de instância
        setCentro(new Ponto(xc, yc));
        setRaio(raio);
    }
    
    /**
     * Circulo Construtor
     *
     * @param xc Um parâmetro
     * @param yc Um parâmetro
     * @param r Um parâmetro
     */
    public Circulo(double xc, double yc, double r){
        setCentro(new Ponto(xc, yc));
        setRaio(r);
    }
    
    /**
     * Circulo Construtor
     *
     * @param c Um parâmetro
     * @param r Um parâmetro
     */
    public Circulo(Ponto c, int r){
        setCentro(c);
        setRaio(r);
    }
    
    /**
     * Circulo Construtor
     *
     * @param c Um parâmetro
     * @param r Um parâmetro
     */
    public Circulo(Ponto c, double r){
        setCentro(c);
        setRaio(r);
    }
    
    /**
     * Circulo Construtor
     *
     * @param a Um parâmetro
     */
    public Circulo(Circulo a){
        setCentro(a.getCentro());
        setRaio(a.getRaio());
    }
    
    /**
     * Método setCentro
     *
     * @param c Um parâmetro
     */
    public void setCentro(Ponto c){
        this.centro = c;
    }
    
    /**
     * Método setRaio
     *
     * @param r Um parâmetro
     */
    public void setRaio(double r){
        this.raio = r;
    }
    
    /**
     * Método setRaio
     *
     * @param xc Um parâmetro
     * @param yc Um parâmetro
     * @param xr Um parâmetro
     * @param yr Um parâmetro
     */
    public void setRaio(int xc, int yc, int xr, int yr){
        double r = Math.round(Math.sqrt(Math.pow(Math.abs(xr-xc), 2) + Math.pow(Math.abs(yr - yc), 2)));
        this.raio = r;
    }
    
    /**
     * Método setRaio
     *
     * @param xc Um parâmetro
     * @param yc Um parâmetro
     * @param xr Um parâmetro
     * @param yr Um parâmetro
     */
    public void setRaio(double xc, double yc, double xr, double yr){
        double r = Math.sqrt(Math.pow(Math.abs(xr-xc), 2) + Math.pow(Math.abs(yr - yc), 2));
        this.raio = r;
    }
    
    /**
     * Método getCentro
     *
     * @return O valor de retorno
     */
    public Ponto getCentro(){
        return this.centro;
    }
    
    /**
     * Método getRaio
     *
     * @return O valor de retorno
     */
    public double getRaio(){
        return this.raio;
    }
        
    /**
     * Método achaY_pos acha o valor positivo do Y
     *
     * @param x Um parâmetro
     * @return O valor de retorno
     */
    public double achaY_pos(double x){
        double y;
        y = (getCentro().getY()) + Math.sqrt(Math.abs(Math.pow(raio, 2) - Math.pow((x - getCentro().getX()), 2)));//encontra y
        return y;
    }
    
    /**
     * Método achaY_neg o valor negativo do Y
     *
     * @param x Um parâmetro
     * @return O valor de retorno
     */
    public double achaY_neg(double x){
        double y;
        y = (getCentro().getY()) - Math.sqrt(Math.abs(Math.pow(raio, 2) - Math.pow((x - getCentro().getX()), 2)));//encontra y
        return y;
    }
    
    /**
     * Método achaX_pos o valor positivo do X
     *
     * @param y Um parâmetro
     * @return O valor de retorno
     */
    public double achaX_pos(double y){
        double x;
        x = (getCentro().getX()) + Math.sqrt(Math.abs(Math.pow(raio, 2) - Math.pow((y - getCentro().getY()), 2)));//encontra y
        return x;
    }
    
    /**
     * Método achaX_neg o valor negativo do X
     *
     * @param y Um parâmetro
     * @return O valor de retorno
     */
    public double achaX_neg(double y){
        double x;
        x = (getCentro().getX()) - Math.sqrt(Math.abs(Math.pow(raio, 2) - Math.pow((y - getCentro().getY()), 2)));//encontra y
        return x;
    }
    
    /**
     * Método quadX
     *
     * @param ang Um parâmetro
     * @return O valor de retorno
     */
    public double quadX(double ang){
        double a = Math.toRadians(ang);//converte de graus para radianos, que é o que a função cos utiliza
        double x = getCentro().getX() + (raio * Math.cos(a));
        return x;
    }
    
    /**
     * Método quadY
     *
     * @param ang Um parâmetro
     * @return O valor de retorno
     */
    public double quadY(double ang){
        double a = Math.toRadians(ang);//converte de graus para radianos, que é o que a função cos utiliza
        double y = getCentro().getY() + (raio * Math.sin(a));
        return y;
    }
    
    /**
     * Method calcularDistancia csalcula a distancia ate a borda do circulo (o ponto pode estar dentro ou fora da circunferencia)
     *
     * @param p ponto qualquer
     * @return The return value
     */
    public double calcularDistancia(Ponto p){
        double d_centro; //distancia ate o centro
        double distancia;
        
        d_centro = Math.sqrt( Math.pow( p.getX() - centro.getX(), 2)  + Math.pow( p.getY() - centro.getY() , 2) );
        
        distancia = Math.abs(d_centro - this.raio);
        
        return distancia;
    }
    
    public Circulo translacaoCirculo(Ponto p){ //e a mesma coisa que usar o novo ponto dado
        Ponto novo_p;
        //double vet_x = p.getX() - centro.getX();
        //double vet_y = p.getY() - centro.getY();
        novo_p = new Ponto(getCentro().translacaoPonto(p));
        //novo_p = new Ponto(centro.getX() + vet_x, centro.getY() + vet_y);
        Circulo cir = new Circulo (novo_p, raio);
        return cir;
    }
    
    public Circulo rotacaoCirculoPontoQualquer(Ponto q, int ang){
        double xc = q.getX() + ((centro.getX() - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((centro.getY() - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yc = q.getY() + ((centro.getX() - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((centro.getY() - q.getY()) * Math.cos( Math.toRadians(ang) ));
        Ponto cen = new Ponto(xc, yc); 
        Circulo cir = new Circulo(xc, yc, this.raio);
        return cir;
    }
    
    public Circulo escalaCirculoPontoQualquer(Ponto q, double s){
        double xc = (this.centro.getX() * s) + (q.getX() * (1 - s) );
        double yc = (this.centro.getY() * s) + (q.getY() * (1 - s) );
        
        //calcula o x do raio para saber o novo raio
        double xr = this.centro.getX() + this.raio;   
        xr = (xr * s) + (q.getX() * (1 - s) );
        
        
        Circulo cir = new Circulo(xc, yc, Math.abs(xc-xr));
        
        return cir;
    }
    
    public Circulo escalaCirculoProprioEixo( double s){
        Circulo cir = new Circulo(this.getCentro(), s * this.raio);
        return cir;
    }
}
