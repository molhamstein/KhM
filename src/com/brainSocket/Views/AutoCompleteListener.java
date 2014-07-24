package com.brainSocket.Views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import com.brainSocket.data.DataSrc;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.google.android.gms.internal.da;
 
public class AutoCompleteListener implements TextWatcher{
 
    
    Context context;
    
     
    public AutoCompleteListener(Context context ){
        this.context = context;
    }
     
    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {
  
        MainMap mainActivity = ((MainMap) context);
        List<String> areas =  KedniApp.dataSrc.localHandler.getAreas(userInput.toString());
        mainActivity.suggestions.clear();
        mainActivity.suggestions.addAll(areas);
        //mainActivity.autoCompleteAdapter.clear();
        mainActivity.autoCompleteAdapter.notifyDataSetChanged();
        //mainActivity.autoCompleteAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        //mainActivity.myAutoComplete.setAdapter(mainActivity.myAdapter);
         
    }
    
    
 
}