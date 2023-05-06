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
public class FormInserirController {

    @GetMapping("/pagInserir")
    public String mostrarFormulario(Model model) {
        Formulario formulario = new Formulario();
        model.addAttribute("objInserir", formulario);
        return "pagInserir";
    }

    @PostMapping("/Enviar")
    public String processarFormulario(@Validated @ModelAttribute("objInserir") Formulario formulario,
            BindingResult bindingResult, Model model) {
        // Verifica se há erros de validação
        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagem", "Erro de Validação");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objInserir", formulario);
            return "pagInserir";
        }

        // Verifica se a Data Real de inicio é menor que a Previsão de Término
        if (formulario.getPrevisaoTermino() != null && formulario.getDataInicio() != null
                && formulario.getDataInicio().isAfter(formulario.getPrevisaoTermino())) {
            bindingResult.rejectValue("dataInicio", "dataInicio.invalid", "");
            bindingResult.rejectValue("previsaoTermino", "previsaoTermino.invalid", "");

            // adicionar o objeto Formulario à Model
            model.addAttribute("mensagem", "A Previsão de Término deve ser igual ou posterior à Data Real de inicio.");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objInserir", formulario);
            return "pagInserir";
        }

        // Processa as informações do formulário


        if (!SystemController.DB_Inserir(formulario)) {
            model.addAttribute("mensagem", "O projeto já existe");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objInserir", formulario);
            return "pagInserir";
        }

        // Limpar o formulário para a próximo inclusão
        Formulario novoFormulario = new Formulario();
        model.addAttribute("objInserir", novoFormulario);

        // Adicionar mensagem de sucesso e flag ao Model
        model.addAttribute("mensagem", "Projeto inserido com sucesso!");
        model.addAttribute("mensagemType", "success");

        return "pagInserir";
    }

}
