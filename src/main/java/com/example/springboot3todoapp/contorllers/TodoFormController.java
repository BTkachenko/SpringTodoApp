package com.example.springboot3todoapp.contorllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot3todoapp.models.TodoItem;
import com.example.springboot3todoapp.services.TodoItemService;

import jakarta.validation.Valid;

@Controller
public class TodoFormController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem)
    {
        return "new-todo-item";
    }


    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        
        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        todoItemService.save(todoItem);
        return "redirect:/";
        
    }



    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id,Model model){
        TodoItem todoItem = todoItemService.getById(id).orElseThrow(()->new IllegalArgumentException("TodoItem id : "+id+" nor found"));

        /* 
        Optional<TodoItem> todoItem =  todoItemService.getById(id);
        if(todoItem.isPresent()){
            todoItem.get();
        }
        */


        todoItemService.delete(todoItem);
        return "redirect:/";
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,Model model){
        TodoItem todoItem = todoItemService.getById(id).orElseThrow(()->new IllegalArgumentException("TodoItem id : "+id+" nor found"));
        model.addAttribute("todo", todoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id,@Valid TodoItem todoItem,BindingResult result, Model model)
    {
        TodoItem item = todoItemService.getById(id).orElseThrow(()->new IllegalArgumentException("TodoItem id : "+id+" nor found"));
        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());

        todoItemService.save(item);

        return "redirect:/";
    }

}
