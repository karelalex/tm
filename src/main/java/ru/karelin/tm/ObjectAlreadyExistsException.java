package ru.karelin.tm;

public class ObjectAlreadyExistsException extends IllegalArgumentException {
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
