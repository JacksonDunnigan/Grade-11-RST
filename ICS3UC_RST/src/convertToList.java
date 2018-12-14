//this converts a string of letter into a list
public class convertToList {
	public static void main(String[] args) {
		String[] array = new String[26];
		String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String output = "{";
		String Quote = "\"";
		
		for (int i = 0; i < array.length; i++) {
			array[i] =""+word.charAt(i);
		}
		
		for (int i = 0; i < array.length; i++) {
			output +=Quote+word.charAt(i)+Quote+",";
		}
		output+="}";
		
		System.out.println(output);
	}
}
