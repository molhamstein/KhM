package com.brainSocket.models;

public enum UserType 
{
	USER(0) , PASSENGER(1) , DRIVER(2);
	private final int value;
    private UserType(int value) 
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
}
