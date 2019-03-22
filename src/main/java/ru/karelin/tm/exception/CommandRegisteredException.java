package ru.karelin.tm.exception;

public class CommandRegisteredException extends ObjectAlreadyExistsException{
    public CommandRegisteredException(String message) {
        super(message);
    }
}
