package com.lincoln.app.model;

import java.time.LocalDate;

import org.springframework.format.annotation.*;

public class Formulario {
    public Formulario() {
        setDataInicio(LocalDate.now()); // Define a data atual como valor inicial
        setPrevisaoTermino(LocalDate.now()); // Define a data atual como valor inicial
        this.orcamentoTotal = 0.0;
        // this.nome = "nome";
        // this.gerenteResponsavel = "gerente Responsável";
        // this.orcamentoTotal = 0f;
        // this.descricaoStatus = "descrição ou Status";
    }

    public Formulario(String nome, LocalDate dataInicio, String gerenteResponsavel, LocalDate previsaoTermino,
                      LocalDate dataRealTermino, double orcamentoTotal, String descricaoStatus) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.gerenteResponsavel = gerenteResponsavel;
        this.PrevisaoTermino = previsaoTermino;
        this.dataRealTermino = dataRealTermino;
        this.orcamentoTotal = orcamentoTotal;
        this.descricaoStatus = descricaoStatus;
    }

    private String nome;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    private String gerenteResponsavel;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate PrevisaoTermino;

    private LocalDate dataRealTermino;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double orcamentoTotal;

    private String descricaoStatus;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(String gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public LocalDate getPrevisaoTermino() {
        return PrevisaoTermino;
    }

    public void setPrevisaoTermino(LocalDate previsaoTermino) {
        PrevisaoTermino = previsaoTermino;
    }

    public LocalDate getDataRealTermino() {
        return dataRealTermino;
    }

    public void setDataRealTermino(LocalDate dataRealTermino) {
        this.dataRealTermino = dataRealTermino;
    }

    public double getOrcamentoTotal() {
        return orcamentoTotal;
    }

    public void setOrcamentoTotal(double orcamentoTotal) {
        this.orcamentoTotal = orcamentoTotal;
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public void setDescricaoStatus(String descricaoStatus) {
        this.descricaoStatus = descricaoStatus;
    }

}
