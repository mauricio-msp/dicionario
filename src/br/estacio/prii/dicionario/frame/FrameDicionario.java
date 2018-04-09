package br.estacio.prii.dicionario.frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import br.estacio.prii.dicionario.entidade.*;
import java.util.ArrayList;

public class FrameDicionario extends JFrame
{    
    private Dicionario dicionario = new Dicionario();
    
    private final String[] opcoes = { "Cadastrar", "Traduzir" };
    private final Font fonteDicionario = new Font("Comic Sans", Font.BOLD, 25);
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuArquivo = new JMenu("Arquivo");
    private final JMenu menuDicionario = new JMenu("Dicionário");
    private final JMenu menuOperacao = new JMenu("Operação");
    private final JMenuItem menuSobre = new JMenuItem("Sobre...");
    private final JMenuItem menuSair = new JMenuItem("Sair");
    private final JMenuItem menuSalvar = new JMenuItem("Salvar");
    private final JMenuItem menuCarregar = new JMenuItem("Carregar");
    private final JMenuItem menuCadastrar = new JMenuItem("Cadastrar");
    private final JMenuItem menuTraduzir = new JMenuItem("Traduzir");
    private final JLabel lblTitulo = new JLabel("* Dicionário Inglês-Português *");
    private final JLabel lblOperacao = new JLabel("Operação: ");
    private final JLabel lblPalavra = new JLabel("Palavra: ");
    private final JLabel lblRodape = new JLabel("Total de Palavras: ");
    private final JComboBox cbbOperacao = new JComboBox(opcoes);
    private final JTextField txtPalavra = new JTextField(10);
    private final JRadioButton rbIngles = new JRadioButton("Inglês");
    private final JRadioButton rbPortugues = new JRadioButton("Português");
    private final DefaultListModel modelLista = new DefaultListModel();
    private final JList listPalavras = new JList(modelLista);
    private final JScrollPane spnLista = new JScrollPane(listPalavras);
    private final JPanel pnlFuncoes = new JPanel();
    private final JPanel pnlLista = new JPanel();
    private final JPanel pnlPrincipal = new JPanel();
    private final JPanel pnlCentral = new JPanel();
    private final JButton btnCadastrar = new JButton("Cadastrar");
    private final JButton btnTraduzir = new JButton();
    private final JSeparator separador = new JSeparator();
	       
    public FrameDicionario ()
    {
        setSize(400,400);
        setTitle("Estácio 2018 - Dicionário Inglês - Português");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        initComponents();
        initEvents();
        
        setVisible(true);
    }
    
    private void initComponents()
    {
        lblTitulo.setFont(fonteDicionario);
        lblRodape.setFont(fonteDicionario);
        lblTitulo.setForeground(Color.RED);
        
        pnlCentral.setLayout(new GridLayout(1,2));
        pnlFuncoes.setLayout(new GridLayout(8,1));
        pnlLista.setLayout(new FlowLayout());
        
        separador.setPreferredSize(new Dimension(800, 5));
	separador.setForeground(new java.awt.Color(0, 0, 255));
	separador.setBackground(new java.awt.Color(0, 64, 128));
	separador.setSize(800, 150);
        
        listPalavras.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listPalavras.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listPalavras.setVisibleRowCount(-1);
        
        spnLista.setPreferredSize(new Dimension(250, 500));
         
        dicionario.adicionar("Mulher", "Woman");
        dicionario.adicionar("Homem", "Man");
        dicionario.adicionar("Querido", "Dear");
        dicionario.adicionar("Bolo", "Cake");
        dicionario.adicionar("Dinheiro", "Money");
        dicionario.adicionar("Avião", "Airplane");
        dicionario.adicionar("Inglês", "English");
        dicionario.adicionar("Unha", "Nail");
        dicionario.adicionar("Deus", "God");
        dicionario.adicionar("Pé", "Footer");
        dicionario.adicionar("Mão", "Hand");
        dicionario.adicionar("Bola", "Ball");
        dicionario.adicionar("Segunda-feira", "Monday");
        dicionario.adicionar("Sorvete", "Ice-Cream");
        dicionario.adicionar("Leão", "Lion");
        dicionario.adicionar("Casa", "House");
        dicionario.adicionar("Carro", "Car");
        
        ArrayList<Palavra> palavras = dicionario.getPalavras();
       
        for (Palavra p : palavras) {
            modelLista.addElement(p.getIngles() + " - " + p.getPortugues());
        }
        
        listPalavras.setModel(modelLista);
        
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
        
        pnlLista.add(listPalavras);
        
        pnlFuncoes.add(lblOperacao);
        pnlFuncoes.add(cbbOperacao);
        pnlFuncoes.add(lblPalavra);
        pnlFuncoes.add(txtPalavra);
        pnlFuncoes.add(rbIngles);
        pnlFuncoes.add(rbPortugues);
        pnlFuncoes.add(btnCadastrar);
        
        pnlCentral.add(pnlLista);
        pnlCentral.add(pnlFuncoes);
        
        pnlPrincipal.add(lblTitulo, "North");
        pnlPrincipal.add(separador);
        pnlPrincipal.add(pnlCentral, "Center");
        pnlPrincipal.add(separador);
        pnlPrincipal.add(lblRodape, "South");
        
        setContentPane(pnlPrincipal);        
    }
    
    private void initEvents()
    {
        menuSair.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	String[] options = {"Sim","Não"};
            	int resposta = JOptionPane.showOptionDialog(null, 
            			"Você deseja realmente sair do dicionário?", 
            			"Confirmação de Saída", 
            			JOptionPane.DEFAULT_OPTION, 
            			JOptionPane.WARNING_MESSAGE, 
            			null, options, options[0]);
            	
            	if (resposta == JOptionPane.YES_OPTION) 
                {
            		System.exit(0);
                }
            }
        });       
    }
}