package com.wefit.crud.user.userRegisterForWeFit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@DiscriminatorValue("FISICA")
public class PessoaFisica extends Pessoa {

    @CPF(message = "CPF inválido")
    @Column(unique = true)
    private String cpf;

    public PessoaFisica(String cpf) {
        this.cpf = cpf;
    }

    public PessoaFisica(Long id, String nome, String celular, String telefone, String email, Endereco endereco, String cpf) {
        super(id, nome, celular, telefone, email, endereco);
        this.cpf = cpf;
    }

    public PessoaFisica() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
