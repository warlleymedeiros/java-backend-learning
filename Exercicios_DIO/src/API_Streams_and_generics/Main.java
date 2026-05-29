package Exercicios_DIO.src.API_Streams_and_generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        var values = generateUsers().stream()
                .filter(u -> u.sexo() == Sex.FEMALE)
                        .collect(Collectors.toMap(User::name, user ->user));
//                .flatMap(u ->u.contatos().stream())
//                .sorted(Comparator.comparing(Contact::description))
//                .map(c -> String.format("{\n'description: %s',\n 'type: %s'\n}", c.description(), c.type()))
//                .filter(c -> c.type() == ContatctType.EMAIL)
//                .sorted((c1,c2) -> c1.description().compareToIgnoreCase(c2.description()))
//                .filter(u ->  u.age() > 30 )
//                .toList();

        values.forEach((key, value) -> System.out.printf("key: %s | value: %s \n", key, value));
    }



    private static List<User> generateUsers(){
        var contato1 = List.of(
                new Contact("6199999", ContatctType.PHONE),
                new Contact("pipipi@gmail.com", ContatctType.EMAIL)
        );

        var contato2 = List.of(
                new Contact("88888", ContatctType.PHONE),
                new Contact("popo@gmail.com", ContatctType.EMAIL)
        );

        var contato3 = List.of(
                new Contact("77777", ContatctType.PHONE),
                new Contact("mimimi@gmail.com", ContatctType.EMAIL)
        );

        var contato4 = List.of(
                new Contact("66666", ContatctType.PHONE),
                new Contact("tiriri@gmail.com", ContatctType.EMAIL)
        );

        var contato5 = List.of(
                new Contact("5555", ContatctType.PHONE),
                new Contact("pokca@gmail.com", ContatctType.EMAIL)
        );

        var user1 = new User("Maria", 22, Sex.FEMALE, new ArrayList<>(contato1));
        var user2 = new User("Lucas", 19, Sex.MALE, new ArrayList<>(contato2));
        var user3 = new User("Carol", 32, Sex.FEMALE, new ArrayList<>(contato3));
        var user4 = new User("Brunno", 28, Sex.MALE, new ArrayList<>(contato4));
        var user5 = new User("Monike", 45, Sex.FEMALE, new ArrayList<>(contato5));



        return List.of(user1, user2, user3, user4, user5);
    }
}
