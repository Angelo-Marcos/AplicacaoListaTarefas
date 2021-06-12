/*
 * Clase para implementação de conexão com banco de dados.
 *
 */

package br.app.listatarefa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Angelo
 */
public class ConnectionFactory {

    private Connection conexao;

    public Connection getConnection() {
        return this.conexao;
    }

    /**
     * Faz a conexão com o banco de dados e cria o banco caso não exista.
     * 
     * @return boolean
     */
    public boolean connect() {
        try {
            String URL = "jdbc:sqlite:db/dblistatarefas.db";

            this.conexao = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar conectar!");
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }
    
    /**
     * Encerra a conexão com o banco de dados.
     * 
     * @return boolean
     */
    public boolean desconnect() {
        try {
            if (this.conexao.isClosed() == false) {
                this.conexao.isClosed();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar desconectar!");
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }

    /**
     * Cria o Statement para execução dos sqls.
     * 
     * @return statement
     */
    public Statement criarStatement() {
        try {
            return this.conexao.createStatement();
        } catch (SQLException ex) {
            System.out.println("Erro ao criar Statement!");
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * Cria o PreparedStatement para execução dos sqls.
     * 
     * @return preparedStatement
     */
    public PreparedStatement criarPreparedStatement(String sql) {
        try {
            return this.conexao.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao criar PreparedStatement!");
            System.out.println(ex.toString());
            return null;
        }
    }
}
