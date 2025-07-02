package com.wefit.crud.user.userRegisterForWeFit.service;

import com.wefit.crud.user.userRegisterForWeFit.exception.ResourceAlreadyExistsException;
import com.wefit.crud.user.userRegisterForWeFit.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void validateCpfNotExists(String cpf) {
        if (pessoaRepository.existsByCpf(cpf)) {
            throw new ResourceAlreadyExistsException("CPF já cadastrado");
        }
    }

    public void validateCnpjNotExists(String cnpj) {
        if (pessoaRepository.existsByCnpj(cnpj)) {
            throw new ResourceAlreadyExistsException("CNPJ já cadastrado");
        }
    }
}


