package shengzhe.haiyan.smartrs.base;

import java.util.ArrayList;
import java.util.List;


import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.base.fragement.FunctionPage2;
import shengzhe.haiyan.smartrs.base.fragement.MePage2;
import shengzhe.haiyan.smartrs.base.fragement.SmartPage2;
import shengzhe.haiyan.smartrs.view.CustomViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeFragment2 extends BaseFragment2 {

	private int pageIndex = 0;// 设置当前页数
	private RadioGroup main_radio;
	private List<BaseFragment2> list = new ArrayList<BaseFragment2>();
	private CustomViewPager viewpager;
	private View view;

	public HomeFragment2(){
		
	}
	
	public HomeFragment2(int pageIndex) {
		// TODO Auto-generated constructor stub
		super();
		this.pageIndex = pageIndex;
	}

	@Override
	public View initView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.frag_home, null);
		main_radio = (RadioGroup) view.findViewById(R.id.main_radio);
		viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		list.add(new FunctionPage2());
		list.add(new SmartPage2());
		list.add(new MePage2());

		viewpager.setAdapter(new ViewpagerAdapter(getFragmentManager()));
		viewpager.setCurrentItem(pageIndex, false);
		main_radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rb_function:
					viewpager.setCurrentItem(0,false);//pagerview自动切换到第0页
					break;
				case R.id.rb_news_center:
					viewpager.setCurrentItem(1,false);//pagerview自动切换到第1页
					
					break;

				case R.id.rb_smart_service:
					viewpager.setCurrentItem(2,false);//pagerview自动切换到第2页
					break;

				}
			}
		});
	}

	class ViewpagerAdapter extends FragmentStatePagerAdapter {

		public ViewpagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
