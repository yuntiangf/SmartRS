package shengzhe.haiyan.smartrs.activity;


import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;

public class SelectSexActivity extends BaseActivity implements OnClickListener{

	
	private LinearLayout dialogLayout;
	private Button btn_pick_man, btn_pick_woman;
	private Intent intent = new Intent();
	private String sex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sex);
		AppManager.getAppManager().addActivity(this);
		
		initView();
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		dialogLayout = (LinearLayout) findViewById(R.id.dialog_layout);
		dialogLayout.setOnClickListener(this);
		btn_pick_man = (Button) findViewById(R.id.btn_pick_man);
		btn_pick_man.setOnClickListener(this);
		btn_pick_woman = (Button) findViewById(R.id.btn_pick_woman);
		btn_pick_woman.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.dialog_layout:
			finish();
			break;
		case R.id.btn_pick_man:
			sex = "ÄÐ";
			changeSex();
			break;
		case R.id.btn_pick_woman:
			sex ="Å®";
			changeSex();
			break;
		default:
			finish();
			break;
		}
	}

	private void changeSex() {
		// TODO Auto-generated method stub
		PreferenceUtil.initialize(SelectSexActivity.this);
		int uid = PreferenceUtil.getUid();
		StringBuilder sb2 = new StringBuilder();
		sb2.append(ServerUrl.UPDATE_SEX);
		sb2.append(ServerUrl.getEncodeName(sex));
		sb2.append("&&id=");
		sb2.append(uid);
		String url = sb2.toString();
		
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				User user = QLParser.parse(responseInfo.result, User.class);
				if (user.status == 200) {
					ToastUtils.showToast(user.message, SelectSexActivity.this);
					SelectSexActivity.this.finish();
				} else if (user.status == 300) {
					ToastUtils.showToast(user.message, SelectSexActivity.this);
				}
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// showTips();
			finish();
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}
}
