package main;

public class Lang {

	//Languages
	public static int english = 0;
	public static int polish = 1;
	

	//Menu strings
	public static String resume;
	public static String newGame;
	public static String loadGame;
	public static String options;
	public static String credits;
	public static String quit;
	
	
	public static int currentLanguage = english;
	
	public static void init() {
		if(currentLanguage == english) {
			resume = "Resume";
			newGame = "New Game";
			loadGame = "Load Game";
			options = "Options";
			credits = "Credits";
			quit = "Quit";
		}
		if(currentLanguage == polish) {
			resume = "Wznów grê";
			newGame = "Nowa gra";
			loadGame = "Wczytaj grê";
			options = "Opcje";
			credits = "Autorzy";
			quit = "WyjdŸ";
		}
	}
	
	public void changeLanguage(int language) {
		currentLanguage = language;
		init();
		
	}
	
}
