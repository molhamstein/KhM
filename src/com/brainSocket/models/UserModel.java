package com.brainSocket.models;

import android.graphics.PointF;

public class UserModel 
{
	private int userID;
	private String fullName;
	private float price;
	private PointF position;
	
	public UserModel(int userID,String fullName,float price,PointF position)
	{
		this.userID=userID;
		this.fullName=fullName;
		this.price=price;
		this.position=position;
	}
	
	public int getUserID()
	{
		return this.userID;
	}
	public String getFullName()
	{
		return this.fullName;
	}
	public float getPrice()
	{
		return this.price;
	}
	public PointF getPosition()
	{
		return this.position;
	}
}
