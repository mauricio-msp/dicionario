package br.estacio.prii.dicionario.entidade;


import com.sun.xml.internal.ws.util.StringUtils;

public class Palavra 
{
    private String portugues;
    private String ingles;

    //Construtor
    public Palavra(String portugues, String ingles) 
    {
        this.setPortugues(portugues);
        this.setIngles(ingles);
    }

    //Métodos Acessores e Modificadores
    public String getPortugues() 
    {
        return portugues;
    }
   
    public void setPortugues(String portugues) 
    {
        this.portugues = StringUtils.capitalize(portugues);
    }
    
    public String getIngles() 
    {
        return ingles;
    }
    
    public void setIngles(String ingles) 
    {
        this.ingles = StringUtils.capitalize(ingles);
    }  
    
}
