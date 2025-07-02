package com.wefit.crud.user.userRegisterForWeFit.service;

import com.wefit.crud.user.userRegisterForWeFit.dto.EnderecoDTO;
import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaJuridicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.Endereco;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaJuridica;
import com.wefit.crud.user.userRegisterForWeFit.exception.ResourceAlreadyExistsException;
import com.wefit.crud.user.userRegisterForWeFit.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaJuridicaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private PessoaJuridicaService pessoaJuridicaService;

    private PessoaJuridicaDTO pessoaJuridicaDTO;
    private PessoaJuridica pessoaJuridica;

    @BeforeEach
    void setUp() {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                "12345-678", "Rua Teste", "123", "Apto 1", "Cidade Teste", "Bairro Teste", "SP"
        );
        Endereco endereco = new Endereco(
                "12345-678", "Rua Teste", "123", "Apto 1", "Cidade Teste", "Bairro Teste", "SP"
        );

        pessoaJuridicaDTO = new PessoaJuridicaDTO(
                "11.111.111/0001-11", "111.111.111-11", "Empresa Teste", "empresa@example.com", enderecoDTO
        );
        pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj(pessoaJuridicaDTO.cnpj());
        pessoaJuridica.setCpfResponsavel(pessoaJuridicaDTO.cpfResponsavel());
        pessoaJuridica.setNome(pessoaJuridicaDTO.nome());
        pessoaJuridica.setEmail(pessoaJuridicaDTO.email());
        pessoaJuridica.setEndereco(endereco);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa jurídica com sucesso")
    void cadastrarPessoaJuridica_Success() {
        doNothing().when(validationService).validateCnpjNotExists(pessoaJuridicaDTO.cnpj());
        when(pessoaRepository.save(any(PessoaJuridica.class))).thenReturn(pessoaJuridica);

        assertDoesNotThrow(() -> pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridicaDTO));
        verify(validationService, times(1)).validateCnpjNotExists(pessoaJuridicaDTO.cnpj());
        verify(pessoaRepository, times(1)).save(any(PessoaJuridica.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceAlreadyExistsException quando CNPJ já existe")
    void cadastrarPessoaJuridica_CnpjAlreadyExists() {
        doThrow(new ResourceAlreadyExistsException("CNPJ já cadastrado"))
                .when(validationService).validateCnpjNotExists(pessoaJuridicaDTO.cnpj());

        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class,
                () -> pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridicaDTO));

        assertEquals("CNPJ já cadastrado", exception.getMessage());
        verify(validationService, times(1)).validateCnpjNotExists(pessoaJuridicaDTO.cnpj());
        verify(pessoaRepository, never()).save(any(PessoaJuridica.class));
    }

    @Test
    @DisplayName("Deve listar todas as pessoas jurídicas")
    void listarTodasPessoasJuridicas() {
        when(pessoaRepository.findAllPessoaJuridica()).thenReturn(Arrays.asList(pessoaJuridica));

        List<PessoaJuridicaDTO> result = pessoaJuridicaService.listarTodasPessoasJuridicas();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(pessoaJuridicaDTO.cnpj(), result.get(0).cnpj());
        verify(pessoaRepository, times(1)).findAllPessoaJuridica();
    }
}