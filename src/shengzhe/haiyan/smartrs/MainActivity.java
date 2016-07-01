package shengzhe.haiyan.smartrs;

import shengzhe.haiyan.smartrs.base.HomeFragment2;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_main);

		HomeFragment2 homeFragment = new HomeFragment2(0);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, homeFragment).commit();
		
//		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
