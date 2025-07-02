package com.wefit.crud.user.userRegisterForWeFit.mapper;

import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaJuridicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaJuridica;

public class PessoaJuridicaMapper {

    public static PessoaJuridica toEntity(PessoaJuridicaDTO dto) {
        if (dto == null) return null;

        PessoaJuridica entity = new PessoaJuridica();
        entity.setCnpj(dto.cnpj());
        entity.setCpfResponsavel(dto.cpfResponsavel());
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setEndereco(EnderecoMapper.toEntity(dto.endereco()));
        return entity;
    }

    public static PessoaJuridicaDTO toDTO(PessoaJuridica entity) {
        if (entity == null) return null;

        return new PessoaJuridicaDTO(
                entity.getCnpj(),
                entity.getCpfResponsavel(),
                entity.getNome(),
                entity.getEmail(),
                EnderecoMapper.toDTO(entity.getEndereco())
        );
    }
}
