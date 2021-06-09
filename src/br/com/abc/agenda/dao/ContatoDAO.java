package br.com.abc.agenda.dao;

import br.com.abc.agenda.connection.ConnectionFactory;
import br.com.abc.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void save(Contato contato) {

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setInt(2, contato.getIdade());
            preparedStatement.setDate(3, new Date(contato.getDataCadastro().getTime()));
            preparedStatement.execute();
            System.out.println("Contato inserido com Sucesso");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Contato> listAll() {
        String sql = "SELECT * FROM contatos";
        List<Contato> contatoList = new ArrayList<>();

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                contatoList.add(new Contato(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getInt("idade"), resultSet.getDate("dataCadastro")));
            }
            System.out.println("Contatos Retornados com sucesso");
            return contatoList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

//    public List<Contato> listByName(String nome){
//        String sql = "SELECT * FROM contatos where nome like ?";
//        List<Contato> contatoList = new ArrayList<>();
//
//        try(Connection connection = ConnectionFactory.createConnectionToMySQL();
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            preparedStatement.setString(1, "%" + nome + "%");
//            while (resultSet.next()){
//                contatoList.add(new Contato(resultSet.getInt("id"), resultSet.getString("nome"),
//                        resultSet.getInt("idade"), resultSet.getDate("dataCadastro")));
//            }
//
//            return contatoList;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public List<Contato> listByName(String nome){
        String sql = "SELECT * FROM contatos where nome like ?";
        List<Contato> contatoList = new ArrayList<>();

        try(Connection connection = ConnectionFactory.createConnectionToMySQL();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + nome + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                contatoList.add(new Contato(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getInt("idade"), resultSet.getDate("dataCadastro")));
            }
            ConnectionFactory.close(resultSet);
            return contatoList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void update(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setInt(2, contato.getIdade());
            preparedStatement.setDate(3, new Date(contato.getDataCadastro().getTime()));

            preparedStatement.setInt(4, contato.getId());
            preparedStatement.execute();
            System.out.println("Contato atualizado com sucesso");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Contato deletado com sucesso");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
