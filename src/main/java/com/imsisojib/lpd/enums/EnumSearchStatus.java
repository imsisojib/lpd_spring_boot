package com.imsisojib.lpd.enums;

public enum EnumSearchStatus {
    NOT_FOUND (1),
    FOUND (2),
    FOUND_BUT_LIMIT (3);

    private final int value;

    EnumSearchStatus(final int newValue){
        value = newValue;
    }

    public  int getValue(){
        return  value;
    }
}
