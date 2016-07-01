package shengzhe.haiyan.smartrs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Utils {

	// ÃÜÂë¸ñÊ½¼ì²â
	public static boolean checkPwd(String ed_pwd) {
		Pattern pattern = Pattern.compile("[A-Z,a-z,0-9,-,_]*");
		Matcher m = pattern.matcher(ed_pwd);
		return m.matches();
	}
}
