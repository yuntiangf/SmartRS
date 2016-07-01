package shengzhe.haiyan.smartrs.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BasePage extends Activity implements OnClickListener{
   private View view;
   public Context ct;

	public BasePage(Context ct) {
		this.ct = ct;
		LayoutInflater inflater = (LayoutInflater)ct.getSystemService(
			      Context.LAYOUT_INFLATER_SERVICE);
		view = initView(inflater);
	}
	public  View getRootView(){
    	return view;
    }
	public abstract View initView(LayoutInflater inflater);
    
	public abstract void initData();
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
