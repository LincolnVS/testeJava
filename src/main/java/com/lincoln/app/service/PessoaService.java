package com.lincoln.app.service;

import com.lincoln.app.model.Pessoa;
import com.lincoln.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodasPessoas() {
        return this.pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoa(Long id) {
        return this.pessoaRepository.findById(id);
    }

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    public boolean existePessoaCadastrada(Long id) {
        return this.pessoaRepository.existsById(id);
    }

    public Pessoa atualizarPessoa(Pessoa pessoa) {
        this.pessoaRepository.save(pessoa);
        return pessoa;
    }

    public void deletarPessoa(Long id) {
        this.pessoaRepository.deleteById(id);
    }


}
