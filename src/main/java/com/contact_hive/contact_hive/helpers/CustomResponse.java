package com.contact_hive.contact_hive.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse<T> {
    private int status;
    private String message;
    private boolean success;
    private T data;
}
