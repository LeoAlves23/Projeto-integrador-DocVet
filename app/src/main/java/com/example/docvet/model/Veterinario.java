package com.example.docvet.model;

import java.io.Serializable;
import java.util.List;

public class Veterinario implements Serializable {

    private String nome;
    private String cpf;
    private String email;
    private String foto;
    private String senha;
    List<String> telefones;

    List<Especialidades> especialidades;

    private String descricao;
    private String crmv;

//    private List<Endereco> enderecos;

    public Veterinario(String nome, String cpf, String email, String senha, String crmv, List<String> telefones, String descricao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
        this.crmv = crmv;
        this.descricao = descricao;
    }

    public Veterinario(String nome, String cpf, String email, String foto, String senha, List<String> telefones, List<Especialidades> especialidades, String descricao, String crmv) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.foto = foto;
        this.senha = senha;
        this.telefones = telefones;
        this.especialidades = especialidades;
        this.descricao = descricao;
        this.crmv = crmv;
    }

    public Veterinario(String nome, String cpf, String email, String senha, String crmv, List<String> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
        this.crmv = crmv;
    }

//    public Veterinario(String nome, String cpf, String email, String foto, String senha, List<String> telefones, List<Especialidades> especialidades, String descricao, String crmv, List<Endereco> enderecos) {
//        this.nome = nome;
//        this.cpf = cpf;
//        this.email = email;
//        this.foto = foto;
//        this.senha = senha;
//        this.telefones = telefones;
//        this.especialidades = especialidades;
//        this.descricao = descricao;
//        this.crmv = crmv;
//        this.enderecos = enderecos;
//    }

    public Veterinario(String nome, String crmv){
        this.nome = nome;
        this.crmv = crmv;
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

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

//    public List<Endereco> getEnderecos() {
//        return enderecos;
//    }
//
//    public void setEnderecos(List<Endereco> enderecos) {
//        this.enderecos = enderecos;
//    }

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
