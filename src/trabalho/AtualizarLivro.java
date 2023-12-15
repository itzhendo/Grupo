package teste_trabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarLivro {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/biblioteca";
        String usuario = "postgres";
        String senha = "1234";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {

            Scanner scanner = new Scanner(System.in);

            // Obter o ISBN do livro que o usuário deseja editar
            System.out.println("Digite o ISBN do livro que deseja editar:");
            String isbnLivro = scanner.nextLine().trim();

            // Obter os novos dados do livro
            System.out.println("Digite o novo ISBN (ou pressione Enter para manter o mesmo):");
            String novoIsbn = scanner.nextLine().trim();
            System.out.println("Digite o novo título do livro (ou pressione Enter para manter o mesmo):");
            String novoTitulo = scanner.nextLine().trim();
            System.out.println("Digite a nova edição do livro (ou pressione Enter para manter a mesma):");
            String novaEdicao = scanner.nextLine().trim();
            System.out.println("Digite o novo ano do livro (ou pressione 0 para manter o mesmo):");
            int novoAno = scanner.nextInt();
            System.out.println("Digite o novo ID do autor (ou pressione 0 para manter o mesmo):");
            int novoIdAutor = scanner.nextInt();
            System.out.println("Digite o novo ID da área do livro (ou pressione 0 para manter o mesmo):");
            int novoIdAreaLivro = scanner.nextInt();
            System.out.println("Digite o novo ID da editora (ou pressione 0 para manter o mesmo):");
            int novoIdEditora = scanner.nextInt();

            // Atualizar o livro
            atualizarLivro(connection, isbnLivro, novoIsbn, novoTitulo, novaEdicao, novoAno, novoIdAutor, novoIdAreaLivro, novoIdEditora);

            System.out.println("Livro atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void atualizarLivro(Connection connection, String isbnLivro, String novoIsbn, String novoTitulo, String novaEdicao,
                                       int novoAno, int novoIdAutor, int novoIdAreaLivro, int novoIdEditora) {
        try {
            // Construir a consulta SQL com base nos campos fornecidos pelo usuário
            StringBuilder sql = new StringBuilder("UPDATE Livro SET ");

            if (!novoIsbn.isEmpty()) {
                sql.append("ISBN=?, ");
            }
            if (!novoTitulo.isEmpty()) {
                sql.append("Titulo=?, ");
            }
            if (!novaEdicao.isEmpty()) {
                sql.append("Edicao=?, ");
            }
            if (novoAno != 0) {
                sql.append("Ano=?, ");
            }
            if (novoIdAutor != 0) {
                sql.append("ID_Autor=?, ");
            }
            if (novoIdAreaLivro != 0) {
                sql.append("ID_Area=?, ");
            }
            if (novoIdEditora != 0) {
                sql.append("ID_Editora=?, ");
            }

            // Remover a vírgula extra no final, se existir
            if (sql.toString().endsWith(", ")) {
                sql.setLength(sql.length() - 2);
            }

            // Adicionar a cláusula WHERE usando o ISBN
            sql.append(" WHERE ISBN=?");

            try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
                int parameterIndex = 1;

                if (!novoIsbn.isEmpty()) {
                    statement.setString(parameterIndex++, novoIsbn);
                }
                if (!novoTitulo.isEmpty()) {
                    statement.setString(parameterIndex++, novoTitulo);
                }
                if (!novaEdicao.isEmpty()) {
                    statement.setString(parameterIndex++, novaEdicao);
                }
                if (novoAno != 0) {
                    statement.setInt(parameterIndex++, novoAno);
                }
                if (novoIdAutor != 0) {
                    statement.setInt(parameterIndex++, novoIdAutor);
                }
                if (novoIdAreaLivro != 0) {
                    statement.setInt(parameterIndex++, novoIdAreaLivro);
                }
                if (novoIdEditora != 0) {
                    statement.setInt(parameterIndex++, novoIdEditora);
                }

                // Adicionar o ISBN do livro à cláusula WHERE
                statement.setString(parameterIndex, isbnLivro);

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}