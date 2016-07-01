package shengzhe.haiyan.smartrs.activity;

import shengzhe.haiyan.smartrs.MainActivity;
import shengzhe.haiyan.smartrs.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * ������ڡ���Ҫ�ж��Ƿ�Ϊ��һ������
 */
public class StartActivity extends Activity {

	boolean isFirstIn = false;
	Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_view);
		
		// �ж��Ƿ�Ϊ��һ�ε�¼
		SharedPreferences preference = getSharedPreferences("firsr_pref", MODE_PRIVATE);
		
		isFirstIn = preference.getBoolean("isFirstIn", true);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(isFirstIn){
					intent= new Intent(StartActivity.this, LeaderActivity.class);
				}else{
					intent= new Intent(StartActivity.this, MainActivity.class);
				}
				StartActivity.this.startActivity(intent);
				StartActivity.this.finish();
			}
		}, 2000);

	}
}
