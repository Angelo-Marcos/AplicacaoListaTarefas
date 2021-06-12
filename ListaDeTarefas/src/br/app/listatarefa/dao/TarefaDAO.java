/*
 * Implementação de classe TarefaDAO, onde são criados todos os métodos CRUD
 * para acesso ao banco de dados.
 */
package br.app.listatarefa.dao;

import br.app.listatarefa.model.Tarefa;
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
public class TarefaDAO extends ConnectionFactory{
    /**
     * Método para inserir uma tarefa no banco de dados.
     * 
     * @param tarefa
     * @return boolean
     */
    public Boolean insertTarefaDAO(Tarefa tarefa) {        
        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "INSERT INTO tbl_tarefa("
                    + "tar_descricao, "
                    + "tar_data,"
                    + "fk_usu_id) "
                    + "VALUES (?, ?, ?)";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
            ps.setString(1, tarefa.getDescricao());
            ps.setString(2, tarefa.getData());
            ps.setInt(3, tarefa.getUsuario().getId());
            
            // Executa a instrução SQL
            ps.executeUpdate();
            
            ps.close();
            // Desconecta o banco de dados
            desconnect();
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar inserir tarefa!");
            System.out.println(ex.toString());
            
            return false;
        }  
    }
    
    /**
     * Método para listar todas as tarefas cadastrados no banco de dados.
     * 
     * @return List
     */
    public List<Tarefa> readTarefaDAO() {

        List<Tarefa> listaTarefas = new ArrayList<>();

        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "SELECT pk_tar_id, "
                    + "tar_descricao, "
                    + "tar_data, "
                    + "fk_usu_id, "
                    + "tbl_usuario.usu_name "
                    + "FROM tbl_tarefa "
                    + "INNER JOIN tbl_usuario "
                    + "ON tbl_usuario.pk_usu_id = tbl_tarefa.fk_usu_id";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
            ResultSet rs;
            
            // Executa a instrução SQL e salva na variavél rs do tipo ResultSet
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("pk_tar_id"));
                tarefa.setDescricao(rs.getString("tar_descricao"));
                tarefa.setData(rs.getString("tar_data"));
                
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("fk_usu_id"));
                usuario.setNome(rs.getString("usu_name"));
                
                tarefa.setUsuario(usuario);
                
                listaTarefas.add(tarefa);
            }

            ps.close();
            rs.close();
            
            // Desconecta o banco de dados
            desconnect();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista de tarefas!");
            System.out.println(ex.toString());
        }

        return listaTarefas;
    }
    
    /**
     * Método para buscar uma tarefa no banco de dados.
     * 
     * @param tar_id
     * @return tarefa
     */
    public Tarefa buscaTarefaDAO(int tar_id) {
        Usuario usuario = new Usuario();
        Tarefa tarefa = new Tarefa();
        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "SELECT pk_tar_id, "
                    + "tar_descricao, "
                    + "tar_data,"
                    + "fk_usu_id "
                    + "FROM tbl_tarefa "
                    + "WHERE pk_tar_id = '"+tar_id+"'";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
            ResultSet rs;
            
            // Executa a instrução SQL e salva na variavél rs do tipo ResultSet
            rs = ps.executeQuery();

            while (rs.next()) {
                
                tarefa.setId(rs.getInt("pk_tar_id"));
                tarefa.setDescricao(rs.getString("tar_descricao"));
                tarefa.setData(rs.getString("tar_data"));
                
                usuario.setId(rs.getInt("fk_usu_id"));
                
                tarefa.setUsuario(usuario);
            }

            ps.close();
            rs.close();
            
            // Desconecta o banco de dados
            desconnect();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário no banco de dados!");
            System.out.println(ex.toString());
        }

        return tarefa;
    }

    /**
     * Método para atualizar uma tarefa pelo Id no banco de dados.
     * 
     * @param tarefa
     * @return boolean
     */
    public Boolean updateTarefaDAO(Tarefa tarefa) {

        try {
            // Conexão com o banco de dados
            connect();

            // Sentença sql
            String sql = "UPDATE tbl_tarefa "
                    + "SET tar_descricao = ?, "
                    + "tar_data = ? "
                    + "WHERE pk_tar_id = '"+tarefa.getId()+"'";

            // Prepara a sentença que será executada no BD
            PreparedStatement ps = criarPreparedStatement(sql);
            ps.setString(1, tarefa.getDescricao());
            ps.setString(2, tarefa.getData());

            ps.executeUpdate();
            ps.close();
            
            // Desconecta o banco de dados
            desconnect();

            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar tarefa!");
            System.out.println(ex.toString());

            return false;
        }
    }
    
    /**
     * Método para excluir uma tarefa pelo Id no banco de dados.
     * 
     * @param tar_id
     * @return boolean
     */
    public Boolean deleteTarefaDAO(int tar_id) {
        try {
            // Conexão com o banco de dados
            connect();
            
            // Sentença sql
            String sql = "DELETE FROM tbl_tarefa WHERE pk_tar_id = '"+tar_id+"'";
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
