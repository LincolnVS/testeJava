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
public class FormAlterarController {

    @GetMapping("/pagAlterar")
    public String mostrarModalFormulario(Model model) {
        Formulario formulario = new Formulario();
        model.addAttribute("mostrarBotao", false);
        model.addAttribute("objAlterar", formulario);
        return "pagAlterar";

    }

    // botao
    @PostMapping("/PesquisarBD")
    public String PesquisarBD(@Validated @ModelAttribute("objAlterar") Formulario formulario,
            BindingResult bindingResult, Model model) {
        /*
         * @lincoln criar metodo para consultar banco pelo nome do pj
         * retorno :
         * achou retorna o os dados do PJ
         * null se nao achou nada
         */
        Formulario novoFormulario = SystemController.DB_Consultar(formulario.getNome());
        if (novoFormulario == null) {
            model.addAttribute("mostrarBotao", false);
            model.addAttribute("mensagem", "Formulário não encontrado");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objAlterar", formulario);
            return "pagAlterar";
        }

        // carregar o formulário com os dados do BD
        model.addAttribute("objAlterar", novoFormulario);
        // Adicionar mensagem de sucesso e flag ao Model
        model.addAttribute("mostrarBotao", true);
        model.addAttribute("mensagem", "Formulário encontrado com sucesso!");
        model.addAttribute("mensagemType", "success");

        return "pagAlterar";
    }

    @PostMapping("/Alterar")
    public String processarFormulario(@Validated @ModelAttribute("objAlterar") Formulario formulario,
            BindingResult bindingResult, Model model) {
        // Verifica se há erros de validação
        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagem", "Erro de Validação");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objAlterar", formulario);
            return "pagAlterar";
        }

        // Verifica se a Data Real de inicio é menor que a Previsão de Término
        if (formulario.getPrevisaoTermino() != null && formulario.getDataInicio() != null
                && formulario.getDataInicio().isAfter(formulario.getPrevisaoTermino())) {
            bindingResult.rejectValue("dataInicio", "dataInicio.invalid", "");
            bindingResult.rejectValue("previsaoTermino", "previsaoTermino.invalid", "");

            // adicionar o objeto Formulario à Model
            model.addAttribute("mensagem", "A Previsão de Término deve ser igual ou posterior à Data Real de inicio.");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objAlterar", formulario);
            return "pagAlterar";
        }

        if (!SystemController.DB_Alterar(formulario)) {
            model.addAttribute("mensagem", "erro no banco de dados não foi possível alterar");
            model.addAttribute("mensagemType", "error");
            model.addAttribute("objAlterar", formulario);
            return "pagAlterar";
        }

        // Limpar o formulário para a próximo inclusão
        Formulario novoFormulario = new Formulario();
        model.addAttribute("objAlterar", novoFormulario);

        // Adicionar mensagem de sucesso e flag ao Model
        model.addAttribute("mostrarBotao", false);
        model.addAttribute("mensagem", "Projeto Alterado com sucesso!");
        model.addAttribute("mensagemType", "success");

        return "pagAlterar";
    }

}
