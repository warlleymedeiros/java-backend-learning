package projeto_matchvagas.Console;

import projeto_matchvagas.model.Candidato;
import projeto_matchvagas.model.ResultadoMatch;
import projeto_matchvagas.model.Vagas;
import projeto_matchvagas.repository.GenericDAO;
import projeto_matchvagas.service.MatchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VagasConsole<T>{
    public static Vagas criarVaga(Scanner scan){

        var remoto = false;

        System.out.println("Digite, nessa ordem os ID, titulo, Area de Atuação, Salario Máximo" +
                " se aceita remoto ou não (1 para sim, 0 para nao) + \n");

        var id = scan.nextLine();

        var titulo = scan.nextLine();

        var atuacao = scan.nextLine();

        var salario = scan.nextDouble();
        scan.nextLine();

        var isremoto = scan.nextInt();
        scan.nextLine();

        if(isremoto == 1) remoto = true;

        System.out.println("Agora escreva a descrição da vaga: ");

        var descricao = scan.nextLine();

        return new Vagas(id, titulo, atuacao, salario, remoto, descricao);

    }

    public static void deletarVaga(Scanner scan, GenericDAO<Vagas> vagasDB){
        var listaVagas = vagasDB.listar();

        if (listaVagas.isEmpty()) {
            System.out.println("Não há vagas cadastradas para excluir.");
            return;
        }

        System.out.println("Digite o número da vaga a ser excluída (começa com 1): ");

        int escolha = scan.nextInt();
        scan.nextLine();

        int indiceReal = escolha - 1;

        if (indiceReal >= 0 && indiceReal < listaVagas.size()) {

            Vagas vagaParaDeletar = listaVagas.get(indiceReal);


            vagasDB.excluir(vagaParaDeletar);

            System.out.println("Vaga \"" + vagaParaDeletar.titulo() + "\" excluída com sucesso!");
        } else {
            System.out.println("Número de vaga inválido!");
        }
    }

    public static void EditarDado(Scanner scan, GenericDAO<Vagas> VagasDB){
        var listaVagas = VagasDB.listar();

        if(listaVagas.isEmpty()){
            System.out.println("Não há vagas para editar");
        }

        System.out.println("Qual o indice do dado a ser editado (começa no 1): ");
        var index = scan.nextLine();

        var indexReal = Integer.parseInt(index) - 1;

        if (indexReal >= 0 && indexReal < listaVagas.size()) {
            Vagas vagaAntiga = listaVagas.get(indexReal);
            String id = vagaAntiga.id();
            String titulo = vagaAntiga.titulo();
            var AreadeAtuacao = vagaAntiga.AreaAtuacao();
            var salarioMax =vagaAntiga.salarioMaximo();
            var remoto = vagaAntiga.aceitaRemoto();
            var descricao = vagaAntiga.Descricao();

            System.out.println("O que você deseja editar: ");
            System.out.println("1 - ID" + "\n" +
                    "2 - Titulo" +"\n" +
                    "3 - Area de Atuação" + "\n" +
                    "4 - Salario Maximo" + "\n" +
                    "5 - Aceita Remoto" + "\n" +
                    "6 - Descrição" + "\n" +
                    "Escolha: "
            );
            var escolha = 0;
            escolha = scan.nextInt();
            switch (escolha){
                case 1 -> {
                    System.out.println("Digite o novo ID: ");
                    id = scan.nextLine();
                }
                case 2 ->{
                    System.out.println("Digite o novo Titulo: ");
                    titulo = scan.nextLine();
                }
                case 3 ->{
                    System.out.println("Digite a nova Area de Atuação: ");
                    AreadeAtuacao = scan.nextLine();
                }
                case 4 ->{
                    System.out.println("Digite o novo Salario Maximo: ");
                    salarioMax = scan.nextDouble();
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
            Vagas vagaEditada = new Vagas(id, titulo, AreadeAtuacao, salarioMax, remoto, descricao);
            VagasDB.editar(indexReal, vagaEditada);
            System.out.println("Vaga Editada com Sucesso");

        }

    }

public static void BuscarCandidatoParaVaga(Scanner scan, GenericDAO<Vagas> VagasDB,
                                           GenericDAO<Candidato> CandidatosDB, MatchService matchService){
    var listadeVagas = VagasDB.listar();

    if(listadeVagas.isEmpty()){
        System.out.println("Nao há Vagas");
        return;
    }

    System.out.println("\n---VAGAS DISPONIVEIS---\n");

    for (int i = 0; i < listadeVagas.size(); i++) {
        System.out.println((i + 1) + " - " + listadeVagas.get(i).titulo());
    }

    System.out.println("Digite o index da vaga a qual a busca de Candidatos será feita: ");
    var index = scan.nextInt();
    scan.nextLine();
    var indexReal = index - 1;

    if(indexReal < 0 || indexReal >= listadeVagas.size()){
        System.out.println("Indice de vaga inválido!");
        return;
    }

    var vagabuscada = listadeVagas.get(indexReal);
    var listadeCandidatos = CandidatosDB.listar();

    if(listadeCandidatos.isEmpty()){
        System.out.println("Nao há candidatos");
        return;
    }

    List<ResultadoMatch> resultado = new ArrayList<>();

    for(Candidato candidato : listadeCandidatos){
        double aptidao = matchService.calcularAptidao(candidato, vagabuscada);
        resultado.add(new ResultadoMatch(candidato, aptidao));
    }

    resultado.sort((r1,r2) -> Double.compare(r2.nota(), r1.nota()));


    System.out.println("\n======RANKING DE CANDIDATOS PARA " + vagabuscada.titulo()+ "==========\n");
    for(int i = 0; i < resultado.size(); i++){
        ResultadoMatch saida = resultado.get(i);
        String classificacao = matchService.classificarNota((int) saida.nota());
        System.out.println((i + 1) + "º Lugar: " + saida.candidato()
                + " | Afinidade: " + String.format("%.1f", saida.nota()) + "%" +
                "-> [" + classificacao + "]");

    }

}

}
