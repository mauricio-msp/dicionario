package br.estacio.prii.dicionario.main;

import java.util.ArrayList;
import br.estacio.prii.dicionario.entidade.*;

public class DicionarioInglesPortugues 
{
    public static void main(String[] args) 
    {  
        Dicionario d = new Dicionario();
        d.adicionar("homem", "man");
        d.adicionar("mulher", "woman");
        
        Tradutor t = new Tradutor(d);
        // d.remover("mulher");
        System.out.println(t.traduzirParaPortugues("woman"));
        //d.getPalavras();
        
    }
}
