package br.estacio.prii.dicionario.entidade;


public class Palavra 
{
    private String portugues;
    private String ingles;

    // Construtor
    public Palavra(String portugues, String ingles) 
    {
        setPortugues(portugues);
        setIngles(ingles);
    }

    // Métodos Acessores e Modificadores
    public String getPortugues() 
    {
        return portugues;
    }
   
    public void setPortugues(String portugues) 
    {
        this.portugues = portugues.substring(0, 1).toUpperCase().concat(portugues.substring(1));
    }
    
    public String getIngles() 
    {
        return ingles;
    }
    
    public void setIngles(String ingles) 
    {
        this.ingles = ingles.substring(0, 1).toUpperCase().concat(ingles.substring(1));
    }  
    
    @Override
    public String toString()
    {
        return getIngles() + " - " + getPortugues();
    }
}
