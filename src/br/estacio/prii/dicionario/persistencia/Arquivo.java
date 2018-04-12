package br.estacio.prii.dicionario.persistencia;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.ArrayList;
import br.estacio.prii.dicionario.entidade.Palavra;

public class Arquivo 
{
    
    //Métodos da Classe: Arquivo
    public void gravar(ArrayList<Palavra> palavras)
    {
        try{
            try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(new File("dicionario.txt")))) {
                StringBuilder str = new StringBuilder();
                
                if(palavras.size() > 0){
                    for(Palavra p : palavras){
                        str.setLength(0);
                        str.append(p.getIngles());
                        str.append(" - ");
                        str.append(p.getPortugues());
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
