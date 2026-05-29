package Exercicios_DIO.src.API_Streams_and_generics;

public class GenericsMain {

   private final static GenericDAO<Integer, UserDomain> dao = new UserDao();


    public static void main(String[] args) {

        var user = new UserDomain(1, "joao", 36);

        System.out.println(dao.count());
        System.out.println(dao.save(user));
        System.out.println(dao.findAll());
        System.out.println(dao.count());



    }
}
