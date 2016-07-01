package shengzhe.haiyan.smartrs.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.adapter.FoodtypeAdapter;
import shengzhe.haiyan.smartrs.adapter.MenuByTypeAdapter;
import shengzhe.haiyan.smartrs.adapter.TypesAdapter;
import shengzhe.haiyan.smartrs.model.Foodtype;
import shengzhe.haiyan.smartrs.model.Foodtype.FoodtypeList;
import shengzhe.haiyan.smartrs.model.Menu.MenuList;
import shengzhe.haiyan.smartrs.model.Menu;
import shengzhe.haiyan.smartrs.model.Type.TypeList;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.Type;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuPartActivity extends BaseActivity implements OnClickListener {

	private ImageView back;
	private TextView title_text;
	private EditText search_name;
	private Button search_button;
	private ListView left_lv;
	private ListView center_lv;
	private ListView right_lv;

	private FoodtypeAdapter foodtypeAdapter;
	private TypesAdapter typeAdapter;
	private MenuByTypeAdapter menuByTypeAdapter;

	private List<Foodtype.FoodtypeList> foodtypeData = new ArrayList<Foodtype.FoodtypeList>();
	private List<Type.TypeList> typeData = new ArrayList<Type.TypeList>();
	private List<Menu.MenuList> menuData = new ArrayList<Menu.MenuList>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_part);

		findView();
		setEvent();
	}

	private void findView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		title_text = (TextView) findViewById(R.id.title_text);
		search_name = (EditText) findViewById(R.id.et_menu_name);
		search_button = (Button) findViewById(R.id.btn_search);
		left_lv = (ListView) findViewById(R.id.left_list);
		center_lv = (ListView) findViewById(R.id.center_list);
		right_lv = (ListView) findViewById(R.id.right_list);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
		title_text.setText("¾°µã·ÖÀà");

		search_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.btn_search:
			String str = search_name.getText().toString();
			System.out.println("str-->"+str);
//			GetMenuByLikeName(str);
			Intent intent = new Intent(MenuPartActivity.this,GetMenuByLikeNameActivity.class);
			intent.putExtra("likename", str);
			startActivity(intent);
			search_name.setText("");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getAllFoodtype(ServerUrl.GET_ALL_PROVINCE);
	}

	private void getAllFoodtype(String url) {
		// TODO Auto-generated method stub
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressFoodtype(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void progressFoodtype(String result) {
		// TODO Auto-generated method stub
		Foodtype foodtype = QLParser.parse(result, Foodtype.class);

		if (foodtype.result == null) {
			return;
		}
		if(foodtypeData.size()==0){
			foodtypeData.addAll(foodtype.result);
		}
		if (foodtype.result.size() > 0) {
			foodtypeAdapter = new FoodtypeAdapter(foodtypeData, this);
			left_lv.setAdapter(foodtypeAdapter);
			left_lv.setOnItemClickListener(leftItemListener);
			getTypeByFatherpoint(ServerUrl.GET_TYPE_BY_FATHERPOINT, 
					foodtypeData.get(0).province_id);
		}
	}

	private OnItemClickListener leftItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			// TODO Auto-generated method stub

			FoodtypeList ft = foodtypeData.get(arg2);

			System.out.println("ssssssssss" + ft.province_id);
			int point = ft.province_id;

			getTypeByFatherpoint(ServerUrl.GET_TYPE_BY_FATHERPOINT, point);

			foodtypeAdapter.setCurrentPos(arg2);
			foodtypeAdapter.notifyDataSetChanged();

		}
	};

	protected void getTypeByFatherpoint(String loadurl, int point) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		sb.append(loadurl);
		sb.append(point);

		String uri = sb.toString();

		loadData(HttpMethod.GET, uri, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressType(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void progressType(String result) {
		// TODO Auto-generated method stub
		Type type = QLParser.parse(result, Type.class);

		if (type.result == null) {
			return;
		}
		if (typeData.size() > 0) {
			typeData.clear();
			typeData.addAll(type.result);
		} else {
			typeData.addAll(type.result);
		}

		if (type.result.size() > 0) {
			typeAdapter = new TypesAdapter(this, typeData);
			center_lv.setAdapter(typeAdapter);
			center_lv.setOnItemClickListener(centerItemListener);
			getMenuByMenutype(ServerUrl.GET_MENU_BY_AREAID, typeData.get(0).area_id);
		}
	}

	private OnItemClickListener centerItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			TypeList t = typeData.get(arg2);

			int menutype = t.area_id;

			getMenuByMenutype(ServerUrl.GET_MENU_BY_AREAID, menutype);

			typeAdapter.setCurrentPos(arg2);
			typeAdapter.notifyDataSetChanged();
		}
	};

	protected void getMenuByMenutype(String loadurl, int menutype) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(loadurl);
		sb.append(menutype);

		String uri = sb.toString();

		loadData(HttpMethod.GET, uri, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				progressMenuByType(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void progressMenuByType(String result) {
		// TODO Auto-generated method stub
		Menu menu = QLParser.parse(result, Menu.class);

		if (menu.result == null) {
			return;
		}

		if (menuData.size() > 0) {
			menuData.clear();
			menuData.addAll(menu.result);
		} else {
			menuData.addAll(menu.result);
		}
		
		if(menu.result.size()>0){
			menuByTypeAdapter = new MenuByTypeAdapter(this, menuData);
			right_lv.setAdapter(menuByTypeAdapter);
			right_lv.setOnItemClickListener(rightItemListener);
		}
	}
	
	private OnItemClickListener rightItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			MenuList ml = menuData.get(arg2);
			Intent intent = new Intent();
			intent.setClass(MenuPartActivity.this, MenuDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("menuId", ml.id);
			bundle.putString("menuName", ml.scenic_name);
			bundle.putString("menuProgress", ml.introduce);
			bundle.putString("menuCharacter", ml.opentime);
			bundle.putString("menuTip", ml.price);
			bundle.putString("menuHabitus", ml.phone);
			bundle.putString("menuSeasoning", ml.besttime);
			bundle.putString("menuIngredient", ml.traffic);
			bundle.putString("menuPicurl", ml.picurl);
			bundle.putString("menuMpoint", ml.complain_phone);
			intent.putExtras(bundle);
			MenuPartActivity.this.startActivity(intent);
		}
	};
}
