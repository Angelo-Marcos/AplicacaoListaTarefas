/*
 * Tipo abstrato de dados Tarefa.
 */
package br.app.listatarefa.model;

/**
 *
 * @author Angelo
 */
public class Tarefa {

    private int id;
    private String descricao;
    private String data;
    private Usuario usuario;

    /**
     * Método construtor.
     * 
     * @param descricao
     * @param data
     * @param usuario 
     */
    public Tarefa(String descricao, String data, Usuario usuario) {
        this.descricao = descricao;
        this.data = data;
        this.usuario = usuario;
    }

    /**
     * Construtor vazio.
     */
    public Tarefa() {

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
