package com.ninos.mapper;

import com.ninos.dto.TodoDTO;
import com.ninos.entity.Todo;
import org.springframework.beans.BeanUtils;

public class TodoMapper {


    public static TodoDTO mapToDTO(Todo todo){
        TodoDTO todoDTO = new TodoDTO();
        BeanUtils.copyProperties(todo, todoDTO);
        return todoDTO;
    }


    public static Todo mapToTodo(TodoDTO todoDTO){
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoDTO, todo);
        return todo;
    }


}
