package shengzhe.haiyan.smartrs;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ServerUrl {
//	public static final String SERVER_ROOT_PATH = "http://localhost:8080/XLJ2/";
	public static final String SERVER_ROOT_PATH = "http://192.168.1.108:8080/Go/";
	// http://localhost:8080/XLJ2/GetAllMenu?page=0
	public static final String GET_ALL_MENU = SERVER_ROOT_PATH
			+ "GetAllMenu?page=";
	// http://localhost:8080/XLJ2/GetAllFoodtype
	public static final String GET_ALL_FOODTYPE = SERVER_ROOT_PATH
			+ "GetAllScenicstype";
	public static final String GET_ALL_PROVINCE = SERVER_ROOT_PATH
			+ "GetAllProvince";
	// http://localhost:8080/XLJ2/GetTypeByFatherpoint?fatherpoint=42
	public static final String GET_TYPE_BY_FATHERPOINT = SERVER_ROOT_PATH
			+ "GetTypeByFatherpoint?fatherpoint=";

	// http://localhost:8080/XLJ2/GetMenuByType?type=44
	public static final String GET_MENU_BY_TYPE = SERVER_ROOT_PATH
			+ "GetMenuByType?type=";

	public static final String GET_MENU_BY_AREAID = SERVER_ROOT_PATH
			+ "GetMenuByAreaId?area_id=";
	
	// http://192.168.1.108:8080/XLJ2/GetMenuByLikeName?likename=æ±?

	public static final String GET_MENU_BY_LIKE_NAME = SERVER_ROOT_PATH
			+ "GetMenuByLikeName?likename=";
	public static final String GET_MENU_BY_TYPE_NAME = SERVER_ROOT_PATH
			+ "GetMenuByTypeName?type_name=";
	// http://192.168.1.108:8080/XLJ2/Login?name=aaa&&pwd=12345
	public static final String LOGIN = SERVER_ROOT_PATH + "Login";
	public static final String LOGIN_OUT = SERVER_ROOT_PATH + "LoginOut";
	public static final String REGISTER = SERVER_ROOT_PATH + "Register";
//	http://192.168.1.108:8080/XLJ2/UpdatePlaceById?place=
	public static final String UPDATE_PLACE = SERVER_ROOT_PATH
			+ "UpdatePlaceById?place=";

	public static final String UPDATE_DATE = SERVER_ROOT_PATH
			+ "UpdateDataById?birthday=";

	public static final String UPDATE_NAME = SERVER_ROOT_PATH
			+ "UpdateNameById?name=";
	
	public static final String UPDATE_SEX = SERVER_ROOT_PATH
			+ "UpdateSexById?sex=";
	
	//http://192.168.1.108:8080/XLJ2/UpdateHabitusById?habitus=å¹³å’Œè´?&&id=2
	public static final String UPDATE_HABITUS = SERVER_ROOT_PATH
			+ "UpdateHabitusById?habitus=";
	
	public static final String UPDATE_INTRO = SERVER_ROOT_PATH
			+ "UpdateIntroById?intro=";
	
	public static final String UPDATE_PHONE = SERVER_ROOT_PATH
			+ "UpdatePhoneById?phone=";
	
	//http://192.168.1.108:8080/XLJ2/GetLovefoodById?id=1
	public static final String GET_LOVEMENU_BY_ID = SERVER_ROOT_PATH
			+ "GetLoveScenicsById?id=";
	
	public static final String QUERY_MENU_IS_EXIST = SERVER_ROOT_PATH
			+ "FindMenuisExist?user_id=";
	//http://192.168.1.108:8080/XLJ2/GetRecommendMenu2?habitus=é˜´è™šè´?&&user_id=1
	public static final String QUERY_RECOMMEND_MENU = SERVER_ROOT_PATH
			+ "GetRecommendMenu2?habitus=";
	public static final String ADD_MENU_TO_LOVEFOOD = SERVER_ROOT_PATH
			+ "AddMenuToLoveScenics?user_id=";
	public static final String DELETE_MENU_TO_LOVEFOOD = SERVER_ROOT_PATH
			+ "DeleteMenuToLoveScenics?user_id=";

	public static String getEncodeName(String n) {
		// TODO Auto-generated method stub
		String name = null;
		try {
			name = URLEncoder.encode(n, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
}
