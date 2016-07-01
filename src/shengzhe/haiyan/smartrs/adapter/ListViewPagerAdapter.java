package shengzhe.haiyan.smartrs.adapter;

import java.util.ArrayList;
import java.util.List;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.habitusdetection.DTTest.MyAadapter;
import shengzhe.haiyan.smartrs.habitusdetection.TestActivity.DetectionAdapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewPagerAdapter extends PagerAdapter implements OnItemClickListener{
	Context mContext;
	private int mChildCount = 0;
	List<View> mListViewPager = new ArrayList<View>(); // ViewPager对象的内容
	List<String> list = new ArrayList<String>();
	List<List<String>> lcontant = null;
	int pageNum = 1;
//	CustomIndicator mCustomIndicator;
	int pageRows=10;

	/**
	 * 
	 * @param context 活动窗体
	 * @param kf 数据
	 * @param customIndicator 圆点控件
	 * @param rows 每页显示多少条数据
	 */ 
	public ListViewPagerAdapter(final Context context, List<String> kf/*,CustomIndicator customIndicator*/,int rows) {
//		this.mCustomIndicator=customIndicator;
		this.pageRows=rows;
		int count = 0;  //循环次数
		int pos = 0;		//当前位置
		
		this.mContext = context;
		this.list = kf;
		//计算页数
		pageNum = (int) Math.ceil(list.size() / pageRows);
		int a = list.size() % pageRows;
		if (a>0) {
			pageNum=pageNum+1;
		} 
		System.out.println("pageRows-->"+pageRows);
//		mCustomIndicator.setCount(pageNum);
		System.out.println("hx2-->"+pageNum);
		if (Math.ceil(kf.size() / pageRows) == 0) {
			pageNum = 1;
		}
		lcontant = new ArrayList<List<String>>();
		for (int i = 0; i < pageNum; i++) {
			Log.d("hx2", String.valueOf(i));
			List<String> item = new ArrayList<String>();
			for(int k = pos;k<kf.size();k++){
				count++;
				pos = k;
				item.add(kf.get(k));
				//每个List10条记录，存满N个跳出
				if(count == pageRows){
					count = 0;
					pos = pos+1;
					break;
				}
			}
			lcontant.add(item);
		}
		
		for (int j = 0; j < pageNum; j++) {
			View viewPager = LayoutInflater.from(mContext).inflate(
					R.layout.list, null);
			ListView mList = (ListView) viewPager.findViewById(R.id.ques_list);
			
//			final DetectionAdapter adapter = new DetectionAdapter(mContext,lcontant.get(j));
			final MyAadapter adapter= new MyAadapter(mContext,lcontant.get(j),j);
			mList.setAdapter(adapter);
			mListViewPager.add(viewPager);
			mList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//			mList.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view,
//						int position, long id) {
//					// TODO Auto-generated method stub
//					System.out.println("position-->"+position);
//					adapter.setSelectItem(position);
//				}
//			});
		}

	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getCount() {
		return mListViewPager.size();
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(mListViewPager.get(position));
		return mListViewPager.get(position);

	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

	@Override
	public void destroyItem(View container, int position, Object arg2) {
		ViewPager pViewPager = ((ViewPager) container);
		pViewPager.removeView(mListViewPager.get(position));
	}

	@Override
	public void finishUpdate(View arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		mChildCount = getCount();
		super.notifyDataSetChanged();
	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		if(mChildCount > 0 ){
			mChildCount --;
			return POSITION_NONE;
		}
		return super.getItemPosition(object);
	}
}
