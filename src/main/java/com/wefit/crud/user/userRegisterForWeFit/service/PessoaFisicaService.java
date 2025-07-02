package com.wefit.crud.user.userRegisterForWeFit.service;

import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaFisicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaFisica;
import com.wefit.crud.user.userRegisterForWeFit.mapper.PessoaMapper;
import com.wefit.crud.user.userRegisterForWeFit.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ValidationService validationService;

    public PessoaFisicaDTO cadastrarPessoaFisica(PessoaFisicaDTO dto) {
        validationService.validateCpfNotExists(dto.cpf());
        PessoaFisica pessoa = PessoaMapper.toPessoaFisica(dto);
        return PessoaMapper.toPessoaFisicaDTO(pessoaRepository.save(pessoa));
    }

    public List<PessoaFisicaDTO> listarTodasPessoasFisicas() {
        return pessoaRepository.findAllPessoaFisica()
                .stream()
                .map(PessoaMapper::toPessoaFisicaDTO)
                .collect(Collectors.toList());
    }
}
