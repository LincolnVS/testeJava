package com.lincoln.app.controller;

import com.lincoln.app.dto.ProjetoDTO;
import com.lincoln.app.model.Projeto;
import com.lincoln.app.service.ProjetoService;
import com.lincoln.app.repository.ProjetoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    private final ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity getTodosProjetos() {
        return ResponseEntity.ok(this.projetoService.buscarTodosProjetos());
    }


    @GetMapping("/{id}")
    public ResponseEntity getProjeto(@PathVariable("id") Long id) {
        Optional<Projeto> projeto = this.projetoService.buscarProjeto(id);
        if (projeto != null) {
            return ResponseEntity.ok(projeto);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser encontrado");
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity addProjeto(@RequestBody @Valid ProjetoDTO projetoDTO) {

        Projeto projeto = new Projeto(projetoDTO);
        if (projeto != null && this.projetoService.MembrosValidos(projeto.getMembros())) {
            Projeto saved_projeto = this.projetoService.adicionarProjeto(projeto);
            if (saved_projeto != null) {
                return ResponseEntity.ok(saved_projeto);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser adicionado!");
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity updateProjeto(@RequestBody @Valid ProjetoDTO projetoDTO) {

        Long id = projetoDTO.id().isPresent() ? projetoDTO.id().get() : null;

        if (!this.projetoService.existeProjetoCadastrado(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser encontrado");
        }

        Projeto projeto = new Projeto(projetoDTO);
        if (!this.projetoService.MembrosValidos(projeto.getMembros())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser atualizado porque pessoas não estão cadastradas!");
        }

        try {
            projeto.setId(id);
            this.projetoService.atualizarProjeto(projeto);
            return ResponseEntity.ok(projeto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser alterado");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProjeto(@PathVariable("id") Long id) {
        Optional<Projeto> projeto = this.projetoService.buscarProjeto(id);

        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser encontrado");
        }

        if (!this.projetoService.podeDeletarProjeto(projeto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Projeto não pode ser deletado");
        }

        this.projetoService.deletarProjeto(id);
        return ResponseEntity.ok("Projeto deletado");
    }


}

