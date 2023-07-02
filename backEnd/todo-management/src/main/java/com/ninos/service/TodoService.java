package com.ninos.service;

import com.ninos.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    TodoDTO createTodo(TodoDTO todoDTO);
    TodoDTO getTodoById(Long todoId);
    List<TodoDTO> getAllTodos();
    TodoDTO updateTodo(Long todoId, TodoDTO todoDTO);
    void deleteTodo(Long id);

    TodoDTO completeTodo(Long todoId);
    TodoDTO inCompleteTodo(Long todoId);



}
