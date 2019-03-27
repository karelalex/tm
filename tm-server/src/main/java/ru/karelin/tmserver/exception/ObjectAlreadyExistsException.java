package ru.karelin.tmserver.exception;

public class ObjectAlreadyExistsException extends IllegalArgumentException {
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
