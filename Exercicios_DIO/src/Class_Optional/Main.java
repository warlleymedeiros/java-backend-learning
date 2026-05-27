package Exercicios_DIO.src.Class_Optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        Optional<User> optional  = Optional.empty();
//        System.out.println(optional.isPresent());
//
        Optional<User> optional = Optional.of(new User("Joao", 12, SexEnum.MALE));
//        optional.ifPresent(System.out::println);

//        Optional<User> optional = Optional.ofNullable(null);

//        int newAge = 22;
//
//        optional.ifPresentOrElse(user -> {
//            System.out.printf("Usuario: %s \n", user);
//            user = new User("Joao", newAge, SexEnum.MALE);
//            System.out.printf("Usuario: %s \n", user);
//                },
//                () -> System.out.println("nao foi informado usuario")
//        );

//        Optional<User> optional = Optional.empty();
//        System.out.println(optional.orElse(new User("Maria", 32, SexEnum.FEMALE)));
//
//        System.out.println(optional.orElseThrow(() -> new RuntimeException("")));

//        System.out.println(optional.orElse(defaultUser()));
//        System.out.println(optional.orElseGet(Main::defaultUser));

       var newUser =  optional.map(user -> new UserV2(user.name(), user.age(), user.sex())).orElseThrow();

        System.out.println(newUser);


    }

    public static User defaultUser(){
        System.out.println("Buscando o valor default");
        return new User("Maria", 32, SexEnum.FEMALE);
    }

}
