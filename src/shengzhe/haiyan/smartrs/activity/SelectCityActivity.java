package shengzhe.haiyan.smartrs.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.model.City;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.City.CityList;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.GetAddress;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import shengzhe.haiyan.smartrs.view.wheel.ArrayWheelAdapter;
import shengzhe.haiyan.smartrs.view.wheel.OnWheelChangedListener;
import shengzhe.haiyan.smartrs.view.wheel.WheelView;

public class SelectCityActivity extends BaseActivity implements
		OnClickListener, OnWheelChangedListener {

	private TextView sure, cancel;
	private WheelView province, city, area;
	private List<CityList> cityList = new ArrayList<City.CityList>();
	private List<CityList> provinceList = new ArrayList<City.CityList>();

	private List<String> provinceName = new ArrayList<String>();
	private List<String> cityName = new ArrayList<String>();
	private List<String> areaName = new ArrayList<String>();
	private Intent intent = new Intent();
	private int uid = 0;

	private String areas = "";

	private String pId = "", cId = "", aId = "";

	/**
	 * 当前省的名称
	 */
	private String mCurrentProviceName = "";
	/**
	 * 当前市的名称
	 */
	private String mCurrentCityName = "";
	/**
	 * 当前区的名称
	 */
	private String mCurrentAreaName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_selectcity);

		AppManager.getAppManager().addActivity(this);

		findView();
		setData();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		sure = (TextView) findViewById(R.id.btn_sure);
		cancel = (TextView) findViewById(R.id.btn_cancel);
		province = (WheelView) findViewById(R.id.province);
		city = (WheelView) findViewById(R.id.cities);
		area = (WheelView) findViewById(R.id.areas);
		
	}

	private void setData() {
		// TODO Auto-generated method stub
		GetAddress.initialize(this);
		cityList = GetAddress.getCity();
		provinceList = GetAddress.getProvince();
		for (int i = 0; i < provinceList.size(); i++) {
			provinceName.add(provinceList.get(i).name);
		}
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		province.setAdapter(new ArrayWheelAdapter<String>(provinceName,
				provinceName.size()));
		province.addChangingListener(this);
		city.addChangingListener(this);
		area.addChangingListener(this);

		sure.setOnClickListener(this);
		cancel.setOnClickListener(this);

		province.setVisibleItems(5);
		city.setVisibleItems(5);
		area.setVisibleItems(5);
		updateCity();
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCity() {
		// TODO Auto-generated method stub
		int pCurrent = province.getCurrentItem();
		mCurrentProviceName = provinceName.get(pCurrent);

		for (int i = 0; i < provinceName.size(); i++) {
			if (mCurrentProviceName.equals(provinceList.get(i).name)) {
				pId = provinceList.get(i).id;
			}
		}

		for (int i = 0; i < cityList.size(); i++) {
			if (pId.equals(cityList.get(i).father_id)) {
				cityName.add(cityList.get(i).name);
			}
		}
		city.setAdapter(new ArrayWheelAdapter<String>(cityName, cityName.size()));
		city.setCurrentItem(0);
		areaName.clear();
		updateAreas();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		// TODO Auto-generated method stub
		int pCurrent = city.getCurrentItem();
		mCurrentCityName = cityName.get(pCurrent);
		for (int i = 0; i < cityList.size(); i++) {
			if (mCurrentCityName.equals(cityList.get(i).name)) {
				cId = cityList.get(i).id;
			}
		}
		for (int i = 0; i < cityList.size(); i++) {
			if (cId.equals(cityList.get(i).father_id)) {
				areaName.add(cityList.get(i).name);
			}
		}

		area.setAdapter(new ArrayWheelAdapter<String>(areaName, areaName.size()));
		area.setCurrentItem(0);
		if (areaName.size() > 0) {
			mCurrentAreaName = areaName.get(0);
		} else {
			mCurrentAreaName = "";
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sure:
			setPlace();
			break;

		default:
			finish();
			break;
		}
	}

	private void setPlace() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(mCurrentProviceName);
		sb.append("-");
		sb.append(mCurrentCityName);
		sb.append("-");
		sb.append(mCurrentAreaName);
		String rp = sb.toString();

		System.out.println("____________________" + rp);

		PreferenceUtil.initialize(SelectCityActivity.this);
		uid = PreferenceUtil.getUid();
		System.out.println("____________________" + uid);
		StringBuilder sb2 = new StringBuilder();
		sb2.append(ServerUrl.UPDATE_PLACE);
		sb2.append(ServerUrl.getEncodeName(rp));
		sb2.append("&&id=");
		sb2.append(uid);

		changePlace(sb2.toString());
	}

//	http://192.168.1.108:8080/XLJ2/UpdatePlaceById?place=
	private void changePlace(String url) {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				User user = QLParser.parse(responseInfo.result, User.class);
				if (user.status == 200) {
					ToastUtils.showToast(user.message, SelectCityActivity.this);
					SelectCityActivity.this.finish();
				} else if (user.status == 300) {
					ToastUtils.showToast(user.message, SelectCityActivity.this);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		switch (wheel.getId()) {
		case R.id.province:
			cityName.clear();
			areaName.clear();
			updateCity();
			break;
		case R.id.cities:
			areaName.clear();
			updateAreas();
			break;
		case R.id.areas:
			mCurrentAreaName = areaName.get(newValue);
			for (int i = 0; i < cityList.size(); i++) {
				if (mCurrentAreaName.equals(cityList.get(i).name)) {
					aId = cityList.get(i).id;
				}
			}
			break;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}
}
