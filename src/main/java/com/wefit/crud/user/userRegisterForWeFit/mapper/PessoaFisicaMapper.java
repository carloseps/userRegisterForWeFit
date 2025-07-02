package com.wefit.crud.user.userRegisterForWeFit.mapper;

import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaFisicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaFisica;

public class PessoaFisicaMapper {

    public static PessoaFisica toEntity(PessoaFisicaDTO dto) {
        if (dto == null) return null;

        PessoaFisica entity = new PessoaFisica();
        entity.setCpf(dto.cpf());
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setEndereco(EnderecoMapper.toEntity(dto.endereco()));
        return entity;
    }

    public static PessoaFisicaDTO toDTO(PessoaFisica entity) {
        if (entity == null) return null;

        return new PessoaFisicaDTO(
                entity.getCpf(),
                entity.getNome(),
                entity.getEmail(),
                EnderecoMapper.toDTO(entity.getEndereco())
        );
    }
}
