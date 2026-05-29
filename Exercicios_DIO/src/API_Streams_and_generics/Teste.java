package Exercicios_DIO.src.API_Streams_and_generics;


public class Teste {
    public static void main(String[] args) {
//        var value2 = IntStream.generate(()-> new Random().nextInt())
//                .limit(5)
//                .toArray();
//
//        System.out.println("-----------------");
//
//        for( var v : value2){
//            System.out.println(v);
//        }

//      List<String> values = new ArrayList<>();


//      var nomes =  Stream.of("Maria", "Joao", "Marcio", "luana", "Carol")
                //.peek(System.out::println) //so funciona se tiver uma função final, como ToList
//                .peek(values::add)
//                .filter(name -> name.endsWith("a"))
//                .limit(1)
//                .toList();
//        .anyMatch(n -> n.contains("a"));
//        .allMatch(n -> n.contains("a"));
//        .findFirst();
//        var numeros = Stream.of(1,2,3,4,5,6,7,8,9,10,2,3,4,5,1)
//                .reduce(0, Integer::sum );
//                .map(n -> n % 2 == 0)
//                .toList();
//
//        System.out.println(numeros);


//      System.out.println(nomes);
//      System.out.println(values);

//        List<Integer> numeros = List.of(3,6,9,12);
//        List<Integer> numeros2 = List.of(1,2,3,4,5,6,7,8,9,10);
//
//        var newValues = numeros2.stream()
//                .filter(numeros::contains) //filtra os numeros q elas tem em comum
//                .map(n -> numeros.stream().reduce(n, (n1, n2) -> n1 -n2)) //para cada filtrado, reduz sobre os numeros da lista 1
//                .collect(Collectors.toSet()); //coloca em uma lista que nao permite duplicados
//
//        System.out.println(newValues);









    }

}
