package br.estacio.prii.dicionario.frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import br.estacio.prii.dicionario.entidade.*;
import java.util.ArrayList;

public class FrameDicionario extends JFrame
{    
    private final String[] opcoes = { "Cadastrar", "Traduzir" };
    
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
    private final JLabel lblRodape             = new JLabel("Total de Palavras: ");
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
    private final JPanel pnlLista              = new JPanel();
    private final JPanel pnlPrincipal          = new JPanel();
    private final JPanel pnlCentral            = new JPanel();
    private final JPanel pnlBotoes             = new JPanel();
    private final JButton btnCadastrar         = new JButton("Cadastrar");
    private final JButton btnExcluir           = new JButton("Excluir");
    private final JButton btnTraduzir          = new JButton();
    private final JSeparator separadorTitulo   = new JSeparator();
    private final JSeparator separadorRodape   = new JSeparator();
	       
    public FrameDicionario (Dicionario dicionario)
    {
        setSize(610, 535);
        setTitle("Estácio 2018: Dicionário Inglês - Português");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        initDatas(dicionario);
        initEvents();
        
        setVisible(true);
    }
    
    private void initComponents()
    {
        // Labels :Propriedades
        lblTitulo.setFont(new Font("Comic Sans", Font.BOLD, 25));
        lblRodape.setFont(new Font("Comic Sans", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30,144,255));
        
        // Paineis :Layouts
        pnlCentral.setLayout(new GridLayout(1, 2, 20, 0));
        pnlCadastro.setLayout(new GridLayout(8, 1, 0, 10));
        pnlCadastro.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Cadastro"), new EmptyBorder(10, 10, 10, 10)));
        pnlBotoes.setLayout(new GridLayout(1, 2, 20, 0));
        pnlLista.setLayout(new BorderLayout(0, 10));
        pnlLista.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Dicionário"), new EmptyBorder(10, 10, 10, 10)));
        
        // Separadores :Propriedades
        separadorTitulo.setPreferredSize(new Dimension(610, 5));
	separadorTitulo.setForeground(new Color(184, 207, 229));
	separadorTitulo.setBackground(new Color(184, 207, 229));
        separadorRodape.setPreferredSize(new Dimension(610, 5));
	separadorRodape.setForeground(new Color(184, 207, 229));
	separadorRodape.setBackground(new Color(184, 207, 229));
        
        // Lista, Scroll(Rolagem) e Botão de Exclusão :Propriedades
        listPalavras.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listPalavras.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listPalavras.setVisibleRowCount(-1);
        listPalavras.setModel(modelLista);
        spnLista.setPreferredSize(new Dimension(250, 300));
        spnLista.setViewportView(listPalavras);
        btnExcluir.setPreferredSize(new Dimension(200, 34));
        
        // Menu, Itens
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
        
        // Painel de Listagem
        pnlLista.add(spnLista, "Center");
        pnlLista.add(btnExcluir, "South");
        pnlLista.repaint();
        
        // painel Cadastro
        pnlCadastro.add(lblOperacao);
        pnlCadastro.add(cbbOperacao);
        pnlCadastro.add(lblPalavra);
        pnlCadastro.add(txtPalavra);
        pnlCadastro.add(pnlBotoes);
        pnlCadastro.add(lblTraducao);
        pnlCadastro.add(txtTraducao);
        pnlCadastro.add(btnCadastrar);
        
        // Painel Central
        pnlCentral.add(pnlLista);
        pnlCentral.add(pnlCadastro);
        
        // Painel de Botões de tradução
        btnGroup.add(rbIngles);
        btnGroup.add(rbPortugues);
        pnlBotoes.add(rbIngles);
        pnlBotoes.add(rbPortugues);
        
        // Painel Principal
        pnlPrincipal.add(lblTitulo, "North");
        pnlPrincipal.add(separadorTitulo);
        pnlPrincipal.add(pnlCentral, "Center");
        pnlPrincipal.add(separadorRodape);
        pnlPrincipal.add(lblRodape, "South");
        
        setContentPane(pnlPrincipal);        
    }
    
    private void initEvents()
    {
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
    }
    
    private void initDatas(Dicionario dicionario)
    {
        ArrayList<Palavra> palavras = dicionario.getPalavras();
        
        palavras.forEach((p) -> {
            modelLista.addElement(p.getIngles() + " - " + p.getPortugues());
        });
        
        // Label Funcional
        lblRodape.setText(lblRodape.getText().concat(String.valueOf(palavras.size())));
    }
}