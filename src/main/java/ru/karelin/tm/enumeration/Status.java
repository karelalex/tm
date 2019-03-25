package ru.karelin.tm.enumeration;

public enum Status {
    
    PLANNED("planned"), PROCESS("in progress"), READY("ready");
    
    private String displayName;

    Status(String displayName){
        this.displayName=displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
