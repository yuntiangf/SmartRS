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
import shengzhe.haiyan.smartrs.util.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private TextView title;
	private ImageView back;
	private TextView new_account;
	private EditText ed_uname, ed_pwd;
	private String uname, upwd;
	private Button btn_login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_login);

		findView();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		new_account = (TextView) findViewById(R.id.edit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		title.setText("登录");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);

		new_account.setVisibility(View.VISIBLE);
		new_account.setText("注册");
		new_account.setOnClickListener(this);

		ed_uname = (EditText) findViewById(R.id.ed_uname);
		ed_uname.addTextChangedListener(nameTextWatcher);
		ed_pwd = (EditText) findViewById(R.id.ed_pwd);
		ed_pwd.addTextChangedListener(pwdTextWatcher);
	}

	private TextWatcher nameTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			ed_uname.setError(null);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	private TextWatcher pwdTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			uname = ed_uname.getText().toString();
			if (uname == null) {
				ToastUtils.showToast("账户不能为空!", LoginActivity.this);
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			upwd = ed_pwd.getText().toString();
			if (s.toString().length() > 5) {

			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.btn_login:
			login();
			break;
		case R.id.edit:
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}

	private void login() {
		// TODO Auto-generated method stub
		if (uname != null && upwd != null && !"".equals(uname)
				&& !"".equals(upwd)) {
			if (Utils.checkPwd(upwd)) {
				StringBuilder sb = new StringBuilder();
				sb.append(ServerUrl.LOGIN);
				sb.append("?name=");
				sb.append(ServerUrl.getEncodeName(uname));
				sb.append("&&pwd=");
				sb.append(ServerUrl.getEncodeName(upwd));

				String url = sb.toString();
				System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLL" + url);
				getLogin(url);
			} else {
				ed_pwd.setText("");
				ToastUtils.showToast(
						getResources().getString(R.string.toast_pwd_limit),
						LoginActivity.this);
			}

		} else {
			ToastUtils.showToast(
					getResources().getString(R.string.toast_input_username),
					LoginActivity.this);
			ed_pwd.setText("");
		}
	}
//	http://192.168.1.108:8080/XLJ2/Login?name=aaa&&pwd=12345
	private void getLogin(String url) {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressData(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void progressData(String result) {
		// TODO Auto-generated method stub
		User user = QLParser.parse(result, User.class);

		int status = user.status;
		// List<UserList> listData = new ArrayList<User.UserList>();
		if (status == 300) {
			ToastUtils.showToast(user.message, LoginActivity.this);
		}

		if (status == 200) {
			// listData.addAll(user.result);
//			System.out.println(user.result.get(0).bothday + " "
//					+ user.result.get(0).id + " " + user.result.get(0).phone
//					+ " " + user.result.get(0).intro + " ");
			PreferenceUtil.initialize(LoginActivity.this);
				
			PreferenceUtil.saveUserPrefer(uname, upwd, user.result.get(0).sex,
					user.result.get(0).habitus, user.result.get(0).headerurl, 
					user.result.get(0).bothday, user.result.get(0).place,
					user.result.get(0).intro, user.result.get(0).phone,
					user.result.get(0).id, 1);
			finish();
		}
	}

}
