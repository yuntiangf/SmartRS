package shengzhe.haiyan.smartrs.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		init(context);
	}
	
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public CustomTextView(Context context, AttributeSet attrs,int defSyle) {
		// TODO Auto-generated constructor stub
		super(context, attrs, defSyle);
		init(context);
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		setTypeface(FontCustom.setFont(context));
	}

	private static class FontCustom{

		static String fongUrl = "fonts/STHeiti-Light.ttc";
		static Typeface tf;
		
		public static Typeface setFont(Context context) {
			// TODO Auto-generated method stub
			if(tf == null){
				tf = Typeface.createFromAsset(context.getAssets(), fongUrl);
			}
			return tf;
		}
		
	}
}
