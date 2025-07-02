package com.wefit.crud.user.userRegisterForWeFit.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaDTO(
        String tipoPessoa,

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome não pode exceder 100 caracteres")
        String nome,

        String celular,
        String telefone,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @Valid
        EnderecoDTO endereco
) {}
