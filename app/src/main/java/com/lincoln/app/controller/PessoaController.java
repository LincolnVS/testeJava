package com.lincoln.app.controller;

import com.lincoln.app.dto.PessoaDTO;
import com.lincoln.app.model.Pessoa;
import com.lincoln.app.repository.PessoaRepository;
import com.lincoln.app.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity getTodasPessoas() {
        return ResponseEntity.ok(this.pessoaService.buscarTodasPessoas());
    }


    @GetMapping("/{id}")
    public ResponseEntity getPessoa(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = this.pessoaService.buscarPessoa(id);
        if (pessoa != null && !pessoa.isEmpty()) {
            return ResponseEntity.ok(pessoa);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não pode ser encontrado");
    }


    @PostMapping(value = {"", "/"})
    public ResponseEntity addPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {

        Pessoa Pessoa = new Pessoa(pessoaDTO);
        if (Pessoa != null) {
            Pessoa saved_pessoa = this.pessoaService.adicionarPessoa(Pessoa);
            if (saved_pessoa != null) {
                return ResponseEntity.ok(saved_pessoa);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não pode ser adicionada!");
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity updatePessoo(@RequestBody @Valid PessoaDTO pessoaDTO) {

        Long id = pessoaDTO.id().isPresent() ? pessoaDTO.id().get() : null;

        if (!this.pessoaService.existePessoaCadastrada(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não pode ser encontrada");
        }

        Pessoa pessoa = new Pessoa(pessoaDTO);
        try {
            pessoa.setId(id);
            this.pessoaService.atualizarPessoa(pessoa);
            return ResponseEntity.ok(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não pode ser alterado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePessoa(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = this.pessoaService.buscarPessoa(id);

        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não pode ser encontrada!");
        }

        this.pessoaService.deletarPessoa(id);
        return ResponseEntity.ok("Pessoa deletada com sucesso!");
    }
}