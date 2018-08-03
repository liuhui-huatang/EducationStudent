package com.htcompany.education.studentforgansu.commonpart.tools;

import android.content.Context;
import android.widget.Toast;

/**  
 * Filename: ToastUtil.java  <br>
 *
 * Description:   <br>
 * 
 * @version: 1.0 <br>
 *
 * @Copyright: Copyright (c)2016 by wrb <br>
 *  
 */

public class ToastUtil {

	public static void showToast(String things, Context context){
		if(context!=null) {
			Toast.makeText(context, things, Toast.LENGTH_SHORT).show();
		}
	}
}
