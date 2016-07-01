package shengzhe.haiyan.smartrs.base.fragement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.activity.CaptureActivity;
import shengzhe.haiyan.smartrs.activity.LoginActivity;
import shengzhe.haiyan.smartrs.activity.MoreActivity;
import shengzhe.haiyan.smartrs.activity.MyInfoActivity;
import shengzhe.haiyan.smartrs.activity.MyLoveActivity;
import shengzhe.haiyan.smartrs.activity.SettingsActivity;
import shengzhe.haiyan.smartrs.adapter.GridViewAdapter;
import shengzhe.haiyan.smartrs.base.BaseFragment2;
import shengzhe.haiyan.smartrs.habitusdetection.DMainActivity;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import shengzhe.haiyan.smartrs.view.CircleImageView;

//������Ϣ
public class MePage2 extends BaseFragment2 {

	//����
	@ViewInject(R.id.title_text)
	private TextView title_text;
	//��½��ע�ᰴť
	@ViewInject(R.id.btn_login_regist)
	private Button login_bt;
	//��ʾ��½��ע�ᰴť��layout
	@ViewInject(R.id.layout_info_01)
	private RelativeLayout layout_info01;
	//��ʾ����ͷ���ǳơ�����ǩ����layout
	@ViewInject(R.id.layout_info_02)
	private LinearLayout layout_info02;
	//ͷ��
	@ViewInject(R.id.photo)
	private CircleImageView photo;
	//�ǳ�
	@ViewInject(R.id.username)
	private TextView username;
	//����ǩ��
	@ViewInject(R.id.intro)
	private TextView user_intro;
	//���ʼ��
	@ViewInject(R.id.img_body_test)
	private RelativeLayout body_test;
	//ϲ���Ĳ�Ʒ
	@ViewInject(R.id.img_my_love_menu)
	private RelativeLayout my_love_menu;
	//ɨһɨ
	@ViewInject(R.id.img_saoyisao)
	private RelativeLayout saoyisao;
	//����
	@ViewInject(R.id.img_settings)
	private RelativeLayout settings;
	//����
	@ViewInject(R.id.img_more)
	private RelativeLayout more;

	private Intent intent = new Intent();
	private BitmapUtils bitmapUtils;

	private String name = "", pwd = "";

	@Override
	public View initView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.page_me2, null);
		ViewUtils.inject(this, view);
		return view;
	}

	//��ʼ��
	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title_text.setText("��");
		body_test.setOnClickListener(this);
		my_love_menu.setOnClickListener(this);
		saoyisao.setOnClickListener(this);
		settings.setOnClickListener(this);
		more.setOnClickListener(this);
		login_bt.setOnClickListener(this);
		layout_info02.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//��½��ע��
		case R.id.btn_login_regist:
			intent.setClass(getActivity(), LoginActivity.class);
			startActivity(intent);
			break;
		//������Ϣ
		case R.id.layout_info_02:
			intent.setClass(getActivity(), MyInfoActivity.class);
			startActivity(intent);
			break;
			
		//���ʼ��
		case R.id.img_body_test:
//			intent.setClass(getActivity(), DMainActivity.class);
//			startActivity(intent);
			break;

		//ϲ���ľ���
		case R.id.img_my_love_menu:
			intent.setClass(getActivity(), MyLoveActivity.class);
			startActivity(intent);
			break;

		//ɨһɨ
		case R.id.img_saoyisao:
			Intent openCameraIntent =new Intent(getActivity(), CaptureActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;

		//����
		case R.id.img_settings:
			intent.setClass(getActivity(), SettingsActivity.class);
			startActivity(intent);
			break;
			
		//����
		case R.id.img_more:
			intent.setClass(getActivity(), MoreActivity.class);
			startActivity(intent);
			break;
		
		default:
			break;
		}
	}
	
	 @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == -1) {
				Bundle bundle = data.getExtras();
				String scanResult = bundle.getString("result");
  
				LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
				View myInflater = layoutInflater.inflate(R.layout.sao_show, null);
				TextView result = (TextView) myInflater
						.findViewById(R.id.showresult);
				result.setText(scanResult);
				Dialog dialog = new AlertDialog.Builder(getActivity())
						.setIcon(R.drawable.saoyisao).setTitle("ɨ������")
						.setView(myInflater).create();
				dialog.show();

			}
		}
	 

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isLogin();
	}

	//�жϱ����Ƿ����û��û������������Ϣ��������ʹ���û����������½��������ʾ��½ע�ᰴť
	private void isLogin() {
		// TODO Auto-generated method stub
		PreferenceUtil.initialize(getActivity());
		int status = PreferenceUtil.getUserStatus();

		name = PreferenceUtil.getUserName();
		pwd = PreferenceUtil.getUserPwd();

		System.out.println("name-->"+name+",pwd-->"+pwd+",status-->"+status);
		if (status == 1) {
			Login();
			layout_info01.setVisibility(View.GONE);
			layout_info02.setVisibility(View.VISIBLE);
		} else {
			layout_info01.setVisibility(View.VISIBLE);
			layout_info02.setVisibility(View.GONE);
		}
	}
	
	//��½
	private void Login() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.LOGIN);
		sb.append("?name=");
		sb.append(ServerUrl.getEncodeName(name));
		sb.append("&&pwd=");
		sb.append(ServerUrl.getEncodeName(pwd));

		String url = sb.toString();
//		http://192.168.8.108:8080/XLJ2/Login?name=aaa&&pwd=12345
		//ͨ���ӿڼ�������
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
	
	//��ȡ������Ϣ
	protected void progressData(String result) {
		// TODO Auto-generated method stub
		User user = QLParser.parse(result, User.class);
		if (user.status == 200) {
			String name = user.result.get(0).name;
			String intro = user.result.get(0).intro;
			String headerurl = user.result.get(0).headerurl;

			if(headerurl == null || headerurl.equals("")){
				photo.setImageResource(R.drawable.default_login);
			}else {
				bitmapUtils = new BitmapUtils(getActivity());
				bitmapUtils.display(photo, ServerUrl.SERVER_ROOT_PATH
						+ headerurl);
			}
			username.setText(name);
			if (intro != null) {
				user_intro.setText(intro);
			}
		}
	}

}
