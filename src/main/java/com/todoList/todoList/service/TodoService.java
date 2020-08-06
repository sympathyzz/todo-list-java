package com.todoList.todoList.service;

import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> updateStatus(Integer id) {
        Optional<Todo> todo = findById(id);
        if(!todo.isPresent()){
        }
        if (todo.get().getStatus()!=null){
            Boolean status=todo.get().getStatus();
            todo.get().setStatus(!status);
            return todo;

        }
        return null;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Boolean deleteTodo(Integer id) throws Exception {
        Optional<Todo> todo = todoRepository.findById(id);
        if (!todo.isPresent()){
            throw new Exception();
        }
        todo.ifPresent(todoRepository::delete);
        return true;
    }

    public Optional<Todo> findById(Integer id) {
        return todoRepository.findById(id);
    }
}
