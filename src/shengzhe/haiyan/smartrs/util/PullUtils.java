package shengzhe.haiyan.smartrs.util;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.origamilabs.library.views.PullToRefreshStaggeredGridView;

public class PullUtils {

	public static void initGridView(PullToRefreshGridView pullToRefresh) {
		ILoadingLayout startLabels = pullToRefresh.getLoadingLayoutProxy(true,
				false);
		startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("正在载入...");// 刷新时
		startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

		ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(false,
				true);
		endLabels.setPullLabel("加载更多...");// 刚上拉时，显示的提示
		endLabels.setRefreshingLabel("正在加载...");// 刷新时
		endLabels.setReleaseLabel("放开加载...");// 下来达到一定距离时，显示的提示
	}

	public static void initScrollView(
			PullToRefreshStaggeredGridView pullToRefresh) {
		ILoadingLayout startLabels = pullToRefresh.getLoadingLayoutProxy(true,
				false);
		startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("正在载入...");// 刷新时
		startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

		ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(false,
				true);
		endLabels.setPullLabel("加载更多...");// 刚上拉时，显示的提示
		endLabels.setRefreshingLabel("正在加载...");// 刷新时
		endLabels.setReleaseLabel("放开加载...");// 下来达到一定距离时，显示的提示
	}

}
