package shengzhe.haiyan.smartrs.activity;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.origamilabs.library.views.PullToRefreshStaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnLoadmoreListener;

import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.adapter.MenuAdapter;
import shengzhe.haiyan.smartrs.model.Menu;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.Menu.MenuList;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.util.PullUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class AllMenuActivity extends BaseActivity implements OnClickListener {

	private TextView title_text;
	private ImageView back;
	private PullToRefreshStaggeredGridView staggeredGridView;
	private StaggeredGridView mGridView;
	private MenuAdapter adapter;
	private int currentPage = 0;

	private List<MenuList> listData = new ArrayList<Menu.MenuList>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_allmenu);

		findView();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		title_text = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		
		staggeredGridView = (PullToRefreshStaggeredGridView) findViewById(R.id.staggeredGridView);
		staggeredGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
		PullUtils.initScrollView(staggeredGridView);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		title_text.setText("ËùÓÐ¾°µã");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		adapter = new MenuAdapter(AllMenuActivity.this, listData);
		mGridView = staggeredGridView.getRefreshableView();
		mGridView.setSelector(R.drawable.background);
		mGridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		staggeredGridView
				.setOnRefreshListener(new OnRefreshListener<StaggeredGridView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<StaggeredGridView> refreshView) {
						// TODO Auto-generated method stub
						StringBuilder sb = new StringBuilder();
						sb.append(ServerUrl.GET_ALL_MENU);
						sb.append(currentPage);
						String loadurl = sb.toString();
						getMenuResult(loadurl, true);
					}
				});
		
		staggeredGridView.setOnLoadmoreListener(new OnLoadmoreListener() {
			
			@Override
			public void onLoadmore() {
				// TODO Auto-generated method stub
				currentPage += 10;
				StringBuilder sb = new StringBuilder();
				sb.append(ServerUrl.GET_ALL_MENU);
				sb.append(currentPage);
				String loadurl = sb.toString();
				getMenuResult(loadurl, false);
			}
		});
	}

	protected void getMenuResult(String loadurl, final boolean isRefresh) {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, loadurl, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressData(isRefresh, responseInfo.result);
				
				System.out.println("1111111111111111"+responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				System.out.println("dddddddddddd");
			}
		});
	}

	protected void progressData(boolean isRefresh, String result) {
		// TODO Auto-generated method stub
		System.out.println("ssssssssssss" + result);

		Menu menu = QLParser.parse(result, Menu.class);

		if (menu.result == null) {
			return;
		}

		listData.addAll(menu.result);

		if (menu.result.size() > 0) {
			if (isRefresh) {
				listData.clear();
				listData.addAll(menu.result);
				System.out.println("list-->"+menu);
				adapter.notifyDataSetChanged();
				staggeredGridView.onRefreshComplete();
			} else {
				adapter.addItemLast(menu.result);
				adapter.notifyDataSetChanged();
			}
		} else {
			ToastUtils.showToast(
					getResources().getString(R.string.toast_not_more),
					AllMenuActivity.this);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.GET_ALL_MENU);
		sb.append(currentPage);
		String loadurl = sb.toString();
		System.out.println("uuuuuuuuuuuuuuu"+loadurl);
		getMenuResult(loadurl, true);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;

		default:
			break;
		}
	}
	
	
}
