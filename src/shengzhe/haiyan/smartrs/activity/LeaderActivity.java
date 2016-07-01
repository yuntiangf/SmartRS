package shengzhe.haiyan.smartrs.activity;

import java.util.ArrayList;
import java.util.List;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.base.fragement.Fragment1_start;
import shengzhe.haiyan.smartrs.base.fragement.Fragment2_start;
import shengzhe.haiyan.smartrs.base.fragement.Fragment3_start;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * Òýµ¼Ò³
 */
public class LeaderActivity extends FragmentActivity {

	private ViewPager viewPage;
	private Fragment1_start mFragment1;
	private Fragment2_start mFragment2;
	private Fragment3_start mFragment3;
	private PagerAdapter mPgAdapter;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lead_view);

		SharedPreferences preferences = getSharedPreferences("firsr_pref", MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("isFirstIn", false);
		editor.commit();
		
		
		initView();
		viewPage.setOnPageChangeListener(new MyPagerChangeListener());

	}

	private void initView() {
		viewPage = (ViewPager) findViewById(R.id.start_viewpager);
		mFragment1 = new Fragment1_start();
		mFragment2 = new Fragment2_start();
		mFragment3 = new Fragment3_start();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		viewPage.setAdapter(mPgAdapter);

	}

	public class MyPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

	}
	
	public class ViewPagerAdapter extends FragmentPagerAdapter {

		private List<Fragment> fragmentList = new ArrayList<Fragment>();

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public ViewPagerAdapter(FragmentManager fragmentManager,
				List<Fragment> arrayList) {
			super(fragmentManager);
			this.fragmentList = arrayList;
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}
	}
}
