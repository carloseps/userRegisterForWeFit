package com.wefit.crud.user.userRegisterForWeFit.service;

import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaJuridicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaJuridica;
import com.wefit.crud.user.userRegisterForWeFit.mapper.PessoaMapper;
import com.wefit.crud.user.userRegisterForWeFit.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ValidationService validationService;

    public PessoaJuridicaDTO cadastrarPessoaJuridica(PessoaJuridicaDTO dto) {
        validationService.validateCnpjNotExists(dto.cnpj());
        PessoaJuridica pessoa = PessoaMapper.toPessoaJuridica(dto);
        return PessoaMapper.toPessoaJuridicaDTO(pessoaRepository.save(pessoa));
    }

    public List<PessoaJuridicaDTO> listarTodasPessoasJuridicas() {
        return pessoaRepository.findAllPessoaJuridica()
                .stream()
                .map(PessoaMapper::toPessoaJuridicaDTO)
                .collect(Collectors.toList());
    }
}
