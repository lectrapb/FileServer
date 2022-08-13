package com.app.back.domain.model.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    TRANSACTION_FIND_EMAIL_DUPLICATE("Email duplicado");
    private String message;
}
