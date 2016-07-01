package shengzhe.haiyan.smartrs.model;

import java.util.ArrayList;

public class Foodtype extends BaseBean{

	public ArrayList<FoodtypeList> result;
	
	public static class FoodtypeList{
		public int id;
		public String province_name;
		public int province_id;
	}
	
}
