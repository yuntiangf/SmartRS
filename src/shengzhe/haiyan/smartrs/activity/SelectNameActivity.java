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
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectNameActivity extends BaseActivity implements OnClickListener {

	private TextView title;
	private ImageView back;
	private EditText edit_name;
	private Button input_sure;
	private String newName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changename);
		AppManager.getAppManager().addActivity(this);

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		edit_name = (EditText) findViewById(R.id.edit_input);
		input_sure = (Button) findViewById(R.id.input_sure);

		title.setText("修改昵称");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		input_sure.setOnClickListener(this);
		input_sure.setClickable(false);

		edit_name.addTextChangedListener(mWatcher);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.input_sure:
			newName = edit_name.getText().toString();

			if (0 < newName.length() && newName.length() < 16) {
				changeName();
			} else {
				ToastUtils.showToast("个性签名为1到15个字符，请重新输入！",
						SelectNameActivity.this);
			}
			break;
		}
	}

	private void changeName() {
		// TODO Auto-generated method stub
		PreferenceUtil.initialize(SelectNameActivity.this);
		int uid = PreferenceUtil.getUid();
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append(ServerUrl.UPDATE_NAME);
		sb2.append(ServerUrl.getEncodeName(newName));
		sb2.append("&&id=");
		sb2.append(uid);
		
		String url = sb2.toString();
		System.out.println("url-->"+url);
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				User user = QLParser.parse(responseInfo.result, User.class);
				if (user.status == 200) {
					PreferenceUtil.initialize(SelectNameActivity.this);
					PreferenceUtil.setUserName(newName);
					ToastUtils.showToast(user.message, SelectNameActivity.this);
					SelectNameActivity.this.finish();
				} else if (user.status == 300) {
					edit_name.setText("");
					ToastUtils.showToast(user.message, SelectNameActivity.this);
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

}
