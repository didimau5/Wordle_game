
import java.util.ArrayList;

/**
 *
 * @author didac
 */
class wordleUtilities {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
    static ArrayList gameColor(String newWord, String misteryWord){
		final String GREEN = "\u001B[092m";
		final String YELLOW = "\u001B[093m";
		final String RED = "\u001B[091m";
		final String RESET = "\u001B[0m";
		ArrayList<String>testedWord = new ArrayList<>();
		for (int i = 0; i < misteryWord.length(); i++) {
			int j = i+1;
			String m = misteryWord.substring(i, j);
			String n = newWord.substring(i, j);
			
				if(n.equals(m)){
					testedWord.add(GREEN+n+RESET);
					}
				else{
					if(misteryWord.contains(n)){
						testedWord.add(YELLOW+n+RESET);
					}
					else{
						testedWord.add(RED+n+RESET);
					}
				}
				
		}
		return testedWord;
	}
}
