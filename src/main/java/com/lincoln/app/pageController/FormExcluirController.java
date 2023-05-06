package com.lincoln.app.pageController;

import com.lincoln.app.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormExcluirController {

    @GetMapping("/pagExcluir")
    public String mostrarModalFormulario(Model model) {
        Formulario formulario = new Formulario();
        model.addAttribute("mostrarBotao", false);
        model.addAttribute("objExcluir", formulario);
        return "pagExcluir";

    }

    // botao para pesquisar
    @PostMapping("/ExcluirBD")
    public String PesquisarBD(@Validated @ModelAttribute("objExcluir") Formulario formulario,
            BindingResult bindingResult, Model model) {

        Formulario novoFormulario = SystemController.DB_Consultar(formulario.getNome());
        if (novoFormulario == null) {
            model.addAttribute("mostrarBotao", false);
            model.addAttribute("mensagem", "Formulário não encontrado");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objExcluir", formulario);
            return "pagExcluir";
        }

        // carregar o formulário com os dados do BD
        model.addAttribute("objExcluir", novoFormulario);
        // Adicionar mensagem de sucesso e flag ao Model
        model.addAttribute("mostrarBotao", true);
        model.addAttribute("mensagem", "Formulário encontrado com sucesso!");
        model.addAttribute("mensagemType", "success");

        return "pagExcluir";
    }

    @PostMapping("/Excluir")
    public String processarFormulario(@Validated @ModelAttribute("objExcluir") Formulario formulario,
            BindingResult bindingResult, Model model) {

        if (!SystemController.DB_Excluir(formulario)) {
            model.addAttribute("mensagem", "Não foi possível excluir o Projeto");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objExcluir", formulario);
            return "pagExcluir";
        }

        // Limpar o formulário para a próximo inclusão
        Formulario novoFormulario = new Formulario();
        model.addAttribute("objExcluir", novoFormulario);

        // Adicionar mensagem de sucesso e flag ao Model
        model.addAttribute("mostrarBotao", false);
        model.addAttribute("mensagem", "Projeto excluido com sucesso!");
        model.addAttribute("mensagemType", "success");

        return "pagExcluir";
    }

}
