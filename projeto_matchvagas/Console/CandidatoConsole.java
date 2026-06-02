package projeto_matchvagas.Console;

import projeto_matchvagas.model.Candidato;
import projeto_matchvagas.model.Vagas;
import projeto_matchvagas.repository.GenericDAO;
import projeto_matchvagas.service.MatchService;

import java.util.Scanner;

public class CandidatoConsole {
    public static Candidato criarcandidato(Scanner scan){

        var remoto = false;

        System.out.println("Digite, nessa ordem os nome, idade, numero da conta,  Area de Atuação," +
                " Expectativa Salarial" +
                " se aceita trabalhar remoto ou não (1 para sim, 0 para nao) + \n");

        var nome = scan.nextLine();

        var idade = scan.nextLine();

        var numConta = scan.nextLine();

        var atuacao = scan.nextLine();

        var Exp_salario = scan.nextDouble();
        scan.nextLine();

        var isremoto = scan.nextInt();
        scan.nextLine();

        if(isremoto == 1) remoto = true;

        System.out.println("Agora escreva a descrição do Candidato: ");

        var descricao = scan.nextLine();

        return  new Candidato(nome, idade, numConta, atuacao, descricao,Exp_salario, remoto);

    }

    public static void deletarCandidato(Scanner scan, GenericDAO<Candidato> candidatoDB){
        var listaCanditados = candidatoDB.listar();

        if (listaCanditados.isEmpty()) {
            System.out.println("Não há canditados cadastradas para excluir.");
            return;
        }

        System.out.println("Digite o numero da conta do candidato a ser excluído: ");

        String numero = scan.nextLine();


        var eliminado = listaCanditados.stream()
                .filter(candidato -> candidato.numConta().equals(numero))
                .findFirst()
                .orElse(null);
        if(eliminado != null){
            candidatoDB.excluir(eliminado);
            System.out.println("Candidato " + eliminado.nome() + " eliminado!");
        } else {
            System.out.println("Candidato com esse numero de conta nao existe!");
        }

    }

    public static void EditarDado(Scanner scan, GenericDAO<Candidato> candidatosDB){
        var ListaCandidatos = candidatosDB.listar();

        if(ListaCandidatos.isEmpty()){
            System.out.println("Não há vagas para editar");
        }

        System.out.println("Qual o indice do dado a ser editado (começa no 1): ");
        var index = scan.nextLine();

        var indexReal = Integer.parseInt(index) - 1;

        if (indexReal >= 0 && indexReal < ListaCandidatos.size()) {
            Candidato CandidatoAntigo = ListaCandidatos.get(indexReal);
            String nome = CandidatoAntigo.nome();
            String idade = CandidatoAntigo.idade();
            var numConta = CandidatoAntigo.numConta();
            var AreadeAtuacao = CandidatoAntigo.AreaTrabalho();
            var Exp_salario =CandidatoAntigo.ExpectativaSalario();
            var remoto = CandidatoAntigo.PrefereRemoto();
            var descricao = CandidatoAntigo.Descricao();

            System.out.println("O que você deseja editar: ");
            System.out.println("1 - Nome" + "\n" +
                    "2 - Idade" +"\n" +
                    "3 - Area de Atuação" + "\n" +
                    "4 - Expectativa Salarial" + "\n" +
                    "5 - Aceita trabalhar Remoto" + "\n" +
                    "6 - Descrição" + "\n" +
                    "Escolha: "
            );
            var escolha = 0;
            escolha = scan.nextInt();
            switch (escolha){
                case 1 -> {
                    System.out.println("Digite o novo nome: ");
                    nome = scan.nextLine();
                }
                case 2 ->{
                    System.out.println("Digite o novo Idade: ");
                    idade = scan.nextLine();
                }
                case 3 ->{
                    System.out.println("Digite a nova Area de Atuação: ");
                    AreadeAtuacao = scan.nextLine();
                }
                case 4 ->{
                    System.out.println("Digite a nova Expectativa Salarial: ");
                    Exp_salario = scan.nextDouble();
                    scan.nextLine();
                }
                case 5 ->{
                    remoto = false;
                    System.out.println("Digite se aceita remoto (1 para sim, 0 para nao: ");
                    var remot = scan.nextInt();
                    if(remot == 1) remoto = true;
                }
                default ->{
                    System.out.println("Tente novamente!");
                }
            }
            Candidato CandidatoEditado = new Candidato(nome, idade, numConta, AreadeAtuacao,
                    descricao, Exp_salario, remoto);
            candidatosDB.editar(indexReal, CandidatoEditado);
            System.out.println("Canditado Editada com Sucesso");

        }

    }

    public static void BuscarVagasParaCandidato(Scanner scan, GenericDAO<Candidato> candidatosBD,
                                                GenericDAO<Vagas> vagasBD, MatchService matchService) {

        var CandidatosLista = candidatosBD.listar();
        var VagasLista = vagasBD.listar();

        if (CandidatosLista == null || VagasLista == null) {
            System.out.println("Não há Candidatos E/OU não há Vagas");
        }

        System.out.println(CandidatosLista);
        System.out.println("Digite o Numero da conta do candidato para qual deseja buscar vagas: ");
        var IDbuscado = scan.nextLine();

        var CandidatoBuscado = CandidatosLista.stream()
                .filter(c -> c.numConta().equals(IDbuscado))
                .findFirst()
                .orElse(null);

        if (CandidatoBuscado == null) {
            System.out.println("Candidato com esse numero de conta nao localizado");
            return;
        }

        var VagasSelecionadas = VagasLista.stream()
                .filter(v -> matchService.calcularAptidao(CandidatoBuscado, v) >= 4)
                .sorted((v1, v2) -> Integer.compare(
                        matchService.calcularAptidao(CandidatoBuscado, v2),
                        matchService.calcularAptidao(CandidatoBuscado, v1)
                ))
                .toList();

        if (VagasSelecionadas.isEmpty()) {
            System.out.println("Nenhuma vaga compatível (nota mínima BOM) encontrada para este candidato.");
        } else {
            System.out.println("\n--- Vagas Compatíveis Encontradas ---");

            VagasSelecionadas.forEach(vaga -> {

                int nota = matchService.calcularAptidao(CandidatoBuscado, vaga);
                String classificacao = matchService.classificarNota(nota);

                System.out.println("Classificação: " + classificacao + " (Nota " + nota + ") | Vaga: " + vaga);
            });


        }
    }
}