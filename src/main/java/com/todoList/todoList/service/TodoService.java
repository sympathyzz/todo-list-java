package com.todoList.todoList.service;

import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.exception.NoSuchDataException;
import com.todoList.todoList.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo updateStatus(Integer id) {
        Todo todo = findById(id);
        if (todo.getStatus()!=null){
            Boolean status=todo.getStatus();
            todo.setStatus(!status);
            todoRepository.save(todo);
            return todo;
        }
        return null;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Boolean deleteTodo(Integer id) {
        Todo todo = findById(id);
        todoRepository.delete(todo);
        return true;
    }

    public Todo findById(Integer id) {
        Optional<Todo> todo=todoRepository.findById(id);
        if(!todo.isPresent()){
            throw new NoSuchDataException();
        }
        return todo.get();
    }

    public List<Todo> findUndoList() {
        return findAll().stream().filter(todo -> todo.getStatus()==false).collect(Collectors.toList());
    }
}
