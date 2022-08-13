package com.app.back.domain.model.exception;

import com.app.back.domain.model.exception.message.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TechnicalException extends Exception{

    private final ErrorMessage errorMessage;

    public TechnicalException(Throwable cause, ErrorMessage errorMessage){
        super(cause);
        this.errorMessage = errorMessage;
    }
}
