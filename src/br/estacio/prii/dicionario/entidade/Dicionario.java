package br.estacio.prii.dicionario.entidade;


import java.util.ArrayList;

public class Dicionario
{
    private ArrayList<Palavra> palavras;

    // Construtor    
    public Dicionario() 
    {
        palavras = new ArrayList<>();
    }
    
    // Métodos da Classe: Dicionário
    public void adicionar(String portugues, String ingles)
    {
        palavras.add(new Palavra(portugues, ingles));
        removerDuplicados();
    }
    
    public void remover(String palavra)
    {
        for(int i = 0; i < palavras.size(); i++) {
            if((palavras.get(i).getIngles() + " - " + palavras.get(i).getPortugues()).equalsIgnoreCase(palavra)) {
                palavras.remove(i);
                i--;
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
    
    // Método Privado da Classe: Dicionário
    private void removerDuplicados() 
    {
        for (int i = 0; i < palavras.size(); i++) {
            Palavra a = palavras.get(i);
            for (int j = i + 1; j < palavras.size(); j++) {
                Palavra b = palavras.get(j);
                if (a.getIngles().equalsIgnoreCase(b.getIngles()) || a.getPortugues().equalsIgnoreCase(b.getPortugues())) {
                    palavras.remove(j);
                    j--;
                }
            }
        }
    }
}
