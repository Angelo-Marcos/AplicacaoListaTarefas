/*
 * Classe TarefaView para comunicação com o usuário externo.
 */
package br.app.listatarefa.view;

import br.app.listatarefa.controller.TarefaController;
import br.app.listatarefa.dao.TarefaDAO;
import br.app.listatarefa.model.Tarefa;
import br.app.listatarefa.model.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.date.DateFormatUtils;

/**
 *
 * @author Angelo
 */
public class TarefaView {

    Tarefa tarefa = new Tarefa();
    Usuario usuario = new Usuario();
    TarefaController tarefaController = new TarefaController();
    Scanner sc = new Scanner(System.in);

    /**
     * Método para usuário externo cadastrar tarefa.
     */
    public void cadastrarTarefa() {
        System.out.println("-----Adicionando Tarefa-----");
        System.out.print("Descrição da tarefa: ");
        tarefa.setDescricao(sc.nextLine());
        System.out.print("Data (dd/MM/yyyy): ");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataFormatada = formato.parse(sc.next());
            String data = DateFormatUtils.format(dataFormatada, "dd/MM/yyyy");
            tarefa.setData(data);
        } catch (ParseException ex) {
            Logger.getLogger(TarefaView.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("Id do usuario: ");
        usuario.setId(sc.nextInt());

        tarefa.setUsuario(usuario);

        if (tarefaController.insertTarefaController(tarefa) == true) {
            System.out.println("Tarefa inserida com sucesso!");
            
        } else {
            System.out.println("Erro ao inserir tarefa!");
        }
    }

    /**
     * Método para imprimir tarefas.
     */
    public void listaTarefas() {
        TarefaDAO tarefaDao = new TarefaDAO();
        
        System.out.println("\n-----Tarefas-----");
        
        for (Tarefa tarefa : tarefaDao.readTarefaDAO()) {
            System.out.println(
                    "Id: " + tarefa.getId()
                    + "\nDescricao: " + tarefa.getDescricao()
                    + "\nData: " + tarefa.getData()
                    + "\nUsuario: " + tarefa.getUsuario().getNome());
            System.out.println("-----------------");
        }
    }

    /**
     * Método para usuário externo excluir tarefa.
     */
    public void deleteTarefa() {
        int tar_id;
        
        System.out.println("-----Excluir Tarefa-----");
        System.out.print("Insira o ID da tarefa: ");
        tar_id = sc.nextInt();

        if (tarefaController.deleteTarefaController(tar_id) == true) {
            System.out.println("Tarefa excluida com sucesso!");
            
        } else {
            System.out.println("Erro ao excluir tarefa!");
        }
    }

    /**
     * Método para usuário externo atualizar tarefa.
     */
    public void updateTarefa() {
        String tarefa_id;
        System.out.println("-----Atualizar Tarefa-----");
        System.out.print("Insira o ID da tarefa: ");
        tarefa_id = sc.nextLine();

        int tar_id = Integer.parseInt(tarefa_id);
        
        if (tarefaController.buscaTarefaController(tar_id).getId() == 0) {
            System.out.println("Tarefa NÃO encontrada!");
            
        } else {
            System.out.println("Tarefa encontrada!");
            System.out.println(
                    "Id: " + tarefaController.buscaTarefaController(tar_id).getId()
                    + "\nDescricao: " + tarefaController.buscaTarefaController(tar_id).getDescricao()
                    + "\nData: " + tarefaController.buscaTarefaController(tar_id).getData()
                    + "\nUsuario: " + tarefaController.buscaTarefaController(tar_id).getUsuario().getNome());

            System.out.println("-----Atualize a tarefa-----");
            tarefa.setId(tar_id);
            System.out.print("Descrição: ");
            tarefa.setDescricao(sc.nextLine());
            System.out.print("Data: ");
            tarefa.setData(sc.next());

            if (tarefaController.updateTarefaController(tarefa) == true) {
                System.out.println("Tarefa Atualizada com sucesso!");
            } else {
                System.out.println("Erro ao atualizar tarefa!");
            }
        }
    }
}
