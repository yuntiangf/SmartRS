package shengzhe.haiyan.smartrs.base.fragement;

import shengzhe.haiyan.smartrs.MainActivity;
import shengzhe.haiyan.smartrs.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment3_start extends Fragment {

	private ImageView imageview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment3_start, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		imageview = (ImageView) getView().findViewById(R.id.into_btn);

		imageview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 保存数据到SharePreference中以此判断是不是第一次登陆
				// getActivity().finish();
				FragmentActivity mActivity=getActivity();
				Intent intent = new Intent();
				//intent.setClass(getActivity(), WelcomeActivity.class);
				intent.setClass(getActivity(), MainActivity.class);
				mActivity.startActivity(intent);
				mActivity.finish();
			}
		});
	}

}
