package ponto;
 
/**
 * Representacao de ponto matematico
 * 
 * @author Julio Arakaki
 * @version 20220815
 */
public class Ponto {
    private double x;
    private double y;
    /**
     * Constroi um ponto em 0,0
     */
    public Ponto() {
        setX(0);
        setY(0);
    }

    /**
     * Constroi um ponto igual ao ponto p (parametro)
     *
     * @param p ponto externo
     */
    public Ponto(Ponto p) {
        setX(p.getX());
        setY(p.getY());
    }
    /**
     * Constroi um ponto com as coordenadas x e y (parametros)
     * @param x coordenada x do ponto 
     * @param y coordenada y do ponto
     */
    public Ponto(double x, double y) {
        setX(x);
        setY(y);
    }
    
    
    /**
     * Retorna a coordenada x
     * @return coordenada x
     */
    public double getX() {
        return x;
    }
    /**
     * Altera a coordenada x de acordo com o valor do parametro x
     * @param x coordenada x externo
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Retorna a coordenada y
     * 
     * @return coordenada y
     */
    public double getY() {
        return y;
    }
    /**
     * Altera a coordenada y de acordo com o valor do parametro y
     * @param y coordenada y externo 
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * Calcula a distancia entre o ponto que vem como parametro
     * 
     * @param p ponto externo
     * 
     * @return d double valor da distancia
     * 
     */
    public double calcularDistancia(Ponto p) {
        double d = Math.sqrt(Math.pow(p.getY()-getY(), 2) + Math.pow(p.getX()-getX(), 2));
        return(d);
    }
    
    
    public Ponto rotacaoPontoQualquer(Ponto q, double ang){
        double xn = q.getX() + ((x - q.getX()) * Math.cos( Math.toRadians(ang) )) - ((y - q.getY()) * Math.sin( Math.toRadians(ang) ));
        double yn = q.getY() + ((x - q.getX()) * Math.sin( Math.toRadians(ang) )) + ((y - q.getY()) * Math.cos( Math.toRadians(ang) ));
        Ponto novo = new Ponto(xn, yn);
        return novo;
    }
    
    public Ponto translacaoPonto(Ponto p){ //e a mesma coisa que usar o novo ponto dado
        Ponto novo_p;
        double vet_x = p.getX() - this.x;
        double vet_y = p.getY() - this.y;
        
        novo_p = new Ponto(this.x + vet_x, this.y + vet_y);
        return novo_p;
    }
    
    /**
     * Method escalaPontoQualquer faz a mudanca de escala em relacao a um ponto c qualquer
     *
     * @param c ponto qualquer
     * @param sx A parameter
     * @param sy A parameter
     * @return novo ponto
     */
    public Ponto escalaPontoQualquer(Ponto c, double sx, double sy){
        double xp = (this.x * sx) + (c.getX() * (1 - sx) );
        double yp = (this.y * sy) + (c.getY() * (1 - sy) );
        Ponto p = new Ponto(xp, yp);
        return p;
    }
    
    /**
     * Método sobrecarregado que imprime um ponto no formato [x, y]
     *
     * @return string representando o ponto
     */
    @Override
    public String toString() {
        return "Ponto [" + getX() + ", " + getY() +  "]";
    }
}
