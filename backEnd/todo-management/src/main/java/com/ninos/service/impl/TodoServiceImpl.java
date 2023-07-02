package com.ninos.service.impl;

import com.ninos.dto.TodoDTO;
import com.ninos.entity.Todo;
import com.ninos.exception.ResourceNotFoundException;
import com.ninos.mapper.TodoMapper;
import com.ninos.repositroy.TodoRepository;
import com.ninos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;


    @Override
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = TodoMapper.mapToTodo(todoDTO);
        Todo savedTodo = todoRepository.save(todo);

        return TodoMapper.mapToDTO(savedTodo);
    }


    @Override
    public TodoDTO getTodoById(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));
        return TodoMapper.mapToDTO(todo);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> TodoMapper.mapToDTO(todo)).collect(Collectors.toList());
    }

    @Override
    public TodoDTO updateTodo(Long todoId, TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        todoRepository.save(todo);

        return TodoMapper.mapToDTO(todo);
    }

    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));
        todoRepository.deleteById(todoId);
    }


    @Override
    public TodoDTO completeTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo = todoRepository.save(todo);
        return TodoMapper.mapToDTO(updateTodo);
    }

    @Override
    public TodoDTO inCompleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        todo.setCompleted(Boolean.FALSE);
        Todo updateTodo = todoRepository.save(todo);
        return TodoMapper.mapToDTO(updateTodo);
    }

}
