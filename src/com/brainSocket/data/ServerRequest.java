package com.brainSocket.data;

public class ServerRequest {
	Notifiable callBack ;
	int priorety ;
	String url ;
	public ServerRequest(Notifiable callBack , String url )  {
		this.callBack = callBack ;
		this.url = url ;
	}
	public void setPriorety(int priorety) {
		this.priorety = priorety;
	}
	public int getPriorety() {
		return priorety;
	}

}
