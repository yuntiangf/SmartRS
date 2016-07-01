package shengzhe.haiyan.smartrs.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import shengzhe.haiyan.smartrs.model.Menu.MenuList;
import shengzhe.haiyan.smartrs.util.PullUtils;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author:hj
 * @date:2016-5-28 下午2:23:22w		
 */
public class GetMenuByLikeNameActivity extends BaseActivity implements OnClickListener {

	private TextView title_text;
	private ImageView back;
	private PullToRefreshStaggeredGridView staggeredGridView;
	private StaggeredGridView mGridView;
	private MenuAdapter adapter;
	private int currentPage = 0;

	private List<MenuList> listData = new ArrayList<Menu.MenuList>();

	private String likename;
	private String enLikename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.search_result);

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
		likename = getIntent().getExtras().getString("likename");
		if (likename.equals("汤类")) {

			enLikename = getEncodeName("汤");
		} else if (likename.equals("粥类")) {
			enLikename = getEncodeName("粥");
		} else if (likename.equals("面类")) {
			enLikename = getEncodeName("面");
		} else if (likename.equals("果品")) {
			enLikename = getEncodeName("果");
		} else if (likename.equals("奶油食品")) {
			enLikename = getEncodeName("奶油");
		} else {
			enLikename = getEncodeName(likename);
		}

		title_text.setText(likename);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);

		adapter = new MenuAdapter(GetMenuByLikeNameActivity.this, listData);
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
						sb.append(ServerUrl.GET_MENU_BY_LIKE_NAME);
//						sb.append("?");
//						sb.append("likename=");
						sb.append(enLikename);
						sb.append("&&");
						sb.append("page=");
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
				sb.append(ServerUrl.GET_MENU_BY_LIKE_NAME);
//				sb.append("?");
//				sb.append("likename=");
				sb.append(enLikename);
				sb.append("&&");
				sb.append("page=");
				sb.append(currentPage);
				String loadurl = sb.toString();
				getMenuResult(loadurl, false);
			}
		});
	}

	private String getEncodeName(String n) {
		// TODO Auto-generated method stub
		String name = null;
		try {
			name = URLEncoder.encode(n, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	protected void getMenuResult(String loadurl, final boolean isRefresh) {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, loadurl, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressData(isRefresh, responseInfo.result);

				System.out.println("1111111111111111" + responseInfo.result);
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
		Menu menu = QLParser.parse(result, Menu.class);

		if (menu.result == null) {
			return;
		}

		listData.addAll(menu.result);

		if (menu.result.size() > 0) {
			if (isRefresh) {
				listData.clear();
				listData.addAll(menu.result);
				adapter.notifyDataSetChanged();
				staggeredGridView.onRefreshComplete();
			} else {
				adapter.addItemLast(menu.result);
				adapter.notifyDataSetChanged();
			}
		} else {
			ToastUtils.showToast(
					getResources().getString(R.string.toast_not_more),
					GetMenuByLikeNameActivity.this);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.GET_MENU_BY_LIKE_NAME);
//		sb.append("?");
//		sb.append("likename=");
		sb.append(enLikename);
		sb.append("&&");
		sb.append("page=");
		sb.append(currentPage);
		String loadurl = sb.toString();
		getMenuResult(loadurl, true);
	}
}
