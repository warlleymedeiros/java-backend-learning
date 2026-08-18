package projeto_matchvagas.Console;

import projeto_matchvagas.model.Candidato;
import projeto_matchvagas.model.Vagas;
import projeto_matchvagas.repository.GenericDAO;
import projeto_matchvagas.service.MatchService;

import java.util.List;
import java.util.Scanner;

public class CandidatoConsole extends GenericConsole<Candidato> {
    public CandidatoConsole(GenericDAO<Candidato> dao, Scanner scan) {
        super(dao, scan, "Candidato");
    }

    @Override
    public Candidato criar() {
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("Idade: ");
        String idade = scan.nextLine();

        System.out.print("Número da Conta: ");
        String numConta = scan.nextLine();

        System.out.print("Área de Atuação: ");
        String atuacao = scan.nextLine();

        System.out.print("Expectativa Salarial: ");
        double expSalario = scan.nextDouble();
        scan.nextLine();

        System.out.print("Prefere Remoto? (1 para Sim, 0 para Não): ");
        boolean remoto = scan.nextInt() == 1;
        scan.nextLine();

        System.out.print("Descrição: ");
        String descricao = scan.nextLine();

        return new Candidato(nome, idade, numConta, atuacao, descricao, expSalario, remoto);
    }

    @Override
    public Candidato editarCampos(Candidato atual) {
        String nome = atual.nome();
        String idade = atual.idade();
        String numConta = atual.numConta();
        String atuacao = atual.AreaTrabalho();
        double expSalario = atual.ExpectativaSalario();
        boolean remoto = atual.PrefereRemoto();
        String descricao = atual.Descricao();

        System.out.println("O que você deseja editar?");
        System.out.println("1 - Nome | 2 - Idade | 3 - Área | 4 - Salário | 5 - Remoto | 6 - Descrição");
        System.out.print("Escolha: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1 -> { System.out.print("Novo Nome: "); nome = scan.nextLine(); }
            case 2 -> { System.out.print("Nova Idade: "); idade = scan.nextLine(); }
            case 3 -> { System.out.print("Nova Área: "); atuacao = scan.nextLine(); }
            case 4 -> { System.out.print("Novo Salário: "); expSalario = scan.nextDouble(); scan.nextLine(); }
            case 5 -> { System.out.print("Aceita remoto (1-Sim / 0-Não): "); remoto = scan.nextInt() == 1; scan.nextLine(); }
            case 6 -> { System.out.print("Nova Descrição: "); descricao = scan.nextLine(); }
            default -> System.out.println("Opção inválida! Mantendo dados anteriores.");
        }

        return new Candidato(nome, idade, numConta, atuacao, descricao, expSalario, remoto);
    }

    public void buscarVagasParaCandidato(GenericDAO<Vagas> vagasDB, MatchService matchService) {
        List<Candidato> candidatos = dao.listar();
        List<Vagas> vagas = vagasDB.listar();

        if (candidatos.isEmpty() || vagas.isEmpty()) {
            System.out.println("Não há candidatos ou vagas cadastrados.");
            return;
        }

        listar();
        System.out.print("Digite o número da conta do candidato: ");
        String numConta = scan.nextLine();

        Candidato buscado = candidatos.stream()
                .filter(c -> c.numConta().equalsIgnoreCase(numConta))
                .findFirst()
                .orElse(null);

        if (buscado == null) {
            System.out.println("Candidato não localizado!");
            return;
        }

        var vagasCompativeis = vagas.stream()
                .filter(v -> matchService.calcularAptidao(buscado, v) >= 4)
                .sorted((v1, v2) -> Integer.compare(
                        matchService.calcularAptidao(buscado, v2),
                        matchService.calcularAptidao(buscado, v1)
                ))
                .toList();

        if (vagasCompativeis.isEmpty()) {
            System.out.println("Nenhuma vaga compatível encontrada.");
        } else {
            System.out.println("\n--- Vagas Compatíveis ---");
            vagasCompativeis.forEach(v -> {
                int nota = matchService.calcularAptidao(buscado, v);
                System.out.println("[" + matchService.classificarNota(nota) + " - Nota " + nota + "] " + v.titulo());
            });
        }
    }
}