package projeto_matchvagas.model;

public record Vagas(
        String id,
        String titulo,
        String AreaAtuacao,
        String salarioMaximo,
        boolean aceitaRemoto
) {
}
