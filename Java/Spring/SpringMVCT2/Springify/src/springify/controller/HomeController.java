package springify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller( "homeController" )
public class HomeController {

    @RequestMapping( "/" )
    public String showHome(Model model) {

        model.addAttribute("title", "Homepage");

        return "home";
    }
}
