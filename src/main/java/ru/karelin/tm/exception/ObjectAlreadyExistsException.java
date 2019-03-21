package ru.karelin.tm.exception;

public class ObjectAlreadyExistsException extends IllegalArgumentException {
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
