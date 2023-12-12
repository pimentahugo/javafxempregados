package com.mycompany.javafxativ2.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {

    private final String ENDERECO = "localhost";
    private final String BANCODEDADOS = "empregados";
    private final String PORTA = "5432";
    private final String USUARIO = "postgres";
    private final String SENHA = "123456";

    protected Connection getConexao() throws SQLException {
        String url = "jdbc:postgresql://" + ENDERECO + ":" + PORTA + "/" + BANCODEDADOS;
        Connection conn = DriverManager.getConnection(url, USUARIO, SENHA);
        return conn;
    }
}
