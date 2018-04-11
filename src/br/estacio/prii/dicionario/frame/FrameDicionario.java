package br.estacio.prii.dicionario.frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import br.estacio.prii.dicionario.entidade.*;
import br.estacio.prii.dicionario.persistencia.Arquivo;
import java.util.ArrayList;

public class FrameDicionario extends JFrame
{   
    private final Dicionario dicionario        = new Dicionario();
    private ArrayList<Palavra> palavras        = null;
    private final String[] opcoes              = {"Cadastrar", "Traduzir"};
    
    private final JMenuBar menuBar             = new JMenuBar();
    private final JMenu menuArquivo            = new JMenu("Arquivo");
    private final JMenu menuDicionario         = new JMenu("Dicionário");
    private final JMenu menuOperacao           = new JMenu("Operação");
    private final JMenuItem menuSobre          = new JMenuItem("Sobre...");
    private final JMenuItem menuSair           = new JMenuItem("Sair");
    private final JMenuItem menuSalvar         = new JMenuItem("Salvar");
    private final JMenuItem menuCarregar       = new JMenuItem("Carregar");
    private final JMenuItem menuCadastrar      = new JMenuItem("Cadastrar");
    private final JMenuItem menuTraduzir       = new JMenuItem("Traduzir");
    private final JLabel lblTitulo             = new JLabel(":: Dicionário Inglês-Português ::");
    private final JLabel lblOperacao           = new JLabel("Operação: ");
    private final JLabel lblPalavra            = new JLabel("Palavra: ");
    private final JLabel lblTraducao           = new JLabel("Tradução: ");
    private final JLabel lblTradzirPara        = new JLabel("Traduzir para: ");
    private final JLabel lblTraduzido          = new JLabel("");
    private final JLabel lblRodape             = new JLabel("Total de Palavras: 0");
    private final JComboBox cbbOperacao        = new JComboBox(opcoes);
    private final JTextField txtPalavra        = new JTextField(10);
    private final JTextField txtTraducao       = new JTextField(10);
    private final JRadioButton rbIngles        = new JRadioButton("Inglês");
    private final JRadioButton rbPortugues     = new JRadioButton("Português");
    private final ButtonGroup btnGroup         = new ButtonGroup();
    private final DefaultListModel modelLista  = new DefaultListModel();
    private final JList listPalavras           = new JList(modelLista);
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
    private final JSeparator separadorBotao    = new JSeparator();
         
    public FrameDicionario ()
    {   
        setSize(610, 535);
        setIconImage(new ImageIcon(getClass().getResource("/icones/translate.png")).getImage());
        setTitle("Estácio 2018: Dicionário Inglês - Português");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        initEvents();
        
        setVisible(true);
    }
    
    private void initComponents()
    {
        // Itens Menus :Icones
        menuSobre.setIcon(new ImageIcon(getClass().getResource("/icones/information.png")));
        menuSair.setIcon(new ImageIcon(getClass().getResource("/icones/door_out.png")));
        menuSalvar.setIcon(new ImageIcon(getClass().getResource("/icones/save.png")));
        menuCarregar.setIcon(new ImageIcon(getClass().getResource("/icones/open.png")));
        menuCadastrar.setIcon(new ImageIcon(getClass().getResource("/icones/add.png")));
        menuTraduzir.setIcon(new ImageIcon(getClass().getResource("/icones/refresh.png")));
        
        // Labels :Propriedades
        lblTraduzido.setForeground(new Color(30, 144, 255));
        lblTitulo.setFont(new Font("Comic Sans", Font.BOLD, 25));
        lblRodape.setFont(new Font("Comic Sans", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 144, 255));
        
        // Botões :Icones
        btnCadastrar.setIcon(new ImageIcon(getClass().getResource("/icones/add.png")));
        btnExcluir.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
        btnTraduzir.setIcon(new ImageIcon(getClass().getResource("/icones/refresh.png")));
        
        // Paineis :Layouts
        pnlCentral.setLayout(new GridLayout(1, 2, 20, 0));
        pnlCadastro.setLayout(new GridLayout(8, 1, 0, 10));
        pnlCadastro.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Cadastro"), new EmptyBorder(10, 10, 10, 10)));
        pnlTraducao.setLayout(new GridLayout(8, 1, 0, 10));
        pnlTraducao.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Traduzir"), new EmptyBorder(10, 10, 10, 10)));
        pnlBotoes.setLayout(new GridLayout(1, 2, 20, 0));
        pnlTraduzido.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlLista.setLayout(new BorderLayout(0, 10));
        pnlLista.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Dicionário"), new EmptyBorder(10, 10, 10, 10)));
        
        // Separadores :Propriedades
        separadorTitulo.setPreferredSize(new Dimension(610, 5));
	separadorTitulo.setForeground(new Color(184, 207, 229));
	separadorTitulo.setBackground(new Color(184, 207, 229));
        separadorRodape.setPreferredSize(new Dimension(610, 5));
	separadorRodape.setForeground(new Color(184, 207, 229));
	separadorRodape.setBackground(new Color(184, 207, 229));
        separadorBotao.setPreferredSize(new Dimension(100, 5));
        
        // Lista, Scroll(Rolagem) e Botão de Exclusão :Propriedades
        listPalavras.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listPalavras.setLayoutOrientation(JList.VERTICAL);
        listPalavras.setVisibleRowCount(-1);
        listPalavras.setModel(modelLista);
        spnLista.setPreferredSize(new Dimension(250, 300));
        spnLista.setViewportView(listPalavras);
        btnExcluir.setPreferredSize(new Dimension(200, 34));
        
        // Menu, Itens :Adicionados
        setJMenuBar(menuBar);
        menuBar.add(menuArquivo);
        menuBar.add(menuDicionario);
        menuBar.add(menuOperacao);
        menuArquivo.add(menuSobre);
        menuArquivo.add(menuSair);
        menuDicionario.add(menuSalvar);
        menuDicionario.add(menuCarregar);
        menuOperacao.add(menuCadastrar);
        menuOperacao.add(menuTraduzir);

        // Painel de Listagem :Adicionados
        pnlLista.add(spnLista, "Center");
        pnlLista.add(btnExcluir, "South");
        pnlLista.repaint();
        
        // Painel Traduzido (Resultado)
        pnlTraduzido.add(lblTraducao);
        pnlTraduzido.add(lblTraduzido);
         
        // Painel Tradução :Adicionados
        pnlTraducao.add(lblOperacao);
        pnlTraducao.add(cbbOperacao);
        pnlTraducao.add(lblPalavra);
        pnlTraducao.add(txtPalavra);
        pnlTraducao.add(lblTradzirPara);
        pnlTraducao.add(pnlBotoes);
        pnlTraducao.add(btnTraduzir);
        pnlTraducao.add(pnlTraduzido);
        
        // Painel Cadastro :Adicionados
//        pnlCadastro.add(lblOperacao);
//        pnlCadastro.add(cbbOperacao);
//        pnlCadastro.add(lblPalavra);
//        pnlCadastro.add(txtPalavra);
//        pnlCadastro.add(lblTraducao);
//        pnlCadastro.add(txtTraducao);
//        pnlCadastro.add(new JLabel());
//        pnlCadastro.add(btnCadastrar);
        
        // Painel Central :Adicionados
        pnlCentral.add(pnlLista);
        pnlCentral.add(pnlTraducao);
        
        // Painel de Botões de tradução :Adicionados
        btnGroup.add(rbIngles);
        btnGroup.add(rbPortugues);
        pnlBotoes.add(rbIngles);
        pnlBotoes.add(rbPortugues);
        
        // Painel Principal :Adicionados
        pnlPrincipal.add(lblTitulo, "North");
        pnlPrincipal.add(separadorTitulo);
        pnlPrincipal.add(pnlCentral, "Center");
        pnlPrincipal.add(separadorRodape);
        pnlPrincipal.add(lblRodape, "South");
        
        setContentPane(pnlPrincipal);        
    }
    
    private void initEvents()
    {
        // Eventos do Menu
        menuSair.addActionListener((ActionEvent ae) -> {
            
            String[] options = {"Sim", "Não"};
            int resposta =  JOptionPane.showOptionDialog(
                                null,
                                "Você deseja realmente sair do dicionário?",
                                "Confirmação de Saída",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null, options, options[0]
                            );
            
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });   
        
        menuSalvar.addActionListener((ActionEvent ae) -> {
            new Arquivo().gravar(palavras);
        });   
        
        menuCarregar.addActionListener((ActionEvent ae) -> {
            modelLista.clear();
            
            palavras = new Arquivo().ler();
            
            palavras.forEach((Palavra p) -> {
                modelLista.addElement(p.getIngles() + " - " + p.getPortugues());
            });
            
            dicionario.setPalavras(palavras);
            
            // Label Funcional
            lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
        });   
        
        // Eventos dos Botões
        btnCadastrar.addActionListener((ActionEvent ae) -> {
            if(txtPalavra.getText().isEmpty() || txtTraducao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Por favor, preencha todos os campos.", "CAMPOS VAZIOS", JOptionPane.WARNING_MESSAGE
                );
            } else {
                dicionario.adicionar(txtTraducao.getText(), txtPalavra.getText());
                
                palavras = dicionario.getPalavras();
        
                palavras.forEach((Palavra p) -> {
                    modelLista.addElement(p.getIngles() + " - " + p.getPortugues());

                    for(int i = 0; i < modelLista.getSize(); i++) {
                        for(int j = i + 1; j < modelLista.getSize(); j++) {
                            if(modelLista.getElementAt(i).equals(modelLista.getElementAt(j)) ) {
                                modelLista.removeElementAt(j);
                                j--;
                            }
                        }
                    }
                });
                
                txtPalavra.setText("");
                txtTraducao.setText(""); 
                
                // Label Funcional
                lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
            }
        });
        
        btnExcluir.addActionListener((ActionEvent ae) -> {
            if(listPalavras.getModel().getSize() == 0) {
                JOptionPane.showMessageDialog(
                    this, "Não existe nenhum item cadastrado.", "LISTA VAZIA", JOptionPane.WARNING_MESSAGE
                );  
            } else {
                if(listPalavras.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(
                        this, "Nenhum item foi selecionado.", "ITEM NÃO SELECIONADO", JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    String[] options = {"Sim", "Não"};
                    int resposta =  JOptionPane.showOptionDialog(
                                        null,
                                        "Você deseja realmente excluir essa palavra? ",
                                        "Confirmação de Exclusão",
                                        JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.WARNING_MESSAGE,
                                        null, options, options[0]
                                    );

                    if (resposta == JOptionPane.YES_OPTION) {
                        String item = String.valueOf(listPalavras.getSelectedValue());

                        dicionario.remover(item);
                        modelLista.removeElement(item);

                        // Label Funcional
                        lblRodape.setText("Total de Palavras: " + Integer.toString(modelLista.getSize()));
                    }   
                }               
            }
        });

        btnTraduzir.addActionListener((ActionEvent ae) -> {
            if(txtPalavra.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Por favor, preencha o campo PALAVRA.", "CAMPO VAZIO", JOptionPane.WARNING_MESSAGE
                );
            } else {
                String radio = rbIngles.isSelected() ? rbIngles.getText() : rbPortugues.getText();
                
                if(!(rbIngles.isSelected() || rbPortugues.isSelected())) {
                    JOptionPane.showMessageDialog(
                        this, "Por favor, marque uma das opções (Inglês ou Português).", "CAMPO DESMARCADO", JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    if(radio.equals("Inglês")) {
                        lblTraduzido.setText(new Tradutor(dicionario).traduzirParaIngles(txtPalavra.getText()));
                    } else {
                        lblTraduzido.setText(new Tradutor(dicionario).traduzirParaPortugues(txtPalavra.getText()));
                    }
                }
            }
        });
    }

    
}