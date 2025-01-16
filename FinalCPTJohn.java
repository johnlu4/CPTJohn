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
		String strMenu;
		Boolean boolAdvantage = false;
		
		con.println("What is your name?");
		strUsername = con.readLine();
		
		
		
		con.clear();
		
		BufferedImage imgGuesstheWord = con.loadImage("GuesstheWord.png");
		
		con.drawImage(imgGuesstheWord, 440, 25);
		con.drawString("(P)lay", 285,420);
		con.drawString("(H)elp", 535,520);
		con.drawString("(E)xit", 785,420);
		con.drawRoundRect(250, 400, 200, 70, 9, 9);
		con.drawRoundRect(500, 500, 200, 70, 9, 9);
		con.drawRoundRect(750, 400, 200, 70, 9, 9);
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
		String strSecretWordArray[][];
		if(strSecret.contains(" ")){
			String[] strSplitWordArray = strSecret.split(" ");
			strSecretWordArray = new String[strSecret.length()][2];
		
			for(intCount = 0; intCount < strSecret.length(); intCount++){
				strSecretWordArray[intCount][0] = Character.toString(strSecret.charAt(intCount));
				System.out.println(strSecretWordArray[intCount][0]);
			} 
		
			for(intCount = 0; intCount < strSecret.length(); intCount++){
				int intRand = (int)(Math.random() * 100 + 1);
				strSecretWordArray[intCount][1] = String.valueOf(intRand);
				System.out.println(strSecretWordArray[intCount][1]);
			} 	
		}else{
			strSecretWordArray = new String[strSecret.length()][2];
		
			for(intCount = 0; intCount < strSecret.length(); intCount++){
				strSecretWordArray[intCount][0] = Character.toString(strSecret.charAt(intCount));
				System.out.println(strSecretWordArray[intCount][0]);
			} 
		
			for(intCount = 0; intCount < strSecret.length(); intCount++){
				int intRand = (int)(Math.random() * 100 + 1);
				strSecretWordArray[intCount][1] = String.valueOf(intRand);
				System.out.println(strSecretWordArray[intCount][1]);
			} 	
		}
		
		String strScrambledWord = ScrambleString(strSecretWordArray, strSecret.length());
		System.out.println(strScrambledWord);
		int intTries = strSecret.length() - 4;
		boolean boolGuessed = false;
		String strGuessedWord;
		
		con.println(strScrambledWord);
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
	
	
	public static String ScrambleString(String strArray2D[][], int intWordLength){
		String strTempTheme;
		String strTempOrder;
		int intRow3;
		int intRow2;
		int intRow;
		String strScrambledWord = "";
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
		for(intRow3 = 0; intRow3 < intWordLength; intRow3++){
			strScrambledWord = strScrambledWord + strArray2D[intRow3][0];
		}
	
		return strScrambledWord;
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
