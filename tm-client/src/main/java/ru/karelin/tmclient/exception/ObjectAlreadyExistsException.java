package ru.karelin.tmclient.exception;

public class ObjectAlreadyExistsException extends IllegalArgumentException {
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
