package com.ninos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;

}
