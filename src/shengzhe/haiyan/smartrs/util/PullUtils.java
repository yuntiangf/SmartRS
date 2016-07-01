package shengzhe.haiyan.smartrs.util;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.origamilabs.library.views.PullToRefreshStaggeredGridView;

public class PullUtils {

	public static void initGridView(PullToRefreshGridView pullToRefresh) {
		ILoadingLayout startLabels = pullToRefresh.getLoadingLayoutProxy(true,
				false);
		startLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		startLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ

		ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(false,
				true);
		endLabels.setPullLabel("���ظ���...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("���ڼ���...");// ˢ��ʱ
		endLabels.setReleaseLabel("�ſ�����...");// �����ﵽһ������ʱ����ʾ����ʾ
	}

	public static void initScrollView(
			PullToRefreshStaggeredGridView pullToRefresh) {
		ILoadingLayout startLabels = pullToRefresh.getLoadingLayoutProxy(true,
				false);
		startLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		startLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ

		ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(false,
				true);
		endLabels.setPullLabel("���ظ���...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("���ڼ���...");// ˢ��ʱ
		endLabels.setReleaseLabel("�ſ�����...");// �����ﵽһ������ʱ����ʾ����ʾ
	}

}
