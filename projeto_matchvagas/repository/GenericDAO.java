package projeto_matchvagas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class GenericDAO<T> {

    protected final List<T> db = new ArrayList<>();

    public T salvar(T dado){
        db.add(dado);
        return dado;
    }

    public T excluir(T dado){
        db.remove(dado);
        return dado;
    }

    public Optional<T> encontrar(Predicate<T> filtro){
        return db.stream().filter(filtro).findFirst();
    }

    public List<T> listar(){
        return db;
    }

    public void editar(int index, T dadoNovo){
      this.db.set(index, dadoNovo);
    }

}
