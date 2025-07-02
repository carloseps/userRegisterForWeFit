package com.wefit.crud.user.userRegisterForWeFit.mapper;

import com.wefit.crud.user.userRegisterForWeFit.dto.*;
import com.wefit.crud.user.userRegisterForWeFit.entity.*;

public class PessoaMapper {

    public static Endereco toEndereco(EnderecoDTO dto) {
        return new Endereco(
                dto.cep(),
                dto.logradouro(),
                dto.numero(),
                dto.complemento(),
                dto.cidade(),
                dto.bairro(),
                dto.estado()
        );
    }

    public static EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado()
        );
    }

    public static PessoaFisica toPessoaFisica(PessoaFisicaDTO dto) {
        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setCpf(dto.cpf());
        pessoa.setNome(dto.nome());
        pessoa.setEmail(dto.email());
        pessoa.setEndereco(toEndereco(dto.endereco()));
        return pessoa;
    }

    public static PessoaFisicaDTO toPessoaFisicaDTO(PessoaFisica pessoa) {
        return new PessoaFisicaDTO(
                pessoa.getCpf(),
                pessoa.getNome(),
                pessoa.getEmail(),
                toEnderecoDTO(pessoa.getEndereco())
        );
    }

    public static PessoaJuridica toPessoaJuridica(PessoaJuridicaDTO dto) {
        PessoaJuridica pessoa = new PessoaJuridica();
        pessoa.setCnpj(dto.cnpj());
        pessoa.setCpfResponsavel(dto.cpfResponsavel());
        pessoa.setNome(dto.nome());
        pessoa.setEmail(dto.email());
        pessoa.setEndereco(toEndereco(dto.endereco()));
        return pessoa;
    }

    public static PessoaJuridicaDTO toPessoaJuridicaDTO(PessoaJuridica pessoa) {
        return new PessoaJuridicaDTO(
                pessoa.getCnpj(),
                pessoa.getCpfResponsavel(),
                pessoa.getNome(),
                pessoa.getEmail(),
                toEnderecoDTO(pessoa.getEndereco())
        );
    }
}
