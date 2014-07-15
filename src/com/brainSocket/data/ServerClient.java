package com.brainSocket.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class ServerClient extends AsyncTask<String, String ,String> {

	Notifiable caller ; 
	
	public ServerClient(Notifiable caller) {
		this.caller =  caller;
	}

	/**
	 * this function requests the data from the server over the Internet in a background process
	 * so the use UI wont freeze 
	 */
	@Override
	protected String doInBackground(String... url) {
		String resp ;
		try{
			resp = readServerResponse( url[0]) ;
			
		} catch (ClientProtocolException e) {
		      e.printStackTrace();
		      resp = "error";
		} catch (IOException e) {
		      e.printStackTrace();
		      resp = "error";
		}
		
		
		return resp;
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		
		super.onPostExecute(result);
		if(caller != null){
			caller.onDataReady(result);
		}

		
	}
	

	public String readServerResponse(String url) throws ClientProtocolException, IOException {
	    StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet(url);
	    
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	      } else {
	    	  
	    	 // failed
	        //Log.e(ParseJSON.class.toString(), "Failed to download file");
	      }
	      
	    String built = builder.toString();
	    return built;
	    //return "{'list':[{'name':'john','age':20}, {'name':'michael','age':25},  {'name':'sara', 'age':23}  ] }" ;
	  }
	
	
}
