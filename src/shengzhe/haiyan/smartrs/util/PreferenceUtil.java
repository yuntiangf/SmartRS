package shengzhe.haiyan.smartrs.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtil {

	private static Context mContext;
	public static String USER = "user_info";
	private static SharedPreferences sharedPreferences;

	public static void initialize(Context context) {
		mContext = context;
	}

	public static void saveUserPrefer(String username, String password, String sex,
			String habitus, String headerurl, String bothday, String place,
			String intro, String phone, int uid, int status) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.putString("sex", sex);
		editor.putString("habitus", habitus);
		editor.putString("headerurl", headerurl);
		editor.putString("bothday", bothday);
		editor.putString("place", place);
		editor.putString("intro", intro);
		editor.putString("phone", phone);
		editor.putInt("uid", uid);
		editor.putInt("status", status);
		
		editor.commit();
	}

	public static String getUserName() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String user = sharedPreferences.getString("username", null);
		return user;
	}

	public static void setUserName(String username){
		sharedPreferences = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("username", username);
		editor.commit();
	}
	
	public static String getUserPwd() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String password = sharedPreferences.getString("password", null);
		return password;
	}

	public static void setUserPwd(String pwd){
		sharedPreferences = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("password", pwd);
		editor.commit();
	}
	
	public static String getSex() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String password = sharedPreferences.getString("sex", null);
		return password;
	}

	public static void setSex(String sex){
		sharedPreferences = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("sex", sex);
		editor.commit();
	}
	
	public static String getHabitus() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String habitus = sharedPreferences.getString("habitus", null);
		return habitus;
	}

	public static void setHabitus(String habitus) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("habitus", habitus);
		editor.commit();
	}
	
	public static String getHeaderurl() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String headerurl = sharedPreferences.getString("headerurl", null);
		return headerurl;
	}

	public static void setHeaderurl(String url) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("headerurl", url);
		editor.commit();
	}

	public static String getBothday() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String place = sharedPreferences.getString("bothday", null);
		return place;
	}

	public static void setBothday(String bothday) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("bothday", bothday);
		editor.commit();
	}
	
	public static String getPlace() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String place = sharedPreferences.getString("place", null);
		return place;
	}

	public static void setPlace(String place) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("place", place);
		editor.commit();
	}
	
	public static String getIntro() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String intro = sharedPreferences.getString("intro", null);
		return intro;
	}

	public static void setIntro(String intro) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("intro", intro);
		editor.commit();
	}
	
	public static String getPhone() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		String intro = sharedPreferences.getString("phone", null);
		return intro;
	}

	public static void setPhone(String phone) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("phone", phone);
		editor.commit();
	}
	
	public static int getUid() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		int uid = sharedPreferences.getInt("uid", 0);
		return uid;
	}
	
	public static int getUserStatus() {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		int userstatus = sharedPreferences.getInt("status", -1);
		return userstatus;
	}
	
	public static void setUserStatus(int status) {
		sharedPreferences = mContext.getSharedPreferences(USER,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putInt("status", status);
		editor.commit();
	}

}
