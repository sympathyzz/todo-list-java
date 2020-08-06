package com.todoList.todoList.integration;

import com.alibaba.fastjson.JSONObject;
import com.todoList.todoList.entity.Todo;
import com.todoList.todoList.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void init(){
        todoRepository.save(new Todo(null,"学习1",false));
        todoRepository.save(new Todo(null,"学习2",true));
    }

    @AfterEach
    void tearDown(){
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todos_when_find_all_given_no_parameter() throws Exception {
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(2)));
    }

    @Test
    void should_return_todo_when_add_given_todo() throws Exception {
        Todo todo=new Todo(null,"学习3",false);
        String todoJsonString = JSONObject.toJSONString(todo);
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(todoJsonString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value(todo.getContent()))
                .andExpect(jsonPath("$.status").value(todo.getStatus()));
    }

    @Test
    void should_return_202_when_delete_given_todo_id() throws Exception {
        Integer id=todoRepository.findAll().get(0).getId();
        mockMvc.perform(delete("/todos/"+id))
                .andExpect(status().isAccepted());
    }

}
