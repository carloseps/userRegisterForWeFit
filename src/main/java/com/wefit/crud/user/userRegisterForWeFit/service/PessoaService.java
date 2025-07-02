package com.wefit.crud.user.userRegisterForWeFit.service;

import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaFisicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaJuridicaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    public PessoaFisicaDTO cadastrarPessoaFisica(PessoaFisicaDTO dto) {
        return pessoaFisicaService.cadastrarPessoaFisica(dto);
    }

    public PessoaJuridicaDTO cadastrarPessoaJuridica(PessoaJuridicaDTO dto) {
        return pessoaJuridicaService.cadastrarPessoaJuridica(dto);
    }

    public List<PessoaFisicaDTO> listarTodasPessoasFisicas() {
        return pessoaFisicaService.listarTodasPessoasFisicas();
    }

    public List<PessoaJuridicaDTO> listarTodasPessoasJuridicas() {
        return pessoaJuridicaService.listarTodasPessoasJuridicas();
    }
}


