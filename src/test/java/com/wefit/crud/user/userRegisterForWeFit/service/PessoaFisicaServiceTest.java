package com.wefit.crud.user.userRegisterForWeFit.service;

import com.wefit.crud.user.userRegisterForWeFit.dto.EnderecoDTO;
import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaFisicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.entity.Endereco;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaFisica;
import com.wefit.crud.user.userRegisterForWeFit.exception.ResourceAlreadyExistsException;
import com.wefit.crud.user.userRegisterForWeFit.mapper.PessoaMapper;
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
class PessoaFisicaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private PessoaFisicaService pessoaFisicaService;

    private PessoaFisicaDTO pessoaFisicaDTO;
    private PessoaFisica pessoaFisica;

    @BeforeEach
    void setUp() {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                "12345-678", "Rua Teste", "123", "Apto 1", "Cidade Teste", "Bairro Teste", "SP"
        );
        Endereco endereco = new Endereco(
                "12345-678", "Rua Teste", "123", "Apto 1", "Cidade Teste", "Bairro Teste", "SP"
        );

        pessoaFisicaDTO = new PessoaFisicaDTO(
                "111.111.111-11", "João da Silva", "joao@example.com", enderecoDTO
        );
        pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf(pessoaFisicaDTO.cpf());
        pessoaFisica.setNome(pessoaFisicaDTO.nome());
        pessoaFisica.setEmail(pessoaFisicaDTO.email());
        pessoaFisica.setEndereco(endereco);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa física com sucesso")
    void cadastrarPessoaFisica_Success() {
        doNothing().when(validationService).validateCpfNotExists(pessoaFisicaDTO.cpf());
        when(pessoaRepository.save(any(PessoaFisica.class))).thenReturn(pessoaFisica);

        assertDoesNotThrow(() -> pessoaFisicaService.cadastrarPessoaFisica(pessoaFisicaDTO));
        verify(validationService, times(1)).validateCpfNotExists(pessoaFisicaDTO.cpf());
        verify(pessoaRepository, times(1)).save(any(PessoaFisica.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceAlreadyExistsException quando CPF já existe")
    void cadastrarPessoaFisica_CpfAlreadyExists() {
        doThrow(new ResourceAlreadyExistsException("CPF já cadastrado"))
                .when(validationService).validateCpfNotExists(pessoaFisicaDTO.cpf());

        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class,
                () -> pessoaFisicaService.cadastrarPessoaFisica(pessoaFisicaDTO));

        assertEquals("CPF já cadastrado", exception.getMessage());
        verify(validationService, times(1)).validateCpfNotExists(pessoaFisicaDTO.cpf());
        verify(pessoaRepository, never()).save(any(PessoaFisica.class));
    }

    @Test
    @DisplayName("Deve listar todas as pessoas físicas")
    void listarTodasPessoasFisicas() {
        when(pessoaRepository.findAllPessoaFisica()).thenReturn(Arrays.asList(pessoaFisica));

        List<PessoaFisicaDTO> result = pessoaFisicaService.listarTodasPessoasFisicas();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(pessoaFisicaDTO.cpf(), result.get(0).cpf());
        verify(pessoaRepository, times(1)).findAllPessoaFisica();
    }
}
