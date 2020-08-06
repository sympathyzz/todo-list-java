package com.todoList.todoList.controller;

import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAll(){
        return todoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo){
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTodo(@PathVariable Integer todoId) throws Exception {
        todoService.deleteTodo(todoId);
    }

}
