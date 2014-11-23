package us.test.stuff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFb {
	
	public static void main(String[] args) {
		String value= "";
		Pattern pattern = Pattern.compile(
				"^(http(s)?\\:\\/\\/)?(www\\.)?(youtube\\.com|youtu\\.?be)\\/(v\\=)?.+$");
		Matcher matcher = pattern.matcher("https://www.youtube.com/watch?v=Rw-2n9hQZIw");
		
		if (matcher.find()) {
			value = matcher.group();
		}
		
		System.out.println(value);
	}
}
