package projeto_matchvagas.model;

public record Candidato(
        String nome,
        String idade,
        String AreaTrabalho,
        double ExpectativaSalario,
        boolean PrefereRemoto) {
}
