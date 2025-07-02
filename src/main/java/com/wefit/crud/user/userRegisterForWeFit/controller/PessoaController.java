package com.wefit.crud.user.userRegisterForWeFit.controller;

import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaFisicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaJuridicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.service.PessoaFisicaService;
import com.wefit.crud.user.userRegisterForWeFit.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping("/fisica")
    public ResponseEntity<PessoaFisicaDTO> cadastrarPessoaFisica(
            @RequestBody @Valid PessoaFisicaDTO dto) {

        PessoaFisicaDTO saved = pessoaFisicaService.cadastrarPessoaFisica(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/fisica")
    public ResponseEntity<List<PessoaFisicaDTO>> listarPessoasFisicas() {
        return ResponseEntity.ok(pessoaFisicaService.listarTodasPessoasFisicas());
    }

    @PostMapping("/juridica")
    public ResponseEntity<PessoaJuridicaDTO> cadastrarPessoaJuridica(
            @RequestBody @Valid PessoaJuridicaDTO dto) {

        PessoaJuridicaDTO saved = pessoaJuridicaService.cadastrarPessoaJuridica(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/juridica")
    public ResponseEntity<List<PessoaJuridicaDTO>> listarPessoasJuridicas() {
        return ResponseEntity.ok(pessoaJuridicaService.listarTodasPessoasJuridicas());
    }
}


