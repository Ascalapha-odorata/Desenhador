
import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import java.awt.*;
import java.math.*;
import java.text.*;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.InternationalFormatter;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
/**
 * Cria a interface com o usuario (GUI)
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;
    private TipoPrimitivo ultimoTipo = TipoPrimitivo.NENHUM;
    
    private TipoOperacao tipoOp = TipoOperacao.NENHUM;

    // Cor atual
    private Color corAtual = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 1;
    
    // Angulo atual do primitivo
    int stdAngulo = 0;
    private int angAtual = stdAngulo;
    private int ultimoAngulo = angAtual;
    
    //Escala dos primitivos
    private double sx = 0, sy = 0;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, tipoOp, corAtual, 10);

    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbRetangulo = new JButton("Retangulo");
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbSelecionar = new JButton("Selecionar");
    private JButton jbDeletar = new JButton("Deletar");
    private JButton jbTranslacao = new JButton("Translacao");
    private JButton jbRotacao_pto_qq = new JButton("Rotacao (angulo e ponto) ");
    private JButton jbEscala_pto_qq = new JButton("Escala (angulo e ponto)");

    private JButton jbCor = new JButton("Cor");
    private JButton jbSair = new JButton("Sair");

    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 1));
    private JSlider jsEsp = new JSlider(1, 50, 1);
    
    private JLabel jlAng = new JLabel("   Rotaçao: " + String.format("%-5s", 0));
    private JSlider jsAng = new JSlider(-360, 360, 0);
    
    private JButton jbInfo = new JButton("Info");
    
    boolean translacao = false;
    boolean rotacao_pto_qq = false;
    boolean escala_pto_qq = false;
    
    boolean selecionou = false;
    
    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {
        /**
         * Definicoes de janela
         */
        super("Programa Desenhador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        setResizable(false);
        
        areaDesenho.set_dim(larg, alt);

        // Adicionando os componentes
        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbSelecionar); //Botao de selecionar
        barraComandos.add(jbDeletar); //Botao de deletar
        barraComandos.add(jbTranslacao);
        barraComandos.add(jbRotacao_pto_qq);
        barraComandos.add(jbEscala_pto_qq);
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos.add(jbCor); // Botao de Cores

        barraComandos.add(jlEsp); // Label para espessura
        barraComandos.add(jsEsp);    // Slider para espacamento
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        
        barraComandos.add(jlAng); // Label para espessura
        barraComandos.add(jsAng);    // Slider para espacamento
        areaDesenho.setAng(angAtual); // define a espessura inicial
        
        barraComandos.add(jbInfo); // Botao de Informaçoes
        barraComandos.add(jbSair); // Botao de Cores
        
        

        // adiciona os componentes com os respectivos layouts
        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);
        
        

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.PONTO;
            areaDesenho.setTipo(tipoAtual);
            ultimoTipo = tipoAtual;
            tipoOp = TipoOperacao.NENHUM;
            areaDesenho.setOp(tipoOp);
            selecionou = false;
            
        });        
        jbReta.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETA;
            areaDesenho.setTipo(tipoAtual);
            ultimoTipo = tipoAtual;
            tipoOp = TipoOperacao.NENHUM;
            areaDesenho.setOp(tipoOp);
            selecionou = false;
            
        });        
        jbCirculo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.CIRCULO;
            areaDesenho.setTipo(tipoAtual);
            ultimoTipo = tipoAtual;
            tipoOp = TipoOperacao.NENHUM;
            areaDesenho.setOp(tipoOp);
            selecionou = false;
            
        });
        jbTriangulo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.TRIANGULO;
            areaDesenho.setTipo(tipoAtual);
            ultimoTipo = tipoAtual;
            tipoOp = TipoOperacao.NENHUM;
            areaDesenho.setOp(tipoOp);
            selecionou = false;
            
        });
        jbRetangulo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETANGULO;
            areaDesenho.setTipo(tipoAtual);
            ultimoTipo = tipoAtual;
            tipoOp = TipoOperacao.NENHUM;
            areaDesenho.setOp(tipoOp);
            selecionou = false;
            
        });
        
        jbSelecionar.addActionListener(e -> { //selecionar 1 primitivo
            tipoOp = TipoOperacao.SELECT;
            areaDesenho.setOp(tipoOp);
            
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual);
            
            areaDesenho.selecionarPrimitivo();
            
            selecionou = true;
        }); 
        
        jbDeletar.addActionListener(e -> { //deletar 1 primitivo selecionado
            tipoOp = TipoOperacao.DELETAR;
            areaDesenho.setOp(tipoOp);
            
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual);
            if(selecionou){
                areaDesenho.deletarPrimitivo();
            }
            
            
            //areaDesenho.removeAll();
            //jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivo da tela)
            repaint();
            areaDesenho.apagar_sombra(larg, alt);
            selecionou = false;
        });
        
        areaDesenho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int x_p = e.getX();
                int y_p = e.getY();
                
                if (translacao) { 
                    areaDesenho.translacaoPrimitivo(x_p, y_p);
                    translacao = false;
                    repaint();
                    areaDesenho.apagar_sombra(larg, alt);
                } else if(rotacao_pto_qq){
                    areaDesenho.rotacaoPrimitivoPontoQualquer(x_p, y_p);
                    rotacao_pto_qq = false;
                    repaint();
                    areaDesenho.apagar_sombra(larg, alt);
                } else if (escala_pto_qq){
                    areaDesenho.escalaPrimitivoPontoQualquer(x_p, y_p, sx, sy);
                    escala_pto_qq = false;
                    repaint();
                    areaDesenho.apagar_sombra(larg, alt);
                }            
                
            }
        });
        
        jbTranslacao.addActionListener(e -> { //translacao de um primitivo
            tipoOp = TipoOperacao.TRANSLACAO;
            areaDesenho.setOp(tipoOp);
            
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual);
            
            translacao = true;
            
            selecionou = false;
        }); 
        
        jbRotacao_pto_qq.addActionListener(e -> { //rotacao de um primitivo
            tipoOp = TipoOperacao.ROTACAO;
            areaDesenho.setOp(tipoOp);
            
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual);
            
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Rotacao");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new GridLayout(3, 1, 5, 5));
                
                //formato para inteiros
                NumberFormat intF = NumberFormat.getIntegerInstance();
                intF.setGroupingUsed(false); // para nao poder colocar virgula
    
                // t1 para o angulo
                JFormattedTextField f1 = new JFormattedTextField(intF);
                f1.setColumns(10);
                f1.setValue(0); 
                
                
                // label para o textfield
                frame.add(new JLabel("Digite o angulo: "));
                frame.add(f1);
                
                // botao de enviar
                JButton jbEnviar = new JButton("Enviar");
                frame.add(jbEnviar);
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                jbEnviar.addActionListener(ae -> {               
                    int ang = 0;
                    try{
                        ang = ((Number) f1.getValue()).intValue();
                        if(ang >= -360 && ang <= 360){
                            frame.setVisible(false);
                            areaDesenho.setAng(ang);
                            rotacao_pto_qq = true;
                            //repaint();
                            //areaDesenho.apagar_sombra(larg, alt);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Coordenadas Invalidas!");
                        }                   
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Coordenadas Invalidas!");
                    }
                });
                
                
                
            });   
            
            //rotacao_pto_qq = true;
            selecionou = false;
        });
        
        jbEscala_pto_qq.addActionListener(e -> { //escala de um primitivo
            tipoOp = TipoOperacao.ESCALA;
            areaDesenho.setOp(tipoOp);
            
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual);
            if(selecionou){//se o usuario selecionou algum primitivo         
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Escala");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLayout(new GridLayout(3, 2, 5, 5));
                    double escalaStd = 1;
                    
                    //formato para numeros
                    NumberFormat decF = NumberFormat.getNumberInstance();
                    decF.setGroupingUsed(false); // para nao poder colocar virgula
                    decF.setMaximumFractionDigits(2);//apenas 2 digitos decimais
                    
                    NumberFormatter formatador = new NumberFormatter(decF);
                    formatador.setAllowsInvalid(true); // deixa o input invalido provisoriamente
                    formatador.setMinimum(0.0); // menor valor > 0
                    formatador.setMaximum(500.0); // maior valor
                    // t1 para o sx
                    JFormattedTextField f1 = new JFormattedTextField(formatador);
                    f1.setColumns(10);
                    f1.setValue(escalaStd);
                    
                    f1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            if (e.getKeyChar() == '.') {
                                e.consume(); // consome o evento para ele naos er processado da maneira padrao por quem o originou
                                f1.setText(f1.getText() + ","); // troca por virgula
                            }
                        }
                    });
                    
                    // t2 para o sy
                    JFormattedTextField f2 = new JFormattedTextField(formatador);
                    f2.setColumns(10);
                    f2.setValue(escalaStd); 
        
                    // labels para os textfields
                    frame.add(new JLabel("Digite SX: "));
                    frame.add(f1);
                    
                    frame.add(new JLabel("Digite SY: "));
                    frame.add(f2);
                    
                    f2.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            if (e.getKeyChar() == '.') {
                                e.consume(); //  consome o evento para ele naos er processado da maneira padrao por quem o originou
                                f2.setText(f2.getText() + ","); // troca por virgula
                            }
                        }
                    });
                    
                    // botao de enviar
                    JButton jbEnviar = new JButton("Enviar");
                    frame.add(jbEnviar);
                    
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    
                    jbEnviar.addActionListener(ae -> {               
                        double es_sx = escalaStd;
                        double es_sy = escalaStd;
                        try{
                            es_sx = ((Number) f1.getValue()).doubleValue();
                            es_sy = ((Number) f2.getValue()).doubleValue();
                            if(es_sx >0 && es_sy > 0 && entradaValida(es_sx, es_sy)){
                                frame.setVisible(false);
                                //areaDesenho.escalaPrimitivoProprioEixo(es_sx, es_sy);
                                escala_pto_qq = true;
                                sx = es_sx;
                                sy = es_sy;
                                //repaint();
                                //areaDesenho.apagar_sombra(larg, alt);
                            } else {
                                if(! entradaValida(es_sx, es_sy)){
                                    JOptionPane.showMessageDialog(frame, "As coordenadas par ponto e circulo precisam ser iguais!");
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Coordenadas Invalidas!");
                                }
                            }                   
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Coordenadas Invalidas!");
                        }
                    });
                });                
            }
            
            selecionou = false;
            
        });
        
        
        jbLimpar.addActionListener(e -> { //limpar toda a tela 
            //tipoAtual = TipoPrimitivo.LIMPAR;
            //areaDesenho.setTipo(tipoAtual);
            String mensagem = "Deseja deletar todo o seu desenho?";
            int result = JOptionPane.showConfirmDialog((Component) null, mensagem,"Atencao!", JOptionPane.OK_CANCEL_OPTION);
            if(result == 0){//se clicou em ok
                areaDesenho.removeAll();
                jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivo da tela)
                repaint();
                areaDesenho.limparTela(larg, alt);
                //areaDesenho.apagar_sombra(larg, alt);
            }
            selecionou = false;
        });  
        
        jbCor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
            if (c != null){ 
                corAtual = c; // pega do chooserColor 
            }            
            areaDesenho.setCorAtual(corAtual); // cor atual
            
            selecionou = false;
        });
        
        jsEsp.addChangeListener(e -> {
            espAtual = jsEsp.getValue();
            jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
            areaDesenho.setEsp(espAtual);
            
            selecionou = false;
        });
        
        jsAng.addChangeListener(e -> {
            ultimoAngulo = angAtual;
            angAtual = jsAng.getValue();
            jlAng.setText("   Rotacao: "+ String.format("%-5s", angAtual));
            //areaDesenho.setAng(angAtual);
            if((tipoOp==TipoOperacao.SELECT) || (tipoOp==TipoOperacao.ROT_PROPRIO_EIXO)){
                /*se nao foi escolhido o botao de rotaçao ao redor de um ponto em especifico na tela, ou seja, se o usuario apenas selecionou
                o primitivo e foi direto no slider com os angulos, sem apertar o botao de rotacao, a opçao eh de rotacao em relaçao ao eixo da figura selecionada */
                
                areaDesenho.setAng(angAtual - ultimoAngulo);
                areaDesenho.rotacaoPrimitivoProprioEixo();
                
                tipoOp = TipoOperacao.ROT_PROPRIO_EIXO;
                areaDesenho.setOp(tipoOp);
                repaint();
                areaDesenho.apagar_sombra(larg, alt);
            } 
            selecionou = false;
        });       

        jbSair.addActionListener(e -> {
            System.exit(0);
        });
        
        jbInfo.addActionListener(e -> {
            String informacoes = ("\nPrograma Desenhador\n\n" +
            "Há várias opções de desenhos para fazer!\n\n" +
            "Para desenhar, clique no tipo de desenho que deseja, escolha uma espessura para o pincel no slider “Espessura” e uma cor.\n\n"+
            "Para selecionar uma cor, clique em “Cor”, escolha uma cor, e pressione “Ok”.\n\n"+
            "Para selecionar um primitivo, clique no botão “Selecionar”, e depois no primitivo.\n\n"+
            "Para deletar, selecione um primitivo, e clique em “Deletar”.\n\n"+
            "Para mudar a localização de um primitivo, selecione o primitivo, clique em “Translação”, "+
            "e clique no local em que deseja colocá-lo.\n\n"+
            "Para rotacionar um primitivo, selecione o primitivo e deslize o slider indicado por ”Rotação”\n\n"+
            "Para rotacionar um primitivo em relação a um ponto de sua escolha, selecione o"+
            "primitivo, clique em “Rotação (ângulo e ponto)”,\ndigite o ângulo desejado, e depois "+
            "clique no ponto que o primitivo deverá usar como referência para rotação.\n\n"+
            "Para modificar o tamanho de um primitivo, selecione o primitivo, clique em “Escala”"+
            ", digite o\nfator de escala e selecione o ponto que o primitivo deverá usar como referência para escala."+
            "\n\nPara apagar o seu desenho, clique em “Limpar”."+
            "\n\nPara sair, clique no “X”, ou em “Sair”.\n");
            JOptionPane.showMessageDialog(null, informacoes);            
        });         
    }
    
    /**
     * Method entradaValida verifica se as coordenadas sx e sy, no caso de escala, sao iguais para os formatos circulo e ponto 
     *
     * @param sx A parameter
     * @param sy A parameter
     * @return The return value
     */
    public boolean entradaValida(double sx, double sy){
        boolean ok = true;
        if((this.ultimoTipo == TipoPrimitivo.PONTO) || (this.ultimoTipo == TipoPrimitivo.CIRCULO) ){
            if(sx != sy){
                ok = false;
            }
        }
        return ok;
    }   
}
