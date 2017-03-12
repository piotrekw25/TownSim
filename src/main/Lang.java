package main;

public class Lang {

	//Languages
	public static int en = 0;
	public static int pl = 1;
	public static String english;
	public static String polish;

	//Menu strings
	public static String resume;
	public static String newGame;
	public static String loadGame;
	public static String options;
	public static String credits;
	public static String quit;
	public static String back;
	public static String language;

	
	public static int currentLanguage = en;
	
	public static void init() {
		if(currentLanguage == en) {
			resume = "Resume";
			newGame = "New Game";
			loadGame = "Load Game";
			options = "Options";
			credits = "Credits";
			quit = "Quit";
			back = "Back";
			language = "Language";
			
			english = "English";
			polish = "Polish";
			
		}
		if(currentLanguage == pl) {
			resume = "Wzn�w gr�";
			newGame = "Nowa gra";
			loadGame = "Wczytaj gr�";
			options = "Opcje";
			credits = "Autorzy";
			quit = "Wyjd�";
			back = "Powr�t";
			language = "J�zyk";
			
			english = "Angielski";
			polish = "Polski";
		}
	}
	
	public static void changeLanguage(int language) {
		currentLanguage = language;
		init();
		
	}
	
}
