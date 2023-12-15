/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste_trabalho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author jhiag
 */
public class ConsultarLivro {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/biblioteca";
        String usuario = "postgres";
        String senha = "1234";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {

            // Consultar autores
            consultarAutores(connection);

            // Consultar áreas de livro
            consultarAreasLivro(connection);

            // Consultar editoras
            consultarEditoras(connection);

            // Consultar livros
            consultarLivros(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarAutores(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Autor")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("Nome");
                String nacionalidade = resultSet.getString("Nacionalidade");
                System.out.println("Autor: " + nome + ", Nacionalidade: " + nacionalidade);
            }
        }
    }

    private static void consultarAreasLivro(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Area_Livro")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String descricao = resultSet.getString("Descricao");
                System.out.println("Área de Livro: " + descricao);
            }
        }
    }

    private static void consultarEditoras(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Editora")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("Nome");
                System.out.println("Editora: " + nome);
            }
        }
    }

    private static void consultarLivros(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Livro")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String titulo = resultSet.getString("Titulo");
                String edicao = resultSet.getString("Edicao");
                int ano = resultSet.getInt("Ano");
                int idAutor = resultSet.getInt("ID_Autor");
                int idAreaLivro = resultSet.getInt("ID_Area");
                int idEditora = resultSet.getInt("ID_Editora");
                System.out.println("Livro: ISBN=" + isbn + ", Título=" + titulo +
                        ", Edição=" + edicao + ", Ano=" + ano + ", ID_Autor=" + idAutor +
                        ", ID_Area=" + idAreaLivro + ", ID_Editora=" + idEditora);
            }
        }
    }
}
