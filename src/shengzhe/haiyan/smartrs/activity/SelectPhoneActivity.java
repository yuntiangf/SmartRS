package shengzhe.haiyan.smartrs.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;

/**
 * @author:hj
 * @date:2016-4-27 下午4:25:48
 */
public class SelectPhoneActivity extends BaseActivity implements OnClickListener {

	private TextView title;
	private ImageView back;
	private EditText edit_input;
	private Button input_sure;
	private String phone = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		edit_input = (EditText) findViewById(R.id.edit_input);
		input_sure = (Button) findViewById(R.id.input_sure);

		title.setText("更改号码");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		input_sure.setOnClickListener(this);
		input_sure.setClickable(false);

		edit_input.addTextChangedListener(mWatcher);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.input_sure:
			String str = edit_input.getText().toString();
			if (str.length() == 11) {
					changePhone();
				} else {
					ToastUtils.showToast("电话号码为11个字符，请重新输入！",
							SelectPhoneActivity.this);
				}
			break;
		}
	}

	private void changePhone() {
		// TODO Auto-generated method stub
		phone = edit_input.getText().toString();
		PreferenceUtil.initialize(SelectPhoneActivity.this);
		int uid = PreferenceUtil.getUid();
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append(ServerUrl.UPDATE_PHONE);
		sb2.append(ServerUrl.getEncodeName(phone));
		sb2.append("&&id=");
		sb2.append(uid);
		
		String url = sb2.toString();
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				User user = QLParser.parse(responseInfo.result, User.class);
				if (user.status == 200) {
					ToastUtils.showToast(user.message, SelectPhoneActivity.this);
					SelectPhoneActivity.this.finish();
				} else if (user.status == 300) {
					edit_input.setText("");
					ToastUtils.showToast(user.message, SelectPhoneActivity.this);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

//	public static boolean isMobileNum(String mobiles){
//		Pattern pattern = Pattern.compile("1[0-9]");
//		
//		Matcher m = pattern.matcher(mobiles);
//		return m.matches();
//	}
	
	private TextWatcher mWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			input_sure.setClickable(true);

		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


}
