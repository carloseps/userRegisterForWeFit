package com.wefit.crud.user.userRegisterForWeFit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaFisicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.dto.PessoaJuridicaDTO;
import com.wefit.crud.user.userRegisterForWeFit.dto.EnderecoDTO;
import com.wefit.crud.user.userRegisterForWeFit.exception.ResourceAlreadyExistsException;
import com.wefit.crud.user.userRegisterForWeFit.exception.InvalidInputException;
import com.wefit.crud.user.userRegisterForWeFit.service.PessoaFisicaService;
import com.wefit.crud.user.userRegisterForWeFit.service.PessoaJuridicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PessoaControllerTest {

    @Mock
    private PessoaFisicaService pessoaFisicaService;

    @Mock
    private PessoaJuridicaService pessoaJuridicaService;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        new ObjectMapper();
    }

    private EnderecoDTO buildEnderecoDTO() {
        return new EnderecoDTO(
                "12345-678",
                "Rua Exemplo",
                "123",
                "Apto 1",
                "Cidade Teste",
                "Bairro Teste",
                "SP"
        );
    }

    @Test
    @DisplayName("Deve cadastrar pessoa física com sucesso")
    void shouldCreatePessoaFisica() {
        PessoaFisicaDTO dto = new PessoaFisicaDTO("123.456.789-00", "João da Silva", "joao@email.com", buildEnderecoDTO());

        when(pessoaFisicaService.cadastrarPessoaFisica(dto)).thenReturn(dto);

        ResponseEntity<PessoaFisicaDTO> response = pessoaController.cadastrarPessoaFisica(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(dto, response.getBody());
        verify(pessoaFisicaService, times(1)).cadastrarPessoaFisica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa física com CPF inválido")
    void shouldNotCreatePessoaFisica_withInvalidCpf() {
        PessoaFisicaDTO dto = new PessoaFisicaDTO("cpf-invalido", "Maria", "maria@email.com", buildEnderecoDTO());

        when(pessoaFisicaService.cadastrarPessoaFisica(dto)).thenThrow(new InvalidInputException("CPF inválido"));

        assertThrows(InvalidInputException.class, () -> {
            pessoaController.cadastrarPessoaFisica(dto);
        });

        verify(pessoaFisicaService, times(1)).cadastrarPessoaFisica(dto);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa jurídica com sucesso")
    void shouldCreatePessoaJuridica() {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "12.345.678/0001-90", "123.456.789-00", "Empresa LTDA", "contato@empresa.com", buildEnderecoDTO()
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto)).thenReturn(dto);

        ResponseEntity<PessoaJuridicaDTO> response = pessoaController.cadastrarPessoaJuridica(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(dto, response.getBody());
        verify(pessoaJuridicaService, times(1)).cadastrarPessoaJuridica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa jurídica com CPNJ inválido")
    void shouldNotCreatePessoaJuridica_withInvalidCnpj() {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "cnpj-invalido", "123.456.789-00", "Empresa LTDA", "contato@empresa.com", buildEnderecoDTO()
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto)).thenThrow(new InvalidInputException("CNPJ inválido"));

        assertThrows(InvalidInputException.class, () -> {
            pessoaController.cadastrarPessoaJuridica(dto);
        });

        verify(pessoaJuridicaService, times(1)).cadastrarPessoaJuridica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa física com CPF null")
    void shouldNotCreatePessoaFisica_withNullCpf() {
        PessoaFisicaDTO dto = new PessoaFisicaDTO(null, "João", "joao@email.com", buildEnderecoDTO());

        when(pessoaFisicaService.cadastrarPessoaFisica(dto))
                .thenThrow(new InvalidInputException("CPF é obrigatório"));

        assertThrows(InvalidInputException.class, () -> pessoaController.cadastrarPessoaFisica(dto));
        verify(pessoaFisicaService).cadastrarPessoaFisica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa física com e-mail inválido")
    void shouldNotCreatePessoaFisica_withInvalidEmail() {
        PessoaFisicaDTO dto = new PessoaFisicaDTO("123.456.789-00", "João", "email-invalido", buildEnderecoDTO());

        when(pessoaFisicaService.cadastrarPessoaFisica(dto))
                .thenThrow(new InvalidInputException("Email inválido"));

        assertThrows(InvalidInputException.class, () -> pessoaController.cadastrarPessoaFisica(dto));
        verify(pessoaFisicaService).cadastrarPessoaFisica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa jurídica com Endereço null")
    void shouldNotCreatePessoaJuridica_withNullEndereco() {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "12.345.678/0001-90", "123.456.789-00", "Empresa X", "empresa@email.com", null
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto))
                .thenThrow(new InvalidInputException("Endereço é obrigatório"));

        assertThrows(InvalidInputException.class, () -> pessoaController.cadastrarPessoaJuridica(dto));
        verify(pessoaJuridicaService).cadastrarPessoaJuridica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa jurídica com Estado inválido")
    void shouldNotCreatePessoaJuridica_withEstadoInvalido() {
        EnderecoDTO endereco = new EnderecoDTO("12345-678", "Rua", "10", "", "Cidade", "Bairro", "BRASIL");
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "12.345.678/0001-90", "123.456.789-00", "Empresa Y", "empresa@email.com", endereco
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto))
                .thenThrow(new InvalidInputException("Estado deve ter 2 letras"));

        assertThrows(InvalidInputException.class, () -> pessoaController.cadastrarPessoaJuridica(dto));
        verify(pessoaJuridicaService).cadastrarPessoaJuridica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa jurídica com nome vazio")
    void shouldNotCreatePessoaJuridica_withEmptyNome() {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "12.345.678/0001-90", "123.456.789-00", "", "empresa@email.com", buildEnderecoDTO()
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto))
                .thenThrow(new InvalidInputException("Nome é obrigatório"));

        assertThrows(InvalidInputException.class, () -> pessoaController.cadastrarPessoaJuridica(dto));
        verify(pessoaJuridicaService).cadastrarPessoaJuridica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa física com CPF já existente")
    void shouldNotCreatePessoaFisica_whenCpfAlreadyExists() {
        PessoaFisicaDTO dto = new PessoaFisicaDTO("123.456.789-00", "João da Silva", "joao@email.com", buildEnderecoDTO());

        when(pessoaFisicaService.cadastrarPessoaFisica(dto))
                .thenThrow(new ResourceAlreadyExistsException("CPF já cadastrado"));

        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            pessoaController.cadastrarPessoaFisica(dto);
        });

        assertTrue(exception.getMessage().contains("CPF já cadastrado"));
        verify(pessoaFisicaService).cadastrarPessoaFisica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa física com e-mail já existente")
    void shouldNotCreatePessoaFisica_whenEmailAlreadyExists() {
        PessoaFisicaDTO dto = new PessoaFisicaDTO("987.654.321-00", "João da Silva", "email@duplicado.com", buildEnderecoDTO());

        when(pessoaFisicaService.cadastrarPessoaFisica(dto))
                .thenThrow(new ResourceAlreadyExistsException("Email já cadastrado"));

        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            pessoaController.cadastrarPessoaFisica(dto);
        });

        assertTrue(exception.getMessage().contains("Email já cadastrado"));
        verify(pessoaFisicaService).cadastrarPessoaFisica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa juridica com CNPJ já existente")
    void shouldNotCreatePessoaJuridica_whenCnpjAlreadyExists() {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "12.345.678/0001-90", "123.456.789-00", "Empresa LTDA", "contato@empresa.com", buildEnderecoDTO()
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto))
                .thenThrow(new ResourceAlreadyExistsException("CNPJ já cadastrado"));

        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            pessoaController.cadastrarPessoaJuridica(dto);
        });

        assertTrue(exception.getMessage().contains("CNPJ já cadastrado"));
        verify(pessoaJuridicaService).cadastrarPessoaJuridica(dto);
    }

    @Test
    @DisplayName("Não deve cadastrar pessoa jurídica com e-mail já existente")
    void shouldNotCreatePessoaJuridica_whenEmailAlreadyExists() {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO(
                "00.000.000/0001-00", "123.456.789-00", "Empresa LTDA", "duplicado@email.com", buildEnderecoDTO()
        );

        when(pessoaJuridicaService.cadastrarPessoaJuridica(dto))
                .thenThrow(new ResourceAlreadyExistsException("Email já cadastrado"));

        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            pessoaController.cadastrarPessoaJuridica(dto);
        });

        assertTrue(exception.getMessage().contains("Email já cadastrado"));
        verify(pessoaJuridicaService).cadastrarPessoaJuridica(dto);
    }
}
