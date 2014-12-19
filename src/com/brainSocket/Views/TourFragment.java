
package com.brainSocket.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainSocket.khednima3ak.R;

public class TourFragment extends Fragment {
    private static final String ARG_KEY = "key";
    private static final String TITLE_KEY = "title";
    private static final String IMG_KEY = "img";
    private static final String MSG_KEY = "msg";
    
    private int img ;
    private int msg ;
    private int title ;
    
    
    public static final TourFragment newInstance(int title, int msg , int img)
    {
        TourFragment f = new TourFragment() ;
        Bundle bdl = new Bundle(3);
        bdl.putInt(TITLE_KEY, title);
        bdl.putInt(MSG_KEY, msg);
        bdl.putInt(IMG_KEY, img);
        
        f.setArguments(bdl);
        return f;
    }

   
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title= getArguments().getInt(TITLE_KEY) ;
        msg= getArguments().getInt(MSG_KEY) ;
        img= getArguments().getInt(IMG_KEY) ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.tour_fragment_page, container, false);
        
        ((TextView) rootView.findViewById(android.R.id.title)).setText(getString(title));
        ((TextView) rootView.findViewById(R.id.tour_msg)).setText(getString(msg));
        ((ImageView) rootView.findViewById(R.id.tour_img)).setImageResource(img);
        

        return rootView;
    }

}
