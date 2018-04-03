package br.estacio.prii.dicionario.entidade;


import java.util.ArrayList;

public class Dicionario
{
    private final ArrayList<Palavra> palavras;

    //Construtor    
    public Dicionario() 
    {
        this.palavras = new ArrayList<>();
    }
    
    //Métodos da Classe: Dicionario
    public void adicionar(String portugues, String ingles)
    {
        this.palavras.add(new Palavra(portugues, ingles));
    }
    
    public void remover(Palavra palavra)
    {
        this.palavras.remove(palavra);
    }
    
    public Palavra pesquisar(String palavra)
    {
        for(Palavra p : this.palavras) {
            if(p.getIngles().equals(palavra) || p.getPortugues().equals(palavra)) {
                return p;
            }
        }
        
        return null;
    }
    
    public void getPalavras() 
    {
        this.palavras.forEach((p) -> {
            System.out.println(p.getIngles() + " - " + p.getPortugues());
        });       
    }
    
}
