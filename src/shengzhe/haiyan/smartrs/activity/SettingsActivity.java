package shengzhe.haiyan.smartrs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;

public class SettingsActivity extends BaseActivity implements OnClickListener {

	
	private TextView title;
	private ImageView back;
	private Button item_set_login_out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		AppManager.getAppManager().addActivity(this);
		
		findView();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		item_set_login_out = (Button) findViewById(R.id.item_set_login_out);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		title.setText("…Ë÷√");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		item_set_login_out.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.item_set_login_out:
			AppManager.getAppManager().AppExit();
			break;
		
		}
	}

}
