package com.todoList.todoList.service;

import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
}
