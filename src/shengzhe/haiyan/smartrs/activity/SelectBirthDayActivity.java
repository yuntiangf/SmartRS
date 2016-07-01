package shengzhe.haiyan.smartrs.activity;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

public class SelectBirthDayActivity extends BaseActivity implements
		OnClickListener {

	private NumberPicker np1, np2, np3;
	private static String str1 = "1999";
	private static String str2 = "1";
	private static String str3 = "1";
	private TextView btn_cancel,btn_sure;
	private Intent intent = new Intent();
	private String birthday = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_birthday);
		AppManager.getAppManager().addActivity(this);
		
		findView();
		setNumPicker();
		setEvent();
	}
	
	
	private void findView() {
		// TODO Auto-generated method stub
		np1 = (NumberPicker) findViewById(R.id.np1);
		np2 = (NumberPicker) findViewById(R.id.np2);
		np3 = (NumberPicker) findViewById(R.id.np3);
		btn_cancel = (TextView) findViewById(R.id.btn_cancel);
		btn_sure = (TextView) findViewById(R.id.btn_sure);
	}


	private void setNumPicker() {
		// TODO Auto-generated method stub
		np1.setMaxValue(2299);
		np1.setMinValue(1970);
		np1.setValue(1999);
		np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				str1 = np1.getValue() + "";
				if (Integer.parseInt(str1) % 4 == 0
						&& Integer.parseInt(str1) % 100 != 0
						|| Integer.parseInt(str1) % 400 == 0) {
					if (str2.equals("1") || str2.equals("3")
							|| str2.equals("5") || str2.equals("7")
							|| str2.equals("8") || str2.equals("10")
							|| str2.equals("12")) {
						np3.setMaxValue(31);
						np3.setMinValue(1);
					} else if (str2.equals("4") || str2.equals("6")
							|| str2.equals("9") || str2.equals("11")) {
						np3.setMaxValue(30);
						np3.setMinValue(1);
					} else {
						np3.setMaxValue(29);
						np3.setMinValue(1);
					}

				} else {
					if (str2.equals("1") || str2.equals("3")
							|| str2.equals("5") || str2.equals("7")
							|| str2.equals("8") || str2.equals("10")
							|| str2.equals("12")) {
						np3.setMaxValue(31);
						np3.setMinValue(1);
					} else if (str2.equals("4") || str2.equals("6")
							|| str2.equals("9") || str2.equals("11")) {
						np3.setMaxValue(30);
						np3.setMinValue(1);
					} else {
						np3.setMaxValue(28);
						np3.setMinValue(1);
					}
				}

			}
		});

		np2.setMaxValue(12);
		np2.setMinValue(1);
		np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				str2 = np2.getValue() + "";
				if (str2.equals("1") || str2.equals("3") || str2.equals("5")
						|| str2.equals("7") || str2.equals("8")
						|| str2.equals("10") || str2.equals("12")) {
					np3.setMaxValue(31);
					np3.setMinValue(1);
				} else if (str2.equals("4") || str2.equals("6")
						|| str2.equals("9") || str2.equals("11")) {
					np3.setMaxValue(30);
					np3.setMinValue(1);
				} else {
					if (Integer.parseInt(str1) % 4 == 0
							&& Integer.parseInt(str1) % 100 != 0
							|| Integer.parseInt(str1) % 400 == 0) {
						np3.setMaxValue(29);
						np3.setMinValue(1);
					} else {
						np3.setMaxValue(28);
						np3.setMinValue(1);
					}
				}
			}
		});

		np3.setMaxValue(31);
		np3.setMinValue(1);
		np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				str3 = np3.getValue() + "";
			}
		});
	}


	private void setEvent() {
		// TODO Auto-generated method stub
		btn_sure.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sure:
			birthday = String.valueOf(np1.getValue()) + "-" + (np2.getValue())
			+ "-" + np3.getValue();
			ChangeBirthday();
			break;
			
		case R.id.btn_cancel:
			finish();
			break;
		default:
			finish();
			break;
		}
	}
	
	private void ChangeBirthday() {
		// TODO Auto-generated method stub
		PreferenceUtil.initialize(SelectBirthDayActivity.this);
		int uid = PreferenceUtil.getUid();
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.UPDATE_DATE);
		sb.append(ServerUrl.getEncodeName(birthday));
		sb.append("&&id=");
		sb.append(uid);
		String url = sb.toString();
		System.out.println("__________"+url);
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				User user = QLParser.parse(responseInfo.result, User.class);
				if (user.status == 200) {
					ToastUtils.showToast(user.message, SelectBirthDayActivity.this);
					SelectBirthDayActivity.this.finish();
				} else if (user.status == 300) {
					ToastUtils.showToast(user.message, SelectBirthDayActivity.this);
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

}
