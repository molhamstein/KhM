package com.brainSocket.enums;

public enum UserSex {
	female(0) , male(1),both(2);
	private final int value;
    private UserSex(int value) 
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
}
