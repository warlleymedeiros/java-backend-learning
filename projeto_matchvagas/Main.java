package projeto_matchvagas;

import projeto_matchvagas.Console.CandidatoConsole;
import projeto_matchvagas.Console.VagasConsole;
import projeto_matchvagas.model.Candidato;
import projeto_matchvagas.model.Vagas;
import projeto_matchvagas.repository.GenericDAO;
import projeto_matchvagas.repository.RepositoryCandidato;
import projeto_matchvagas.repository.RepositoryVagas;
import projeto_matchvagas.service.MatchService;

import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);
    private static final GenericDAO<Candidato> candidatosDB = new RepositoryCandidato();
    private static final GenericDAO<Vagas> vagasDB = new RepositoryVagas();
    public static final MatchService matchservice = new MatchService();

    private static final CandidatoConsole candidatoConsole = new CandidatoConsole(candidatosDB, scan);
    private static final VagasConsole vagasConsole = new VagasConsole(vagasDB, scan);

    public static void main(String[] args) {

        // === MASSA DE TESTES DE CANDIDATOS ===
        candidatosDB.salvar(new Candidato(
                "Carlos Silva", "22", "12345-X", "TI",
                "Desenvolvedor focado em Java e Spring Boot com projetos pessoais.",
                4500.0, true
        ));

        candidatosDB.salvar(new Candidato(
                "Ana Costa", "28", "98765-1", "Design",
                "Designer UX/UI apaixonada por interfaces limpas e mobile.",
                6000.0, false
        ));

        candidatosDB.salvar(new Candidato(
                "Bruno Souza", "19", "55443-2", "TI",
                "Estudante de Engenharia querendo aprender lógica e Python.",
                2000.0, true
        ));

        // === MASSA DE TESTES DE VAGAS ===
        vagasDB.salvar(new Vagas(
                "1", "Dev Java Júnior", "TI",
                5000.0, true,
                "Vaga para atuar no backend de sistemas financeiros usando Java."
        ));

        vagasDB.salvar(new Vagas(
                "2", "UX Designer Pleno", "Design",
                7000.0, false,
                "Atuar no redesenho do aplicativo mobile da empresa de forma presencial."
        ));

        vagasDB.salvar(new Vagas(
                "3", "Estágio em Suporte", "TI",
                1500.0, true,
                "Auxiliar a equipe de infraestrutura e atendimento ao usuário."
        ));

        var scan = new Scanner(System.in);
        int perfil = 0;

        do {
            System.out.println("\n=== Bem vindo ao MatchVagas! ===");
            System.out.println("Você é um: ");
            System.out.println("1 - Empregador");
            System.out.println("2 - Empregado");
            System.out.println("0 - Desligar o sistema");
            System.out.print("Escolha: ");
            perfil = scan.nextInt();
            scan.nextLine();

            int escolhaInterna = 0;

            switch (perfil) {
                case 1 -> {
                    do {
                        System.out.println("\n--- MENU EMPREGADOR ---");
                        System.out.println("1 - Cadastrar Vaga");
                        System.out.println("2 - Editar Vaga");
                        System.out.println("3 - Excluir Vaga");
                        System.out.println("4 - Buscar Candidatos para uma Vaga");
                        System.out.println("5 - Listar vagas");
                        System.out.println("6 - Voltar ao menu principal");
                        System.out.print("Escolha: ");
                        escolhaInterna = scan.nextInt();
                        scan.nextLine(); // CORREÇÃO: Limpa o buffer do int!

                        switch (escolhaInterna) {
                            case 1 -> vagasConsole.cadastrar();
                            case 2 -> vagasConsole.editar();
                            case 3 -> vagasConsole.excluir();
                            case 4 -> vagasConsole.buscarCandidatosParaVaga(candidatosDB, matchservice);
                            case 5 -> vagasConsole.listar();
                            case 6 -> System.out.println("Voltando...");
                            default -> System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (escolhaInterna != 6);
                }

                case 2 -> {
                    do {
                        System.out.println("\n--- MENU EMPREGADO ---");
                        System.out.println("1 - Cadastrar Candidato");
                        System.out.println("2 - Editar Candidato");
                        System.out.println("3 - Excluir Candidato");
                        System.out.println("4 - Buscar Vaga para um Candidato");
                        System.out.println("5 - Listar Candidatos");
                        System.out.println("0 - Voltar ao menu principal");
                        System.out.print("Escolha: ");
                        escolhaInterna = scan.nextInt();
                        scan.nextLine();

                        switch (escolhaInterna) {
                            case 1 -> candidatoConsole.cadastrar();
                            case 2 -> candidatoConsole.editar();
                            case 3 -> candidatoConsole.excluir();
                            case 4 -> candidatoConsole.buscarVagasParaCandidato(vagasDB, matchservice);
                            case 5 -> candidatoConsole.listar();
                            case 0 -> System.out.println("Voltando...");
                            default -> System.out.println("Opção inválida! Tente Novamente.");
                        }
                    } while (escolhaInterna != 0);
                }
                case 0 -> System.out.println("Desligando o sistema...");
                default -> System.out.println("Opção Inválida no menu principal!");
            }
        } while (perfil != 0);
    }
}