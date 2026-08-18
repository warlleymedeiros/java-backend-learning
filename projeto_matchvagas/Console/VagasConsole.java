package projeto_matchvagas.Console;

import projeto_matchvagas.model.Candidato;
import projeto_matchvagas.model.ResultadoMatch;
import projeto_matchvagas.model.Vagas;
import projeto_matchvagas.repository.GenericDAO;
import projeto_matchvagas.service.MatchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VagasConsole extends GenericConsole<Vagas> {

    public VagasConsole(GenericDAO<Vagas> dao, Scanner scan) {
        super(dao, scan, "Vaga");
    }

    @Override
    public Vagas criar() {
        System.out.print("ID: ");
        String id = scan.nextLine();

        System.out.print("Título da Vaga: ");
        String titulo = scan.nextLine();

        System.out.print("Área de Atuação: ");
        String atuacao = scan.nextLine();

        System.out.print("Salário Máximo: ");
        double salario = scan.nextDouble();
        scan.nextLine();

        System.out.print("Aceita Remoto? (1 para Sim, 0 para Não): ");
        boolean remoto = scan.nextInt() == 1;
        scan.nextLine();

        System.out.print("Descrição da Vaga: ");
        String descricao = scan.nextLine();

        return new Vagas(id, titulo, atuacao, salario, remoto, descricao);
    }

    @Override
    public Vagas editarCampos(Vagas atual) {
        String id = atual.id();
        String titulo = atual.titulo();
        String atuacao = atual.AreaAtuacao();
        double salario = atual.salarioMaximo();
        boolean remoto = atual.aceitaRemoto();
        String descricao = atual.Descricao();

        System.out.println("O que você deseja editar?");
        System.out.println("1 - ID | 2 - Título | 3 - Área | 4 - Salário | 5 - Remoto | 6 - Descrição");
        System.out.print("Escolha: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1 -> { System.out.print("Novo ID: "); id = scan.nextLine(); }
            case 2 -> { System.out.print("Novo Título: "); titulo = scan.nextLine(); }
            case 3 -> { System.out.print("Nova Área: "); atuacao = scan.nextLine(); }
            case 4 -> { System.out.print("Novo Salário Máximo: "); salario = scan.nextDouble(); scan.nextLine(); }
            case 5 -> { System.out.print("Aceita Remoto (1-Sim / 0-Não): "); remoto = scan.nextInt() == 1; scan.nextLine(); }
            case 6 -> { System.out.print("Nova Descrição: "); descricao = scan.nextLine(); }
            default -> System.out.println("Opção inválida! Mantendo dados anteriores.");
        }

        return new Vagas(id, titulo, atuacao, salario, remoto, descricao);
    }

    public void buscarCandidatosParaVaga(GenericDAO<Candidato> candidatosDB, MatchService matchService) {
        List<Vagas> vagas = dao.listar();
        List<Candidato> candidatos = candidatosDB.listar();

        if (vagas.isEmpty() || candidatos.isEmpty()) {
            System.out.println("Não há vagas ou candidatos suficientes para realizar a busca.");
            return;
        }

        listar();
        System.out.print("Digite o índice da vaga: ");
        int index = scan.nextInt() - 1;
        scan.nextLine();

        if (index < 0 || index >= vagas.size()) {
            System.out.println("Índice de vaga inválido!");
            return;
        }

        Vagas vagaEscolhida = vagas.get(index);
        List<ResultadoMatch> resultados = new ArrayList<>();

        for (Candidato c : candidatos) {
            double aptidao = matchService.calcularAptidao(c, vagaEscolhida);
            resultados.add(new ResultadoMatch(c, aptidao));
        }

        resultados.sort((r1, r2) -> Double.compare(r2.nota(), r1.nota()));

        System.out.println("\n====== RANKING DE CANDIDATOS PARA: " + vagaEscolhida.titulo() + " ======");
        for (int i = 0; i < resultados.size(); i++) {
            ResultadoMatch res = resultados.get(i);
            String classificacao = matchService.classificarNota((int) res.nota());
            System.out.println((i + 1) + "º Lugar: " + res.candidato().nome()
                    + " | Nota: " + res.nota() + " -> [" + classificacao + "]");
        }
    }
}