/*******************************************************************************
 * Copyright (c) 1999, 2014 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution. 
 *
 * The Eclipse Public License is available at 
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 */
package com.dnomaid.mqtt.global;

import android.content.Context;
import android.widget.Toast;

public class Notify {
  public static void toast(Context context, CharSequence text) {
    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    toast.show();
  }
}
