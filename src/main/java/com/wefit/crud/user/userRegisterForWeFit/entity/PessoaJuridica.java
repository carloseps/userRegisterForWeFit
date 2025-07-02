package com.wefit.crud.user.userRegisterForWeFit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@DiscriminatorValue("JURIDICA")
public class PessoaJuridica extends Pessoa {

    @CNPJ(message = "CNPJ inválido")
    @Column(unique = true)
    private String cnpj;

    @CPF(message = "CPF do responsável inválido")
    @Column(unique = true)
    private String cpfResponsavel;

    public PessoaJuridica(String cnpj, String cpfResponsavel, String nome, String email, Endereco endereco) {
        super(null, nome, null, null, email, endereco);
        this.cnpj = cnpj;
        this.cpfResponsavel = cpfResponsavel;
    }

    public PessoaJuridica(String cnpj, String cpfResponsavel) {
        this.cnpj = cnpj;
        this.cpfResponsavel = cpfResponsavel;
    }

    public PessoaJuridica(Long id, String nome, String celular, String telefone, String email, Endereco endereco, String cnpj, String cpfResponsavel) {
        super(id, nome, celular, telefone, email, endereco);
        this.cnpj = cnpj;
        this.cpfResponsavel = cpfResponsavel;
    }

    public PessoaJuridica () {}

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }
}
