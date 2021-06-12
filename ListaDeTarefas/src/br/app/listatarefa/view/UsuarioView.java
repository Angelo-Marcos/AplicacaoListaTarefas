/*
 * Classe UsuárioView para comunicação com o usuário externo.
 */
package br.app.listatarefa.view;

import br.app.listatarefa.controller.UsuarioController;
import br.app.listatarefa.dao.UsuarioDAO;
import br.app.listatarefa.model.Usuario;
import java.util.Scanner;

/**
 *
 * @author Angelo
 */
public class UsuarioView {

    Usuario usuario = new Usuario();
    UsuarioController usuarioController = new UsuarioController();
    Scanner sc = new Scanner(System.in);

    /**
     * Método para usuário externo cadastrar usuario.
     */
    public void cadastrarUsuario() {
        System.out.println("-----Cadastrando Usuario-----");
        System.out.print("Nome: ");
        usuario.setNome(sc.next());
        System.out.print("Login: ");
        usuario.setLogin(sc.next());
        System.out.print("Senha: ");
        usuario.setSenha(sc.next());

        if (usuarioController.insertUsuarioController(usuario) == true) {
            System.out.println("Usuário inserido com sucesso!");
            
        } else {
            System.out.println("Erro ao inserir usuário!");
        }
    }

    /**
     * Método para imprimir usuários.
     */
    public void listaUsuarios() {
        System.out.println("-----Usuarios-----");
        UsuarioDAO usuarioDao = new UsuarioDAO();

        for (Usuario usuarios : usuarioDao.readUsuarioDAO()) {
            System.out.println(
                    "Id: " + usuarios.getId()
                    + "\nNome: " + usuarios.getNome());
            System.out.println("------------------");
        }  
    }

    /**
     * Método para usuário externo excluir usuario.
     */
    public void deleteUsuario() {
        int usu_id;
        
        System.out.println("-----Excluir Usuario-----");
        System.out.print("Insira o ID do usuario: ");
        usu_id = sc.nextInt();

        if (usuarioController.deleteUsuarioController(usu_id) == true) {
            System.out.println("Usuário excluido com sucesso!");
            
        } else {
            System.out.println("Erro ao excluir usuário!");
        }
    }

    /**
     * Método para usuário externo atualizar usuario.
     */
    public void updateUsuario() {
        int usu_id;
        System.out.println("-----Atualizar Usuario-----");
        System.out.print("Insira o ID do usuario: ");
        usu_id = sc.nextInt();
        if (usuarioController.buscaUsuarioController(usu_id).getId() == 0) {
            System.out.println("Usuario NÃO encontrado!");
            
        } else {
            System.out.println("Usuario encontrado!");
            System.out.println(
                    "Id: " + usuarioController.buscaUsuarioController(usu_id).getId()
                    + "\nNome: " + usuarioController.buscaUsuarioController(usu_id).getNome()
                    + "\nLogin: " + usuarioController.buscaUsuarioController(usu_id).getLogin()
                    + "\nSenha: " + usuarioController.buscaUsuarioController(usu_id).getSenha());

            System.out.println("-----Atualize o usuario-----");
            usuario.setId(usu_id);
            System.out.print("Nome: ");
            usuario.setNome(sc.next());
            System.out.print("Login: ");
            usuario.setLogin(sc.next());
            System.out.print("Senha: ");
            usuario.setSenha(sc.next());

            if (usuarioController.updateUsuarioController(usuario) == true) {
                System.out.println("Usuário Atualizado com sucesso!");
                
            } else {
                System.out.println("Erro ao atualizar usuário!");
            }
        }
    }
}
