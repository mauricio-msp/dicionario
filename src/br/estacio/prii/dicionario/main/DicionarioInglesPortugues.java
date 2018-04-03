package br.estacio.prii.dicionario.main;

import java.util.ArrayList;
import br.estacio.prii.dicionario.entidade.*;

public class DicionarioInglesPortugues 
{
    public static void main(String[] args) 
    {  
        Dicionario d = new Dicionario();
        d.adicionar("homem", "man");
        d.adicionar("homem", "man");
        
        //Tradutor t = new Tradutor(d);
        
        //System.out.println(t.traduzirParaPortugues("woman"));
        d.getPalavras();
        
    }
}
