package com.brainSocket.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.ImageView;

public class ServerDataSrc {
	
	static final String URL = "https://api.twitter.com/1/statuses/user_timeline/vogella.json";
	
	static final String baseServiceURL  ="http://malle.somee.com/ClientService/PublisherService.svc/";
	
	static final String CATEGORIE_SERVICE = "/ListDynamicCategoryProducts/DynamicCats";
	static final String MAIN_ADDS_SERVICE = "/ListMainAds/Ads";
	static final String CATALOG_SERVICE = "/GetProductDetails/ProductCatalogs";
	static final String SEARCH_PROD_SERVICE = "/ListProductsSearch";
	static final String GALLERY_SERVICE = "/ListProductImages/ProductProfiles";
	static final String REGISTER_SERVICE = "/RegisterNewCustomer";
	static final String STATIC_CATEGORIE_SERVICE = "/ListCategorizedProducts/ProductCatalogs";
	
	
	public static final String NEW_USER = "newUser";
	public static final String USER_ID = "UserID";
	public static final String USER_Name = "LoginName";
	public static final String PW = "Password";
	public static final String ROLE_ID = "RoleID";
	public static final String IS_ACTIVE = "IsActive";
	public static final String USER_TYPE = "UserType";
	

/*
	
	public void getCategorie(Notifiable caller , int catIdentifier) {
		ServerClient client = new ServerClient(caller);
		List<NameValuePair> params  = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("dynamicCategoryID", String.valueOf(catIdentifier) ));
		params.add(new BasicNameValuePair("language", MalleApplication.lang ));
		String serviceURL = baseServiceURL + CATEGORIE_SERVICE ;
		String fullURL = addParamsToUrl(serviceURL, params);
		client.execute(fullURL);	
	}
	
	
	public void getProdCatatlog(Notifiable caller , int id){
		ServerClient client = new ServerClient(caller);
		List<NameValuePair> params  = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("productCatalogID", String.valueOf(id) ));
		params.add(new BasicNameValuePair("language", MalleApplication.lang ));
		String serviceURL = baseServiceURL + CATALOG_SERVICE ;
		String fullURL = addParamsToUrl(serviceURL, params);
		client.execute(fullURL);
	}
	
	protected String addParamsToUrl(String url , List<NameValuePair> params){
		
	    if(!url.endsWith("?"))
	        url += "?";

	    String paramString = URLEncodedUtils.format(params, "utf-8");
	    url += paramString;
	    
	    return url;
	}
	
	
	public void getMAinAdds(Notifiable caller, int count){
		//http://mallestore-001-site1.smarterasp.net/PublisherService.svc/ListMainAds/Ads?topNumber=8
		ServerClient client = new ServerClient(caller);
		List<NameValuePair> params  = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("topNumber", String.valueOf(count) ));
		String serviceURL = baseServiceURL + MAIN_ADDS_SERVICE;
		String fullURL = addParamsToUrl(serviceURL, params);
		client.execute(fullURL);
	}
	
	public void registerUser(Notifiable caller, List<NameValuePair> params){
		
		POSTServerClient client = new POSTServerClient(caller);
		String serviceURL = baseServiceURL + REGISTER_SERVICE ;
		
		try {
			String str = jsonFromValues(params).toString();
			JSONObject valuePairs = new JSONObject(str);
			JSONObject json = new JSONObject();
			json.put("newUser", valuePairs);
			//json.accumulate(, str);
			String finalData  = json.toString();
			String fullURL = serviceURL+'/';
			
			client.execute( new String[] {fullURL,finalData} );
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private JSONObject jsonFromValues (List<NameValuePair> list) throws JSONException{
		
		JSONObject json = new JSONObject();
		for (NameValuePair pair : list) {
			json.accumulate(pair.getName(), pair.getValue());
		}
		
		return json ;
	}
	
	public void loadImage(ImageView caller  ,String url){
		ImageLoader loder = new ImageLoader(caller);
		loder.execute(url);
	}
	
	public void searchProduct ( Notifiable caller, String key ){
		
		ServerClient client = new ServerClient(caller);
		String serviceURL = baseServiceURL + SEARCH_PROD_SERVICE;
		String fullURL = serviceURL +"/"+key +"/"+MalleApplication.lang;
		client.execute(fullURL);
		
	}
	
	public void getGallery(Notifiable caller , int id){
		ServerClient client = new ServerClient(caller);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("productProfile", String.valueOf(id) ));
		String serviceURL = baseServiceURL + GALLERY_SERVICE;
		String fullURL = addParamsToUrl(serviceURL, params);
		client.execute(fullURL);
	}
*/
}
