package club.claycoffee.ClayTech.utils;

public class StrUtils {
	public static String getLeftStr(String bep, String leftstr) {
		if (bep.contains(leftstr)) {
			int index = bep.indexOf(leftstr);
			index -= 1;
			String str = "";
			char[] ca = bep.toCharArray();
			for (int i = 0; i <= index; i++) {
				str += ca[i];
			}
			return str;
		} else {
			return null;
		}
	}

	public static String getRightStr(String bep, String rightstr) {
		if (bep.contains(rightstr)) {
			int index = bep.indexOf(rightstr);
			index += rightstr.length();
			index += 1;
			String str = "";
			char[] ca = bep.toCharArray();
			for (; index <= bep.length(); index++) {
				str += ca[index - 1];
			}
			return str;
		} else {
			return null;
		}
	}

}
