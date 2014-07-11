package com.brainSocket.adapters;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brainSocket.khednima3ak.EventsActivity;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.khednima3ak.SettingsActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter implements OnItemClickListener{
	
	
	  public static DrawerElement [] elements  = {
		  new DrawerElement(R.string.my_acount, R.drawable.contact, null) ,
		  new DrawerElement(R.string.Notifications, R.drawable.notifications, EventsActivity.class) ,
		  new DrawerElement(R.string.settings, R.drawable.settings, SettingsActivity.class) ,
		  new DrawerElement(R.string.history, R.drawable.history, null) , 
		  new DrawerElement(R.string.Signout, R.drawable.close, null) , 
		  new DrawerElement(R.string.about_us , R.drawable.about, null) ,
		  new DrawerElement(R.string.Signin, R.drawable.settings, null)
		  };
	
	  protected final Context context;
	  protected Boolean selectable ;
	  protected List<Integer> selected ; 
	  protected ListView list ;
	  protected View view ;

	  
	  public DrawerAdapter(Context context, ListView view ) {
	    super();
	    this.context = context;
	    
	    this.view = view ;
	    
	    list = view ;
	    
	    list.setOnItemClickListener(this);
	  }

	 
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  
		View rowView ;
		if(convertView == null){  
		  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		  rowView = inflater.inflate(R.layout.drawer_list_elem, parent, false);
		}else{
		  rowView = convertView ;
		}
		String title = context.getString(elements[position].stringId);
		int imRes  = elements[position].iconId;
		
		/*
		switch (position) {
		case 0:
			title = "My Acount" ;
			imRes = R.drawable.contact ;
			break;
		case 1:
			title = "Notifications" ;
			imRes = R.drawable.notifications ;
			break;
		case 2:
			title = "Settings" ;
			imRes = R.drawable.settings ;
			break;
		case 3:
			title = "History" ;
			imRes = R.drawable.history ;
			break;
		case 4:
			title = "SignOut" ;
			imRes = R.drawable.close ;
			break;
		case 5:
			title = "AboutUs" ;
			imRes = R.drawable.about ;
			break;

		default:
			title = "Signin" ;
			imRes = R.drawable.settings ;
			break;
		}
		*/
	    TextView txt  = (TextView) rowView.findViewById(R.id.title);
	    ImageView icon = (ImageView) rowView.findViewById(R.id.drawable_icon);
	    
	    txt.setText(title);
	    icon.setImageResource(imRes);
	    
	 /*   if( (position % 2)==0 ){
	    	rowView.setBackgroundResource(R.color.drawer_bg2);
	    }else{
	    	rowView.setBackgroundResource(R.color.drawer_bg1);
	    }
	   */ 

	    return rowView;
	  }
	  
	  
	@Override
	public int getCount() {
		return elements.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}


	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		if(elements[arg2].dest != null){	
			Intent intent = new Intent(context, elements[arg2].dest);
			context.startActivity(intent);
		}
	}
	
	
	static class DrawerElement{
		int stringId ;
		int iconId ;
		Class dest ;
		
		public DrawerElement(int str , int ico , Class dest) {
			stringId = str ;
			iconId = ico ;
			this.dest = dest ;
		}
	}
		  
}
