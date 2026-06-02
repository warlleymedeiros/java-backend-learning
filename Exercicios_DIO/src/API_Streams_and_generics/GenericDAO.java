package Exercicios_DIO.src.API_Streams_and_generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

 abstract class GenericDAO<ID, T extends GenericDomain<ID>> {

    private final List<T> db = new ArrayList<>();

    public T save(T domain){
        db.add(domain);
        return domain;
    }

    public T update(ID id, T domain) {
        var stored = find(d-> d.getId().equals(id)).orElseThrow();
        db.remove(stored);
        return domain;
    }

    public boolean delete (T domain){
        return db.remove(domain);
    }

    public Optional<T> find(Predicate<T> filterCallback){
        return db.stream().filter(filterCallback).findFirst();
    }

    public List<T> findAll(){
        return db;
    }

    public int count(){
        return db.size();
    }





}
