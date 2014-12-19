package com.brainSocket.data;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.graphics.PointF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.brainSocket.Listners.SetGoalListner;
import com.brainSocket.enums.FilterType;
import com.brainSocket.enums.UserSex;
import com.brainSocket.enums.UserType;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.models.ScheduledTrip;

public class ServerDataSrc {
	
	
	//static final String baseServiceURL  ="http://brain-socket.com/5ednym3ak1/ci/index.php/users";
	//static final String baseServiceURL  ="http://198.38.91.194/5ednym3ak1/ci/index.php/users";
	//static final byte[] baseServiceURL_FOR_UDP  = {(byte) 198,(byte) 38,(byte) 91,(byte) 194};
	//static final String baseServiceURL_FOR_UDP  = "198.38.91.194" ;
	
	static final String REGISTER_SERVICE = "/register";
	static final String SEND_VERIFICATION_SERVICE = "/sendVerificationMessage";
	static final String ACCEPT_VERIFICATION_SERVICE = "/acceptVerificationMessage";
	static final String CHANGE_USER_STATE_SERVICE="/changeState";
	static final String SET_GOAL_SERVICE="/setGoal";
	static final String SET_FILTER_SERVICE="/setFilterType";
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
	static final String GET_VISIBILITY_AUTHORIZE_USERS_LIST_SERVICE="/getVisibilityAuthorizeUsersList";
	static final String BLOCK_FRIEND_SERVICE="/blockFriend";
	static final String UNBLOCK_FRIEND_SERVICE="/unblockFriend";
	static final String RESFRESH_ACCOUNT_SERVICE = "/refreshAccount" ;
	static final String SET_PRICE_SERVICE = "/setPrice" ;
	static final String IS_VALID_SERVICE = "/isVersionValid" ;
	static final String CHECK_SERVICE = "/checkService" ;
	static final String CHANGE_ACC_SEX_SERVICE = "/changeAcceptableSex" ;
	static final String GET_MESSAGES_RELATED_TO = "/getMessagesRelatedTo" ;
	static final String SEND_MESSAGE = "/sendMessage" ;
	
	static final String SCHEDULE_TRIP = "/setScheduledGoal" ;
	static final String DELETE_SCHEDULED_TRIP = "/removeScheduledGoal" ;
	
	// PORTS
//	private static final int UPDATE_MY_POS_PORT = 2522;
//	private static final int GET_USERS_ARROUND_ME_PORT = 2524;
	
	private static ConnectivityManager cm ;
	
	public ServerDataSrc (){
		
		 
	}
	
	// need to create staticaly 
	public boolean isOnline() {
		cm =(ConnectivityManager) KedniApp.appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	public void getVisibilityAuthorizeUsersList(Notifiable caller)
	{
		String serviceURL = KedniConfig.baseServiceURL + GET_VISIBILITY_AUTHORIZE_USERS_LIST_SERVICE ;
		String plainParams =  String.valueOf( KedniApp.getUserID() );
		String fullURL = serviceURL+"/"+appendDigest(plainParams);
		doRequest(caller, fullURL);
	}
	public void getPlacesStartsWith(Notifiable caller,String startedWith)
	{
		String serviceURL = KedniConfig.baseServiceURL + GET_PLACES_STARTS_WITH_SERVICE ;
		String plainParams = String.valueOf( KedniApp.getUserID()+"/"+startedWith );

		String fullURL = serviceURL+"/"+appendDigest(plainParams);
		doRequest(caller, fullURL);
	}
	public void changeAcceptableSex(Notifiable caller,UserSex sex)
	{
		String serviceURL = KedniConfig.baseServiceURL + CHANGE_ACCEPTABLE_SEX_SERVICE ;
		String plainParams = KedniApp.getUserID()+"/"+sex.toString();
		String fullURL = serviceURL+"/"+appendDigest(plainParams);
		doRequest(caller, fullURL);
	}
	public void changeScopeTo(Notifiable caller,int newScopeRadius)
	{
		String serviceURL = KedniConfig.baseServiceURL + CHANGE_SCOPE_TO_SERVICE ;
		String plainParams = KedniApp.getUserID()+"/"+newScopeRadius;
		String fullURL = serviceURL+"/"+appendDigest(plainParams);
		doRequest(caller, fullURL);
	}
	public void addVehicle(Notifiable caller,int carModelType,int carColor)
	{
		String serviceURL = KedniConfig.baseServiceURL + ADD_VEHICLE_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+carModelType+"/"+carColor);
		doRequest(caller, fullURL);
	}
	public void blockFriend(Notifiable caller,long friendId )
	{
		String serviceURL = KedniConfig.baseServiceURL + BLOCK_FRIEND_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+friendId);
		doRequest(caller, fullURL);
	}
	public void unblockFriend(Notifiable caller,long friendId )
	{
		String serviceURL = KedniConfig.baseServiceURL + UNBLOCK_FRIEND_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+friendId);
		doRequest(caller, fullURL);
	}
	public void getNotifications(Notifiable caller)
	{
		String serviceURL = KedniConfig.baseServiceURL + GET_NOTIFICATIONS_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(String.valueOf(KedniApp.getUserID()));
		doRequest(caller, fullURL);
	}
	public void register(Notifiable caller,String facebookID,String mobileNumber)
	{
		String serviceURL = KedniConfig.baseServiceURL + REGISTER_SERVICE ;
		String fullURL = serviceURL+"/"+facebookID+"/"+mobileNumber;
		doRequest(caller, fullURL);
	}
	
	public void AccesptVerificatioMessage(Notifiable caller,int userId, String msg)
	{
		String serviceURL = KedniConfig.baseServiceURL + ACCEPT_VERIFICATION_SERVICE;
		String fullURL = serviceURL+"/"+appendDigest(userId+"/"+msg);
		doRequest(caller, fullURL);
	}
	
	public void sendVerificatioMessage(Notifiable caller,String userId)
	{
		String serviceURL = KedniConfig.baseServiceURL + SEND_VERIFICATION_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(String.valueOf(userId ));
		doRequest(caller, fullURL);
	}

	public void changeState(Notifiable caller,UserType userType)
	{
		String serviceURL = KedniConfig.baseServiceURL + CHANGE_USER_STATE_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+userType.getValue());
		doRequest(caller, fullURL);
	}
	
	public void setGoal(Notifiable caller,int placeID,PointF position)
	{
		String serviceURL = KedniConfig.baseServiceURL + SET_GOAL_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+placeID+"/"+position.x+"/"+position.y);
		SetGoalListner.pendingGoal = true ;
		doRequest(caller, fullURL);
	}
	public void requestFor(Notifiable caller,int destinationUserID,PointF position , String price)
	{
		String serviceURL = KedniConfig.baseServiceURL + REQUEST_FOR_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+destinationUserID+"/"+position.x+"/"+position.y+ "/"+KedniApp.getGoalID() + "/" + price);
		doRequest(caller, fullURL);
	}
	public void responseFor(Notifiable caller,int partnerId ,int requestID,int hasAgreement,PointF position)
	{
		String serviceURL = KedniConfig.baseServiceURL + RESPONSE_FOR_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+partnerId+"/"+requestID+"/"+hasAgreement+"/"+position.x+"/"+position.y);
		doRequest(caller, fullURL);
	}
	public void goalAchieved(Notifiable caller,int goalID)
	{
		String serviceURL = KedniConfig.baseServiceURL + GOAL_ACHIEVED_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+goalID);
		doRequest(caller, fullURL);
	}
	public void rate(Notifiable caller,float rateValue,int rateForUserID , int goalId)
	{
		String serviceURL = KedniConfig.baseServiceURL + RATE_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+rateValue+"/"+rateForUserID);
		doRequest(caller, fullURL);
	}
	public void refreshAccount(Notifiable caller,String accessToken)
	{
		String serviceURL = KedniConfig.baseServiceURL + RESFRESH_ACCOUNT_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+accessToken );
		doRequest(caller, fullURL);
	}
	
	public void setPrice(Notifiable caller, int price)
	{
		String serviceURL = KedniConfig.baseServiceURL + SET_PRICE_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+price );
		doRequest(caller, fullURL);
	}
	
	public void checkValidVersion(Notifiable caller, String version)
	{
		String serviceURL = KedniConfig.baseServiceURL + IS_VALID_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+version );
		doRequest(caller, fullURL);
	}
	
	public void checkServiceValid(Notifiable caller, String version)
	{
		String serviceURL = KedniConfig.baseServiceURL + CHECK_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+version );
		doRequest(caller, fullURL);
	}
	
	public void setFilterType(Notifiable caller, FilterType filterType)
	{
		String serviceURL = KedniConfig.baseServiceURL + SET_FILTER_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+filterType.getValue() );
		doRequest(caller, fullURL);
	}
	
	public void changeAccSex(Notifiable caller, UserSex sex)
	{
		String serviceURL = KedniConfig.baseServiceURL + CHANGE_ACC_SEX_SERVICE ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+sex.getValue() );
		doRequest(caller, fullURL);
	}
	
	public void setScheduledTrip(Notifiable caller, ScheduledTrip trip)
	{
		String serviceURL = KedniConfig.baseServiceURL + SCHEDULE_TRIP ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+trip.getdestId()+"/"+trip.getDepartloc().longitude+"/"+trip.getDepartloc().latitude+"/"+trip.getMinutesToDepart());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("desc",trip.getAddressDescription()));
		
		try {
			UrlEncodedFormEntity URI = new UrlEncodedFormEntity(params,"UTF-8") ;
			doPOSTRequest(caller, fullURL, URI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		
		
		
		//String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/" );
		//doRequest(caller, fullURL);
	}
	
	public void deleteScheduledTrip(Notifiable caller)
	{
		String serviceURL = KedniConfig.baseServiceURL + DELETE_SCHEDULED_TRIP ;
		String fullURL = serviceURL+"/"+appendDigest( String.valueOf(KedniApp.getUserID()) );
		//String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/" );
		doRequest(caller, fullURL);
	}
	
	// GET messaging
	/*public void sendMessage (Notifiable caller , String msg , String peerId) {
		String serviceURL = baseServiceURL + SEND_MESSAGE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+peerId + "/"+msg ;
		
		doRequest(caller, fullURL);
	}
	*/
	
	// POST messaging
	public void sendMessage (Notifiable caller , String msg , String peerId) {
		String serviceURL = KedniConfig.baseServiceURL + SEND_MESSAGE ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("msg",msg));
		
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+peerId ) ;
		try {
			UrlEncodedFormEntity URI = new UrlEncodedFormEntity(params,"UTF-8") ;
			doPOSTRequest(caller, fullURL, URI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}
	
	public void getMessagesRelatedTo (Notifiable caller , String peerId ){
		String serviceURL = KedniConfig.baseServiceURL + GET_MESSAGES_RELATED_TO ;
		String fullURL = serviceURL+"/"+appendDigest(KedniApp.getUserID()+"/"+peerId) ;
		doRequest(caller, fullURL);
	}
	
	///UDP based 
	public void updateMyPosition(Notifiable caller,PointF position)
	{
		/*String serviceURL = baseServiceURLUDP ;//+ UPDATE_MY_POSITION_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+position.x+"/"+position.y;
		doUDPRequest(caller, fullURL, UPDATE_MY_POS_PORT); //doRequest(caller, fullURL);
		*/
		String fullURL = appendDigest(KedniApp.getUserID()+"/"+position.x+"/"+position.y);
		doUDPRequest(caller, fullURL, KedniConfig.UPDATE_MY_POS_PORT); //doRequest(caller, fullURL);
	}
	public void getUsersAroundMe(Notifiable caller,FilterType filterType,PointF position)
	{
		/*String serviceURL = baseServiceURL + GET_USERS_AROUND_ME_SERVICE ;
		String fullURL = serviceURL+"/"+KedniApp.getUserID()+"/"+filterType.getValue()+"/"+position.x+"/"+position.y + "/" + KedniApp.getDestinationID();
		doUDPRequest(caller, fullURL, UPDATE_MY_POS_PORT) ;//doRequest(caller, fullURL);
		 */	
		String fullURL = appendDigest(KedniApp.getUserID()+"/"+filterType.getValue()+"/"+position.x+"/"+position.y + "/" + KedniApp.getDestinationID());
		doUDPRequest(caller, fullURL, KedniConfig.GET_USERS_ARROUND_ME_PORT) ;//doRequest(caller, fullURL);
	}
	
	
	private void doPOSTRequest(Notifiable caller , String fullURL , UrlEncodedFormEntity params ){
		if(KedniApp.isVersionValid() ){
			ServerClientPOST client=new ServerClientPOST(caller,params);
			client.execute(fullURL);
		}else{
			// TODO notefy version is not valid
		}
	}
	
	public void doRequest(Notifiable caller,String fullURL)
	{
		if(KedniApp.isVersionValid() ){
			ServerClient client=new ServerClient(caller);
			//if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			//client.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, fullURL) ;
			client.execute(fullURL);
		}else{
			// TODO notefy version is not valid
		}
	}
	
	public void doUDPRequest(Notifiable caller,String fullURL , int port)
	{
		if(KedniApp.isVersionValid() ){
			ServerClientUDP client=new ServerClientUDP(caller, port,KedniConfig.baseServiceURL_FOR_UDP);
			client.execute(fullURL);
		}else{
			// TODO notefy version is not valid
		}
	}
	
	protected String addParamsToUrl(String url , List<NameValuePair> params){
		
	    if(!url.endsWith("?"))
	        url += "?";

	    String paramString = URLEncodedUtils.format(params, "utf-8");
	    url += paramString;
	    
	    return url;
	}
	
	private String appendDigest(String msg){
		int digest = EncManager.getDigest(msg, KedniApp.encKey) ;
		String msgWithDigest = msg +"/"+digest ;
		return msgWithDigest ;
	}

}
