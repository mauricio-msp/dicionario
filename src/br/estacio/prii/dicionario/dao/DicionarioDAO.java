package br.estacio.prii.dicionario.dao;


import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import br.estacio.prii.dicionario.entidade.Palavra;
import br.estacio.prii.dicionario.entidade.Dicionario;

public class DicionarioDAO 
{
    private final Dicionario dicionario;
    
    public DicionarioDAO(Dicionario dicionario) 
    {
        this.dicionario = dicionario;
    }
    
    //Métodos da Classe: DicionarioDAO
    public void gravar()
    {
        
        try{
            try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(new File("dicionario.txt")))) {
                StringBuilder str = new StringBuilder();
                
                if(dicionario.getPalavras().size() > 0){
                    for(Palavra palavra : dicionario.getPalavras()){
                        str.setLength(0);
                        str.append(palavra.getIngles());
                        str.append(" - ");
                        str.append(palavra.getPortugues());
                        arquivo.write(str.toString());
                        arquivo.newLine();
                    }
                    
                    arquivo.close();
                } else {
                    arquivo.close();
                    new File("dicionario.txt").delete();
                }
            }
            
        }catch(IOException ioe){
            //
        }
    }
    
    public ArrayList<Palavra> ler()
    {
        ArrayList<Palavra> palavras = new ArrayList<>();
        
        try{
            try (BufferedReader arquivo = new BufferedReader(new FileReader(new File("dicionario.txt")))) {
                String palavra[], str;
                str = arquivo.readLine();
                
                while(str != null){
                    palavra = str.split(" - ");
                    palavras.add(new Palavra(palavra[1], palavra[0]));
                    str = arquivo.readLine();
                }
                
                arquivo.close();
            }
            
        }catch(IOException ioe){
            //
        }
        
        return palavras;
    }
    
}
