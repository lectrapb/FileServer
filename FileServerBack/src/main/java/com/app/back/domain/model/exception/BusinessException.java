package com.app.back.domain.model.exception;

import com.app.back.domain.model.exception.message.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends Exception{
    private final ErrorMessage errorMessage;
}
