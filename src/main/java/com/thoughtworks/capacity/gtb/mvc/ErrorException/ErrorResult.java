package com.thoughtworks.capacity.gtb.mvc.ErrorException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private int code;
    private String message;
}
