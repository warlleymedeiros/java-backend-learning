package projeto_matchvagas.model;

public record Vagas(
        String id,
        String titulo,
        String AreaAtuacao,
        Double salarioMaximo,
        boolean aceitaRemoto,
        String Descricao

) {
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ID: ").append(id).append("\n");
        s.append("Titulo: ").append(titulo).append("\n");
        s.append("Area de Trabalho").append(AreaAtuacao).append("\n");
        s.append("Salario Maximo: ").append(salarioMaximo).append("\n");
        s.append("Pode trabalhar remoto: ").append(aceitaRemoto).append("\n");
        s.append("Descrição: ").append(Descricao).append("\n");
        s.append("---------------\n");
        return s.toString();
    }
}
