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
public class FormConsultarController {

    @GetMapping("/pagConsultar")
    public String mostrarModalFormulario(Model model) {
        Formulario formulario = new Formulario();
        model.addAttribute("mostrarBotao", false);
        model.addAttribute("objConsultar", formulario);
        return "pagConsultar";

    }

    // botao
    @PostMapping("/ConsultarBD")
    public String PesquisarBD(@Validated @ModelAttribute("objConsultar") Formulario formulario,
            BindingResult bindingResult, Model model) {

        Formulario novoFormulario = SystemController.DB_Consultar(formulario.getNome());
        if (novoFormulario == null) {
            model.addAttribute("mostrarBotao", false);
            model.addAttribute("mensagem", "Projeto não encontrado");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objConsultar", formulario);
            return "pagConsultar";
        }

        // carregar o formulário com os dados do BD
        model.addAttribute("objConsultar", novoFormulario);
        // Adicionar mensagem de sucesso e flag ao Model
        model.addAttribute("mostrarBotao", true);
        model.addAttribute("mensagem", "Projeto encontrado com sucesso!");
        model.addAttribute("mensagemType", "success");

        return "pagConsultar";
    }
}
