package com.ninos.controller;

import com.ninos.dto.TodoDTO;
import com.ninos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RequestMapping("/api/v1/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO){
        TodoDTO todo = todoService.createTodo(todoDTO);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id){
        TodoDTO todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
        List<TodoDTO> allTodos = todoService.getAllTodos();
        return ResponseEntity.ok(allTodos);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> update(@PathVariable Long id, @RequestBody TodoDTO todoDTO){
        return ResponseEntity.ok(todoService.updateTodo(id, todoDTO));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("todo deleted successfully");
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable Long id){
        TodoDTO todo = todoService.completeTodo(id);
        return ResponseEntity.ok(todo);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable Long id){
        TodoDTO todo = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todo);
    }


}
