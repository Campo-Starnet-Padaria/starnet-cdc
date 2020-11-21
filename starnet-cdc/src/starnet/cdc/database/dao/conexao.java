package starnet.cdc.database.dao;

import java.sql.*;

public class conexao {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/starnet";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection conexao() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }

    //close
    public static void fecharConexao(Connection conexao, PreparedStatement statement) throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    //close + ResultSet
    public static void fecharConexao(Connection conexao, PreparedStatement statement, ResultSet rs) throws SQLException {
        fecharConexao(conexao, statement);
        if (rs != null) {
            rs.close();
        }
    }
}
