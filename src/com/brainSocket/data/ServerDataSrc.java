package com.brainSocket.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.enums.UserSex;
import com.brainSocket.enums.UserType;

import android.graphics.PointF;
import android.widget.ImageView;

public class ServerDataSrc {
	
	static final String URL = "https://api.twitter.com/1/statuses/user_timeline/vogella.json";
	
	static final String baseServiceURL  ="http://brain-socket.com/index.php/users";
	
	static final String REGISTER_SERVICE = "/regsiter";
	static final String CHANGE_USER_STATE_SERVICE="/changeState";
	static final String SET_GOAL_SERVICE="/setGoal";
	static final String REQUEST_FOR_SERVICE="/requestFor";
	static final String RESPONSE_FOR_SERVICE="/responseFor";
	static final String GOAL_ACHIEVED_SERVICE="/goalAchieved";
	static final String RATE_SERVICE="/rate";
	static final String UPDATE_MY_POSITION_SERVICE="/updateMyPosition";
	static final String GET_USERS_AROUND_ME_SERVICE="/getUsersAroundMe";
	static final String GET_NOTIFICATIONS_SERVICE="/getNotifications";
	static final String ADD_VEHICLE_SERVICE="/addVehicle";
	static final String CHANGE_SCOPE_TO_SERVICE="/addVehicle";
	static final String CHANGE_ACCEPTABLE_SEX_SERVICE="/changeAcceptableSex";
	static final String GET_PLACES_STARTS_WITH_SERVICE="/changeAcceptableSex";
	static final String GET_VISIBILITY_AUTHORIZE_USERS_LIST_SERVICE="/changeAcceptableSex";
	
	
	public void getVisibilityAuthorizeUsersList(Notifiable caller)
	{
		String serviceURL = baseServiceURL + GET_VISIBILITY_AUTHORIZE_USERS_LIST_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID();
		doRequest(caller, fullURL);
	}
	public void getPlacesStartsWith(Notifiable caller,String startedWith)
	{
		String serviceURL = baseServiceURL + GET_PLACES_STARTS_WITH_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+startedWith;
		doRequest(caller, fullURL);
	}
	public void changeAcceptableSex(Notifiable caller,UserSex sex)
	{
		String serviceURL = baseServiceURL + CHANGE_ACCEPTABLE_SEX_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+sex.toString();
		doRequest(caller, fullURL);
	}
	public void changeScopeTo(Notifiable caller,int newScopeRadius)
	{
		String serviceURL = baseServiceURL + CHANGE_SCOPE_TO_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+newScopeRadius;
		doRequest(caller, fullURL);
	}
	public void addVehicle(Notifiable caller,int carModelType,int carColor)
	{
		String serviceURL = baseServiceURL + ADD_VEHICLE_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+carModelType+"/"+carColor;
		doRequest(caller, fullURL);
	}
	public void getNotifications(Notifiable caller)
	{
		String serviceURL = baseServiceURL + GET_NOTIFICATIONS_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID();
		doRequest(caller, fullURL);
	}
	public void register(Notifiable caller,String facebookID,String mobileNumber)
	{
		String serviceURL = baseServiceURL + REGISTER_SERVICE ;
		String fullURL = serviceURL+"/"+facebookID+"/"+mobileNumber;
		doRequest(caller, fullURL);
	}
	
	public void changeState(Notifiable caller,UserType userType)
	{
		String serviceURL = baseServiceURL + CHANGE_USER_STATE_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+userType.getValue();
		doRequest(caller, fullURL);
	}
	
	public void setGoal(Notifiable caller,int placeID,PointF position)
	{
		String serviceURL = baseServiceURL + SET_GOAL_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+placeID+"/"+position.x+"/"+position.y;
		doRequest(caller, fullURL);
	}
	public void requestFor(Notifiable caller,int destinationUserID,PointF position)
	{
		String serviceURL = baseServiceURL + REQUEST_FOR_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+destinationUserID+"/"+position.x+"/"+position.y;
		doRequest(caller, fullURL);
	}
	public void responseFor(Notifiable caller,int requestID,boolean hasAgreement,PointF position)
	{
		String serviceURL = baseServiceURL + RESPONSE_FOR_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+requestID+"/"+hasAgreement+"/"+position.x+"/"+position.y;
		doRequest(caller, fullURL);
	}
	public void goalAchieved(Notifiable caller,int goalID)
	{
		String serviceURL = baseServiceURL + GOAL_ACHIEVED_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+goalID;
		doRequest(caller, fullURL);
	}
	public void rate(Notifiable caller,float rateValue,int rateForUserID)
	{
		String serviceURL = baseServiceURL + RATE_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+rateValue+"/"+rateForUserID;
		doRequest(caller, fullURL);
	}
	public void updateMyPosition(Notifiable caller,PointF position)
	{
		String serviceURL = baseServiceURL + UPDATE_MY_POSITION_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+position.x+"/"+position.y;
		doRequest(caller, fullURL);
	}
	public void getUsersAroundMe(Notifiable caller,int wantedUserState,PointF position)
	{
		String serviceURL = baseServiceURL + GET_USERS_AROUND_ME_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+wantedUserState+"/"+position.x+"/"+position.y;
		doRequest(caller, fullURL);
	}
	
	public void doRequest(Notifiable caller,String fullURL)
	{
		ServerClient client=new ServerClient(caller);
		client.execute(fullURL);
	}
	
	protected String addParamsToUrl(String url , List<NameValuePair> params){
		
	    if(!url.endsWith("?"))
	        url += "?";

	    String paramString = URLEncodedUtils.format(params, "utf-8");
	    url += paramString;
	    
	    return url;
	}
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
