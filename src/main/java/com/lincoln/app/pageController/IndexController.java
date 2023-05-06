package com.lincoln.app.pageController;

import com.lincoln.app.model.Formulario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    // @RequestMapping("*")
    public String index(Model model) {
        Formulario formulario = new Formulario();
        model.addAttribute("objInserir", formulario);
        return "index";
    }

    // @RequestMapping("*")
    // public void handleNotFound() {
    // throw new ResourceNotFoundException("Página não encontrada");
    // }

}