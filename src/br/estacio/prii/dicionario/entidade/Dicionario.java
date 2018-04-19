package br.estacio.prii.dicionario.entidade;


import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dicionario
{
    private ArrayList<Palavra> palavras;

    // Construtor    
    public Dicionario() 
    {
        palavras = new ArrayList<>();
    }
    
    // Métodos da Classe: Dicionário
    public void adicionar(Palavra palavra)
    {
        palavras.add(palavra);
        boolean achou = false;
        
        for(int i = 0; i < palavras.size(); i++) {
            if(palavras.get(i).toString().equalsIgnoreCase(palavra.toString())) {
                if(achou) {
                    JOptionPane.showMessageDialog(
                        null, "Essa palavra já esta cadastrada!", "PALAVRA JÁ EXISTE", JOptionPane.ERROR_MESSAGE
                    );

                    palavras.remove(palavra);
                }
                
                achou = true;
            }
        }
    }
    
    public void remover(String palavra)
    {
        for(int i = 0; i < palavras.size(); i++) {
            if((palavras.get(i).toString()).equalsIgnoreCase(palavra)) {
                palavras.remove(i);
            }
        }
    }
    
    public Palavra pesquisar(String palavra)
    {
        for(Palavra p : palavras) {
            if(p.getIngles().equalsIgnoreCase(palavra) || p.getPortugues().equalsIgnoreCase(palavra)) {
                return p;
            }
        }
        
        return null;
    }
    
    // Método Acessor e Modificador
    public void setPalavras(ArrayList<Palavra> palavras)
    {
        this.palavras = palavras;
    }
    
    public ArrayList<Palavra> getPalavras() 
    {
        return palavras;
    }
    
}
