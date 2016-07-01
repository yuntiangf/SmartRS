package shengzhe.haiyan.smartrs.activity;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import shengzhe.haiyan.smartrs.view.CircleImageView;

public class MyInfoActivity extends BaseActivity implements OnClickListener {

	private TextView title;
	private ImageView back;
	// private CircleImageView img_photo;
	private ImageView img_photo;
	private TextView nickname, intro, habitus, sex, bothday, place, phone;

	private Intent intent = new Intent();
	private BitmapUtils bitmapUtils;

	String my_name = "", my_intro = "", my_habitus = "", my_sex = "",
			my_bothday = "", my_place = "", my_header = "",my_phone= "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_myinfo);

		initView();
		setEvent();
	}

	private void initView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		img_photo = (ImageView) findViewById(R.id.img_photo);
		nickname = (TextView) findViewById(R.id.nickname);
		habitus = (TextView) findViewById(R.id.habitus);
		intro = (TextView) findViewById(R.id.signatrue);
		sex = (TextView) findViewById(R.id.sex);
		bothday = (TextView) findViewById(R.id.birth);
		place = (TextView) findViewById(R.id.address);
		phone = (TextView) findViewById(R.id.phone);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		title.setText("个人信息");
		back.setOnClickListener(this);
		findViewById(R.id.item_myinfo).setOnClickListener(this);
		findViewById(R.id.item_nickname).setOnClickListener(this);
		findViewById(R.id.item_mysignature).setOnClickListener(this);
		findViewById(R.id.item_myhabitus).setOnClickListener(this);
		findViewById(R.id.item_sex).setOnClickListener(this);
		findViewById(R.id.item_mybirth).setOnClickListener(this);
		findViewById(R.id.item_address).setOnClickListener(this);
		findViewById(R.id.item_phone).setOnClickListener(this);
		findViewById(R.id.login_out).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		

		case R.id.item_nickname:
			intent.setClass(MyInfoActivity.this, SelectNameActivity.class);
			startActivity(intent);
			break;
			
		case R.id.item_mysignature:
			intent.setClass(MyInfoActivity.this, SelectIntroActivity.class);
			startActivity(intent);
			break;
			
		case R.id.item_sex:
			intent.setClass(MyInfoActivity.this, SelectSexActivity.class);
			startActivity(intent);
			break;
			
		case R.id.item_mybirth:
			intent.setClass(MyInfoActivity.this, SelectBirthDayActivity.class);
			startActivity(intent);
			break;

		case R.id.item_address:
			intent.setClass(MyInfoActivity.this, SelectCityActivity.class);
			startActivity(intent);
			break;

		case R.id.item_phone:
			intent.setClass(MyInfoActivity.this, SelectPhoneActivity.class);
			startActivity(intent);
			break;

		case R.id.login_out:
			LoginOut();
			break;
		}
	}

	private void LoginOut() {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, ServerUrl.LOGIN_OUT, null,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						User user = QLParser.parse(responseInfo.result,
								User.class);
						if (user.status == 200) {
//							ToastUtils.showToast(user.message,
//									MyInfoActivity.this);
							PreferenceUtil.initialize(MyInfoActivity.this);
							PreferenceUtil.setUserStatus(0);
							MyInfoActivity.this.finish();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		PreferenceUtil.initialize(MyInfoActivity.this);
		int status = PreferenceUtil.getUserStatus();

		System.out.println("status--->"+status);
		if (status == 1) {
			String name = PreferenceUtil.getUserName();
			String pwd = PreferenceUtil.getUserPwd();

			getData(name, pwd);
		}

	}

	private void getData(String name, String pwd) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.LOGIN);
		sb.append("?name=");
		sb.append(ServerUrl.getEncodeName(name));
		sb.append("&&pwd=");
		sb.append(ServerUrl.getEncodeName(pwd));

		String url = sb.toString();
		System.out.println("intro-->"+PreferenceUtil.getIntro()+",place-->"+PreferenceUtil.getPlace()+",phone"+PreferenceUtil.getPhone());
		System.out.println("url-->"+url);
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				System.out.println("res-->"+responseInfo.result);
				User user = QLParser.parse(responseInfo.result, User.class);
				
				my_name = user.result.get(0).name;
				my_intro = user.result.get(0).intro;
				my_habitus = user.result.get(0).habitus;
				my_sex = user.result.get(0).sex;
				my_place = user.result.get(0).place;
				my_bothday = user.result.get(0).bothday;
				my_header = user.result.get(0).headerurl;
				my_phone = user.result.get(0).phone;
				
				nickname.setText(my_name);
				System.out.println("habitus-->"+user.result.get(0).habitus);
				if (my_habitus==null) {
					habitus.setText("自然景观、休闲");
				} else {
					habitus.setText(my_habitus);
				}
				intro.setText(my_intro);
				sex.setText(my_sex);
				bothday.setText(my_bothday);
				place.setText(my_place);
				phone.setText(my_phone);
				
//				if (my_header == null || my_header.equals("")) {
//					my_header = "imgs/hulu.jpg";
//				}
//				bitmapUtils = new BitmapUtils(MyInfoActivity.this);
//				bitmapUtils.display(img_photo, ServerUrl.SERVER_ROOT_PATH
//						+ my_header);

				PreferenceUtil.initialize(MyInfoActivity.this);
				PreferenceUtil.setBothday(my_bothday);
				PreferenceUtil.setHeaderurl(my_header);
				PreferenceUtil.setIntro(my_intro);
				PreferenceUtil.setPlace(my_place);
				PreferenceUtil.setSex(my_sex);
				PreferenceUtil.setPhone(my_phone);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

}
