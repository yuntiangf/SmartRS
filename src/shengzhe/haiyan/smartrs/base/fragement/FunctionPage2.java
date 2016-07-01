package shengzhe.haiyan.smartrs.base.fragement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.activity.AllMenuActivity;
import shengzhe.haiyan.smartrs.activity.CaptureActivity;
import shengzhe.haiyan.smartrs.activity.GuideActivity;
import shengzhe.haiyan.smartrs.activity.MenuPartActivity;
import shengzhe.haiyan.smartrs.activity.SearchActivity;
import shengzhe.haiyan.smartrs.adapter.GridViewAdapter;
import shengzhe.haiyan.smartrs.adapter.ImgAdapter;
import shengzhe.haiyan.smartrs.base.BaseFragment2;
import shengzhe.haiyan.smartrs.chat.ChatList;
import shengzhe.haiyan.smartrs.habitusdetection.TestActivity;
import shengzhe.haiyan.smartrs.view.MyGallery;

//首页
public class FunctionPage2 extends BaseFragment2 {

	@ViewInject(R.id.gridview)
	private GridView gridView;
	@ViewInject(R.id.dot_0)
	private View dot_0;
	@ViewInject(R.id.dot_1)
	private View dot_1;
	@ViewInject(R.id.dot_2)
	private View dot_2;
	@ViewInject(R.id.dot_3)
	private View dot_3;
	@ViewInject(R.id.dot_4)
	private View dot_4;

	@ViewInject(R.id.dot_title)
	private TextView dot_title;

	@ViewInject(R.id.title_text)
	private TextView title_text;
	@ViewInject(R.id.scan)
	private Button scan;
	@ViewInject(R.id.gallery)
	private MyGallery gallery = null;
	private ArrayList<Integer> imgList;
	ArrayList<View> dots;

	private int imageIds[];
	private String[] titles;
	private int currentItem;
	
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	private int oldPosition = 0;
	private ScheduledExecutorService scheduledExecutorService;
	
	Intent intent = new Intent();

	@Override
	public View initView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.page_function, null);

		ViewUtils.inject(this, view);

		gallery = (MyGallery) view.findViewById(R.id.gallery);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		imageIds = new int[] { R.drawable.image1, R.drawable.image2,
				R.drawable.image3, R.drawable.image4, R.drawable.image5 };
//
		titles = new String[] { "呼伦贝尔大草原", "九寨沟", "丽江古城", "烟雨乌镇", "秀美黄山" };

//		imageIds = new int[] { R.drawable.qililaonong,R.drawable.gongfuji,
//				R.drawable.liangtouwu};
//		titles = new String[] {"七里农场","功夫鸡","两头乌"};
		imgList = new ArrayList<Integer>();
		for (int i = 0; i < titles.length; i++) {
			imgList.add(imageIds[i]);
		}

		dots = new ArrayList<View>();
		dots.add(dot_0);
		dots.add(dot_1);
		dots.add(dot_2);
		dots.add(dot_3);
		dots.add(dot_4);

		dot_title.setText(titles[0]);
		title_text.setText("首页");
		scan.setVisibility(View.VISIBLE);
		
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCameraIntent =new Intent(getActivity(),CaptureActivity.class);
//				Intent openCameraIntent =new Intent(getActivity(),com.zbar.lib.CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
		
		gallery.setAdapter(new ImgAdapter(getActivity(), imgList));
		gallery.setFocusable(true);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				position = position % imgList.size();
				dot_title.setText(titles[position]);
				dots.get(oldPosition).setBackgroundResource(
						R.drawable.dot_normal);
				dots.get(position)
						.setBackgroundResource(R.drawable.dot_focused);
				oldPosition = position;
				currentItem = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		initGridView();
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
					System.out.println(scanResult);
					Dialog dialog = new AlertDialog.Builder(getActivity())
							.setIcon(R.drawable.saoyisao).setTitle("扫描结果：")
							.setView(myInflater).create();
					dialog.show();

				}
			}
	 
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

		// 每隔2秒钟切换一张图片
		scheduledExecutorService.scheduleWithFixedDelay(new GalleryTask(), 3,
				3, TimeUnit.SECONDS);
	}

	// 切换图片
	private class GalleryTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			currentItem = (currentItem + 1) % imageIds.length;
			// 更新界面
			handler.obtainMessage().sendToTarget();
		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// 设置当前页面
			gallery.setSelection(currentItem);
		}

	};
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		scheduledExecutorService.shutdown();
	}
	
	private void initGridView() {
		// TODO Auto-generated method stub

		// 所有景点 景点分类 景点搜索 景点服务 旅游指南 
		for (int i = 0; i < 6; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			switch (i) {
			case 0:
				map.put("icon", R.drawable.icon_suoyou);
				map.put("iacon_name", "所有景点");
				break;
			case 1:
				map.put("icon", R.drawable.icon_fenlei);
				map.put("iacon_name", "景点分类");
				break;
			case 2:
				map.put("icon", R.drawable.icon_sousuo2);
				map.put("iacon_name", "景点搜索");
				break;
			case 3:
				map.put("icon", R.drawable.icon_fuwu);
				map.put("iacon_name", "天气查询");
				break;
			case 4:
				map.put("icon", R.drawable.icon_zhinan);
				map.put("iacon_name", "路线指南");
				break;
			case 5:
				map.put("icon", R.drawable.icon_more);
				map.put("iacon_name", "更多");
				break;
			}

			if (data.size() < 6) {
				data.add(map);
			}

		}

		GridViewAdapter gridViewApter = new GridViewAdapter(getActivity(), data);
		gridView.setAdapter(gridViewApter);
		gridView.setOnItemClickListener(new ItemClickListener());
	}

	class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			switch (arg2) {
			case 0:
				//所有景点
				intent.setClass(getActivity(), AllMenuActivity.class);
				startActivity(intent);
				break;
			case 1:
				//景点分类
				intent.setClass(getActivity(), MenuPartActivity.class);
				startActivity(intent);
				break;
			case 2:
				//景点搜索
				intent.setClass(getActivity(), SearchActivity.class);
				startActivity(intent);
				break;
			case 3:
				//景点服务
//				intent.setClass(getActivity(), ChatList.class);
//				startActivity(intent);
				break;
			case 4:
				//旅游指南
//				intent.setClass(getActivity(), GuideActivity.class);
//				startActivity(intent);
				break;
			case 5:

				break;
			}
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
