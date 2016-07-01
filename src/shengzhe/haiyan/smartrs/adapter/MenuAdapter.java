package shengzhe.haiyan.smartrs.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.activity.MenuDetailActivity;
import shengzhe.haiyan.smartrs.model.FoodList;
import shengzhe.haiyan.smartrs.model.Menu;
import shengzhe.haiyan.smartrs.model.FoodList.FoodListResult;
import shengzhe.haiyan.smartrs.model.Menu.MenuList;

import android.R.menu;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {

	private Context context;
	private List<Menu.MenuList> listData;
	private MenuList menuResult;
	BitmapUtils bitmapUtil;
	
	public MenuAdapter(Context context, List<MenuList> listData) {
		super();
		this.context = context;
		this.listData = listData;
		bitmapUtil = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void addItemLast(List<MenuList> datas){
		listData.addAll(datas);
	}
	
	public void addItemTop(List<MenuList> datas){
		
		for (MenuList info : datas) {
			listData.add(info);
		}
	}
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder holder = null;
		
		menuResult = listData.get(position);
		if(convertView == null){
			view = LayoutInflater.from(context).inflate(R.layout.item_gridview_menu, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}else{
			view = convertView;
		}
		
		holder = (ViewHolder) view.getTag();
		holder.layout_item.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(context, MenuDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("menuId", listData.get(position).id);
				bundle.putString("menuName", listData.get(position).scenic_name);
				bundle.putString("menuProgress", listData.get(position).introduce);
				bundle.putString("menuCharacter", listData.get(position).opentime);
				bundle.putString("menuTip", listData.get(position).price);
				bundle.putString("menuHabitus", listData.get(position).phone);
				bundle.putString("menuSeasoning", listData.get(position).besttime);
				bundle.putString("menuIngredient", listData.get(position).traffic);
				bundle.putString("menuPicurl", listData.get(position).picurl);
				bundle.putString("menuMpoint", listData.get(position).complain_phone);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		holder.product_title.setText(menuResult.scenic_name);
//		holder.product_pic.setImageResource(R.drawable.donggua);
		if(menuResult.picurl != null){
			StringBuilder sb = new StringBuilder();
			sb.append(ServerUrl.SERVER_ROOT_PATH);
//			http://192.168.1.108:8080/Go/imgs/100031.jpg
			sb.append("imgs/");
			sb.append(menuResult.picurl);
			String uri = sb.toString();
			bitmapUtil.display(holder.product_pic, uri);
		}else{
			holder.product_pic.setImageResource(R.drawable.tiananmen);
		}
		
		return view;
	}
	
	class ViewHolder{
		RelativeLayout layout_item;
		ImageView product_pic;
		TextView product_title;
		
		public ViewHolder(View v){
			layout_item = (RelativeLayout) v.findViewById(R.id.layout_item);
			product_pic = (ImageView) v.findViewById(R.id.product_pic);
			product_title = (TextView) v.findViewById(R.id.product_title);
		}
	}

}
