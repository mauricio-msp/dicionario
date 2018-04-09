package br.estacio.prii.dicionario.persistencia;

import br.estacio.prii.dicionario.entidade.Palavra;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ArquivoDicionario 
{
     public void gravar(ArrayList<Palavra> palavras)
    {
        try{
            BufferedWriter arq = new BufferedWriter(new FileWriter(new File("palavras.txt")));
            StringBuilder str = new StringBuilder();
            
            for(Palavra word : palavras){
                str.setLength(0);
                str.append(word.getPortugues());
                str.append(" - ");
                str.append(word.getIngles());
                arq.write(str.toString());
                arq.newLine();
            }
            
            arq.close();
            
        }catch(IOException e){
        }
    }
    
    public ArrayList ler()
    {
        ArrayList<Palavra> palavras = new ArrayList<>();
        try{
            BufferedReader arq = new BufferedReader(new FileReader(new File("palavras.txt")));
            String palavra[], str;
            
            
            str = arq.readLine();
            
            while(str != null){
                palavra = str.split(" - ");
                palavras.add(new Palavra(palavra[0], palavra[1]));
                str = arq.readLine();
            }
            
            arq.close();
            
        }catch(IOException e){
        }
        
        return palavras;
        
    }
}
