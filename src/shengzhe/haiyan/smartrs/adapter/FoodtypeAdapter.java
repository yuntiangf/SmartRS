package shengzhe.haiyan.smartrs.adapter;

import java.util.List;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.model.Foodtype;
import shengzhe.haiyan.smartrs.model.Foodtype.FoodtypeList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FoodtypeAdapter extends BaseAdapter {

	private List<Foodtype.FoodtypeList> listData;
	private Context context;
	private FoodtypeList foodtypeResult;
	
	private Integer currentPos;
	
	public void setCurrentPos(Integer pos){
		this.currentPos = pos;
	}
	
	public FoodtypeAdapter(List<FoodtypeList> listData, Context context) {
		super();
		this.listData = listData;
		this.context = context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder holder = null;
		
		foodtypeResult = listData.get(position);
		if(convertView == null){
			view = LayoutInflater.from(context).inflate(R.layout.foodtype_item, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}else{
			view = convertView;
		}
		
		holder = (ViewHolder) view.getTag();
		holder.type_name.setText(foodtypeResult.province_name);
		
		if(currentPos != null){
			if(currentPos == position){
				holder.rl_type_bg.setBackgroundResource(R.drawable.listview_selector_false);
				holder.img_selected.setVisibility(View.VISIBLE);
			}else{
				holder.rl_type_bg.setBackgroundResource(R.drawable.listview_selector2);
				holder.img_selected.setVisibility(View.GONE);
			}
		}
		
		return view;
	}
	
	class ViewHolder{
		ImageView img_selected;
		TextView type_name;
		RelativeLayout rl_type_bg;
		public ViewHolder(View v){
			img_selected = (ImageView) v.findViewById(R.id.img_selected);
			type_name = (TextView) v.findViewById(R.id.type_name);
			rl_type_bg = (RelativeLayout) v.findViewById(R.id.rl_type_bg);
		}
	}

}
