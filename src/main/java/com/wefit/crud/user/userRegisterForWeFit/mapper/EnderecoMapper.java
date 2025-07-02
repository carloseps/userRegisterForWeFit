package com.wefit.crud.user.userRegisterForWeFit.mapper;

import com.wefit.crud.user.userRegisterForWeFit.dto.EnderecoDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) return null;

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

    public static EnderecoDTO toDTO(Endereco entity) {
        if (entity == null) return null;

        return new EnderecoDTO(
                entity.getCep(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getCidade(),
                entity.getBairro(),
                entity.getEstado()
        );
    }
}
