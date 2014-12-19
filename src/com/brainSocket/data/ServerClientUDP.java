package com.brainSocket.data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import android.util.Log;

public class ServerClientUDP extends AsyncTask<String, String ,String> {

	private Notifiable caller ;
	private String url ;
	private int port ;
	private String serverAddress ;
	
	public ServerClientUDP(Notifiable caller, int port, String address) {
		this.caller =  caller;
		this.port = port ;
		this.serverAddress = address ;
	}

	/**
	 * this function requests the data from the server over the Internet in a background process
	 * so the use UI wont freeze 
	 */
	@Override
	protected String doInBackground(String... url) {
		String resp ;
		try{
			this.url = url[0] ;
			resp = readServerResponse( url[0]) ;
			
		} catch (Exception e) {
		      e.printStackTrace();
		      resp = "error";
		} 
		
		return resp;
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		
		super.onPostExecute(result);
		if(caller != null){
			if ( result.equals("error") ){
				caller.onDataLoadFail(url) ;
			}else{
				caller.onDataReady(result);
			}
		}

		
	}
	

	public String readServerResponse(String url) throws ClientProtocolException, IOException {
		 StringBuilder builder = new StringBuilder();
		    
		    //InetAddress serveraddr2 = InetAddress.getByName("www.google.com");
		 
		    InetAddress serveraddr = InetAddress.getByName(serverAddress); //getByAddress( serverAddress );
		    String text;
		    byte[] message = new byte[1500];
		    
		    DatagramPacket res = new DatagramPacket(message,message.length);
		    DatagramPacket send = new DatagramPacket(url.getBytes(),url.getBytes().length,serveraddr,port);		    
		    DatagramSocket s = new DatagramSocket();
		    s.setSoTimeout(1500);
		    
		    s.send(send);
		    s.receive(res);
		    
		    text = new String(message, 0, res.getLength(),"UTF-8");
		    Log.d("Udp client","message:" + text);
		    s.close();
		    //builder.append(message);
		      
		    //String built = builder.toString();
		    return text;
	  }
	
	
}
