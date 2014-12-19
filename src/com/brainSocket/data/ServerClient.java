package com.brainSocket.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;

public class ServerClient extends AsyncTask<String, String ,String> {

	Notifiable caller ;
	String url ;
	
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
	    
	    HttpParams httpParameters = new BasicHttpParams();	   
	    int timeoutConnection = 3500;
	    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	    int timeoutSocket = 5000;
	    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	    
	    HttpClient client = new DefaultHttpClient(httpParameters);
	    HttpGet httpGet = new HttpGet(url);
	    
	    httpGet.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    httpGet.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    
	    //httpGet.setDateHeader("Expires", 0);
	    
	    
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
