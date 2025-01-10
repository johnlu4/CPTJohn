import arc.*;
import java.awt.*;
import java.awt.image.*;


public class FinalCPTJohn{
	public static void main(String[] args){
		Console con = new Console("Guess the word", 1280,720);
		//con.setBackgroundColor(Color.WHITE);
		//con.setTextColor(Color.BLACK);
		TextInputFile themes = new TextInputFile("themes.txt");
		
		String strUsername;
		String strSelectedTheme;
		Boolean boolAdvantage = false;
		con.println("What is your name?");
		strUsername = con.readLine();
		
		
		while(themes.eof() == false){
			con.println(themes.readLine());
			//ThemeLength += 1;
		}
		
		themes.close();
		con.println("Which theme do you want to play?");
		strSelectedTheme = con.readLine();
		
		TextInputFile theme = new TextInputFile(strSelectedTheme+".txt");
		int intWordsLength = 0;
		
		while(theme.eof() == false){
			theme.readLine();
			intWordsLength += 1;
		}
		
		while(intWordsLength == 0){
			con.println("Invalid theme, please choose a theme you want to play.");
			strSelectedTheme = con.readLine();
			theme = new TextInputFile(strSelectedTheme+".txt");
			while(theme.eof() == false){
				theme.readLine();
				intWordsLength += 1;
			}
		}
		
		theme.close();
		
		theme = new TextInputFile(strSelectedTheme+".txt");
		String strWordsArray[][] = new String[intWordsLength][2];
		int intLength = 0;
		
		while(theme.eof() == false){
			strWordsArray[intLength][0] = theme.readLine();
			intLength += 1;
		}
		
		int intCount;
		
		for(intCount = 0; intCount < intWordsLength; intCount++){
			int intRand = (int)(Math.random() * 100 + 1);
			strWordsArray[intCount][1] = String.valueOf(intRand);
		} 
		
		String strSecret = GetRandomized(strWordsArray, intWordsLength);
		
		
		String strScrambledWord = ScrambleString(con, strSecret, strSecret.length());
		int intTries = strSecret.length() - 4;
		boolean boolGuessed = false;
		String strGuessedWord;
		
		con.println(strSecret + strScrambledWord);
		if(strUsername.toLowerCase().equals("statitan")){
			intTries += 2;
			con.println("You have "+intTries+" Tries. (+2 more with 'advantage')");
		}else{
			con.println("You have "+intTries+" Tries. ");
		}
		while(intTries > 0 && boolGuessed == false){
			strGuessedWord = con.readLine();
			if(strGuessedWord.toLowerCase().equals(strSecret.toLowerCase())){
				boolGuessed = true;
			}else{
				intTries -= 1;
				con.println("Wrong, you have " + intTries + " Tries left, guess again.");
			}
		}
		if(boolGuessed == true){
			con.println("You guessed it right!");
		}else{
			con.println("You suck balls, the word was: "+strSecret+".");
		}
	}
	
	public static String ScrambleString(Console con, String word, int intLength){
		char[] charWordArray = word.toCharArray();
		
		
		
		return "Yes";
	}
	
	public static String GetRandomized(String strArray2D[][], int intWordLength){
		String strTempTheme;
		String strTempOrder;
		int intRow2;
		int intRow;
		for(intRow2 = 0; intRow2 < intWordLength-1; intRow2++){
			for(intRow = 0; intRow < intWordLength-1-intRow2; intRow++){
			// Bubble sort. If left is bigger than right
			if(Integer.parseInt(strArray2D[intRow][1]) > Integer.parseInt(strArray2D[intRow+1][1])){
				// Take the left item
				strTempTheme = strArray2D[intRow][0];
				strTempOrder = strArray2D[intRow][1];
				// Right item moves to the left
				strArray2D[intRow][0] = strArray2D[intRow + 1][0];
				strArray2D[intRow][1] = strArray2D[intRow + 1][1];
				// Put temporary value on the right.
				strArray2D[intRow+1][0] = strTempTheme;
				strArray2D[intRow+1][1] = strTempOrder;
				}
			}
		}
	
		return strArray2D[0][0];
	}
}
