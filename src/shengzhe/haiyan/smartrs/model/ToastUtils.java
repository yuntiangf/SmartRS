package shengzhe.haiyan.smartrs.model;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.view.CustomTextView;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {

	public static void showToast(String content,Context context){
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View layout = inflater.inflate(R.layout.view_toast, null);
		CustomTextView textToast = (CustomTextView) layout.findViewById(R.id.TextToast);
		textToast.setText(content);
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}
}
