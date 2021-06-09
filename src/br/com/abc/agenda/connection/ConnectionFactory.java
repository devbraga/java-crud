package br.com.abc.agenda.connection;

import java.sql.*;

public class ConnectionFactory {

    public static Connection createConnectionToMySQL() {
        String url = "jdbc:mysql://localhost:3306/agenda";
        String user = "root";
        String password = "root";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
