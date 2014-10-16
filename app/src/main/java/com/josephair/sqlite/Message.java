package com.josephair.sqlite;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Joseph_Air on 10/12/14.
 */
public class Message {

    public  static void message(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }
}
