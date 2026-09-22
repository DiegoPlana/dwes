package daw.dpm.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String greeting(
            @RequestParam(value = "username", required = false, defaultValue = "Invitado 1") String username,
            @RequestParam(value = "username2", required = false, defaultValue = "Invitado 2") String username2,
            Model model) {

        // Se formatea el mensaje en una sola línea para evitar problemas de renderizado en HTML
        String mensajeFormateado = String.format("¡Hola %s y %s!", username, username2);

        model.addAttribute("message", mensajeFormateado);

        return "hello";
    }
}
