package com.example.customviewbase.demo.pan;

import android.view.View;
import android.view.ViewGroup;

public interface Adapter {
    
    int getCount();
    Object getItem(int position);
    long getItemId(int position);
    View getView(int position);
    
}
