package com.brainSocket.models;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class AbstractModel
{
	protected Dictionary<Integer, String> errors=new Hashtable<Integer, String>();
	protected static int errorIndex=200;
	
	//protected boolean isVaild=true;
	//abstract T createModel(String jsonData);
	public boolean isVaild()
	{
		if(errorIndex>0)
			return true;
		return false;
	}
	public String getErrorString()
	{
		return errors.get(errorIndex);
	}
}
