import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * @author didac
 */
public class Wordle {

	/**
	 * @param args the command line arguments
	 * @throws java.lang.Exception
	 */
	public static void main(String[] args) throws Exception {

		//One Array to save the coloured attempts and another to load the dictionary
		ArrayList<ArrayList>attemptsLog = new ArrayList<>();
		ArrayList<String>dictionary = new ArrayList<>();
		
		//Calling the file & adding to my dictionary ArrayList.
		File file = new File("dic.txt");
		Scanner in = new Scanner(file);
		while (in.hasNextLine()){
			dictionary.add(in.nextLine());
		}
		in.close();
		
		//random chosen word.
		final String misteryWord = dictionary.get((int)(Math.random()*dictionary.size()));
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Guess the " + misteryWord.length() + "-lettered word");
		String newWord = sc.nextLine().toLowerCase();
		
		//main conditional of the game.
		while(!newWord.equals(misteryWord)){
			
			if (Wordle.helpMe(newWord, sc)) {
				newWord = sc.next().toLowerCase();
				continue;
			}
			//:giveup condition.
			if (Wordle.giveUp(newWord, misteryWord)) {
				return;
			}
			//Input validation.
			newWord = Wordle.isShorter(misteryWord, newWord, sc);
			newWord = Wordle.isLonger(newWord, misteryWord);
			newWord = Wordle.isExisting(newWord, sc);
			newWord = Wordle.isWithInDictionary(newWord, sc, dictionary);
			
			//saving the return value from gameColor to my attempts ArrayList.
			ArrayList temp = gameColor(newWord, misteryWord);
			attemptsLog.add(temp);
			System.out.println("\n");
			System.out.println("****************************");
			for ( ArrayList element: attemptsLog ){
				for(Object i : element){
					System.out.print(i);
				}
				System.out.print("\n");
			}
			System.out.println("GUESS AGAIN!");
			newWord = sc.nextLine().toLowerCase();
		}
		//Exiting & printing out when you win.
		System.out.println("Well done, you won the game! The number of "
				+ "attempts is " + attemptsLog.size());
	}
	
	/**
	 * Method to give colours to the resulting words.
	 * @param newWord
	 * @param misteryWord
	 * @return ArrayList with coloured letters colour coded to meet the game needs.
	 */
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
	
	/**
	 * Method to check if input is longer than it should, then chops it.
	 * @param newWord
	 * @param misteryWord
	 * @return newWord sub string to meet maximum length criteria
	 */
	static String isLonger(String newWord, String misteryWord){
		if (misteryWord.length() < newWord.length()) {
			System.out.println("Such tryhard, we are only considering '" 
					+ newWord.substring(0, misteryWord.length()) + "' you just lost an attempt");
		}
		return newWord;
	}
	
	/**
	 * Method to check if input is shorter than it should.
	 * @param misteryWord
	 * @param newWord
	 * @param sc
	 * @return newWord only if it meets minimum length conditions.
	 */
	static String isShorter(String misteryWord,String newWord,Scanner sc){
		while(misteryWord.length() > newWord.length()){
				System.out.println("That's not long enough.");
				newWord = sc.nextLine().toLowerCase();
				if (misteryWord.length() == newWord.length()) {
					break;
				}
			}
		return newWord;
	}	
	
	/**
	 * Method to check if word provided doesn't have numbers or special characters.
	 * @param newWord
	 * @param sc
	 * @return newWord if it is composed of a-z letters.
	 */
	static String isExisting(String newWord, Scanner sc){
		String vocabulary = "qwertyuiopasdfghjklzxcvbnmñ-";
		
		for (int i = 0; i < newWord.length(); i++) {
			int j = i+1;
			String n = newWord.substring(i, j);
			while(!(vocabulary.contains(n))){
				System.out.println("Please choose an actual word.");
				newWord = sc.nextLine().toLowerCase();
				if (!(vocabulary.contains(n))) {
					break;
				}
			}
		}
		return newWord;
	}
	
	/**
	 * Method to check if the word is in the dictionary.
	 * @param newWord
	 * @param sc
	 * @param dictionary
	 * @return newWord if it exists in the dictionary provided.
	 */
	static String isWithInDictionary(String newWord, Scanner sc, ArrayList dictionary){
			while(!(dictionary.contains(newWord))){
				System.out.println("This word is not in the Dictionary");
				newWord = sc.nextLine().toLowerCase();
				if (dictionary.contains(newWord)) {
					break;
				}
			}
		return newWord;
	}
	
	/**
	 * Method to implement the :help call.
	 * @param newWord
	 * @param sc
	 * @return true if :helpMe is invoked.
	 */
	static boolean helpMe(String newWord, Scanner sc){
		boolean result = false;
		if (newWord.equals(":help")) {
				System.out.println("""
								   Oh dear, so you need help. There is not much to be explained really.
								   I have chosen a word and you kinda need to guess it. 
								   I have been printing the words you choose and giving you some hints.
								   
								   Green means it's the right letter in the right spot. 
								   Yellow means it's the right letter in the wrong spot. 
								   Red means it's the wrong letter, so don't keep trying that one. 
								   
								   @Rememeber, if you get frustrated, you can always :giveup :D""");
				result = true;
			}
		return result;
	}
	
	/**
	 * Method to implement the :giveUp call.
	 * @param newWord
	 * @param misteryWord
	 * @return true if :give up is called and ends the program.
	 */
	static boolean giveUp(String newWord, String misteryWord){
		boolean result = false;
		if (newWord.equals(":giveup")) {
				System.out.println("I guess you couldnt endure the pressure"
						+ ", the word was: " + misteryWord);
				result = true;
			}
		return result;
	}
}

