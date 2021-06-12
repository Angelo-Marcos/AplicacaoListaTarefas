/*
 * Implementação de classe UsuarioDAO, onde são criados todos os métodos CRUD
 * para acesso ao banco de dados.
 */
package br.app.listatarefa.dao;

import br.app.listatarefa.model.Usuario;
import br.app.listatarefa.util.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class UsuarioDAO extends ConnectionFactory {

    /**
     * Método para inserir usuário no banco de dados.
     * 
     * @param usuario
     * @return boolean
     */
    public Boolean insertUsuarioDAO(Usuario usuario) {
        try {
            // Conexão com o banco de dados
            connect();

            // Sentença sql
            String sql = "INSERT INTO tbl_usuario("
                    + "usu_name, "
                    + "usu_login, "
                    + "usu_senha) "
                    + "VALUES (?, ?, ?)";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());

            // Executa a instrução SQL
            ps.executeUpdate();

            ps.close();
            // Desconecta o banco de dados
            desconnect();

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());

            return false;
        }
    }

    /**
     * Método para listar todos os usuários cadastrados no banco de dados.
     * 
     * @return List
     */
    public List<Usuario> readUsuarioDAO() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "SELECT * FROM tbl_usuario";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
            ResultSet rs;
            
            // Executa a instrução SQL e salva na variavél rs do tipo ResultSet
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("pk_usu_id"));
                usuario.setNome(rs.getString("usu_name"));
                usuario.setLogin(rs.getString("usu_login"));
                listaUsuarios.add(usuario);
            }

            ps.close();
            rs.close();
            
            // Desconecta o banco de dados
            desconnect();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista de usuários!");
            System.out.println(ex.toString());
        }

        return listaUsuarios;
    }
    
    /**
     * Método para buscar um usuário no banco de dados.
     * 
     * @param usu_id
     * @return usuario
     */
    public Usuario buscaUsuarioDAO(int usu_id) {

        Usuario usuario = new Usuario();
        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "SELECT pk_usu_id, "
                    + "usu_name, "
                    + "usu_login, "
                    + "usu_senha "
                    + "FROM tbl_usuario "
                    + "WHERE pk_usu_id = '"+usu_id+"'";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
            ResultSet rs;
            
            // Executa a instrução SQL e salva na variavél rs do tipo ResultSet
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("pk_usu_id"));
                usuario.setNome(rs.getString("usu_name"));
                usuario.setLogin(rs.getString("usu_login"));
                usuario.setSenha(rs.getString("usu_senha"));
                
            }

            ps.close();
            rs.close();
            
            // Desconecta o banco de dados
            desconnect();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário no banco de dados!");
            System.out.println(ex.toString());
        }

        return usuario;
    }

    /**
     * Método para atualizar um usuário pelo Id no banco de dados.
     * 
     * @param usuario
     * @return boolean
     */
    public Boolean updateUsuarioDAO(Usuario usuario) {

        try {
            // Conexão com o banco de dados
            connect();

            // Sentença sql
            String sql = "UPDATE tbl_usuario "
                    + "SET usu_name = ?, "
                    + "usu_login = ?, "
                    + "usu_senha = ? "
                    + "WHERE pk_usu_id = '"+usuario.getId()+"'";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            
            // executa a instrução SQL
            ps.executeUpdate();
            ps.close();
            
            // Desconecta o banco de dados
            desconnect();

            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar usuário!");
            System.out.println(ex.toString());

            return false;
        }
    }
    
    /**
     * Método para excluir um usuário pelo Id no banco de dados.
     * 
     * @param usu_id
     * @return boolean
     */
    public Boolean deleteUsuarioDAO(int usu_id) {
        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "DELETE FROM tbl_usuario "
                    + "WHERE pk_usu_id = '"+usu_id+"'";
            
            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
                      
            // executa a instrução SQL
            ps.execute();
            
            ps.close();
            
            // Desconecta o banco de dados
            desconnect();
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar excluir!");
            System.out.println(ex.toString());
            
            return false;
        } 
    }
}
