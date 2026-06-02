package projeto_matchvagas.service;

import projeto_matchvagas.model.Candidato;
import projeto_matchvagas.model.Vagas;
import projeto_matchvagas.repository.RepositoryCandidato;
import projeto_matchvagas.repository.RepositoryVagas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchService {

    public Map<String, List<Candidato>> selecionarCandidatosParaVaga(Vagas vaga, RepositoryCandidato candidatosDB){

           var listaDeCandidatosGeral = candidatosDB.listar();

           return listaDeCandidatosGeral.stream()
                   .collect(Collectors.groupingBy(candidato -> {
                       int nota = calcularAptidao(candidato, vaga);

                       return classificarNota(nota);
                   }));
    }



    public int calcularAptidao(Candidato candidato, Vagas vaga){
        var aptidao = 0;

        if(candidato.AreaTrabalho() != null && candidato.AreaTrabalho().equalsIgnoreCase(vaga.AreaAtuacao())) {
            aptidao += 3;
        }
        if(candidato.ExpectativaSalario() <= vaga.salarioMaximo()) aptidao +=2;
        if(candidato.PrefereRemoto() == vaga.aceitaRemoto()) aptidao+=1;

        return aptidao;
    }

    public String classificarNota(int nota){
        return switch(nota){
            case 6 -> "PERFEITO";
            case 5 -> "OTIMO";
            case 4 -> "BOM";
            default -> "RUIM";
        };
    }
}
