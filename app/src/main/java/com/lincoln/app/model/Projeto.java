package com.lincoln.app.model;

import com.lincoln.app.enumeration.RiscoProjeto;
import com.lincoln.app.enumeration.StatusProjeto;
import com.lincoln.app.dto.ProjetoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_previsao_fim")
    @Temporal(TemporalType.DATE)
    private Date dataPrevisaoFim;

    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Column(length = 5000)
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private StatusProjeto status;

    private Float orcamento;

    @Enumerated(EnumType.ORDINAL)
    private RiscoProjeto risco;

    @ManyToMany
    private List<Pessoa> membros;

    public Projeto(ProjetoDTO projetoDTO) {
        this.nome = projetoDTO.nome();
        this.dataInicio = projetoDTO.dataInicio();
        this.dataPrevisaoFim = projetoDTO.dataPrevisaoFim();
        this.dataFim = projetoDTO.dataFim();
        this.orcamento = projetoDTO.orcamento();
        this.descricao = projetoDTO.descricao();
        this.status = projetoDTO.status();
        this.risco = projetoDTO.risco();
        this.membros = projetoDTO.membros();
    }

}