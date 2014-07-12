package com.brainSocket.models;

public enum FilterType 
{
	All(0) , facebookFriends(1) , CustomList(2);
	private final int value;
    private FilterType(int value) 
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
}
