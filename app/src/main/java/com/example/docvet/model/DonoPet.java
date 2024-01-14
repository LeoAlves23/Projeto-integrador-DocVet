package com.example.docvet.model;

import java.io.Serializable;
import java.util.List;

public class DonoPet implements Serializable {

    private String nome;
    private String cpf;
    private String email;
    private String foto;
    private String senha;
    List<String> telefones;

    public DonoPet(String nome, String cpf, String email, String foto, String senha, List<String> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.foto = foto;
        this.senha = senha;
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", foto='" + foto + '\'' +
                ", senha='" + senha + '\'' +
                ", telefones=" + telefones +
                '}';
    }
}
