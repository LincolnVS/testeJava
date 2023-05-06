package com.lincoln.app.service;

import com.lincoln.app.model.Pessoa;
import com.lincoln.app.model.Projeto;
import com.lincoln.app.repository.ProjetoRepository;
import com.lincoln.app.service.PessoaService;
import com.lincoln.app.enumeration.StatusProjeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private PessoaService pessoaService;

    public List<Projeto> buscarTodosProjetos() {
        return this.projetoRepository.findAll();
    }

    public Optional<Projeto> buscarProjeto(Long id) {
        return this.projetoRepository.findById(id);
    }

    public Projeto adicionarProjeto(Projeto projeto) {
        return this.projetoRepository.save(projeto);
    }

    public boolean existeProjetoCadastrado(Long id) {
        return this.projetoRepository.existsById(id);
    }

    public Projeto atualizarProjeto(Projeto projeto) {
        this.projetoRepository.save(projeto);
        return projeto;
    }

    public void deletarProjeto(Long id) {
        this.projetoRepository.deleteById(id);
    }


    public boolean podeDeletarProjeto(Optional<Projeto> projeto) {
        if (projeto.get().getStatus().equals(StatusProjeto.emAndamento) ||
                projeto.get().getStatus().equals(StatusProjeto.iniciado) ||
                projeto.get().getStatus().equals(StatusProjeto.encerrado)) {
            return false;
        }
        return true;
    }

    public boolean MembrosValidos(List<Pessoa> membros) {
        if (membros == null) {
            return true;
        }
        for (Pessoa pessoa : membros) {
            if (pessoa == null) {
                continue;
            }
            if (!this.pessoaService.existePessoaCadastrada(pessoa.getId())) {
                return false;
            }
        }
        return true;
    }
}
