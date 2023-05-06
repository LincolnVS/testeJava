package com.lincoln.app.model;

import com.lincoln.app.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(length = 14)
    private String cpf;

    private Boolean funcionario;


    public Pessoa(PessoaDTO pessoaDTO) {
        this.nome = pessoaDTO.nome();
        this.dataNascimento = pessoaDTO.dataNascimento();
        this.cpf = pessoaDTO.cpf();
        this.funcionario = pessoaDTO.funcionario();
    }

}