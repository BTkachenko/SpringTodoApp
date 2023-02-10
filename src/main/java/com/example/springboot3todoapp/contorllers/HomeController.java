package com.example.springboot3todoapp.contorllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot3todoapp.services.TodoItemService;

@Controller
public class HomeController {
    @Autowired
    private TodoItemService todoItemService;
    

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("todoItems",todoItemService.getAll());
        return model;
    }

}
