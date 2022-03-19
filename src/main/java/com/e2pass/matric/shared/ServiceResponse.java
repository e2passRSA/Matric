package com.e2pass.matric.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ServiceResponse<T> {
    private Boolean isSuccess;
    private T data;
    private LocalDateTime timeStamp;
    private List<String> errors;

    public ServiceResponse(Boolean isSuccess, LocalDateTime timeStamp, List<String> errors) {
        this.isSuccess = isSuccess;
        this.timeStamp = timeStamp;
        this.errors = errors;
    }
}
