package br.estacio.prii.dicionario.apresentacao;


import java.util.ArrayList;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.KeyStroke;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.estacio.prii.dicionario.entidade.Palavra;
import br.estacio.prii.dicionario.entidade.Tradutor;
import br.estacio.prii.dicionario.entidade.Dicionario;

import br.estacio.prii.dicionario.dao.DicionarioDAO;

public class FrameDicionario extends JFrame
{   
    private ArrayList<Palavra> palavras        = null;
    private Dicionario dicionario              = new Dicionario();
    private final DicionarioDAO dao            = new DicionarioDAO();
    private final String opcoes[]              = {"Cadastrar", "Traduzir"};
    
    private final JMenuBar menuBar             = new JMenuBar();
    private final JMenu menuArquivo            = new JMenu("Arquivo");
    private final JMenu menuDicionario         = new JMenu("Dicionário");
    private final JMenu menuOperacao           = new JMenu("Operação");
    private final JMenuItem menuSobre          = new JMenuItem("Sobre...");
    private final JMenuItem menuSair           = new JMenuItem("Sair");
    private final JMenuItem menuSalvar         = new JMenuItem("Salvar");
    private final JMenuItem menuCarregar       = new JMenuItem("Carregar");
    private final JMenuItem menuLimpar         = new JMenuItem("Limpar");
    private final JMenuItem menuCadastrar      = new JMenuItem("Cadastrar");
    private final JMenuItem menuTraduzir       = new JMenuItem("Traduzir");
    private final JLabel lblTitulo             = new JLabel(":: Dicionário Inglês-Português ::");
    private final JLabel lblOperacaoC          = new JLabel("Operação: ");
    private final JLabel lblPalavraC           = new JLabel("Palavra: ");
    private final JLabel lblTraducaoC          = new JLabel("Tradução: ");
    private final JLabel lblOperacaoT          = new JLabel("Operação: ");
    private final JLabel lblPalavraT           = new JLabel("Palavra: ");
    private final JLabel lblTraducaoT          = new JLabel("Tradução: ");
    private final JLabel lblTraduzirParaT      = new JLabel("Traduzir para: ");
    private final JLabel lblTraduzidoT         = new JLabel("");
    private final JLabel lblRodape             = new JLabel("Total de Palavras: 0");
    private final JComboBox cbxOperacaoC       = new JComboBox(opcoes);
    private final JComboBox cbxOperacaoT       = new JComboBox(opcoes);
    private final JTextField txtPalavraC       = new JTextField(25);
    private final JTextField txtPalavraT       = new JTextField(25);
    private final JTextField txtTraducaoC      = new JTextField(25);
    private final JRadioButton rbInglesT       = new JRadioButton("Inglês", false);
    private final JRadioButton rbPortuguesT    = new JRadioButton("Português", true);
    private final ButtonGroup btnGroup         = new ButtonGroup();
    private final DefaultListModel modelLista  = new DefaultListModel();
    private final JList listPalavras           = new JList();
    private final JScrollPane spnLista         = new JScrollPane();
    private final JPanel pnlCadastro           = new JPanel();
    private final JPanel pnlTraducao           = new JPanel();
    private final JPanel pnlTraduzido          = new JPanel();
    private final JPanel pnlLista              = new JPanel();
    private final JPanel pnlPrincipal          = new JPanel();
    private final JPanel pnlCentral            = new JPanel();
    private final JPanel pnlBotoes             = new JPanel();
    private final JButton btnCadastrar         = new JButton("Cadastrar");
    private final JButton btnExcluir           = new JButton("Excluir");
    private final JButton btnTraduzir          = new JButton("Traduzir");
    private final JSeparator separadorTitulo   = new JSeparator();
    private final JSeparator separadorRodape   = new JSeparator();
         
    // Construtor
    public FrameDicionario ()
    {   
        setSize(650, 565);
        setIconImage(new ImageIcon(getClass().getResource("/icones/translate.png")).getImage());
        setTitle("Estácio - 2018 : Dicionário Inglês - Português");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        initEvents();
        
        setVisible(true);
    }
    
    // Componentes
    private void initComponents()
    {   
        // Eventos de Atalho
        menuSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
        menuSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        menuSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        menuCarregar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        menuLimpar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_MASK));
        menuCadastrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
        menuTraduzir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
               
        // Itens Menus :Icones
        menuSobre.setIcon(new ImageIcon(getClass().getResource("/icones/information.png")));
        menuSair.setIcon(new ImageIcon(getClass().getResource("/icones/door_out.png")));
        menuSalvar.setIcon(new ImageIcon(getClass().getResource("/icones/save.png")));
        menuCarregar.setIcon(new ImageIcon(getClass().getResource("/icones/open.png")));
        menuLimpar.setIcon(new ImageIcon(getClass().getResource("/icones/edit.png")));
        menuCadastrar.setIcon(new ImageIcon(getClass().getResource("/icones/add.png")));
        menuTraduzir.setIcon(new ImageIcon(getClass().getResource("/icones/refresh.png")));
        
        // Paineis :Layouts
        pnlCentral.setLayout(new BorderLayout(10, 0));
        pnlCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlCadastro.setLayout(new GridLayout(8, 1, 0, 10));
        pnlCadastro.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Cadastro"), new EmptyBorder(10, 10, 10, 10)));
        pnlTraducao.setLayout(new GridLayout(8, 1, 0, 10));
        pnlTraducao.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Tradução"), new EmptyBorder(10, 10, 10, 10)));
        pnlBotoes.setLayout(new GridLayout(1, 2, 20, 0));
        pnlTraduzido.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlLista.setLayout(new BorderLayout(0, 10));
        pnlLista.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Dicionário"), new EmptyBorder(10, 10, 10, 10)));
        
        // Separadores :Propriedades
        separadorTitulo.setPreferredSize(new Dimension(630, 5));
	separadorTitulo.setForeground(new Color(184, 207, 229));
	separadorTitulo.setBackground(new Color(184, 207, 229));
        separadorRodape.setPreferredSize(new Dimension(630, 5));
	separadorRodape.setForeground(new Color(184, 207, 229));
	separadorRodape.setBackground(new Color(184, 207, 229));
        
        // Lista, Scroll(Rolagem) e Botão de Exclusão :Propriedades
        listPalavras.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listPalavras.setLayoutOrientation(JList.VERTICAL);
        listPalavras.setVisibleRowCount(-1);
        listPalavras.setModel(modelLista);
        spnLista.setPreferredSize(new Dimension(270, 300));
        spnLista.setViewportView(listPalavras);
        btnExcluir.setPreferredSize(new Dimension(200, 34));
        
        // Labels :Propriedades
        lblTraducaoT.setFont(new Font("Comic Sans", Font.BOLD, 15));
        lblTraduzidoT.setFont(new Font("Comic Sans", Font.BOLD, 15));
        lblTraduzidoT.setForeground(new Color(30, 144, 255));
        lblTitulo.setFont(new Font("Comic Sans", Font.BOLD, 25));
        lblRodape.setFont(new Font("Comic Sans", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 144, 255));
        
        // ComboBox :Propriedades
        cbxOperacaoC.setMaximumRowCount(2);
        cbxOperacaoT.setMaximumRowCount(2);
        
        // Botões :Icones
        btnCadastrar.setIcon(new ImageIcon(getClass().getResource("/icones/add.png")));
        btnExcluir.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
        btnTraduzir.setIcon(new ImageIcon(getClass().getResource("/icones/refresh.png")));
        
        // Campos :Propriedade
        txtPalavraC.setToolTipText("Informe a palavra no idioma inglês.");
        txtPalavraT.setToolTipText("Informe a palavra a ser traduzida.");
        txtTraducaoC.setToolTipText("Informe a tradução da palavra no idioma português.");
        
        // Menu, Itens :Adicionados
        setJMenuBar(menuBar);
        menuBar.add(menuArquivo);
        menuBar.add(menuDicionario);
        menuBar.add(menuOperacao);
        menuArquivo.add(menuSobre);
        menuArquivo.add(menuSair);
        menuDicionario.add(menuSalvar);
        menuDicionario.add(menuCarregar);
        menuDicionario.add(menuLimpar);
        menuOperacao.add(menuCadastrar);
        menuOperacao.add(menuTraduzir);

        // Painel de Listagem :Adicionados
        pnlLista.add(spnLista, "Center");
        pnlLista.add(btnExcluir, "South");
        pnlLista.repaint();
        
        // Painel Traduzido (Resultado)
        pnlTraduzido.add(lblTraducaoT);
        pnlTraduzido.add(lblTraduzidoT);
         
        // Painel Tradução :Adicionados
        pnlTraducao.add(lblOperacaoT);
        pnlTraducao.add(cbxOperacaoT);
        pnlTraducao.add(lblPalavraT);
        pnlTraducao.add(txtPalavraT);
        pnlTraducao.add(lblTraduzirParaT);
        pnlTraducao.add(pnlBotoes);
        pnlTraducao.add(btnTraduzir);
        pnlTraducao.add(pnlTraduzido);
        
        pnlTraducao.setVisible(false);
        
        // Painel Cadastro :Adicionados
        pnlCadastro.add(lblOperacaoC);
        pnlCadastro.add(cbxOperacaoC);
        pnlCadastro.add(lblPalavraC);
        pnlCadastro.add(txtPalavraC);
        pnlCadastro.add(lblTraducaoC);
        pnlCadastro.add(txtTraducaoC);
        pnlCadastro.add(new JLabel());
        pnlCadastro.add(btnCadastrar);
        
        // Painel Central :Adicionados
        pnlCentral.add(pnlLista, "West");
        pnlCentral.add(pnlCadastro, "East");
        pnlCentral.add(pnlTraducao);
        
        // Painel de Botões de tradução :Adicionados
        btnGroup.add(rbInglesT);
        btnGroup.add(rbPortuguesT);
        pnlBotoes.add(rbInglesT);
        pnlBotoes.add(rbPortuguesT);
        
        // Painel Principal :Adicionados
        pnlPrincipal.add(lblTitulo, "North");
        pnlPrincipal.add(separadorTitulo);
        pnlPrincipal.add(pnlCentral, "Center");
        pnlPrincipal.add(separadorRodape);
        pnlPrincipal.add(lblRodape, "South");
        
        setContentPane(pnlPrincipal);        
    }
    
    // Eventos
    private void initEvents()
    {
        // Eventos da Janela
        addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent we) 
            {
                String opcoes[] = {"Sim", "Não"};
        
                int resposta =  JOptionPane.showOptionDialog(
                                    null, "Você deseja realmente sair do dicionário?", "CONFIRMAÇÃO DE SAÍDA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]
                                );

                if (resposta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void windowOpened(WindowEvent we) 
            {
                modelLista.clear();
                
                dao.ler();
                
                dicionario = dao.getDicionario();
                palavras   = dicionario.getPalavras();
                
                palavras.forEach((Palavra palavra) -> {
                    modelLista.addElement(palavra.getIngles() + " - " + palavra.getPortugues());
                });
                
                dicionario.setPalavras(palavras);

                lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
            }
   
        });
        
        // Eventos do Menu
        menuSobre.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(
                this, "Desenvolvido por:\n\nClaudia Mendes Fabris\nDhonata Freitas Holanda\nMaurício de Souza Porfírio", "SOBRE NÓS", JOptionPane.INFORMATION_MESSAGE
            );
        });
        
        menuSair.addActionListener((ActionEvent ae) -> {
            String opcoes[] = {"Sim", "Não"};
        
            int resposta =  JOptionPane.showOptionDialog(
                                null, "Você deseja realmente sair do dicionário?", "CONFIRMAÇÃO DE SAÍDA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]
                            );

            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });   
        
        menuSalvar.addActionListener((ActionEvent ae) -> {
            
            if(dao.gravar(dicionario)){
                JOptionPane.showMessageDialog(
                    this, "Dicionário salvo com sucesso!", "DICIONÁRIO SALVO", JOptionPane.QUESTION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this, "Houve um erro com o salvamento!", "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE
                );
            }            
        });   
        
        menuCarregar.addActionListener((ActionEvent ae) -> {
            modelLista.clear();
            
            dao.ler();
            
            dicionario = dao.getDicionario();
            palavras   = dicionario.getPalavras();

            palavras.forEach((Palavra palavra) -> {
                modelLista.addElement(palavra.getIngles() + " - " + palavra.getPortugues());
            });

            dicionario.setPalavras(palavras);

            lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
        });   
        
        menuLimpar.addActionListener((ActionEvent ae) -> {
            modelLista.clear();
            palavras.clear();
            
            lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
        });
        
        menuCadastrar.addActionListener((ActionEvent ae) -> {
            pnlCadastro.setVisible(true);
            pnlTraducao.setVisible(false);
            
            txtPalavraC.requestFocus();
            cbxOperacaoC.setSelectedIndex(0);
        });
        
        menuTraduzir.addActionListener((ActionEvent ae) -> {
            pnlCadastro.setVisible(false);
            pnlTraducao.setVisible(true);
            
            txtPalavraT.requestFocus();
            cbxOperacaoT.setSelectedIndex(1);
        });
        
        // Eventos do ComboBox
        cbxOperacaoC.addItemListener((ItemEvent ie) -> {
            if(cbxOperacaoC.getSelectedIndex() == 0) {
                pnlTraducao.setVisible(false);
                pnlCadastro.setVisible(true);

                txtPalavraC.requestFocus();
            } else {
                pnlTraducao.setVisible(true);
                pnlCadastro.setVisible(false);

                txtPalavraT.requestFocus();
            } 
            
            cbxOperacaoT.setSelectedIndex(1);
        });
        
        cbxOperacaoT.addItemListener((ItemEvent ie) -> {
            if(cbxOperacaoT.getSelectedIndex() == 0) {
                pnlTraducao.setVisible(false);
                pnlCadastro.setVisible(true);

                txtPalavraC.requestFocus();
            } else {
                pnlTraducao.setVisible(true);
                pnlCadastro.setVisible(false);

                txtPalavraT.requestFocus();
            } 
            
            cbxOperacaoC.setSelectedIndex(0);
        });
        
        // Eventos dos Botões
        btnCadastrar.addActionListener((ActionEvent ae) -> {
            if(txtPalavraC.getText().isEmpty() || txtTraducaoC.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Por favor, preencha todos os campos.", "CAMPOS VAZIOS", JOptionPane.WARNING_MESSAGE
                );
            } else {
                dicionario.adicionar(txtTraducaoC.getText().trim(), txtPalavraC.getText().trim());
                
                palavras = dicionario.getPalavras();
        
                palavras.forEach((Palavra palavra) -> {
                    modelLista.addElement(palavra.getIngles() + " - " + palavra.getPortugues());

                    // Verificar palavras iguais
                    for(int i = 0; i < modelLista.getSize(); i++) {
                        for(int j = i + 1; j < modelLista.getSize(); j++) {
                            if(modelLista.getElementAt(i).equals(modelLista.getElementAt(j)) ) {
                                modelLista.removeElementAt(j);
                                j--;
                            }
                        }
                    }
                });
                
                txtPalavraC.setText("");
                txtTraducaoC.setText(""); 
                
                lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
            }
            
            txtPalavraC.requestFocus();
        });
        
        btnExcluir.addActionListener((ActionEvent ae) -> {
            if(listPalavras.getModel().getSize() == 0) {
                JOptionPane.showMessageDialog(
                    this, "Não existe nenhuma palavra cadastrada.", "LISTA VAZIA", JOptionPane.WARNING_MESSAGE
                );  
            } else {
                if(listPalavras.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(
                        this, "Nenhuma palavra foi selecionado.", "PALAVRA NÃO SELECIONADO", JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    String opcoes[] = {"Sim", "Não"};
                    
                    int resposta =  JOptionPane.showOptionDialog(
                                        null, "Você deseja realmente excluir essa palavra? ", "CONFIRMAÇÃO DE EXCLUSÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]
                                    );

                    if (resposta == JOptionPane.YES_OPTION) {
                        String item = String.valueOf(listPalavras.getSelectedValue());

                        dicionario.remover(item);
                        modelLista.removeElement(item);

                        lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
                    }   
                }               
            }
        });

        btnTraduzir.addActionListener((ActionEvent ae) -> {
            if(txtPalavraT.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Por favor, preencha o campo PALAVRA.", "CAMPO VAZIO", JOptionPane.WARNING_MESSAGE
                );
            } else {
                String radio = rbInglesT.isSelected() ? rbInglesT.getText() : rbPortuguesT.getText();
              
                if(radio.equals("Inglês")) {
                    lblTraduzidoT.setText(new Tradutor(dicionario).traduzirParaIngles(txtPalavraT.getText()));
                } else {
                    lblTraduzidoT.setText(new Tradutor(dicionario).traduzirParaPortugues(txtPalavraT.getText()));
                }
            }
            
            txtPalavraT.requestFocus();
        });
        
    }
    
}