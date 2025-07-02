package com.wefit.crud.user.userRegisterForWeFit.repository;

import com.wefit.crud.user.userRegisterForWeFit.entity.Pessoa;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaFisica;
import com.wefit.crud.user.userRegisterForWeFit.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE TYPE(p) = PessoaFisica")
    List<PessoaFisica> findAllPessoaFisica();

    @Query("SELECT p FROM Pessoa p WHERE TYPE(p) = PessoaJuridica")
    List<PessoaJuridica> findAllPessoaJuridica();

    @Query("SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf")
    Optional<PessoaFisica> findByCpf(@Param("cpf") String cpf);

    @Query("SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj")
    Optional<PessoaJuridica> findByCnpj(@Param("cnpj") String cnpj);

    @Query("SELECT p FROM PessoaJuridica p WHERE p.cpfResponsavel = :cpfResponsavel")
    List<PessoaJuridica> findByCpfResponsavel(@Param("cpfResponsavel") String cpfResponsavel);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PessoaFisica p WHERE p.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PessoaJuridica p WHERE p.cnpj = :cnpj")
    boolean existsByCnpj(@Param("cnpj") String cnpj);
}
