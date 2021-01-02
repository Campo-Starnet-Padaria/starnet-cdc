/**
 Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
 @author Felipe Alafy
 Created on 20/11/2020
 */
package starnet.cdc.database.db;

import starnet.cdc.backend.Reference;

import java.sql.*;

public class mysql {
    private static final Reference reference = new Reference();
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = reference.getDbString();
    private static final String USER = reference.getDbuser();
    private static final String PASS = reference.getDbpass();

    public static Connection conexao() throws SQLException, ClassNotFoundException {
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
