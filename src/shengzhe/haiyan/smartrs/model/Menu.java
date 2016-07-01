package shengzhe.haiyan.smartrs.model;

import java.util.ArrayList;

public class Menu extends BaseBean{

	public ArrayList<MenuList> result;
	
	public static class MenuList{
		public int id;
		public int province_id;
		public String province_name;
		public int area_id;
		public String area_name;
		public int type_id;
		public String type_name;
		public String scenic_name;
		public String introduce;
		public String opentime;
		public String price;
		public String besttime;
		public String phone;
		public String traffic;
		public String picurl;
		public String complain_phone;
	}
	
}
