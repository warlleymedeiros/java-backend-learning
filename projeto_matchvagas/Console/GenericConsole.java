package projeto_matchvagas.Console;

import projeto_matchvagas.repository.GenericDAO;

import java.util.List;
import java.util.Scanner;

public abstract class GenericConsole<T> {

    protected final GenericDAO<T> dao;
    protected final Scanner scan;
    protected final String nomeEntidade;

    public GenericConsole(GenericDAO<T> dao, Scanner scan, String nomeEntidade) {
        this.dao = dao;
        this.scan = scan;
        this.nomeEntidade = nomeEntidade;
    }

    // Métodos que cada entidade implementa com seus próprios campos
    public abstract T criar();
    public abstract T editarCampos(T entidadeAtual);

    // Métodos comuns e reutilizáveis
    public void cadastrar() {
        System.out.println("\n--- Cadastrar " + nomeEntidade + " ---");
        T novo = criar();
        dao.salvar(novo);
        System.out.println(nomeEntidade + " cadastrado(a) com sucesso!");
    }

    public void listar() {
        List<T> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum(a) " + nomeEntidade.toLowerCase() + " cadastrado(a).");
            return;
        }

        System.out.println("\n--- Lista de " + nomeEntidade + "s ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
    }

    public void excluir() {
        List<T> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há " + nomeEntidade.toLowerCase() + "s para excluir.");
            return;
        }

        listar();
        System.out.print("Digite o número do(a) " + nomeEntidade.toLowerCase() + " a ser excluído(a): ");
        int index = scan.nextInt() - 1;
        scan.nextLine(); // Limpa buffer

        if (index >= 0 && index < lista.size()) {
            T removido = lista.get(index);
            dao.excluir(removido);
            System.out.println(nomeEntidade + " excluído(a) com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    public void editar() {
        List<T> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há " + nomeEntidade.toLowerCase() + "s para editar.");
            return;
        }

        listar();
        System.out.print("Digite o número do(a) " + nomeEntidade.toLowerCase() + " a ser editado(a): ");
        int index = scan.nextInt() - 1;
        scan.nextLine(); // Limpa buffer

        if (index >= 0 && index < lista.size()) {
            T atual = lista.get(index);
            T atualizado = editarCampos(atual);
            dao.editar(index, atualizado);
            System.out.println(nomeEntidade + " editado(a) com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
}