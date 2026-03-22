import javax.swing.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ponto.FiguraPontos;
import reta.FiguraRetas;
import circulo.FiguraCirculos;
import triangulo.FiguraTriangulos;
import retangulo.FiguraRetangulos;

import ponto.*;
import reta.*;
import circulo .*;
import triangulo .*;
import retangulo .*;
import ponto.PontoGr;

import java.io.Serializable;
import armazenagem .*;
/**
 * Cria desenhos de acordo com o tipo e eventos do mouse
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
    
    int largura_quadro;
    int altura_quadro;

    JLabel msg;           // Label para mensagens
    TipoPrimitivo tipo; // Tipo do primitivo
    TipoOperacao tipoOp; //tipo de operação
    Color corAtual;       // Cor atual do primitivo
    int esp;              // Diametro do ponto
    
    int ang;              // Angulo do ponto

    // Para ponto
    int x, y;

    // Para reta
    int x1, y1, x2, y2, x1_ant=0, y1_ant=0;
    
    //Para circulo
    int xc, yc, xr, yr, r, xr_ant = 0, yr_ant = 0;//xc e yc sao coordenadas do centro do circulo
    
    //Para triangulo
    int xp1, yp1, xp2, yp2, xp3, yp3;
    
    //PARA RETANGULO
    int xd1, yd1, xd2, yd2, xd2_ant = 0, yd2_ant = 0;
    
    //para selecionar primitivo
    int x_sel, y_sel;
    int sinal = -1;
    
    //para translacao
    int x_trans, y_trans;
    
    //para escala
    //int sx, sy;
    
    // selecionar primeiro click do mouse
    boolean primeiraVez = true;
    boolean segundaVez = false;
    
    boolean jaSelecionou = false;
    
    public static IArmazenador armz = new VetDin();
    ArrayList<Object> prox = new ArrayList<>();

    /**
     * Constroi o painel de desenho
     *
     * @param msg mensagem a ser escrita no rodape do painel
     * @param tipo tipo atual do primitivo
     * @param corAtual cor atual do primitivo
     * @param esp espessura atual do primitivo
     */
    public PainelDesenho(JLabel msg, TipoPrimitivo tipo, TipoOperacao tipoOp, Color corAtual, int esp){
        setTipo(tipo);
        setOp(tipoOp);
        setMsg(msg);
        setCorAtual(corAtual);
        setEsp(esp);

        // Adiciona "ouvidor" de eventos de mouse
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);

    }   
    
    /**
     * Method set_dim dimensoes do quadro de desenho
     *
     * @param larg A parameter
     * @param alt A parameter
     */
    public void set_dim(int larg, int alt){
        this.largura_quadro = larg;
        this.altura_quadro = alt;
    }

    /**
     * Altera o tipo atual do primitivo
     *
     * @param tipo tipo do primitivo
     */
    public void setTipo(TipoPrimitivo tipo){
        this.tipo = tipo;
    }
    
    public void setOp(TipoOperacao tipoOp){
        this.tipoOp = tipoOp;
    }

    /**
     * Retorna o tipo do primitivo
     *
     * @return tipo do primitivo
     */
    public TipoPrimitivo getTipo(){
        return this.tipo;
    }

    /**
     * Altera a espessura do primitivo
     *
     * @param esp espessura do primitivo
     */
    public void setEsp(int esp){
        this.esp = esp;
    }

    /**
     * Retorna a espessura do primitivo
     *
     * @return espessura do primitivo
     */
    public int getEsp(){
        return this.esp;
    }
    
    /**
     * Altera o angulo do primitivo
     *
     * @param ang A parameter
     */
    public void setAng(int ang){
        this.ang = 360 - ang; //como as coordenadas sao invertidas na tela, para normalizar, precisa subtrair de 360 graus para girar para o lugar certo
        
    }
    
    /**
     * Method getAng retorna o angulo de rotacao escolhido pelo usuario
     *
     */
    public int getAng(){
        return this.ang;
    }
    
    /**
     * Altera a cor atual do primitivo
     *
     * @param corAtual cor atual do primitivo
     */
    public void setCorAtual(Color corAtual){
        this.corAtual = corAtual;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCorAtual(){
        return this.corAtual;
    }

    /**
     * Altera a msg a ser apresentada no rodape
     *
     * @param msg mensagem a ser apresentada
     */
    public void setMsg(JLabel msg){
        this.msg = msg;
    }

    /**
     * Retorna a mensagem
     *
     * @return mensagem as ser apresentada no rodape
     */
    public JLabel getMsg(){
        return this.msg;
    }

    /**
     * Metodo chamado quando o paint eh acionado
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void paintComponent(Graphics g) {
        apagarPrimitivos(g);
        desenharPrimitivos(g);        
        redesenharPrimitivos(g);
    }

    
    /**
     * Evento: pressionar do mouse verifica o tipo de primitivo escolhido e pega as coordenadas de acordo
     *
     * @param e dados do evento
     */
    public void mousePressed(MouseEvent e) { 
        Graphics g = getGraphics();  
        if (tipo == TipoPrimitivo.PONTO){
            x = e.getX();
            y = e.getY();
            paint(g);
        } else if (tipo == TipoPrimitivo.RETA){

            if (primeiraVez == true) {
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            }
        } else if(tipo == TipoPrimitivo.CIRCULO){
            if(primeiraVez == true){
                xc = (int)e.getX();//coordenadas do centro
                yc = (int)e.getY();
                primeiraVez = false;
            } 
        } else if(tipo == TipoPrimitivo.TRIANGULO){ 
            if(primeiraVez == true){ //xa, ya, xb, yb, xh, yh
                xp1 = (int)e.getX();//p1
                yp1 = (int)e.getY();
                primeiraVez = false;
                segundaVez = true;
            } else if (segundaVez == true) { //segunda vez
                xp2 = (int)e.getX();//p2
                yp2 = (int)e.getY();
                primeiraVez = false;
                segundaVez = false;
            } else { //terceira vez                
                xp3 = (int)e.getX();//p3
                yp3 = (int)e.getY();
                primeiraVez = true;
                segundaVez = false;
                paint(g);
            }
        } else if(tipo == TipoPrimitivo.RETANGULO){
            if (primeiraVez == true) {
                xd1 = (int)e.getX();
                yd1 = (int)e.getY();
                primeiraVez = false;
            }
        }         
    }

    /**
     * Método mouseReleased pega as "coordenadas finais" do mouse, para que possa ser feito o desenho "definitivo", após o usuário definir a forma final ao soltar o mouse 
     *
     * @param e Um parâmetro
     */
    public void mouseReleased(MouseEvent e) {
        Graphics g = getGraphics();  
        if(tipo == TipoPrimitivo.RETA){
            x2 = (int)e.getX();
            y2 = (int)e.getY();
            primeiraVez = true;
            paint(g);
            RetaGr r_store = new RetaGr(x1, y1, x2, y2, getCorAtual(), "", getEsp());
            armz.adicionar(r_store);
        } else if(tipo == TipoPrimitivo.CIRCULO){
            xr = (int)e.getX();//coordenadas para achar o raio
            yr = (int)e.getY();
            primeiraVez = true;
            paint(g);
            CirculoGr c_store = new CirculoGr(xc, yc, xr, yr, getCorAtual(), "", getEsp());
            armz.adicionar(c_store);
        } else if(tipo == TipoPrimitivo.RETANGULO){
            xd2 = (int)e.getX();
            yd2 = (int)e.getY();
            primeiraVez = true;
            paint(g);
            RetanguloGr ret_store = new RetanguloGr (xd1, yd1, xd2, yd2, getCorAtual(), "", getEsp());
            armz.adicionar(ret_store);
        }
    }           

    public void mouseClicked(MouseEvent e) {
        Graphics g = getGraphics();
        if(tipoOp == TipoOperacao.SELECT){
            selecionarCoordenada(e);//seleciona uma coordenada
            jaSelecionou = true;        
        }
    }
    
    /**
     * Method apagar_sombra apaga a "rebarba", ou a "sombra" de um primitivo que ja foi deletado da lista
     *
     * @param larg A parameter
     * @param alt A parameter
     */
    public void apagar_sombra(int larg, int alt){
        Graphics g = getGraphics();
        FiguraRetangulos.desenharRetangulo(g, 0, 0, larg, alt, "", getEsp(), getBackground());
    }
    
    public void selecionarCoordenada(MouseEvent e){
        //primeiro fazer selecionando um ponto que faz parte (exatamente) do objeto
        Graphics g = getGraphics();
        x_sel = (int)e.getX();
        y_sel = (int)e.getY();
    }
    
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    /**
     * Método mouseDragged pega as coordenadas em que o mouse está para desenhar os primitivos, e cria o efeito "elástico"
     *
     * @param e Um parâmetro
     */
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();
        if(tipo == TipoPrimitivo.RETA) {
            x1_ant = x2;
            y1_ant = y2;
            x2 = (int)e.getX();
            y2 = (int)e.getY();
            paint(g);
            this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
        } else if(tipo == TipoPrimitivo.CIRCULO) {
           xr_ant = xr;
           yr_ant = yr;
           xr = (int)e.getX();
           yr = (int)e.getY();
           paint(g);
           this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo()); 
        } else if(tipo == TipoPrimitivo.RETANGULO){
            xd2_ant = xd2;
            yd2_ant = yd2;
            xd2 = (int)e.getX();
            yd2 = (int)e.getY();
            paint(g);
            this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
        }
    }

    /**
     * Evento mouseMoved: escreve mensagem no rodape (x, y) do mouse
     *
     * @param e dados do evento do mouse
     */
    public void mouseMoved(MouseEvent e) {
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    /**
     * Desenha os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void desenharPrimitivos(Graphics g){
        if (tipo == TipoPrimitivo.PONTO){
            FiguraPontos.desenharPonto(g, x, y, "", getEsp(), getCorAtual());
            PontoGr p_store = new PontoGr(x, y, getCorAtual(), "", getEsp());
            armz.adicionar(p_store);
        }

        if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
        }

        if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCirculos.desenharCirculo(g, xc, yc, xr, yr, "", getEsp(), getCorAtual());
        }
        
        if (tipo==TipoPrimitivo.TRIANGULO){
            FiguraTriangulos.desenharTriangulo(g, xp1, yp1, xp2, yp2, xp3, yp3, "", getEsp(), getCorAtual());
            TrianguloGr t_store = new TrianguloGr (xp1, yp1, xp2, yp2, xp3, yp3, getCorAtual(), "", getEsp());
            armz.adicionar(t_store);
        }
        
        if (tipo==TipoPrimitivo.RETANGULO){
            FiguraRetangulos.desenharRetangulo(g, xd1, yd1, xd2, yd2, "", getEsp(), getCorAtual());
            //FiguraRetangulos.desenharRetangulo(g, (int)ret_gr.getP1().getX(), (int)ret_gr.getP1().getY(), (int)ret_gr.getP2().getX(),(int)ret_gr.getP2().getY(),(int)ret_gr.getP3().getX(),(int)ret_gr.getP3().getY(), (int)ret_gr.getP4().getX(),(int)ret_gr.getP4().getY(),ret_gr.getNomeRetangulo(), ret_gr.getEspRetangulo(), ret_gr.getCorRetangulo());
                
        }
    }
    
    /**
     * Apaga os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void apagarPrimitivos(Graphics g){
        if (tipo == TipoPrimitivo.RETA) {
            FiguraRetas.desenharReta(g, x1, y1, x1_ant, y1_ant, "", getEsp(), getBackground());
        }

        if (tipo==TipoPrimitivo.CIRCULO) {
            FiguraCirculos.desenharCirculo(g, xc, yc, xr_ant, yr_ant, "", getEsp(), getBackground());
        }
        
        if (tipo==TipoPrimitivo.RETANGULO){
            FiguraRetangulos.desenharRetangulo(g, xd1, yd1, xd2_ant, yd2_ant, "", getEsp(), getBackground());
        }
    }
    
    /**
     * Método redesenharPrimitivos redesenha os primitivos que estão na memória, "restarura" a imagem
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void redesenharPrimitivos(Graphics g){
        int k;
        if(!armz.estaVazia()){
            for(k = 0; k < armz.getQtd(); k++){
                Object obj = armz.buscar(k);
                if(obj instanceof PontoGr){
                    PontoGr p_load = (PontoGr) obj;
                    FiguraPontos.desenharPonto(g, (int)p_load.getX(), (int)p_load.getY(), p_load.getNomePto(), p_load.getDiametro(), p_load.getCorPto());
                } else if(obj instanceof RetaGr){
                    RetaGr r_load = (RetaGr) obj;
                    FiguraRetas.desenharReta(g, (int)r_load.getP1().getX(), (int)r_load.getP1().getY(), (int)r_load.getP2().getX(),(int)r_load.getP2().getY(), r_load.getNomeReta(), r_load.getEspReta(), r_load.getCorReta());
                } else if(obj instanceof CirculoGr){
                    CirculoGr c_load = (CirculoGr) obj;
                    FiguraCirculos.desenharCirculo(g, (int)Math.round(c_load.getCentro().getX()), (int)Math.round(c_load.getCentro().getY()), (int)Math.round(c_load.getRaio()), c_load.getNomeCirculo(), c_load.getEspCirculo(), c_load.getCorCirculo());
                } else if(obj instanceof TrianguloGr){
                    TrianguloGr tri_load = (TrianguloGr) obj;
                    FiguraTriangulos.desenharTriangulo(g, (int)Math.round(tri_load.getP1().getX()), (int)Math.round(tri_load.getP1().getY()), (int)Math.round(tri_load.getP2().getX()), (int)Math.round(tri_load.getP2().getY()), (int)Math.round(tri_load.getP3().getX()), (int)Math.round(tri_load.getP3().getY()), tri_load.getNomeTriangulo(), tri_load.getEspTriangulo(), tri_load.getCorTriangulo());
                } else if(obj instanceof RetanguloGr){
                    RetanguloGr ret_load = (RetanguloGr) obj;
                    FiguraRetangulos.desenharRetangulo(g, (int)ret_load.getP1().getX(), (int)ret_load.getP1().getY(), (int)ret_load.getP2().getX(),(int)ret_load.getP2().getY(),(int)ret_load.getP3().getX(),(int)ret_load.getP3().getY(), (int)ret_load.getP4().getX(),(int)ret_load.getP4().getY(),ret_load.getNomeRetangulo(), ret_load.getEspRetangulo(), ret_load.getCorRetangulo());
                    //FiguraRetangulos.desenharRetangulo(g, (int)Math.round(ret_load.getP1().getX()),(int)Math.round(ret_load.getP1().getY()), (int)Math.round(ret_load.getP2().getX()), (int)Math.round(ret_load.getP2().getY()), ret_load.getNomeRetangulo(), ret_load.getEspRetangulo(), ret_load.getCorRetangulo());
                }
            }
        }
    }
    
    /**
     * Método limparTela limpa a estrutura de dados e a tela
     *
     * @param larg Um parâmetro dimensões da tela
     * @param alt Um parâmetro dimensões da tela
     */
    public void limparTela(int larg, int alt){
        Graphics g = getGraphics(); //para desenhar em modo grafico
        if(!armz.estaVazia()){ //limpa a estrutura de dados
            int k = armz.getQtd();
            k--;
            while(!armz.estaVazia() && k >= 0){
                armz.remover(k);
                k--;
            }
        }
        //desenha um retângulo na tela, apagando o desenho anterior
        FiguraRetangulos.desenharRetangulo(g, 0, 0, larg, alt, "", getEsp(), getBackground());
        //g.setColor(getBackground());
        //g.fillRect(0, 0, larg, alt);
    }
    
    /**
     * Method deletarPrimitivo deleta apenas um primitivo da lista, de acordo com o seu local e se o usurio o selecionou
     *
     */
    public void deletarPrimitivo(){
        int local = selecionarPrimitivo();
        if((local != sinal) && (!armz.estaVazia() && jaSelecionou)){
            armz.remover(local);
            jaSelecionou = false;
        }
    }
    
    /**
     * Método translacaoPrimitivo atualiza a lista de primitivos com o primitivo transladado (apaga o antigo e recoloca o novo)
     * @param x_novo coordenada que vem da classe Gui
     * @param y_novo coordenada que vem da classe Gui
     *
     */
    public void translacaoPrimitivo(int x_novo, int y_novo){
        Graphics g = getGraphics(); //para desenhar em modo grafico 
        int local = selecionarPrimitivo();//seleciona o primitivo de acordo com as coordenadas x_sel, y_sel (obtidas por meio do botão selecionar)
        if(local != sinal){  
            Object obj = armz.buscar(local);
            Ponto p_aux = new Ponto(x_novo, y_novo);//cria um ponto com as coordenadas que o usuario clicou
            if(obj instanceof PontoGr){
                PontoGr p_load = (PontoGr) obj;//ponto selecionado para mudança
                Ponto p_atu = new Ponto(p_load.getX(), p_load.getY());
                Ponto p_tr = new Ponto (p_atu.translacaoPonto(p_aux));
                PontoGr p_novo = new PontoGr((int)Math.round(p_tr.getX()), (int)Math.round(p_tr.getY()), p_load.getCorPto(),  p_load.getNomePto() , p_load.getDiametro()); //cria ponto novo
                armz.adicionar(p_novo);//adiciona o ponto grafico novo na lista
                FiguraPontos.desenharPonto(g, (int)p_novo.getX(), (int)p_novo.getY(), p_novo.getNomePto(), p_novo.getDiametro(), p_novo.getCorPto()); //desenha ponto grafico novo
                armz.remover(local);    
            } else if(obj instanceof RetaGr){
                RetaGr r_load = (RetaGr) obj;
                Reta r_aux = new Reta (r_load.getP1(), r_load.getP2()); //cria reta com as mesmas especificacoes
                Reta rn = new Reta (r_aux.translacaoPontoMedio(p_aux)); //acha a nova reta com base no ponto medio
                //cria e guarda a nova reta grafica na memoria
                RetaGr nova_r = new RetaGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), r_load.getCorReta(), r_load.getNomeReta(), r_load.getEspReta() );
                FiguraRetas.desenharReta(g, (int)nova_r.getP1().getX(), (int)nova_r.getP1().getY(), (int)nova_r.getP2().getX(),(int)nova_r.getP2().getY(), nova_r.getNomeReta(), nova_r.getEspReta(), nova_r.getCorReta());
                armz.adicionar(nova_r);
                armz.remover(local);
            } else if(obj instanceof CirculoGr){
                CirculoGr c_load = (CirculoGr) obj;//utiliza as coordenadas nos parmetros como o novo centro do circulo
                Circulo c_atu = new Circulo(c_load.getCentro(), c_load.getRaio());
                Circulo c_tr = new Circulo(c_atu.translacaoCirculo(p_aux));
                CirculoGr c_novo = new CirculoGr((int)Math.round(c_tr.getCentro().getX()), (int)Math.round(c_tr.getCentro().getY()), (int)Math.round(c_tr.getRaio()), c_load.getCorCirculo(), c_load.getNomeCirculo(), c_load.getEspCirculo());
                FiguraCirculos.desenharCirculo(g, (int)Math.round(c_novo.getCentro().getX()), (int)Math.round(c_novo.getCentro().getY()), (int)Math.round(c_novo.getRaio()), c_novo.getNomeCirculo(), c_novo.getEspCirculo(), c_novo.getCorCirculo());
                armz.adicionar(c_novo);
                armz.remover(local);
            } else if(obj instanceof TrianguloGr){
                TrianguloGr tri_load = (TrianguloGr) obj;//utliliza as coordenadas nos parmetros como o novo centro do triangulo (baricentro)
                Triangulo t_aux = new Triangulo(tri_load.getP1(), tri_load.getP2(), tri_load.getP3());
                Triangulo tn = new Triangulo(t_aux.translacaoBaricentro(p_aux));//triangulo com coordenadas novas
                //triangulo grafico com coordenadas novas
                TrianguloGr tri_gr = new TrianguloGr((int)Math.round(tn.getP1().getX()), (int)Math.round(tn.getP1().getY()), (int)Math.round(tn.getP2().getX()), (int)Math.round(tn.getP2().getY()), (int)Math.round(tn.getP3().getX()), (int)Math.round(tn.getP3().getY()), tri_load.getCorTriangulo(), tri_load.getNomeTriangulo(), tri_load.getEspTriangulo());
                armz.adicionar(tri_gr);
                armz.remover(local);
            } else if(obj instanceof RetanguloGr){ //utliliza as coordenadas nos parmetros como o novo centro do retangulo
                RetanguloGr ret_load = (RetanguloGr) obj;
                Retangulo r_aux = new Retangulo(ret_load.getP1(), ret_load.getP2(), ret_load.getP3(), ret_load.getP4());//diagonal do retangulo
                Retangulo rn = new Retangulo(r_aux.retanguloTranslacaoCentro(p_aux)); //acha a nova reta diagonal com base no ponto medio
                RetanguloGr ret_gr = new RetanguloGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), (int)rn.getP3().getX(), (int)rn.getP3().getY(), (int)rn.getP4().getX(), (int)rn.getP4().getY(), ret_load.getCorRetangulo(), ret_load.getNomeRetangulo(), ret_load.getEspRetangulo() );
                FiguraRetangulos.desenharRetangulo(g, (int)ret_gr.getP1().getX(), (int)ret_gr.getP1().getY(), (int)ret_gr.getP2().getX(),(int)ret_gr.getP2().getY(),(int)ret_gr.getP3().getX(),(int)ret_gr.getP3().getY(), (int)ret_gr.getP4().getX(),(int)ret_gr.getP4().getY(),ret_gr.getNomeRetangulo(), ret_gr.getEspRetangulo(), ret_gr.getCorRetangulo());
                armz.adicionar(ret_gr);
                armz.remover(local);
            }
        }
    }
    
    public void rotacaoPrimitivoProprioEixo(){
        Graphics g = getGraphics(); //para desenhar em modo grafico
        int local;
        if(tipoOp != TipoOperacao.ROT_PROPRIO_EIXO){
            local = selecionarPrimitivo();//seleciona o primitivo de acordo com as coordenadas x_sel, y_sel (obtidas por meio do botão selecionar)
        } else {
            local = armz.getQtd() - 1;//se a ultima coisa feita foi rotacionar (o mesmo objeto), o lugar na lista em que ele esta eh o ultimo lugar
            //ja que para rotacionar, ele eh apagado e recriado, e adicionado, obrigatoriamente, no final da lista
        }
        if(local != sinal){
            Object obj = armz.buscar(local);
            if(obj instanceof PontoGr){
                //nao muda nada
            } else if(obj instanceof RetaGr){
                RetaGr r_load = (RetaGr) obj;
                Reta r_aux = new Reta (r_load.getP1(), r_load.getP2()); //cria reta com as mesmas especificacoes
                Reta rn = new Reta (r_aux.rotacaoRetaProprioEixo(getAng())); //acha a nova reta
                //cria e guarda a nova reta grafica na memoria
                RetaGr nova_r = new RetaGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), r_load.getCorReta(), r_load.getNomeReta(), r_load.getEspReta() );
                FiguraRetas.desenharReta(g, (int)nova_r.getP1().getX(), (int)nova_r.getP1().getY(), (int)nova_r.getP2().getX(),(int)nova_r.getP2().getY(), nova_r.getNomeReta(), nova_r.getEspReta(), nova_r.getCorReta());
                armz.adicionar(nova_r);
                armz.remover(local);
            }  else if(obj instanceof CirculoGr){
                //nao muda nada
            } else if(obj instanceof TrianguloGr){
                TrianguloGr tri_load = (TrianguloGr) obj;
                Triangulo t_aux = new Triangulo(tri_load.getP1(), tri_load.getP2(), tri_load.getP3());
                Triangulo tn = new Triangulo(t_aux.rotacaoTrianguloProprioEixo(getAng() ));//triangulo com coordenadas novas
                //triangulo grafico com coordenadas novas
                TrianguloGr tri_gr = new TrianguloGr((int)Math.round(tn.getP1().getX()), (int)Math.round(tn.getP1().getY()), (int)Math.round(tn.getP2().getX()), (int)Math.round(tn.getP2().getY()), (int)Math.round(tn.getP3().getX()), (int)Math.round(tn.getP3().getY()), tri_load.getCorTriangulo(), tri_load.getNomeTriangulo(), tri_load.getEspTriangulo());
                armz.adicionar(tri_gr);
                armz.remover(local);            
            } else if(obj instanceof RetanguloGr){
                RetanguloGr ret_load = (RetanguloGr) obj;
                Retangulo r_aux = new Retangulo(ret_load.getP1(), ret_load.getP2(), ret_load.getP3(), ret_load.getP4());
                Retangulo rn = new Retangulo(r_aux.rotacaoRetanguloProprioEixo(getAng())); //acha a nova reta diagonal com base no ponto medio
                RetanguloGr ret_gr = new RetanguloGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), (int)rn.getP3().getX(), (int)rn.getP3().getY(), (int)rn.getP4().getX(), (int)rn.getP4().getY(), ret_load.getCorRetangulo(), ret_load.getNomeRetangulo(), ret_load.getEspRetangulo() );
                FiguraRetangulos.desenharRetangulo(g, (int)ret_gr.getP1().getX(), (int)ret_gr.getP1().getY(), (int)ret_gr.getP2().getX(),(int)ret_gr.getP2().getY(),(int)ret_gr.getP3().getX(),(int)ret_gr.getP3().getY(), (int)ret_gr.getP4().getX(),(int)ret_gr.getP4().getY(),ret_gr.getNomeRetangulo(), ret_gr.getEspRetangulo(), ret_gr.getCorRetangulo());
                armz.adicionar(ret_gr);
                armz.remover(local);
            }
        }
    }
    
    
    public void rotacaoPrimitivoPontoQualquer(int x_novo, int y_novo){
        Graphics g = getGraphics(); //para desenhar em modo grafico 
        int local = selecionarPrimitivo();//seleciona o primitivo de acordo com as coordenadas x_sel, y_sel (obtidas por meio do botão selecionar)
        if(local != sinal){
            Object obj = armz.buscar(local);
            Ponto p_aux = new Ponto(x_novo, y_novo);//cria um ponto com as coordenadas que o usuario clicou
            if(obj instanceof PontoGr){
                PontoGr p_load = (PontoGr) obj;//ponto selecionado para mudança
                Ponto p_a = new Ponto(p_load.getX(), p_load.getY());
                Ponto p_rot = new Ponto(p_a.rotacaoPontoQualquer(p_aux, getAng() ));
                PontoGr p_novo = new PontoGr((int)Math.round(p_rot.getX()), (int)Math.round(p_rot.getY()), p_load.getCorPto(),  p_load.getNomePto() , p_load.getDiametro()); //cria ponto novo
                armz.adicionar(p_novo);//adiciona o ponto grafico novo na lista
                FiguraPontos.desenharPonto(g, (int)p_novo.getX(), (int)p_novo.getY(), p_novo.getNomePto(), p_novo.getDiametro(), p_novo.getCorPto()); //desenha ponto grafico novo
                armz.remover(local);
            } else if(obj instanceof RetaGr){
                RetaGr r_load = (RetaGr) obj;
                Reta r_aux = new Reta (r_load.getP1(), r_load.getP2()); //cria reta com as mesmas especificacoes
                Reta rn = new Reta (r_aux.rotacaoPontoQualquer(p_aux, getAng())); //acha a nova reta com base no ponto medio
                //Reta rn = new Reta (r_aux.rotacaoReta(getAng())); //acha a nova reta
                //cria e guarda a nova reta grafica na memoria
                RetaGr nova_r = new RetaGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), r_load.getCorReta(), r_load.getNomeReta(), r_load.getEspReta() );
                FiguraRetas.desenharReta(g, (int)nova_r.getP1().getX(), (int)nova_r.getP1().getY(), (int)nova_r.getP2().getX(),(int)nova_r.getP2().getY(), nova_r.getNomeReta(), nova_r.getEspReta(), nova_r.getCorReta());
                armz.adicionar(nova_r);
                armz.remover(local);
            }  else if(obj instanceof CirculoGr){
                CirculoGr c_load = (CirculoGr) obj;
                Circulo c_atu = new Circulo(c_load.getCentro(), c_load.getRaio());
                Circulo c_rot = new Circulo(c_atu.rotacaoCirculoPontoQualquer( p_aux, getAng() ) );
                CirculoGr c_novo = new CirculoGr((int)Math.round(c_rot.getCentro().getX()), (int)Math.round(c_rot.getCentro().getY()), (int)Math.round(c_rot.getRaio()), c_load.getCorCirculo(), c_load.getNomeCirculo(), c_load.getEspCirculo());
                FiguraCirculos.desenharCirculo(g, (int)Math.round(c_novo.getCentro().getX()), (int)Math.round(c_novo.getCentro().getY()), (int)Math.round(c_novo.getRaio()), c_novo.getNomeCirculo(), c_novo.getEspCirculo(), c_novo.getCorCirculo());
                armz.adicionar(c_novo);
                armz.remover(local);
            } else if(obj instanceof TrianguloGr){
                TrianguloGr tri_load = (TrianguloGr) obj;
                Triangulo t_aux = new Triangulo(tri_load.getP1(), tri_load.getP2(), tri_load.getP3());
                Triangulo tn = new Triangulo(t_aux.rotacaoTrianguloPontoQualquer(p_aux, getAng() ));//triangulo com coordenadas novas
                //Triangulo tn = new Triangulo(t_aux.rotacaoTriangulo(getAng() ));//triangulo com coordenadas novas
                //triangulo grafico com coordenadas novas
                TrianguloGr tri_gr = new TrianguloGr((int)Math.round(tn.getP1().getX()), (int)Math.round(tn.getP1().getY()), (int)Math.round(tn.getP2().getX()), (int)Math.round(tn.getP2().getY()), (int)Math.round(tn.getP3().getX()), (int)Math.round(tn.getP3().getY()), tri_load.getCorTriangulo(), tri_load.getNomeTriangulo(), tri_load.getEspTriangulo());
                armz.adicionar(tri_gr);
                armz.remover(local);            
            } else if(obj instanceof RetanguloGr){
                RetanguloGr ret_load = (RetanguloGr) obj;
                Retangulo r_aux = new Retangulo(ret_load.getP1(), ret_load.getP2(), ret_load.getP3(), ret_load.getP4());//
                Retangulo rn = new Retangulo(r_aux.rotacaoRetanguloPontoQualquer(p_aux, getAng() )); //acha a nova reta diagonal com base no ponto medio
                RetanguloGr ret_gr = new RetanguloGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), (int)rn.getP3().getX(), (int)rn.getP3().getY(), (int)rn.getP4().getX(), (int)rn.getP4().getY(), ret_load.getCorRetangulo(), ret_load.getNomeRetangulo(), ret_load.getEspRetangulo() );
                FiguraRetangulos.desenharRetangulo(g, (int)ret_gr.getP1().getX(), (int)ret_gr.getP1().getY(), (int)ret_gr.getP2().getX(),(int)ret_gr.getP2().getY(),(int)ret_gr.getP3().getX(),(int)ret_gr.getP3().getY(), (int)ret_gr.getP4().getX(),(int)ret_gr.getP4().getY(),ret_gr.getNomeRetangulo(), ret_gr.getEspRetangulo(), ret_gr.getCorRetangulo());
                armz.adicionar(ret_gr);
                armz.remover(local);
            }
        }
    }
    
    public void escalaPrimitivoPontoQualquer(int px, int py, double sx, double sy){
        Graphics g = getGraphics(); //para desenhar em modo grafico 
        int local = selecionarPrimitivo();//seleciona o primitivo de acordo com as coordenadas x_sel, y_sel (obtidas por meio do botão selecionar)
        if(local != sinal){
            Object obj = armz.buscar(local);
            Ponto p_aux = new Ponto(px, py);//cria um ponto com as coordenadas que o usuario clicou
            if(obj instanceof PontoGr){
                //ponto nao tem escala
            } else if(obj instanceof RetaGr){
                RetaGr r_load = (RetaGr) obj;
                Reta r_aux = new Reta (r_load.getP1(), r_load.getP2()); //cria reta com as mesmas especificacoes
                Reta rn = new Reta (r_aux.escalaRetaPontoQualquer(p_aux, sx, sy)); //acha a nova reta
                //cria e guarda a nova reta grafica na memoria
                RetaGr nova_r = new RetaGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), r_load.getCorReta(), r_load.getNomeReta(), r_load.getEspReta() );
                FiguraRetas.desenharReta(g, (int)nova_r.getP1().getX(), (int)nova_r.getP1().getY(), (int)nova_r.getP2().getX(),(int)nova_r.getP2().getY(), nova_r.getNomeReta(), nova_r.getEspReta(), nova_r.getCorReta());
                armz.adicionar(nova_r);
                armz.remover(local);
            }  else if(obj instanceof CirculoGr){
                CirculoGr c_load = (CirculoGr) obj;
                Circulo c_atu = new Circulo(c_load.getCentro(), c_load.getRaio());
                Circulo c_rot = new Circulo(c_atu.escalaCirculoPontoQualquer(p_aux, sx) );
                CirculoGr c_novo = new CirculoGr((int)Math.round(c_rot.getCentro().getX()), (int)Math.round(c_rot.getCentro().getY()), (int)Math.round(c_rot.getRaio()), c_load.getCorCirculo(), c_load.getNomeCirculo(), c_load.getEspCirculo());
                FiguraCirculos.desenharCirculo(g, (int)Math.round(c_novo.getCentro().getX()), (int)Math.round(c_novo.getCentro().getY()), (int)Math.round(c_novo.getRaio()), c_novo.getNomeCirculo(), c_novo.getEspCirculo(), c_novo.getCorCirculo());
                armz.adicionar(c_novo);
                armz.remover(local);
            } else if(obj instanceof TrianguloGr){
                TrianguloGr tri_load = (TrianguloGr) obj;
                Triangulo t_aux = new Triangulo(tri_load.getP1(), tri_load.getP2(), tri_load.getP3());
                Triangulo tn = new Triangulo(t_aux.escalaTrianguloPontoQualquer(p_aux, sx, sy ));//triangulo com coordenadas novas
                //triangulo grafico com coordenadas novas
                TrianguloGr tri_gr = new TrianguloGr((int)Math.round(tn.getP1().getX()), (int)Math.round(tn.getP1().getY()), (int)Math.round(tn.getP2().getX()), (int)Math.round(tn.getP2().getY()), (int)Math.round(tn.getP3().getX()), (int)Math.round(tn.getP3().getY()), tri_load.getCorTriangulo(), tri_load.getNomeTriangulo(), tri_load.getEspTriangulo());
                armz.adicionar(tri_gr);
                armz.remover(local);            
            } else if(obj instanceof RetanguloGr){
                RetanguloGr ret_load = (RetanguloGr) obj;
                Retangulo r_aux = new Retangulo(ret_load.getP1(), ret_load.getP2(), ret_load.getP3(), ret_load.getP4());//diagonal do retangulo
                Retangulo rn = new Retangulo(r_aux.escalaRetanguloPontoQualquer(p_aux, sx, sy )); //acha a nova reta diagonal com base no ponto medio
                RetanguloGr ret_gr = new RetanguloGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), (int)rn.getP3().getX(), (int)rn.getP3().getY(), (int)rn.getP4().getX(), (int)rn.getP4().getY(), ret_load.getCorRetangulo(), ret_load.getNomeRetangulo(), ret_load.getEspRetangulo() );
                FiguraRetangulos.desenharRetangulo(g, (int)ret_gr.getP1().getX(), (int)ret_gr.getP1().getY(), (int)ret_gr.getP2().getX(),(int)ret_gr.getP2().getY(),(int)ret_gr.getP3().getX(),(int)ret_gr.getP3().getY(), (int)ret_gr.getP4().getX(),(int)ret_gr.getP4().getY(),ret_gr.getNomeRetangulo(), ret_gr.getEspRetangulo(), ret_gr.getCorRetangulo());
                armz.adicionar(ret_gr);
                armz.remover(local);
            }
        }
    }    
    
    public void escalaPrimitivoProprioEixo(double sx, double sy){
        Graphics g = getGraphics(); //para desenhar em modo grafico 
        int local = selecionarPrimitivo();//seleciona o primitivo de acordo com as coordenadas x_sel, y_sel (obtidas por meio do botão selecionar)
        if(local != sinal){
            Object obj = armz.buscar(local);
            Ponto p_aux = new Ponto(sx, sy);//cria um ponto com as coordenadas que o usuario clicou
            if(obj instanceof PontoGr){
                //ponto nao tem escala
            } else if(obj instanceof RetaGr){
                RetaGr r_load = (RetaGr) obj;
                Reta r_aux = new Reta (r_load.getP1(), r_load.getP2()); //cria reta com as mesmas especificacoes
                Reta rn = new Reta (r_aux.escalaRetaProprioEixo(sx, sy)); //acha a nova reta
                //cria e guarda a nova reta grafica na memoria
                RetaGr nova_r = new RetaGr((int)rn.getP1().getX(), (int)rn.getP1().getY(), (int)rn.getP2().getX(), (int)rn.getP2().getY(), r_load.getCorReta(), r_load.getNomeReta(), r_load.getEspReta() );
                FiguraRetas.desenharReta(g, (int)nova_r.getP1().getX(), (int)nova_r.getP1().getY(), (int)nova_r.getP2().getX(),(int)nova_r.getP2().getY(), nova_r.getNomeReta(), nova_r.getEspReta(), nova_r.getCorReta());
                armz.adicionar(nova_r);
                armz.remover(local);
            }  else if(obj instanceof CirculoGr){
                CirculoGr c_load = (CirculoGr) obj;
                Circulo c_atu = new Circulo(c_load.getCentro(), c_load.getRaio());
                Circulo c_rot = new Circulo(c_atu.escalaCirculoProprioEixo(sx) );
                CirculoGr c_novo = new CirculoGr((int)Math.round(c_rot.getCentro().getX()), (int)Math.round(c_rot.getCentro().getY()), (int)Math.round(c_rot.getRaio()), c_load.getCorCirculo(), c_load.getNomeCirculo(), c_load.getEspCirculo());
                FiguraCirculos.desenharCirculo(g, (int)Math.round(c_novo.getCentro().getX()), (int)Math.round(c_novo.getCentro().getY()), (int)Math.round(c_novo.getRaio()), c_novo.getNomeCirculo(), c_novo.getEspCirculo(), c_novo.getCorCirculo());
                armz.adicionar(c_novo);
                armz.remover(local);
            } else if(obj instanceof TrianguloGr){
                TrianguloGr tri_load = (TrianguloGr) obj;
                Triangulo t_aux = new Triangulo(tri_load.getP1(), tri_load.getP2(), tri_load.getP3());
                Triangulo tn = new Triangulo(t_aux.escalaTrianguloProprioEixo(sx, sy ));//triangulo com coordenadas novas
                //triangulo grafico com coordenadas novas
                TrianguloGr tri_gr = new TrianguloGr((int)Math.round(tn.getP1().getX()), (int)Math.round(tn.getP1().getY()), (int)Math.round(tn.getP2().getX()), (int)Math.round(tn.getP2().getY()), (int)Math.round(tn.getP3().getX()), (int)Math.round(tn.getP3().getY()), tri_load.getCorTriangulo(), tri_load.getNomeTriangulo(), tri_load.getEspTriangulo());
                armz.adicionar(tri_gr);
                armz.remover(local);            
            } else if(obj instanceof RetanguloGr){
                
            }
        }
    }    
    
    /**
     * Método selecionarPrimitivos seleciona primitivo mais proximo de acordo com as coordenadas que sao selecionadas pelo click do mouse no método selecionarCoordenada
     *
     * @return O valor de retorno
     */
    public int selecionarPrimitivo(){
        int k, local = sinal;
        Object primitivo = null;
        double menorDist = sinal; //menor distância entre o ponto selecinado e os primitivos
        Graphics g = getGraphics();  
        Ponto p_sel = new Ponto(x_sel, y_sel);
        
        if(!armz.estaVazia()){
            for(k = 0; k < armz.getQtd(); k++){
                Object obj = armz.buscar(k);
                if(obj instanceof PontoGr){
                    PontoGr p_load = (PontoGr) obj;
                    if(menorDist == sinal){
                        menorDist = p_load.calcularDistancia(p_sel);
                        local = k;
                        primitivo = p_load;
                    } else {
                        if(menorDist > p_load.calcularDistancia(p_sel)){
                            menorDist = p_load.calcularDistancia(p_sel);
                            
                            local = k;
                            primitivo = p_load;
                        }
                    }
                } else if(obj instanceof RetaGr){
                    RetaGr r_load = (RetaGr) obj;
                    if(menorDist == sinal){
                        menorDist = r_load.calcularDistancia(p_sel);
                        local = k;
                        primitivo = r_load;                    
                    } else {
                        if(menorDist > r_load.calcularDistancia(p_sel)){
                            menorDist = r_load.calcularDistancia(p_sel);
                            local = k;
                            primitivo = r_load;
                        }
                    }
                    
                } else if(obj instanceof CirculoGr){
                    CirculoGr c_load = (CirculoGr) obj;
                    
                    if(menorDist == sinal){
                        menorDist = c_load.calcularDistancia(p_sel);
                        local = k;
                        primitivo = c_load;                    
                    } else {
                        if(menorDist > c_load.calcularDistancia(p_sel)){
                            menorDist = c_load.calcularDistancia(p_sel);
                            local = k;
                            primitivo = c_load;
                        }
                    }
                    
                } else if(obj instanceof TrianguloGr){
                    TrianguloGr tri_load = (TrianguloGr) obj;
                    if(menorDist == sinal){
                        menorDist = tri_load.calcularDistancia(p_sel);
                        local = k;
                        primitivo = tri_load;
                    } else {
                        if(menorDist > tri_load.calcularDistancia(p_sel)){
                            menorDist = tri_load.calcularDistancia(p_sel);
                            local = k;
                            primitivo = tri_load;
                        }
                    }
                    
                } else if(obj instanceof RetanguloGr){
                    RetanguloGr ret_load = (RetanguloGr) obj;
                    if(menorDist == sinal){
                        menorDist = ret_load.calcularDistancia(p_sel);
                        local = k;
                        primitivo = ret_load;
                    } else {
                        if(menorDist > ret_load.calcularDistancia(p_sel)){
                            menorDist = ret_load.calcularDistancia(p_sel);
                            local = k;
                            primitivo = ret_load;
                        }
                    }
                    
                }
            }
            // if(local != sinal) {
                // armz.remover(local);
                // local = sinal;
            // }
            
        }
        //x_sel = sinal;
        //y_sel = sinal;
        //paint(g);
        menorDist = sinal;
        return local;
    }
    
}
