package shengzhe.haiyan.smartrs.base.fragement;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.origamilabs.library.views.PullToRefreshStaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnLoadmoreListener;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.activity.AllMenuActivity;
import shengzhe.haiyan.smartrs.adapter.MenuAdapter;
import shengzhe.haiyan.smartrs.base.BaseFragment2;
import shengzhe.haiyan.smartrs.model.Menu;
import shengzhe.haiyan.smartrs.model.Menu.MenuList;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import shengzhe.haiyan.smartrs.util.PullUtils;

//推荐列表
public class SmartPage2 extends BaseFragment2 {

	//标题
	@ViewInject(R.id.title_text)
	private TextView title_text;
	
	@ViewInject(R.id.staggeredGridView)
	private PullToRefreshStaggeredGridView staggeredGridView;
	
	@ViewInject(R.id.layout01)
	private LinearLayout layout01;
	@ViewInject(R.id.layout02)
	private LinearLayout layout02;
	@ViewInject(R.id.loginstatus)
	private TextView loginstatus;
	private MenuAdapter adapter;
	private StaggeredGridView mGridView;

	private int uid=0, status;
	private String habitus = "";
	
	//存放推荐菜品
	private List<MenuList> listData = new ArrayList<Menu.MenuList>();

	@Override
	public View initView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.page_smart, null);

		ViewUtils.inject(this, view);

		return view;
	}

	//初始化界面
	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title_text.setText("推荐列表");
		staggeredGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
		PullUtils.initScrollView(staggeredGridView);

		//从本地获取用户id和体质
		PreferenceUtil.initialize(getActivity());
		status = PreferenceUtil.getUserStatus();
		uid = PreferenceUtil.getUid();
		habitus = PreferenceUtil.getHabitus();
		
		String name = PreferenceUtil.getUserName();
		System.out.println("uid-->"+uid+",habitus-->"+habitus+",name-->"+name);
		System.out.println("loginstatus-->"+status);
		//判断登陆状态
		if(uid == 0){
			loginstatus.setText("您还未注册，请先注册");
		}else if(status == 0){
			loginstatus.setText("您还未登陆，请先登陆");
			listData.clear();
		}else{//判断有没有进行体质检测，若没有，则按照“平和质”给出推荐结果
			if(habitus == null || habitus.equals("")){
				ToastUtils.showToast("您还未记录喜好的景点类型，请先进行问卷调查，默认类型为自然景观！", getActivity());
				habitus = "自然景观";
			}
			getRecommendMenu();
		}
			
		
		if (listData.size() > 0) {
			layout01.setVisibility(View.VISIBLE);
			layout02.setVisibility(View.GONE);
		} else {
			layout02.setVisibility(View.VISIBLE);
			layout01.setVisibility(View.GONE);
		}

		adapter = new MenuAdapter(getActivity(), listData);
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
						getRecommendMenu();
					}
				});

		staggeredGridView.setOnLoadmoreListener(new OnLoadmoreListener() {

			@Override
			public void onLoadmore() {

			}
		});
	}

	private void getRecommendMenu() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.QUERY_RECOMMEND_MENU);
		sb.append(ServerUrl.getEncodeName(habitus));
		sb.append("&&");
		sb.append("user_id=");
		sb.append(uid);

		String loadurl = sb.toString();
		System.out.println("loadurl-->aaaaaaaaa--->"+loadurl);
		//通过接口访问服务器，获取推荐景点
		loadData(HttpMethod.GET, loadurl, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressData(responseInfo.result);

			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
			}
		});
	}

	protected void progressData(String result) {
		// TODO Auto-generated method stub
		Menu menu = QLParser.parse(result, Menu.class);
		System.out.println("result-->"+menu.result);
		//添加返回的菜单
		if(listData.size() == 0){
			listData.addAll(menu.result);
		}else{
			listData.clear();
			listData.addAll(menu.result);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
