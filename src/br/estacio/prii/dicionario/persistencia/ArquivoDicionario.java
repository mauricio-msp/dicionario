package br.estacio.prii.dicionario.persistencia;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.ArrayList;
import br.estacio.prii.dicionario.entidade.Palavra;

public class ArquivoDicionario 
{
    
    //Métodos da Classe: ArquivoDicionario
    public void gravar(ArrayList<Palavra> palavras)
    {
        try{
            try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(new File("dicionario.txt")))) {
                StringBuilder str = new StringBuilder();
                
                for(Palavra p : palavras){
                    str.setLength(0);
                    str.append(p.getPortugues());
                    str.append(" - ");
                    str.append(p.getIngles());
                    arquivo.write(str.toString());
                    arquivo.newLine();
                }
            }
            
        }catch(IOException e){
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
                    palavras.add(new Palavra(palavra[0], palavra[1]));
                    str = arquivo.readLine();
                }
            }
            
        }catch(IOException e){
            //
        }
        
        return palavras;
    }
    
}
