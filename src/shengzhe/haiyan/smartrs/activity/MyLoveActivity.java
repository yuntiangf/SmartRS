package shengzhe.haiyan.smartrs.activity;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
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
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.model.Menu.MenuList;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import shengzhe.haiyan.smartrs.util.PullUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyLoveActivity extends BaseActivity implements OnClickListener {

	private TextView title_text;
	private ImageView back;
	private PullToRefreshStaggeredGridView staggeredGridView;
	private StaggeredGridView mGridView;
	private MenuAdapter adapter;
	private int currentPage = 0;
	private TextView loginstatus;
	private LinearLayout layout01, layout02;

	private int uid = 0;
	private int status = 0;

	private List<MenuList> listData = new ArrayList<Menu.MenuList>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mylovemenu);
		AppManager.getAppManager().addActivity(this);

		PreferenceUtil.initialize(MyLoveActivity.this);
		status = PreferenceUtil.getUserStatus();

		findView();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		title_text = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);

		layout01 = (LinearLayout) findViewById(R.id.layout01);
		layout02 = (LinearLayout) findViewById(R.id.layout02);
		loginstatus = (TextView) findViewById(R.id.loginstatus);
		loginstatus.setOnClickListener(this);
		staggeredGridView = (PullToRefreshStaggeredGridView) findViewById(R.id.staggeredGridView);
		staggeredGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
		PullUtils.initScrollView(staggeredGridView);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		title_text.setText("我关注的景点");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		adapter = new MenuAdapter(MyLoveActivity.this, listData);
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
						if (status == 1) {
							uid = PreferenceUtil.getUid();
							StringBuilder sb = new StringBuilder();
							sb.append(ServerUrl.GET_LOVEMENU_BY_ID);
							sb.append(uid);

							getMenuResult(sb.toString());
						}
					}
				});
		staggeredGridView.setOnLoadmoreListener(new OnLoadmoreListener() {

			@Override
			public void onLoadmore() {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void getMenuResult(String loadurl) {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, loadurl, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				Menu menu = QLParser.parse(responseInfo.result, Menu.class);
				if (menu.result == null) {
					return;
				}

				if (menu.result.size() > 0) {
					listData.clear();
					listData.addAll(menu.result);
					adapter.notifyDataSetChanged();
					staggeredGridView.onRefreshComplete();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.loginstatus:
			Intent intent = new Intent();
			intent.setClass(MyLoveActivity.this, LoginActivity.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (status == 0) {
			layout01.setVisibility(View.GONE);
			layout02.setVisibility(View.VISIBLE);
		} else if (status == 1) {
			uid = PreferenceUtil.getUid();
			layout02.setVisibility(View.GONE);
			layout01.setVisibility(View.VISIBLE);
			StringBuilder sb = new StringBuilder();
			sb.append(ServerUrl.GET_LOVEMENU_BY_ID);
			sb.append(uid);

			getMenuResult(sb.toString());
		}
	}
}
