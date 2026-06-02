package projeto_matchvagas.model;

public record Candidato(
        String nome,
        String idade,
        String numConta,
        String AreaTrabalho,
        String Descricao,
        double ExpectativaSalario,
        boolean PrefereRemoto) {

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append("Nome: ").append(nome).append("\n");
        s.append("Idade: ").append(idade).append("\n");
        s.append("Numero da Conta: ").append(numConta).append("\n");
        s.append("Area de Atuação: ").append(AreaTrabalho).append("\n");
        s.append("Expectativa de Salario: ").append(ExpectativaSalario).append("\n");
        s.append("Prefere Remoto: ").append(PrefereRemoto).append("\n");
        s.append("Descrição: ").append(Descricao).append("\n");
        s.append("---------------\n");
        return s.toString();

    }
}
