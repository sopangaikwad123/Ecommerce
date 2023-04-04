package com.Nia.electronic.store.exceptions;

import lombok.Builder;

public class ResourceNotFoundException extends RuntimeException{

    @Builder
    public ResourceNotFoundException(){
        super("Resource not found !!");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }

}
