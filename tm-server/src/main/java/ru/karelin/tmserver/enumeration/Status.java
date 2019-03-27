package ru.karelin.tmserver.enumeration;

public enum Status {
    
    PLANNED("planned"), PROCESS("in progress"), READY("ready");
    
    private String displayName;
    private int sortIndex;

    Status(String displayName){
        this.displayName=displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
