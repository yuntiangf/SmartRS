package shengzhe.haiyan.smartrs.habitusdetection;


import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.activity.SelectNameActivity;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends BaseActivity implements OnClickListener{

	private TextView title;
	private ImageView back;
	private String str = null;
	private Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);

		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		submit = (Button) findViewById(R.id.submit);
		
		title.setText("测试结果");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
		
		
		double[] ss = new double[9];
		ss = getIntent().getDoubleArrayExtra("transformdata");
		TextView t = (TextView) findViewById(R.id.result);
		// t.setText((int) ss[0] + " " + ss[1] + " " + ss[2] + " " + ss[3] + " "
		// + ss[4] + " " + ss[5] + " " + ss[6] + " " + ss[7] + " " + ss[8]
		// + "");
		if (judge1(ss[8])) {
			if (judge2(ss[0]) && judge2(ss[1]) && judge2(ss[2])
					&& judge2(ss[3]) && judge2(ss[4]) && judge2(ss[5])
					&& judge2(ss[6]) && judge2(ss[7])) {
				
				t.setText("您的体质为：平和质。");
				str = "平和质";
			} else if (judge3(ss[0]) && judge3(ss[1]) && judge3(ss[2])
					&& judge3(ss[3]) && judge3(ss[4]) && judge3(ss[5])
					&& judge3(ss[6]) && judge3(ss[7])) {
				String habitus = null;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ss.length - 1; i++) {
					if (judge5(ss[i])) {
						sb.append(habitus(i));
						sb.append("、");
					}
				}
				habitus = sb.substring(0, sb.length() - 1);
				t.setText("您的体质为：基本是平和质，有" + habitus + "倾向。");
				str = habitus;
			} else if (judge4(ss[0]) || judge4(ss[1]) || judge4(ss[2])
					|| judge4(ss[3]) || judge4(ss[4]) || judge4(ss[5])
					|| judge4(ss[6]) || judge4(ss[7])) {
				String habitus = null;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ss.length - 1; i++) {
					if (judge4(ss[i])) {
						sb.append(habitus(i));
						sb.append("、");
					}
				}
				habitus = sb.substring(0, sb.length() - 1);
				t.setText("您的体质为：" + habitus + "。");
				str = habitus;
			}
		} else if (ss[8] < 60) {
			if (judge4(ss[0]) || judge4(ss[1]) || judge4(ss[2])
					|| judge4(ss[3]) || judge4(ss[4]) || judge4(ss[5])
					|| judge4(ss[6]) || judge4(ss[7])) {
				String habitus = null;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ss.length - 1; i++) {
					if (judge4(ss[i])) {
						sb.append(habitus(i));
						sb.append("、");
					}
				}
				habitus = sb.substring(0, sb.length() - 1);
				t.setText("您的体质为：" + habitus + "。");
				str = habitus;
			} else if (judge5(ss[0]) || judge5(ss[1]) || judge5(ss[2])
					|| judge5(ss[3]) || judge5(ss[4]) || judge5(ss[5])
					|| judge5(ss[6]) || judge5(ss[7])) {
				String habitus = null;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ss.length - 1; i++) {
					if (judge5(ss[i])) {
						sb.append(habitus(i));
						sb.append("、");
					}
				}
				habitus = sb.substring(0, sb.length() - 1);
				t.setText("您的体质为：倾向是" + habitus + "。");
				str = habitus;
			} else if (judge2(ss[0]) && judge2(ss[1]) && judge2(ss[2])
					&& judge2(ss[3]) && judge2(ss[4]) && judge2(ss[5])
					&& judge2(ss[6]) && judge2(ss[7])) {
				t.setText("您未选择完所有题目，得不出正确的结论，请返回继续选择！");
			}
		}
		
		System.out.println("您的体质为-->"+str);
//		changeHabitus(str);
	}

	private void changeHabitus(String str) {
		PreferenceUtil.initialize(ResultActivity.this);
		int uid = PreferenceUtil.getUid();
		PreferenceUtil.setHabitus(str);
		StringBuilder sb2 = new StringBuilder();
		sb2.append(ServerUrl.UPDATE_HABITUS);
		sb2.append(ServerUrl.getEncodeName(str));
		sb2.append("&&id=");
		sb2.append(uid);
		
		String url = sb2.toString();
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				User user = QLParser.parse(responseInfo.result, User.class);
				if(user.status == 200){
					ToastUtils.showToast(user.message, ResultActivity.this);
					ResultActivity.this.finish();
				}else if(user.status == 300){
					ToastUtils.showToast(user.message, ResultActivity.this);
				}
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private boolean judge1(double d) {

		if (d >= 60) {
			return true;
		} else {
			return false;
		}

	}

	private boolean judge2(double d) {

		if (d < 30) {
			return true;
		} else {
			return false;
		}

	}

	private boolean judge3(double d) {

		if (d < 40) {
			return true;
		} else {
			return false;
		}

	}

	private boolean judge4(double d) {

		if (d >= 40) {
			return true;
		} else {
			return false;
		}

	}

	private boolean judge5(double d) {

		if (d >= 30 && d < 40) {
			return true;
		} else {
			return false;
		}

	}

	private String habitus(int a) {
		String habitus = null;
		if (a == 0) {
			habitus = "阳虚质";
		} else if (a == 1) {
			habitus = "阴虚质";
		} else if (a == 2) {
			habitus = "气虚质";
		} else if (a == 3) {
			habitus = "痰湿质";
		} else if (a == 4) {
			habitus = "湿热质";
		} else if (a == 5) {
			habitus = "血瘀质";
		} else if (a == 6) {
			habitus = "特禀质";
		} else if (a == 7) {
			habitus = "气郁质";
		}
		return habitus;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;

		case R.id.submit:
			changeHabitus(str);
			break;
		}
	}

}
