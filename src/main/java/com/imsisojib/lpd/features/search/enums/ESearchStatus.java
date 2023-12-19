package com.imsisojib.lpd.features.search.enums;

public enum ESearchStatus {
    NOT_FOUND (1),
    FOUND (2),
    FOUND_BUT_LIMIT (3);    //user has limited search access

    private final int value;

    ESearchStatus(final int newValue){
        value = newValue;
    }

    public  int getValue(){
        return  value;
    }
}
