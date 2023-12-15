package teste_trabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AdicionarLivro {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/biblioteca";
        String usuario = "postgres";
        String senha = "1234";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {

            // Adicionar um autor;;
//            adicionarAutor(connection, "Autor Exemplo", "Nacionalidade Exemplo");

            // Adicionar uma área de livro
//            adicionarAreaLivro(connection, "Romance");

            // Adicionar uma editora
//            adicionarEditora(connection, "Editora Exemplo");

            // Adicionar um livro
            adicionarLivro(connection, "1234567890", "Título do Livro", "Edição 1", 2023, 1, 1, 1);

            System.out.println("Livro adicionado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adicionarAutor(Connection connection, String nome, String nacionalidade) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Autor (Nome, Nacionalidade) VALUES (?, ?)")) {
            statement.setString(1, nome);
            statement.setString(2, nacionalidade);
            statement.executeUpdate();
        }
    }

    private static void adicionarAreaLivro(Connection connection, String descricao) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Area_Livro (Descricao) VALUES (?)")) {

            statement.setString(1, descricao);
            statement.executeUpdate();
        }
    }

    private static void adicionarEditora(Connection connection, String nome) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Editora (Nome) VALUES (?)")) {
            statement.setString(1, nome);
            statement.executeUpdate();
        }
    }

    private static void adicionarLivro(Connection connection, String isbn, String titulo, String edicao,
                                       int ano, int idAutor, int idAreaLivro, int idEditora) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Livro (ISBN, Titulo, Edicao, Ano, ID_Autor, ID_Area, ID_Editora) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, isbn);
            statement.setString(2, titulo);
            statement.setString(3, edicao);
            statement.setInt(4, ano);
            statement.setInt(5, idAutor);
            statement.setInt(6, idAreaLivro);
            statement.setInt(7, idEditora);
            statement.executeUpdate();
        }
    }
}
