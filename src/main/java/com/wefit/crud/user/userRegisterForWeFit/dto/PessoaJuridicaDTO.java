package com.wefit.crud.user.userRegisterForWeFit.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaJuridicaDTO(
        @NotBlank(message = "CNPJ é obrigatório")
        @CNPJ(message = "CNPJ inválido")
        String cnpj,

        @NotBlank(message = "CPF do responsável é obrigatório")
        @CPF(message = "CPF do responsável inválido")
        String cpfResponsavel,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        String email,

        @Valid
        EnderecoDTO endereco
) {}
