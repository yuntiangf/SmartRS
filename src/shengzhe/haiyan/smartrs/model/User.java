package shengzhe.haiyan.smartrs.model;

import java.util.ArrayList;

public class User extends BaseBean{

	public ArrayList<UserList> result;
	public String message;
	public static class UserList{
		public int id;
		public String name;
		public String pwd;
		public String sex;
		public String habitus;
		public String headerurl;
		public String bothday;
		public String place;
		public String intro;
		public String phone;
	}
	
}
