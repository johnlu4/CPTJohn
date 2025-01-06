import arc.*;

public class FinalCPTJohn{
	public static void main(String[] args){
		Console con = new Console();
		con.println("hi. here's johny");
		
		TextInputFile themes = new TextInputFile("themes.txt");
		
		int ThemeLength;
		
		ThemeLength = 0;
		
		while(themes.eof() == false){
			themes.readLine();
			ThemeLength += 1;
		}
		
		String ThemeArray[][] = new String[ThemeLength][2];
		String SelectedTheme = RandomizeAndGet(ThemeArray);
		
		themes.close();
	}
	
	public static String RandomizeAndGet(String array2D[][]){
		int strTempTheme;
		int strTempOrder;
		int intRow2;
		for(intRow2 = 0; intRow2 < 10-1; intRow2++){
			for(intRow = 0; intRow < 10-1-intRow2; intRow++){
			// Bubble sort. If left is bigger than right
			if(Integer.parseInt(strShop[intRow][1]) > Integer.parseInt(strShop[intRow+1][1])){
				// Take the left item
				strTempTheme = strShop[intRow][0];
				strTempOrder = strShop[intRow][1];
				// Right item moves to the left
				strShop[intRow][0] = strShop[intRow + 1][0];
				strShop[intRow][1] = strShop[intRow + 1][1];
				// Put temporary value on the right.
				strShop[intRow+1][0] = strTempName;
				strShop[intRow+1][1] = strTempPrice;
				}
			}
		}
		return "Yes";
	}
}
