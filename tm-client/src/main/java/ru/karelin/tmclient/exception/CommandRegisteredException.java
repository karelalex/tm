package ru.karelin.tmclient.exception;

public class CommandRegisteredException extends ObjectAlreadyExistsException{
    public CommandRegisteredException(String message) {
        super(message);
    }
}
