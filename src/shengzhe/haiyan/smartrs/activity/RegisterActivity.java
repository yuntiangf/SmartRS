package shengzhe.haiyan.smartrs.activity;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.base.fragement.MePage2;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity implements OnClickListener{

	private TextView title_text;
	private EditText register_username;
	private EditText register_password;
	private EditText register_password2;
	private EditText register_phone;
	private Button submit;
	private Button cancel;
	
	private String username;
	private String password;
	private String password2;
	private String phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		title_text = (TextView) findViewById(R.id.title_text);
		title_text.setText("注册");
		initView();
	}

	public void initView() {
		register_username = (EditText) findViewById(R.id.register_username);
		register_password = (EditText) findViewById(R.id.register_password);
		register_password2 = (EditText) findViewById(R.id.register_password2);
		register_phone = (EditText) findViewById(R.id.register_phone);
		submit = (Button) findViewById(R.id.register_submit);
		cancel = (Button) findViewById(R.id.register_cancel);
		
		submit.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_submit:
			postRegister();
			break;
		case R.id.register_cancel:
			this.finish();
			break;
		}
	}
	
	private void postRegister(){
		username = register_username.getText().toString();
		password = register_password.getText().toString();
		password2 = register_password2.getText().toString();
		phone = register_phone.getText().toString();
		
		if(username.isEmpty()){
			ToastUtils.showToast("请输入用户名！", RegisterActivity.this);
		}else if(password.isEmpty() || password2.isEmpty()){
			ToastUtils.showToast("密码不能为空！", RegisterActivity.this);
		}else if(password.length() < 3){
    		ToastUtils.showToast("密码长度过短!", RegisterActivity.this);
    	}else if(!password.equals(password2)){
    		ToastUtils.showToast("两次密码输入不一致,请重新输入！", RegisterActivity.this);
		}else{
			register();
//			Intent intent = new Intent();
//            intent.setClass(RegisterActivity.this,MePage2.class);
//            this.startActivity(intent);
//			this.finish();
		}
	}

	private void register() {
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.REGISTER);
		sb.append("?name=");
		sb.append(ServerUrl.getEncodeName(username));
		sb.append("&&pwd=");
		sb.append(ServerUrl.getEncodeName(password));
		sb.append("&&phone=");
		sb.append(ServerUrl.getEncodeName(phone));

		String url = sb.toString();
		System.out.println("URL-->" + url);

		//http://192.168.1.108:8080/XLJ2/Register?name=ads&&pwd=111&&phone=00000000000
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				System.out.println("llllllll");
				progressData(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				System.out.println("ssssssssss");
			}
		});
	}

	protected void progressData(String result) {
		User user = QLParser.parse(result, User.class);

		int status = user.status;
		System.out.println("status-->asssssss"+status);
		// List<UserList> listData = new ArrayList<User.UserList>();
		if (status == 300) {
			ToastUtils.showToast(user.message, RegisterActivity.this);
		}

		if (status == 200) {
			// listData.addAll(user.result);
			PreferenceUtil.initialize(RegisterActivity.this);
			PreferenceUtil.saveUserPrefer(username, password, user.result.get(0).sex,
					user.result.get(0).habitus, user.result.get(0).headerurl, 
					user.result.get(0).bothday, user.result.get(0).place,
					user.result.get(0).intro, user.result.get(0).phone,
					user.result.get(0).id, 1);
			finish();
		}
	}

}
