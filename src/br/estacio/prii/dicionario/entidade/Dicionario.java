package br.estacio.prii.dicionario.entidade;


import java.util.ArrayList;

public class Dicionario
{
    private final ArrayList<Palavra> palavras;

    //Construtor    
    public Dicionario() 
    {
        palavras = new ArrayList<>();
    }
    
    //Métodos da Classe: Dicionario
    public void adicionar(String portugues, String ingles)
    {
        palavras.add(new Palavra(portugues, ingles));
    }
    
    public void remover(String palavra)
    {
        for(int i = 0; i < palavras.size(); i++) {
            if(palavras.get(i).getIngles().equals(palavra) || palavras.get(i).getPortugues().equals(palavra)) {
                palavras.remove(i);
                i--;
            }
        }
    }
    
    public Palavra pesquisar(String palavra)
    {
        for(Palavra p : palavras) {
            if(p.getIngles().equals(palavra) || p.getPortugues().equals(palavra)) {
                return p;
            }
        }
        
        return null;
    }
    
    public void getPalavras() 
    {
        palavras.forEach((p) -> {
            System.out.println(p.getIngles() + " - " + p.getPortugues());
        });
    }
    
}
