package com.lincoln.app.pageController;

import com.lincoln.app.model.Formulario;
import com.lincoln.app.model.Projeto;
import com.lincoln.app.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class SystemController {

    public static boolean DB_Inserir(Formulario formulario) {
        return true;

    }

    public static Formulario DB_Consultar(String nome) {
        Formulario form = new Formulario("Projeto TESTE", null, "Lincoln", null, null, 10000.00, "Descrição  do pj teste");

        return form;
    }

    public static boolean DB_Alterar(Formulario formulario) {
        return true;
    }

    public static boolean DB_Excluir(Formulario formulario) {
        return true;
    }

}
