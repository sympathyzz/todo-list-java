package com.todoList.todoList.service;

import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.repository.TodoRepository;

import java.util.List;

public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo updateStatus(Integer id) {
        return todoRepository.updateStatus(id);
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.addTodo(todo);
    }

    public String deleteTodo(Integer id) {
        Todo todo = todoRepository.findById(id);
        return "delete success!";
    }

    public Todo findById(Integer id) {
        return todoRepository.findById(id);
    }
}
