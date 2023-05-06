package com.lincoln.app.dto;

import com.lincoln.app.enumeration.StatusProjeto;
import com.lincoln.app.enumeration.RiscoProjeto;
import com.lincoln.app.model.Pessoa;
import com.lincoln.app.model.Projeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public record ProjetoDTO(
        Optional<Long> id,
        @NotBlank
        String nome,
        @NotNull
        Date dataInicio,
//        @NotBlank
//        String gerenteResponsavel,
        @NotNull
        Date dataPrevisaoFim,

        Date dataFim,
        @NotNull
        Float orcamento,
        String descricao,
        StatusProjeto status,
        RiscoProjeto risco,
        List<Pessoa> membros
) {
    public ProjetoDTO(Projeto projeto) {
        this(
                Optional.of(projeto.getId()),
                projeto.getNome(),
                projeto.getDataInicio(),
                projeto.getDataPrevisaoFim(),
                projeto.getDataFim(),
                projeto.getOrcamento(),
                projeto.getDescricao(),
                projeto.getStatus(),
                projeto.getRisco(),
                projeto.getMembros());
    }

}
