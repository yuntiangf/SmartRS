package shengzhe.haiyan.smartrs.activity;

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
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectIntroActivity extends BaseActivity implements
		OnClickListener {

	private TextView title;
	private ImageView back;
	private EditText edit_input;
	private Button input_sure;
	private String intro = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		edit_input = (EditText) findViewById(R.id.edit_input);
		input_sure = (Button) findViewById(R.id.input_sure);

		title.setText("个性签名");
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
			if (0 < edit_input.getText().toString().length()
					&& edit_input.getText().toString().length() < 30) {
				changeIntro();
			} else {
				ToastUtils.showToast("个性签名为1到30个字符，请重新输入！",
						SelectIntroActivity.this);
			}
			break;

		}
	}

	private void changeIntro() {
		// TODO Auto-generated method stub
		intro = edit_input.getText().toString();
		PreferenceUtil.initialize(SelectIntroActivity.this);
		int uid = PreferenceUtil.getUid();
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append(ServerUrl.UPDATE_INTRO);
		sb2.append(ServerUrl.getEncodeName(intro));
		sb2.append("&&id=");
		sb2.append(uid);
		
		String url = sb2.toString();
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				User user = QLParser.parse(responseInfo.result, User.class);
				if (user.status == 200) {
					ToastUtils.showToast(user.message, SelectIntroActivity.this);
					SelectIntroActivity.this.finish();
				} else if (user.status == 300) {
					edit_input.setText("");
					ToastUtils.showToast(user.message, SelectIntroActivity.this);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

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
