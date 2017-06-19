package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by violet on 6/19/17.
 */
@Controller
public class PersonalController {
    @GetMapping("/resume")
    public String resume(){
        return "resume";
    }

    @GetMapping("/portfolio")
    public String portfolio(){
        return "portfolio";
    }

    @GetMapping("/roll-dice")
    public String getDiceNumber(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model){
        int random = (int)(Math.random() * 6) + 1;
        if (random == n) {
            model.addAttribute("message", "You are right!");
        } else {
            model.addAttribute("message", "Try again!");
        }

        return "roll-dice";
    }


}
