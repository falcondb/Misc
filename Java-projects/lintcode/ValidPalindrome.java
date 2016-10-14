
public class ValidPalindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		//String input = "A man, a plan, a canal: Panama";
		String input = "race a car";
		
		input = clean(input);
		
		if (input == null || input.length() == 0)
			System.out.println(true);
		else{
			
			System.out.println(isPalindrome(input));
			
		}
		
	}

	public static String clean(String in){
		StringBuffer cleaned = new StringBuffer();
		if (in == null)
			return null;
		in = in.toLowerCase();
		for (int i = 0; i < in.length(); ++i){
			if (in.charAt(i) >= 'a' && in.charAt(i) <='z'){
				cleaned.append(in.charAt(i));
			}
				
		}
		return cleaned.toString();
	}
	
	public static boolean isPalindrome(String in){
		
		if(in.length()%2 == 0){
			int center = in.length() /2 -1;
			for (int dis = 0; center - dis >= 0; dis++ ){
				
				if (in.charAt(center - dis) != in.charAt(center + 1 + dis))
					return false;
			}
		}
		else{
			int center = in.length()/2;
			for (int dis = 0; center -dis >=0; dis ++){
				if (in.charAt(center - dis) != in.charAt(center + dis))
					return false;
			}
			
		}
		return true;
	}
}
