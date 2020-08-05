package com.todoList.todoList.service;

import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class TodoServiceTest {
    @Test
    void should_return_todos_when_find_all_given_no_paramater(){
        //given
        TodoRepository mock = Mockito.mock(TodoRepository.class);
        when(mock.findAll()).thenReturn(
                Arrays.asList(
                        new Todo(1, "学习1",false),
                        new Todo(2, "学习2",false)
                )
        );
        TodoService todoService = new TodoService(mock);
        //when
        List<Todo> todos = todoService.findAll();
        //then
        assertEquals(2, todos.size());
    }

    @Test
    void should_change_status_when_update_status_given_id(){
        //given
        TodoRepository mock = Mockito.mock(TodoRepository.class);
        when(mock.updateStatus(1)).thenReturn(
                        new Todo(1, "学习1",false)
        );
        TodoService todoService = new TodoService(mock);
        Todo todo=new Todo(1, "学习1",true);
        //when
        Todo updateTodo = todoService.updateStatus(todo.getId());
        //then
        assertEquals(false, updateTodo.getStatus());
    }

    @Test
    void should_return_todo_when_add_given_new_todo(){
        //given
        TodoRepository mock = Mockito.mock(TodoRepository.class);
        Todo todo=new Todo(1, "学习1",true);
        when(mock.addTodo(todo)).thenReturn(
                todo
        );
        TodoService todoService = new TodoService(mock);
        //when
        Todo addedTodo = todoService.addTodo(todo);
        //then
        assertEquals(todo.getId(), addedTodo.getId());
    }

    @Test
    void should_return_success_message_when_delete_todo_given_id(){
        //given
        TodoRepository mock = Mockito.mock(TodoRepository.class);
        Todo todo=new Todo(1, "学习1",true);
        given(mock.findById(todo.getId())).willReturn(
                todo
        );
        TodoService todoService = new TodoService(mock);
        //when
        String message=todoService.deleteTodo(todo.getId());
        //then
        assertEquals("delete success!", message);
    }

}
