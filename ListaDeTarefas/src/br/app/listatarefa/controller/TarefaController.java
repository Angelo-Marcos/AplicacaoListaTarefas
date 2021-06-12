/*
 * Implementação de classe TarefaController que intermedia a comunicação entre
 * as classes das camadas model e view.
 *
 */
package br.app.listatarefa.controller;

import br.app.listatarefa.dao.TarefaDAO;
import br.app.listatarefa.model.Tarefa;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class TarefaController {

    TarefaDAO tarefaDao = new TarefaDAO();

    /**
     * Insere uma nova tarefa no banco de dados.
     *
     * @param tarefa
     * @return boolean
     */
    public Boolean insertTarefaController(Tarefa tarefa) {
        return this.tarefaDao.insertTarefaDAO(tarefa);
    }

    /**
     * Lista todas as tarefas cadastrados no banco de dados.
     *
     * @return List
     */
    public List<Tarefa> readTarefaController() {
        return this.tarefaDao.readTarefaDAO();
    }

    /**
     * Busca uma tarefa no banco de dados.
     *
     * @param tar_id
     * @return tarefa
     */
    public Tarefa buscaTarefaController(int tar_id) {
        return this.tarefaDao.buscaTarefaDAO(tar_id);
    }

    /**
     * Atualiza uma tarefa pelo Id no banco de dados.
     *
     * @param tarefa
     * @return boolean
     */
    public Boolean updateTarefaController(Tarefa tarefa) {
        return this.tarefaDao.updateTarefaDAO(tarefa);
    }

    /**
     * Exclui uma tarefa pelo Id no banco de dados.
     *
     * @param tar_id
     * @return boolean
     */
    public Boolean deleteTarefaController(int tar_id) {
        return this.tarefaDao.deleteTarefaDAO(tar_id);
    }
}
