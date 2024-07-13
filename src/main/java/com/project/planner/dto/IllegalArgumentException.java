package com.project.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class IllegalArgumentException {
    private int code;
    private String message;
}
