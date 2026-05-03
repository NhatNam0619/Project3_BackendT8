package com.devon.building.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/home");
        return mav;
}
    @GetMapping({"/admin", "/admin/"})
    public String redirectAdmin() {
        return "redirect:/admin/home";
    }

    @GetMapping({"/login", "/login/"})
    public String redirectLogin() {
        return "redirect:/admin/login";
    }
}
