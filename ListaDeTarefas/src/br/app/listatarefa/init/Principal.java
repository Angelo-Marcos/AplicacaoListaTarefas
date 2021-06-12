/*
 * Classe Principal para implementa aplicação de lista de tarefas.
 */
package br.app.listatarefa.init;

import br.app.listatarefa.view.TarefaView;
import br.app.listatarefa.view.UsuarioView;
import java.util.Scanner;
import static javafx.application.Platform.exit;

/**
 *
 * @author Angelo
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioView usuario = new UsuarioView();
        TarefaView tarefa = new TarefaView();
        Scanner sc = new Scanner(System.in);

        System.out.print("--------LISTA DE TAREFAS--------\n");
        int op;
        do {
            System.out.print("-----MENU-----"
                    + "\n(1) Adicionar Usuario"
                    + "\n(2) Adicionar Tarefa"
                    + "\n(3) Excluir Usuario"
                    + "\n(4) Excluir Tarefa"
                    + "\n(5) Alterar Usuario"
                    + "\n(6) Alterar Tarefa"
                    + "\n(7) Listar Usuarios"
                    + "\n(8) Listar Tarefas"
                    + "\n(9) SAIR \nEscolha: ");

            op = sc.nextInt();
            switch (op) {
                case 1:
                    usuario.cadastrarUsuario();
                    break;

                case 2:
                    tarefa.cadastrarTarefa();
                    break;

                case 3:
                    usuario.deleteUsuario();
                    break;

                case 4:
                    tarefa.deleteTarefa();
                    break;

                case 5:
                    usuario.updateUsuario();
                    break;
                    
                case 6:
                    tarefa.updateTarefa();
                    break;
                    
                case 7:
                    usuario.listaUsuarios();
                    break;
                    
                case 8:
                    tarefa.listaTarefas();
                    break;
                    
                case 9:
                    exit();
                    break;
            }
        } while (op != 9);
    }
}
