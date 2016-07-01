package shengzhe.haiyan.smartrs.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{

	public View view;
	public Context ct;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ct = getActivity();
	}
	
	/**
	 * setContentView;
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = initView(inflater);

		return view;
	}
	
	/**
	 * ��ʼ��view
	 */
	public abstract View initView(LayoutInflater inflater);

	/**
	 * ��ʼ������
	 */
	public abstract void initData(Bundle savedInstanceState);
}
