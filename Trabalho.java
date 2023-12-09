/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho;

/**
 *
 * @author Hendo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Trabalho {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/seu_banco_de_dados";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             Statement statement = connection.createStatement()) {

            //--------------Colaborador--------------------
            statement.executeUpdate("CREATE TABLE Colaborador (" +
                    "Matricula_Colaborador SERIAL PRIMARY KEY," +
                    "Nome VARCHAR(255)," +
                    "Numero_OBA VARCHAR(20)," +
                    "Email VARCHAR(255)," +
                    "Telefone VARCHAR(20)," +
                    "Estado VARCHAR(50)," +
                    "Cargo VARCHAR(100)" +
                    ")");

             //--------------Emprestimo--------------------
            statement.executeUpdate("CREATE TABLE Emprestimo (" +
                    "ID_Emprestimo SERIAL PRIMARY KEY," +
                    "Matricula_Colaborador INTEGER REFERENCES Colaborador(Matricula_Colaborador)," +
                    "ISBN VARCHAR(20) REFERENCES Livro(ISBN)," +
                    "Data_Emprestimo DATE," +
                    "Data_Devolucao DATE" +
                    ")");

             //--------------Livro--------------------
            statement.executeUpdate("CREATE TABLE Livro (" +
                    "ISBN VARCHAR(20) PRIMARY KEY," +
                    "Titulo VARCHAR(255)," +
                    "Edicao VARCHAR(50)," +
                    "Ano INTEGER" +
                    ")");

             //--------------Exemplar--------------------
            statement.executeUpdate("CREATE TABLE Exemplar (" +
                    "ID_Exemplar SERIAL PRIMARY KEY," +
                    "ISBN VARCHAR(20) REFERENCES Livro(ISBN)," +
                    "Estado VARCHAR(50)," +
                    "Preco DECIMAL(10, 2)," +
                    "Motivo VARCHAR(255)," +
                    "Data_Aquisicao DATE" +
                    ")");

             //--------------Editora--------------------
            statement.executeUpdate("CREATE TABLE Editora (" +
                    "ID_Editora SERIAL PRIMARY KEY," +
                    "Nome VARCHAR(255)" +
                    ")");

             //--------------Autor--------------------
            statement.executeUpdate("CREATE TABLE Autor (" +
                    "ID_Autor SERIAL PRIMARY KEY," +
                    "Nome VARCHAR(255)," +
                    "Nacionalidade VARCHAR(50)" +
                    ")");

             //--------------Area_Livro--------------------
            statement.executeUpdate("CREATE TABLE Area_Livro (" +
                    "ID_Area SERIAL PRIMARY KEY," +
                    "Descricao VARCHAR(255)" +
                    ")");
            
            System.out.println("Tabelas criadas com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

