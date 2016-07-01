package shengzhe.haiyan.smartrs.activity;

import java.util.Map;
import java.util.Random;


import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.view.KeywordsFlow;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchActivity extends Activity implements OnClickListener,
		OnTouchListener, OnGestureListener {

	private TextView title, hot_tag;
//	private EditText tag_name;
//	private Button tag_button;
	private ImageView back;
	private KeywordsFlow keywordsFlow;
	private Intent intent = new Intent();
	private static final int FLING_MIN_DISTANCE = 100;
	private static final int FLING_MIN_VELOCITY = 80;
	private GestureDetector gDetector;
	private int currentPage = 1;
	private String[] keywords = new String[] { "冰川雪原", "博物馆", "草原草甸", "城市观光",
			"古镇村落","海岛风情", "极限酷旅", "江河瀑布", "湿地湖泊", "森林公园" , "人文景观",
			"野生动物园","主题公园","自然保护区", "娱乐与夜生活","宗教旅游"};
	
	private ListTask task = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_search);

		findView();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		hot_tag = (TextView) findViewById(R.id.hot_tag);
//		tag_name = (EditText) findViewById(R.id.tag_name);
//		tag_button = (Button) findViewById(R.id.tag_button);
		back = (ImageView) findViewById(R.id.back);
		keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);
//		tag_button.setOnClickListener(this);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		title.setText("景点搜索");
		hot_tag.setText("热门标签：");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		keywordsFlow.setDuration(800l);
		keywordsFlow.setOnItemClickListener(this);
		keywordsFlow.setOnTouchListener(this);
		keywordsFlow.setFocusable(true);
		keywordsFlow.setClickable(true);
		gDetector = new GestureDetector(this);

	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			showRightOut();
		}
		if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			showLeftIn();
		}

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return gDetector.onTouchEvent(event);
	}

	private void showLeftIn() {
		// TODO Auto-generated method stub
		if (currentPage > 1) {
			AddItemToContainer(--currentPage, 1);
		} else {
			currentPage = 1;
			AddItemToContainer(currentPage, 1);
		}
	}

	private void showRightOut() {
		// TODO Auto-generated method stub
		if (keywords.length > 0) {
			AddItemToContainer(++currentPage, 2);
		} else {
			AddItemToContainer(currentPage, 2);
		}
	}

	@SuppressWarnings("unchecked")
	private void AddItemToContainer(int pageindex, int type) {
		// TODO Auto-generated method stub

		if(task.getStatus() != Status.RUNNING){
			task = new ListTask(type);
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
	}


	class ListTask extends AsyncTask<Map<String, Object>, Integer, String[]> {

		private int mType = 1;

		public ListTask(int type) {
			super();
			mType = type;
		}

		@Override
		protected String[] doInBackground(Map<String, Object>... params) {
			// TODO Auto-generated method stub
			return keywords;
		}

		@Override
		protected void onPostExecute(String[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result == null)
				return;
			if (keywords.length > 0) {
				if (mType == 1) {
					keywordsFlow.rubKeywords();
					feedKeywordsFlow(keywordsFlow, keywords);
					keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
				} else {
					keywordsFlow.rubKeywords();
					feedKeywordsFlow(keywordsFlow, keywords);
					keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
				}
			}
		}

	}

	private void feedKeywordsFlow(KeywordsFlow keywordsFlows, String[] keyws) {
		// TODO Auto-generated method stub
		Random random = new Random();
		for (int i = 0; i < keyws.length; i++) {
			int ran = random.nextInt(keyws.length);
			String tmp = keyws[i];
			keywordsFlows.feedKeyword(tmp);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		if (v == back) {
			finish();
		} else if (v instanceof TextView) {
			String keyword = ((TextView) v).getText().toString();
			bundle.putString("tag", keyword);
		}
		
//		if(v.getId() == R.id.tag_button){
//			String tag = tag_name.getText().toString();
//			bundle.putString("tag", tag);
//			tag_name.setText("");
//		}
		intent.putExtras(bundle);
		intent.setClass(this, TagSearchActivity.class);
		startActivity(intent);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		task = new ListTask(1);
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

	}
}
