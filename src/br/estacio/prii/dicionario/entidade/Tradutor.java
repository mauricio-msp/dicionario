package br.estacio.prii.dicionario.entidade;


public class Tradutor 
{
    private final Dicionario dicionario;

    //Contrutor
    public Tradutor(Dicionario dicionario) 
    {
        this.dicionario = dicionario;
    }
    
    //Métodos da Classe: Dicionario
    public String traduzirParaIngles(String palavra)
    {
        Palavra p = this.dicionario.pesquisar(palavra);
        
        if(p != null) {
            return p.getIngles();
        }
        
        return "Tradução não encontrada!";
    }
    
    public String traduzirParaPortugues(String palavra)
    {
        Palavra p = this.dicionario.pesquisar(palavra);
        
        if(p != null) {
            return p.getPortugues();
        }
        
        return "Tradução não encontrada!";
    }
 
}
