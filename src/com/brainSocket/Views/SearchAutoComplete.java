package com.brainSocket.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
 
public class SearchAutoComplete extends AutoCompleteTextView {
 
    public SearchAutoComplete(Context context) {
        super(context);
    }
     
    public SearchAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public SearchAutoComplete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(filterText, keyCode);
    }
 
    /*
     * after a selection we have to capture the new value and append to the existing text
     */
    @Override
    protected void replaceText(final CharSequence text) {
        super.replaceText(text);
    }
 
}