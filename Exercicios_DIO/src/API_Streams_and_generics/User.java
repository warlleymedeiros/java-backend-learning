package Exercicios_DIO.src.API_Streams_and_generics;

import java.util.List;

public record User(String name, int age, Sex sexo, List<Contact> contatos) {

    public String ToString(){
       return String.format("Nome: %s | Idade: %d | Sexo: %s | Contatos: %s", name, age, sexo, contatos);
    };

}
