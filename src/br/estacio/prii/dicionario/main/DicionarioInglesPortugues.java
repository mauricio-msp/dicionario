package br.estacio.prii.dicionario.main;

import br.estacio.prii.dicionario.entidade.*;
import br.estacio.prii.dicionario.frame.*;

public class DicionarioInglesPortugues 
{
    public static void main(String[] args) 
    {  
        Dicionario dicionario = new Dicionario();
        
        // Testando inserção de dados na lista
        dicionario.adicionar("Mulher", "Woman");
        dicionario.adicionar("Homem", "Man");
        dicionario.adicionar("Querido", "Dear");
        dicionario.adicionar("Bolo", "Cake");
        dicionario.adicionar("Dinheiro", "Money");
        dicionario.adicionar("Avião", "Airplane");
        dicionario.adicionar("Inglês", "English");
        dicionario.adicionar("Unha", "Nail");
        dicionario.adicionar("Deus", "God");
        dicionario.adicionar("Pé", "Footer");
        dicionario.adicionar("Mão", "Hand");
        dicionario.adicionar("Bola", "Ball");
        dicionario.adicionar("Segunda-feira", "Monday");
        dicionario.adicionar("Sorvete", "Ice-Cream");
        dicionario.adicionar("Leão", "Lion");
        dicionario.adicionar("Casa", "House");
        dicionario.adicionar("Carro", "Car");
        dicionario.adicionar("Teste", "test");
        //dicionario.adicionar("Novo", "New");
        
        new FrameDicionario(dicionario);
    }
}