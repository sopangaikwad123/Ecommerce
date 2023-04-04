package com.Nia.electronic.store.dtos;

import com.Nia.electronic.store.controllers.UserController;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {
    private static Logger logger = LoggerFactory.getLogger( ApiResponseMessage.class);
    private String message;

    private boolean success;

    private HttpStatus status;

}
