/*
 * Implementação de classe UsuarioController que intermedia a comunicação entre
 * as classes das camadas model e view.
 *
 */
package br.app.listatarefa.controller;

import br.app.listatarefa.dao.UsuarioDAO;
import br.app.listatarefa.model.Usuario;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class UsuarioController {

    UsuarioDAO usuarioDao = new UsuarioDAO();

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param usuario
     * @return boolean
     */
    public Boolean insertUsuarioController(Usuario usuario) {
        return this.usuarioDao.insertUsuarioDAO(usuario);
    }

    /**
     * Lista todos os usuários cadastrados no banco de dados.
     *
     * @return List
     */
    public List<Usuario> readUsuarioController() {
        return this.usuarioDao.readUsuarioDAO();
    }

    /**
     * Busca um usuário no banco de dados.
     *
     * @param usu_id
     * @return usuario
     */
    public Usuario buscaUsuarioController(int usu_id) {
        return this.usuarioDao.buscaUsuarioDAO(usu_id);
    }

    /**
     * Atualiza um usuário pelo Id no banco de dados.
     *
     * @param usuario
     * @return boolean
     */
    public Boolean updateUsuarioController(Usuario usuario) {
        return this.usuarioDao.updateUsuarioDAO(usuario);
    }

    /**
     * Excluir um usuário pelo Id no banco de dados.
     *
     * @param usu_id
     * @return boolean
     */
    public Boolean deleteUsuarioController(int usu_id) {
        return this.usuarioDao.deleteUsuarioDAO(usu_id);
    }
}
