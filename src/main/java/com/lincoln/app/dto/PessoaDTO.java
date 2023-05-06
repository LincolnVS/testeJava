package com.lincoln.app.dto;

import com.lincoln.app.model.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Optional;

public record PessoaDTO(
        Optional<Long> id,
        @NotBlank
        String nome,
        @NotNull
        Date dataNascimento,
        String cpf,
        Boolean funcionario
) {
    public PessoaDTO(Pessoa pessoa) {
        this(
                Optional.of(pessoa.getId()),
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getCpf(),
                pessoa.getFuncionario()
        );
    }

}
