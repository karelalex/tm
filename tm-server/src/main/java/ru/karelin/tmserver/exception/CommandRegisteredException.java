package ru.karelin.tmserver.exception;

public class CommandRegisteredException extends ObjectAlreadyExistsException{
    public CommandRegisteredException(String message) {
        super(message);
    }
}
