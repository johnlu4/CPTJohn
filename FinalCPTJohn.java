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
		int WordsLength = 0;
		
		while(theme.eof() == false){
			theme.readLine();
			WordsLength += 1;
		}
		
		while(WordsLength == 0){
			con.println("Invalid theme, please choose a theme you want to play.");
			strSelectedTheme = con.readLine();
			theme = new TextInputFile(strSelectedTheme+".txt");
			while(theme.eof() == false){
				theme.readLine();
				WordsLength += 1;
			}
		}
		
		theme.close();
		
		theme = new TextInputFile(strSelectedTheme+".txt");
		String WordsArray[][] = new String[WordsLength][2];
		int intLength = 0;
		
		while(theme.eof() == false){
			WordsArray[intLength][0] = theme.readLine();
			intLength += 1;
		}
		
		int intCount;
		
		for(intCount = 0; intCount < WordsLength; intCount++){
			int intRand = (int)(Math.random() * 100 + 1);
			WordsArray[intCount][1] = String.valueOf(intRand);
		} 
		
		String strSecret = GetRandomized(WordsArray);
		String strScrambledWord = new String[strSecret.length()][2];
		int intTries = strSecret.length() - 4;
		boolean boolGuessed = false;
		String strGuessedWord;
		
		con.println(strSelectedWord);
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
	
	public static String ScrambleArrayIntoString  (String array2D[][]){
		String strScrambledWord = "":
		
		String strTempTheme;
		String strTempOrder;
		int intRow2;
		int intRow;
		
		for(intRow2 = 0; intRow2 < 10-1; intRow2++){
			for(intRow = 0; intRow < 10-1-intRow2; intRow++){
			// Bubble sort. If left is bigger than right
			if(Integer.parseInt(array2D[intRow][1]) > Integer.parseInt(array2D[intRow+1][1])){
				// Take the left item
				strTempTheme = array2D[intRow][0];
				strTempOrder = array2D[intRow][1];
				// Right item moves to the left
				array2D[intRow][0] = array2D[intRow + 1][0];
				array2D[intRow][1] = array2D[intRow + 1][1];
				// Put temporary value on the right.
				array2D[intRow+1][0] = strTempTheme;
				array2D[intRow+1][1] = strTempOrder;
				}
			}
		}
		
		
		
		return "Yes";
	}
	
	public static String GetRandomized(String array2D[][]){
		String strTempTheme;
		String strTempOrder;
		int intRow2;
		int intRow;
		for(intRow2 = 0; intRow2 < 10-1; intRow2++){
			for(intRow = 0; intRow < 10-1-intRow2; intRow++){
			// Bubble sort. If left is bigger than right
			if(Integer.parseInt(array2D[intRow][1]) > Integer.parseInt(array2D[intRow+1][1])){
				// Take the left item
				strTempTheme = array2D[intRow][0];
				strTempOrder = array2D[intRow][1];
				// Right item moves to the left
				array2D[intRow][0] = array2D[intRow + 1][0];
				array2D[intRow][1] = array2D[intRow + 1][1];
				// Put temporary value on the right.
				array2D[intRow+1][0] = strTempTheme;
				array2D[intRow+1][1] = strTempOrder;
				}
			}
		}
		return array2D[0][0];
	}
}
