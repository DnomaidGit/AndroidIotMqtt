package com.dnomaid.mqtt.global;

import android.content.Context;
import android.widget.Toast;

public class Notify {
  public static void toast(Context context, CharSequence text) {
    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    toast.show();
  }
}
