package com.brainSocket.models;

import org.json.JSONObject;

public class ChatMessage {

	private String content ;
	private String peerFBid ;
	private String peerName ;
	public String getPeerName() {
		return peerName;
	}

	public void setPeerName(String peerName) {
		this.peerName = peerName;
	}
	private boolean isFromMe ;
	private String date ;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ChatMessage() {
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPeerFBid() {
		return peerFBid;
	}
	public void setPeerFBid(String peerFBid) {
		this.peerFBid = peerFBid;
	}
	public boolean isFromMe() {
		return isFromMe;
	}
	public void setFromMe(boolean isFromMe) {
		this.isFromMe = isFromMe;
	}
	
	
	
}
